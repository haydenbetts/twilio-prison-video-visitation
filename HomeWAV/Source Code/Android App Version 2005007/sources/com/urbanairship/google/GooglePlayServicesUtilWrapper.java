package com.urbanairship.google;

import android.content.Context;
import com.google.android.gms.common.GoogleApiAvailability;

public class GooglePlayServicesUtilWrapper {
    public static boolean isUserRecoverableError(int i) {
        return GoogleApiAvailability.getInstance().isUserResolvableError(i);
    }

    public static int isGooglePlayServicesAvailable(Context context) {
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context);
    }
}
