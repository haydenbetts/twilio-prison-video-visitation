package com.urbanairship;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Looper;
import android.os.SystemClock;
import androidx.core.content.pm.PackageInfoCompat;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.actions.ActionRegistry;
import com.urbanairship.actions.DeepLinkListener;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.app.GlobalActivityMonitor;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.channel.NamedUser;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.config.RemoteAirshipUrlConfigProvider;
import com.urbanairship.google.PlayServicesUtils;
import com.urbanairship.images.DefaultImageLoader;
import com.urbanairship.images.ImageLoader;
import com.urbanairship.js.UrlAllowList;
import com.urbanairship.locale.LocaleManager;
import com.urbanairship.modules.Module;
import com.urbanairship.modules.Modules;
import com.urbanairship.modules.accengage.AccengageModule;
import com.urbanairship.modules.accengage.AccengageNotificationHandler;
import com.urbanairship.modules.location.AirshipLocationClient;
import com.urbanairship.modules.location.LocationModule;
import com.urbanairship.push.PushManager;
import com.urbanairship.push.PushProvider;
import com.urbanairship.remoteconfig.RemoteAirshipConfig;
import com.urbanairship.remoteconfig.RemoteAirshipConfigListener;
import com.urbanairship.remoteconfig.RemoteConfigManager;
import com.urbanairship.remotedata.RemoteData;
import com.urbanairship.util.PlatformUtils;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UAirship {
    public static final String ACTION_AIRSHIP_READY = "com.urbanairship.AIRSHIP_READY";
    public static final int AMAZON_PLATFORM = 1;
    public static final int ANDROID_PLATFORM = 2;
    public static final String DATA_COLLECTION_ENABLED_KEY = "com.urbanairship.DATA_COLLECTION_ENABLED";
    public static final String EXTRA_APP_KEY_KEY = "app_key";
    public static final String EXTRA_CHANNEL_ID_KEY = "channel_id";
    public static final String EXTRA_PAYLOAD_VERSION_KEY = "payload_version";
    private static final String LIBRARY_VERSION_KEY = "com.urbanairship.application.device.LIBRARY_VERSION";
    public static boolean LOG_TAKE_OFF_STACKTRACE = false;
    private static final String PLATFORM_KEY = "com.urbanairship.application.device.PLATFORM";
    private static final String PROVIDER_CLASS_KEY = "com.urbanairship.application.device.PUSH_PROVIDER";
    private static final Object airshipLock = new Object();
    static Application application = null;
    static volatile boolean isFlying = false;
    static volatile boolean isMainProcess = false;
    static volatile boolean isTakingOff = false;
    private static final List<CancelableOperation> pendingAirshipRequests = new ArrayList();
    private static boolean queuePendingAirshipRequests = true;
    static UAirship sharedAirship;
    AccengageNotificationHandler accengageNotificationHandler;
    ActionRegistry actionRegistry;
    AirshipConfigOptions airshipConfigOptions;
    Analytics analytics;
    ApplicationMetrics applicationMetrics;
    AirshipChannel channel;
    ChannelCapture channelCapture;
    private final Map<Class, AirshipComponent> componentClassMap = new HashMap();
    private final List<AirshipComponent> components = new ArrayList();
    private DeepLinkListener deepLinkListener;
    ImageLoader imageLoader;
    LocaleManager localeManager;
    AirshipLocationClient locationClient;
    NamedUser namedUser;
    PreferenceDataStore preferenceDataStore;
    PushProviders providers;
    PushManager pushManager;
    PushProvider pushProvider;
    RemoteConfigManager remoteConfigManager;
    RemoteData remoteData;
    AirshipRuntimeConfig runtimeConfig;
    UrlAllowList urlAllowList;

    public interface OnReadyCallback {
        void onAirshipReady(UAirship uAirship);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Platform {
    }

    public static String getVersion() {
        return "14.0.2";
    }

    UAirship(AirshipConfigOptions airshipConfigOptions2) {
        this.airshipConfigOptions = airshipConfigOptions2;
    }

    public static UAirship shared() {
        UAirship waitForTakeOff;
        synchronized (airshipLock) {
            if (!isTakingOff) {
                if (!isFlying) {
                    throw new IllegalStateException("Take off must be called before shared()");
                }
            }
            waitForTakeOff = waitForTakeOff(0);
        }
        return waitForTakeOff;
    }

    public static Cancelable shared(OnReadyCallback onReadyCallback) {
        return shared((Looper) null, onReadyCallback);
    }

    public static UAirship waitForTakeOff(long j) {
        synchronized (airshipLock) {
            if (isFlying) {
                UAirship uAirship = sharedAirship;
                return uAirship;
            }
            if (j > 0) {
                try {
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    long j2 = j;
                    while (!isFlying && j2 > 0) {
                        airshipLock.wait(j2);
                        j2 = j - (SystemClock.elapsedRealtime() - elapsedRealtime);
                    }
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                }
            } else {
                while (!isFlying) {
                    airshipLock.wait();
                }
            }
            if (isFlying) {
                UAirship uAirship2 = sharedAirship;
                return uAirship2;
            }
            return null;
        }
    }

    public static Cancelable shared(Looper looper, final OnReadyCallback onReadyCallback) {
        AnonymousClass1 r0 = new CancelableOperation(looper) {
            public void onRun() {
                OnReadyCallback onReadyCallback = onReadyCallback;
                if (onReadyCallback != null) {
                    onReadyCallback.onAirshipReady(UAirship.shared());
                }
            }
        };
        List<CancelableOperation> list = pendingAirshipRequests;
        synchronized (list) {
            if (queuePendingAirshipRequests) {
                list.add(r0);
            } else {
                r0.run();
            }
        }
        return r0;
    }

    public static void takeOff(Application application2) {
        takeOff(application2, (AirshipConfigOptions) null, (OnReadyCallback) null);
    }

    public static void takeOff(Application application2, OnReadyCallback onReadyCallback) {
        takeOff(application2, (AirshipConfigOptions) null, onReadyCallback);
    }

    public static void takeOff(Application application2, AirshipConfigOptions airshipConfigOptions2) {
        takeOff(application2, airshipConfigOptions2, (OnReadyCallback) null);
    }

    public static void takeOff(final Application application2, final AirshipConfigOptions airshipConfigOptions2, final OnReadyCallback onReadyCallback) {
        if (application2 != null) {
            if (Looper.myLooper() == null || Looper.getMainLooper() != Looper.myLooper()) {
                Logger.error("takeOff() must be called on the main thread!", new Object[0]);
            }
            if (LOG_TAKE_OFF_STACKTRACE) {
                StringBuilder sb = new StringBuilder();
                for (StackTraceElement stackTraceElement : new Exception().getStackTrace()) {
                    sb.append("\n\tat ");
                    sb.append(stackTraceElement.toString());
                }
                Logger.debug("Takeoff stack trace: %s", sb.toString());
            }
            synchronized (airshipLock) {
                if (!isFlying) {
                    if (!isTakingOff) {
                        Logger.info("Airship taking off!", new Object[0]);
                        isTakingOff = true;
                        application = application2;
                        AirshipExecutors.THREAD_POOL_EXECUTOR.execute(new Runnable() {
                            public void run() {
                                UAirship.executeTakeOff(application2, airshipConfigOptions2, onReadyCallback);
                            }
                        });
                        return;
                    }
                }
                Logger.error("You can only call takeOff() once.", new Object[0]);
                return;
            }
        }
        throw new IllegalArgumentException("Application argument must not be null");
    }

    /* access modifiers changed from: private */
    public static void executeTakeOff(Application application2, AirshipConfigOptions airshipConfigOptions2, OnReadyCallback onReadyCallback) {
        if (airshipConfigOptions2 == null) {
            airshipConfigOptions2 = new AirshipConfigOptions.Builder().applyDefaultProperties(application2.getApplicationContext()).build();
        }
        airshipConfigOptions2.validate();
        Logger.setLogLevel(airshipConfigOptions2.logLevel);
        Logger.setTag(getAppName() + " - " + Logger.DEFAULT_TAG);
        Logger.info("Airship taking off!", new Object[0]);
        Logger.info("Airship log level: %s", Integer.valueOf(airshipConfigOptions2.logLevel));
        Logger.info("UA Version: %s / App key = %s Production = %s", getVersion(), airshipConfigOptions2.appKey, Boolean.valueOf(airshipConfigOptions2.inProduction));
        Logger.verbose(BuildConfig.SDK_VERSION, new Object[0]);
        sharedAirship = new UAirship(airshipConfigOptions2);
        synchronized (airshipLock) {
            isFlying = true;
            isTakingOff = false;
            sharedAirship.init();
            Logger.info("Airship ready!", new Object[0]);
            if (onReadyCallback != null) {
                onReadyCallback.onAirshipReady(sharedAirship);
            }
            for (AirshipComponent onAirshipReady : sharedAirship.getComponents()) {
                onAirshipReady.onAirshipReady(sharedAirship);
            }
            List<CancelableOperation> list = pendingAirshipRequests;
            synchronized (list) {
                queuePendingAirshipRequests = false;
                for (CancelableOperation run : list) {
                    run.run();
                }
                pendingAirshipRequests.clear();
            }
            Intent addCategory = new Intent(ACTION_AIRSHIP_READY).setPackage(getPackageName()).addCategory(getPackageName());
            if (sharedAirship.runtimeConfig.getConfigOptions().extendedBroadcastsEnabled) {
                addCategory.putExtra("channel_id", sharedAirship.channel.getId());
                addCategory.putExtra(EXTRA_APP_KEY_KEY, sharedAirship.runtimeConfig.getConfigOptions().appKey);
                addCategory.putExtra(EXTRA_PAYLOAD_VERSION_KEY, 1);
            }
            application2.sendBroadcast(addCategory);
            airshipLock.notifyAll();
        }
    }

    public static void land() {
        synchronized (airshipLock) {
            if (isTakingOff || isFlying) {
                shared().tearDown();
                isFlying = false;
                isTakingOff = false;
                sharedAirship = null;
                application = null;
                queuePendingAirshipRequests = true;
            }
        }
    }

    public void setDeepLinkListener(DeepLinkListener deepLinkListener2) {
        this.deepLinkListener = deepLinkListener2;
    }

    public static String getPackageName() {
        return getApplicationContext().getPackageName();
    }

    public static PackageManager getPackageManager() {
        return getApplicationContext().getPackageManager();
    }

    public DeepLinkListener getDeepLinkListener() {
        return this.deepLinkListener;
    }

    public static PackageInfo getPackageInfo() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Logger.warn(e, "UAirship - Unable to get package info.", new Object[0]);
            return null;
        }
    }

    public static ApplicationInfo getAppInfo() {
        return getApplicationContext().getApplicationInfo();
    }

    public static String getAppName() {
        return getAppInfo() != null ? getPackageManager().getApplicationLabel(getAppInfo()).toString() : "";
    }

    public static int getAppIcon() {
        ApplicationInfo appInfo = getAppInfo();
        if (appInfo != null) {
            return appInfo.icon;
        }
        return -1;
    }

    public static long getAppVersion() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return PackageInfoCompat.getLongVersionCode(packageInfo);
        }
        return -1;
    }

    public static Context getApplicationContext() {
        Application application2 = application;
        if (application2 != null) {
            return application2.getApplicationContext();
        }
        throw new IllegalStateException("TakeOff must be called first.");
    }

    public static boolean isFlying() {
        return isFlying;
    }

    public static boolean isTakingOff() {
        return isTakingOff;
    }

    public static boolean isMainProcess() {
        return isMainProcess;
    }

    public ImageLoader getImageLoader() {
        if (this.imageLoader == null) {
            this.imageLoader = new DefaultImageLoader(getApplicationContext());
        }
        return this.imageLoader;
    }

    public AirshipRuntimeConfig getRuntimeConfig() {
        return this.runtimeConfig;
    }

    public void setImageLoader(ImageLoader imageLoader2) {
        this.imageLoader = imageLoader2;
    }

    private void init() {
        AccengageNotificationHandler accengageNotificationHandler2;
        AirshipLocationClient airshipLocationClient;
        PreferenceDataStore preferenceDataStore2 = new PreferenceDataStore(application);
        this.preferenceDataStore = preferenceDataStore2;
        preferenceDataStore2.init();
        this.localeManager = new LocaleManager(application, this.preferenceDataStore);
        PushProviders load = PushProviders.load(application, this.airshipConfigOptions);
        this.providers = load;
        int determinePlatform = determinePlatform(load);
        PushProvider determinePushProvider = determinePushProvider(determinePlatform, this.providers);
        this.pushProvider = determinePushProvider;
        if (determinePushProvider != null) {
            Logger.info("Using push provider: %s", determinePushProvider);
        }
        RemoteAirshipUrlConfigProvider remoteAirshipUrlConfigProvider = new RemoteAirshipUrlConfigProvider(this.airshipConfigOptions, this.preferenceDataStore);
        AirshipRuntimeConfig airshipRuntimeConfig = new AirshipRuntimeConfig(determinePlatform, this.airshipConfigOptions, remoteAirshipUrlConfigProvider);
        this.runtimeConfig = airshipRuntimeConfig;
        AirshipChannel airshipChannel = new AirshipChannel(application, this.preferenceDataStore, airshipRuntimeConfig, this.localeManager);
        this.channel = airshipChannel;
        if (airshipChannel.getId() == null && "huawei".equalsIgnoreCase(Build.MANUFACTURER)) {
            remoteAirshipUrlConfigProvider.disableFallbackUrls();
        }
        this.components.add(this.channel);
        this.urlAllowList = UrlAllowList.createDefaultUrlAllowList(this.airshipConfigOptions);
        ActionRegistry actionRegistry2 = new ActionRegistry();
        this.actionRegistry = actionRegistry2;
        actionRegistry2.registerDefaultActions(getApplicationContext());
        Analytics analytics2 = new Analytics(application, this.preferenceDataStore, this.runtimeConfig, this.channel, this.localeManager);
        this.analytics = analytics2;
        this.components.add(analytics2);
        Application application2 = application;
        ApplicationMetrics applicationMetrics2 = new ApplicationMetrics(application2, this.preferenceDataStore, GlobalActivityMonitor.shared(application2));
        this.applicationMetrics = applicationMetrics2;
        this.components.add(applicationMetrics2);
        PushManager pushManager2 = new PushManager(application, this.preferenceDataStore, this.airshipConfigOptions, this.pushProvider, this.channel, this.analytics);
        this.pushManager = pushManager2;
        this.components.add(pushManager2);
        NamedUser namedUser2 = new NamedUser(application, this.preferenceDataStore, this.runtimeConfig, this.channel);
        this.namedUser = namedUser2;
        this.components.add(namedUser2);
        Application application3 = application;
        ChannelCapture channelCapture2 = new ChannelCapture(application3, this.airshipConfigOptions, this.channel, this.preferenceDataStore, GlobalActivityMonitor.shared(application3));
        this.channelCapture = channelCapture2;
        this.components.add(channelCapture2);
        Application application4 = application;
        RemoteData remoteData2 = new RemoteData(application4, this.preferenceDataStore, this.airshipConfigOptions, GlobalActivityMonitor.shared(application4), this.pushManager, this.localeManager);
        this.remoteData = remoteData2;
        this.components.add(remoteData2);
        RemoteConfigManager remoteConfigManager2 = new RemoteConfigManager(application, this.preferenceDataStore, this.remoteData);
        this.remoteConfigManager = remoteConfigManager2;
        remoteConfigManager2.addRemoteAirshipConfigListener(remoteAirshipUrlConfigProvider);
        this.remoteConfigManager.addRemoteAirshipConfigListener(new RemoteAirshipConfigListener() {
            public void onRemoteConfigUpdated(RemoteAirshipConfig remoteAirshipConfig) {
                if (UAirship.this.channel.getId() == null) {
                    UAirship.this.channel.updateRegistration();
                }
            }
        });
        this.components.add(this.remoteConfigManager);
        AccengageModule accengage = Modules.accengage(application, this.preferenceDataStore, this.channel, this.pushManager, this.analytics);
        processModule(accengage);
        if (accengage == null) {
            accengageNotificationHandler2 = null;
        } else {
            accengageNotificationHandler2 = accengage.getAccengageNotificationHandler();
        }
        this.accengageNotificationHandler = accengageNotificationHandler2;
        processModule(Modules.messageCenter(application, this.preferenceDataStore, this.channel, this.pushManager));
        LocationModule location = Modules.location(application, this.preferenceDataStore, this.channel, this.analytics);
        processModule(location);
        if (location == null) {
            airshipLocationClient = null;
        } else {
            airshipLocationClient = location.getLocationClient();
        }
        this.locationClient = airshipLocationClient;
        processModule(Modules.automation(application, this.preferenceDataStore, this.runtimeConfig, this.channel, this.pushManager, this.analytics, this.remoteData, this.namedUser));
        processModule(Modules.debug(application, this.preferenceDataStore));
        processModule(Modules.adId(application, this.preferenceDataStore));
        for (AirshipComponent init : this.components) {
            init.init();
        }
        String version = getVersion();
        String string = this.preferenceDataStore.getString(LIBRARY_VERSION_KEY, (String) null);
        if (string != null && !string.equals(version)) {
            Logger.info("Airship library changed from %s to %s.", string, version);
        }
        this.preferenceDataStore.put(LIBRARY_VERSION_KEY, getVersion());
        if (!this.preferenceDataStore.isSet(DATA_COLLECTION_ENABLED_KEY)) {
            boolean z = !this.airshipConfigOptions.dataCollectionOptInEnabled;
            Logger.debug("Airship - Setting data collection enabled to %s", Boolean.valueOf(z));
            setDataCollectionEnabled(z);
        }
    }

    private void processModule(Module module) {
        if (module != null) {
            this.components.addAll(module.getComponents());
            module.registerActions(application, getActionRegistry());
        }
    }

    private void tearDown() {
        for (AirshipComponent tearDown : getComponents()) {
            tearDown.tearDown();
        }
        this.preferenceDataStore.tearDown();
    }

    public AirshipConfigOptions getAirshipConfigOptions() {
        return this.airshipConfigOptions;
    }

    public NamedUser getNamedUser() {
        return this.namedUser;
    }

    public PushManager getPushManager() {
        return this.pushManager;
    }

    public AirshipChannel getChannel() {
        return this.channel;
    }

    public AirshipLocationClient getLocationClient() {
        return this.locationClient;
    }

    public RemoteData getRemoteData() {
        return this.remoteData;
    }

    public Analytics getAnalytics() {
        return this.analytics;
    }

    public ApplicationMetrics getApplicationMetrics() {
        return this.applicationMetrics;
    }

    public UrlAllowList getUrlAllowList() {
        return this.urlAllowList;
    }

    public ActionRegistry getActionRegistry() {
        return this.actionRegistry;
    }

    public ChannelCapture getChannelCapture() {
        return this.channelCapture;
    }

    public AccengageNotificationHandler getAccengageNotificationHandler() {
        return this.accengageNotificationHandler;
    }

    public int getPlatformType() {
        return this.runtimeConfig.getPlatform();
    }

    public List<AirshipComponent> getComponents() {
        return this.components;
    }

    public <T extends AirshipComponent> T getComponent(Class<T> cls) {
        T t = (AirshipComponent) this.componentClassMap.get(cls);
        if (t == null) {
            Iterator it = this.components.iterator();
            while (true) {
                if (!it.hasNext()) {
                    t = null;
                    break;
                }
                T t2 = (AirshipComponent) it.next();
                if (t2.getClass().equals(cls)) {
                    this.componentClassMap.put(cls, t2);
                    t = t2;
                    break;
                }
            }
        }
        if (t != null) {
            return t;
        }
        return null;
    }

    public <T extends AirshipComponent> T requireComponent(Class<T> cls) {
        T component = getComponent(cls);
        if (component != null) {
            return component;
        }
        throw new IllegalArgumentException("Unable to find component " + cls);
    }

    public void setDataCollectionEnabled(boolean z) {
        this.preferenceDataStore.put(DATA_COLLECTION_ENABLED_KEY, z);
    }

    public boolean isDataCollectionEnabled() {
        return this.preferenceDataStore.getBoolean(DATA_COLLECTION_ENABLED_KEY, true);
    }

    public void setLocaleOverride(Locale locale) {
        this.localeManager.setLocaleOverride(locale);
    }

    public Locale getLocale() {
        return this.localeManager.getLocale();
    }

    public LocaleManager getLocaleManager() {
        return this.localeManager;
    }

    public PushProviders getPushProviders() {
        return this.providers;
    }

    private PushProvider determinePushProvider(int i, PushProviders pushProviders) {
        PushProvider provider;
        String string = this.preferenceDataStore.getString(PROVIDER_CLASS_KEY, (String) null);
        if (!UAStringUtil.isEmpty(string) && (provider = pushProviders.getProvider(i, string)) != null) {
            return provider;
        }
        PushProvider bestProvider = pushProviders.getBestProvider(i);
        if (bestProvider != null) {
            this.preferenceDataStore.put(PROVIDER_CLASS_KEY, bestProvider.getClass().toString());
        }
        return bestProvider;
    }

    private int determinePlatform(PushProviders pushProviders) {
        int i = this.preferenceDataStore.getInt(PLATFORM_KEY, -1);
        if (PlatformUtils.isPlatformValid(i)) {
            return PlatformUtils.parsePlatform(i);
        }
        PushProvider bestProvider = pushProviders.getBestProvider();
        int i2 = 1;
        if (bestProvider != null) {
            int parsePlatform = PlatformUtils.parsePlatform(bestProvider.getPlatform());
            Logger.info("Setting platform to %s for push provider: %s", PlatformUtils.asString(parsePlatform), bestProvider);
            i2 = parsePlatform;
        } else {
            if (PlayServicesUtils.isGooglePlayStoreAvailable(getApplicationContext())) {
                Logger.info("Google Play Store available. Setting platform to Android.", new Object[0]);
            } else if (ChannelRegistrationPayload.AMAZON_DEVICE_TYPE.equalsIgnoreCase(Build.MANUFACTURER)) {
                Logger.info("Build.MANUFACTURER is AMAZON. Setting platform to Amazon.", new Object[0]);
            } else {
                Logger.info("Defaulting platform to Android.", new Object[0]);
            }
            i2 = 2;
        }
        this.preferenceDataStore.put(PLATFORM_KEY, i2);
        return PlatformUtils.parsePlatform(i2);
    }
}
