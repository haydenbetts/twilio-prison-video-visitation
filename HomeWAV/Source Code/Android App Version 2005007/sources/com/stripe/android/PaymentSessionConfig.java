package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import com.stripe.android.model.Address;
import com.stripe.android.model.ShippingInformation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentSessionConfig implements Parcelable {
    public static final Parcelable.Creator<PaymentSessionConfig> CREATOR = new Parcelable.Creator<PaymentSessionConfig>() {
        public PaymentSessionConfig createFromParcel(Parcel parcel) {
            return new PaymentSessionConfig(parcel);
        }

        public PaymentSessionConfig[] newArray(int i) {
            return new PaymentSessionConfig[i];
        }
    };
    private List<String> mHiddenShippingInfoFields;
    private List<String> mOptionalShippingInfoFields;
    private boolean mShippingInfoRequired;
    private ShippingInformation mShippingInformation;
    private boolean mShippingMethodRequired;

    public int describeContents() {
        return 0;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public List<String> mHiddenShippingInfoFields;
        /* access modifiers changed from: private */
        public List<String> mOptionalShippingInfoFields;
        /* access modifiers changed from: private */
        public boolean mShippingInfoRequired = true;
        /* access modifiers changed from: private */
        public ShippingInformation mShippingInformation;
        /* access modifiers changed from: private */
        public boolean mShippingMethodsRequired = true;

        public Builder setHiddenShippingInfoFields(String... strArr) {
            this.mHiddenShippingInfoFields = Arrays.asList(strArr);
            return this;
        }

        public Builder setOptionalShippingInfoFields(String... strArr) {
            this.mOptionalShippingInfoFields = Arrays.asList(strArr);
            return this;
        }

        public Builder setPrepopulatedShippingInfo(ShippingInformation shippingInformation) {
            this.mShippingInformation = shippingInformation;
            return this;
        }

        public Builder setShippingInfoRequired(boolean z) {
            this.mShippingInfoRequired = z;
            return this;
        }

        public Builder setShippingMethodsRequired(boolean z) {
            this.mShippingMethodsRequired = z;
            return this;
        }

        public PaymentSessionConfig build() {
            return new PaymentSessionConfig(this);
        }
    }

    PaymentSessionConfig(Builder builder) {
        this.mHiddenShippingInfoFields = builder.mHiddenShippingInfoFields;
        this.mOptionalShippingInfoFields = builder.mOptionalShippingInfoFields;
        this.mShippingInformation = builder.mShippingInformation;
        this.mShippingInfoRequired = builder.mShippingInfoRequired;
        this.mShippingMethodRequired = builder.mShippingMethodsRequired;
    }

    private PaymentSessionConfig(Parcel parcel) {
        ArrayList arrayList = new ArrayList();
        this.mHiddenShippingInfoFields = arrayList;
        parcel.readList(arrayList, String.class.getClassLoader());
        ArrayList arrayList2 = new ArrayList();
        this.mOptionalShippingInfoFields = arrayList2;
        parcel.readList(arrayList2, String.class.getClassLoader());
        this.mShippingInformation = (ShippingInformation) parcel.readParcelable(Address.class.getClassLoader());
        boolean z = false;
        this.mShippingInfoRequired = parcel.readInt() == 1;
        this.mShippingMethodRequired = parcel.readInt() == 1 ? true : z;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PaymentSessionConfig paymentSessionConfig = (PaymentSessionConfig) obj;
        if (isShippingInfoRequired() == paymentSessionConfig.isShippingInfoRequired() && isShippingMethodRequired() == paymentSessionConfig.isShippingMethodRequired() && getHiddenShippingInfoFields().equals(paymentSessionConfig.getHiddenShippingInfoFields()) && getOptionalShippingInfoFields().equals(paymentSessionConfig.getOptionalShippingInfoFields())) {
            return getPrepopulatedShippingInfo().equals(paymentSessionConfig.getPrepopulatedShippingInfo());
        }
        return false;
    }

    public int hashCode() {
        return (((((((getHiddenShippingInfoFields().hashCode() * 31) + getOptionalShippingInfoFields().hashCode()) * 31) + this.mShippingInformation.hashCode()) * 31) + (isShippingInfoRequired() ? 1 : 0)) * 31) + (isShippingMethodRequired() ? 1 : 0);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.mHiddenShippingInfoFields);
        parcel.writeList(this.mOptionalShippingInfoFields);
        parcel.writeParcelable(this.mShippingInformation, i);
        parcel.writeInt(this.mShippingInfoRequired ? 1 : 0);
        parcel.writeInt(this.mShippingMethodRequired ? 1 : 0);
    }

    public List<String> getHiddenShippingInfoFields() {
        return this.mHiddenShippingInfoFields;
    }

    public List<String> getOptionalShippingInfoFields() {
        return this.mOptionalShippingInfoFields;
    }

    public ShippingInformation getPrepopulatedShippingInfo() {
        return this.mShippingInformation;
    }

    public boolean isShippingInfoRequired() {
        return this.mShippingInfoRequired;
    }

    public boolean isShippingMethodRequired() {
        return this.mShippingMethodRequired;
    }
}
