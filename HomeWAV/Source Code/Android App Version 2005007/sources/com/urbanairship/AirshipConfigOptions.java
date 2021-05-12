package com.urbanairship;

import android.content.Context;
import android.net.Uri;
import com.urbanairship.push.PushProvider;
import com.urbanairship.util.Checks;
import com.urbanairship.util.ConfigParser;
import com.urbanairship.util.PropertiesConfigParser;
import com.urbanairship.util.UAStringUtil;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;
import kotlin.text.Typography;
import lib.android.paypal.com.magnessdk.a;

public class AirshipConfigOptions {
    public static final String ADM_TRANSPORT = "ADM";
    private static final Pattern APP_CREDENTIAL_PATTERN = Pattern.compile("^[a-zA-Z0-9\\-_]{22}$");
    private static final long DEFAULT_BG_REPORTING_INTERVAL_MS = 86400000;
    private static final int DEFAULT_DEVELOPMENT_LOG_LEVEL = 3;
    private static final int DEFAULT_PRODUCTION_LOG_LEVEL = 6;
    private static final String EU_ANALYTICS_URL = "https://combine.asnapieu.com/";
    private static final String EU_DEVICE_URL = "https://device-api.asnapieu.com/";
    private static final String EU_REMOTE_DATA_URL = "https://remote-data.asnapieu.com/";
    private static final String EU_WALLET_URL = "https://wallet-api.asnapieu.com";
    public static final String FCM_TRANSPORT = "FCM";
    public static final String HMS_TRANSPORT = "HMS";
    private static final long MAX_BG_REPORTING_INTERVAL_MS = 86400000;
    private static final long MIN_BG_REPORTING_INTERVAL_MS = 60000;
    public static final String SITE_EU = "EU";
    public static final String SITE_US = "US";
    private static final String US_ANALYTICS_URL = "https://combine.urbanairship.com/";
    private static final String US_DEVICE_URL = "https://device-api.urbanairship.com/";
    private static final String US_REMOTE_DATA_URL = "https://remote-data.urbanairship.com/";
    private static final String US_WALLET_URL = "https://wallet-api.urbanairship.com";
    public final List<String> allowedTransports;
    public final boolean analyticsEnabled;
    public final String analyticsUrl;
    public final String appKey;
    public final String appSecret;
    public final Uri appStoreUri;
    public final boolean autoLaunchApplication;
    public final long backgroundReportingIntervalMS;
    public final boolean channelCaptureEnabled;
    public final boolean channelCreationDelayEnabled;
    public final PushProvider customPushProvider;
    public final boolean dataCollectionOptInEnabled;
    public final String deviceUrl;
    public final boolean extendedBroadcastsEnabled;
    public final String fcmSenderId;
    public final boolean inProduction;
    public final int logLevel;
    public final int notificationAccentColor;
    public final String notificationChannel;
    public final int notificationIcon;
    public final int notificationLargeIcon;
    public final String remoteDataUrl;
    public final List<String> urlAllowList;
    public final List<String> urlAllowListScopeJavaScriptInterface;
    public final List<String> urlAllowListScopeOpenUrl;
    public final String walletUrl;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Site {
    }

