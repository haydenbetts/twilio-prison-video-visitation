package com.urbanairship.push;

import android.content.Context;
import android.os.Build;
import androidx.core.app.NotificationManagerCompat;
import com.urbanairship.AirshipComponent;
import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.R;
import com.urbanairship.UAirship;
import com.urbanairship.analytics.Analytics;
import com.urbanairship.channel.AirshipChannel;
import com.urbanairship.channel.ChannelRegistrationPayload;
import com.urbanairship.job.JobDispatcher;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonValue;
import com.urbanairship.push.IncomingPushRunnable;
import com.urbanairship.push.notifications.AirshipNotificationProvider;
import com.urbanairship.push.notifications.NotificationActionButtonGroup;
import com.urbanairship.push.notifications.NotificationChannelRegistry;
import com.urbanairship.push.notifications.NotificationProvider;
import com.urbanairship.util.UAStringUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;

public class PushManager extends AirshipComponent {
    static final String ACTION_DISPLAY_NOTIFICATION = "ACTION_DISPLAY_NOTIFICATION";
    public static final String ACTION_NOTIFICATION_DISMISSED = "com.urbanairship.push.ACTION_NOTIFICATION_DISMISSED";
    public static final String ACTION_NOTIFICATION_RESPONSE = "com.urbanairship.push.ACTION_NOTIFICATION_RESPONSE";
    static final String ACTION_UPDATE_PUSH_REGISTRATION = "ACTION_UPDATE_PUSH_REGISTRATION";
    public static final String EXTRA_NOTIFICATION_ACTION_BUTTON_DESCRIPTION = "com.urbanairship.push.EXTRA_NOTIFICATION_ACTION_BUTTON_DESCRIPTION";
    public static final String EXTRA_NOTIFICATION_BUTTON_ACTIONS_PAYLOAD = "com.urbanairship.push.EXTRA_NOTIFICATION_BUTTON_ACTIONS_PAYLOAD";
    public static final String EXTRA_NOTIFICATION_BUTTON_FOREGROUND = "com.urbanairship.push.EXTRA_NOTIFICATION_BUTTON_FOREGROUND";
    public static final String EXTRA_NOTIFICATION_BUTTON_ID = "com.urbanairship.push.EXTRA_NOTIFICATION_BUTTON_ID";
    public static final String EXTRA_NOTIFICATION_CONTENT_INTENT = "com.urbanairship.push.EXTRA_NOTIFICATION_CONTENT_INTENT";
    public static final String EXTRA_NOTIFICATION_DELETE_INTENT = "com.urbanairship.push.EXTRA_NOTIFICATION_DELETE_INTENT";
    public static final String EXTRA_NOTIFICATION_ID = "com.urbanairship.push.NOTIFICATION_ID";
    public static final String EXTRA_NOTIFICATION_TAG = "com.urbanairship.push.NOTIFICATION_TAG";
    public static final String EXTRA_PUSH_MESSAGE_BUNDLE = "com.urbanairship.push.EXTRA_PUSH_MESSAGE_BUNDLE";
    static final String KEY_PREFIX = "com.urbanairship.push";
    private static final String LAST_CANONICAL_IDS_KEY = "com.urbanairship.push.LAST_CANONICAL_IDS";
    static final String LAST_RECEIVED_METADATA = "com.urbanairship.push.LAST_RECEIVED_METADATA";
    private static final int MAX_CANONICAL_IDS = 10;
    static final String PUSH_DELIVERY_TYPE = "com.urbanairship.push.PUSH_DELIVERY_TYPE";
    static final String PUSH_ENABLED_KEY = "com.urbanairship.push.PUSH_ENABLED";
    static final String PUSH_ENABLED_SETTINGS_MIGRATED_KEY = "com.urbanairship.push.PUSH_ENABLED_SETTINGS_MIGRATED";
    static final ExecutorService PUSH_EXECUTOR = AirshipExecutors.THREAD_POOL_EXECUTOR;
    static final String PUSH_TOKEN_KEY = "com.urbanairship.push.REGISTRATION_TOKEN_KEY";
    static final String PUSH_TOKEN_REGISTRATION_ENABLED_KEY = "com.urbanairship.push.PUSH_TOKEN_REGISTRATION_ENABLED";
    static final String QUIET_TIME_ENABLED = "com.urbanairship.push.QUIET_TIME_ENABLED";
    static final String QUIET_TIME_INTERVAL = "com.urbanairship.push.QUIET_TIME_INTERVAL";
    static final String SOUND_ENABLED_KEY = "com.urbanairship.push.SOUND_ENABLED";
    static final String USER_NOTIFICATIONS_ENABLED_KEY = "com.urbanairship.push.USER_NOTIFICATIONS_ENABLED";
    static final String VIBRATE_ENABLED_KEY = "com.urbanairship.push.VIBRATE_ENABLED";
    private final String UA_NOTIFICATION_BUTTON_GROUP_PREFIX;
    private final Map<String, NotificationActionButtonGroup> actionGroupMap;
    private final AirshipChannel airshipChannel;
    private final Analytics analytics;
    private final Context context;
    private List<InternalNotificationListener> internalNotificationListeners;
    private final JobDispatcher jobDispatcher;
    private NotificationChannelRegistry notificationChannelRegistry;
    private NotificationListener notificationListener;
    private final NotificationManagerCompat notificationManagerCompat;
    private NotificationProvider notificationProvider;
    private final PreferenceDataStore preferenceDataStore;
    private List<PushListener> pushListeners;
    private final PushProvider pushProvider;
    private List<PushTokenListener> pushTokenListeners;
    private final Object uniqueIdLock;

