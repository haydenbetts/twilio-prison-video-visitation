package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ThreeDSecureV1UiCustomization implements Parcelable {
    public static final Parcelable.Creator<ThreeDSecureV1UiCustomization> CREATOR = new Parcelable.Creator<ThreeDSecureV1UiCustomization>() {
        public ThreeDSecureV1UiCustomization createFromParcel(Parcel parcel) {
            return new ThreeDSecureV1UiCustomization(parcel);
        }

        public ThreeDSecureV1UiCustomization[] newArray(int i) {
            return new ThreeDSecureV1UiCustomization[i];
        }
    };
    private String mRedirectButtonText;
    private String mRedirectDescription;

    public int describeContents() {
        return 0;
    }

    public ThreeDSecureV1UiCustomization() {
    }

    public ThreeDSecureV1UiCustomization redirectButtonText(String str) {
        this.mRedirectButtonText = str;
        return this;
    }

    public ThreeDSecureV1UiCustomization redirectDescription(String str) {
        this.mRedirectDescription = str;
        return this;
    }

    public String getRedirectButtonText() {
        return this.mRedirectButtonText;
    }

    public String getRedirectDescription() {
        return this.mRedirectDescription;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRedirectButtonText);
        parcel.writeString(this.mRedirectDescription);
    }

    private ThreeDSecureV1UiCustomization(Parcel parcel) {
        this.mRedirectButtonText = parcel.readString();
        this.mRedirectDescription = parcel.readString();
    }
}
