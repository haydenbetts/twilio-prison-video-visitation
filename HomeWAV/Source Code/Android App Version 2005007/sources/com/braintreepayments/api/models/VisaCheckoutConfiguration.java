package com.braintreepayments.api.models;

import com.braintreepayments.api.Json;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import org.json.JSONObject;

public class VisaCheckoutConfiguration {
    private String mApiKey;
    private List<String> mCardBrands;
    private String mExternalClientId;
    private boolean mIsEnabled;

    static VisaCheckoutConfiguration fromJson(JSONObject jSONObject) {
        VisaCheckoutConfiguration visaCheckoutConfiguration = new VisaCheckoutConfiguration();
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String optString = Json.optString(jSONObject, "apikey", "");
        visaCheckoutConfiguration.mApiKey = optString;
        visaCheckoutConfiguration.mIsEnabled = !optString.equals("");
        visaCheckoutConfiguration.mExternalClientId = Json.optString(jSONObject, "externalClientId", "");
        visaCheckoutConfiguration.mCardBrands = supportedCardTypesToAcceptedCardBrands(CardConfiguration.fromJson(jSONObject).getSupportedCardTypes());
        return visaCheckoutConfiguration;
    }

    public boolean isEnabled() {
        return this.mIsEnabled;
    }

    public String getExternalClientId() {
        return this.mExternalClientId;
    }

    public String getApiKey() {
        return this.mApiKey;
    }

    public List<String> getAcceptedCardBrands() {
        return this.mCardBrands;
    }

    private static List<String> supportedCardTypesToAcceptedCardBrands(Set<String> set) {
        ArrayList arrayList = new ArrayList();
        for (String lowerCase : set) {
            String lowerCase2 = lowerCase.toLowerCase(Locale.ROOT);
            lowerCase2.hashCode();
            char c = 65535;
            switch (lowerCase2.hashCode()) {
                case -2038717326:
                    if (lowerCase2.equals("mastercard")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1120637072:
                    if (lowerCase2.equals("american express")) {
                        c = 1;
                        break;
                    }
                    break;
                case 3619905:
                    if (lowerCase2.equals("visa")) {
                        c = 2;
                        break;
                    }
                    break;
                case 273184745:
                    if (lowerCase2.equals("discover")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    arrayList.add("MASTERCARD");
                    break;
                case 1:
                    arrayList.add("AMEX");
                    break;
                case 2:
                    arrayList.add("VISA");
                    break;
                case 3:
                    arrayList.add("DISCOVER");
                    break;
            }
        }
        return arrayList;
    }
}
