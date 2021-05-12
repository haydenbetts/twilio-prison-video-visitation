package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.model.StripeSourceTypeModel;
import com.stripe.android.utils.ObjectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class SourceCardData extends StripeSourceTypeModel {
    private static final String FIELD_ADDRESS_LINE1_CHECK = "address_line1_check";
    private static final String FIELD_ADDRESS_ZIP_CHECK = "address_zip_check";
    private static final String FIELD_BRAND = "brand";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_CVC_CHECK = "cvc_check";
    private static final String FIELD_DYNAMIC_LAST4 = "dynamic_last4";
    private static final String FIELD_EXP_MONTH = "exp_month";
    private static final String FIELD_EXP_YEAR = "exp_year";
    private static final String FIELD_FUNDING = "funding";
    private static final String FIELD_LAST4 = "last4";
    private static final String FIELD_THREE_D_SECURE = "three_d_secure";
    private static final String FIELD_TOKENIZATION_METHOD = "tokenization_method";
    public static final String NOT_SUPPORTED = "not_supported";
    public static final String OPTIONAL = "optional";
    public static final String RECOMMENDED = "recommended";
    public static final String REQUIRED = "required";
    private static final Set<String> STANDARD_FIELDS = new HashSet(Arrays.asList(new String[]{FIELD_ADDRESS_LINE1_CHECK, FIELD_ADDRESS_ZIP_CHECK, FIELD_BRAND, "country", FIELD_CVC_CHECK, FIELD_DYNAMIC_LAST4, FIELD_EXP_MONTH, FIELD_EXP_YEAR, FIELD_FUNDING, FIELD_LAST4, "three_d_secure", FIELD_TOKENIZATION_METHOD}));
    public static final String UNKNOWN = "unknown";
    private final String mAddressLine1Check;
    private final String mAddressZipCheck;
    private final String mBrand;
    private final String mCountry;
    private final String mCvcCheck;
    private final String mDynamicLast4;
    private final Integer mExpiryMonth;
    private final Integer mExpiryYear;
    private final String mFunding;
    private final String mLast4;
    private final String mThreeDSecureStatus;
    private final String mTokenizationMethod;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ThreeDSecureStatus {
    }

    private SourceCardData(Builder builder) {
        super(builder);
        this.mAddressLine1Check = builder.mAddressLine1Check;
        this.mAddressZipCheck = builder.mAddressZipCheck;
        this.mBrand = builder.mBrand;
        this.mCountry = builder.mCountry;
        this.mCvcCheck = builder.mCvcCheck;
        this.mDynamicLast4 = builder.mDynamicLast4;
        this.mExpiryMonth = builder.mExpiryMonth;
        this.mExpiryYear = builder.mExpiryYear;
        this.mFunding = builder.mFunding;
        this.mLast4 = builder.mLast4;
        this.mThreeDSecureStatus = builder.mThreeDSecureStatus;
        this.mTokenizationMethod = builder.mTokenizationMethod;
    }

    public String getAddressLine1Check() {
        return this.mAddressLine1Check;
    }

    public String getAddressZipCheck() {
        return this.mAddressZipCheck;
    }

    public String getBrand() {
        return this.mBrand;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String getCvcCheck() {
        return this.mCvcCheck;
    }

    public String getDynamicLast4() {
        return this.mDynamicLast4;
    }

    public Integer getExpiryMonth() {
        return this.mExpiryMonth;
    }

    public Integer getExpiryYear() {
        return this.mExpiryYear;
    }

    public String getFunding() {
        return this.mFunding;
    }

    public String getLast4() {
        return this.mLast4;
    }

    public String getThreeDSecureStatus() {
        return this.mThreeDSecureStatus;
    }

    public String getTokenizationMethod() {
        return this.mTokenizationMethod;
    }

    public JSONObject toJson() {
        JSONObject json = super.toJson();
        StripeJsonUtils.putStringIfNotNull(json, FIELD_ADDRESS_LINE1_CHECK, this.mAddressLine1Check);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_ADDRESS_ZIP_CHECK, this.mAddressZipCheck);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_BRAND, this.mBrand);
        StripeJsonUtils.putStringIfNotNull(json, "country", this.mCountry);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_DYNAMIC_LAST4, this.mDynamicLast4);
        StripeJsonUtils.putIntegerIfNotNull(json, FIELD_EXP_MONTH, this.mExpiryMonth);
        StripeJsonUtils.putIntegerIfNotNull(json, FIELD_EXP_YEAR, this.mExpiryYear);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_FUNDING, this.mFunding);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_LAST4, this.mLast4);
        StripeJsonUtils.putStringIfNotNull(json, "three_d_secure", this.mThreeDSecureStatus);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_TOKENIZATION_METHOD, this.mTokenizationMethod);
        return json;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap(super.toMap());
        hashMap.put(FIELD_ADDRESS_LINE1_CHECK, this.mAddressLine1Check);
        hashMap.put(FIELD_ADDRESS_ZIP_CHECK, this.mAddressZipCheck);
        hashMap.put(FIELD_BRAND, this.mBrand);
        hashMap.put("country", this.mCountry);
        hashMap.put(FIELD_DYNAMIC_LAST4, this.mDynamicLast4);
        hashMap.put(FIELD_EXP_MONTH, this.mExpiryMonth);
        hashMap.put(FIELD_EXP_YEAR, this.mExpiryYear);
        hashMap.put(FIELD_FUNDING, this.mFunding);
        hashMap.put(FIELD_LAST4, this.mLast4);
        hashMap.put("three_d_secure", this.mThreeDSecureStatus);
        hashMap.put(FIELD_TOKENIZATION_METHOD, this.mTokenizationMethod);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    static SourceCardData fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Builder access$2400 = new Builder().setAddressLine1Check(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_LINE1_CHECK)).setAddressZipCheck(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_ZIP_CHECK)).setBrand(Card.asCardBrand(StripeJsonUtils.optString(jSONObject, FIELD_BRAND))).setCountry(StripeJsonUtils.optString(jSONObject, "country")).setCvcCheck(StripeJsonUtils.optString(jSONObject, FIELD_CVC_CHECK)).setDynamicLast4(StripeJsonUtils.optString(jSONObject, FIELD_DYNAMIC_LAST4)).setExpiryMonth(StripeJsonUtils.optInteger(jSONObject, FIELD_EXP_MONTH)).setExpiryYear(StripeJsonUtils.optInteger(jSONObject, FIELD_EXP_YEAR)).setFunding(Card.asFundingType(StripeJsonUtils.optString(jSONObject, FIELD_FUNDING))).setLast4(StripeJsonUtils.optString(jSONObject, FIELD_LAST4)).setThreeDSecureStatus(asThreeDSecureStatus(StripeJsonUtils.optString(jSONObject, "three_d_secure"))).setTokenizationMethod(StripeJsonUtils.optString(jSONObject, FIELD_TOKENIZATION_METHOD));
        Map<String, Object> jsonObjectToMapWithoutKeys = jsonObjectToMapWithoutKeys(jSONObject, STANDARD_FIELDS);
        if (jsonObjectToMapWithoutKeys != null) {
            access$2400.setAdditionalFields(jsonObjectToMapWithoutKeys);
        }
        return access$2400.build();
    }

    static SourceCardData fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    static String asThreeDSecureStatus(String str) {
        if (StripeJsonUtils.nullIfNullOrEmpty(str) == null) {
            return null;
        }
        if (REQUIRED.equalsIgnoreCase(str)) {
            return REQUIRED;
        }
        if (OPTIONAL.equalsIgnoreCase(str)) {
            return OPTIONAL;
        }
        if (NOT_SUPPORTED.equalsIgnoreCase(str)) {
            return NOT_SUPPORTED;
        }
        if (RECOMMENDED.equalsIgnoreCase(str)) {
            return RECOMMENDED;
        }
        return "unknown";
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SourceCardData) && typedEquals((SourceCardData) obj));
    }

    /* access modifiers changed from: package-private */
    public boolean typedEquals(SourceCardData sourceCardData) {
        return super.typedEquals(sourceCardData) && ObjectUtils.equals(this.mAddressLine1Check, sourceCardData.mAddressLine1Check) && ObjectUtils.equals(this.mAddressZipCheck, sourceCardData.mAddressZipCheck) && ObjectUtils.equals(this.mBrand, sourceCardData.mBrand) && ObjectUtils.equals(this.mCountry, sourceCardData.mCountry) && ObjectUtils.equals(this.mCvcCheck, sourceCardData.mCvcCheck) && ObjectUtils.equals(this.mDynamicLast4, sourceCardData.mDynamicLast4) && ObjectUtils.equals(this.mExpiryMonth, sourceCardData.mExpiryMonth) && ObjectUtils.equals(this.mExpiryYear, sourceCardData.mExpiryYear) && ObjectUtils.equals(this.mFunding, sourceCardData.mFunding) && ObjectUtils.equals(this.mLast4, sourceCardData.mLast4) && ObjectUtils.equals(this.mThreeDSecureStatus, sourceCardData.mThreeDSecureStatus) && ObjectUtils.equals(this.mTokenizationMethod, sourceCardData.mTokenizationMethod);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mAddressLine1Check, this.mAddressZipCheck, this.mBrand, this.mCountry, this.mCvcCheck, this.mDynamicLast4, this.mExpiryMonth, this.mExpiryYear, this.mFunding, this.mLast4, this.mThreeDSecureStatus, this.mTokenizationMethod);
    }

    private static final class Builder extends StripeSourceTypeModel.BaseBuilder {
        /* access modifiers changed from: private */
        public String mAddressLine1Check;
        /* access modifiers changed from: private */
        public String mAddressZipCheck;
        /* access modifiers changed from: private */
        public String mBrand;
        /* access modifiers changed from: private */
        public String mCountry;
        /* access modifiers changed from: private */
        public String mCvcCheck;
        /* access modifiers changed from: private */
        public String mDynamicLast4;
        /* access modifiers changed from: private */
        public Integer mExpiryMonth;
        /* access modifiers changed from: private */
        public Integer mExpiryYear;
        /* access modifiers changed from: private */
        public String mFunding;
        /* access modifiers changed from: private */
        public String mLast4;
        /* access modifiers changed from: private */
        public String mThreeDSecureStatus;
        /* access modifiers changed from: private */
        public String mTokenizationMethod;

        private Builder() {
        }

        /* access modifiers changed from: private */
        public Builder setAddressLine1Check(String str) {
            this.mAddressLine1Check = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setAddressZipCheck(String str) {
            this.mAddressZipCheck = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setBrand(String str) {
            this.mBrand = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setCountry(String str) {
            this.mCountry = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setCvcCheck(String str) {
            this.mCvcCheck = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setDynamicLast4(String str) {
            this.mDynamicLast4 = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setExpiryMonth(Integer num) {
            this.mExpiryMonth = num;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setExpiryYear(Integer num) {
            this.mExpiryYear = num;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setFunding(String str) {
            this.mFunding = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setLast4(String str) {
            this.mLast4 = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setThreeDSecureStatus(String str) {
            this.mThreeDSecureStatus = str;
            return this;
        }

        /* access modifiers changed from: private */
        public Builder setTokenizationMethod(String str) {
            this.mTokenizationMethod = str;
            return this;
        }

        public SourceCardData build() {
            return new SourceCardData(this);
        }
    }
}
