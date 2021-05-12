package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.braintreepayments.api.PayPal;
import org.json.JSONException;
import org.json.JSONObject;

public class ThreeDSecureAdditionalInformation implements Parcelable {
    public static final Parcelable.Creator<ThreeDSecureAdditionalInformation> CREATOR = new Parcelable.Creator<ThreeDSecureAdditionalInformation>() {
        public ThreeDSecureAdditionalInformation createFromParcel(Parcel parcel) {
            return new ThreeDSecureAdditionalInformation(parcel);
        }

        public ThreeDSecureAdditionalInformation[] newArray(int i) {
            return new ThreeDSecureAdditionalInformation[i];
        }
    };
    private String mAccountAgeIndicator;
    private String mAccountChangeDate;
    private String mAccountChangeIndicator;
    private String mAccountCreateDate;
    private String mAccountId;
    private String mAccountPurchases;
    private String mAccountPwdChangeDate;
    private String mAccountPwdChangeIndicator;
    private String mAddCardAttempts;
    private String mAddressMatch;
    private String mAuthenticationIndicator;
    private String mDeliveryEmail;
    private String mDeliveryTimeframe;
    private String mFraudActivity;
    private String mGiftCardAmount;
    private String mGiftCardCount;
    private String mGiftCardCurrencyCode;
    private String mInstallment;
    private String mIpAddress;
    private String mOrderDescription;
    private String mPaymentAccountAge;
    private String mPaymentAccountIndicator;
    private String mPreorderDate;
    private String mPreorderIndicator;
    private String mProductCode;
    private String mPurchaseDate;
    private String mRecurringEnd;
    private String mRecurringFrequency;
    private String mReorderIndicator;
    private String mSdkMaxTimeout;
    private ThreeDSecurePostalAddress mShippingAddress;
    private String mShippingAddressUsageDate;
    private String mShippingAddressUsageIndicator;
    private String mShippingMethodIndicator;
    private String mShippingNameIndicator;
    private String mTaxAmount;
    private String mTransactionCountDay;
    private String mTransactionCountYear;
    private String mUserAgent;
    private String mWorkPhoneNumber;

    public int describeContents() {
        return 0;
    }

    public ThreeDSecureAdditionalInformation() {
    }

    public ThreeDSecureAdditionalInformation shippingAddress(ThreeDSecurePostalAddress threeDSecurePostalAddress) {
        this.mShippingAddress = threeDSecurePostalAddress;
        return this;
    }

