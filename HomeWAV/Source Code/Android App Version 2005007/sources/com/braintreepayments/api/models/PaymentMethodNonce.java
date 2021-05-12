package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PaymentMethodNonce implements Parcelable {
    protected static final String DATA_KEY = "data";
    private static final String DESCRIPTION_KEY = "description";
    private static final String PAYMENT_METHOD_DEFAULT_KEY = "default";
    private static final String PAYMENT_METHOD_NONCE_COLLECTION_KEY = "paymentMethods";
    private static final String PAYMENT_METHOD_NONCE_KEY = "nonce";
    private static final String PAYMENT_METHOD_TYPE_KEY = "type";
    protected static final String TOKEN_KEY = "token";
    protected boolean mDefault;
    protected String mDescription;
    protected String mNonce;

    public int describeContents() {
        return 0;
    }

    public abstract String getTypeLabel();

    protected static JSONObject getJsonObjectForType(String str, JSONObject jSONObject) throws JSONException {
        return jSONObject.getJSONArray(str).getJSONObject(0);
    }

    /* access modifiers changed from: protected */
    public void fromJson(JSONObject jSONObject) throws JSONException {
        this.mNonce = jSONObject.getString(PAYMENT_METHOD_NONCE_KEY);
        this.mDescription = jSONObject.getString(DESCRIPTION_KEY);
        this.mDefault = jSONObject.optBoolean("default", false);
    }

    public String getNonce() {
        return this.mNonce;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean isDefault() {
        return this.mDefault;
    }

    public static List<PaymentMethodNonce> parsePaymentMethodNonces(String str) throws JSONException {
        JSONArray jSONArray = new JSONObject(str).getJSONArray(PAYMENT_METHOD_NONCE_COLLECTION_KEY);
        if (jSONArray == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            PaymentMethodNonce parsePaymentMethodNonces = parsePaymentMethodNonces(jSONObject, jSONObject.getString("type"));
            if (parsePaymentMethodNonces != null) {
                arrayList.add(parsePaymentMethodNonces);
            }
        }
        return arrayList;
    }

    public static PaymentMethodNonce parsePaymentMethodNonces(String str, String str2) throws JSONException {
        return parsePaymentMethodNonces(new JSONObject(str), str2);
    }

    public static PaymentMethodNonce parsePaymentMethodNonces(JSONObject jSONObject, String str) throws JSONException {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -1807185524:
                if (str.equals("VenmoAccount")) {
                    c = 0;
                    break;
                }
                break;
            case -650599305:
                if (str.equals("VisaCheckoutCard")) {
                    c = 1;
                    break;
                }
                break;
            case 1212590010:
                if (str.equals("PayPalAccount")) {
                    c = 2;
                    break;
                }
                break;
            case 1428640201:
                if (str.equals("CreditCard")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (jSONObject.has("venmoAccounts")) {
                    return VenmoAccountNonce.fromJson(jSONObject.toString());
                }
                VenmoAccountNonce venmoAccountNonce = new VenmoAccountNonce();
                venmoAccountNonce.fromJson(jSONObject);
                return venmoAccountNonce;
            case 1:
                if (jSONObject.has("visaCheckoutCards")) {
                    return VisaCheckoutNonce.fromJson(jSONObject.toString());
                }
                VisaCheckoutNonce visaCheckoutNonce = new VisaCheckoutNonce();
                visaCheckoutNonce.fromJson(jSONObject);
                return visaCheckoutNonce;
            case 2:
                if (jSONObject.has("paypalAccounts")) {
                    return PayPalAccountNonce.fromJson(jSONObject.toString());
                }
                PayPalAccountNonce payPalAccountNonce = new PayPalAccountNonce();
                payPalAccountNonce.fromJson(jSONObject);
                return payPalAccountNonce;
            case 3:
                if (jSONObject.has("creditCards") || jSONObject.has("data")) {
                    return CardNonce.fromJson(jSONObject.toString());
                }
                CardNonce cardNonce = new CardNonce();
                cardNonce.fromJson(jSONObject);
                return cardNonce;
            default:
                return null;
        }
    }

    public PaymentMethodNonce() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mNonce);
        parcel.writeString(this.mDescription);
        parcel.writeByte(this.mDefault ? (byte) 1 : 0);
    }

    protected PaymentMethodNonce(Parcel parcel) {
        this.mNonce = parcel.readString();
        this.mDescription = parcel.readString();
        this.mDefault = parcel.readByte() > 0;
    }
}
