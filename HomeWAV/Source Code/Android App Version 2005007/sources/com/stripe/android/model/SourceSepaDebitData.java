package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.model.StripeSourceTypeModel;
import com.stripe.android.utils.ObjectUtils;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class SourceSepaDebitData extends StripeSourceTypeModel {
    private static final String FIELD_BANK_CODE = "bank_code";
    private static final String FIELD_BRANCH_CODE = "branch_code";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_FINGERPRINT = "fingerprint";
    private static final String FIELD_LAST4 = "last4";
    private static final String FIELD_MANDATE_REFERENCE = "mandate_reference";
    private static final String FIELD_MANDATE_URL = "mandate_url";
    private static final Set<String> STANDARD_FIELDS = new HashSet(Arrays.asList(new String[]{FIELD_BANK_CODE, FIELD_BRANCH_CODE, "country", FIELD_FINGERPRINT, FIELD_LAST4, FIELD_MANDATE_REFERENCE, FIELD_MANDATE_URL}));
    private final String mBankCode;
    private final String mBranchCode;
    private final String mCountry;
    private final String mFingerPrint;
    private final String mLast4;
    private final String mMandateReference;
    private final String mMandateUrl;

    private SourceSepaDebitData(Builder builder) {
        super(builder);
        this.mBankCode = builder.mBankCode;
        this.mBranchCode = builder.mBranchCode;
        this.mCountry = builder.mCountry;
        this.mFingerPrint = builder.mFingerPrint;
        this.mLast4 = builder.mLast4;
        this.mMandateReference = builder.mMandateReference;
        this.mMandateUrl = builder.mMandateUrl;
    }

    public static SourceSepaDebitData fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        Builder mandateUrl = new Builder().setBankCode(StripeJsonUtils.optString(jSONObject, FIELD_BANK_CODE)).setBranchCode(StripeJsonUtils.optString(jSONObject, FIELD_BRANCH_CODE)).setCountry(StripeJsonUtils.optString(jSONObject, "country")).setFingerPrint(StripeJsonUtils.optString(jSONObject, FIELD_FINGERPRINT)).setLast4(StripeJsonUtils.optString(jSONObject, FIELD_LAST4)).setMandateReference(StripeJsonUtils.optString(jSONObject, FIELD_MANDATE_REFERENCE)).setMandateUrl(StripeJsonUtils.optString(jSONObject, FIELD_MANDATE_URL));
        Map<String, Object> jsonObjectToMapWithoutKeys = jsonObjectToMapWithoutKeys(jSONObject, STANDARD_FIELDS);
        if (jsonObjectToMapWithoutKeys != null) {
            mandateUrl.setAdditionalFields(jsonObjectToMapWithoutKeys);
        }
        return mandateUrl.build();
    }

    public String getBankCode() {
        return this.mBankCode;
    }

    public String getBranchCode() {
        return this.mBranchCode;
    }

    public String getCountry() {
        return this.mCountry;
    }

    public String getFingerPrint() {
        return this.mFingerPrint;
    }

    public String getLast4() {
        return this.mLast4;
    }

    public String getMandateReference() {
        return this.mMandateReference;
    }

    public String getMandateUrl() {
        return this.mMandateUrl;
    }

    public JSONObject toJson() {
        JSONObject json = super.toJson();
        StripeJsonUtils.putStringIfNotNull(json, FIELD_BANK_CODE, this.mBankCode);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_BRANCH_CODE, this.mBranchCode);
        StripeJsonUtils.putStringIfNotNull(json, "country", this.mCountry);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_FINGERPRINT, this.mFingerPrint);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_LAST4, this.mLast4);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_MANDATE_REFERENCE, this.mMandateReference);
        StripeJsonUtils.putStringIfNotNull(json, FIELD_MANDATE_URL, this.mMandateUrl);
        return json;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap(super.toMap());
        hashMap.put(FIELD_BANK_CODE, this.mBankCode);
        hashMap.put(FIELD_BRANCH_CODE, this.mBranchCode);
        hashMap.put("country", this.mCountry);
        hashMap.put(FIELD_FINGERPRINT, this.mFingerPrint);
        hashMap.put(FIELD_LAST4, this.mLast4);
        hashMap.put(FIELD_MANDATE_REFERENCE, this.mMandateReference);
        hashMap.put(FIELD_MANDATE_URL, this.mMandateUrl);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    static SourceSepaDebitData fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SourceSepaDebitData) && typedEquals((SourceSepaDebitData) obj));
    }

    private boolean typedEquals(SourceSepaDebitData sourceSepaDebitData) {
        return super.typedEquals(sourceSepaDebitData) && ObjectUtils.equals(this.mBankCode, sourceSepaDebitData.mBankCode) && ObjectUtils.equals(this.mBranchCode, sourceSepaDebitData.mBranchCode) && ObjectUtils.equals(this.mCountry, sourceSepaDebitData.mCountry) && ObjectUtils.equals(this.mFingerPrint, sourceSepaDebitData.mFingerPrint) && ObjectUtils.equals(this.mLast4, sourceSepaDebitData.mLast4) && ObjectUtils.equals(this.mMandateReference, sourceSepaDebitData.mMandateReference) && ObjectUtils.equals(this.mMandateUrl, sourceSepaDebitData.mMandateUrl);
    }

    public int hashCode() {
        return ObjectUtils.hash(Integer.valueOf(super.hashCode()), this.mBankCode, this.mBranchCode, this.mCountry, this.mFingerPrint, this.mLast4, this.mMandateReference, this.mMandateUrl);
    }

    public static final class Builder extends StripeSourceTypeModel.BaseBuilder {
        /* access modifiers changed from: private */
        public String mBankCode;
        /* access modifiers changed from: private */
        public String mBranchCode;
        /* access modifiers changed from: private */
        public String mCountry;
        /* access modifiers changed from: private */
        public String mFingerPrint;
        /* access modifiers changed from: private */
        public String mLast4;
        /* access modifiers changed from: private */
        public String mMandateReference;
        /* access modifiers changed from: private */
        public String mMandateUrl;

        /* access modifiers changed from: package-private */
        public Builder setBankCode(String str) {
            this.mBankCode = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setBranchCode(String str) {
            this.mBranchCode = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setCountry(String str) {
            this.mCountry = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setFingerPrint(String str) {
            this.mFingerPrint = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setLast4(String str) {
            this.mLast4 = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setMandateReference(String str) {
            this.mMandateReference = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setMandateUrl(String str) {
            this.mMandateUrl = str;
            return this;
        }

        public SourceSepaDebitData build() {
            return new SourceSepaDebitData(this);
        }
    }
}
