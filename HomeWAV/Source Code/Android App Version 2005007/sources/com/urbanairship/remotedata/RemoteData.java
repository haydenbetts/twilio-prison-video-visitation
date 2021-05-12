package com.urbanairship.remotedata;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Handler;
import android.os.HandlerThread;
import androidx.core.content.pm.PackageInfoCompat;
import com.urbanairship.AirshipComponent;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.app.SimpleApplicationListener;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.locale.LocaleChangedListener;
import com.urbanairship.locale.LocaleManager;
import com.urbanairship.push.PushListener;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushMessage;
import com.urbanairship.reactive.Function;
import com.urbanairship.reactive.Observable;
import com.urbanairship.reactive.Schedulers;
import com.urbanairship.reactive.Subject;
import com.urbanairship.reactive.Supplier;
import com.urbanairship.util.AirshipHandlerThread;
import com.urbanairship.util.Clock;
import com.urbanairship.util.UAStringUtil;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class RemoteData extends AirshipComponent {
    private static final String DATABASE_NAME = "ua_remotedata.db";
    private static final String FOREGROUND_REFRESH_INTERVAL_KEY = "com.urbanairship.remotedata.FOREGROUND_REFRESH_INTERVAL";
    private static final String LAST_MODIFIED_KEY = "com.urbanairship.remotedata.LAST_MODIFIED";
    private static final String LAST_REFRESH_APP_VERSION_KEY = "com.urbanairship.remotedata.LAST_REFRESH_APP_VERSION";
    private static final String LAST_REFRESH_METADATA = "com.urbanairship.remotedata.LAST_REFRESH_METADATA";
    private static final String LAST_REFRESH_TIME_KEY = "com.urbanairship.remotedata.LAST_REFRESH_TIME";
    static final String REMOTE_DATA_UPDATE_KEY = "com.urbanairship.remote-data.update";
    private final ActivityMonitor activityMonitor;
    private final ApplicationListener applicationListener;
    /* access modifiers changed from: private */
    public Handler backgroundHandler;
    final HandlerThread backgroundThread;
    private final Clock clock;
    final RemoteDataStore dataStore;
    private final JobDispatcher jobDispatcher;
    private RemoteDataJobHandler jobHandler;
    private final LocaleChangedListener localeChangedListener;
    private LocaleManager localeManager;
    final Subject<Set<RemoteDataPayload>> payloadUpdates;
    /* access modifiers changed from: private */
    public final PreferenceDataStore preferenceDataStore;
    private final PushListener pushListener;
    private final PushManager pushManager;

    public RemoteData(Context context, PreferenceDataStore preferenceDataStore2, AirshipConfigOptions airshipConfigOptions, ActivityMonitor activityMonitor2, PushManager pushManager2, LocaleManager localeManager2) {
        this(context, preferenceDataStore2, airshipConfigOptions, activityMonitor2, JobDispatcher.shared(context), localeManager2, pushManager2, Clock.DEFAULT_CLOCK);
    }

    RemoteData(Context context, PreferenceDataStore preferenceDataStore2, AirshipConfigOptions airshipConfigOptions, ActivityMonitor activityMonitor2, JobDispatcher jobDispatcher2, LocaleManager localeManager2, PushManager pushManager2, Clock clock2) {
        super(context, preferenceDataStore2);
        this.applicationListener = new SimpleApplicationListener() {
            public void onForeground(long j) {
                if (RemoteData.this.shouldRefresh()) {
                    RemoteData.this.refresh();
                }
            }
        };
        this.localeChangedListener = new LocaleChangedListener() {
            public void onLocaleChanged(Locale locale) {
                if (RemoteData.this.shouldRefresh()) {
                    RemoteData.this.refresh();
                }
            }
        };
        this.pushListener = new PushListener() {
            public void onPushReceived(PushMessage pushMessage, boolean z) {
                if (pushMessage.getPushBundle().containsKey(RemoteData.REMOTE_DATA_UPDATE_KEY)) {
                    RemoteData.this.refresh();
                }
            }
        };
        this.jobDispatcher = jobDispatcher2;
        this.dataStore = new RemoteDataStore(context, airshipConfigOptions.appKey, DATABASE_NAME);
        this.preferenceDataStore = preferenceDataStore2;
        this.backgroundThread = new AirshipHandlerThread("remote data store");
        this.payloadUpdates = Subject.create();
        this.activityMonitor = activityMonitor2;
        this.localeManager = localeManager2;
        this.pushManager = pushManager2;
        this.clock = clock2;
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.backgroundThread.start();
        this.backgroundHandler = new Handler(this.backgroundThread.getLooper());
        this.activityMonitor.addApplicationListener(this.applicationListener);
        this.pushManager.addPushListener(this.pushListener);
        this.localeManager.addListener(this.localeChangedListener);
        if (shouldRefresh()) {
            refresh();
        }
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        this.pushManager.removePushListener(this.pushListener);
        this.activityMonitor.removeApplicationListener(this.applicationListener);
        this.localeManager.removeListener(this.localeChangedListener);
        this.backgroundThread.quit();
    }

    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        if (this.jobHandler == null) {
            this.jobHandler = new RemoteDataJobHandler(getContext(), uAirship);
        }
        return this.jobHandler.performJob(jobInfo);
    }

    public void setForegroundRefreshInterval(long j) {
        this.preferenceDataStore.put(FOREGROUND_REFRESH_INTERVAL_KEY, j);
    }

    public long getForegroundRefreshInterval() {
        return this.preferenceDataStore.getLong(FOREGROUND_REFRESH_INTERVAL_KEY, 0);
    }

    public void refresh() {
        this.jobDispatcher.dispatch(JobInfo.newBuilder().setAction("ACTION_REFRESH").setId(10).setNetworkAccessRequired(true).setAirshipComponent((Class<? extends AirshipComponent>) RemoteData.class).build());
    }

    public Observable<RemoteDataPayload> payloadsForType(String str) {
        return payloadsForTypes((Collection<String>) Collections.singleton(str)).flatMap(new Function<Collection<RemoteDataPayload>, Observable<RemoteDataPayload>>() {
            public Observable<RemoteDataPayload> apply(Collection<RemoteDataPayload> collection) {
                return Observable.from(collection);
            }
        });
    }

    public Observable<Collection<RemoteDataPayload>> payloadsForTypes(String... strArr) {
        return payloadsForTypes((Collection<String>) Arrays.asList(strArr));
    }

    public Observable<Collection<RemoteDataPayload>> payloadsForTypes(final Collection<String> collection) {
        return Observable.concat(cachedPayloads(collection), this.payloadUpdates).map(new Function<Set<RemoteDataPayload>, Map<String, Collection<RemoteDataPayload>>>() {
            public Map<String, Collection<RemoteDataPayload>> apply(Set<RemoteDataPayload> set) {
                HashMap hashMap = new HashMap();
                for (RemoteDataPayload next : set) {
                    Collection collection = (Collection) hashMap.get(next.getType());
                    if (collection == null) {
                        collection = new HashSet();
                        hashMap.put(next.getType(), collection);
                    }
                    collection.add(next);
                }
                return hashMap;
            }
        }).map(new Function<Map<String, Collection<RemoteDataPayload>>, Collection<RemoteDataPayload>>() {
            public Collection<RemoteDataPayload> apply(Map<String, Collection<RemoteDataPayload>> map) {
                HashSet hashSet = new HashSet();
                Iterator it = new HashSet(collection).iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    Collection collection = map.get(str);
                    if (collection != null) {
                        hashSet.addAll(collection);
                    } else {
                        hashSet.add(RemoteDataPayload.emptyPayload(str));
                    }
                }
                return hashSet;
            }
        }).distinctUntilChanged();
    }

    /* access modifiers changed from: package-private */
    public String getLastModified() {
        if (isLastMetadataCurrent()) {
            return this.preferenceDataStore.getString(LAST_MODIFIED_KEY, (String) null);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public boolean shouldRefresh() {
        if (!this.activityMonitor.isAppForegrounded()) {
            return false;
        }
        if (getForegroundRefreshInterval() <= this.clock.currentTimeMillis() - this.preferenceDataStore.getLong(LAST_REFRESH_TIME_KEY, -1)) {
            return true;
        }
        long j = this.preferenceDataStore.getLong(LAST_REFRESH_APP_VERSION_KEY, 0);
        PackageInfo packageInfo = UAirship.getPackageInfo();
        if ((packageInfo == null || PackageInfoCompat.getLongVersionCode(packageInfo) == j) && isLastMetadataCurrent()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onNewData(final Set<RemoteDataPayload> set, final String str, final JsonMap jsonMap) {
        this.backgroundHandler.post(new Runnable() {
            public void run() {
                if (!RemoteData.this.dataStore.deletePayloads()) {
                    Logger.error("Unable to delete existing payload data", new Object[0]);
                    return;
                }
                if (!RemoteData.this.dataStore.savePayloads(set)) {
                    Logger.error("Unable to save remote data payloads", new Object[0]);
                }
                RemoteData.this.preferenceDataStore.put(RemoteData.LAST_REFRESH_METADATA, (JsonSerializable) jsonMap);
                RemoteData.this.preferenceDataStore.put(RemoteData.LAST_MODIFIED_KEY, str);
                RemoteData.this.payloadUpdates.onNext(set);
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void onRefreshFinished() {
        this.preferenceDataStore.put(LAST_REFRESH_TIME_KEY, this.clock.currentTimeMillis());
        PackageInfo packageInfo = UAirship.getPackageInfo();
        if (packageInfo != null) {
            this.preferenceDataStore.put(LAST_REFRESH_APP_VERSION_KEY, PackageInfoCompat.getLongVersionCode(packageInfo));
        }
    }

    private Observable<Set<RemoteDataPayload>> cachedPayloads(final Collection<String> collection) {
        return Observable.defer(new Supplier<Observable<Set<RemoteDataPayload>>>() {
            public Observable<Set<RemoteDataPayload>> apply() {
                return Observable.just(RemoteData.this.dataStore.getPayloads(collection)).subscribeOn(Schedulers.looper(RemoteData.this.backgroundHandler.getLooper()));
            }
        });
    }

    static JsonMap createMetadata(Locale locale) {
        return JsonMap.newBuilder().putOpt(RemoteDataPayload.METADATA_SDK_VERSION, UAirship.getVersion()).putOpt("country", UAStringUtil.nullIfEmpty(locale.getCountry())).putOpt("language", UAStringUtil.nullIfEmpty(locale.getLanguage())).build();
    }

    public boolean isMetadataCurrent(JsonMap jsonMap) {
        return jsonMap.equals(createMetadata(this.localeManager.getLocale()));
    }

    public boolean isLastMetadataCurrent() {
        return isMetadataCurrent(getLastMetadata());
    }

    public JsonMap getLastMetadata() {
        return this.preferenceDataStore.getJsonValue(LAST_REFRESH_METADATA).optMap();
    }
}
