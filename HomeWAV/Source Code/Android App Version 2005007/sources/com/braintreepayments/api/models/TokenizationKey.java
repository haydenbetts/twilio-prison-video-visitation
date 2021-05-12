package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.braintreepayments.api.BuildConfig;
import com.braintreepayments.api.exceptions.InvalidArgumentException;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import lib.android.paypal.com.magnessdk.a;

public class TokenizationKey extends Authorization implements Parcelable {
    public static final Parcelable.Creator<TokenizationKey> CREATOR = new Parcelable.Creator<TokenizationKey>() {
        public TokenizationKey createFromParcel(Parcel parcel) {
            return new TokenizationKey(parcel);
        }

        public TokenizationKey[] newArray(int i) {
            return new TokenizationKey[i];
        }
    };
    protected static final String MATCHER = "^[a-zA-Z0-9]+_[a-zA-Z0-9]+_[a-zA-Z0-9_]+$";
    private final String mEnvironment;
    private final String mMerchantId;
    private final String mUrl;

    public int describeContents() {
        return 0;
    }

    TokenizationKey(String str) throws InvalidArgumentException {
        super(str);
        String[] split = str.split("_", 3);
        String str2 = split[0];
        this.mEnvironment = str2;
        String str3 = split[2];
        this.mMerchantId = str3;
        this.mUrl = BraintreeEnvironment.getUrl(str2) + "merchants/" + str3 + "/client_api/";
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String getMerchantId() {
        return this.mMerchantId;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public String getConfigUrl() {
        return this.mUrl + "v1/configuration";
    }

    public String getBearer() {
        return toString();
    }

    private enum BraintreeEnvironment {
        DEVELOPMENT("development", BuildConfig.DEVELOPMENT_URL),
        SANDBOX(EnvironmentManager.SANDBOX, "https://api.sandbox.braintreegateway.com/"),
        PRODUCTION(a.d, "https://api.braintreegateway.com/");
        
        private String mEnvironment;
        private String mUrl;

        private BraintreeEnvironment(String str, String str2) {
            this.mEnvironment = str;
            this.mUrl = str2;
        }

        static String getUrl(String str) throws InvalidArgumentException {
            for (BraintreeEnvironment braintreeEnvironment : values()) {
                if (braintreeEnvironment.mEnvironment.equals(str)) {
                    return braintreeEnvironment.mUrl;
                }
            }
            throw new InvalidArgumentException("Tokenization Key contained invalid environment");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mEnvironment);
        parcel.writeString(this.mMerchantId);
        parcel.writeString(this.mUrl);
    }

    protected TokenizationKey(Parcel parcel) {
        super(parcel);
        this.mEnvironment = parcel.readString();
        this.mMerchantId = parcel.readString();
        this.mUrl = parcel.readString();
    }
}
