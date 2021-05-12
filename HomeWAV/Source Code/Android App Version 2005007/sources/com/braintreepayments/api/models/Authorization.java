package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.braintreepayments.api.exceptions.InvalidArgumentException;

public abstract class Authorization implements Parcelable {
    private final String mRawValue;

    public abstract String getBearer();

    public abstract String getConfigUrl();

    public Authorization(String str) {
        this.mRawValue = str;
    }

    public static Authorization fromString(String str) throws InvalidArgumentException {
        if (isTokenizationKey(str)) {
            return new TokenizationKey(str);
        }
        if (isPayPalUAT(str)) {
            return new PayPalUAT(str);
        }
        if (isClientToken(str)) {
            return new ClientToken(str);
        }
        throw new InvalidArgumentException("Authorization provided is invalid: " + str);
    }

    public String toString() {
        return this.mRawValue;
    }

    @Deprecated
    public static boolean isTokenizationKey(String str) {
        return !TextUtils.isEmpty(str) && str.matches("^[a-zA-Z0-9]+_[a-zA-Z0-9]+_[a-zA-Z0-9_]+$");
    }

    private static boolean isPayPalUAT(String str) {
        return !TextUtils.isEmpty(str) && str.matches("^[a-zA-Z0-9]+\\.[a-zA-Z0-9]+\\.[a-zA-Z0-9_-]+$");
    }

    private static boolean isClientToken(String str) {
        return !TextUtils.isEmpty(str) && str.matches("([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRawValue);
    }

    public Authorization(Parcel parcel) {
        this.mRawValue = parcel.readString();
    }
}
