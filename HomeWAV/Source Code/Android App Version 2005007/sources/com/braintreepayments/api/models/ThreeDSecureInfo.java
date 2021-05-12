package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

public class ThreeDSecureInfo implements Parcelable {
    private static final String ACS_TRANSACTION_ID_KEY = "acsTransactionId";
    private static final String AUTHENTICATION_KEY = "authentication";
    private static final String CAVV_KEY = "cavv";
    public static final Parcelable.Creator<ThreeDSecureInfo> CREATOR = new Parcelable.Creator<ThreeDSecureInfo>() {
        public ThreeDSecureInfo createFromParcel(Parcel parcel) {
            return new ThreeDSecureInfo(parcel);
        }

        public ThreeDSecureInfo[] newArray(int i) {
            return new ThreeDSecureInfo[i];
        }
    };
    private static final String DS_TRANSACTION_ID_KEY = "dsTransactionId";
    private static final String ECI_FLAG_KEY = "eciFlag";
    private static final String ENROLLED_KEY = "enrolled";
    private static final String LIABILITY_SHIFTED_KEY = "liabilityShifted";
    private static final String LIABILITY_SHIFT_POSSIBLE_KEY = "liabilityShiftPossible";
    private static final String LOOKUP_KEY = "lookup";
    private static final String PARES_STATUS_KEY = "paresStatus";
    private static final String STATUS_KEY = "status";
    private static final String THREE_D_SECURE_AUTHENTICATION_ID_KEY = "threeDSecureAuthenticationId";
    private static final String THREE_D_SECURE_SERVER_TRANSACTION_ID_KEY = "threeDSecureServerTransactionId";
    private static final String THREE_D_SECURE_VERSION_KEY = "threeDSecureVersion";
    private static final String TRANS_STATUS_KEY = "transStatus";
    private static final String TRANS_STATUS_REASON_KEY = "transStatusReason";
    private static final String XID_KEY = "xid";
    private String mAcsTransactionId;
    private String mAuthenticationTransactionStatus;
    private String mAuthenticationTransactionStatusReason;
    private String mCavv;
    private String mDsTransactionId;
    private String mEciFlag;
    private String mEnrolled;
    private String mErrorMessage;
    private boolean mLiabilityShiftPossible;
    private boolean mLiabilityShifted;
    private String mLookupTransactionStatus;
    private String mLookupTransactionStatusReason;
    private String mParesStatus;
    private String mStatus;
    private String mThreeDSecureAuthenticationId;
    private ThreeDSecureAuthenticationResponse mThreeDSecureAuthenticationResponse;
    private String mThreeDSecureServerTransactionId;
    private String mThreeDSecureVersion;
    private boolean mWasVerified;
    private String mXid;

    public int describeContents() {
        return 0;
    }

    protected static ThreeDSecureInfo fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        ThreeDSecureInfo threeDSecureInfo = new ThreeDSecureInfo();
        threeDSecureInfo.mCavv = jSONObject.optString(CAVV_KEY);
        threeDSecureInfo.mDsTransactionId = jSONObject.optString(DS_TRANSACTION_ID_KEY);
        threeDSecureInfo.mEciFlag = jSONObject.optString(ECI_FLAG_KEY);
        threeDSecureInfo.mEnrolled = jSONObject.optString(ENROLLED_KEY);
        threeDSecureInfo.mLiabilityShifted = jSONObject.optBoolean(LIABILITY_SHIFTED_KEY);
        threeDSecureInfo.mLiabilityShiftPossible = jSONObject.optBoolean(LIABILITY_SHIFT_POSSIBLE_KEY);
        threeDSecureInfo.mStatus = jSONObject.optString("status");
        threeDSecureInfo.mThreeDSecureVersion = jSONObject.optString(THREE_D_SECURE_VERSION_KEY);
        threeDSecureInfo.mWasVerified = jSONObject.has(LIABILITY_SHIFTED_KEY) && jSONObject.has(LIABILITY_SHIFT_POSSIBLE_KEY);
        threeDSecureInfo.mXid = jSONObject.optString(XID_KEY);
        threeDSecureInfo.mAcsTransactionId = jSONObject.optString(ACS_TRANSACTION_ID_KEY);
        threeDSecureInfo.mThreeDSecureAuthenticationId = jSONObject.optString(THREE_D_SECURE_AUTHENTICATION_ID_KEY);
        threeDSecureInfo.mThreeDSecureServerTransactionId = jSONObject.optString(THREE_D_SECURE_SERVER_TRANSACTION_ID_KEY);
        threeDSecureInfo.mParesStatus = jSONObject.optString(PARES_STATUS_KEY);
        JSONObject optJSONObject = jSONObject.optJSONObject(AUTHENTICATION_KEY);
        if (optJSONObject != null) {
            threeDSecureInfo.mAuthenticationTransactionStatus = optJSONObject.optString(TRANS_STATUS_KEY);
            threeDSecureInfo.mAuthenticationTransactionStatusReason = optJSONObject.optString(TRANS_STATUS_REASON_KEY);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject(LOOKUP_KEY);
        if (optJSONObject2 != null) {
            threeDSecureInfo.mLookupTransactionStatus = optJSONObject2.optString(TRANS_STATUS_KEY);
            threeDSecureInfo.mLookupTransactionStatusReason = optJSONObject2.optString(TRANS_STATUS_REASON_KEY);
        }
        return threeDSecureInfo;
    }