    public int getComponentGroup() {
        return 0;
    }

    public PushManager(Context context2, PreferenceDataStore preferenceDataStore2, AirshipConfigOptions airshipConfigOptions, PushProvider pushProvider2, AirshipChannel airshipChannel2, Analytics analytics2) {
        this(context2, preferenceDataStore2, airshipConfigOptions, pushProvider2, airshipChannel2, analytics2, JobDispatcher.shared(context2));
    }

    PushManager(Context context2, PreferenceDataStore preferenceDataStore2, AirshipConfigOptions airshipConfigOptions, PushProvider pushProvider2, AirshipChannel airshipChannel2, Analytics analytics2, JobDispatcher jobDispatcher2) {
        super(context2, preferenceDataStore2);
        this.UA_NOTIFICATION_BUTTON_GROUP_PREFIX = "ua_";
        HashMap hashMap = new HashMap();
        this.actionGroupMap = hashMap;
        this.pushTokenListeners = new CopyOnWriteArrayList();
        this.pushListeners = new CopyOnWriteArrayList();
        this.internalNotificationListeners = new CopyOnWriteArrayList();
        this.uniqueIdLock = new Object();
        this.context = context2;
        this.preferenceDataStore = preferenceDataStore2;
        this.pushProvider = pushProvider2;
        this.airshipChannel = airshipChannel2;
        this.analytics = analytics2;
        this.jobDispatcher = jobDispatcher2;
        this.notificationProvider = new AirshipNotificationProvider(context2, airshipConfigOptions);
        this.notificationManagerCompat = NotificationManagerCompat.from(context2);
        this.notificationChannelRegistry = new NotificationChannelRegistry(context2, airshipConfigOptions);
        hashMap.putAll(ActionButtonGroupsParser.fromXml(context2, R.xml.ua_notification_buttons));
        if (Build.VERSION.SDK_INT >= 23) {
            hashMap.putAll(ActionButtonGroupsParser.fromXml(context2, R.xml.ua_notification_button_overrides));
        }
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        migratePushEnabledSettings();
        this.airshipChannel.addChannelRegistrationPayloadExtender(new AirshipChannel.ChannelRegistrationPayloadExtender() {
            public ChannelRegistrationPayload.Builder extend(ChannelRegistrationPayload.Builder builder) {
                return PushManager.this.extendChannelRegistrationPayload(builder);
            }
        });
        this.analytics.addHeaderDelegate(new Analytics.AnalyticsHeaderDelegate() {
            public Map<String, String> onCreateAnalyticsHeaders() {
                return PushManager.this.createAnalyticsHeaders();
            }
        });
        this.notificationChannelRegistry.createDeferredNotificationChannels(R.xml.ua_default_channels);
        String string = this.preferenceDataStore.getString(PUSH_DELIVERY_TYPE, (String) null);
        PushProvider pushProvider2 = this.pushProvider;
        if (pushProvider2 == null) {
            this.preferenceDataStore.remove(PUSH_DELIVERY_TYPE);
            this.preferenceDataStore.remove(PUSH_TOKEN_KEY);
        } else if (!pushProvider2.getDeliveryType().equals(string)) {
            this.preferenceDataStore.remove(PUSH_TOKEN_KEY);
            this.preferenceDataStore.put(PUSH_DELIVERY_TYPE, this.pushProvider.getDeliveryType());
        }
        dispatchUpdatePushTokenJob();
    }

