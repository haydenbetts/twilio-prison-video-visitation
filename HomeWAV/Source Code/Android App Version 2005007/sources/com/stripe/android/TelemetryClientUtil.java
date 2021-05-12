package com.stripe.android;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import lib.android.paypal.com.magnessdk.a.b;

class TelemetryClientUtil {
    TelemetryClientUtil() {
    }

    static Map<String, Object> createTelemetryMap(Context context) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        hashMap.put("v2", 1);
        hashMap.put("tag", BuildConfig.VERSION_NAME);
        hashMap.put("src", "android-sdk");
        hashMap2.put("c", createSingleValuePair(Locale.getDefault().toString()));
        hashMap2.put("d", createSingleValuePair(getAndroidVersionString()));
        hashMap2.put("f", createSingleValuePair(getScreen(context)));
        hashMap2.put("g", createSingleValuePair(getTimeZoneString()));
        hashMap.put("a", hashMap2);
        hashMap3.put("d", getHashedMuid(context));
        String packageName = getPackageName(context);
        hashMap3.put("k", packageName);
        hashMap3.put("o", Build.VERSION.RELEASE);
        hashMap3.put(TtmlNode.TAG_P, Integer.valueOf(Build.VERSION.SDK_INT));
        hashMap3.put("q", Build.MANUFACTURER);
        hashMap3.put("r", Build.BRAND);
        hashMap3.put("s", Build.MODEL);
        hashMap3.put("t", Build.TAGS);
        if (context.getPackageName() != null) {
            try {
                hashMap3.put("l", context.getPackageManager().getPackageInfo(packageName, 0).versionName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        hashMap.put("b", hashMap3);
        return hashMap;
    }

    private static Map<String, Object> createSingleValuePair(Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put("v", obj);
        return hashMap;
    }

    private static String getTimeZoneString() {
        int convert = (int) TimeUnit.MINUTES.convert((long) TimeZone.getDefault().getRawOffset(), TimeUnit.MILLISECONDS);
        if (convert % 60 == 0) {
            return String.valueOf(convert / 60);
        }
        return new BigDecimal(convert).setScale(2, 6).divide(new BigDecimal(60), new MathContext(2)).setScale(2, 6).toString();
    }

    private static String getScreen(Context context) {
        if (context.getResources() == null) {
            return "";
        }
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        int i3 = context.getResources().getDisplayMetrics().densityDpi;
        return String.format(Locale.ENGLISH, "%dw_%dh_%ddpi", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3)});
    }

    private static String getAndroidVersionString() {
        return "Android" + " " + Build.VERSION.RELEASE + " " + Build.VERSION.CODENAME + " " + Build.VERSION.SDK_INT;
    }

    static String getHashedId(Context context) {
        String shaHashInput;
        String string = Settings.Secure.getString(context.getContentResolver(), b.f);
        if (!StripeTextUtils.isBlank(string) && (shaHashInput = StripeTextUtils.shaHashInput(string)) != null) {
            return shaHashInput;
        }
        return "";
    }

    private static String getHashedMuid(Context context) {
        String hashedId = getHashedId(context);
        String packageName = getPackageName(context);
        String shaHashInput = StripeTextUtils.shaHashInput(packageName + hashedId);
        return shaHashInput == null ? "" : shaHashInput;
    }

    private static String getPackageName(Context context) {
        return (context.getApplicationContext() == null || context.getApplicationContext().getPackageName() == null) ? "" : context.getApplicationContext().getPackageName();
    }
}
