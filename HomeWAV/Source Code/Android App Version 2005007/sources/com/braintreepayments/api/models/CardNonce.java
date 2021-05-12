package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class CardNonce extends PaymentMethodNonce implements Parcelable {
    protected static final String API_RESOURCE_KEY = "creditCards";
    private static final String AUTHENTICATION_INSIGHT_KEY = "authenticationInsight";
    private static final String BIN_KEY = "bin";
    private static final String CARDHOLDER_NAME_KEY = "cardholderName";
    private static final String CARD_DETAILS_KEY = "details";
    private static final String CARD_TYPE_KEY = "cardType";
    public static final Parcelable.Creator<CardNonce> CREATOR = new Parcelable.Creator<CardNonce>() {
        public CardNonce createFromParcel(Parcel parcel) {
            return new CardNonce(parcel);
        }

        public CardNonce[] newArray(int i) {
            return new CardNonce[i];
        }
    };
    private static final String EXPIRATION_MONTH_KEY = "expirationMonth";
    private static final String EXPIRATION_YEAR_KEY = "expirationYear";
    private static final String GRAPHQL_BRAND_KEY = "brand";
    private static final String GRAPHQL_CREDIT_CARD_KEY = "creditCard";
    private static final String GRAPHQL_LAST_FOUR_KEY = "last4";
    private static final String GRAPHQL_TOKENIZE_CREDIT_CARD_KEY = "tokenizeCreditCard";
    private static final String LAST_FOUR_KEY = "lastFour";
    private static final String LAST_TWO_KEY = "lastTwo";
    private static final String THREE_D_SECURE_INFO_KEY = "threeDSecureInfo";
    protected static final String TYPE = "CreditCard";
    private AuthenticationInsight mAuthenticationInsight;
    private String mBin;
    private BinData mBinData;
    private String mCardType;
    private String mCardholderName;
    private String mExpirationMonth;
    private String mExpirationYear;
    private String mLastFour;
    private String mLastTwo;
    private ThreeDSecureInfo mThreeDSecureInfo;

    public static CardNonce fromJson(String str) throws JSONException {
        CardNonce cardNonce = new CardNonce();
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("data")) {
            cardNonce.fromGraphQLJson(jSONObject);
        } else {
            cardNonce.fromJson(getJsonObjectForType(API_RESOURCE_KEY, jSONObject));
        }
        return cardNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject jSONObject) throws JSONException {
        super.fromJson(jSONObject);
        JSONObject jSONObject2 = jSONObject.getJSONObject("details");
        this.mLastTwo = jSONObject2.getString(LAST_TWO_KEY);
        this.mLastFour = jSONObject2.getString(LAST_FOUR_KEY);
        this.mCardType = jSONObject2.getString(CARD_TYPE_KEY);
        this.mThreeDSecureInfo = ThreeDSecureInfo.fromJson(jSONObject.optJSONObject(THREE_D_SECURE_INFO_KEY));
        this.mBin = Json.optString(jSONObject2, BIN_KEY, "");
        this.mBinData = BinData.fromJson(jSONObject.optJSONObject(BinData.BIN_DATA_KEY));
        this.mAuthenticationInsight = AuthenticationInsight.fromJson(jSONObject.optJSONObject(AUTHENTICATION_INSIGHT_KEY));
        this.mExpirationMonth = Json.optString(jSONObject2, EXPIRATION_MONTH_KEY, "");
        this.mExpirationYear = Json.optString(jSONObject2, EXPIRATION_YEAR_KEY, "");
        this.mCardholderName = Json.optString(jSONObject2, CARDHOLDER_NAME_KEY, "");
    }

    private void fromGraphQLJson(JSONObject jSONObject) throws JSONException {
        String str;
        String str2;
        JSONObject jSONObject2 = jSONObject.getJSONObject("data");
        if (jSONObject2.has(GRAPHQL_TOKENIZE_CREDIT_CARD_KEY)) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject(GRAPHQL_TOKENIZE_CREDIT_CARD_KEY);
            JSONObject jSONObject4 = jSONObject3.getJSONObject(GRAPHQL_CREDIT_CARD_KEY);
            String optString = Json.optString(jSONObject4, GRAPHQL_LAST_FOUR_KEY, "");
            this.mLastFour = optString;
            if (optString.length() < 4) {
                str = "";
            } else {
                str = this.mLastFour.substring(2);
            }
            this.mLastTwo = str;
            this.mCardType = Json.optString(jSONObject4, GRAPHQL_BRAND_KEY, "Unknown");
            this.mThreeDSecureInfo = ThreeDSecureInfo.fromJson((JSONObject) null);
            this.mBin = Json.optString(jSONObject4, BIN_KEY, "");
            this.mBinData = BinData.fromJson(jSONObject4.optJSONObject(BinData.BIN_DATA_KEY));
            this.mNonce = jSONObject3.getString("token");
            if (TextUtils.isEmpty(this.mLastTwo)) {
                str2 = "";
            } else {
                str2 = "ending in ••" + this.mLastTwo;
            }
            this.mDescription = str2;
            this.mDefault = false;
            this.mAuthenticationInsight = AuthenticationInsight.fromJson(jSONObject3.optJSONObject(AUTHENTICATION_INSIGHT_KEY));
            this.mExpirationMonth = Json.optString(jSONObject4, EXPIRATION_MONTH_KEY, "");
            this.mExpirationYear = Json.optString(jSONObject4, EXPIRATION_YEAR_KEY, "");
            this.mCardholderName = Json.optString(jSONObject4, CARDHOLDER_NAME_KEY, "");
            return;
        }
        throw new JSONException("Failed to parse GraphQL response JSON");
    }

    public String getTypeLabel() {
        return this.mCardType;
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

    public String getExpirationMonth() {
        return this.mExpirationMonth;
    }

    public String getExpirationYear() {
        return this.mExpirationYear;
    }

    public String getCardholderName() {
        return this.mCardholderName;
    }

    public ThreeDSecureInfo getThreeDSecureInfo() {
        return this.mThreeDSecureInfo;
    }

    public String getBin() {
        return this.mBin;
    }

    public BinData getBinData() {
        return this.mBinData;
    }

    public AuthenticationInsight getAuthenticationInsight() {
        return this.mAuthenticationInsight;
    }

    public CardNonce() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mCardType);
        parcel.writeString(this.mLastTwo);
        parcel.writeString(this.mLastFour);
        parcel.writeParcelable(this.mBinData, i);
        parcel.writeParcelable(this.mThreeDSecureInfo, i);
        parcel.writeParcelable(this.mAuthenticationInsight, i);
        parcel.writeString(this.mExpirationMonth);
        parcel.writeString(this.mExpirationYear);
        parcel.writeString(this.mCardholderName);
    }

    protected CardNonce(Parcel parcel) {
        super(parcel);
        this.mCardType = parcel.readString();
        this.mLastTwo = parcel.readString();
        this.mLastFour = parcel.readString();
        this.mBinData = (BinData) parcel.readParcelable(BinData.class.getClassLoader());
        this.mThreeDSecureInfo = (ThreeDSecureInfo) parcel.readParcelable(ThreeDSecureInfo.class.getClassLoader());
        this.mAuthenticationInsight = (AuthenticationInsight) parcel.readParcelable(AuthenticationInsight.class.getClassLoader());
        this.mExpirationMonth = parcel.readString();
        this.mExpirationYear = parcel.readString();
        this.mCardholderName = parcel.readString();
    }
}
