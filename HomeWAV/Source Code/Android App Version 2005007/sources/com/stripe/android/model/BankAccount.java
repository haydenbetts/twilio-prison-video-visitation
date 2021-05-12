package com.stripe.android.model;

import com.stripe.android.utils.ObjectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONException;
import org.json.JSONObject;

public class BankAccount {
    private static final String FIELD_ACCOUNT_HOLDER_NAME = "account_holder_name";
    private static final String FIELD_ACCOUNT_HOLDER_TYPE = "account_holder_type";
    private static final String FIELD_BANK_NAME = "bank_name";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_CURRENCY = "currency";
    private static final String FIELD_FINGERPRINT = "fingerprint";
    private static final String FIELD_LAST4 = "last4";
    private static final String FIELD_ROUTING_NUMBER = "routing_number";
    public static final String TYPE_COMPANY = "company";
    public static final String TYPE_INDIVIDUAL = "individual";
    private final String mAccountHolderName;
    private final String mAccountHolderType;
    private final String mAccountNumber;
    private final String mBankName;
    private final String mCountryCode;
    private final String mCurrency;
    private final String mFingerprint;
    private final String mLast4;
    private final String mRoutingNumber;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BankAccountType {
    }

    public BankAccount(String str, String str2, String str3, String str4) {
        this(str, (String) null, (String) null, (String) null, str2, str3, (String) null, (String) null, str4);
    }

    public BankAccount(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this((String) null, str, str2, str3, str4, str5, str6, str7, str8);
    }

    public BankAccount(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.mAccountNumber = str;
        this.mAccountHolderName = str2;
        this.mAccountHolderType = str3;
        this.mBankName = str4;
        this.mCountryCode = str5;
        this.mCurrency = str6;
        this.mFingerprint = str7;
        this.mLast4 = str8;
        this.mRoutingNumber = str9;
    }

    public String getAccountNumber() {
        return this.mAccountNumber;
    }

    public String getAccountHolderName() {
        return this.mAccountHolderName;
    }

    public String getAccountHolderType() {
        return this.mAccountHolderType;
    }

    public String getBankName() {
        return this.mBankName;
    }

    public String getCountryCode() {
        return this.mCountryCode;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public String getFingerprint() {
        return this.mFingerprint;
    }

    public String getLast4() {
        return this.mLast4;
    }

    public String getRoutingNumber() {
        return this.mRoutingNumber;
    }

    public static String asBankAccountType(String str) {
        if ("company".equals(str)) {
            return "company";
        }
        if (TYPE_INDIVIDUAL.equals(str)) {
            return TYPE_INDIVIDUAL;
        }
        return null;
    }

    public static BankAccount fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static BankAccount fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new BankAccount(StripeJsonUtils.optString(jSONObject, FIELD_ACCOUNT_HOLDER_NAME), asBankAccountType(StripeJsonUtils.optString(jSONObject, FIELD_ACCOUNT_HOLDER_TYPE)), StripeJsonUtils.optString(jSONObject, FIELD_BANK_NAME), StripeJsonUtils.optCountryCode(jSONObject, "country"), StripeJsonUtils.optCurrency(jSONObject, FIELD_CURRENCY), StripeJsonUtils.optString(jSONObject, FIELD_FINGERPRINT), StripeJsonUtils.optString(jSONObject, FIELD_LAST4), StripeJsonUtils.optString(jSONObject, FIELD_ROUTING_NUMBER));
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mAccountHolderName, this.mAccountHolderType, this.mAccountNumber, this.mBankName, this.mCountryCode, this.mCurrency, this.mFingerprint, this.mLast4, this.mRoutingNumber);
    }

    public boolean equals(Object obj) {
        return super.equals(obj) || ((obj instanceof BankAccount) && typedEquals((BankAccount) obj));
    }

    private boolean typedEquals(BankAccount bankAccount) {
        return ObjectUtils.equals(this.mAccountHolderName, bankAccount.mAccountHolderName) && ObjectUtils.equals(this.mAccountHolderType, bankAccount.mAccountHolderType) && ObjectUtils.equals(this.mAccountNumber, bankAccount.mAccountNumber) && ObjectUtils.equals(this.mBankName, bankAccount.mBankName) && ObjectUtils.equals(this.mCountryCode, bankAccount.mCountryCode) && ObjectUtils.equals(this.mCurrency, bankAccount.mCurrency) && ObjectUtils.equals(this.mFingerprint, bankAccount.mFingerprint) && ObjectUtils.equals(this.mLast4, bankAccount.mLast4) && ObjectUtils.equals(this.mRoutingNumber, bankAccount.mRoutingNumber);
    }
}
