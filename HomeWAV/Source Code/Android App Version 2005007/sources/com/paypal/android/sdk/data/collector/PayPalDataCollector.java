package com.paypal.android.sdk.data.collector;

import android.content.Context;
import android.util.Log;
import lib.android.paypal.com.magnessdk.Environment;
import lib.android.paypal.com.magnessdk.InvalidInputException;
import lib.android.paypal.com.magnessdk.MagnesSDK;
import lib.android.paypal.com.magnessdk.MagnesSettings;
import lib.android.paypal.com.magnessdk.MagnesSource;

public class PayPalDataCollector {
    public static String getClientMetadataId(Context context) {
        return getClientMetadataId(context, new PayPalDataCollectorRequest().setApplicationGuid(InstallationIdentifier.getInstallationGUID(context)));
    }

    public static String getClientMetadataId(Context context, String str) {
        return getClientMetadataId(context, new PayPalDataCollectorRequest().setApplicationGuid(InstallationIdentifier.getInstallationGUID(context)).setClientMetadataId(str));
    }

    public static String getClientMetadataId(Context context, PayPalDataCollectorRequest payPalDataCollectorRequest) {
        if (context == null) {
            return "";
        }
        try {
            MagnesSDK instance = MagnesSDK.getInstance();
            instance.setUp(new MagnesSettings.Builder(context).setMagnesSource(MagnesSource.BRAINTREE).disableBeacon(payPalDataCollectorRequest.isDisableBeacon()).setMagnesEnvironment(Environment.LIVE).setAppGuid(payPalDataCollectorRequest.getApplicationGuid()).build());
            return instance.collectAndSubmit(context, payPalDataCollectorRequest.getClientMetadataId(), payPalDataCollectorRequest.getAdditionalData()).getPaypalClientMetaDataId();
        } catch (InvalidInputException e) {
            Log.e("Exception", "Error fetching client metadata ID. Contact Braintree Support for assistance.", e);
            return "";
        }
    }
}
