package com.urbanairship.remoteconfig;

import android.content.Context;
import com.urbanairship.AirshipComponent;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.reactive.Function;
import com.urbanairship.reactive.Subscriber;
import com.urbanairship.reactive.Subscription;
import com.urbanairship.remotedata.RemoteData;
import com.urbanairship.remotedata.RemoteDataPayload;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public class RemoteConfigManager extends AirshipComponent {
    private static final String AIRSHIP_CONFIG_KEY = "airship_config";
    /* access modifiers changed from: private */
    public static Comparator<RemoteDataPayload> COMPARE_BY_PAYLOAD_TYPE = new Comparator<RemoteDataPayload>() {
        public int compare(RemoteDataPayload remoteDataPayload, RemoteDataPayload remoteDataPayload2) {
            if (remoteDataPayload.getType().equals(remoteDataPayload2.getType())) {
                return 0;
            }
            return remoteDataPayload.getType().equals(RemoteConfigManager.CONFIG_TYPE_COMMON) ? -1 : 1;
        }
    };
    private static final String CONFIG_TYPE_AMAZON = "app_config:amazon";
    private static final String CONFIG_TYPE_ANDROID = "app_config:android";
    private static final String CONFIG_TYPE_COMMON = "app_config";
    private static final String DISABLE_INFO_KEY = "disable_features";
    private Collection<RemoteAirshipConfigListener> listeners;
    private final ModuleAdapter moduleAdapter;
    private RemoteAirshipConfig remoteAirshipConfig;
    private final RemoteData remoteData;
    private Subscription subscription;

    public RemoteConfigManager(Context context, PreferenceDataStore preferenceDataStore, RemoteData remoteData2) {
        this(context, preferenceDataStore, remoteData2, new ModuleAdapter());
    }

    RemoteConfigManager(Context context, PreferenceDataStore preferenceDataStore, RemoteData remoteData2, ModuleAdapter moduleAdapter2) {
        super(context, preferenceDataStore);
        this.listeners = new CopyOnWriteArraySet();
        this.remoteData = remoteData2;
        this.moduleAdapter = moduleAdapter2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.subscription = this.remoteData.payloadsForTypes(CONFIG_TYPE_COMMON, UAirship.shared().getPlatformType() == 1 ? CONFIG_TYPE_AMAZON : CONFIG_TYPE_ANDROID).map(new Function<Collection<RemoteDataPayload>, JsonMap>() {
            public JsonMap apply(Collection<RemoteDataPayload> collection) {
                ArrayList<RemoteDataPayload> arrayList = new ArrayList<>(collection);
                Collections.sort(arrayList, RemoteConfigManager.COMPARE_BY_PAYLOAD_TYPE);
                JsonMap.Builder newBuilder = JsonMap.newBuilder();
                for (RemoteDataPayload data : arrayList) {
                    newBuilder.putAll(data.getData());
                }
                return newBuilder.build();
            }
        }).subscribe(new Subscriber<JsonMap>() {
            public void onNext(JsonMap jsonMap) {
                try {
                    RemoteConfigManager.this.processConfig(jsonMap);
                } catch (Exception e) {
                    Logger.error(e, "Failed to process remote data", new Object[0]);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void processConfig(JsonMap jsonMap) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        JsonValue jsonValue = JsonValue.NULL;
        for (String next : jsonMap.keySet()) {
            JsonValue opt = jsonMap.opt(next);
            if (AIRSHIP_CONFIG_KEY.equals(next)) {
                jsonValue = opt;
            } else if (DISABLE_INFO_KEY.equals(next)) {
                Iterator<JsonValue> it = opt.optList().iterator();
                while (it.hasNext()) {
                    try {
                        arrayList.add(DisableInfo.fromJson(it.next()));
                    } catch (JsonException e) {
                        Logger.error(e, "Failed to parse remote config: %s", jsonMap);
                    }
                }
            } else {
                hashMap.put(next, opt);
            }
        }
        updateRemoteAirshipConfig(jsonValue);
        apply(DisableInfo.filter(arrayList, UAirship.getVersion(), UAirship.getAppVersion()));
        HashSet<String> hashSet = new HashSet<>(Modules.ALL_MODULES);
        hashSet.addAll(hashMap.keySet());
        for (String str : hashSet) {
            JsonValue jsonValue2 = (JsonValue) hashMap.get(str);
            if (jsonValue2 == null) {
                this.moduleAdapter.onNewConfig(str, (JsonMap) null);
            } else {
                this.moduleAdapter.onNewConfig(str, jsonValue2.optMap());
            }
        }
    }

    public void addRemoteAirshipConfigListener(RemoteAirshipConfigListener remoteAirshipConfigListener) {
        this.listeners.add(remoteAirshipConfigListener);
    }

    private void updateRemoteAirshipConfig(JsonValue jsonValue) {
        this.remoteAirshipConfig = RemoteAirshipConfig.fromJson(jsonValue);
        notifyRemoteConfigUpdated();
    }

    private void notifyRemoteConfigUpdated() {
        for (RemoteAirshipConfigListener onRemoteConfigUpdated : this.listeners) {
            onRemoteConfigUpdated.onRemoteConfigUpdated(this.remoteAirshipConfig);
        }
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        super.tearDown();
        Subscription subscription2 = this.subscription;
        if (subscription2 != null) {
            subscription2.cancel();
        }
    }

    private void apply(List<DisableInfo> list) {
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>(Modules.ALL_MODULES);
        long j = 0;
        for (DisableInfo next : list) {
            hashSet.addAll(next.getDisabledModules());
            hashSet2.removeAll(next.getDisabledModules());
            j = Math.max(j, next.getRemoteDataRefreshInterval());
        }
        for (String componentEnabled : hashSet) {
            this.moduleAdapter.setComponentEnabled(componentEnabled, false);
        }
        for (String componentEnabled2 : hashSet2) {
            this.moduleAdapter.setComponentEnabled(componentEnabled2, true);
        }
        this.remoteData.setForegroundRefreshInterval(j);
    }
}
