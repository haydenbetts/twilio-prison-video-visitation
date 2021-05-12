package com.stripe.android.model;

import com.stripe.android.model.PaymentMethod;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;

public class PaymentMethodCreateParams {
    private static final String FIELD_BILLING_DETAILS = "billing_details";
    private static final String FIELD_CARD = "card";
    private static final String FIELD_IDEAL = "ideal";
    private static final String FIELD_METADATA = "metadata";
    private static final String FIELD_TYPE = "type";
    private final PaymentMethod.BillingDetails billingDetails;
    private final Card card;
    private final Ideal ideal;
    private final Map<String, String> metadata;
    private final Type type;

    public static PaymentMethodCreateParams create(Card card2, PaymentMethod.BillingDetails billingDetails2) {
        return new PaymentMethodCreateParams(card2, billingDetails2, (Map<String, String>) null);
    }

    public static PaymentMethodCreateParams create(Card card2, PaymentMethod.BillingDetails billingDetails2, Map<String, String> map) {
        return new PaymentMethodCreateParams(card2, billingDetails2, map);
    }

    public static PaymentMethodCreateParams create(Ideal ideal2, PaymentMethod.BillingDetails billingDetails2) {
        return new PaymentMethodCreateParams(ideal2, billingDetails2, (Map<String, String>) null);
    }

    public static PaymentMethodCreateParams create(Ideal ideal2, PaymentMethod.BillingDetails billingDetails2, Map<String, String> map) {
        return new PaymentMethodCreateParams(ideal2, billingDetails2, map);
    }

    private PaymentMethodCreateParams(Card card2, PaymentMethod.BillingDetails billingDetails2, Map<String, String> map) {
        this.type = Type.Card;
        this.card = card2;
        this.ideal = null;
        this.billingDetails = billingDetails2;
        this.metadata = map;
    }

    private PaymentMethodCreateParams(Ideal ideal2, PaymentMethod.BillingDetails billingDetails2, Map<String, String> map) {
        this.type = Type.Ideal;
        this.card = null;
        this.ideal = ideal2;
        this.billingDetails = billingDetails2;
        this.metadata = map;
    }

    public Map<String, Object> toParamMap() {
        Ideal ideal2;
        Card card2;
        HashMap hashMap = new HashMap();
        hashMap.put("type", this.type.mCode);
        if (this.type == Type.Card && (card2 = this.card) != null) {
            hashMap.put("card", card2.toMap());
        } else if (this.type == Type.Ideal && (ideal2 = this.ideal) != null) {
            hashMap.put("ideal", ideal2.toMap());
        }
        PaymentMethod.BillingDetails billingDetails2 = this.billingDetails;
        if (billingDetails2 != null) {
            hashMap.put(FIELD_BILLING_DETAILS, billingDetails2.toMap());
        }
        Map<String, String> map = this.metadata;
        if (map != null) {
            hashMap.put("metadata", map);
        }
        return hashMap;
    }

    enum Type {
        Card("card"),
        Ideal("ideal");
        
        /* access modifiers changed from: private */
        public final String mCode;

        private Type(String str) {
            this.mCode = str;
        }
    }

    public static final class Card {
        private static final String FIELD_CVC = "cvc";
        private static final String FIELD_EXP_MONTH = "exp_month";
        private static final String FIELD_EXP_YEAR = "exp_year";
        private static final String FIELD_NUMBER = "number";
        private static final String FIELD_TOKEN = "token";
        private final String mCvc;
        private final Integer mExpiryMonth;
        private final Integer mExpiryYear;
        private final String mNumber;
        private final String mToken;

        public static Card create(String str) {
            return new Card(str);
        }

        private Card(String str) {
            this.mToken = str;
            this.mNumber = null;
            this.mExpiryMonth = null;
            this.mExpiryYear = null;
            this.mCvc = null;
        }

        private Card(Builder builder) {
            this.mNumber = builder.mNumber;
            this.mExpiryMonth = builder.mExpiryMonth;
            this.mExpiryYear = builder.mExpiryYear;
            this.mCvc = builder.mCvc;
            this.mToken = null;
        }

        public int hashCode() {
            return ObjectUtils.hash(this.mNumber, this.mExpiryMonth, this.mExpiryYear, this.mCvc, this.mToken);
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof Card) && typedEquals((Card) obj));
        }

        private boolean typedEquals(Card card) {
            return ObjectUtils.equals(this.mNumber, card.mNumber) && ObjectUtils.equals(this.mCvc, card.mCvc) && ObjectUtils.equals(this.mExpiryMonth, card.mExpiryMonth) && ObjectUtils.equals(this.mExpiryYear, card.mExpiryYear) && ObjectUtils.equals(this.mToken, card.mToken);
        }

        public Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            String str = this.mNumber;
            if (str != null) {
                hashMap.put(FIELD_NUMBER, str);
            }
            Integer num = this.mExpiryMonth;
            if (num != null) {
                hashMap.put(FIELD_EXP_MONTH, num);
            }
            Integer num2 = this.mExpiryYear;
            if (num2 != null) {
                hashMap.put(FIELD_EXP_YEAR, num2);
            }
            String str2 = this.mCvc;
            if (str2 != null) {
                hashMap.put(FIELD_CVC, str2);
            }
            String str3 = this.mToken;
            if (str3 != null) {
                hashMap.put(FIELD_TOKEN, str3);
            }
            return hashMap;
        }

        public static final class Builder {
            /* access modifiers changed from: private */
            public String mCvc;
            /* access modifiers changed from: private */
            public Integer mExpiryMonth;
            /* access modifiers changed from: private */
            public Integer mExpiryYear;
            /* access modifiers changed from: private */
            public String mNumber;

            public Builder setNumber(String str) {
                this.mNumber = str;
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

            public Builder setCvc(String str) {
                this.mCvc = str;
                return this;
            }

            public Card build() {
                return new Card(this);
            }
        }
    }

    public static final class Ideal {
        private static final String FIELD_BANK = "bank";
        private final String mBank;

        private Ideal(Builder builder) {
            this.mBank = builder.mBank;
        }

        public Map<String, Object> toMap() {
            HashMap hashMap = new HashMap();
            hashMap.put(FIELD_BANK, this.mBank);
            return hashMap;
        }

        public int hashCode() {
            return ObjectUtils.hash(this.mBank);
        }

        public boolean equals(Object obj) {
            return this == obj || ((obj instanceof Ideal) && typedEquals((Ideal) obj));
        }

        private boolean typedEquals(Ideal ideal) {
            return ObjectUtils.equals(this.mBank, ideal.mBank);
        }

        public static final class Builder {
            /* access modifiers changed from: private */
            public String mBank;

            public Builder setBank(String str) {
                this.mBank = str;
                return this;
            }

            public Ideal build() {
                return new Ideal(this);
            }
        }
    }
}
