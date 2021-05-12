package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;

public class PayPalProductAttributes implements Parcelable {
    public static final Parcelable.Creator<PayPalProductAttributes> CREATOR = new Parcelable.Creator<PayPalProductAttributes>() {
        public PayPalProductAttributes createFromParcel(Parcel parcel) {
            return new PayPalProductAttributes(parcel);
        }

        public PayPalProductAttributes[] newArray(int i) {
            return new PayPalProductAttributes[i];
        }
    };
    private String mChargePattern;
    private String mName;
    private String mProductCode;

    public int describeContents() {
        return 0;
    }

    public PayPalProductAttributes() {
    }

    public PayPalProductAttributes chargePattern(String str) {
        this.mChargePattern = str;
        return this;
    }

    public PayPalProductAttributes name(String str) {
        this.mName = str;
        return this;
    }

    public PayPalProductAttributes productCode(String str) {
        this.mProductCode = str;
        return this;
    }

    public String getName() {
        return this.mName;
    }

    public String getProductCode() {
        return this.mProductCode;
    }

    public String getChargePattern() {
        return this.mChargePattern;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mChargePattern);
        parcel.writeString(this.mName);
        parcel.writeString(this.mProductCode);
    }

    private PayPalProductAttributes(Parcel parcel) {
        this.mChargePattern = parcel.readString();
        this.mName = parcel.readString();
        this.mProductCode = parcel.readString();
    }
}
