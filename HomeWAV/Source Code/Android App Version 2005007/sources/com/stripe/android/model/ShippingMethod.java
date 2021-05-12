package com.stripe.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.stripe.android.utils.ObjectUtils;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ShippingMethod extends StripeJsonModel implements Parcelable {
    public static final Parcelable.Creator<ShippingMethod> CREATOR = new Parcelable.Creator<ShippingMethod>() {
        public ShippingMethod createFromParcel(Parcel parcel) {
            return new ShippingMethod(parcel);
        }

        public ShippingMethod[] newArray(int i) {
            return new ShippingMethod[i];
        }
    };
    private static final String FIELD_AMOUNT = "amount";
    private static final String FIELD_CURRENCY_CODE = "currency_code";
    private static final String FIELD_DETAIL = "detail";
    private static final String FIELD_IDENTIFIER = "identifier";
    private static final String FIELD_LABEL = "label";
    private final long mAmount;
    private final String mCurrencyCode;
    private final String mDetail;
    private final String mIdentifier;
    private final String mLabel;

    public int describeContents() {
        return 0;
    }

    public ShippingMethod(String str, String str2, long j, String str3) {
        this(str, str2, (String) null, j, str3);
    }

    public ShippingMethod(String str, String str2, String str3, long j, String str4) {
        this.mLabel = str;
        this.mIdentifier = str2;
        this.mDetail = str3;
        this.mAmount = j;
        this.mCurrencyCode = str4;
    }

    private ShippingMethod(Parcel parcel) {
        this.mAmount = parcel.readLong();
        this.mCurrencyCode = parcel.readString();
        this.mDetail = parcel.readString();
        this.mIdentifier = parcel.readString();
        this.mLabel = parcel.readString();
    }

    public Currency getCurrency() {
        return Currency.getInstance(this.mCurrencyCode);
    }

    public long getAmount() {
        return this.mAmount;
    }

    public String getLabel() {
        return this.mLabel;
    }

    public String getDetail() {
        return this.mDetail;
    }

    public String getIdentifier() {
        return this.mIdentifier;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putLongIfNotNull(jSONObject, FIELD_AMOUNT, Long.valueOf(this.mAmount));
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CURRENCY_CODE, this.mCurrencyCode);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_DETAIL, this.mDetail);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_IDENTIFIER, this.mIdentifier);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "label", this.mLabel);
        return jSONObject;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(FIELD_AMOUNT, Long.valueOf(this.mAmount));
        hashMap.put(FIELD_CURRENCY_CODE, this.mCurrencyCode);
        hashMap.put(FIELD_DETAIL, this.mDetail);
        hashMap.put(FIELD_IDENTIFIER, this.mIdentifier);
        hashMap.put("label", this.mLabel);
        return hashMap;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mAmount);
        parcel.writeString(this.mCurrencyCode);
        parcel.writeString(this.mDetail);
        parcel.writeString(this.mIdentifier);
        parcel.writeString(this.mLabel);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ShippingMethod) && typedEquals((ShippingMethod) obj));
    }

    private boolean typedEquals(ShippingMethod shippingMethod) {
        return this.mAmount == shippingMethod.mAmount && ObjectUtils.equals(this.mCurrencyCode, shippingMethod.mCurrencyCode) && ObjectUtils.equals(this.mDetail, shippingMethod.mDetail) && ObjectUtils.equals(this.mIdentifier, shippingMethod.mIdentifier) && ObjectUtils.equals(this.mLabel, shippingMethod.mLabel);
    }

    public int hashCode() {
        return ObjectUtils.hash(Long.valueOf(this.mAmount), this.mCurrencyCode, this.mDetail, this.mIdentifier, this.mLabel);
    }
}
