package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecureAuthenticationResponse implements Parcelable {
    public static final Parcelable.Creator<ThreeDSecureAuthenticationResponse> CREATOR = new Parcelable.Creator<ThreeDSecureAuthenticationResponse>() {
        public ThreeDSecureAuthenticationResponse createFromParcel(Parcel parcel) {
            return new ThreeDSecureAuthenticationResponse(parcel);
        }

        public ThreeDSecureAuthenticationResponse[] newArray(int i) {
            return new ThreeDSecureAuthenticationResponse[i];
        }
    };
    private static final String ERRORS_KEY = "errors";
    private static final String ERROR_KEY = "error";
    private static final String MESSAGE_KEY = "message";
    private static final String PAYMENT_METHOD_KEY = "paymentMethod";
    private static final String SUCCESS_KEY = "success";
    private CardNonce mCardNonce;
    private String mErrors;
    private String mException;
    private boolean mSuccess;

    public int describeContents() {
        return 0;
    }

    public static ThreeDSecureAuthenticationResponse fromJson(String str) {
        ThreeDSecureAuthenticationResponse threeDSecureAuthenticationResponse = new ThreeDSecureAuthenticationResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject(PAYMENT_METHOD_KEY);
            if (optJSONObject != null) {
                CardNonce cardNonce = new CardNonce();
                cardNonce.fromJson(optJSONObject);
                threeDSecureAuthenticationResponse.mCardNonce = cardNonce;
            }
            if (jSONObject.has("success")) {
                if (jSONObject.has("error")) {
                    threeDSecureAuthenticationResponse.mErrors = Json.optString(jSONObject.getJSONObject("error"), "message", (String) null);
                }
                threeDSecureAuthenticationResponse.mSuccess = jSONObject.getBoolean("success");
            } else {
                if (jSONObject.has("errors")) {
                    threeDSecureAuthenticationResponse.mErrors = Json.optString(jSONObject.getJSONArray("errors").getJSONObject(0), "message", (String) null);
                }
                threeDSecureAuthenticationResponse.mSuccess = threeDSecureAuthenticationResponse.mErrors == null;
            }
        } catch (JSONException unused) {
            threeDSecureAuthenticationResponse.mSuccess = false;
        }
        return threeDSecureAuthenticationResponse;
    }

    @Deprecated
    public static CardNonce getNonceWithAuthenticationDetails(String str, CardNonce cardNonce) {
        ThreeDSecureAuthenticationResponse threeDSecureAuthenticationResponse = new ThreeDSecureAuthenticationResponse();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("success")) {
                threeDSecureAuthenticationResponse.mSuccess = jSONObject.getBoolean("success");
            } else if (!jSONObject.has("errors")) {
                threeDSecureAuthenticationResponse.mSuccess = true;
            }
            if (threeDSecureAuthenticationResponse.mSuccess) {
                JSONObject optJSONObject = jSONObject.optJSONObject(PAYMENT_METHOD_KEY);
                if (optJSONObject != null) {
                    CardNonce cardNonce2 = new CardNonce();
                    try {
                        cardNonce2.fromJson(optJSONObject);
                        cardNonce = cardNonce2;
                    } catch (JSONException e) {
                        e = e;
                        cardNonce = cardNonce2;
                        threeDSecureAuthenticationResponse.mSuccess = false;
                        threeDSecureAuthenticationResponse.mException = e.getMessage();
                        cardNonce.getThreeDSecureInfo().setThreeDSecureAuthenticationResponse(threeDSecureAuthenticationResponse);
                        return cardNonce;
                    }
                }
            } else {
                threeDSecureAuthenticationResponse.mErrors = str;
            }
        } catch (JSONException e2) {
            e = e2;
        }
        cardNonce.getThreeDSecureInfo().setThreeDSecureAuthenticationResponse(threeDSecureAuthenticationResponse);
        return cardNonce;
    }

    @Deprecated
    public static ThreeDSecureAuthenticationResponse fromException(String str) {
        ThreeDSecureAuthenticationResponse threeDSecureAuthenticationResponse = new ThreeDSecureAuthenticationResponse();
        threeDSecureAuthenticationResponse.mSuccess = false;
        threeDSecureAuthenticationResponse.mException = str;
        return threeDSecureAuthenticationResponse;
    }

    @Deprecated
    public boolean isSuccess() {
        return this.mSuccess;
    }

    public CardNonce getCardNonce() {
        return this.mCardNonce;
    }

    @Deprecated
    public String getErrors() {
        return this.mErrors;
    }

    public String getException() {
        return this.mException;
    }

    public ThreeDSecureAuthenticationResponse() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mSuccess ? (byte) 1 : 0);
        parcel.writeParcelable(this.mCardNonce, i);
        parcel.writeString(this.mErrors);
        parcel.writeString(this.mException);
    }

    private ThreeDSecureAuthenticationResponse(Parcel parcel) {
        this.mSuccess = parcel.readByte() != 0;
        this.mCardNonce = (CardNonce) parcel.readParcelable(CardNonce.class.getClassLoader());
        this.mErrors = parcel.readString();
        this.mException = parcel.readString();
    }
}
