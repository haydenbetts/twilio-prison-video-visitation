package com.stripe.android.model.wallets;

import com.stripe.android.model.StripeJsonModel;
import com.stripe.android.model.StripeJsonUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class Wallet extends StripeJsonModel {
    static final String FIELD_DYANMIC_LAST4 = "dynamic_last4";
    static final String FIELD_TYPE = "type";
    private final String dynamicLast4;
    private final Type walletType;

    /* access modifiers changed from: package-private */
    public abstract JSONObject getWalletTypeJson();

    /* access modifiers changed from: package-private */
    public abstract Map<String, Object> getWalletTypeMap();

    Wallet(Type type, Builder builder) {
        this.walletType = type;
        this.dynamicLast4 = builder.mDynamicLast4;
    }

    public final Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", this.walletType.code);
        hashMap.put(FIELD_DYANMIC_LAST4, this.dynamicLast4);
        hashMap.put(this.walletType.code, getWalletTypeMap());
        return hashMap;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", this.walletType.code);
            jSONObject.put(FIELD_DYANMIC_LAST4, this.dynamicLast4);
            jSONObject.put(this.walletType.code, getWalletTypeJson());
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    static abstract class Builder<W extends Wallet> {
        /* access modifiers changed from: private */
        public String mDynamicLast4;

        /* access modifiers changed from: package-private */
        public abstract W build();

        Builder() {
        }

        public Builder setDynamicLast4(String str) {
            this.mDynamicLast4 = str;
            return this;
        }
    }

    enum Type {
        AmexExpressCheckout("amex_express_checkout"),
        ApplePay("apple_pay"),
        GooglePay("google_pay"),
        Masterpass("master_pass"),
        SamsungPay("samsung_pay"),
        VisaCheckout("visa_checkout");
        
        public final String code;

        private Type(String str) {
            this.code = str;
        }

        static Type fromCode(String str) {
            for (Type type : values()) {
                if (type.code.equals(str)) {
                    return type;
                }
            }
            return null;
        }
    }

    public static class Address extends StripeJsonModel {
        static final String FIELD_CITY = "city";
        static final String FIELD_COUNTRY = "country";
        static final String FIELD_LINE1 = "line1";
        static final String FIELD_LINE2 = "line2";
        static final String FIELD_POSTAL_CODE = "postal_code";
        static final String FIELD_STATE = "state";
        public final String city;
        public final String country;
        public final String line1;
        public final String line2;
        public final String postalCode;
        public final String state;

        private Address(Builder builder) {
            this.city = builder.mCity;
            this.country = builder.mCountry;
            this.line1 = builder.mLine1;
            this.line2 = builder.mLine2;
            this.postalCode = builder.mPostalCode;
            this.state = builder.mState;
        }

        public Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put("city", this.city);
            hashMap.put("country", this.country);
            hashMap.put("line1", this.line1);
            hashMap.put("line2", this.line2);
            hashMap.put("postal_code", this.postalCode);
            hashMap.put("state", this.state);
            return hashMap;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("city", this.city);
                jSONObject.put("country", this.country);
                jSONObject.put("line1", this.line1);
                jSONObject.put("line2", this.line2);
                jSONObject.put("postal_code", this.postalCode);
                jSONObject.put("state", this.state);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        static Address fromJson(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new Builder().setCity(StripeJsonUtils.optString(jSONObject, "city")).setCountry(StripeJsonUtils.optString(jSONObject, "country")).setLine1(StripeJsonUtils.optString(jSONObject, "line1")).setLine2(StripeJsonUtils.optString(jSONObject, "line2")).setPostalCode(StripeJsonUtils.optString(jSONObject, "postal_code")).setState(StripeJsonUtils.optString(jSONObject, "state")).build();
        }

        public int hashCode() {
            return ObjectUtils.hash(this.city, this.country, this.line1, this.line2, this.postalCode, this.state);
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof Address) && typedEquals((Address) obj));
        }

        private boolean typedEquals(Address address) {
            return ObjectUtils.equals(this.city, address.city) && ObjectUtils.equals(this.country, address.country) && ObjectUtils.equals(this.line1, address.line1) && ObjectUtils.equals(this.line2, address.line2) && ObjectUtils.equals(this.postalCode, address.postalCode) && ObjectUtils.equals(this.state, address.state);
        }

        static final class Builder {
            /* access modifiers changed from: private */
            public String mCity;
            /* access modifiers changed from: private */
            public String mCountry;
            /* access modifiers changed from: private */
            public String mLine1;
            /* access modifiers changed from: private */
            public String mLine2;
            /* access modifiers changed from: private */
            public String mPostalCode;
            /* access modifiers changed from: private */
            public String mState;

            Builder() {
            }

            public Builder setCity(String str) {
                this.mCity = str;
                return this;
            }

            public Builder setCountry(String str) {
                this.mCountry = str;
                return this;
            }

            public Builder setLine1(String str) {
                this.mLine1 = str;
                return this;
            }

            public Builder setLine2(String str) {
                this.mLine2 = str;
                return this;
            }

            public Builder setPostalCode(String str) {
                this.mPostalCode = str;
                return this;
            }

            public Builder setState(String str) {
                this.mState = str;
                return this;
            }

            public Address build() {
                return new Address(this);
            }
        }
    }
}