    private AirshipConfigOptions(Builder builder) {
        if (builder.inProduction.booleanValue()) {
            this.appKey = firstOrEmpty(builder.productionAppKey, builder.appKey);
            this.appSecret = firstOrEmpty(builder.productionAppSecret, builder.appSecret);
            this.fcmSenderId = firstOrNull(builder.productionFcmSenderId, builder.fcmSenderId);
            this.logLevel = first(builder.productionLogLevel, builder.logLevel, 6);
        } else {
            this.appKey = firstOrEmpty(builder.developmentAppKey, builder.appKey);
            this.appSecret = firstOrEmpty(builder.developmentAppSecret, builder.appSecret);
            this.fcmSenderId = firstOrNull(builder.developmentFcmSenderId, builder.fcmSenderId);
            this.logLevel = first(builder.developmentLogLevel, builder.logLevel, 3);
        }
        String access$1300 = builder.site;
        char c = 65535;
        int hashCode = access$1300.hashCode();
        if (hashCode != 2224) {
            if (hashCode == 2718 && access$1300.equals(SITE_US)) {
                c = 1;
            }
        } else if (access$1300.equals(SITE_EU)) {
            c = 0;
        }
        if (c != 0) {
            this.deviceUrl = firstOrEmpty(builder.deviceUrl, US_DEVICE_URL);
            this.analyticsUrl = firstOrEmpty(builder.analyticsUrl, US_ANALYTICS_URL);
            this.remoteDataUrl = firstOrEmpty(builder.remoteDataUrl, US_REMOTE_DATA_URL);
            this.walletUrl = firstOrEmpty(builder.walletUrl, US_WALLET_URL);
        } else {
            this.deviceUrl = firstOrEmpty(builder.deviceUrl, EU_DEVICE_URL);
            this.analyticsUrl = firstOrEmpty(builder.analyticsUrl, EU_ANALYTICS_URL);
            this.remoteDataUrl = firstOrEmpty(builder.remoteDataUrl, EU_REMOTE_DATA_URL);
            this.walletUrl = firstOrEmpty(builder.walletUrl, EU_WALLET_URL);
        }
        this.allowedTransports = Collections.unmodifiableList(new ArrayList(builder.allowedTransports));
        this.urlAllowList = Collections.unmodifiableList(new ArrayList(builder.urlAllowList));
        this.urlAllowListScopeJavaScriptInterface = Collections.unmodifiableList(new ArrayList(builder.urlAllowListScopeJavaScriptInterface));
        this.urlAllowListScopeOpenUrl = Collections.unmodifiableList(new ArrayList(builder.urlAllowListScopeOpenUrl));
        this.inProduction = builder.inProduction.booleanValue();
        this.analyticsEnabled = builder.analyticsEnabled;
        this.backgroundReportingIntervalMS = builder.backgroundReportingIntervalMS;
        this.autoLaunchApplication = builder.autoLaunchApplication;
        this.channelCreationDelayEnabled = builder.channelCreationDelayEnabled;
        this.channelCaptureEnabled = builder.channelCaptureEnabled;
        this.notificationIcon = builder.notificationIcon;
        this.notificationLargeIcon = builder.notificationLargeIcon;
        this.notificationAccentColor = builder.notificationAccentColor;
        this.notificationChannel = builder.notificationChannel;
        this.customPushProvider = builder.customPushProvider;
        this.appStoreUri = builder.appStoreUri;
        this.dataCollectionOptInEnabled = builder.dataCollectionOptInEnabled;
        this.extendedBroadcastsEnabled = builder.extendedBroadcastsEnabled;
    }

