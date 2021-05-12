package com.urbanairship.analytics;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import com.microsoft.appcenter.Constants;
import com.urbanairship.AirshipComponent;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.AssociatedIdentifiers;
import com.urbanairship.analytics.data.EventManager;
import com.urbanairship.analytics.location.RegionEvent;
import com.urbanairship.app.ActivityMonitor;
import com.urbanairship.app.ApplicationListener;
import com.urbanairship.app.GlobalActivityMonitor;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.AirshipChannelListener;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.locale.LocaleManager;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class Analytics extends AirshipComponent {
    private static final String ANALYTICS_ENABLED_KEY = "com.urbanairship.analytics.ANALYTICS_ENABLED";
    private static final String ASSOCIATED_IDENTIFIERS_KEY = "com.urbanairship.analytics.ASSOCIATED_IDENTIFIERS";
    public static final String EXTENSION_CORDOVA = "cordova";
    public static final String EXTENSION_FLUTTER = "flutter";
    public static final String EXTENSION_REACT_NATIVE = "react-native";
    public static final String EXTENSION_TITANIUM = "titanum";
    public static final String EXTENSION_UNITY = "unity";
    public static final String EXTENSION_XAMARIN = "xamarin";
    private static final String KEY_PREFIX = "com.urbanairship.analytics";
    private static final long SCHEDULE_SEND_DELAY_SECONDS = 10;
    private final ActivityMonitor activityMonitor;
    private final AirshipChannel airshipChannel;
    private final List<AnalyticsListener> analyticsListeners;
    /* access modifiers changed from: private */
    public final Object associatedIdentifiersLock;
    private String conversionMetadata;
    private String conversionSendId;
    private String currentScreen;
    private final List<EventListener> eventListeners;
    /* access modifiers changed from: private */
    public final EventManager eventManager;
    private final Executor executor;
    private final List<AnalyticsHeaderDelegate> headerDelegates;
    private final ApplicationListener listener;
    private final LocaleManager localeManager;
    private String previousScreen;
    private final AirshipRuntimeConfig runtimeConfig;
    private long screenStartTime;
    private final List<String> sdkExtensions;
    /* access modifiers changed from: private */
    public String sessionId;

    public interface AnalyticsHeaderDelegate {
        Map<String, String> onCreateAnalyticsHeaders();
    }

    public interface EventListener {
        void onEventAdded(Event event, String str);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionName {
    }

    public int getComponentGroup() {
        return 1;
    }

    public Analytics(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel2, LocaleManager localeManager2) {
        this(context, preferenceDataStore, airshipRuntimeConfig, airshipChannel2, GlobalActivityMonitor.shared(context), localeManager2, AirshipExecutors.newSerialExecutor(), new EventManager(context, preferenceDataStore, airshipRuntimeConfig));
    }

    Analytics(Context context, PreferenceDataStore preferenceDataStore, AirshipRuntimeConfig airshipRuntimeConfig, AirshipChannel airshipChannel2, ActivityMonitor activityMonitor2, LocaleManager localeManager2, Executor executor2, EventManager eventManager2) {
        super(context, preferenceDataStore);
        this.analyticsListeners = new CopyOnWriteArrayList();
        this.eventListeners = new CopyOnWriteArrayList();
        this.headerDelegates = new CopyOnWriteArrayList();
        this.associatedIdentifiersLock = new Object();
        this.sdkExtensions = new ArrayList();
        this.runtimeConfig = airshipRuntimeConfig;
        this.airshipChannel = airshipChannel2;
        this.activityMonitor = activityMonitor2;
        this.localeManager = localeManager2;
        this.executor = executor2;
        this.eventManager = eventManager2;
        this.sessionId = UUID.randomUUID().toString();
        this.listener = new ApplicationListener() {
            public void onForeground(long j) {
                Analytics.this.onForeground(j);
            }

            public void onBackground(long j) {
                Analytics.this.onBackground(j);
            }
        };
    }

    public void addHeaderDelegate(AnalyticsHeaderDelegate analyticsHeaderDelegate) {
        this.headerDelegates.add(analyticsHeaderDelegate);
    }

    public void addEventListener(EventListener eventListener) {
        this.eventListeners.add(eventListener);
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.activityMonitor.addApplicationListener(this.listener);
        if (this.activityMonitor.isAppForegrounded()) {
            onForeground(System.currentTimeMillis());
        }
        this.airshipChannel.addChannelListener(new AirshipChannelListener() {
            public void onChannelUpdated(String str) {
            }

            public void onChannelCreated(String str) {
                Analytics.this.uploadEvents();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void tearDown() {
        this.activityMonitor.removeApplicationListener(this.listener);
    }

    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        if (!EventManager.ACTION_SEND.equals(jobInfo.getAction()) || !isEnabled()) {
            return 0;
        }
        if (this.airshipChannel.getId() == null) {
            Logger.debug("AnalyticsJobHandler - No channel ID, skipping analytics send.", new Object[0]);
            return 0;
        } else if (!this.eventManager.uploadEvents(getAnalyticHeaders())) {
            return 1;
        } else {
            return 0;
        }
    }

    public boolean isAppInForeground() {
        return this.activityMonitor.isAppForegrounded();
    }

    public void addEvent(final Event event) {
        if (event == null || !event.isValid()) {
            Logger.error("Analytics - Invalid event: %s", event);
        } else if (!isEnabled() || !isDataCollectionEnabled()) {
            Logger.debug("Analytics - Disabled ignoring event: %s", event.getType());
        } else {
            Logger.verbose("Analytics - Adding event: %s", event.getType());
            this.executor.execute(new Runnable() {
                public void run() {
                    Analytics.this.eventManager.addEvent(event, Analytics.this.sessionId);
                }
            });
            applyListeners(event);
        }
    }

    public String getConversionSendId() {
        return this.conversionSendId;
    }

    public void setConversionSendId(String str) {
        Logger.debug("Analytics - Setting conversion send ID: %s", str);
        this.conversionSendId = str;
    }

    public String getConversionMetadata() {
        return this.conversionMetadata;
    }

    public void setConversionMetadata(String str) {
        Logger.debug("Analytics - Setting conversion metadata: %s", str);
        this.conversionMetadata = str;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    /* access modifiers changed from: package-private */
    public void onForeground(long j) {
        String uuid = UUID.randomUUID().toString();
        this.sessionId = uuid;
        Logger.debug("Analytics - New session: %s", uuid);
        if (this.currentScreen == null) {
            trackScreen(this.previousScreen);
        }
        addEvent(new AppForegroundEvent(j));
    }

    /* access modifiers changed from: package-private */
    public void onBackground(long j) {
        trackScreen((String) null);
        addEvent(new AppBackgroundEvent(j));
        setConversionSendId((String) null);
        setConversionMetadata((String) null);
    }

    public void setEnabled(boolean z) {
        if (getDataStore().getBoolean(ANALYTICS_ENABLED_KEY, true) && !z) {
            clearPendingEvents();
        }
        if (z && !isDataCollectionEnabled()) {
            Logger.warn("Analytics - Analytics is disabled until data collection is opted in.", new Object[0]);
        }
        getDataStore().put(ANALYTICS_ENABLED_KEY, z);
    }

    private void clearPendingEvents() {
        this.executor.execute(new Runnable() {
            public void run() {
                Logger.info("Deleting all analytic events.", new Object[0]);
                Analytics.this.eventManager.deleteEvents();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDataCollectionEnabledChanged(boolean z) {
        if (!z) {
            clearPendingEvents();
            synchronized (this.associatedIdentifiersLock) {
                getDataStore().remove(ASSOCIATED_IDENTIFIERS_KEY);
            }
        }
    }

    public boolean isEnabled() {
        return this.runtimeConfig.getConfigOptions().analyticsEnabled && getDataStore().getBoolean(ANALYTICS_ENABLED_KEY, true) && isDataCollectionEnabled();
    }

    public AssociatedIdentifiers.Editor editAssociatedIdentifiers() {
        return new AssociatedIdentifiers.Editor() {
            /* access modifiers changed from: package-private */
            public void onApply(boolean z, Map<String, String> map, List<String> list) {
                synchronized (Analytics.this.associatedIdentifiersLock) {
                    if (!Analytics.this.isDataCollectionEnabled()) {
                        Logger.warn("Analytics - Unable to track associated identifiers when data collection is disabled.", new Object[0]);
                        return;
                    }
                    HashMap hashMap = new HashMap();
                    AssociatedIdentifiers associatedIdentifiers = Analytics.this.getAssociatedIdentifiers();
                    if (!z) {
                        hashMap.putAll(associatedIdentifiers.getIds());
                    }
                    hashMap.putAll(map);
                    for (String remove : list) {
                        hashMap.remove(remove);
                    }
                    AssociatedIdentifiers associatedIdentifiers2 = new AssociatedIdentifiers(hashMap);
                    if (associatedIdentifiers.getIds().equals(associatedIdentifiers2.getIds())) {
                        Logger.info("Skipping analytics event addition for duplicate associated identifiers.", new Object[0]);
                        return;
                    }
                    Analytics.this.getDataStore().put(Analytics.ASSOCIATED_IDENTIFIERS_KEY, (JsonSerializable) associatedIdentifiers2);
                    Analytics.this.addEvent(new AssociateIdentifiersEvent(associatedIdentifiers2));
                }
            }
        };
    }

    public AssociatedIdentifiers getAssociatedIdentifiers() {
        synchronized (this.associatedIdentifiersLock) {
            try {
                JsonValue jsonValue = getDataStore().getJsonValue(ASSOCIATED_IDENTIFIERS_KEY);
                if (!jsonValue.isNull()) {
                    AssociatedIdentifiers fromJson = AssociatedIdentifiers.fromJson(jsonValue);
                    return fromJson;
                }
            } catch (JsonException e) {
                Logger.error(e, "Unable to parse associated identifiers.", new Object[0]);
                getDataStore().remove(ASSOCIATED_IDENTIFIERS_KEY);
            } catch (Throwable th) {
                throw th;
            }
            AssociatedIdentifiers associatedIdentifiers = new AssociatedIdentifiers();
            return associatedIdentifiers;
        }
    }

    public void trackScreen(String str) {
        String str2 = this.currentScreen;
        if (str2 == null || !str2.equals(str)) {
            String str3 = this.currentScreen;
            if (str3 != null) {
                ScreenTrackingEvent screenTrackingEvent = new ScreenTrackingEvent(str3, this.previousScreen, this.screenStartTime, System.currentTimeMillis());
                this.previousScreen = this.currentScreen;
                addEvent(screenTrackingEvent);
            }
            this.currentScreen = str;
            if (str != null) {
                for (AnalyticsListener onScreenTracked : this.analyticsListeners) {
                    onScreenTracked.onScreenTracked(str);
                }
            }
            this.screenStartTime = System.currentTimeMillis();
        }
    }

    public void uploadEvents() {
        this.eventManager.scheduleEventUpload(10, TimeUnit.SECONDS);
    }

    public void addAnalyticsListener(AnalyticsListener analyticsListener) {
        this.analyticsListeners.add(analyticsListener);
    }

    public void removeAnalyticsListener(AnalyticsListener analyticsListener) {
        this.analyticsListeners.remove(analyticsListener);
    }

    private void applyListeners(Event event) {
        for (EventListener onEventAdded : this.eventListeners) {
            onEventAdded.onEventAdded(event, getSessionId());
        }
        for (AnalyticsListener next : this.analyticsListeners) {
            String type = event.getType();
            type.hashCode();
            if (!type.equals(RegionEvent.TYPE)) {
                if (type.equals("enhanced_custom_event") && (event instanceof CustomEvent)) {
                    next.onCustomEventAdded((CustomEvent) event);
                }
            } else if (event instanceof RegionEvent) {
                next.onRegionEventAdded((RegionEvent) event);
            }
        }
    }

    private Map<String, String> getAnalyticHeaders() {
        HashMap hashMap = new HashMap();
        for (AnalyticsHeaderDelegate onCreateAnalyticsHeaders : this.headerDelegates) {
            hashMap.putAll(onCreateAnalyticsHeaders.onCreateAnalyticsHeaders());
        }
        hashMap.put("X-UA-Package-Name", getPackageName());
        hashMap.put("X-UA-Package-Version", getPackageVersion());
        hashMap.put("X-UA-Android-Version-Code", String.valueOf(Build.VERSION.SDK_INT));
        hashMap.put("X-UA-Device-Family", this.runtimeConfig.getPlatform() == 1 ? ChannelRegistrationPayload.AMAZON_DEVICE_TYPE : ChannelRegistrationPayload.ANDROID_DEVICE_TYPE);
        hashMap.put("X-UA-Lib-Version", UAirship.getVersion());
        hashMap.put("X-UA-App-Key", this.runtimeConfig.getConfigOptions().appKey);
        hashMap.put("X-UA-In-Production", Boolean.toString(this.runtimeConfig.getConfigOptions().inProduction));
        hashMap.put("X-UA-Channel-ID", this.airshipChannel.getId());
        hashMap.put("X-UA-Push-Address", this.airshipChannel.getId());
        if (!this.sdkExtensions.isEmpty()) {
            hashMap.put("X-UA-Frameworks", UAStringUtil.join(this.sdkExtensions, ","));
        }
        hashMap.put("X-UA-Device-Model", Build.MODEL);
        hashMap.put("X-UA-Timezone", TimeZone.getDefault().getID());
        Locale locale = this.localeManager.getLocale();
        if (!UAStringUtil.isEmpty(locale.getLanguage())) {
            hashMap.put("X-UA-Locale-Language", locale.getLanguage());
            if (!UAStringUtil.isEmpty(locale.getCountry())) {
                hashMap.put("X-UA-Locale-Country", locale.getCountry());
            }
            if (!UAStringUtil.isEmpty(locale.getVariant())) {
                hashMap.put("X-UA-Locale-Variant", locale.getVariant());
            }
        }
        return hashMap;
    }

    private String getPackageName() {
        try {
            return getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    private String getPackageVersion() {
        try {
            return getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public void registerSDKExtension(String str, String str2) {
        String lowerCase = str.toLowerCase();
        String replace = str2.replace(",", "");
        List<String> list = this.sdkExtensions;
        list.add(lowerCase + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + replace);
    }
}
