package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class PayPalAccountNonce extends PaymentMethodNonce implements Parcelable {
    private static final String ACCOUNT_ADDRESS_KEY = "accountAddress";
    protected static final String API_RESOURCE_KEY = "paypalAccounts";
    private static final String BILLING_ADDRESS_KEY = "billingAddress";
    private static final String CLIENT_METADATA_ID_KEY = "correlationId";
    public static final Parcelable.Creator<PayPalAccountNonce> CREATOR = new Parcelable.Creator<PayPalAccountNonce>() {
        public PayPalAccountNonce createFromParcel(Parcel parcel) {
            return new PayPalAccountNonce(parcel);
        }

        public PayPalAccountNonce[] newArray(int i) {
            return new PayPalAccountNonce[i];
        }
    };
    private static final String CREDIT_FINANCING_KEY = "creditFinancingOffered";
    private static final String DETAILS_KEY = "details";
    private static final String EMAIL_KEY = "email";
    private static final String FIRST_NAME_KEY = "firstName";
    private static final String LAST_NAME_KEY = "lastName";
    private static final String PAYER_ID_KEY = "payerId";
    private static final String PAYER_INFO_KEY = "payerInfo";
    protected static final String PAYMENT_METHOD_DATA_KEY = "paymentMethodData";
    private static final String PHONE_KEY = "phone";
    private static final String SHIPPING_ADDRESS_KEY = "shippingAddress";
    protected static final String TOKENIZATION_DATA_KEY = "tokenizationData";
    protected static final String TOKEN_KEY = "token";
    protected static final String TYPE = "PayPalAccount";
    private String mAuthenticateUrl;
    private PostalAddress mBillingAddress;
    private String mClientMetadataId;
    private PayPalCreditFinancing mCreditFinancing;
    private String mEmail;
    private String mFirstName;
    private String mLastName;
    private String mPayerId;
    private String mPhone;
    private PostalAddress mShippingAddress;

    public String getTypeLabel() {
        return "PayPal";
    }

    public static PayPalAccountNonce fromJson(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        PayPalAccountNonce payPalAccountNonce = new PayPalAccountNonce();
        if (jSONObject.has(API_RESOURCE_KEY)) {
            payPalAccountNonce.fromJson(getJsonObjectForType(API_RESOURCE_KEY, jSONObject));
        } else if (jSONObject.has(PAYMENT_METHOD_DATA_KEY)) {
            payPalAccountNonce.fromJson(getJsonObjectForType(API_RESOURCE_KEY, new JSONObject(new JSONObject(str).getJSONObject(PAYMENT_METHOD_DATA_KEY).getJSONObject(TOKENIZATION_DATA_KEY).getString(TOKEN_KEY))));
            JSONObject optJSONObject = jSONObject.optJSONObject(SHIPPING_ADDRESS_KEY);
            if (optJSONObject != null) {
                payPalAccountNonce.mShippingAddress = PostalAddressParser.fromJson(optJSONObject);
            }
        } else {
            throw new JSONException("Could not parse JSON for a payment method nonce");
        }
        return payPalAccountNonce;
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject jSONObject) throws JSONException {
        super.fromJson(jSONObject);
        this.mAuthenticateUrl = Json.optString(jSONObject, "authenticateUrl", (String) null);
        JSONObject jSONObject2 = jSONObject.getJSONObject("details");
        this.mEmail = Json.optString(jSONObject2, "email", (String) null);
        this.mClientMetadataId = Json.optString(jSONObject2, CLIENT_METADATA_ID_KEY, (String) null);
        try {
            if (jSONObject2.has(CREDIT_FINANCING_KEY)) {
                this.mCreditFinancing = PayPalCreditFinancing.fromJson(jSONObject2.getJSONObject(CREDIT_FINANCING_KEY));
            }
            JSONObject jSONObject3 = jSONObject2.getJSONObject(PAYER_INFO_KEY);
            JSONObject optJSONObject = jSONObject3.optJSONObject(BILLING_ADDRESS_KEY);
            if (jSONObject3.has(ACCOUNT_ADDRESS_KEY)) {
                optJSONObject = jSONObject3.optJSONObject(ACCOUNT_ADDRESS_KEY);
            }
            this.mShippingAddress = PostalAddressParser.fromJson(jSONObject3.optJSONObject(SHIPPING_ADDRESS_KEY));
            this.mBillingAddress = PostalAddressParser.fromJson(optJSONObject);
            this.mFirstName = Json.optString(jSONObject3, FIRST_NAME_KEY, "");
            this.mLastName = Json.optString(jSONObject3, LAST_NAME_KEY, "");
            this.mPhone = Json.optString(jSONObject3, "phone", "");
            this.mPayerId = Json.optString(jSONObject3, PAYER_ID_KEY, "");
            if (this.mEmail == null) {
                this.mEmail = Json.optString(jSONObject3, "email", (String) null);
            }
        } catch (JSONException unused) {
            this.mBillingAddress = new PostalAddress();
            this.mShippingAddress = new PostalAddress();
        }
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getDescription() {
        if (!TextUtils.equals(super.getDescription(), "PayPal") || TextUtils.isEmpty(getEmail())) {
            return super.getDescription();
        }
        return getEmail();
    }

    public PostalAddress getBillingAddress() {
        return this.mBillingAddress;
    }

    public PostalAddress getShippingAddress() {
        return this.mShippingAddress;
    }

    public String getFirstName() {
        return this.mFirstName;
    }

    public String getLastName() {
        return this.mLastName;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public String getClientMetadataId() {
        return this.mClientMetadataId;
    }

    public String getPayerId() {
        return this.mPayerId;
    }

    public boolean isTwoFactorAuthRequired() {
        return this.mAuthenticateUrl != null;
    }

    public PayPalCreditFinancing getCreditFinancing() {
        return this.mCreditFinancing;
    }

    public String getAuthenticateUrl() {
        return this.mAuthenticateUrl;
    }

    public PayPalAccountNonce() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mClientMetadataId);
        parcel.writeParcelable(this.mBillingAddress, i);
        parcel.writeParcelable(this.mShippingAddress, i);
        parcel.writeString(this.mFirstName);
        parcel.writeString(this.mLastName);
        parcel.writeString(this.mEmail);
        parcel.writeString(this.mPhone);
        parcel.writeString(this.mPayerId);
        parcel.writeParcelable(this.mCreditFinancing, i);
        parcel.writeString(this.mAuthenticateUrl);
    }

    private PayPalAccountNonce(Parcel parcel) {
        super(parcel);
        this.mClientMetadataId = parcel.readString();
        this.mBillingAddress = (PostalAddress) parcel.readParcelable(PostalAddress.class.getClassLoader());
        this.mShippingAddress = (PostalAddress) parcel.readParcelable(PostalAddress.class.getClassLoader());
        this.mFirstName = parcel.readString();
        this.mLastName = parcel.readString();
        this.mEmail = parcel.readString();
        this.mPhone = parcel.readString();
        this.mPayerId = parcel.readString();
        this.mCreditFinancing = (PayPalCreditFinancing) parcel.readParcelable(PayPalCreditFinancing.class.getClassLoader());
        this.mAuthenticateUrl = parcel.readString();
    }
}