    public ThreeDSecureAdditionalInformation shippingMethodIndicator(String str) {
        this.mShippingMethodIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation productCode(String str) {
        this.mProductCode = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation deliveryTimeframe(String str) {
        this.mDeliveryTimeframe = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation deliveryEmail(String str) {
        this.mDeliveryEmail = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation reorderIndicator(String str) {
        this.mReorderIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation preorderIndicator(String str) {
        this.mPreorderIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation preorderDate(String str) {
        this.mPreorderDate = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation giftCardAmount(String str) {
        this.mGiftCardAmount = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation giftCardCurrencyCode(String str) {
        this.mGiftCardCurrencyCode = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation giftCardCount(String str) {
        this.mGiftCardCount = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountAgeIndicator(String str) {
        this.mAccountAgeIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountCreateDate(String str) {
        this.mAccountCreateDate = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountChangeIndicator(String str) {
        this.mAccountChangeIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountChangeDate(String str) {
        this.mAccountChangeDate = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountPwdChangeIndicator(String str) {
        this.mAccountPwdChangeIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountPwdChangeDate(String str) {
        this.mAccountPwdChangeDate = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation shippingAddressUsageIndicator(String str) {
        this.mShippingAddressUsageIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation shippingAddressUsageDate(String str) {
        this.mShippingAddressUsageDate = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation transactionCountDay(String str) {
        this.mTransactionCountDay = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation transactionCountYear(String str) {
        this.mTransactionCountYear = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation addCardAttempts(String str) {
        this.mAddCardAttempts = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountPurchases(String str) {
        this.mAccountPurchases = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation fraudActivity(String str) {
        this.mFraudActivity = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation shippingNameIndicator(String str) {
        this.mShippingNameIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation paymentAccountIndicator(String str) {
        this.mPaymentAccountIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation paymentAccountAge(String str) {
        this.mPaymentAccountAge = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation addressMatch(String str) {
        this.mAddressMatch = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation accountId(String str) {
        this.mAccountId = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation ipAddress(String str) {
        this.mIpAddress = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation orderDescription(String str) {
        this.mOrderDescription = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation taxAmount(String str) {
        this.mTaxAmount = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation userAgent(String str) {
        this.mUserAgent = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation authenticationIndicator(String str) {
        this.mAuthenticationIndicator = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation installment(String str) {
        this.mInstallment = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation purchaseDate(String str) {
        this.mPurchaseDate = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation recurringEnd(String str) {
        this.mRecurringEnd = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation recurringFrequency(String str) {
        this.mRecurringFrequency = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation sdkMaxTimeout(String str) {
        this.mSdkMaxTimeout = str;
        return this;
    }

    public ThreeDSecureAdditionalInformation workPhoneNumber(String str) {
        this.mWorkPhoneNumber = str;
        return this;
    }

    public ThreeDSecurePostalAddress getShippingAddress() {
        return this.mShippingAddress;
    }

    public String getShippingMethodIndicator() {
        return this.mShippingMethodIndicator;
    }

    public String getProductCode() {
        return this.mProductCode;
    }

    public String getDeliveryTimeframe() {
        return this.mDeliveryTimeframe;
    }

    public String getDeliveryEmail() {
        return this.mDeliveryEmail;
    }

    public String getReorderIndicator() {
        return this.mReorderIndicator;
    }

    public String getPreorderIndicator() {
        return this.mPreorderIndicator;
    }

    public String getPreorderDate() {
        return this.mPreorderDate;
    }

    public String getGiftCardAmount() {
        return this.mGiftCardAmount;
    }

    public String getGiftCardCurrencyCode() {
        return this.mGiftCardCurrencyCode;
    }

    public String getGiftCardCount() {
        return this.mGiftCardCount;
    }

    public String getAccountAgeIndicator() {
        return this.mAccountAgeIndicator;
    }

    public String getAccountCreateDate() {
        return this.mAccountCreateDate;
    }

    public String getAccountChangeIndicator() {
        return this.mAccountChangeIndicator;
    }

    public String getAccountChangeDate() {
        return this.mAccountChangeDate;
    }

    public String getAccountPwdChangeIndicator() {
        return this.mAccountPwdChangeIndicator;
    }

    public String getAccountPwdChangeDate() {
        return this.mAccountPwdChangeDate;
    }

    public String getShippingAddressUsageIndicator() {
        return this.mShippingAddressUsageIndicator;
    }

    public String getShippingAddressUsageDate() {
        return this.mShippingAddressUsageDate;
    }

    public String getTransactionCountDay() {
        return this.mTransactionCountDay;
    }

    public String getTransactionCountYear() {
        return this.mTransactionCountYear;
    }

    public String getAddCardAttempts() {
        return this.mAddCardAttempts;
    }

    public String getAccountPurchases() {
        return this.mAccountPurchases;
    }

    public String getFraudActivity() {
        return this.mFraudActivity;
    }

    public String getShippingNameIndicator() {
        return this.mShippingNameIndicator;
    }

    public String getPaymentAccountIdicator() {
        return this.mPaymentAccountIndicator;
    }

    public String getPaymentAccountAge() {
        return this.mPaymentAccountAge;
    }

    public String getAddressMatch() {
        return this.mAddressMatch;
    }

    public String getAccountId() {
        return this.mAccountId;
    }

    public String getIpAddress() {
        return this.mIpAddress;
    }

    public String getOrderDescription() {
        return this.mOrderDescription;
    }

    public String getTaxAmount() {
        return this.mTaxAmount;
    }

    public String getUserAgent() {
        return this.mUserAgent;
    }

    public String getAuthenticationIndicator() {
        return this.mAuthenticationIndicator;
    }

    public String getInstallment() {
        return this.mInstallment;
    }

    public String getPurchaseDate() {
        return this.mPurchaseDate;
    }

    public String getRecurringEnd() {
        return this.mRecurringEnd;
    }

    public String getRecurringFrequency() {
        return this.mRecurringFrequency;
    }

    public String getSdkMaxTimeout() {
        return this.mSdkMaxTimeout;
    }

    public String getWorkPhoneNumber() {
        return this.mWorkPhoneNumber;
    }

    public ThreeDSecureAdditionalInformation(Parcel parcel) {
        this.mShippingAddress = (ThreeDSecurePostalAddress) parcel.readParcelable(ThreeDSecurePostalAddress.class.getClassLoader());
        this.mShippingMethodIndicator = parcel.readString();
        this.mProductCode = parcel.readString();
        this.mDeliveryTimeframe = parcel.readString();
        this.mDeliveryEmail = parcel.readString();
        this.mReorderIndicator = parcel.readString();
        this.mPreorderIndicator = parcel.readString();
        this.mPreorderDate = parcel.readString();
        this.mGiftCardAmount = parcel.readString();
        this.mGiftCardCurrencyCode = parcel.readString();
        this.mGiftCardCount = parcel.readString();
        this.mAccountAgeIndicator = parcel.readString();
        this.mAccountCreateDate = parcel.readString();
        this.mAccountChangeIndicator = parcel.readString();
        this.mAccountChangeDate = parcel.readString();
        this.mAccountPwdChangeIndicator = parcel.readString();
        this.mAccountPwdChangeDate = parcel.readString();
        this.mShippingAddressUsageIndicator = parcel.readString();
        this.mShippingAddressUsageDate = parcel.readString();
        this.mTransactionCountDay = parcel.readString();
        this.mTransactionCountYear = parcel.readString();
        this.mAddCardAttempts = parcel.readString();
        this.mAccountPurchases = parcel.readString();
        this.mFraudActivity = parcel.readString();
        this.mShippingNameIndicator = parcel.readString();
        this.mPaymentAccountIndicator = parcel.readString();
        this.mPaymentAccountAge = parcel.readString();
        this.mAddressMatch = parcel.readString();
        this.mAccountId = parcel.readString();
        this.mIpAddress = parcel.readString();
        this.mOrderDescription = parcel.readString();
        this.mTaxAmount = parcel.readString();
        this.mUserAgent = parcel.readString();
        this.mAuthenticationIndicator = parcel.readString();
        this.mInstallment = parcel.readString();
        this.mPurchaseDate = parcel.readString();
        this.mRecurringEnd = parcel.readString();
        this.mRecurringFrequency = parcel.readString();
        this.mSdkMaxTimeout = parcel.readString();
        this.mWorkPhoneNumber = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mShippingAddress, i);
        parcel.writeString(this.mShippingMethodIndicator);
        parcel.writeString(this.mProductCode);
        parcel.writeString(this.mDeliveryTimeframe);
        parcel.writeString(this.mDeliveryEmail);
        parcel.writeString(this.mReorderIndicator);
        parcel.writeString(this.mPreorderIndicator);
        parcel.writeString(this.mPreorderDate);
        parcel.writeString(this.mGiftCardAmount);
        parcel.writeString(this.mGiftCardCurrencyCode);
        parcel.writeString(this.mGiftCardCount);
        parcel.writeString(this.mAccountAgeIndicator);
        parcel.writeString(this.mAccountCreateDate);
        parcel.writeString(this.mAccountChangeIndicator);
        parcel.writeString(this.mAccountChangeDate);
        parcel.writeString(this.mAccountPwdChangeIndicator);
        parcel.writeString(this.mAccountPwdChangeDate);
        parcel.writeString(this.mShippingAddressUsageIndicator);
        parcel.writeString(this.mShippingAddressUsageDate);
        parcel.writeString(this.mTransactionCountDay);
        parcel.writeString(this.mTransactionCountYear);
        parcel.writeString(this.mAddCardAttempts);
        parcel.writeString(this.mAccountPurchases);
        parcel.writeString(this.mFraudActivity);
        parcel.writeString(this.mShippingNameIndicator);
        parcel.writeString(this.mPaymentAccountIndicator);
        parcel.writeString(this.mPaymentAccountAge);
        parcel.writeString(this.mAddressMatch);
        parcel.writeString(this.mAccountId);
        parcel.writeString(this.mIpAddress);
        parcel.writeString(this.mOrderDescription);
        parcel.writeString(this.mTaxAmount);
        parcel.writeString(this.mUserAgent);
        parcel.writeString(this.mAuthenticationIndicator);
        parcel.writeString(this.mInstallment);
        parcel.writeString(this.mPurchaseDate);
        parcel.writeString(this.mRecurringEnd);
        parcel.writeString(this.mRecurringFrequency);
        parcel.writeString(this.mSdkMaxTimeout);
        parcel.writeString(this.mWorkPhoneNumber);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            ThreeDSecurePostalAddress threeDSecurePostalAddress = this.mShippingAddress;
            if (threeDSecurePostalAddress != null) {
                jSONObject.putOpt("shipping_given_name", threeDSecurePostalAddress.getGivenName());
                jSONObject.putOpt("shipping_surname", this.mShippingAddress.getSurname());
                jSONObject.putOpt("shipping_phone", this.mShippingAddress.getPhoneNumber());
                jSONObject.putOpt("shipping_line1", this.mShippingAddress.getStreetAddress());
                jSONObject.putOpt("shipping_line2", this.mShippingAddress.getExtendedAddress());
                jSONObject.putOpt("shipping_line3", this.mShippingAddress.getLine3());
                jSONObject.putOpt("shipping_city", this.mShippingAddress.getLocality());
                jSONObject.putOpt("shipping_state", this.mShippingAddress.getRegion());
                jSONObject.putOpt("shipping_postal_code", this.mShippingAddress.getPostalCode());
                jSONObject.putOpt("shipping_country_code", this.mShippingAddress.getCountryCodeAlpha2());
            }
            jSONObject.putOpt("shipping_method_indicator", this.mShippingMethodIndicator);
            jSONObject.putOpt(PayPal.PRODUCT_CODE, this.mProductCode);
            jSONObject.putOpt("delivery_timeframe", this.mDeliveryTimeframe);
            jSONObject.putOpt("delivery_email", this.mDeliveryEmail);
            jSONObject.putOpt("reorder_indicator", this.mReorderIndicator);
            jSONObject.putOpt("preorder_indicator", this.mPreorderIndicator);
            jSONObject.putOpt("preorder_date", this.mPreorderDate);
            jSONObject.putOpt("gift_card_amount", this.mGiftCardAmount);
            jSONObject.putOpt("gift_card_currency_code", this.mGiftCardCurrencyCode);
            jSONObject.putOpt("gift_card_count", this.mGiftCardCount);
            jSONObject.putOpt("account_age_indicator", this.mAccountAgeIndicator);
            jSONObject.putOpt("account_create_date", this.mAccountCreateDate);
            jSONObject.putOpt("account_change_indicator", this.mAccountChangeIndicator);
            jSONObject.putOpt("account_change_date", this.mAccountChangeDate);
            jSONObject.putOpt("account_pwd_change_indicator", this.mAccountPwdChangeIndicator);
            jSONObject.putOpt("account_pwd_change_date", this.mAccountPwdChangeDate);
            jSONObject.putOpt("shipping_address_usage_indicator", this.mShippingAddressUsageIndicator);
            jSONObject.putOpt("shipping_address_usage_date", this.mShippingAddressUsageDate);
            jSONObject.putOpt("transaction_count_day", this.mTransactionCountDay);
            jSONObject.putOpt("transaction_count_year", this.mTransactionCountYear);
            jSONObject.putOpt("add_card_attempts", this.mAddCardAttempts);
            jSONObject.putOpt("account_purchases", this.mAccountPurchases);
            jSONObject.putOpt("fraud_activity", this.mFraudActivity);
            jSONObject.putOpt("shipping_name_indicator", this.mShippingNameIndicator);
            jSONObject.putOpt("payment_account_indicator", this.mPaymentAccountIndicator);
            jSONObject.putOpt("payment_account_age", this.mPaymentAccountAge);
            jSONObject.putOpt("address_match", this.mAddressMatch);
            jSONObject.putOpt("account_id", this.mAccountId);
            jSONObject.putOpt("ip_address", this.mIpAddress);
            jSONObject.putOpt("order_description", this.mOrderDescription);
            jSONObject.putOpt("tax_amount", this.mTaxAmount);
            jSONObject.putOpt("user_agent", this.mUserAgent);
            jSONObject.putOpt("authentication_indicator", this.mAuthenticationIndicator);
            jSONObject.putOpt("installment", this.mInstallment);
            jSONObject.putOpt("purchase_date", this.mPurchaseDate);
            jSONObject.putOpt("recurring_end", this.mRecurringEnd);
            jSONObject.putOpt("recurring_frequency", this.mRecurringFrequency);
            jSONObject.putOpt("sdk_max_timeout", this.mSdkMaxTimeout);
            jSONObject.putOpt("work_phone_number", this.mWorkPhoneNumber);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