    public void validate() {
        String str = this.inProduction ? a.d : "development";
        Pattern pattern = APP_CREDENTIAL_PATTERN;
        if (!pattern.matcher(this.appKey).matches()) {
            throw new IllegalArgumentException("AirshipConfigOptions: " + this.appKey + " is not a valid " + str + " app key");
        } else if (pattern.matcher(this.appSecret).matches()) {
            long j = this.backgroundReportingIntervalMS;
            if (j < 60000) {
                Logger.warn("AirshipConfigOptions - The backgroundReportingIntervalMS %s may decrease battery life.", Long.valueOf(j));
            } else if (j > 86400000) {
                Logger.warn("AirshipConfigOptions - The backgroundReportingIntervalMS %s may provide less detailed analytic reports.", Long.valueOf(j));
            }
        } else {
            throw new IllegalArgumentException("AirshipConfigOptions: " + this.appSecret + " is not a valid " + str + " app secret");
        }
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    /* access modifiers changed from: private */
    public static String parseSite(String str) {
        if (SITE_EU.equalsIgnoreCase(str)) {
            return SITE_EU;
        }
        if (SITE_US.equalsIgnoreCase(str)) {
            return SITE_US;
        }
        throw new IllegalArgumentException("Invalid site: " + str);
    }

    private static String firstOrNull(String... strArr) {
        for (String str : strArr) {
            if (!UAStringUtil.isEmpty(str)) {
                return str;
            }
        }
        return null;
    }

    private static String firstOrEmpty(String... strArr) {
        for (String str : strArr) {
            if (!UAStringUtil.isEmpty(str)) {
                return str;
            }
        }
        return "";
    }

    private static int first(Integer... numArr) {
        for (Integer num : numArr) {
            if (num != null) {
                return num.intValue();
            }
        }
        return 0;
    }

    public static final class Builder {
        private static final String CONFIG_ELEMENT = "AirshipConfigOptions";
        private static final String DEFAULT_PROPERTIES_FILENAME = "airshipconfig.properties";
        private static final String FIELD_ALLOWED_TRANSPORTS = "allowedTransports";
        private static final String FIELD_ANALYTICS_ENABLED = "analyticsEnabled";
        private static final String FIELD_ANALYTICS_URL = "analyticsUrl";
        private static final String FIELD_APP_KEY = "appKey";
        private static final String FIELD_APP_SECRET = "appSecret";
        private static final String FIELD_APP_STORE_URI = "appStoreUri";
        private static final String FIELD_AUTO_LAUNCH_APPLICATION = "autoLaunchApplication";
        private static final String FIELD_BACKGROUND_REPORTING_INTERVAL_MS = "backgroundReportingIntervalMS";
        private static final String FIELD_CHANNEL_CAPTURE_ENABLED = "channelCaptureEnabled";
        private static final String FIELD_CHANNEL_CREATION_DELAY_ENABLED = "channelCreationDelayEnabled";
        private static final String FIELD_CUSTOM_PUSH_PROVIDER = "customPushProvider";
        private static final String FIELD_DATA_COLLECTION_OPT_IN_ENABLED = "dataCollectionOptInEnabled";
        private static final String FIELD_DEVELOPMENT_APP_KEY = "developmentAppKey";
        private static final String FIELD_DEVELOPMENT_APP_SECRET = "developmentAppSecret";
        private static final String FIELD_DEVELOPMENT_FCM_SENDER_ID = "developmentFcmSenderId";
        private static final String FIELD_DEVELOPMENT_LOG_LEVEL = "developmentLogLevel";
        private static final String FIELD_DEVICE_URL = "deviceUrl";
        private static final String FIELD_ENABLE_URL_ALLOW_LIST = "enableUrlAllowList";
        private static final String FIELD_EXTENDED_BROADCASTS_ENABLED = "extendedBroadcastsEnabled";
        private static final String FIELD_FCM_SENDER_ID = "fcmSenderId";
        private static final String FIELD_GCM_SENDER = "gcmSender";
        private static final String FIELD_IN_PRODUCTION = "inProduction";
        private static final String FIELD_LEGACY_ANALYTICS_SERVER = "analyticsServer";
        private static final String FIELD_LEGACY_DEVICE_URL = "hostURL";
        private static final String FIELD_LEGACY_REMOTE_DATA_URL = "remoteDataURL";
        private static final String FIELD_LOG_LEVEL = "logLevel";
        private static final String FIELD_NOTIFICATION_ACCENT_COLOR = "notificationAccentColor";
        private static final String FIELD_NOTIFICATION_CHANNEL = "notificationChannel";
        private static final String FIELD_NOTIFICATION_ICON = "notificationIcon";
        private static final String FIELD_NOTIFICATION_LARGE_ICON = "notificationLargeIcon";
        private static final String FIELD_PRODUCTION_APP_KEY = "productionAppKey";
        private static final String FIELD_PRODUCTION_APP_SECRET = "productionAppSecret";
        private static final String FIELD_PRODUCTION_FCM_SENDER_ID = "productionFcmSenderId";
        private static final String FIELD_PRODUCTION_LOG_LEVEL = "productionLogLevel";
        private static final String FIELD_REMOTE_DATA_URL = "remoteDataUrl";
        private static final String FIELD_SITE = "site";
        private static final String FIELD_URL_ALLOW_LIST = "urlAllowList";
        private static final String FIELD_URL_ALLOW_LIST_SCOPE_JAVASCRIPT_INTERFACE = "urlAllowListScopeJavaScriptInterface";
        private static final String FIELD_URL_ALLOW_LIST_SCOPE_OPEN_URL = "urlAllowListScopeOpenUrl";
        private static final String FIELD_WALLET_URL = "walletUrl";
        /* access modifiers changed from: private */
        public List<String> allowedTransports = new ArrayList(Arrays.asList(new String[]{AirshipConfigOptions.ADM_TRANSPORT, "FCM", AirshipConfigOptions.HMS_TRANSPORT}));
        /* access modifiers changed from: private */
        public boolean analyticsEnabled = true;
        /* access modifiers changed from: private */
        public String analyticsUrl;
        /* access modifiers changed from: private */
        public String appKey;
        /* access modifiers changed from: private */
        public String appSecret;
        /* access modifiers changed from: private */
        public Uri appStoreUri;
        /* access modifiers changed from: private */
        public boolean autoLaunchApplication = true;
        /* access modifiers changed from: private */
        public long backgroundReportingIntervalMS = 86400000;
        /* access modifiers changed from: private */
        public boolean channelCaptureEnabled = true;
        /* access modifiers changed from: private */
        public boolean channelCreationDelayEnabled = false;
        /* access modifiers changed from: private */
        public PushProvider customPushProvider;
        /* access modifiers changed from: private */
        public boolean dataCollectionOptInEnabled;
        /* access modifiers changed from: private */
        public String developmentAppKey;
        /* access modifiers changed from: private */
        public String developmentAppSecret;
        /* access modifiers changed from: private */
        public String developmentFcmSenderId;
        /* access modifiers changed from: private */
        public Integer developmentLogLevel;
        /* access modifiers changed from: private */
        public String deviceUrl;
        /* access modifiers changed from: private */
        public boolean extendedBroadcastsEnabled;
        /* access modifiers changed from: private */
        public String fcmSenderId;
        /* access modifiers changed from: private */
        public Boolean inProduction = null;
        /* access modifiers changed from: private */
        public Integer logLevel;
        /* access modifiers changed from: private */
        public int notificationAccentColor = 0;
        /* access modifiers changed from: private */
        public String notificationChannel;
        /* access modifiers changed from: private */
        public int notificationIcon;
        /* access modifiers changed from: private */
        public int notificationLargeIcon;
        /* access modifiers changed from: private */
        public String productionAppKey;
        /* access modifiers changed from: private */
        public String productionAppSecret;
        /* access modifiers changed from: private */
        public String productionFcmSenderId;
        /* access modifiers changed from: private */
        public Integer productionLogLevel;
        /* access modifiers changed from: private */
        public String remoteDataUrl;
        /* access modifiers changed from: private */
        public String site = AirshipConfigOptions.SITE_US;
        /* access modifiers changed from: private */
        public List<String> urlAllowList = new ArrayList();
        /* access modifiers changed from: private */
        public List<String> urlAllowListScopeJavaScriptInterface = new ArrayList();
        /* access modifiers changed from: private */
        public List<String> urlAllowListScopeOpenUrl = new ArrayList();
        /* access modifiers changed from: private */
        public String walletUrl;

        public Builder applyDefaultProperties(Context context) {
            return applyProperties(context, DEFAULT_PROPERTIES_FILENAME);
        }

        public Builder applyProperties(Context context, String str) {
            try {
                applyConfigParser(context, PropertiesConfigParser.fromAssets(context, str));
            } catch (Exception e) {
                Logger.error(e, "AirshipConfigOptions - Unable to apply config.", new Object[0]);
            }
            return this;
        }

        public Builder applyProperties(Context context, Properties properties) {
            try {
                applyConfigParser(context, PropertiesConfigParser.fromProperties(context, properties));
            } catch (Exception e) {
                Logger.error(e, "AirshipConfigOptions - Unable to apply config.", new Object[0]);
            }
            return this;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001b, code lost:
            if (r0 == null) goto L_0x001e;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x001e, code lost:
            return r2;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:3:0x000a, code lost:
            if (r0 != null) goto L_0x000c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:4:0x000c, code lost:
            r0.close();
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public com.urbanairship.AirshipConfigOptions.Builder applyConfig(android.content.Context r3, int r4) {
            /*
                r2 = this;
                r0 = 0
                java.lang.String r1 = "AirshipConfigOptions"
                com.urbanairship.util.XmlConfigParser r0 = com.urbanairship.util.XmlConfigParser.parseElement(r3, r4, r1)     // Catch:{ Exception -> 0x0012 }
                r2.applyConfigParser(r3, r0)     // Catch:{ Exception -> 0x0012 }
                if (r0 == 0) goto L_0x001e
            L_0x000c:
                r0.close()
                goto L_0x001e
            L_0x0010:
                r3 = move-exception
                goto L_0x001f
            L_0x0012:
                r3 = move-exception
                java.lang.String r4 = "AirshipConfigOptions - Unable to apply config."
                r1 = 0
                java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0010 }
                com.urbanairship.Logger.error(r3, r4, r1)     // Catch:{ all -> 0x0010 }
                if (r0 == 0) goto L_0x001e
                goto L_0x000c
            L_0x001e:
                return r2
            L_0x001f:
                if (r0 == 0) goto L_0x0024
                r0.close()
            L_0x0024:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.AirshipConfigOptions.Builder.applyConfig(android.content.Context, int):com.urbanairship.AirshipConfigOptions$Builder");
        }

        private void applyConfigParser(Context context, ConfigParser configParser) {
            for (int i = 0; i < configParser.getCount(); i++) {
                try {
                    String name = configParser.getName(i);
                    if (name != null) {
                        char c = 65535;
                        switch (name.hashCode()) {
                            case -2131444128:
                                if (name.equals(FIELD_CHANNEL_CREATION_DELAY_ENABLED)) {
                                    c = 25;
                                    break;
                                }
                                break;
                            case -1829910004:
                                if (name.equals(FIELD_APP_STORE_URI)) {
                                    c = '%';
                                    break;
                                }
                                break;
                            case -1776171144:
                                if (name.equals(FIELD_PRODUCTION_APP_SECRET)) {
                                    c = 3;
                                    break;
                                }
                                break;
                            case -1720015653:
                                if (name.equals(FIELD_ANALYTICS_ENABLED)) {
                                    c = 19;
                                    break;
                                }
                                break;
                            case -1653850041:
                                if (name.equals("whitelist")) {
                                    c = 14;
                                    break;
                                }
                                break;
                            case -1597596356:
                                if (name.equals(FIELD_CUSTOM_PUSH_PROVIDER)) {
                                    c = Typography.dollar;
                                    break;
                                }
                                break;
                            case -1565695247:
                                if (name.equals(FIELD_DATA_COLLECTION_OPT_IN_ENABLED)) {
                                    c = '\'';
                                    break;
                                }
                                break;
                            case -1565320553:
                                if (name.equals(FIELD_PRODUCTION_APP_KEY)) {
                                    c = 2;
                                    break;
                                }
                                break;
                            case -1554123216:
                                if (name.equals(FIELD_URL_ALLOW_LIST_SCOPE_JAVASCRIPT_INTERFACE)) {
                                    c = 16;
                                    break;
                                }
                                break;
                            case -1411093378:
                                if (name.equals(FIELD_APP_KEY)) {
                                    c = 0;
                                    break;
                                }
                                break;
                            case -1387253559:
                                if (name.equals(FIELD_URL_ALLOW_LIST_SCOPE_OPEN_URL)) {
                                    c = 17;
                                    break;
                                }
                                break;
                            case -1285301710:
                                if (name.equals(FIELD_ALLOWED_TRANSPORTS)) {
                                    c = 13;
                                    break;
                                }
                                break;
                            case -1266098791:
                                if (name.equals(FIELD_DEVELOPMENT_APP_KEY)) {
                                    c = 4;
                                    break;
                                }
                                break;
                            case -1249058386:
                                if (name.equals(FIELD_AUTO_LAUNCH_APPLICATION)) {
                                    c = 24;
                                    break;
                                }
                                break;
                            case -1106202922:
                                if (name.equals(FIELD_EXTENDED_BROADCASTS_ENABLED)) {
                                    c = '(';
                                    break;
                                }
                                break;
                            case -874660855:
                                if (name.equals(FIELD_ANALYTICS_URL)) {
                                    c = 9;
                                    break;
                                }
                                break;
                            case -398391045:
                                if (name.equals(FIELD_DEVELOPMENT_LOG_LEVEL)) {
                                    c = 21;
                                    break;
                                }
                                break;
                            case -361592578:
                                if (name.equals(FIELD_CHANNEL_CAPTURE_ENABLED)) {
                                    c = 26;
                                    break;
                                }
                                break;
                            case -318159706:
                                if (name.equals(FIELD_GCM_SENDER)) {
                                    c = 12;
                                    break;
                                }
                                break;
                            case -187695495:
                                if (name.equals(FIELD_PRODUCTION_LOG_LEVEL)) {
                                    c = 22;
                                    break;
                                }
                                break;
                            case -116200981:
                                if (name.equals(FIELD_BACKGROUND_REPORTING_INTERVAL_MS)) {
                                    c = 20;
                                    break;
                                }
                                break;
                            case -93122203:
                                if (name.equals(FIELD_DEVELOPMENT_FCM_SENDER_ID)) {
                                    c = '!';
                                    break;
                                }
                                break;
                            case 3530567:
                                if (name.equals(FIELD_SITE)) {
                                    c = Typography.amp;
                                    break;
                                }
                                break;
                            case 24145854:
                                if (name.equals(FIELD_IN_PRODUCTION)) {
                                    c = 18;
                                    break;
                                }
                                break;
                            case 25200441:
                                if (name.equals(FIELD_DEVICE_URL)) {
                                    c = 7;
                                    break;
                                }
                                break;
                            case 233293225:
                                if (name.equals(FIELD_NOTIFICATION_LARGE_ICON)) {
                                    c = 28;
                                    break;
                                }
                                break;
                            case 282201398:
                                if (name.equals(FIELD_DEVELOPMENT_APP_SECRET)) {
                                    c = 5;
                                    break;
                                }
                                break;
                            case 476084841:
                                if (name.equals(FIELD_LEGACY_ANALYTICS_SERVER)) {
                                    c = 8;
                                    break;
                                }
                                break;
                            case 988154272:
                                if (name.equals(FIELD_FCM_SENDER_ID)) {
                                    c = ' ';
                                    break;
                                }
                                break;
                            case 1065256263:
                                if (name.equals("enableUrlWhitelisting")) {
                                    c = '#';
                                    break;
                                }
                                break;
                            case 1098683047:
                                if (name.equals(FIELD_LEGACY_DEVICE_URL)) {
                                    c = 6;
                                    break;
                                }
                                break;
                            case 1465076406:
                                if (name.equals(FIELD_WALLET_URL)) {
                                    c = 30;
                                    break;
                                }
                                break;
                            case 1485559857:
                                if (name.equals(FIELD_APP_SECRET)) {
                                    c = 1;
                                    break;
                                }
                                break;
                            case 1505552078:
                                if (name.equals(FIELD_NOTIFICATION_ACCENT_COLOR)) {
                                    c = 29;
                                    break;
                                }
                                break;
                            case 1611189252:
                                if (name.equals(FIELD_NOTIFICATION_ICON)) {
                                    c = 27;
                                    break;
                                }
                                break;
                            case 1779744152:
                                if (name.equals(FIELD_NOTIFICATION_CHANNEL)) {
                                    c = 31;
                                    break;
                                }
                                break;
                            case 1790788391:
                                if (name.equals(FIELD_PRODUCTION_FCM_SENDER_ID)) {
                                    c = Typography.quote;
                                    break;
                                }
                                break;
                            case 1855914712:
                                if (name.equals(FIELD_URL_ALLOW_LIST)) {
                                    c = 15;
                                    break;
                                }
                                break;
                            case 1958618687:
                                if (name.equals(FIELD_LEGACY_REMOTE_DATA_URL)) {
                                    c = 10;
                                    break;
                                }
                                break;
                            case 1958619711:
                                if (name.equals(FIELD_REMOTE_DATA_URL)) {
                                    c = 11;
                                    break;
                                }
                                break;
                            case 1995731616:
                                if (name.equals(FIELD_LOG_LEVEL)) {
                                    c = 23;
                                    break;
                                }
                                break;
                        }
                        switch (c) {
                            case 0:
                                setAppKey(configParser.getString(name));
                                break;
                            case 1:
                                setAppSecret(configParser.getString(name));
                                break;
                            case 2:
                                setProductionAppKey(configParser.getString(name));
                                break;
                            case 3:
                                setProductionAppSecret(configParser.getString(name));
                                break;
                            case 4:
                                setDevelopmentAppKey(configParser.getString(name));
                                break;
                            case 5:
                                setDevelopmentAppSecret(configParser.getString(name));
                                break;
                            case 6:
                            case 7:
                                setDeviceUrl(configParser.getString(name, this.deviceUrl));
                                break;
                            case 8:
                            case 9:
                                setAnalyticsUrl(configParser.getString(name, this.analyticsUrl));
                                break;
                            case 10:
                            case 11:
                                setRemoteDataUrl(configParser.getString(name, this.remoteDataUrl));
                                break;
                            case 12:
                                throw new IllegalArgumentException("gcmSender no longer supported. Please use fcmSender or remove it to allow the Airship SDK to pull from the google-services.json.");
                            case 13:
                                setAllowedTransports(configParser.getStringArray(name));
                                break;
                            case 14:
                                Logger.error("Parameter whitelist is deprecated and will be removed in a future version of the SDK. Use urlAllowList instead.", new Object[0]);
                                setUrlAllowList(configParser.getStringArray(name));
                                break;
                            case 15:
                                setUrlAllowList(configParser.getStringArray(name));
                                break;
                            case 16:
                                setUrlAllowListScopeJavaScriptInterface(configParser.getStringArray(name));
                                break;
                            case 17:
                                setUrlAllowListScopeOpenUrl(configParser.getStringArray(name));
                                break;
                            case 18:
                                Boolean bool = this.inProduction;
                                setInProduction(configParser.getBoolean(name, bool != null && bool.booleanValue()));
                                break;
                            case 19:
                                setAnalyticsEnabled(configParser.getBoolean(name, this.analyticsEnabled));
                                break;
                            case 20:
                                setBackgroundReportingIntervalMS(configParser.getLong(name, this.backgroundReportingIntervalMS));
                                break;
                            case 21:
                                setDevelopmentLogLevel(Logger.parseLogLevel(configParser.getString(name), 3));
                                break;
                            case 22:
                                setProductionLogLevel(Logger.parseLogLevel(configParser.getString(name), 6));
                                break;
                            case 23:
                                setLogLevel(Logger.parseLogLevel(configParser.getString(name), 6));
                                break;
                            case 24:
                                setAutoLaunchApplication(configParser.getBoolean(name, this.autoLaunchApplication));
                                break;
                            case 25:
                                setChannelCreationDelayEnabled(configParser.getBoolean(name, this.channelCreationDelayEnabled));
                                break;
                            case 26:
                                setChannelCaptureEnabled(configParser.getBoolean(name, this.channelCaptureEnabled));
                                break;
                            case 27:
                                setNotificationIcon(configParser.getDrawableResourceId(name));
                                break;
                            case 28:
                                setNotificationLargeIcon(configParser.getDrawableResourceId(name));
                                break;
                            case 29:
                                setNotificationAccentColor(configParser.getColor(name, this.notificationAccentColor));
                                break;
                            case 30:
                                setWalletUrl(configParser.getString(name, this.walletUrl));
                                break;
                            case 31:
                                setNotificationChannel(configParser.getString(name));
                                break;
                            case ' ':
                                setFcmSenderId(configParser.getString(name));
                                break;
                            case '!':
                                setDevelopmentFcmSenderId(configParser.getString(name));
                                break;
                            case '\"':
                                setProductionFcmSenderId(configParser.getString(name));
                                break;
                            case '#':
                                Logger.error("Parameter enableUrlWhitelisting has been removed. See urlAllowListScopeJavaScriptBridge and urlAllowListScopeOpen instead.", new Object[0]);
                                break;
                            case '$':
                                String string = configParser.getString(name);
                                Checks.checkNotNull(string, "Missing custom push provider class name");
                                setCustomPushProvider((PushProvider) Class.forName(string).asSubclass(PushProvider.class).newInstance());
                                break;
                            case '%':
                                setAppStoreUri(Uri.parse(configParser.getString(name)));
                                break;
                            case '&':
                                setSite(AirshipConfigOptions.parseSite(configParser.getString(name)));
                                break;
                            case '\'':
                                setDataCollectionOptInEnabled(configParser.getBoolean(name, false));
                                break;
                            case '(':
                                setExtendedBroadcastsEnabled(configParser.getBoolean(name, false));
                                break;
                        }
                    }
                } catch (Exception e) {
                    Logger.error(e, "Unable to set config field '%s' due to invalid configuration value.", configParser.getName(i));
                }
            }
            if (this.inProduction == null) {
                detectProvisioningMode(context);
            }
        }

        public Builder setNotificationChannel(String str) {
            this.notificationChannel = str;
            return this;
        }

        public Builder setNotificationIcon(int i) {
            this.notificationIcon = i;
            return this;
        }

        public Builder setNotificationLargeIcon(int i) {
            this.notificationLargeIcon = i;
            return this;
        }

        public Builder setNotificationAccentColor(int i) {
            this.notificationAccentColor = i;
            return this;
        }

        public Builder setAppKey(String str) {
            this.appKey = str;
            return this;
        }

        public Builder setAppSecret(String str) {
            this.appSecret = str;
            return this;
        }

        public Builder setProductionAppKey(String str) {
            this.productionAppKey = str;
            return this;
        }

        public Builder setProductionAppSecret(String str) {
            this.productionAppSecret = str;
            return this;
        }

        public Builder setDevelopmentAppKey(String str) {
            this.developmentAppKey = str;
            return this;
        }

        public Builder setDevelopmentAppSecret(String str) {
            this.developmentAppSecret = str;
            return this;
        }

        public Builder setDeviceUrl(String str) {
            this.deviceUrl = str;
            return this;
        }

        public Builder setAnalyticsUrl(String str) {
            this.analyticsUrl = str;
            return this;
        }

        public Builder setRemoteDataUrl(String str) {
            this.remoteDataUrl = str;
            return this;
        }

        public Builder setProductionFcmSenderId(String str) {
            this.productionFcmSenderId = str;
            return this;
        }

        public Builder setDevelopmentFcmSenderId(String str) {
            this.developmentFcmSenderId = str;
            return this;
        }

        public Builder setFcmSenderId(String str) {
            this.fcmSenderId = str;
            return this;
        }

        public Builder setAllowedTransports(String[] strArr) {
            this.allowedTransports.clear();
            if (strArr != null) {
                this.allowedTransports.addAll(Arrays.asList(strArr));
            }
            return this;
        }

        public Builder setUrlAllowList(String[] strArr) {
            this.urlAllowList.clear();
            if (strArr != null) {
                this.urlAllowList.addAll(Arrays.asList(strArr));
            }
            return this;
        }

        public Builder setUrlAllowListScopeJavaScriptInterface(String[] strArr) {
            this.urlAllowListScopeJavaScriptInterface.clear();
            if (strArr != null) {
                this.urlAllowListScopeJavaScriptInterface.addAll(Arrays.asList(strArr));
            }
            return this;
        }

        public Builder setUrlAllowListScopeOpenUrl(String[] strArr) {
            this.urlAllowListScopeOpenUrl.clear();
            if (strArr != null) {
                this.urlAllowListScopeOpenUrl.addAll(Arrays.asList(strArr));
            }
            return this;
        }

        public Builder setInProduction(boolean z) {
            this.inProduction = Boolean.valueOf(z);
            return this;
        }

        public Builder detectProvisioningMode(Context context) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append(context.getPackageName());
                sb.append(".BuildConfig");
                this.inProduction = Boolean.valueOf(!((Boolean) Class.forName(sb.toString()).getField("DEBUG").get((Object) null)).booleanValue());
            } catch (Exception unused) {
                Logger.warn("AirshipConfigOptions - Unable to determine the build mode. Defaulting to debug.", new Object[0]);
                this.inProduction = false;
            }
            return this;
        }

        public Builder setAnalyticsEnabled(boolean z) {
            this.analyticsEnabled = z;
            return this;
        }

        public Builder setBackgroundReportingIntervalMS(long j) {
            this.backgroundReportingIntervalMS = j;
            return this;
        }

        public Builder setDevelopmentLogLevel(int i) {
            this.developmentLogLevel = Integer.valueOf(i);
            return this;
        }

        public Builder setProductionLogLevel(int i) {
            this.productionLogLevel = Integer.valueOf(i);
            return this;
        }

        public Builder setLogLevel(int i) {
            this.logLevel = Integer.valueOf(i);
            return this;
        }

        public Builder setAutoLaunchApplication(boolean z) {
            this.autoLaunchApplication = z;
            return this;
        }

        public Builder setChannelCreationDelayEnabled(boolean z) {
            this.channelCreationDelayEnabled = z;
            return this;
        }

        public Builder setChannelCaptureEnabled(boolean z) {
            this.channelCaptureEnabled = z;
            return this;
        }

        public Builder setWalletUrl(String str) {
            this.walletUrl = str;
            return this;
        }

        public Builder setCustomPushProvider(PushProvider pushProvider) {
            this.customPushProvider = pushProvider;
            return this;
        }

        public Builder setAppStoreUri(Uri uri) {
            this.appStoreUri = uri;
            return this;
        }

        public Builder setSite(String str) {
            this.site = str;
            return this;
        }

        public Builder setDataCollectionOptInEnabled(boolean z) {
            this.dataCollectionOptInEnabled = z;
            return this;
        }

        public Builder setExtendedBroadcastsEnabled(boolean z) {
            this.extendedBroadcastsEnabled = z;
            return this;
        }

        public AirshipConfigOptions build() {
            if (this.inProduction == null) {
                this.inProduction = false;
            }
            String str = this.productionAppKey;
            if (str != null && str.equals(this.developmentAppKey)) {
                Logger.warn("Production App Key matches Development App Key", new Object[0]);
            }
            String str2 = this.productionAppSecret;
            if (str2 != null && str2.equals(this.developmentAppSecret)) {
                Logger.warn("Production App Secret matches Development App Secret", new Object[0]);
            }
            return new AirshipConfigOptions(this);
        }
    }
}
