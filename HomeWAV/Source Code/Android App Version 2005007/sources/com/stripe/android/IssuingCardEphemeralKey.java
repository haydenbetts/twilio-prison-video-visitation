package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

class IssuingCardEphemeralKey extends AbstractEphemeralKey {
    public static final Parcelable.Creator<IssuingCardEphemeralKey> CREATOR = new Parcelable.Creator<IssuingCardEphemeralKey>() {
        public IssuingCardEphemeralKey createFromParcel(Parcel parcel) {
            return new IssuingCardEphemeralKey(parcel);
        }

        public IssuingCardEphemeralKey[] newArray(int i) {
            return new IssuingCardEphemeralKey[i];
        }
    };

    private IssuingCardEphemeralKey(Parcel parcel) {
        super(parcel);
    }

    protected IssuingCardEphemeralKey(long j, String str, long j2, String str2, boolean z, String str3, String str4, String str5) {
        super(j, str, j2, str2, z, str3, str4, str5);
    }

    public IssuingCardEphemeralKey(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
    }

    /* access modifiers changed from: package-private */
    public String getIssuingCardId() {
        return this.mObjectId;
    }

    static IssuingCardEphemeralKey fromString(String str) throws JSONException {
        return (IssuingCardEphemeralKey) AbstractEphemeralKey.fromString(str, IssuingCardEphemeralKey.class);
    }

    static IssuingCardEphemeralKey fromJson(JSONObject jSONObject) {
        return (IssuingCardEphemeralKey) AbstractEphemeralKey.fromJson(jSONObject, IssuingCardEphemeralKey.class);
    }
}
