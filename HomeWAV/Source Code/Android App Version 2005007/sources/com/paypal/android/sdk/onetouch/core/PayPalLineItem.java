package com.paypal.android.sdk.onetouch.core;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalLineItem implements Parcelable {
    public static final Parcelable.Creator<PayPalLineItem> CREATOR = new Parcelable.Creator<PayPalLineItem>() {
        public PayPalLineItem createFromParcel(Parcel parcel) {
            return new PayPalLineItem(parcel);
        }

        public PayPalLineItem[] newArray(int i) {
            return new PayPalLineItem[i];
        }
    };
    private static final String DESCRIPTION_KEY = "description";
    public static final String KIND_CREDIT = "credit";
    public static final String KIND_DEBIT = "debit";
    private static final String KIND_KEY = "kind";
    private static final String NAME_KEY = "name";
    private static final String PRODUCT_CODE_KEY = "product_code";
    private static final String QUANTITY_KEY = "quantity";
    private static final String UNIT_AMOUNT_KEY = "unit_amount";
    private static final String UNIT_TAX_AMOUNT_KEY = "unit_tax_amount";
    private static final String URL_KEY = "url";
    private String mDescription;
    private String mKind;
    private String mName;
    private String mProductCode;
    private String mQuantity;
    private String mUnitAmount;
    private String mUnitTaxAmount;
    private String mUrl;

    public int describeContents() {
        return 0;
    }

    public PayPalLineItem(String str, String str2, String str3, String str4) {
        this.mKind = str;
        this.mName = str2;
        this.mQuantity = str3;
        this.mUnitAmount = str4;
    }

    public void setDescription(String str) {
        this.mDescription = str;
    }

    public void setKind(String str) {
        this.mKind = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public void setProductCode(String str) {
        this.mProductCode = str;
    }

    public void setQuantity(String str) {
        this.mQuantity = str;
    }

    public void setUnitAmount(String str) {
        this.mUnitAmount = str;
    }

    public void setUnitTaxAmount(String str) {
        this.mUnitTaxAmount = str;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getKind() {
        return this.mKind;
    }

    public String getName() {
        return this.mName;
    }

    public String getProductCode() {
        return this.mProductCode;
    }

    public String getQuantity() {
        return this.mQuantity;
    }

    public String getUnitAmount() {
        return this.mUnitAmount;
    }

    public String getUnitTaxAmount() {
        return this.mUnitTaxAmount;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject().putOpt(DESCRIPTION_KEY, this.mDescription).putOpt(KIND_KEY, this.mKind).putOpt("name", this.mName).putOpt("product_code", this.mProductCode).putOpt(QUANTITY_KEY, this.mQuantity).putOpt(UNIT_AMOUNT_KEY, this.mUnitAmount).putOpt(UNIT_TAX_AMOUNT_KEY, this.mUnitTaxAmount).putOpt("url", this.mUrl);
        } catch (JSONException unused) {
            return new JSONObject();
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mKind);
        parcel.writeString(this.mName);
        parcel.writeString(this.mProductCode);
        parcel.writeString(this.mQuantity);
        parcel.writeString(this.mUnitAmount);
        parcel.writeString(this.mUnitTaxAmount);
        parcel.writeString(this.mUrl);
    }

    private PayPalLineItem(Parcel parcel) {
        this.mDescription = parcel.readString();
        this.mKind = parcel.readString();
        this.mName = parcel.readString();
        this.mProductCode = parcel.readString();
        this.mQuantity = parcel.readString();
        this.mUnitAmount = parcel.readString();
        this.mUnitTaxAmount = parcel.readString();
        this.mUrl = parcel.readString();
    }
}
