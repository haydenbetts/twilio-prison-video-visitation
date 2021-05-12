package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.paypal.android.sdk.onetouch.core.PayPalLineItem;
import java.util.ArrayList;
import java.util.Collection;

public class PayPalRequest implements Parcelable {
    public static final Parcelable.Creator<PayPalRequest> CREATOR = new Parcelable.Creator<PayPalRequest>() {
        public PayPalRequest createFromParcel(Parcel parcel) {
            return new PayPalRequest(parcel);
        }

        public PayPalRequest[] newArray(int i) {
            return new PayPalRequest[i];
        }
    };
    public static final String INTENT_AUTHORIZE = "authorize";
    public static final String INTENT_ORDER = "order";
    public static final String INTENT_SALE = "sale";
    public static final String LANDING_PAGE_TYPE_BILLING = "billing";
    public static final String LANDING_PAGE_TYPE_LOGIN = "login";
    public static final String USER_ACTION_COMMIT = "commit";
    public static final String USER_ACTION_DEFAULT = "";
    private String mAmount;
    private String mBillingAgreementDescription;
    private String mCurrencyCode;
    private String mDisplayName;
    private String mIntent;
    private String mLandingPageType;
    private ArrayList<PayPalLineItem> mLineItems;
    private String mLocaleCode;
    private String mMerchantAccountId;
    private boolean mOfferCredit;
    private PayPalProductAttributes mProductAttributes;
    private boolean mShippingAddressEditable;
    private PostalAddress mShippingAddressOverride;
    private boolean mShippingAddressRequired;
    private String mUserAction;

    public int describeContents() {
        return 0;
    }

    public PayPalRequest(String str) {
        this.mShippingAddressEditable = false;
        this.mIntent = INTENT_AUTHORIZE;
        this.mUserAction = "";
        this.mLineItems = new ArrayList<>();
        this.mAmount = str;
        this.mShippingAddressRequired = false;
        this.mOfferCredit = false;
    }

    public PayPalRequest() {
        this.mShippingAddressEditable = false;
        this.mIntent = INTENT_AUTHORIZE;
        this.mUserAction = "";
        this.mLineItems = new ArrayList<>();
        this.mAmount = null;
        this.mShippingAddressRequired = false;
        this.mOfferCredit = false;
    }

    public PayPalRequest currencyCode(String str) {
        this.mCurrencyCode = str;
        return this;
    }

    public PayPalRequest shippingAddressRequired(boolean z) {
        this.mShippingAddressRequired = z;
        return this;
    }

    public PayPalRequest shippingAddressEditable(boolean z) {
        this.mShippingAddressEditable = z;
        return this;
    }

    public PayPalRequest localeCode(String str) {
        this.mLocaleCode = str;
        return this;
    }

    public PayPalRequest displayName(String str) {
        this.mDisplayName = str;
        return this;
    }

    public PayPalRequest billingAgreementDescription(String str) {
        this.mBillingAgreementDescription = str;
        return this;
    }

    public PayPalRequest shippingAddressOverride(PostalAddress postalAddress) {
        this.mShippingAddressOverride = postalAddress;
        return this;
    }

    public PayPalRequest intent(String str) {
        this.mIntent = str;
        return this;
    }

    public PayPalRequest landingPageType(String str) {
        this.mLandingPageType = str;
        return this;
    }

    public PayPalRequest userAction(String str) {
        this.mUserAction = str;
        return this;
    }

    public PayPalRequest offerCredit(boolean z) {
        this.mOfferCredit = z;
        return this;
    }

    public PayPalRequest merchantAccountId(String str) {
        this.mMerchantAccountId = str;
        return this;
    }

    public PayPalRequest lineItems(Collection<PayPalLineItem> collection) {
        this.mLineItems.clear();
        this.mLineItems.addAll(collection);
        return this;
    }

    public PayPalRequest productAttributes(PayPalProductAttributes payPalProductAttributes) {
        this.mProductAttributes = payPalProductAttributes;
        return this;
    }

    public String getAmount() {
        return this.mAmount;
    }

    public String getCurrencyCode() {
        return this.mCurrencyCode;
    }

    public String getLocaleCode() {
        return this.mLocaleCode;
    }

    public String getBillingAgreementDescription() {
        return this.mBillingAgreementDescription;
    }

    public boolean isShippingAddressRequired() {
        return this.mShippingAddressRequired;
    }

    public boolean isShippingAddressEditable() {
        return this.mShippingAddressEditable;
    }

    public PostalAddress getShippingAddressOverride() {
        return this.mShippingAddressOverride;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public boolean shouldOfferCredit() {
        return this.mOfferCredit;
    }

    public String getMerchantAccountId() {
        return this.mMerchantAccountId;
    }

    public ArrayList<PayPalLineItem> getLineItems() {
        return this.mLineItems;
    }

    public PayPalProductAttributes getProductAttributes() {
        return this.mProductAttributes;
    }

    public String getIntent() {
        return this.mIntent;
    }

    public String getLandingPageType() {
        return this.mLandingPageType;
    }

    public String getUserAction() {
        return this.mUserAction;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAmount);
        parcel.writeString(this.mCurrencyCode);
        parcel.writeString(this.mLocaleCode);
        parcel.writeString(this.mBillingAgreementDescription);
        parcel.writeByte(this.mShippingAddressRequired ? (byte) 1 : 0);
        parcel.writeByte(this.mShippingAddressEditable ? (byte) 1 : 0);
        parcel.writeParcelable(this.mShippingAddressOverride, i);
        parcel.writeString(this.mIntent);
        parcel.writeString(this.mLandingPageType);
        parcel.writeString(this.mUserAction);
        parcel.writeString(this.mDisplayName);
        parcel.writeByte(this.mOfferCredit ? (byte) 1 : 0);
        parcel.writeString(this.mMerchantAccountId);
        parcel.writeList(this.mLineItems);
        parcel.writeParcelable(this.mProductAttributes, i);
    }

    public PayPalRequest(Parcel parcel) {
        boolean z = false;
        this.mShippingAddressEditable = false;
        this.mIntent = INTENT_AUTHORIZE;
        this.mUserAction = "";
        this.mLineItems = new ArrayList<>();
        this.mAmount = parcel.readString();
        this.mCurrencyCode = parcel.readString();
        this.mLocaleCode = parcel.readString();
        this.mBillingAgreementDescription = parcel.readString();
        this.mShippingAddressRequired = parcel.readByte() > 0;
        this.mShippingAddressEditable = parcel.readByte() > 0;
        this.mShippingAddressOverride = (PostalAddress) parcel.readParcelable(PostalAddress.class.getClassLoader());
        this.mIntent = parcel.readString();
        this.mLandingPageType = parcel.readString();
        this.mUserAction = parcel.readString();
        this.mDisplayName = parcel.readString();
        this.mOfferCredit = parcel.readByte() > 0 ? true : z;
        this.mMerchantAccountId = parcel.readString();
        this.mLineItems = parcel.readArrayList(PayPalLineItem.class.getClassLoader());
        this.mProductAttributes = (PayPalProductAttributes) parcel.readParcelable(PayPalProductAttributes.class.getClassLoader());
    }
}