    /* access modifiers changed from: private */
    public ChannelRegistrationPayload.Builder extendChannelRegistrationPayload(ChannelRegistrationPayload.Builder builder) {
        String str;
        boolean z = false;
        if (isPushTokenRegistrationEnabled()) {
            if (getPushToken() == null) {
                performPushRegistration(false);
            }
            str = getPushToken();
        } else {
            str = null;
        }
        builder.setPushAddress(str);
        PushProvider pushProvider2 = getPushProvider();
        if (!(str == null || pushProvider2 == null || pushProvider2.getPlatform() != 2)) {
            builder.setDeliveryType(pushProvider2.getDeliveryType());
        }
        ChannelRegistrationPayload.Builder optIn = builder.setOptIn(isOptIn());
        if (isPushEnabled() && isPushAvailable()) {
            z = true;
        }
        return optIn.setBackgroundEnabled(z);
    }

    public void onComponentEnableChange(boolean z) {
        if (z) {
            dispatchUpdatePushTokenJob();
        }
    }

    public int onPerformJob(UAirship uAirship, JobInfo jobInfo) {
        String action = jobInfo.getAction();
        action.hashCode();
        if (action.equals(ACTION_UPDATE_PUSH_REGISTRATION)) {
            return performPushRegistration(true);
        }
        if (!action.equals(ACTION_DISPLAY_NOTIFICATION)) {
            return 0;
        }
        PushMessage fromJsonValue = PushMessage.fromJsonValue(jobInfo.getExtras().opt("EXTRA_PUSH"));
        String string = jobInfo.getExtras().opt("EXTRA_PROVIDER_CLASS").getString();
        if (string == null) {
            return 0;
        }
        new IncomingPushRunnable.Builder(getContext()).setLongRunning(true).setProcessed(true).setMessage(fromJsonValue).setProviderClass(string).build().run();
        return 0;
    }

    public void setPushEnabled(boolean z) {
        this.preferenceDataStore.put(PUSH_ENABLED_KEY, z);
        this.airshipChannel.updateRegistration();
    }

    public boolean isPushEnabled() {
        return this.preferenceDataStore.getBoolean(PUSH_ENABLED_KEY, true);
    }

    public void setUserNotificationsEnabled(boolean z) {
        this.preferenceDataStore.put(USER_NOTIFICATIONS_ENABLED_KEY, z);
        this.airshipChannel.updateRegistration();
    }

    public boolean getUserNotificationsEnabled() {
        return this.preferenceDataStore.getBoolean(USER_NOTIFICATIONS_ENABLED_KEY, false);
    }

    public void setNotificationProvider(NotificationProvider notificationProvider2) {
        this.notificationProvider = notificationProvider2;
    }

    public NotificationProvider getNotificationProvider() {
        return this.notificationProvider;
    }

    public NotificationChannelRegistry getNotificationChannelRegistry() {
        return this.notificationChannelRegistry;
    }

    @Deprecated
    public boolean isSoundEnabled() {
        return this.preferenceDataStore.getBoolean(SOUND_ENABLED_KEY, true);
    }

    @Deprecated
    public void setSoundEnabled(boolean z) {
        this.preferenceDataStore.put(SOUND_ENABLED_KEY, z);
    }

    @Deprecated
    public boolean isVibrateEnabled() {
        return this.preferenceDataStore.getBoolean(VIBRATE_ENABLED_KEY, true);
    }

    @Deprecated
    public void setVibrateEnabled(boolean z) {
        this.preferenceDataStore.put(VIBRATE_ENABLED_KEY, z);
    }

