package com.stripe.android.model;

import android.text.TextUtils;
import com.stripe.android.CardUtils;
import com.stripe.android.R;
import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.StripeTextUtils;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.utils.ObjectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Card extends StripeJsonModel implements StripePaymentSource {
    public static final String AMERICAN_EXPRESS = "American Express";
    public static final Map<String, Integer> BRAND_RESOURCE_MAP = new HashMap<String, Integer>() {
        {
            put(Card.AMERICAN_EXPRESS, Integer.valueOf(R.drawable.ic_amex));
            put(Card.DINERS_CLUB, Integer.valueOf(R.drawable.ic_diners));
            put(Card.DISCOVER, Integer.valueOf(R.drawable.ic_discover));
            put(Card.JCB, Integer.valueOf(R.drawable.ic_jcb));
            put(Card.MASTERCARD, Integer.valueOf(R.drawable.ic_mastercard));
            put(Card.VISA, Integer.valueOf(R.drawable.ic_visa));
            put(Card.UNIONPAY, Integer.valueOf(R.drawable.ic_unionpay));
            put("Unknown", Integer.valueOf(R.drawable.ic_unknown));
        }
    };
    public static final int CVC_LENGTH_AMERICAN_EXPRESS = 4;
    public static final int CVC_LENGTH_COMMON = 3;
    public static final String DINERS_CLUB = "Diners Club";
    public static final String DISCOVER = "Discover";
    private static final String FIELD_ADDRESS_CITY = "address_city";
    private static final String FIELD_ADDRESS_COUNTRY = "address_country";
    private static final String FIELD_ADDRESS_LINE1 = "address_line1";
    private static final String FIELD_ADDRESS_LINE1_CHECK = "address_line1_check";
    private static final String FIELD_ADDRESS_LINE2 = "address_line2";
    private static final String FIELD_ADDRESS_STATE = "address_state";
    private static final String FIELD_ADDRESS_ZIP = "address_zip";
    private static final String FIELD_ADDRESS_ZIP_CHECK = "address_zip_check";
    private static final String FIELD_BRAND = "brand";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_CURRENCY = "currency";
    private static final String FIELD_CUSTOMER = "customer";
    private static final String FIELD_CVC_CHECK = "cvc_check";
    private static final String FIELD_EXP_MONTH = "exp_month";
    private static final String FIELD_EXP_YEAR = "exp_year";
    private static final String FIELD_FINGERPRINT = "fingerprint";
    private static final String FIELD_FUNDING = "funding";
    private static final String FIELD_ID = "id";
    private static final String FIELD_LAST4 = "last4";
    private static final String FIELD_METADATA = "metadata";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_OBJECT = "object";
    private static final String FIELD_TOKENIZATION_METHOD = "tokenization_method";
    public static final String FUNDING_CREDIT = "credit";
    public static final String FUNDING_DEBIT = "debit";
    public static final String FUNDING_PREPAID = "prepaid";
    public static final String FUNDING_UNKNOWN = "unknown";
    public static final String JCB = "JCB";
    public static final String MASTERCARD = "MasterCard";
    public static final int MAX_LENGTH_AMERICAN_EXPRESS = 15;
    public static final int MAX_LENGTH_DINERS_CLUB = 14;
    public static final int MAX_LENGTH_STANDARD = 16;
    public static final String[] PREFIXES_AMERICAN_EXPRESS = {"34", "37"};
    public static final String[] PREFIXES_DINERS_CLUB = {"300", "301", "302", "303", "304", "305", "309", "36", "38", "39"};
    public static final String[] PREFIXES_DISCOVER = {"60", "64", "65"};
    public static final String[] PREFIXES_JCB = {"35"};
    public static final String[] PREFIXES_MASTERCARD = {"2221", "2222", "2223", "2224", "2225", "2226", "2227", "2228", "2229", "223", "224", "225", "226", "227", "228", "229", "23", "24", "25", "26", "270", "271", "2720", "50", "51", "52", "53", "54", "55", "67"};
    public static final String[] PREFIXES_UNIONPAY = {"62"};
    public static final String[] PREFIXES_VISA = {"4"};
    public static final String UNIONPAY = "UnionPay";
    public static final String UNKNOWN = "Unknown";
    static final String VALUE_CARD = "card";
    public static final String VISA = "Visa";
    private String addressCity;
    private String addressCountry;
    private String addressLine1;
    private String addressLine1Check;
    private String addressLine2;
    private String addressState;
    private String addressZip;
    private String addressZipCheck;
    private String brand;
    private String country;
    private String currency;
    private String customerId;
    private String cvc;
    private String cvcCheck;
    private Integer expMonth;
    private Integer expYear;
    private String fingerprint;
    private String funding;
    private String id;
    private String last4;
    private final List<String> loggingTokens;
    private Map<String, String> metadata;
    private String name;
    private String number;
    private String tokenizationMethod;

    @Retention(RetentionPolicy.SOURCE)
    public @interface CardBrand {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface FundingType {
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String addressCity;
        /* access modifiers changed from: private */
        public String addressCountry;
        /* access modifiers changed from: private */
        public String addressLine1;
        /* access modifiers changed from: private */
        public String addressLine1Check;
        /* access modifiers changed from: private */
        public String addressLine2;
        /* access modifiers changed from: private */
        public String addressState;
        /* access modifiers changed from: private */
        public String addressZip;
        /* access modifiers changed from: private */
        public String addressZipCheck;
        /* access modifiers changed from: private */
        public String brand;
        /* access modifiers changed from: private */
        public String country;
        /* access modifiers changed from: private */
        public String currency;
        /* access modifiers changed from: private */
        public String customer;
        /* access modifiers changed from: private */
        public final String cvc;
        /* access modifiers changed from: private */
        public String cvcCheck;
        /* access modifiers changed from: private */
        public final Integer expMonth;
        /* access modifiers changed from: private */
        public final Integer expYear;
        /* access modifiers changed from: private */
        public String fingerprint;
        /* access modifiers changed from: private */
        public String funding;
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public String last4;
        /* access modifiers changed from: private */
        public Map<String, String> metadata;
        /* access modifiers changed from: private */
        public String name;
        /* access modifiers changed from: private */
        public final String number;
        /* access modifiers changed from: private */
        public String tokenizationMethod;

        public Builder(String str, Integer num, Integer num2, String str2) {
            this.number = str;
            this.expMonth = num;
            this.expYear = num2;
            this.cvc = str2;
        }

        public Builder name(String str) {
            this.name = str;
            return this;
        }

        public Builder addressLine1(String str) {
            this.addressLine1 = str;
            return this;
        }

        public Builder addressLine1Check(String str) {
            this.addressLine1Check = str;
            return this;
        }

        public Builder addressLine2(String str) {
            this.addressLine2 = str;
            return this;
        }

        public Builder addressCity(String str) {
            this.addressCity = str;
            return this;
        }

        public Builder addressState(String str) {
            this.addressState = str;
            return this;
        }

        public Builder addressZip(String str) {
            this.addressZip = str;
            return this;
        }

        public Builder addressZipCheck(String str) {
            this.addressZipCheck = str;
            return this;
        }

        public Builder addressCountry(String str) {
            this.addressCountry = str;
            return this;
        }

        public Builder brand(String str) {
            this.brand = str;
            return this;
        }

        public Builder fingerprint(String str) {
            this.fingerprint = str;
            return this;
        }

        public Builder funding(String str) {
            this.funding = str;
            return this;
        }

        public Builder country(String str) {
            this.country = str;
            return this;
        }

        public Builder currency(String str) {
            this.currency = str;
            return this;
        }

        public Builder customer(String str) {
            this.customer = str;
            return this;
        }

        public Builder cvcCheck(String str) {
            this.cvcCheck = str;
            return this;
        }

        public Builder last4(String str) {
            this.last4 = str;
            return this;
        }

        public Builder id(String str) {
            this.id = str;
            return this;
        }

        public Builder tokenizationMethod(String str) {
            this.tokenizationMethod = str;
            return this;
        }

        public Builder metadata(Map<String, String> map) {
            this.metadata = map;
            return this;
        }

        public Card build() {
            return new Card(this);
        }
    }

    public static String asCardBrand(String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return null;
        }
        if (AMERICAN_EXPRESS.equalsIgnoreCase(str)) {
            return AMERICAN_EXPRESS;
        }
        if (MASTERCARD.equalsIgnoreCase(str)) {
            return MASTERCARD;
        }
        if (DINERS_CLUB.equalsIgnoreCase(str)) {
            return DINERS_CLUB;
        }
        if (DISCOVER.equalsIgnoreCase(str)) {
            return DISCOVER;
        }
        if (JCB.equalsIgnoreCase(str)) {
            return JCB;
        }
        if (VISA.equalsIgnoreCase(str)) {
            return VISA;
        }
        if (UNIONPAY.equalsIgnoreCase(str)) {
            return UNIONPAY;
        }
        return "Unknown";
    }

    public static String asFundingType(String str) {
        if (str == null || TextUtils.isEmpty(str.trim())) {
            return null;
        }
        if ("credit".equalsIgnoreCase(str)) {
            return "credit";
        }
        if ("debit".equalsIgnoreCase(str)) {
            return "debit";
        }
        if (FUNDING_PREPAID.equalsIgnoreCase(str)) {
            return FUNDING_PREPAID;
        }
        return "unknown";
    }

    public static Card fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Card fromJson(JSONObject jSONObject) {
        if (jSONObject == null || !"card".equals(jSONObject.optString(FIELD_OBJECT))) {
            return null;
        }
        Integer optInteger = StripeJsonUtils.optInteger(jSONObject, FIELD_EXP_MONTH);
        Integer optInteger2 = StripeJsonUtils.optInteger(jSONObject, FIELD_EXP_YEAR);
        if (optInteger != null && (optInteger.intValue() < 1 || optInteger.intValue() > 12)) {
            optInteger = null;
        }
        if (optInteger2 != null && optInteger2.intValue() < 0) {
            optInteger2 = null;
        }
        return new Builder((String) null, optInteger, optInteger2, (String) null).addressCity(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_CITY)).addressLine1(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_LINE1)).addressLine1Check(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_LINE1_CHECK)).addressLine2(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_LINE2)).addressCountry(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_COUNTRY)).addressState(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_STATE)).addressZip(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_ZIP)).addressZipCheck(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS_ZIP_CHECK)).brand(asCardBrand(StripeJsonUtils.optString(jSONObject, FIELD_BRAND))).country(StripeJsonUtils.optCountryCode(jSONObject, "country")).customer(StripeJsonUtils.optString(jSONObject, FIELD_CUSTOMER)).currency(StripeJsonUtils.optCurrency(jSONObject, FIELD_CURRENCY)).cvcCheck(StripeJsonUtils.optString(jSONObject, FIELD_CVC_CHECK)).funding(asFundingType(StripeJsonUtils.optString(jSONObject, FIELD_FUNDING))).fingerprint(StripeJsonUtils.optString(jSONObject, FIELD_FINGERPRINT)).id(StripeJsonUtils.optString(jSONObject, "id")).last4(StripeJsonUtils.optString(jSONObject, FIELD_LAST4)).name(StripeJsonUtils.optString(jSONObject, "name")).tokenizationMethod(StripeJsonUtils.optString(jSONObject, FIELD_TOKENIZATION_METHOD)).metadata(StripeJsonUtils.optHash(jSONObject, "metadata")).build();
    }

    public Card(String str, Integer num, Integer num2, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, Map<String, String> map) {
        this.loggingTokens = new ArrayList();
        this.number = StripeTextUtils.nullIfBlank(normalizeCardNumber(str));
        this.expMonth = num;
        this.expYear = num2;
        this.cvc = StripeTextUtils.nullIfBlank(str2);
        this.name = StripeTextUtils.nullIfBlank(str3);
        this.addressLine1 = StripeTextUtils.nullIfBlank(str4);
        this.addressLine2 = StripeTextUtils.nullIfBlank(str5);
        this.addressCity = StripeTextUtils.nullIfBlank(str6);
        this.addressState = StripeTextUtils.nullIfBlank(str7);
        this.addressZip = StripeTextUtils.nullIfBlank(str8);
        this.addressCountry = StripeTextUtils.nullIfBlank(str9);
        this.brand = asCardBrand(str10) == null ? getBrand() : str10;
        this.last4 = StripeTextUtils.nullIfBlank(str11) == null ? getLast4() : str11;
        this.fingerprint = StripeTextUtils.nullIfBlank(str12);
        this.funding = asFundingType(str13);
        this.country = StripeTextUtils.nullIfBlank(str14);
        this.currency = StripeTextUtils.nullIfBlank(str15);
        this.id = StripeTextUtils.nullIfBlank(str16);
        this.metadata = map;
    }

    public Card(String str, Integer num, Integer num2, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, Map<String, String> map) {
        this(str, num, num2, str2, str3, str4, str5, str6, str7, str8, str9, (String) null, (String) null, (String) null, (String) null, (String) null, str10, (String) null, map);
    }

    public Card(String str, Integer num, Integer num2, String str2) {
        this(str, num, num2, str2, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (Map<String, String>) null);
    }

    public PaymentMethodCreateParams.Card toPaymentMethodParamsCard() {
        return new PaymentMethodCreateParams.Card.Builder().setNumber(this.number).setCvc(this.cvc).setExpiryMonth(this.expMonth).setExpiryYear(this.expYear).build();
    }

    public boolean validateCard() {
        return validateCard(Calendar.getInstance());
    }

    public boolean validateNumber() {
        return CardUtils.isValidCardNumber(this.number);
    }

    public boolean validateExpiryDate() {
        return validateExpiryDate(Calendar.getInstance());
    }

    public boolean validateCVC() {
        if (StripeTextUtils.isBlank(this.cvc)) {
            return false;
        }
        String trim = this.cvc.trim();
        String brand2 = getBrand();
        boolean z = (brand2 == null && trim.length() >= 3 && trim.length() <= 4) || (AMERICAN_EXPRESS.equals(brand2) && trim.length() == 4) || trim.length() == 3;
        if (!ModelUtils.isWholePositiveNumber(trim) || !z) {
            return false;
        }
        return true;
    }

    public boolean validateExpMonth() {
        Integer num = this.expMonth;
        return num != null && num.intValue() >= 1 && this.expMonth.intValue() <= 12;
    }

    /* access modifiers changed from: package-private */
    public boolean validateExpYear(Calendar calendar) {
        Integer num = this.expYear;
        return num != null && !ModelUtils.hasYearPassed(num.intValue(), calendar);
    }

    public String getNumber() {
        return this.number;
    }

    public List<String> getLoggingTokens() {
        return this.loggingTokens;
    }

    public Card addLoggingToken(String str) {
        this.loggingTokens.add(str);
        return this;
    }

    @Deprecated
    public void setNumber(String str) {
        this.number = str;
        this.brand = null;
        this.last4 = null;
    }

    public String getCVC() {
        return this.cvc;
    }

    @Deprecated
    public void setCVC(String str) {
        this.cvc = str;
    }

    public Integer getExpMonth() {
        return this.expMonth;
    }

    @Deprecated
    public void setExpMonth(Integer num) {
        this.expMonth = num;
    }

    public Integer getExpYear() {
        return this.expYear;
    }

    @Deprecated
    public void setExpYear(Integer num) {
        this.expYear = num;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public void setAddressLine1(String str) {
        this.addressLine1 = str;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public void setAddressLine2(String str) {
        this.addressLine2 = str;
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public void setAddressCity(String str) {
        this.addressCity = str;
    }

    public String getAddressZip() {
        return this.addressZip;
    }

    public void setAddressZip(String str) {
        this.addressZip = str;
    }

    public String getAddressState() {
        return this.addressState;
    }

    public void setAddressState(String str) {
        this.addressState = str;
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public void setAddressCountry(String str) {
        this.addressCountry = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Map<String, String> map) {
        this.metadata = map;
    }

    public String getLast4() {
        if (!StripeTextUtils.isBlank(this.last4)) {
            return this.last4;
        }
        String str = this.number;
        if (str == null || str.length() <= 4) {
            return null;
        }
        String str2 = this.number;
        String substring = str2.substring(str2.length() - 4);
        this.last4 = substring;
        return substring;
    }

    @Deprecated
    public String getType() {
        return getBrand();
    }

    public String getBrand() {
        if (StripeTextUtils.isBlank(this.brand) && !StripeTextUtils.isBlank(this.number)) {
            this.brand = CardUtils.getPossibleCardType(this.number);
        }
        return this.brand;
    }

    public String getFingerprint() {
        return this.fingerprint;
    }

    public String getFunding() {
        return this.funding;
    }

    public String getCountry() {
        return this.country;
    }

    public String getId() {
        return this.id;
    }

    public String getAddressLine1Check() {
        return this.addressLine1Check;
    }

    public String getAddressZipCheck() {
        return this.addressZipCheck;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getCvcCheck() {
        return this.cvcCheck;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject, "name", this.name);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_CITY, this.addressCity);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_COUNTRY, this.addressCountry);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_LINE1, this.addressLine1);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_LINE1_CHECK, this.addressLine1Check);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_LINE2, this.addressLine2);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_STATE, this.addressState);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_ZIP, this.addressZip);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS_ZIP_CHECK, this.addressZipCheck);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_BRAND, this.brand);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CURRENCY, this.currency);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "country", this.country);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CUSTOMER, this.customerId);
        StripeJsonUtils.putIntegerIfNotNull(jSONObject, FIELD_EXP_MONTH, this.expMonth);
        StripeJsonUtils.putIntegerIfNotNull(jSONObject, FIELD_EXP_YEAR, this.expYear);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_FINGERPRINT, this.fingerprint);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_FUNDING, this.funding);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CVC_CHECK, this.cvcCheck);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_LAST4, this.last4);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "id", this.id);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_TOKENIZATION_METHOD, this.tokenizationMethod);
        StripeJsonUtils.putStringHashIfNotNull(jSONObject, "metadata", this.metadata);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_OBJECT, "card");
        return jSONObject;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.name);
        hashMap.put(FIELD_ADDRESS_CITY, this.addressCity);
        hashMap.put(FIELD_ADDRESS_COUNTRY, this.addressCountry);
        hashMap.put(FIELD_ADDRESS_LINE1, this.addressLine1);
        hashMap.put(FIELD_ADDRESS_LINE1_CHECK, this.addressLine1Check);
        hashMap.put(FIELD_ADDRESS_LINE2, this.addressLine2);
        hashMap.put(FIELD_ADDRESS_STATE, this.addressState);
        hashMap.put(FIELD_ADDRESS_ZIP, this.addressZip);
        hashMap.put(FIELD_ADDRESS_ZIP_CHECK, this.addressZipCheck);
        hashMap.put(FIELD_BRAND, this.brand);
        hashMap.put(FIELD_CURRENCY, this.currency);
        hashMap.put("country", this.country);
        hashMap.put(FIELD_CUSTOMER, this.customerId);
        hashMap.put(FIELD_CVC_CHECK, this.cvcCheck);
        hashMap.put(FIELD_EXP_MONTH, this.expMonth);
        hashMap.put(FIELD_EXP_YEAR, this.expYear);
        hashMap.put(FIELD_FINGERPRINT, this.fingerprint);
        hashMap.put(FIELD_FUNDING, this.funding);
        hashMap.put("id", this.id);
        hashMap.put(FIELD_LAST4, this.last4);
        hashMap.put(FIELD_TOKENIZATION_METHOD, this.tokenizationMethod);
        hashMap.put("metadata", this.metadata);
        hashMap.put(FIELD_OBJECT, "card");
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public String getTokenizationMethod() {
        return this.tokenizationMethod;
    }

    /* access modifiers changed from: package-private */
    public boolean validateCard(Calendar calendar) {
        if (this.cvc == null) {
            if (!validateNumber() || !validateExpiryDate(calendar)) {
                return false;
            }
            return true;
        } else if (!validateNumber() || !validateExpiryDate(calendar) || !validateCVC()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean validateExpiryDate(Calendar calendar) {
        if (validateExpMonth() && validateExpYear(calendar)) {
            return !ModelUtils.hasMonthPassed(this.expYear.intValue(), this.expMonth.intValue(), calendar);
        }
        return false;
    }

    private Card(Builder builder) {
        String str;
        String str2;
        this.loggingTokens = new ArrayList();
        this.number = StripeTextUtils.nullIfBlank(normalizeCardNumber(builder.number));
        this.expMonth = builder.expMonth;
        this.expYear = builder.expYear;
        this.cvc = StripeTextUtils.nullIfBlank(builder.cvc);
        this.name = StripeTextUtils.nullIfBlank(builder.name);
        this.addressLine1 = StripeTextUtils.nullIfBlank(builder.addressLine1);
        this.addressLine1Check = StripeTextUtils.nullIfBlank(builder.addressLine1Check);
        this.addressLine2 = StripeTextUtils.nullIfBlank(builder.addressLine2);
        this.addressCity = StripeTextUtils.nullIfBlank(builder.addressCity);
        this.addressState = StripeTextUtils.nullIfBlank(builder.addressState);
        this.addressZip = StripeTextUtils.nullIfBlank(builder.addressZip);
        this.addressZipCheck = StripeTextUtils.nullIfBlank(builder.addressZipCheck);
        this.addressCountry = StripeTextUtils.nullIfBlank(builder.addressCountry);
        if (StripeTextUtils.nullIfBlank(builder.last4) == null) {
            str = getLast4();
        } else {
            str = builder.last4;
        }
        this.last4 = str;
        if (asCardBrand(builder.brand) == null) {
            str2 = getBrand();
        } else {
            str2 = builder.brand;
        }
        this.brand = str2;
        this.fingerprint = StripeTextUtils.nullIfBlank(builder.fingerprint);
        this.funding = asFundingType(builder.funding);
        this.country = StripeTextUtils.nullIfBlank(builder.country);
        this.currency = StripeTextUtils.nullIfBlank(builder.currency);
        this.customerId = StripeTextUtils.nullIfBlank(builder.customer);
        this.cvcCheck = StripeTextUtils.nullIfBlank(builder.cvcCheck);
        this.id = StripeTextUtils.nullIfBlank(builder.id);
        this.tokenizationMethod = StripeTextUtils.nullIfBlank(builder.tokenizationMethod);
        this.metadata = builder.metadata;
    }

    private String normalizeCardNumber(String str) {
        if (str == null) {
            return null;
        }
        return str.trim().replaceAll("\\s+|-", "");
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Card) && typedEquals((Card) obj));
    }

    private boolean typedEquals(Card card) {
        return ObjectUtils.equals(this.number, card.number) && ObjectUtils.equals(this.cvc, card.cvc) && ObjectUtils.equals(this.expMonth, card.expMonth) && ObjectUtils.equals(this.expYear, card.expYear) && ObjectUtils.equals(this.name, card.name) && ObjectUtils.equals(this.addressLine1, card.addressLine1) && ObjectUtils.equals(this.addressLine1Check, card.addressLine1Check) && ObjectUtils.equals(this.addressLine2, card.addressLine2) && ObjectUtils.equals(this.addressCity, card.addressCity) && ObjectUtils.equals(this.addressState, card.addressState) && ObjectUtils.equals(this.addressZip, card.addressZip) && ObjectUtils.equals(this.addressZipCheck, card.addressZipCheck) && ObjectUtils.equals(this.addressCountry, card.addressCountry) && ObjectUtils.equals(this.last4, card.last4) && ObjectUtils.equals(this.brand, card.brand) && ObjectUtils.equals(this.funding, card.funding) && ObjectUtils.equals(this.fingerprint, card.fingerprint) && ObjectUtils.equals(this.country, card.country) && ObjectUtils.equals(this.currency, card.currency) && ObjectUtils.equals(this.customerId, card.customerId) && ObjectUtils.equals(this.cvcCheck, card.cvcCheck) && ObjectUtils.equals(this.id, card.id) && ObjectUtils.equals(this.loggingTokens, card.loggingTokens) && ObjectUtils.equals(this.tokenizationMethod, card.tokenizationMethod) && ObjectUtils.equals(this.metadata, card.metadata);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.number, this.cvc, this.expMonth, this.expYear, this.name, this.addressLine1, this.addressLine1Check, this.addressLine2, this.addressCity, this.addressState, this.addressZip, this.addressZipCheck, this.addressCountry, this.last4, this.brand, this.funding, this.fingerprint, this.country, this.currency, this.customerId, this.cvcCheck, this.id, this.loggingTokens, this.tokenizationMethod, this.metadata);
    }
}
