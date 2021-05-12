package com.urbanairship.google;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import com.urbanairship.Logger;

public class PlayServicesUtils {
    private static final int CONNECTION_SUCCESS = 0;
    private static final String GOOGLE_PLAY_STORE_PACKAGE = "com.android.vending";
    private static final String GOOGLE_PLAY_STORE_PACKAGE_OLD = "com.google.market";
    public static final int MISSING_PLAY_SERVICE_DEPENDENCY = -1;
    private static Boolean isFusedLocationDependencyAvailable;
    private static Boolean isGoogleAdsDependencyAvailable;
    private static Boolean isGooglePlayServicesDependencyAvailable;
    private static Boolean isGooglePlayStoreAvailable;

    public static void handleAnyPlayServicesError(Context context) {
        if (isGooglePlayServicesDependencyAvailable()) {
            try {
                int isGooglePlayServicesAvailable = GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(context);
                if (isGooglePlayServicesAvailable != 0) {
                    if (GooglePlayServicesUtilWrapper.isUserRecoverableError(isGooglePlayServicesAvailable)) {
                        Logger.debug("Launching Play Services Activity to resolve error.", new Object[0]);
                        try {
                            context.startActivity(new Intent(context, PlayServicesErrorActivity.class));
                        } catch (ActivityNotFoundException e) {
                            Logger.error(e);
                        }
                    } else {
                        Logger.info("Error %s is not user recoverable.", Integer.valueOf(isGooglePlayServicesAvailable));
                    }
                }
            } catch (IllegalStateException e2) {
                Logger.error(e2, "Google Play services developer error.", new Object[0]);
            }
        }
    }

    public static int isGooglePlayServicesAvailable(Context context) {
        if (isGooglePlayServicesDependencyAvailable()) {
            return GooglePlayServicesUtilWrapper.isGooglePlayServicesAvailable(context);
        }
        return -1;
    }

    public static boolean isGooglePlayServicesDependencyAvailable() {
        if (isGooglePlayServicesDependencyAvailable == null) {
            try {
                Class.forName("com.google.android.gms.common.GooglePlayServicesUtil");
                isGooglePlayServicesDependencyAvailable = true;
            } catch (ClassNotFoundException unused) {
                isGooglePlayServicesDependencyAvailable = false;
            }
        }
        return isGooglePlayServicesDependencyAvailable.booleanValue();
    }

    public static boolean isFusedLocationDependencyAvailable() {
        if (isFusedLocationDependencyAvailable == null) {
            if (!isGooglePlayServicesDependencyAvailable()) {
                isFusedLocationDependencyAvailable = false;
            } else {
                try {
                    Class.forName("com.google.android.gms.location.FusedLocationProviderClient");
                    isFusedLocationDependencyAvailable = true;
                } catch (ClassNotFoundException unused) {
                    isFusedLocationDependencyAvailable = false;
                }
            }
        }
        return isFusedLocationDependencyAvailable.booleanValue();
    }

    public static boolean isGoogleAdsDependencyAvailable() {
        if (isGoogleAdsDependencyAvailable == null) {
            if (!isGooglePlayServicesDependencyAvailable()) {
                isGoogleAdsDependencyAvailable = false;
            } else {
                try {
                    Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                    isGoogleAdsDependencyAvailable = true;
                } catch (ClassNotFoundException unused) {
                    isGoogleAdsDependencyAvailable = false;
                }
            }
        }
        return isGoogleAdsDependencyAvailable.booleanValue();
    }

    public static boolean isGooglePlayStoreAvailable(Context context) {
        if (isGooglePlayStoreAvailable == null) {
            isGooglePlayStoreAvailable = Boolean.valueOf(isPackageAvailable(context, "com.android.vending") || isPackageAvailable(context, GOOGLE_PLAY_STORE_PACKAGE_OLD));
        }
        return isGooglePlayStoreAvailable.booleanValue();
    }

    private static boolean isPackageAvailable(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
