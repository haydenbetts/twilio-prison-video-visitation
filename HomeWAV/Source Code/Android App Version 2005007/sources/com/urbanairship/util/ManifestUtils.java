package com.urbanairship.util;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import com.urbanairship.UAirship;

public class ManifestUtils {
    public static final String ENABLE_LOCAL_STORAGE = "com.urbanairship.webview.ENABLE_LOCAL_STORAGE";
    private static final String INSTALL_NETWORK_SECURITY_PROVIDER = "com.urbanairship.INSTALL_NETWORK_SECURITY_PROVIDER";
    public static final String LOCAL_STORAGE_DATABASE_DIRECTORY = "com.urbanairship.webview.localstorage";

    public static boolean isPermissionGranted(String str) {
        return UAirship.getPackageManager().checkPermission(str, UAirship.getPackageName()) == 0;
    }

    public static ActivityInfo getActivityInfo(Class cls) {
        if (cls.getCanonicalName() == null) {
            return null;
        }
        try {
            return UAirship.getPackageManager().getActivityInfo(new ComponentName(UAirship.getPackageName(), cls.getCanonicalName()), 128);
        } catch (Exception unused) {
            return null;
        }
    }

    public static ApplicationInfo getApplicationInfo() {
        try {
            return UAirship.getPackageManager().getApplicationInfo(UAirship.getPackageName(), 128);
        } catch (Exception unused) {
            return null;
        }
    }

    public static boolean shouldEnableLocalStorage() {
        ApplicationInfo applicationInfo = getApplicationInfo();
        if (applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.getBoolean(ENABLE_LOCAL_STORAGE, false)) {
            return false;
        }
        return true;
    }

    public static boolean shouldInstallNetworkSecurityProvider() {
        ApplicationInfo applicationInfo = getApplicationInfo();
        if (applicationInfo == null || applicationInfo.metaData == null || !applicationInfo.metaData.getBoolean(INSTALL_NETWORK_SECURITY_PROVIDER, false)) {
            return false;
        }
        return true;
    }
}
