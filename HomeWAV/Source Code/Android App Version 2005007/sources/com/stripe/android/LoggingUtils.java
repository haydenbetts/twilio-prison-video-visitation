package com.stripe.android;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class LoggingUtils {
    private static final String ANALYTICS_NAME = "stripe_android";
    private static final String ANALYTICS_PREFIX = "analytics";
    private static final String ANALYTICS_VERSION = "1.0";
    static final String EVENT_ADD_PAYMENT_METHOD = "add_payment_method";
    static final String EVENT_ADD_SOURCE = "add_source";
    static final String EVENT_CONFIRM_PAYMENT_INTENT = "payment_intent_confirmation";
    static final String EVENT_DEFAULT_SOURCE = "default_source";
    static final String EVENT_DELETE_SOURCE = "delete_source";
    static final String EVENT_RETRIEVE_PAYMENT_INTENT = "payment_intent_retrieval";
    static final String EVENT_SET_SHIPPING_INFO = "set_shipping_info";
    static final String EVENT_SOURCE_CREATION = "source_creation";
    static final String EVENT_TOKEN_CREATION = "token_creation";
    static final String FIELD_ANALYTICS_UA = "analytics_ua";
    static final String FIELD_APP_NAME = "app_name";
    static final String FIELD_APP_VERSION = "app_version";
    static final String FIELD_BINDINGS_VERSION = "bindings_version";
    static final String FIELD_DEVICE_TYPE = "device_type";
    static final String FIELD_EVENT = "event";
    static final String FIELD_OS_NAME = "os_name";
    static final String FIELD_OS_RELEASE = "os_release";
    static final String FIELD_OS_VERSION = "os_version";
    static final String FIELD_PRODUCT_USAGE = "product_usage";
    static final String FIELD_PUBLISHABLE_KEY = "publishable_key";
    static final String FIELD_SOURCE_TYPE = "source_type";
    static final String FIELD_TOKEN_TYPE = "token_type";
    static final String NO_CONTEXT = "no_context";
    static final String UNKNOWN = "unknown";
    static final Set<String> VALID_PARAM_FIELDS;

    static String getAnalyticsUa() {
        return "analytics.stripe_android-1.0";
    }

    LoggingUtils() {
    }

    static {
        HashSet hashSet = new HashSet();
        VALID_PARAM_FIELDS = hashSet;
        hashSet.add(FIELD_ANALYTICS_UA);
        hashSet.add(FIELD_APP_NAME);
        hashSet.add(FIELD_APP_VERSION);
        hashSet.add(FIELD_BINDINGS_VERSION);
        hashSet.add(FIELD_DEVICE_TYPE);
        hashSet.add("event");
        hashSet.add(FIELD_OS_VERSION);
        hashSet.add(FIELD_OS_NAME);
        hashSet.add(FIELD_OS_RELEASE);
        hashSet.add(FIELD_PRODUCT_USAGE);
        hashSet.add(FIELD_PUBLISHABLE_KEY);
        hashSet.add(FIELD_SOURCE_TYPE);
        hashSet.add(FIELD_TOKEN_TYPE);
    }

    static Map<String, Object> getTokenCreationParams(Context context, List<String> list, String str, String str2) {
        return getEventLoggingParams(context.getApplicationContext(), list, (String) null, str2, str, EVENT_TOKEN_CREATION);
    }

    static Map<String, Object> getPaymentMethodCreationParams(Context context, List<String> list, String str) {
        return getEventLoggingParams(context.getApplicationContext(), list, (String) null, (String) null, str, EVENT_ADD_PAYMENT_METHOD);
    }

    static Map<String, Object> getSourceCreationParams(Context context, List<String> list, String str, String str2) {
        return getEventLoggingParams(context.getApplicationContext(), list, str2, (String) null, str, EVENT_SOURCE_CREATION);
    }

    static Map<String, Object> getAddSourceParams(Context context, List<String> list, String str, String str2) {
        return getEventLoggingParams(context.getApplicationContext(), list, str2, (String) null, str, EVENT_ADD_SOURCE);
    }

    static Map<String, Object> getDeleteSourceParams(Context context, List<String> list, String str) {
        return getEventLoggingParams(context.getApplicationContext(), list, (String) null, (String) null, str, EVENT_DELETE_SOURCE);
    }

    static Map<String, Object> getPaymentIntentConfirmationParams(Context context, List<String> list, String str, String str2) {
        return getEventLoggingParams(context.getApplicationContext(), list, str2, (String) null, str, EVENT_CONFIRM_PAYMENT_INTENT);
    }

    static Map<String, Object> getPaymentIntentRetrieveParams(Context context, List<String> list, String str) {
        return getEventLoggingParams(context.getApplicationContext(), list, (String) null, (String) null, str, EVENT_RETRIEVE_PAYMENT_INTENT);
    }

    static Map<String, Object> getEventLoggingParams(Context context, List<String> list, String str, String str2, String str3, String str4) {
        HashMap hashMap = new HashMap();
        hashMap.put(FIELD_ANALYTICS_UA, getAnalyticsUa());
        hashMap.put("event", getEventParamName(str4));
        hashMap.put(FIELD_PUBLISHABLE_KEY, str3);
        hashMap.put(FIELD_OS_NAME, Build.VERSION.CODENAME);
        hashMap.put(FIELD_OS_RELEASE, Build.VERSION.RELEASE);
        hashMap.put(FIELD_OS_VERSION, Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap.put(FIELD_DEVICE_TYPE, getDeviceLoggingString());
        hashMap.put(FIELD_BINDINGS_VERSION, BuildConfig.VERSION_NAME);
        addNameAndVersion(hashMap, context.getPackageManager(), context.getPackageName());
        if (list != null) {
            hashMap.put(FIELD_PRODUCT_USAGE, list);
        }
        if (str != null) {
            hashMap.put(FIELD_SOURCE_TYPE, str);
        }
        if (str2 != null) {
            hashMap.put(FIELD_TOKEN_TYPE, str2);
        } else if (str == null) {
            hashMap.put(FIELD_TOKEN_TYPE, "unknown");
        }
        return hashMap;
    }

    static void addNameAndVersion(Map<String, Object> map, PackageManager packageManager, String str) {
        if (packageManager != null) {
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                String str2 = null;
                if (packageInfo.applicationInfo != null) {
                    CharSequence loadLabel = packageInfo.applicationInfo.loadLabel(packageManager);
                    if (loadLabel != null) {
                        str2 = loadLabel.toString();
                    }
                    map.put(FIELD_APP_NAME, str2);
                }
                if (StripeTextUtils.isBlank(str2)) {
                    map.put(FIELD_APP_NAME, packageInfo.packageName);
                }
                map.put(FIELD_APP_VERSION, Integer.valueOf(packageInfo.versionCode));
            } catch (PackageManager.NameNotFoundException unused) {
                map.put(FIELD_APP_NAME, "unknown");
                map.put(FIELD_APP_VERSION, "unknown");
            }
        } else {
            map.put(FIELD_APP_NAME, NO_CONTEXT);
            map.put(FIELD_APP_VERSION, NO_CONTEXT);
        }
    }

    private static String getDeviceLoggingString() {
        return Build.MANUFACTURER + '_' + Build.BRAND + '_' + Build.MODEL;
    }

    static String getEventParamName(String str) {
        return "stripe_android." + str;
    }
}
