package com.urbanairship.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;

public class Network {
    public static boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) UAirship.getApplicationContext().getSystemService("connectivity");
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
                return false;
            }
            return true;
        }
        Logger.error("Error fetching network info.", new Object[0]);
        return false;
    }
}