    @Deprecated
    public boolean isQuietTimeEnabled() {
        return this.preferenceDataStore.getBoolean(QUIET_TIME_ENABLED, false);
    }

    @Deprecated
    public void setQuietTimeEnabled(boolean z) {
        this.preferenceDataStore.put(QUIET_TIME_ENABLED, z);
    }

    @Deprecated
    public boolean isInQuietTime() {
        if (!isQuietTimeEnabled()) {
            return false;
        }
        try {
            return QuietTimeInterval.fromJson(this.preferenceDataStore.getJsonValue(QUIET_TIME_INTERVAL)).isInQuietTime(Calendar.getInstance());
        } catch (JsonException unused) {
            Logger.error("Failed to parse quiet time interval", new Object[0]);
            return false;
        }
    }

    @Deprecated
    public Date[] getQuietTimeInterval() {
        try {
            return QuietTimeInterval.fromJson(this.preferenceDataStore.getJsonValue(QUIET_TIME_INTERVAL)).getQuietTimeIntervalDateArray();
        } catch (JsonException unused) {
            Logger.error("Failed to parse quiet time interval", new Object[0]);
            return null;
        }
    }

    @Deprecated
    public void setQuietTimeInterval(Date date, Date date2) {
        this.preferenceDataStore.put(QUIET_TIME_INTERVAL, QuietTimeInterval.newBuilder().setQuietTimeInterval(date, date2).build().toJsonValue());
    }

    public boolean isPushAvailable() {
        return isPushTokenRegistrationEnabled() && !UAStringUtil.isEmpty(getPushToken());
    }

    public boolean isOptIn() {
        return isPushEnabled() && isPushAvailable() && areNotificationsOptedIn();
    }

    public boolean areNotificationsOptedIn() {
        return getUserNotificationsEnabled() && this.notificationManagerCompat.areNotificationsEnabled();
    }

    public boolean isPushTokenRegistrationEnabled() {
        return getDataStore().getBoolean(PUSH_TOKEN_REGISTRATION_ENABLED_KEY, isDataCollectionEnabled());
    }

    public void setPushTokenRegistrationEnabled(boolean z) {
        getDataStore().put(PUSH_TOKEN_REGISTRATION_ENABLED_KEY, z);
        this.airshipChannel.updateRegistration();
    }

    public String getLastReceivedMetadata() {
        return this.preferenceDataStore.getString(LAST_RECEIVED_METADATA, (String) null);
    }

    /* access modifiers changed from: package-private */
    public void setLastReceivedMetadata(String str) {
        this.preferenceDataStore.put(LAST_RECEIVED_METADATA, str);
    }

    public void setNotificationListener(NotificationListener notificationListener2) {
        this.notificationListener = notificationListener2;
    }

    public void addPushListener(PushListener pushListener) {
        this.pushListeners.add(pushListener);
    }

    public void removePushListener(PushListener pushListener) {
        this.pushListeners.remove(pushListener);
    }

    public void addPushTokenListener(PushTokenListener pushTokenListener) {
        this.pushTokenListeners.add(pushTokenListener);
    }

    public void removePushTokenListener(PushTokenListener pushTokenListener) {
        this.pushTokenListeners.remove(pushTokenListener);
    }

    public void addInternalNotificationListener(InternalNotificationListener internalNotificationListener) {
        this.internalNotificationListeners.add(internalNotificationListener);
    }

    public NotificationListener getNotificationListener() {
        return this.notificationListener;
    }

    /* access modifiers changed from: package-private */
    public List<PushListener> getPushListeners() {
        return this.pushListeners;
    }

    public void addNotificationActionButtonGroup(String str, NotificationActionButtonGroup notificationActionButtonGroup) {
        if (str.startsWith("ua_")) {
            Logger.error("Unable to add any notification button groups that starts with the reserved Airship prefix %s", "ua_");
            return;
        }
        this.actionGroupMap.put(str, notificationActionButtonGroup);
    }

    public void addNotificationActionButtonGroups(Context context2, int i) {
        for (Map.Entry next : ActionButtonGroupsParser.fromXml(context2, i).entrySet()) {
            addNotificationActionButtonGroup((String) next.getKey(), (NotificationActionButtonGroup) next.getValue());
        }
    }

