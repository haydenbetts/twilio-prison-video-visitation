package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.braintreepayments.api.Json;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecureLookup implements Parcelable {
    private static final String ACS_URL_KEY = "acsUrl";
    private static final String CARD_NONCE_KEY = "paymentMethod";
    public static final Parcelable.Creator<ThreeDSecureLookup> CREATOR = new Parcelable.Creator<ThreeDSecureLookup>() {
        public ThreeDSecureLookup createFromParcel(Parcel parcel) {
            return new ThreeDSecureLookup(parcel);
        }

        public ThreeDSecureLookup[] newArray(int i) {
            return new ThreeDSecureLookup[i];
        }
    };
    private static final String LOOKUP_KEY = "lookup";
    private static final String MD_KEY = "md";
    private static final String PA_REQ_KEY = "pareq";
    private static final String TERM_URL_KEY = "termUrl";
    private static final String THREE_D_SECURE_VERSION_KEY = "threeDSecureVersion";
    private static final String TRANSACTION_ID_KEY = "transactionId";
    private String mAcsUrl;
    private CardNonce mCardNonce;
    private String mMd;
    private String mPareq;
    private String mTermUrl;
    private String mThreeDSecureVersion;
    private String mTransactionId;

    public int describeContents() {
        return 0;
    }

    public static ThreeDSecureLookup fromJson(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        ThreeDSecureLookup threeDSecureLookup = new ThreeDSecureLookup();
        CardNonce cardNonce = new CardNonce();
        cardNonce.fromJson(jSONObject.getJSONObject(CARD_NONCE_KEY));
        threeDSecureLookup.mCardNonce = cardNonce;
        JSONObject jSONObject2 = jSONObject.getJSONObject(LOOKUP_KEY);
        if (jSONObject2.isNull(ACS_URL_KEY)) {
            threeDSecureLookup.mAcsUrl = null;
        } else {
            threeDSecureLookup.mAcsUrl = jSONObject2.getString(ACS_URL_KEY);
        }
        threeDSecureLookup.mMd = jSONObject2.getString(MD_KEY);
        threeDSecureLookup.mTermUrl = jSONObject2.getString(TERM_URL_KEY);
        threeDSecureLookup.mPareq = jSONObject2.getString(PA_REQ_KEY);
        threeDSecureLookup.mThreeDSecureVersion = Json.optString(jSONObject2, THREE_D_SECURE_VERSION_KEY, "");
        threeDSecureLookup.mTransactionId = Json.optString(jSONObject2, TRANSACTION_ID_KEY, "");
        return threeDSecureLookup;
    }

    public CardNonce getCardNonce() {
        return this.mCardNonce;
    }

    public String getAcsUrl() {
        return this.mAcsUrl;
    }

    public String getMd() {
        return this.mMd;
    }

    public String getTermUrl() {
        return this.mTermUrl;
    }

    public String getPareq() {
        return this.mPareq;
    }

    public String getThreeDSecureVersion() {
        return this.mThreeDSecureVersion;
    }

    public String getTransactionId() {
        return this.mTransactionId;
    }

    public boolean requiresUserAuthentication() {
        return this.mAcsUrl != null;
    }

    public ThreeDSecureLookup() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mCardNonce, i);
        parcel.writeString(this.mAcsUrl);
        parcel.writeString(this.mMd);
        parcel.writeString(this.mTermUrl);
        parcel.writeString(this.mPareq);
        parcel.writeString(this.mThreeDSecureVersion);
        parcel.writeString(this.mTransactionId);
    }

    private ThreeDSecureLookup(Parcel parcel) {
        this.mCardNonce = (CardNonce) parcel.readParcelable(CardNonce.class.getClassLoader());
        this.mAcsUrl = parcel.readString();
        this.mMd = parcel.readString();
        this.mTermUrl = parcel.readString();
        this.mPareq = parcel.readString();
        this.mThreeDSecureVersion = parcel.readString();
        this.mTransactionId = parcel.readString();
    }
}
