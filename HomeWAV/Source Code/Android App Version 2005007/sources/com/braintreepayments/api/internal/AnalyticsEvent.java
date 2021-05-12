package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.braintreepayments.api.Venmo;
import com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsEvent {
    private static final String DEVICE_NETWORK_TYPE_KEY = "deviceNetworkType";
    private static final String DROP_IN_VERSION_KEY = "dropinVersion";
    private static final String INTEGRATION_TYPE_KEY = "integrationType";
    private static final String MERCHANT_APP_VERSION_KEY = "merchantAppVersion";
    private static final String PAYPAL_INSTALLED_KEY = "paypalInstalled";
    private static final String SESSION_ID_KEY = "sessionId";
    private static final String USER_INTERFACE_ORIENTATION_KEY = "userInterfaceOrientation";
    private static final String VENMO_INSTALLED_KEY = "venmoInstalled";
    String event;
    int id;
    JSONObject metadata = new JSONObject();
    long timestamp;

    public AnalyticsEvent() {
    }

    public AnalyticsEvent(Context context, String str, String str2, String str3) {
        this.event = "android." + str3;
        this.timestamp = System.currentTimeMillis();
        try {
            this.metadata.put(SESSION_ID_KEY, str).put(INTEGRATION_TYPE_KEY, str2).put(DEVICE_NETWORK_TYPE_KEY, getNetworkType(context)).put(USER_INTERFACE_ORIENTATION_KEY, getUserOrientation(context)).put(MERCHANT_APP_VERSION_KEY, getAppVersion(context)).put(PAYPAL_INSTALLED_KEY, isPayPalInstalled(context)).put(VENMO_INSTALLED_KEY, Venmo.isVenmoInstalled(context)).put(DROP_IN_VERSION_KEY, getDropInVersion());
        } catch (JSONException unused) {
        }
    }

    private String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        String typeName = activeNetworkInfo != null ? activeNetworkInfo.getTypeName() : null;
        return typeName == null ? "none" : typeName;
    }

    private String getUserOrientation(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        if (i != 1) {
            return i != 2 ? "Unknown" : "Landscape";
        }
        return "Portrait";
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "VersionUnknown";
        }
    }

    private boolean isPayPalInstalled(Context context) {
        try {
            Class.forName(PayPalOneTouchCore.class.getName());
            return PayPalOneTouchCore.isWalletAppInstalled(context);
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    private static String getDropInVersion() {
        return (String) ClassHelper.getFieldValue("com.braintreepayments.api.dropin.BuildConfig", "VERSION_NAME");
    }
}