    public void removeNotificationActionButtonGroup(String str) {
        if (str.startsWith("ua_")) {
            Logger.error("Unable to remove any reserved Airship actions groups that begin with %s", "ua_");
            return;
        }
        this.actionGroupMap.remove(str);
    }

    public NotificationActionButtonGroup getNotificationActionGroup(String str) {
        if (str == null) {
            return null;
        }
        return this.actionGroupMap.get(str);
    }

    public String getPushToken() {
        return this.preferenceDataStore.getString(PUSH_TOKEN_KEY, (String) null);
    }

    /* access modifiers changed from: package-private */
    public void migratePushEnabledSettings() {
        if (!this.preferenceDataStore.getBoolean(PUSH_ENABLED_SETTINGS_MIGRATED_KEY, false)) {
            Logger.debug("Migrating push enabled preferences", new Object[0]);
            boolean z = this.preferenceDataStore.getBoolean(PUSH_ENABLED_KEY, false);
            Logger.debug("Setting user notifications enabled to %s", Boolean.toString(z));
            this.preferenceDataStore.put(USER_NOTIFICATIONS_ENABLED_KEY, z);
            if (!z) {
                Logger.info("Push is now enabled. You can continue to toggle the opt-in state by enabling or disabling user notifications", new Object[0]);
            }
            this.preferenceDataStore.put(PUSH_ENABLED_KEY, true);
            this.preferenceDataStore.put(PUSH_ENABLED_SETTINGS_MIGRATED_KEY, true);
        }
    }

    public PushProvider getPushProvider() {
        return this.pushProvider;
    }

    /* access modifiers changed from: package-private */
    public boolean isUniqueCanonicalId(String str) {
        if (UAStringUtil.isEmpty(str)) {
            return true;
        }
        synchronized (this.uniqueIdLock) {
            JsonList jsonList = null;
            try {
                jsonList = JsonValue.parseString(this.preferenceDataStore.getString(LAST_CANONICAL_IDS_KEY, (String) null)).getList();
            } catch (JsonException e) {
                Logger.debug(e, "PushJobHandler - Unable to parse canonical Ids.", new Object[0]);
            }
            List arrayList = jsonList == null ? new ArrayList() : jsonList.getList();
            JsonValue wrap = JsonValue.wrap(str);
            if (arrayList.contains(wrap)) {
                return false;
            }
            arrayList.add(wrap);
            if (arrayList.size() > 10) {
                arrayList = arrayList.subList(arrayList.size() - 10, arrayList.size());
            }
            this.preferenceDataStore.put(LAST_CANONICAL_IDS_KEY, JsonValue.wrapOpt(arrayList).toString());
            return true;
        }
    }

