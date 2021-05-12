package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.cardinalcommerce.shared.userinterfaces.UiCustomization;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecureRequest implements Parcelable {
    public static final Parcelable.Creator<ThreeDSecureRequest> CREATOR = new Parcelable.Creator<ThreeDSecureRequest>() {
        public ThreeDSecureRequest createFromParcel(Parcel parcel) {
            return new ThreeDSecureRequest(parcel);
        }

        public ThreeDSecureRequest[] newArray(int i) {
            return new ThreeDSecureRequest[i];
        }
    };
    public static final String VERSION_1 = "1";
    public static final String VERSION_2 = "2";
    private ThreeDSecureAdditionalInformation mAdditionalInformation;
    private String mAmount;
    private ThreeDSecurePostalAddress mBillingAddress;
    private boolean mChallengeRequested;
    private String mEmail;
    private boolean mExemptionRequested;
    private String mMobilePhoneNumber;
    private String mNonce;
    private String mShippingMethod;
    private UiCustomization mUiCustomization;
    private ThreeDSecureV1UiCustomization mV1UiCustomization;
    private String mVersionRequested;

    public int describeContents() {
        return 0;
    }

    public ThreeDSecureRequest nonce(String str) {
        this.mNonce = str;
        return this;
    }

    public ThreeDSecureRequest amount(String str) {
        this.mAmount = str;
        return this;
    }

    public ThreeDSecureRequest mobilePhoneNumber(String str) {
        this.mMobilePhoneNumber = str;
        return this;
    }

    public ThreeDSecureRequest email(String str) {
        this.mEmail = str;
        return this;
    }

    public ThreeDSecureRequest shippingMethod(String str) {
        this.mShippingMethod = str;
        return this;
    }

    public ThreeDSecureRequest billingAddress(ThreeDSecurePostalAddress threeDSecurePostalAddress) {
        this.mBillingAddress = threeDSecurePostalAddress;
        return this;
    }

    public ThreeDSecureRequest versionRequested(String str) {
        this.mVersionRequested = str;
        return this;
    }

    public ThreeDSecureRequest additionalInformation(ThreeDSecureAdditionalInformation threeDSecureAdditionalInformation) {
        this.mAdditionalInformation = threeDSecureAdditionalInformation;
        return this;
    }

    public ThreeDSecureRequest challengeRequested(boolean z) {
        this.mChallengeRequested = z;
        return this;
    }

    public ThreeDSecureRequest exemptionRequested(boolean z) {
        this.mExemptionRequested = z;
        return this;
    }

    public ThreeDSecureRequest uiCustomization(UiCustomization uiCustomization) {
        this.mUiCustomization = uiCustomization;
        return this;
    }

    public ThreeDSecureRequest v1UiCustomization(ThreeDSecureV1UiCustomization threeDSecureV1UiCustomization) {
        this.mV1UiCustomization = threeDSecureV1UiCustomization;
        return this;
    }

    public String getNonce() {
        return this.mNonce;
    }

    public String getAmount() {
        return this.mAmount;
    }

    public String getMobilePhoneNumber() {
        return this.mMobilePhoneNumber;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getShippingMethod() {
        return this.mShippingMethod;
    }

    public ThreeDSecurePostalAddress getBillingAddress() {
        return this.mBillingAddress;
    }

    public String getVersionRequested() {
        return this.mVersionRequested;
    }

    public ThreeDSecureAdditionalInformation getAdditionalInformation() {
        return this.mAdditionalInformation;
    }

    public boolean isChallengeRequested() {
        return this.mChallengeRequested;
    }

    public boolean isExemptionRequested() {
        return this.mExemptionRequested;
    }

    public UiCustomization getUiCustomization() {
        return this.mUiCustomization;
    }

    public ThreeDSecureV1UiCustomization getV1UiCustomization() {
        return this.mV1UiCustomization;
    }

    public ThreeDSecureRequest() {
        this.mVersionRequested = "1";
        this.mChallengeRequested = false;
        this.mExemptionRequested = false;
        this.mUiCustomization = new UiCustomization();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mNonce);
        parcel.writeString(this.mAmount);
        parcel.writeString(this.mMobilePhoneNumber);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mShippingMethod);
        parcel.writeParcelable(this.mBillingAddress, i);
        parcel.writeString(this.mVersionRequested);
        parcel.writeParcelable(this.mAdditionalInformation, i);
        parcel.writeByte(this.mChallengeRequested ? (byte) 1 : 0);
        parcel.writeByte(this.mExemptionRequested ? (byte) 1 : 0);
        parcel.writeSerializable(this.mUiCustomization);
        parcel.writeParcelable(this.mV1UiCustomization, i);
    }

    public ThreeDSecureRequest(Parcel parcel) {
        this.mVersionRequested = "1";
        boolean z = false;
        this.mChallengeRequested = false;
        this.mExemptionRequested = false;
        this.mNonce = parcel.readString();
        this.mAmount = parcel.readString();
        this.mMobilePhoneNumber = parcel.readString();
        this.mEmail = parcel.readString();
        this.mShippingMethod = parcel.readString();
        this.mBillingAddress = (ThreeDSecurePostalAddress) parcel.readParcelable(ThreeDSecurePostalAddress.class.getClassLoader());
        this.mVersionRequested = parcel.readString();
        this.mAdditionalInformation = (ThreeDSecureAdditionalInformation) parcel.readParcelable(ThreeDSecureAdditionalInformation.class.getClassLoader());
        this.mChallengeRequested = parcel.readByte() > 0;
        this.mExemptionRequested = parcel.readByte() > 0 ? true : z;
        this.mUiCustomization = parcel.readSerializable();
        this.mV1UiCustomization = (ThreeDSecureV1UiCustomization) parcel.readParcelable(ThreeDSecureV1UiCustomization.class.getClassLoader());
    }

    public String build(String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        ThreeDSecurePostalAddress billingAddress = getBillingAddress();
        if (getAdditionalInformation() == null) {
            jSONObject = new JSONObject();
        } else {
            jSONObject = getAdditionalInformation().toJson();
        }
        try {
            jSONObject2.put("amount", this.mAmount);
            jSONObject2.put("additional_info", jSONObject);
            jSONObject.putOpt("mobile_phone_number", getMobilePhoneNumber());
            jSONObject.putOpt("shipping_method", getShippingMethod());
            jSONObject.putOpt("email", getEmail());
            if (billingAddress != null) {
                jSONObject.putOpt("billing_given_name", billingAddress.getGivenName());
                jSONObject.putOpt("billing_surname", billingAddress.getSurname());
                jSONObject.putOpt("billing_line1", billingAddress.getStreetAddress());
                jSONObject.putOpt("billing_line2", billingAddress.getExtendedAddress());
                jSONObject.putOpt("billing_line3", billingAddress.getLine3());
                jSONObject.putOpt("billing_city", billingAddress.getLocality());
                jSONObject.putOpt("billing_state", billingAddress.getRegion());
                jSONObject.putOpt("billing_postal_code", billingAddress.getPostalCode());
                jSONObject.putOpt("billing_country_code", billingAddress.getCountryCodeAlpha2());
                jSONObject.putOpt("billing_phone_number", billingAddress.getPhoneNumber());
            }
            if ("2".equals(getVersionRequested())) {
                jSONObject2.putOpt("df_reference_id", str);
            }
            jSONObject2.put("challenge_requested", this.mChallengeRequested);
            jSONObject2.put("exemption_requested", this.mExemptionRequested);
        } catch (JSONException unused) {
        }
        return jSONObject2.toString();
    }
}
