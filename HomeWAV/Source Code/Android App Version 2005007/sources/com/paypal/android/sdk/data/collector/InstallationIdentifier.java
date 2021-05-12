package com.paypal.android.sdk.data.collector;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class InstallationIdentifier {
    private static final String INSTALL_GUID = "InstallationGUID";

    public static String getInstallationGUID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PayPalOTC", 0);
        String string = sharedPreferences.getString(INSTALL_GUID, (String) null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        sharedPreferences.edit().putString(INSTALL_GUID, uuid).apply();
        return uuid;
    }
}
