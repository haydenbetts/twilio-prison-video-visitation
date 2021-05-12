package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecurePostalAddress implements Parcelable {
    protected static final String BILLING_ADDRESS_KEY = "billingAddress";
    protected static final String COUNTRY_CODE_ALPHA_2_KEY = "countryCode";
    public static final Parcelable.Creator<ThreeDSecurePostalAddress> CREATOR = new Parcelable.Creator<ThreeDSecurePostalAddress>() {
        public ThreeDSecurePostalAddress createFromParcel(Parcel parcel) {
            return new ThreeDSecurePostalAddress(parcel);
        }

        public ThreeDSecurePostalAddress[] newArray(int i) {
            return new ThreeDSecurePostalAddress[i];
        }
    };
    protected static final String EXTENDED_ADDRESS_KEY = "line2";
    protected static final String FIRST_NAME_KEY = "firstName";
    protected static final String LAST_NAME_KEY = "lastName";
    protected static final String LINE_3_KEY = "line3";
    protected static final String LOCALITY_KEY = "city";
    protected static final String PHONE_NUMBER_KEY = "phoneNumber";
    protected static final String POSTAL_CODE_KEY = "postalCode";
    protected static final String REGION_KEY = "state";
    protected static final String STREET_ADDRESS_KEY = "line1";
    private String mCountryCodeAlpha2;
    private String mExtendedAddress;
    private String mGivenName;
    private String mLine3;
    private String mLocality;
    private String mPhoneNumber;
    private String mPostalCode;
    private String mRegion;
    private String mStreetAddress;
    private String mSurname;

    public int describeContents() {
        return 0;
    }

    public ThreeDSecurePostalAddress() {
    }

    @Deprecated
    public ThreeDSecurePostalAddress firstName(String str) {
        givenName(str);
        return this;
    }

    public ThreeDSecurePostalAddress givenName(String str) {
        this.mGivenName = str;
        return this;
    }

    @Deprecated
    public ThreeDSecurePostalAddress lastName(String str) {
        surname(str);
        return this;
    }

    public ThreeDSecurePostalAddress surname(String str) {
        this.mSurname = str;
        return this;
    }

    public ThreeDSecurePostalAddress streetAddress(String str) {
        this.mStreetAddress = str;
        return this;
    }

    public ThreeDSecurePostalAddress extendedAddress(String str) {
        this.mExtendedAddress = str;
        return this;
    }

    public ThreeDSecurePostalAddress line3(String str) {
        this.mLine3 = str;
        return this;
    }

    public ThreeDSecurePostalAddress locality(String str) {
        this.mLocality = str;
        return this;
    }

    public ThreeDSecurePostalAddress region(String str) {
        this.mRegion = str;
        return this;
    }

    public ThreeDSecurePostalAddress postalCode(String str) {
        this.mPostalCode = str;
        return this;
    }

    public ThreeDSecurePostalAddress countryCodeAlpha2(String str) {
        this.mCountryCodeAlpha2 = str;
        return this;
    }

    public ThreeDSecurePostalAddress phoneNumber(String str) {
        this.mPhoneNumber = str;
        return this;
    }

    @Deprecated
    public String getFirstName() {
        return this.mGivenName;
    }

    public String getGivenName() {
        return this.mGivenName;
    }

    @Deprecated
    public String getLastName() {
        return this.mSurname;
    }

    public String getSurname() {
        return this.mSurname;
    }

    public String getStreetAddress() {
        return this.mStreetAddress;
    }

    public String getExtendedAddress() {
        return this.mExtendedAddress;
    }

    public String getLine3() {
        return this.mLine3;
    }

    public String getLocality() {
        return this.mLocality;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public String getPostalCode() {
        return this.mPostalCode;
    }

    public String getCountryCodeAlpha2() {
        return this.mCountryCodeAlpha2;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public ThreeDSecurePostalAddress(Parcel parcel) {
        this.mGivenName = parcel.readString();
        this.mSurname = parcel.readString();
        this.mStreetAddress = parcel.readString();
        this.mExtendedAddress = parcel.readString();
        this.mLine3 = parcel.readString();
        this.mLocality = parcel.readString();
        this.mRegion = parcel.readString();
        this.mPostalCode = parcel.readString();
        this.mCountryCodeAlpha2 = parcel.readString();
        this.mPhoneNumber = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mGivenName);
        parcel.writeString(this.mSurname);
        parcel.writeString(this.mStreetAddress);
        parcel.writeString(this.mExtendedAddress);
        parcel.writeString(this.mLine3);
        parcel.writeString(this.mLocality);
        parcel.writeString(this.mRegion);
        parcel.writeString(this.mPostalCode);
        parcel.writeString(this.mCountryCodeAlpha2);
        parcel.writeString(this.mPhoneNumber);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.putOpt(FIRST_NAME_KEY, this.mGivenName);
            jSONObject.putOpt(LAST_NAME_KEY, this.mSurname);
            jSONObject.putOpt("phoneNumber", this.mPhoneNumber);
            jSONObject2.putOpt("line1", this.mStreetAddress);
            jSONObject2.putOpt("line2", this.mExtendedAddress);
            jSONObject2.putOpt(LINE_3_KEY, this.mLine3);
            jSONObject2.putOpt("city", this.mLocality);
            jSONObject2.putOpt("state", this.mRegion);
            jSONObject2.putOpt("postalCode", this.mPostalCode);
            jSONObject2.putOpt("countryCode", this.mCountryCodeAlpha2);
            if (jSONObject2.length() != 0) {
                jSONObject.putOpt(BILLING_ADDRESS_KEY, jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