    private void dispatchUpdatePushTokenJob() {
        this.jobDispatcher.dispatch(JobInfo.newBuilder().setAction(ACTION_UPDATE_PUSH_REGISTRATION).setId(4).setAirshipComponent((Class<? extends AirshipComponent>) PushManager.class).build());
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0066, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int performPushRegistration(boolean r7) {
        /*
            r6 = this;
            java.lang.String r0 = r6.getPushToken()
            com.urbanairship.push.PushProvider r1 = r6.pushProvider
            r2 = 0
            if (r1 != 0) goto L_0x0011
            java.lang.String r7 = "PushManager - Push registration failed. Missing push provider."
            java.lang.Object[] r0 = new java.lang.Object[r2]
            com.urbanairship.Logger.info(r7, r0)
            return r2
        L_0x0011:
            monitor-enter(r1)
            com.urbanairship.push.PushProvider r3 = r6.pushProvider     // Catch:{ all -> 0x0089 }
            android.content.Context r4 = r6.context     // Catch:{ all -> 0x0089 }
            boolean r3 = r3.isAvailable(r4)     // Catch:{ all -> 0x0089 }
            r4 = 1
            if (r3 != 0) goto L_0x002a
            java.lang.String r7 = "PushManager - Push registration failed. Push provider unavailable: %s"
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch:{ all -> 0x0089 }
            com.urbanairship.push.PushProvider r3 = r6.pushProvider     // Catch:{ all -> 0x0089 }
            r0[r2] = r3     // Catch:{ all -> 0x0089 }
            com.urbanairship.Logger.warn(r7, r0)     // Catch:{ all -> 0x0089 }
            monitor-exit(r1)     // Catch:{ all -> 0x0089 }
            return r4
        L_0x002a:
            com.urbanairship.push.PushProvider r3 = r6.pushProvider     // Catch:{ RegistrationException -> 0x0067 }
            android.content.Context r5 = r6.context     // Catch:{ RegistrationException -> 0x0067 }
            java.lang.String r3 = r3.getRegistrationToken(r5)     // Catch:{ RegistrationException -> 0x0067 }
            if (r3 == 0) goto L_0x0065
            boolean r0 = com.urbanairship.util.UAStringUtil.equals(r3, r0)     // Catch:{ all -> 0x0089 }
            if (r0 != 0) goto L_0x0065
            java.lang.String r0 = "PushManager - Push registration updated."
            java.lang.Object[] r4 = new java.lang.Object[r2]     // Catch:{ all -> 0x0089 }
            com.urbanairship.Logger.info(r0, r4)     // Catch:{ all -> 0x0089 }
            com.urbanairship.PreferenceDataStore r0 = r6.preferenceDataStore     // Catch:{ all -> 0x0089 }
            java.lang.String r4 = "com.urbanairship.push.REGISTRATION_TOKEN_KEY"
            r0.put((java.lang.String) r4, (java.lang.String) r3)     // Catch:{ all -> 0x0089 }
            java.util.List<com.urbanairship.push.PushTokenListener> r0 = r6.pushTokenListeners     // Catch:{ all -> 0x0089 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0089 }
        L_0x004e:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0089 }
            if (r4 == 0) goto L_0x005e
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0089 }
            com.urbanairship.push.PushTokenListener r4 = (com.urbanairship.push.PushTokenListener) r4     // Catch:{ all -> 0x0089 }
            r4.onPushTokenUpdated(r3)     // Catch:{ all -> 0x0089 }
            goto L_0x004e
        L_0x005e:
            if (r7 == 0) goto L_0x0065
            com.urbanairship.channel.AirshipChannel r7 = r6.airshipChannel     // Catch:{ all -> 0x0089 }
            r7.updateRegistration()     // Catch:{ all -> 0x0089 }
        L_0x0065:
            monitor-exit(r1)     // Catch:{ all -> 0x0089 }
            return r2
        L_0x0067:
            r7 = move-exception
            boolean r0 = r7.isRecoverable()     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0080
            java.lang.String r0 = "PushManager - Push registration failed with error: %s. Will retry."
            java.lang.Object[] r3 = new java.lang.Object[r4]     // Catch:{ all -> 0x0089 }
            java.lang.String r5 = r7.getMessage()     // Catch:{ all -> 0x0089 }
            r3[r2] = r5     // Catch:{ all -> 0x0089 }
            com.urbanairship.Logger.debug(r0, r3)     // Catch:{ all -> 0x0089 }
            com.urbanairship.Logger.verbose(r7)     // Catch:{ all -> 0x0089 }
            monitor-exit(r1)     // Catch:{ all -> 0x0089 }
            return r4
        L_0x0080:
            java.lang.String r0 = "PushManager - Push registration failed."
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ all -> 0x0089 }
            com.urbanairship.Logger.error(r7, r0, r3)     // Catch:{ all -> 0x0089 }
            monitor-exit(r1)     // Catch:{ all -> 0x0089 }
            return r2
        L_0x0089:
            r7 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0089 }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.push.PushManager.performPushRegistration(boolean):int");
    }

    /* access modifiers changed from: protected */
    public void onDataCollectionEnabledChanged(boolean z) {
        this.airshipChannel.updateRegistration();
    }

    /* access modifiers changed from: package-private */
    public List<InternalNotificationListener> getInternalNotificationListeners() {
        return this.internalNotificationListeners;
    }

    /* access modifiers changed from: private */
    public Map<String, String> createAnalyticsHeaders() {
        HashMap hashMap = new HashMap();
        hashMap.put("X-UA-Channel-Opted-In", Boolean.toString(isOptIn()));
        hashMap.put("X-UA-Channel-Background-Enabled", Boolean.toString(isPushEnabled() && isPushAvailable()));
        return hashMap;
    }
}
