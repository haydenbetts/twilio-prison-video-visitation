package com.stripe.android.model;

import com.stripe.android.model.wallets.Wallet;
import com.stripe.android.model.wallets.WalletFactory;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentMethod extends StripeJsonModel {
    private static final String FIELD_BILLING_DETAILS = "billing_details";
    private static final String FIELD_CARD = "card";
    private static final String FIELD_CARD_PRESENT = "card_present";
    private static final String FIELD_CREATED = "created";
    private static final String FIELD_CUSTOMER = "customer";
    private static final String FIELD_ID = "id";
    private static final String FIELD_IDEAL = "ideal";
    private static final String FIELD_LIVEMODE = "livemode";
    private static final String FIELD_METADATA = "metadata";
    private static final String FIELD_TYPE = "type";
    public final BillingDetails billingDetails;
    public final Card card;
    public final CardPresent cardPresent;
    public final Long created;
    public final String customerId;
    public final String id;
    public final Ideal ideal;
    public final boolean liveMode;
    public final Map<String, String> metadata;
    public final String type;

    private PaymentMethod(Builder builder) {
        this.id = builder.mId;
        this.liveMode = builder.mLiveMode;
        this.type = builder.mType;
        this.created = builder.mCreated;
        this.billingDetails = builder.mBillingDetails;
        this.customerId = builder.mCustomerId;
        this.card = builder.mCard;
        this.cardPresent = builder.mCardPresent;
        this.ideal = builder.mIdeal;
        this.metadata = builder.mMetadata;
    }

    public boolean isValid() {
        return this.type != null;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.id);
        hashMap.put(FIELD_CREATED, this.created);
        hashMap.put(FIELD_CUSTOMER, this.customerId);
        hashMap.put(FIELD_LIVEMODE, Boolean.valueOf(this.liveMode));
        hashMap.put("type", this.type);
        BillingDetails billingDetails2 = this.billingDetails;
        Map<String, Object> map = null;
        hashMap.put(FIELD_BILLING_DETAILS, billingDetails2 != null ? billingDetails2.toMap() : null);
        Card card2 = this.card;
        hashMap.put("card", card2 != null ? card2.toMap() : null);
        CardPresent cardPresent2 = this.cardPresent;
        hashMap.put(FIELD_CARD_PRESENT, cardPresent2 != null ? cardPresent2.toMap() : null);
        Ideal ideal2 = this.ideal;
        if (ideal2 != null) {
            map = ideal2.toMap();
        }
        hashMap.put("ideal", map);
        hashMap.put("metadata", this.metadata);
        return hashMap;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.id);
            jSONObject.put(FIELD_CREATED, this.created);
            jSONObject.put(FIELD_CUSTOMER, this.customerId);
            jSONObject.put(FIELD_LIVEMODE, this.liveMode);
            JSONObject jSONObject2 = null;
            jSONObject.put("metadata", this.metadata != null ? new JSONObject(this.metadata) : null);
            jSONObject.put("type", this.type);
            BillingDetails billingDetails2 = this.billingDetails;
            jSONObject.put(FIELD_BILLING_DETAILS, billingDetails2 != null ? billingDetails2.toJson() : null);
            Card card2 = this.card;
            jSONObject.put("card", card2 != null ? card2.toJson() : null);
            CardPresent cardPresent2 = this.cardPresent;
            jSONObject.put(FIELD_CARD_PRESENT, cardPresent2 != null ? cardPresent2.toJson() : null);
            Ideal ideal2 = this.ideal;
            if (ideal2 != null) {
                jSONObject2 = ideal2.toJson();
            }
            jSONObject.put("ideal", jSONObject2);
            return jSONObject;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static PaymentMethod fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static PaymentMethod fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = StripeJsonUtils.optString(jSONObject, "type");
        Builder metadata2 = new Builder().setId(StripeJsonUtils.optString(jSONObject, "id")).setType(optString).setCreated(StripeJsonUtils.optLong(jSONObject, FIELD_CREATED)).setBillingDetails(BillingDetails.fromJson(jSONObject.optJSONObject(FIELD_BILLING_DETAILS))).setCustomerId(StripeJsonUtils.optString(jSONObject, FIELD_CUSTOMER)).setLiveMode(Boolean.TRUE.equals(Boolean.valueOf(jSONObject.optBoolean(FIELD_LIVEMODE)))).setMetadata(StripeJsonUtils.optHash(jSONObject, "metadata"));
        if ("card".equals(optString)) {
            metadata2.setCard(Card.fromJson(jSONObject.optJSONObject("card")));
        } else if (FIELD_CARD_PRESENT.equals(optString)) {
            metadata2.setCardPresent(CardPresent.EMPTY);
        } else if ("ideal".equals(optString)) {
            metadata2.setIdeal(Ideal.fromJson(jSONObject.optJSONObject("ideal")));
        }
        return metadata2.build();
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof PaymentMethod) && typedEquals((PaymentMethod) obj));
    }

    private boolean typedEquals(PaymentMethod paymentMethod) {
        return ObjectUtils.equals(this.id, paymentMethod.id) && ObjectUtils.equals(this.created, paymentMethod.created) && this.liveMode == paymentMethod.liveMode && ObjectUtils.equals(this.type, paymentMethod.type) && ObjectUtils.equals(this.billingDetails, paymentMethod.billingDetails) && ObjectUtils.equals(this.card, paymentMethod.card) && ObjectUtils.equals(this.cardPresent, paymentMethod.cardPresent) && ObjectUtils.equals(this.ideal, paymentMethod.ideal) && ObjectUtils.equals(this.customerId, paymentMethod.customerId);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.id, this.created, Boolean.valueOf(this.liveMode), this.type, this.billingDetails, this.card, this.cardPresent, this.ideal, this.customerId);
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public BillingDetails mBillingDetails;
        /* access modifiers changed from: private */
        public Card mCard;
        /* access modifiers changed from: private */
        public CardPresent mCardPresent;
        /* access modifiers changed from: private */
        public Long mCreated;
        /* access modifiers changed from: private */
        public String mCustomerId;
        /* access modifiers changed from: private */
        public String mId;
        /* access modifiers changed from: private */
        public Ideal mIdeal;
        /* access modifiers changed from: private */
        public boolean mLiveMode;
        /* access modifiers changed from: private */
        public Map<String, String> mMetadata;
        /* access modifiers changed from: private */
        public String mType;

        public Builder setId(String str) {
            this.mId = str;
            return this;
        }

        public Builder setCreated(Long l) {
            this.mCreated = l;
            return this;
        }

        public Builder setLiveMode(boolean z) {
            this.mLiveMode = z;
            return this;
        }

        public Builder setMetadata(Map<String, String> map) {
            this.mMetadata = map;
            return this;
        }

        public Builder setType(String str) {
            this.mType = str;
            return this;
        }

        public Builder setBillingDetails(BillingDetails billingDetails) {
            this.mBillingDetails = billingDetails;
            return this;
        }

        public Builder setCard(Card card) {
            this.mCard = card;
            return this;
        }

        public Builder setCardPresent(CardPresent cardPresent) {
            this.mCardPresent = cardPresent;
            return this;
        }

        public Builder setCustomerId(String str) {
            this.mCustomerId = str;
            return this;
        }

        public Builder setIdeal(Ideal ideal) {
            this.mIdeal = ideal;
            return this;
        }

        public PaymentMethod build() {
            return new PaymentMethod(this);
        }
    }

    public static final class BillingDetails extends StripeJsonModel {
        private static final String FIELD_ADDRESS = "address";
        private static final String FIELD_EMAIL = "email";
        private static final String FIELD_NAME = "name";
        private static final String FIELD_PHONE = "phone";
        public final Address address;
        public final String email;
        public final String name;
        public final String phone;

        private BillingDetails(Builder builder) {
            this.address = builder.mAddress;
            this.email = builder.mEmail;
            this.name = builder.mName;
            this.phone = builder.mPhone;
        }

        public Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put(FIELD_ADDRESS, this.address.toMap());
            hashMap.put("email", this.email);
            hashMap.put("name", this.name);
            hashMap.put("phone", this.phone);
            return hashMap;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FIELD_ADDRESS, this.address.toJson());
                jSONObject.put("email", this.email);
                jSONObject.put("name", this.name);
                jSONObject.put("phone", this.phone);
                return jSONObject;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        public static BillingDetails fromJson(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new Builder().setAddress(Address.fromJson(jSONObject.optJSONObject(FIELD_ADDRESS))).setEmail(StripeJsonUtils.optString(jSONObject, "email")).setName(StripeJsonUtils.optString(jSONObject, "name")).setPhone(StripeJsonUtils.optString(jSONObject, "phone")).build();
        }

        public int hashCode() {
            return ObjectUtils.hash(this.address, this.email, this.name, this.phone);
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof BillingDetails) && typedEquals((BillingDetails) obj));
        }

        private boolean typedEquals(BillingDetails billingDetails) {
            return ObjectUtils.equals(this.address, billingDetails.address) && ObjectUtils.equals(this.email, billingDetails.email) && ObjectUtils.equals(this.name, billingDetails.name) && ObjectUtils.equals(this.phone, billingDetails.phone);
        }

        public static final class Builder {
            /* access modifiers changed from: private */
            public Address mAddress;
            /* access modifiers changed from: private */
            public String mEmail;
            /* access modifiers changed from: private */
            public String mName;
            /* access modifiers changed from: private */
            public String mPhone;

            public Builder setAddress(Address address) {
                this.mAddress = address;
                return this;
            }

            public Builder setEmail(String str) {
                this.mEmail = str;
                return this;
            }

            public Builder setName(String str) {
                this.mName = str;
                return this;
            }

            public Builder setPhone(String str) {
                this.mPhone = str;
                return this;
            }

            public BillingDetails build() {
                return new BillingDetails(this);
            }
        }
    }

    public static final class Card extends StripeJsonModel {
        private static final String FIELD_BRAND = "brand";
        private static final String FIELD_CHECKS = "checks";
        private static final String FIELD_COUNTRY = "country";
        private static final String FIELD_EXP_MONTH = "exp_month";
        private static final String FIELD_EXP_YEAR = "exp_year";
        private static final String FIELD_FUNDING = "funding";
        private static final String FIELD_LAST4 = "last4";
        private static final String FIELD_THREE_D_SECURE_USAGE = "three_d_secure_usage";
        private static final String FIELD_WALLET = "wallet";
        public final String brand;
        public final Checks checks;
        public final String country;
        public final Integer expiryMonth;
        public final Integer expiryYear;
        public final String funding;
        public final String last4;
        public final ThreeDSecureUsage threeDSecureUsage;
        public final Wallet wallet;

        private Card(Builder builder) {
            this.brand = builder.mBrand;
            this.checks = builder.checks;
            this.country = builder.mCountry;
            this.expiryMonth = builder.mExpiryMonth;
            this.expiryYear = builder.mExpiryYear;
            this.funding = builder.mFunding;
            this.last4 = builder.mLast4;
            this.threeDSecureUsage = builder.mThreeDSecureUsage;
            this.wallet = builder.mWallet;
        }

        public Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put(FIELD_BRAND, this.brand);
            Checks checks2 = this.checks;
            Map<String, Object> map = null;
            hashMap.put(FIELD_CHECKS, checks2 != null ? checks2.toMap() : null);
            hashMap.put("country", this.country);
            hashMap.put(FIELD_EXP_MONTH, this.expiryMonth);
            hashMap.put(FIELD_EXP_YEAR, this.expiryYear);
            hashMap.put(FIELD_FUNDING, this.funding);
            hashMap.put(FIELD_LAST4, this.last4);
            ThreeDSecureUsage threeDSecureUsage2 = this.threeDSecureUsage;
            if (threeDSecureUsage2 != null) {
                map = threeDSecureUsage2.toMap();
            }
            hashMap.put(FIELD_THREE_D_SECURE_USAGE, map);
            hashMap.put(FIELD_WALLET, this.wallet);
            return hashMap;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FIELD_BRAND, this.brand);
                Checks checks2 = this.checks;
                JSONObject jSONObject2 = null;
                jSONObject.put(FIELD_CHECKS, checks2 != null ? checks2.toJson() : null);
                jSONObject.put("country", this.country);
                jSONObject.put(FIELD_EXP_MONTH, this.expiryMonth);
                jSONObject.put(FIELD_EXP_YEAR, this.expiryYear);
                jSONObject.put(FIELD_FUNDING, this.funding);
                jSONObject.put(FIELD_LAST4, this.last4);
                ThreeDSecureUsage threeDSecureUsage2 = this.threeDSecureUsage;
                if (threeDSecureUsage2 != null) {
                    jSONObject2 = threeDSecureUsage2.toJson();
                }
                jSONObject.put(FIELD_THREE_D_SECURE_USAGE, jSONObject2);
                jSONObject.put(FIELD_WALLET, this.wallet);
            } catch (JSONException unused) {
            }
            return jSONObject;
        }

        public static Card fromJson(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new Builder().setBrand(StripeJsonUtils.optString(jSONObject, FIELD_BRAND)).setChecks(Checks.fromJson(jSONObject.optJSONObject(FIELD_CHECKS))).setCountry(StripeJsonUtils.optString(jSONObject, "country")).setExpiryMonth(StripeJsonUtils.optInteger(jSONObject, FIELD_EXP_MONTH)).setExpiryYear(StripeJsonUtils.optInteger(jSONObject, FIELD_EXP_YEAR)).setFunding(StripeJsonUtils.optString(jSONObject, FIELD_FUNDING)).setLast4(StripeJsonUtils.optString(jSONObject, FIELD_LAST4)).setThreeDSecureUsage(ThreeDSecureUsage.fromJson(jSONObject.optJSONObject(FIELD_THREE_D_SECURE_USAGE))).setWallet(new WalletFactory().create(jSONObject.optJSONObject(FIELD_WALLET))).build();
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof Card) && typedEquals((Card) obj));
        }

        private boolean typedEquals(Card card) {
            return ObjectUtils.equals(this.brand, card.brand) && ObjectUtils.equals(this.checks, card.checks) && ObjectUtils.equals(this.country, card.country) && ObjectUtils.equals(this.expiryMonth, card.expiryMonth) && ObjectUtils.equals(this.expiryYear, card.expiryYear) && ObjectUtils.equals(this.funding, card.funding) && ObjectUtils.equals(this.last4, card.last4) && ObjectUtils.equals(this.threeDSecureUsage, card.threeDSecureUsage) && ObjectUtils.equals(this.wallet, card.wallet);
        }

        public int hashCode() {
            return ObjectUtils.hash(this.brand, this.checks, this.country, this.expiryMonth, this.expiryYear, this.funding, this.last4, this.threeDSecureUsage, this.wallet);
        }

        public static final class Builder {
            /* access modifiers changed from: private */
            public Checks checks;
            /* access modifiers changed from: private */
            public String mBrand;
            /* access modifiers changed from: private */
            public String mCountry;
            /* access modifiers changed from: private */
            public Integer mExpiryMonth;
            /* access modifiers changed from: private */
            public Integer mExpiryYear;
            /* access modifiers changed from: private */
            public String mFunding;
            /* access modifiers changed from: private */
            public String mLast4;
            /* access modifiers changed from: private */
            public ThreeDSecureUsage mThreeDSecureUsage;
            /* access modifiers changed from: private */
            public Wallet mWallet;

            public Builder setBrand(String str) {
                this.mBrand = str;
                return this;
            }

            public Builder setChecks(Checks checks2) {
                this.checks = checks2;
                return this;
            }

            public Builder setCountry(String str) {
                this.mCountry = str;
                return this;
            }

            public Builder setExpiryMonth(Integer num) {
                this.mExpiryMonth = num;
                return this;
            }

            public Builder setExpiryYear(Integer num) {
                this.mExpiryYear = num;
                return this;
            }

            public Builder setFunding(String str) {
                this.mFunding = str;
                return this;
            }

            public Builder setLast4(String str) {
                this.mLast4 = str;
                return this;
            }

            public Builder setThreeDSecureUsage(ThreeDSecureUsage threeDSecureUsage) {
                this.mThreeDSecureUsage = threeDSecureUsage;
                return this;
            }

            public Builder setWallet(Wallet wallet) {
                this.mWallet = wallet;
                return this;
            }

            public Card build() {
                return new Card(this);
            }
        }

        public static final class Checks extends StripeJsonModel {
            private static final String FIELD_ADDRESS_LINE1_CHECK = "address_line1_check";
            private static final String FIELD_ADDRESS_POSTAL_CODE_CHECK = "address_postal_code_check";
            private static final String FIELD_CVC_CHECK = "cvc_check";
            public final String addressLine1Check;
            public final String addressPostalCodeCheck;
            public final String cvcCheck;

            private Checks(Builder builder) {
                this.addressLine1Check = builder.addressLine1Check;
                this.addressPostalCodeCheck = builder.addressPostalCodeCheck;
                this.cvcCheck = builder.cvcCheck;
            }

            public Map<String, Object> toMap() {
                HashMap hashMap = new HashMap();
                hashMap.put(FIELD_ADDRESS_LINE1_CHECK, this.addressLine1Check);
                hashMap.put(FIELD_ADDRESS_POSTAL_CODE_CHECK, this.addressPostalCodeCheck);
                hashMap.put(FIELD_CVC_CHECK, this.cvcCheck);
                return hashMap;
            }

            public JSONObject toJson() {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(FIELD_ADDRESS_LINE1_CHECK, this.addressLine1Check);
                    jSONObject.put(FIELD_ADDRESS_POSTAL_CODE_CHECK, this.addressPostalCodeCheck);
                    jSONObject.put(FIELD_CVC_CHECK, this.cvcCheck);
                } catch (JSONException unused) {
                }
                return jSONObject;
            }

            public static Checks fromJson(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return null;
                }
                return new Builder().setAddressLine1Check(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_LINE1_CHECK)).setAddressPostalCodeCheck(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_POSTAL_CODE_CHECK)).setCvcCheck(StripeJsonUtils.optString(jSONObject, FIELD_CVC_CHECK)).build();
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof Checks) && typedEquals((Checks) obj));
            }

            private boolean typedEquals(Checks checks) {
                return ObjectUtils.equals(this.addressLine1Check, checks.addressLine1Check) && ObjectUtils.equals(this.addressPostalCodeCheck, checks.addressPostalCodeCheck) && ObjectUtils.equals(this.cvcCheck, checks.cvcCheck);
            }

            public int hashCode() {
                return ObjectUtils.hash(this.addressLine1Check, this.addressPostalCodeCheck, this.cvcCheck);
            }

            public static final class Builder {
                /* access modifiers changed from: private */
                public String addressLine1Check;
                /* access modifiers changed from: private */
                public String addressPostalCodeCheck;
                /* access modifiers changed from: private */
                public String cvcCheck;

                public Builder setAddressLine1Check(String str) {
                    this.addressLine1Check = str;
                    return this;
                }

                public Builder setAddressPostalCodeCheck(String str) {
                    this.addressPostalCodeCheck = str;
                    return this;
                }

                public Builder setCvcCheck(String str) {
                    this.cvcCheck = str;
                    return this;
                }

                public Checks build() {
                    return new Checks(this);
                }
            }
        }

        public static final class ThreeDSecureUsage extends StripeJsonModel {
            private static final String FIELD_IS_SUPPORTED = "supported";
            public final boolean isSupported;

            private ThreeDSecureUsage(Builder builder) {
                this.isSupported = builder.mIsSupported;
            }

            public Map<String, Object> toMap() {
                HashMap hashMap = new HashMap();
                hashMap.put(FIELD_IS_SUPPORTED, Boolean.valueOf(this.isSupported));
                return hashMap;
            }

            public JSONObject toJson() {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put(FIELD_IS_SUPPORTED, this.isSupported);
                } catch (JSONException unused) {
                }
                return jSONObject;
            }

            public static ThreeDSecureUsage fromJson(JSONObject jSONObject) {
                if (jSONObject == null) {
                    return null;
                }
                return new Builder().setSupported(Boolean.TRUE.equals(StripeJsonUtils.optBoolean(jSONObject, FIELD_IS_SUPPORTED))).build();
            }

            public int hashCode() {
                return ObjectUtils.hash(Boolean.valueOf(this.isSupported));
            }

            public boolean equals(Object obj) {
                return this == obj || ((obj instanceof ThreeDSecureUsage) && typedEquals((ThreeDSecureUsage) obj));
            }

            private boolean typedEquals(ThreeDSecureUsage threeDSecureUsage) {
                return this.isSupported == threeDSecureUsage.isSupported;
            }

            public static final class Builder {
                /* access modifiers changed from: private */
                public boolean mIsSupported;

                public Builder setSupported(boolean z) {
                    this.mIsSupported = z;
                    return this;
                }

                public ThreeDSecureUsage build() {
                    return new ThreeDSecureUsage(this);
                }
            }
        }
    }

    public static final class CardPresent extends StripeJsonModel {
        public static final CardPresent EMPTY = new CardPresent();

        private CardPresent() {
        }

        public Map<String, Object> toMap() {
            return new HashMap();
        }

        public JSONObject toJson() {
            return new JSONObject();
        }
    }

    public static final class Ideal extends StripeJsonModel {
        private static final String FIELD_BANK = "bank";
        private static final String FIELD_BIC = "bic";
        public final String bank;
        public final String bankIdentifierCode;

        private Ideal(Builder builder) {
            this.bank = builder.mBank;
            this.bankIdentifierCode = builder.mBankIdentifierCode;
        }

        public Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put(FIELD_BANK, this.bank);
            hashMap.put(FIELD_BIC, this.bankIdentifierCode);
            return hashMap;
        }

        public JSONObject toJson() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(FIELD_BANK, this.bank);
                jSONObject.put(FIELD_BIC, this.bankIdentifierCode);
                return jSONObject;
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        public static Ideal fromJson(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return new Builder().setBank(StripeJsonUtils.optString(jSONObject, FIELD_BANK)).setBankIdentifierCode(StripeJsonUtils.optString(jSONObject, FIELD_BIC)).build();
        }

        public int hashCode() {
            return ObjectUtils.hash(this.bank, this.bankIdentifierCode);
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof Ideal) && typedEquals((Ideal) obj));
        }

        private boolean typedEquals(Ideal ideal) {
            return ObjectUtils.equals(this.bank, ideal.bank) && ObjectUtils.equals(this.bankIdentifierCode, ideal.bankIdentifierCode);
        }

        public static final class Builder {
            /* access modifiers changed from: private */
            public String mBank;
            /* access modifiers changed from: private */
            public String mBankIdentifierCode;

            public Builder setBank(String str) {
                this.mBank = str;
                return this;
            }

            public Builder setBankIdentifierCode(String str) {
                this.mBankIdentifierCode = str;
                return this;
            }

            public Ideal build() {
                return new Ideal(this);
            }
        }
    }
}
