package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONException;
import org.json.JSONObject;

class CustomerEphemeralKey extends AbstractEphemeralKey {
    public static final Parcelable.Creator<CustomerEphemeralKey> CREATOR = new Parcelable.Creator<CustomerEphemeralKey>() {
        public CustomerEphemeralKey createFromParcel(Parcel parcel) {
            return new CustomerEphemeralKey(parcel);
        }

        public CustomerEphemeralKey[] newArray(int i) {
            return new CustomerEphemeralKey[i];
        }
    };

    private CustomerEphemeralKey(Parcel parcel) {
        super(parcel);
    }

    protected CustomerEphemeralKey(long j, String str, long j2, String str2, boolean z, String str3, String str4, String str5) {
        super(j, str, j2, str2, z, str3, str4, str5);
    }

    public CustomerEphemeralKey(JSONObject jSONObject) throws JSONException {
        super(jSONObject);
    }

    /* access modifiers changed from: package-private */
    public String getCustomerId() {
        return this.mObjectId;
    }

    static CustomerEphemeralKey fromString(String str) throws JSONException {
        return (CustomerEphemeralKey) AbstractEphemeralKey.fromString(str, CustomerEphemeralKey.class);
    }

    static CustomerEphemeralKey fromJson(JSONObject jSONObject) {
        return (CustomerEphemeralKey) AbstractEphemeralKey.fromJson(jSONObject, CustomerEphemeralKey.class);
    }
}
