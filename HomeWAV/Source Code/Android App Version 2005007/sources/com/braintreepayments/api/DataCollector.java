package com.braintreepayments.api;

import android.text.TextUtils;
import com.braintreepayments.api.interfaces.BraintreeResponseListener;
import com.braintreepayments.api.interfaces.ConfigurationListener;
import com.braintreepayments.api.internal.UUIDHelper;
import com.braintreepayments.api.models.ClientToken;
import com.braintreepayments.api.models.Configuration;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.kount.api.DataCollector;
import com.paypal.android.sdk.data.collector.InstallationIdentifier;
import com.paypal.android.sdk.data.collector.PayPalDataCollector;
import com.paypal.android.sdk.data.collector.PayPalDataCollectorRequest;
import java.util.HashMap;
import lib.android.paypal.com.magnessdk.a;
import org.json.JSONException;
import org.json.JSONObject;

public class DataCollector {
    private static final String CORRELATION_ID_KEY = "correlation_id";
    private static final String DEVICE_SESSION_ID_KEY = "device_session_id";
    private static final String FRAUD_MERCHANT_ID_KEY = "fraud_merchant_id";

    public static void collectDeviceData(BraintreeFragment braintreeFragment, BraintreeResponseListener<String> braintreeResponseListener) {
        collectDeviceData(braintreeFragment, (String) null, braintreeResponseListener);
    }

    public static void collectDeviceData(final BraintreeFragment braintreeFragment, final String str, final BraintreeResponseListener<String> braintreeResponseListener) {
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                final JSONObject jSONObject = new JSONObject();
                try {
                    String payPalClientMetadataId = DataCollector.getPayPalClientMetadataId(braintreeFragment.getApplicationContext());
                    if (!TextUtils.isEmpty(payPalClientMetadataId)) {
                        jSONObject.put(DataCollector.CORRELATION_ID_KEY, payPalClientMetadataId);
                    }
                } catch (JSONException unused) {
                }
                if (configuration.getKount().isEnabled()) {
                    final String str = str;
                    if (str == null) {
                        str = configuration.getKount().getKountMerchantId();
                    }
                    try {
                        final String formattedUUID = UUIDHelper.getFormattedUUID();
                        DataCollector.startDeviceCollector(braintreeFragment, str, formattedUUID, new BraintreeResponseListener<String>() {
                            public void onResponse(String str) {
                                try {
                                    jSONObject.put(DataCollector.DEVICE_SESSION_ID_KEY, formattedUUID);
                                    jSONObject.put(DataCollector.FRAUD_MERCHANT_ID_KEY, str);
                                } catch (JSONException unused) {
                                }
                                braintreeResponseListener.onResponse(jSONObject.toString());
                            }
                        });
                    } catch (ClassNotFoundException | NoClassDefFoundError | NumberFormatException unused2) {
                        braintreeResponseListener.onResponse(jSONObject.toString());
                    }
                } else {
                    braintreeResponseListener.onResponse(jSONObject.toString());
                }
            }
        });
    }

    public static void collectPayPalDeviceData(BraintreeFragment braintreeFragment, BraintreeResponseListener<String> braintreeResponseListener) {
        JSONObject jSONObject = new JSONObject();
        try {
            String payPalClientMetadataId = getPayPalClientMetadataId(braintreeFragment.getApplicationContext());
            if (!TextUtils.isEmpty(payPalClientMetadataId)) {
                jSONObject.put(CORRELATION_ID_KEY, payPalClientMetadataId);
            }
        } catch (JSONException unused) {
        }
        braintreeResponseListener.onResponse(jSONObject.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0009, code lost:
        return com.paypal.android.sdk.data.collector.PayPalDataCollector.getClientMetadataId(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000a, code lost:
        return "";
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0005 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String getPayPalClientMetadataId(android.content.Context r0) {
        /*
            java.lang.String r0 = com.paypal.android.sdk.onetouch.core.PayPalOneTouchCore.getClientMetadataId(r0)     // Catch:{ NoClassDefFoundError -> 0x0005 }
            return r0
        L_0x0005:
            java.lang.String r0 = com.paypal.android.sdk.data.collector.PayPalDataCollector.getClientMetadataId(r0)     // Catch:{ NoClassDefFoundError -> 0x000a }
            return r0
        L_0x000a:
            java.lang.String r0 = ""
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.DataCollector.getPayPalClientMetadataId(android.content.Context):java.lang.String");
    }

    /* access modifiers changed from: private */
    public static void startDeviceCollector(final BraintreeFragment braintreeFragment, final String str, final String str2, final BraintreeResponseListener<String> braintreeResponseListener) throws ClassNotFoundException, NumberFormatException {
        braintreeFragment.sendAnalyticsEvent("data-collector.kount.started");
        Class.forName(com.kount.api.DataCollector.class.getName());
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                com.kount.api.DataCollector instance = com.kount.api.DataCollector.getInstance();
                instance.setContext(braintreeFragment.getApplicationContext());
                instance.setMerchantID(Integer.parseInt(str));
                instance.setLocationCollectorConfig(DataCollector.LocationConfig.COLLECT);
                instance.setEnvironment(DataCollector.getDeviceCollectorEnvironment(configuration.getEnvironment()));
                instance.collectForSession(str2, new DataCollector.CompletionHandler() {
                    public void completed(String str) {
                        braintreeFragment.sendAnalyticsEvent("data-collector.kount.succeeded");
                        if (braintreeResponseListener != null) {
                            braintreeResponseListener.onResponse(str);
                        }
                    }

                    public void failed(String str, DataCollector.Error error) {
                        braintreeFragment.sendAnalyticsEvent("data-collector.kount.failed");
                        if (braintreeResponseListener != null) {
                            braintreeResponseListener.onResponse(str);
                        }
                    }
                });
            }
        });
    }

    static void collectRiskData(final BraintreeFragment braintreeFragment, final PaymentMethodNonce paymentMethodNonce) {
        braintreeFragment.waitForConfiguration(new ConfigurationListener() {
            public void onConfigurationFetched(Configuration configuration) {
                String customerId;
                if (configuration.getCardConfiguration().isFraudDataCollectionEnabled()) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("rda_tenant", "bt_card");
                    hashMap.put("mid", configuration.getMerchantId());
                    if ((braintreeFragment.getAuthorization() instanceof ClientToken) && (customerId = ((ClientToken) braintreeFragment.getAuthorization()).getCustomerId()) != null) {
                        hashMap.put("cid", customerId);
                    }
                    PayPalDataCollector.getClientMetadataId(braintreeFragment.getApplicationContext(), new PayPalDataCollectorRequest().setApplicationGuid(InstallationIdentifier.getInstallationGUID(braintreeFragment.getApplicationContext())).setClientMetadataId(paymentMethodNonce.getNonce()).setDisableBeacon(true).setAdditionalData(hashMap));
                }
            }
        });
    }

    static int getDeviceCollectorEnvironment(String str) {
        return a.d.equalsIgnoreCase(str) ? 2 : 1;
    }
}