    /* access modifiers changed from: protected */
    public void setThreeDSecureAuthenticationResponse(ThreeDSecureAuthenticationResponse threeDSecureAuthenticationResponse) {
        this.mThreeDSecureAuthenticationResponse = threeDSecureAuthenticationResponse;
    }

    public void setErrorMessage(String str) {
        this.mErrorMessage = str;
    }

    public String getCavv() {
        return this.mCavv;
    }

    public String getDsTransactionId() {
        return this.mDsTransactionId;
    }

    public String getEciFlag() {
        return this.mEciFlag;
    }

    public String getEnrolled() {
        return this.mEnrolled;
    }

    public boolean isLiabilityShifted() {
        return this.mLiabilityShifted;
    }

    public boolean isLiabilityShiftPossible() {
        return this.mLiabilityShiftPossible;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public String getThreeDSecureVersion() {
        return this.mThreeDSecureVersion;
    }

    public boolean wasVerified() {
        return this.mWasVerified;
    }

    public String getXid() {
        return this.mXid;
    }

    public String getAcsTransactionId() {
        return this.mAcsTransactionId;
    }

    public String getThreeDSecureAuthenticationId() {
        return this.mThreeDSecureAuthenticationId;
    }

    public String getThreeDSecureServerTransactionId() {
        return this.mThreeDSecureServerTransactionId;
    }

    public String getParesStatus() {
        return this.mParesStatus;
    }

    public ThreeDSecureAuthenticationResponse getThreeDSecureAuthenticationResponse() {
        return this.mThreeDSecureAuthenticationResponse;
    }

    public String getAuthenticationTransactionStatus() {
        return this.mAuthenticationTransactionStatus;
    }

    public String getAuthenticationTransactionStatusReason() {
        return this.mAuthenticationTransactionStatusReason;
    }

    public String getLookupTransactionStatus() {
        return this.mLookupTransactionStatus;
    }

    public String getLookupTransactionStatusReason() {
        return this.mLookupTransactionStatusReason;
    }

    public String getErrorMessage() {
        return this.mErrorMessage;
    }

    public ThreeDSecureInfo() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mCavv);
        parcel.writeString(this.mDsTransactionId);
        parcel.writeString(this.mEciFlag);
        parcel.writeString(this.mEnrolled);
        parcel.writeByte(this.mLiabilityShifted ? (byte) 1 : 0);
        parcel.writeByte(this.mLiabilityShiftPossible ? (byte) 1 : 0);
        parcel.writeString(this.mStatus);
        parcel.writeString(this.mThreeDSecureVersion);
        parcel.writeByte(this.mWasVerified ? (byte) 1 : 0);
        parcel.writeString(this.mXid);
        parcel.writeString(this.mAuthenticationTransactionStatus);
        parcel.writeString(this.mAuthenticationTransactionStatusReason);
        parcel.writeString(this.mLookupTransactionStatus);
        parcel.writeString(this.mLookupTransactionStatusReason);
        parcel.writeString(this.mThreeDSecureAuthenticationId);
    }

    private ThreeDSecureInfo(Parcel parcel) {
        this.mCavv = parcel.readString();
        this.mDsTransactionId = parcel.readString();
        this.mEciFlag = parcel.readString();
        this.mEnrolled = parcel.readString();
        boolean z = true;
        this.mLiabilityShifted = parcel.readByte() != 0;
        this.mLiabilityShiftPossible = parcel.readByte() != 0;
        this.mStatus = parcel.readString();
        this.mThreeDSecureVersion = parcel.readString();
        this.mWasVerified = parcel.readByte() == 0 ? false : z;
        this.mXid = parcel.readString();
        this.mAuthenticationTransactionStatus = parcel.readString();
        this.mAuthenticationTransactionStatusReason = parcel.readString();
        this.mLookupTransactionStatus = parcel.readString();
        this.mLookupTransactionStatusReason = parcel.readString();
        this.mThreeDSecureAuthenticationId = parcel.readString();
    }
}
