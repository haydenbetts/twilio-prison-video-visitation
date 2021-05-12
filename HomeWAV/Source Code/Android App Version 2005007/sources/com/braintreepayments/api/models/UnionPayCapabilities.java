package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

public class UnionPayCapabilities implements Parcelable {
    public static final Parcelable.Creator<UnionPayCapabilities> CREATOR = new Parcelable.Creator<UnionPayCapabilities>() {
        public UnionPayCapabilities createFromParcel(Parcel parcel) {
            return new UnionPayCapabilities(parcel);
        }

        public UnionPayCapabilities[] newArray(int i) {
            return new UnionPayCapabilities[i];
        }
    };
    private static final String IS_DEBIT_KEY = "isDebit";
    private static final String IS_SUPPORTED_KEY = "isSupported";
    private static final String IS_UNIONPAY_KEY = "isUnionPay";
    private static final String SUPPORTS_TWO_STEP_AUTH_AND_CAPTURE_KEY = "supportsTwoStepAuthAndCapture";
    private static final String UNIONPAY_KEY = "unionPay";
    private boolean mIsDebit;
    private boolean mIsSupported;
    private boolean mIsUnionPay;
    private boolean mSupportsTwoStepAuthAndCapture;

    public int describeContents() {
        return 0;
    }

    public static UnionPayCapabilities fromJson(String str) {
        UnionPayCapabilities unionPayCapabilities = new UnionPayCapabilities();
        try {
            JSONObject jSONObject = new JSONObject(str);
            unionPayCapabilities.mIsUnionPay = jSONObject.optBoolean(IS_UNIONPAY_KEY);
            unionPayCapabilities.mIsDebit = jSONObject.optBoolean(IS_DEBIT_KEY);
            if (jSONObject.has(UNIONPAY_KEY)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(UNIONPAY_KEY);
                unionPayCapabilities.mSupportsTwoStepAuthAndCapture = jSONObject2.optBoolean(SUPPORTS_TWO_STEP_AUTH_AND_CAPTURE_KEY);
                unionPayCapabilities.mIsSupported = jSONObject2.optBoolean(IS_SUPPORTED_KEY);
            }
        } catch (JSONException unused) {
        }
        return unionPayCapabilities;
    }

    public boolean isUnionPay() {
        return this.mIsUnionPay;
    }

    public boolean isDebit() {
        return this.mIsDebit;
    }

    public boolean supportsTwoStepAuthAndCapture() {
        return this.mSupportsTwoStepAuthAndCapture;
    }

    public boolean isSupported() {
        return this.mIsSupported;
    }

    private UnionPayCapabilities() {
    }

    public UnionPayCapabilities(Parcel parcel) {
        boolean z = true;
        this.mIsUnionPay = parcel.readByte() > 0;
        this.mIsDebit = parcel.readByte() > 0;
        this.mSupportsTwoStepAuthAndCapture = parcel.readByte() > 0;
        this.mIsSupported = parcel.readByte() <= 0 ? false : z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsUnionPay ? (byte) 1 : 0);
        parcel.writeByte(this.mIsDebit ? (byte) 1 : 0);
        parcel.writeByte(this.mSupportsTwoStepAuthAndCapture ? (byte) 1 : 0);
        parcel.writeByte(this.mIsSupported ? (byte) 1 : 0);
    }
}
