package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.ShippingMethod;

public class PaymentSessionData implements Parcelable {
    public static final Parcelable.Creator<PaymentSessionData> CREATOR = new Parcelable.Creator<PaymentSessionData>() {
        public PaymentSessionData createFromParcel(Parcel parcel) {
            return new PaymentSessionData(parcel);
        }

        public PaymentSessionData[] newArray(int i) {
            return new PaymentSessionData[i];
        }
    };
    private static final String NO_PAYMENT = "NO_PAYMENT";
    private long mCartTotal;
    private boolean mIsPaymentReadyToCharge;
    private String mPaymentResult;
    private String mSelectedPaymentMethodId;
    private ShippingInformation mShippingInformation;
    private ShippingMethod mShippingMethod;
    private long mShippingTotal;

    public int describeContents() {
        return 0;
    }

    public PaymentSessionData() {
        this.mCartTotal = 0;
        this.mSelectedPaymentMethodId = NO_PAYMENT;
        this.mShippingTotal = 0;
        this.mPaymentResult = PaymentResultListener.INCOMPLETE;
    }

    public String getSelectedPaymentMethodId() {
        if (this.mSelectedPaymentMethodId.equals(NO_PAYMENT)) {
            return null;
        }
        return this.mSelectedPaymentMethodId;
    }

    public long getCartTotal() {
        return this.mCartTotal;
    }

    public String getPaymentResult() {
        return this.mPaymentResult;
    }

    public boolean isPaymentReadyToCharge() {
        return this.mIsPaymentReadyToCharge;
    }

    public void setPaymentReadyToCharge(boolean z) {
        this.mIsPaymentReadyToCharge = z;
    }

    public long getShippingTotal() {
        return this.mShippingTotal;
    }

    public ShippingInformation getShippingInformation() {
        return this.mShippingInformation;
    }

    public void setShippingInformation(ShippingInformation shippingInformation) {
        this.mShippingInformation = shippingInformation;
    }

    public ShippingMethod getShippingMethod() {
        return this.mShippingMethod;
    }

    public void setShippingMethod(ShippingMethod shippingMethod) {
        this.mShippingMethod = shippingMethod;
    }

    /* access modifiers changed from: package-private */
    public void setCartTotal(long j) {
        this.mCartTotal = j;
    }

    /* access modifiers changed from: package-private */
    public void setPaymentResult(String str) {
        this.mPaymentResult = str;
    }

    /* access modifiers changed from: package-private */
    public void setSelectedPaymentMethodId(String str) {
        if (str == null) {
            str = NO_PAYMENT;
        }
        this.mSelectedPaymentMethodId = str;
    }

    /* access modifiers changed from: package-private */
    public void setShippingTotal(long j) {
        this.mShippingTotal = j;
    }

    public boolean updateIsPaymentReadyToCharge(PaymentSessionConfig paymentSessionConfig) {
        if (StripeTextUtils.isBlank(getSelectedPaymentMethodId()) || ((paymentSessionConfig.isShippingInfoRequired() && getShippingInformation() == null) || (paymentSessionConfig.isShippingMethodRequired() && getShippingMethod() == null))) {
            setPaymentReadyToCharge(false);
            return false;
        }
        setPaymentReadyToCharge(true);
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PaymentSessionData paymentSessionData = (PaymentSessionData) obj;
        if (this.mCartTotal != paymentSessionData.mCartTotal || this.mIsPaymentReadyToCharge != paymentSessionData.mIsPaymentReadyToCharge || this.mShippingTotal != paymentSessionData.mShippingTotal || !this.mSelectedPaymentMethodId.equals(paymentSessionData.mSelectedPaymentMethodId) || !this.mPaymentResult.equals(paymentSessionData.mPaymentResult)) {
            return false;
        }
        ShippingInformation shippingInformation = this.mShippingInformation;
        if (shippingInformation == null ? paymentSessionData.mShippingInformation != null : !shippingInformation.equals(paymentSessionData.mShippingInformation)) {
            return false;
        }
        ShippingMethod shippingMethod = this.mShippingMethod;
        if (shippingMethod != null) {
            return shippingMethod.equals(paymentSessionData.mShippingMethod);
        }
        if (paymentSessionData.mShippingMethod == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.mCartTotal;
        long j2 = this.mShippingTotal;
        int hashCode = ((((((((((int) (j ^ (j >>> 32))) * 31) + (this.mIsPaymentReadyToCharge ? 1 : 0)) * 31) + this.mSelectedPaymentMethodId.hashCode()) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + this.mPaymentResult.hashCode()) * 31;
        ShippingInformation shippingInformation = this.mShippingInformation;
        int i = 0;
        int hashCode2 = (hashCode + (shippingInformation != null ? shippingInformation.hashCode() : 0)) * 31;
        ShippingMethod shippingMethod = this.mShippingMethod;
        if (shippingMethod != null) {
            i = shippingMethod.hashCode();
        }
        return hashCode2 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mCartTotal);
        parcel.writeInt(this.mIsPaymentReadyToCharge ? 1 : 0);
        parcel.writeString(this.mPaymentResult);
        parcel.writeString(this.mSelectedPaymentMethodId);
        parcel.writeParcelable(this.mShippingInformation, i);
        parcel.writeParcelable(this.mShippingMethod, i);
        parcel.writeLong(this.mShippingTotal);
    }

    private PaymentSessionData(Parcel parcel) {
        this.mCartTotal = 0;
        this.mSelectedPaymentMethodId = NO_PAYMENT;
        this.mShippingTotal = 0;
        this.mPaymentResult = PaymentResultListener.INCOMPLETE;
        this.mCartTotal = parcel.readLong();
        this.mIsPaymentReadyToCharge = parcel.readInt() != 1 ? false : true;
        this.mPaymentResult = PaymentSessionUtils.paymentResultFromString(parcel.readString());
        this.mSelectedPaymentMethodId = parcel.readString();
        this.mShippingInformation = (ShippingInformation) parcel.readParcelable(ShippingInformation.class.getClassLoader());
        this.mShippingMethod = (ShippingMethod) parcel.readParcelable(ShippingMethod.class.getClassLoader());
        this.mShippingTotal = parcel.readLong();
    }
}
