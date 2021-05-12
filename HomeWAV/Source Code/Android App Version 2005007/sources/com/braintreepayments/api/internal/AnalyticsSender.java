package com.braintreepayments.api.internal;

import android.content.Context;
import android.os.Build;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.models.Authorization;
import com.braintreepayments.api.models.ClientToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AnalyticsSender {
    private static final String ANALYTICS_KEY = "analytics";
    private static final String AUTHORIZATION_FINGERPRINT_KEY = "authorization_fingerprint";
    private static final String DEVICE_APP_GENERATED_PERSISTENT_UUID_KEY = "deviceAppGeneratedPersistentUuid";
    private static final String DEVICE_MANUFACTURER_KEY = "deviceManufacturer";
    private static final String DEVICE_MODEL_KEY = "deviceModel";
    private static final String DEVICE_ROOTED_KEY = "deviceRooted";
    private static final String IS_SIMULATOR_KEY = "isSimulator";
    private static final String KIND_KEY = "kind";
    private static final String MERCHANT_APP_ID_KEY = "merchantAppId";
    private static final String MERCHANT_APP_NAME_KEY = "merchantAppName";
    private static final String META_KEY = "_meta";
    private static final String PLATFORM_KEY = "platform";
    private static final String PLATFORM_VERSION_KEY = "platformVersion";
    private static final String SDK_VERSION_KEY = "sdkVersion";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String TOKENIZATION_KEY = "tokenization_key";

    public static void send(Context context, Authorization authorization, BraintreeHttpClient braintreeHttpClient, String str, boolean z) {
        final AnalyticsDatabase instance = AnalyticsDatabase.getInstance(context);
        try {
            for (final List next : instance.getPendingRequests()) {
                JSONObject serializeEvents = serializeEvents(context, authorization, next);
                if (z) {
                    try {
                        braintreeHttpClient.post(str, serializeEvents.toString());
                        instance.removeEvents(next);
                    } catch (Exception unused) {
                    }
                } else {
                    braintreeHttpClient.post(str, serializeEvents.toString(), new HttpResponseCallback() {
                        public void failure(Exception exc) {
                        }

                        public void success(String str) {
                            instance.removeEvents(next);
                        }
                    });
                }
            }
        } catch (JSONException unused2) {
        }
    }

    private static JSONObject serializeEvents(Context context, Authorization authorization, List<AnalyticsEvent> list) throws JSONException {
        AnalyticsEvent analyticsEvent = list.get(0);
        JSONObject jSONObject = new JSONObject();
        if (authorization instanceof ClientToken) {
            jSONObject.put(AUTHORIZATION_FINGERPRINT_KEY, authorization.getBearer());
        } else {
            jSONObject.put(TOKENIZATION_KEY, authorization.getBearer());
        }
        jSONObject.put("_meta", analyticsEvent.metadata.put(PLATFORM_KEY, "Android").put(PLATFORM_VERSION_KEY, Integer.toString(Build.VERSION.SDK_INT)).put(SDK_VERSION_KEY, "3.14.2").put(MERCHANT_APP_ID_KEY, context.getPackageName()).put(MERCHANT_APP_NAME_KEY, getAppName(context)).put(DEVICE_ROOTED_KEY, isDeviceRooted()).put(DEVICE_MANUFACTURER_KEY, Build.MANUFACTURER).put(DEVICE_MODEL_KEY, Build.MODEL).put(DEVICE_APP_GENERATED_PERSISTENT_UUID_KEY, UUIDHelper.getPersistentUUID(context)).put(IS_SIMULATOR_KEY, detectEmulator()));
        JSONArray jSONArray = new JSONArray();
        for (AnalyticsEvent next : list) {
            jSONArray.put(new JSONObject().put(KIND_KEY, next.event).put("timestamp", next.timestamp));
        }
        jSONObject.put("analytics", jSONArray);
        return jSONObject;
    }

    private static String detectEmulator() {
        return ("google_sdk".equalsIgnoreCase(Build.PRODUCT) || "sdk".equalsIgnoreCase(Build.PRODUCT) || "Genymotion".equalsIgnoreCase(Build.MANUFACTURER) || Build.FINGERPRINT.contains("generic")) ? "true" : "false";
    }

    /* JADX WARNING: type inference failed for: r3v3, types: [java.lang.CharSequence] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String getAppName(android.content.Context r3) {
        /*
            java.lang.String r0 = r3.getPackageName()
            android.content.pm.PackageManager r3 = r3.getPackageManager()
            r1 = 0
            r2 = 0
            android.content.pm.ApplicationInfo r0 = r3.getApplicationInfo(r0, r1)     // Catch:{ NameNotFoundException -> 0x000f }
            goto L_0x0010
        L_0x000f:
            r0 = r2
        L_0x0010:
            if (r0 == 0) goto L_0x0019
            java.lang.CharSequence r3 = r3.getApplicationLabel(r0)
            r2 = r3
            java.lang.String r2 = (java.lang.String) r2
        L_0x0019:
            if (r2 != 0) goto L_0x001e
            java.lang.String r3 = "ApplicationNameUnknown"
            return r3
        L_0x001e:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.internal.AnalyticsSender.getAppName(android.content.Context):java.lang.String");
    }

    private static String isDeviceRooted() {
        boolean z;
        boolean z2;
        String str = Build.TAGS;
        boolean z3 = true;
        boolean z4 = str != null && str.contains("test-keys");
        try {
            z = new File("/system/app/Superuser.apk").exists();
        } catch (Exception unused) {
            z = false;
        }
        try {
            if (new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"}).getInputStream())).readLine() != null) {
                z2 = true;
                if (!z4 && !z && !z2) {
                    z3 = false;
                }
                return Boolean.toString(z3);
            }
        } catch (Exception unused2) {
        }
        z2 = false;
        z3 = false;
        return Boolean.toString(z3);
    }
}
