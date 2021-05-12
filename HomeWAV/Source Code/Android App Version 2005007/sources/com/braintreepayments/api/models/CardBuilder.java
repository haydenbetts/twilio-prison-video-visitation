package com.braintreepayments.api.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.internal.GraphQLConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class CardBuilder extends BaseCardBuilder<CardBuilder> implements Parcelable {
    private static final String AUTHENTICATION_INSIGHT_INPUT_KEY = "authenticationInsightInput";
    private static final String AUTHENTICATION_INSIGHT_REQUESTED_KEY = "authenticationInsight";
    public static final Parcelable.Creator<CardBuilder> CREATOR = new Parcelable.Creator<CardBuilder>() {
        public CardBuilder createFromParcel(Parcel parcel) {
            return new CardBuilder(parcel);
        }

        public CardBuilder[] newArray(int i) {
            return new CardBuilder[i];
        }
    };
    private static final String MERCHANT_ACCOUNT_ID_KEY = "merchantAccountId";
    private boolean mAuthenticationInsightRequested;
    private String mMerchantAccountId;

    /* access modifiers changed from: protected */
    public void buildGraphQL(Context context, JSONObject jSONObject, JSONObject jSONObject2) throws BraintreeException, JSONException {
        JSONObject jSONObject3 = jSONObject2.getJSONObject(GraphQLConstants.Keys.INPUT);
        if (!TextUtils.isEmpty(this.mMerchantAccountId) || !this.mAuthenticationInsightRequested) {
            if (this.mAuthenticationInsightRequested) {
                jSONObject2.put(AUTHENTICATION_INSIGHT_INPUT_KEY, new JSONObject().put(MERCHANT_ACCOUNT_ID_KEY, this.mMerchantAccountId));
            }
            jSONObject.put("query", getCardTokenizationGraphQLMutation());
            jSONObject.put(GraphQLConstants.Keys.OPERATION_NAME, "TokenizeCreditCard");
            JSONObject put = new JSONObject().put("number", this.mCardnumber).put("expirationMonth", this.mExpirationMonth).put("expirationYear", this.mExpirationYear).put("cvv", this.mCvv).put("cardholderName", this.mCardholderName);
            JSONObject put2 = new JSONObject().put("firstName", this.mFirstName).put("lastName", this.mLastName).put("company", this.mCompany).put("countryCode", this.mCountryCode).put(PostalAddressParser.USER_ADDRESS_LOCALITY_KEY, this.mLocality).put("postalCode", this.mPostalCode).put("region", this.mRegion).put("streetAddress", this.mStreetAddress).put("extendedAddress", this.mExtendedAddress);
            if (put2.length() > 0) {
                put.put("billingAddress", put2);
            }
            jSONObject3.put("creditCard", put);
            return;
        }
        throw new BraintreeException("A merchant account ID is required when authenticationInsightRequested is true.");
    }

    public CardBuilder() {
    }

    public CardBuilder merchantAccountId(String str) {
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.mMerchantAccountId = str;
        return this;
    }

    public CardBuilder authenticationInsightRequested(boolean z) {
        this.mAuthenticationInsightRequested = z;
        return this;
    }

    /* access modifiers changed from: protected */
    public void build(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException {
        super.build(jSONObject, jSONObject2);
        if (this.mAuthenticationInsightRequested) {
            jSONObject.put(MERCHANT_ACCOUNT_ID_KEY, this.mMerchantAccountId);
            jSONObject.put(AUTHENTICATION_INSIGHT_REQUESTED_KEY, this.mAuthenticationInsightRequested);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mMerchantAccountId);
        parcel.writeByte(this.mAuthenticationInsightRequested ? (byte) 1 : 0);
    }

    protected CardBuilder(Parcel parcel) {
        super(parcel);
        this.mMerchantAccountId = parcel.readString();
        this.mAuthenticationInsightRequested = parcel.readByte() > 0;
    }

    private String getCardTokenizationGraphQLMutation() {
        StringBuilder sb = new StringBuilder();
        sb.append("mutation TokenizeCreditCard($input: TokenizeCreditCardInput!");
        if (this.mAuthenticationInsightRequested) {
            sb.append(", $authenticationInsightInput: AuthenticationInsightInput!");
        }
        sb.append(") {  tokenizeCreditCard(input: $input) {    token    creditCard {      bin      brand      expirationMonth      expirationYear      cardholderName      last4      binData {        prepaid        healthcare        debit        durbinRegulated        commercial        payroll        issuingBank        countryOfIssuance        productId      }    }");
        if (this.mAuthenticationInsightRequested) {
            sb.append("    authenticationInsight(input: $authenticationInsightInput) {      customerAuthenticationRegulationEnvironment    }");
        }
        sb.append("  }}");
        return sb.toString();
    }
}
