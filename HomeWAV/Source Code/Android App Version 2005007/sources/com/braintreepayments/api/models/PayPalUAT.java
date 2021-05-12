package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Base64;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.microsoft.appcenter.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalUAT extends Authorization {
    public static final Parcelable.Creator<PayPalUAT> CREATOR = new Parcelable.Creator<PayPalUAT>() {
        public PayPalUAT createFromParcel(Parcel parcel) {
            return new PayPalUAT(parcel);
        }

        public PayPalUAT[] newArray(int i) {
            return new PayPalUAT[i];
        }
    };
    private static final String EXTERNAL_ID_STRING = "external_id";
    protected static final String MATCHER = "^[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.[a-zA-Z0-9_-]+$";
    private String mBraintreeMerchantID;
    private String mConfigUrl;
    private Environment mEnvironment;
    private String mPayPalUrl;
    private String mToken;

    public enum Environment {
        STAGING,
        SANDBOX,
        PRODUCTION
    }

    public int describeContents() {
        return 0;
    }

    PayPalUAT(String str) throws InvalidArgumentException {
        super(str);
        this.mToken = str;
        try {
            JSONObject jSONObject = new JSONObject(decodeUATString(str));
            JSONArray jSONArray = jSONObject.getJSONArray(EXTERNAL_ID_STRING);
            int i = 0;
            while (true) {
                if (i >= jSONArray.length()) {
                    break;
                } else if (jSONArray.getString(i).startsWith("Braintree:")) {
                    this.mBraintreeMerchantID = jSONArray.getString(i).split(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR)[1];
                    break;
                } else {
                    i++;
                }
            }
            if (TextUtils.isEmpty(this.mBraintreeMerchantID)) {
                throw new IllegalArgumentException("Missing Braintree merchant account ID.");
            } else if (jSONObject.has("iss")) {
                this.mPayPalUrl = jSONObject.getString("iss");
                this.mEnvironment = determineIssuerEnv();
                this.mConfigUrl = generateConfigUrl();
            } else {
                throw new IllegalArgumentException("Does not contain issuer, or \"iss\" key.");
            }
        } catch (IllegalArgumentException | NullPointerException | JSONException e) {
            throw new InvalidArgumentException("PayPal UAT invalid: " + e.getMessage());
        }
    }

    private String decodeUATString(String str) {
        return new String(Base64.decode(str.split("[.]")[1], 0));
    }

    private String generateConfigUrl() {
        String str = (this.mEnvironment == Environment.STAGING || this.mEnvironment == Environment.SANDBOX) ? "https://api.sandbox.braintreegateway.com:443/merchants/" : "https://api.braintreegateway.com:443/merchants/";
        return str + this.mBraintreeMerchantID + "/client_api/v1/configuration";
    }

    private Environment determineIssuerEnv() throws IllegalArgumentException {
        String str = this.mPayPalUrl;
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1750115095:
                if (str.equals("https://api.paypal.com")) {
                    c = 0;
                    break;
                }
                break;
            case 823203617:
                if (str.equals("https://api.msmaster.qa.paypal.com")) {
                    c = 1;
                    break;
                }
                break;
            case 1731655536:
                if (str.equals("https://api.sandbox.paypal.com")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Environment.PRODUCTION;
            case 1:
                return Environment.STAGING;
            case 2:
                return Environment.SANDBOX;
            default:
                throw new IllegalArgumentException("PayPal issuer URL missing or unknown: " + this.mPayPalUrl);
        }
    }

    public String getConfigUrl() {
        return this.mConfigUrl;
    }

    public String getBearer() {
        return this.mToken;
    }

    public String getPayPalURL() {
        return this.mPayPalUrl;
    }

    public Environment getEnvironment() {
        return this.mEnvironment;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mConfigUrl);
        parcel.writeString(this.mPayPalUrl);
        parcel.writeString(this.mToken);
        parcel.writeString(this.mBraintreeMerchantID);
    }

    protected PayPalUAT(Parcel parcel) {
        super(parcel);
        this.mConfigUrl = parcel.readString();
        this.mPayPalUrl = parcel.readString();
        this.mToken = parcel.readString();
        this.mBraintreeMerchantID = parcel.readString();
    }
}
