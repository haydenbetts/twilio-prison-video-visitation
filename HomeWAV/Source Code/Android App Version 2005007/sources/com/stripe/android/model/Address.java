package com.stripe.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.stripe.android.utils.ObjectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Address extends StripeJsonModel implements Parcelable {
    public static final Parcelable.Creator<Address> CREATOR = new Parcelable.Creator<Address>() {
        public Address createFromParcel(Parcel parcel) {
            return new Address(parcel);
        }

        public Address[] newArray(int i) {
            return new Address[i];
        }
    };
    private static final String FIELD_CITY = "city";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_LINE_1 = "line1";
    private static final String FIELD_LINE_2 = "line2";
    private static final String FIELD_POSTAL_CODE = "postal_code";
    private static final String FIELD_STATE = "state";
    private String mCity;
    private String mCountry;
    private String mLine1;
    private String mLine2;
    private String mPostalCode;
    private String mState;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequiredBillingAddressFields {
        public static final int FULL = 2;
        public static final int NAME = 3;
        public static final int NONE = 0;
        public static final int ZIP = 1;
    }

    public int describeContents() {
        return 0;
    }

    private Address(String str, String str2, String str3, String str4, String str5, String str6) {
        this.mCity = str;
        this.mCountry = str2;
        this.mLine1 = str3;
        this.mLine2 = str4;
        this.mPostalCode = str5;
        this.mState = str6;
    }

    private Address(Builder builder) {
        this(builder.mCity, builder.mCountry, builder.mLine1, builder.mLine2, builder.mPostalCode, builder.mState);
    }

    protected Address(Parcel parcel) {
        this.mCity = parcel.readString();
        this.mCountry = parcel.readString();
        this.mLine1 = parcel.readString();
        this.mLine2 = parcel.readString();
        this.mPostalCode = parcel.readString();
        this.mState = parcel.readString();
    }

    public String getCity() {
        return this.mCity;
    }

    @Deprecated
    public void setCity(String str) {
        this.mCity = str;
    }

    public String getCountry() {
        return this.mCountry;
    }

    @Deprecated
    public void setCountry(String str) {
        this.mCountry = str;
    }

    public String getLine1() {
        return this.mLine1;
    }

    @Deprecated
    public void setLine1(String str) {
        this.mLine1 = str;
    }

    public String getLine2() {
        return this.mLine2;
    }

    @Deprecated
    public void setLine2(String str) {
        this.mLine2 = str;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    @Deprecated
    public void setPostalCode(String str) {
        this.mPostalCode = str;
    }

    public String getState() {
        return this.mState;
    }

    @Deprecated
    public void setState(String str) {
        this.mState = str;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("city", this.mCity);
        hashMap.put("country", this.mCountry);
        hashMap.put("line1", this.mLine1);
        hashMap.put("line2", this.mLine2);
        hashMap.put("postal_code", this.mPostalCode);
        hashMap.put("state", this.mState);
        return hashMap;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject, "city", this.mCity);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "country", this.mCountry);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "line1", this.mLine1);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "line2", this.mLine2);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "postal_code", this.mPostalCode);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "state", this.mState);
        return jSONObject;
    }

    public static Address fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Address fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new Address(StripeJsonUtils.optString(jSONObject, "city"), StripeJsonUtils.optString(jSONObject, "country"), StripeJsonUtils.optString(jSONObject, "line1"), StripeJsonUtils.optString(jSONObject, "line2"), StripeJsonUtils.optString(jSONObject, "postal_code"), StripeJsonUtils.optString(jSONObject, "state"));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Address) && typedEquals((Address) obj));
    }

    private boolean typedEquals(Address address) {
        return ObjectUtils.equals(this.mCity, address.mCity) && ObjectUtils.equals(this.mCountry, address.mCountry) && ObjectUtils.equals(this.mLine1, address.mLine1) && ObjectUtils.equals(this.mLine2, address.mLine2) && ObjectUtils.equals(this.mPostalCode, address.mPostalCode) && ObjectUtils.equals(this.mState, address.mState);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mCity, this.mCountry, this.mLine1, this.mLine2, this.mPostalCode, this.mState);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCity);
        parcel.writeString(this.mCountry);
        parcel.writeString(this.mLine1);
        parcel.writeString(this.mLine2);
        parcel.writeString(this.mPostalCode);
        parcel.writeString(this.mState);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String mCity;
        /* access modifiers changed from: private */
        public String mCountry;
        /* access modifiers changed from: private */
        public String mLine1;
        /* access modifiers changed from: private */
        public String mLine2;
        /* access modifiers changed from: private */
        public String mPostalCode;
        /* access modifiers changed from: private */
        public String mState;

        public Builder setCity(String str) {
            this.mCity = str;
            return this;
        }

        public Builder setCountry(String str) {
            this.mCountry = str.toUpperCase(Locale.ROOT);
            return this;
        }

        public Builder setLine1(String str) {
            this.mLine1 = str;
            return this;
        }

        public Builder setLine2(String str) {
            this.mLine2 = str;
            return this;
        }

        public Builder setPostalCode(String str) {
            this.mPostalCode = str;
            return this;
        }

        public Builder setState(String str) {
            this.mState = str;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
