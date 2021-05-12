package com.braintreepayments.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Base64;
import androidx.exifinterface.media.ExifInterface;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.braintreepayments.api.internal.BraintreeSharedPreferences;
import com.braintreepayments.api.models.Configuration;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

class ConfigurationManager {
    static final long TTL = TimeUnit.MINUTES.toMillis(5);
    static boolean sFetchingConfiguration = false;

    private ConfigurationManager() {
    }

    static boolean isFetchingConfiguration() {
        return sFetchingConfiguration;
    }

    static void getConfiguration(final BraintreeFragment braintreeFragment, final ConfigurationListener configurationListener, final BraintreeResponseListener<Exception> braintreeResponseListener) {
        final String uri = Uri.parse(braintreeFragment.getAuthorization().getConfigUrl()).buildUpon().appendQueryParameter("configVersion", ExifInterface.GPS_MEASUREMENT_3D).build().toString();
        Context applicationContext = braintreeFragment.getApplicationContext();
        Configuration cachedConfiguration = getCachedConfiguration(applicationContext, uri + braintreeFragment.getAuthorization().getBearer());
        if (cachedConfiguration != null) {
            configurationListener.onConfigurationFetched(cachedConfiguration);
            return;
        }
        sFetchingConfiguration = true;
        braintreeFragment.getHttpClient().get(uri, new HttpResponseCallback() {
            public void success(String str) {
                try {
                    Configuration fromJson = Configuration.fromJson(str);
                    Context applicationContext = braintreeFragment.getApplicationContext();
                    ConfigurationManager.cacheConfiguration(applicationContext, uri + braintreeFragment.getAuthorization().getBearer(), fromJson);
                    ConfigurationManager.sFetchingConfiguration = false;
                    configurationListener.onConfigurationFetched(fromJson);
                } catch (JSONException e) {
                    ConfigurationManager.sFetchingConfiguration = false;
                    braintreeResponseListener.onResponse(e);
                }
            }

            public void failure(Exception exc) {
                ConfigurationManager.sFetchingConfiguration = false;
                braintreeResponseListener.onResponse(exc);
            }
        });
    }

    private static Configuration getCachedConfiguration(Context context, String str) {
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(context);
        String encodeToString = Base64.encodeToString(str.getBytes(), 0);
        if (System.currentTimeMillis() - sharedPreferences.getLong(encodeToString + "_timestamp", 0) > TTL) {
            return null;
        }
        try {
            return Configuration.fromJson(sharedPreferences.getString(encodeToString, ""));
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static void cacheConfiguration(Context context, String str, Configuration configuration) {
        String encodeToString = Base64.encodeToString(str.getBytes(), 0);
        BraintreeSharedPreferences.getSharedPreferences(context).edit().putString(encodeToString, configuration.toJson()).putLong(encodeToString + "_timestamp", System.currentTimeMillis()).apply();
    }
}
