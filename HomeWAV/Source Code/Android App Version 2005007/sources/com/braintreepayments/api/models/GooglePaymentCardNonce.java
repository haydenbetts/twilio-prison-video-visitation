package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePaymentCardNonce extends PaymentMethodNonce implements Parcelable {
    protected static final String API_RESOURCE_KEY = "androidPayCards";
    private static final String CARD_DETAILS_KEY = "details";
    private static final String CARD_TYPE_KEY = "cardType";
    public static final Parcelable.Creator<GooglePaymentCardNonce> CREATOR = new Parcelable.Creator<GooglePaymentCardNonce>() {
        public GooglePaymentCardNonce createFromParcel(Parcel parcel) {
            return new GooglePaymentCardNonce(parcel);
        }

        public GooglePaymentCardNonce[] newArray(int i) {
            return new GooglePaymentCardNonce[i];
        }
    };
    private static final String IS_NETWORK_TOKENIZED_KEY = "isNetworkTokenized";
    private static final String LAST_FOUR_KEY = "lastFour";
    private static final String LAST_TWO_KEY = "lastTwo";
    private PostalAddress mBillingAddress;
    private BinData mBinData;
    private String mCardType;
    private String mEmail;
    private Boolean mIsNetworkTokenized;
    private String mLastFour;
    private String mLastTwo;
    private PostalAddress mShippingAddress;

    public String getTypeLabel() {
        return "Google Pay";
    }

    public static GooglePaymentCardNonce fromJson(String str) throws JSONException {
        GooglePaymentCardNonce googlePaymentCardNonce = new GooglePaymentCardNonce();
        googlePaymentCardNonce.fromJson(new JSONObject(str));
        return googlePaymentCardNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        JSONObject jSONObject4 = new JSONObject(PaymentMethodNonceFactory.extractPaymentMethodToken(jSONObject.toString()).getJSONArray(API_RESOURCE_KEY).get(0).toString());
        super.fromJson(jSONObject4);
        JSONObject jSONObject5 = jSONObject4.getJSONObject("details");
        JSONObject jSONObject6 = jSONObject.getJSONObject("paymentMethodData").getJSONObject("info");
        if (jSONObject6.has("billingAddress")) {
            jSONObject2 = jSONObject6.getJSONObject("billingAddress");
        }
        if (jSONObject.has("shippingAddress")) {
            jSONObject3 = jSONObject.getJSONObject("shippingAddress");
        }
        this.mDescription = jSONObject.getJSONObject("paymentMethodData").get("description").toString();
        this.mEmail = Json.optString(jSONObject, "email", "");
        this.mBillingAddress = postalAddressFromJson(jSONObject2);
        this.mShippingAddress = postalAddressFromJson(jSONObject3);
        this.mBinData = BinData.fromJson(jSONObject.optJSONObject(BinData.BIN_DATA_KEY));
        this.mLastTwo = jSONObject5.getString(LAST_TWO_KEY);
        this.mLastFour = jSONObject5.getString(LAST_FOUR_KEY);
        this.mCardType = jSONObject5.getString(CARD_TYPE_KEY);
        Boolean bool = Boolean.FALSE;
        this.mIsNetworkTokenized = Boolean.valueOf(jSONObject5.optBoolean(IS_NETWORK_TOKENIZED_KEY, false));
    }

    public static PostalAddress postalAddressFromJson(JSONObject jSONObject) {
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.recipientName(Json.optString(jSONObject, "name", "")).phoneNumber(Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_PHONE_NUMBER_KEY, "")).streetAddress(Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_ADDRESS_1_KEY, "")).extendedAddress(formatExtendedAddress(jSONObject)).locality(Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_LOCALITY_KEY, "")).region(Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_ADMINISTRATIVE_AREA_KEY, "")).countryCodeAlpha2(Json.optString(jSONObject, "countryCode", "")).postalCode(Json.optString(jSONObject, "postalCode", "")).sortingCode(Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_SORTING_CODE_KEY, ""));
        return postalAddress;
    }

    private static String formatExtendedAddress(JSONObject jSONObject) {
        return ("" + Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_ADDRESS_2_KEY, "") + "\n" + Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_ADDRESS_3_KEY, "") + "\n" + Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_ADDRESS_4_KEY, "") + "\n" + Json.optString(jSONObject, PostalAddressParser.USER_ADDRESS_ADDRESS_5_KEY, "")).trim();
    }

    public String getCardType() {
        return this.mCardType;
    }

    public String getLastTwo() {
        return this.mLastTwo;
    }

    public String getLastFour() {
        return this.mLastFour;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public Boolean isNetworkTokenized() {
        return this.mIsNetworkTokenized;
    }

    public PostalAddress getBillingAddress() {
        return this.mBillingAddress;
    }

    public PostalAddress getShippingAddress() {
        return this.mShippingAddress;
    }

    public BinData getBinData() {
        return this.mBinData;
    }

    public GooglePaymentCardNonce() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mCardType);
        parcel.writeString(this.mLastTwo);
        parcel.writeString(this.mLastFour);
        parcel.writeString(this.mEmail);
        parcel.writeParcelable(this.mBillingAddress, i);
        parcel.writeParcelable(this.mShippingAddress, i);
        parcel.writeParcelable(this.mBinData, i);
    }

    private GooglePaymentCardNonce(Parcel parcel) {
        super(parcel);
        this.mCardType = parcel.readString();
        this.mLastTwo = parcel.readString();
        this.mLastFour = parcel.readString();
        this.mEmail = parcel.readString();
        this.mBillingAddress = (PostalAddress) parcel.readParcelable(PostalAddress.class.getClassLoader());
        this.mShippingAddress = (PostalAddress) parcel.readParcelable(PostalAddress.class.getClassLoader());
        this.mBinData = (BinData) parcel.readParcelable(BinData.class.getClassLoader());
    }
}
