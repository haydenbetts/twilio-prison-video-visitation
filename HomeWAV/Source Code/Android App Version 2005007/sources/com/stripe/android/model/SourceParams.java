package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import java.util.HashMap;
import java.util.Map;

public class SourceParams {
    private static final String API_PARAM_AMOUNT = "amount";
    private static final String API_PARAM_CLIENT_SECRET = "client_secret";
    private static final String API_PARAM_CURRENCY = "currency";
    private static final String API_PARAM_METADATA = "metadata";
    private static final String API_PARAM_OWNER = "owner";
    private static final String API_PARAM_REDIRECT = "redirect";
    private static final String API_PARAM_TOKEN = "token";
    private static final String API_PARAM_TYPE = "type";
    private static final String API_PARAM_USAGE = "usage";
    private static final String CALL_ID = "callid";
    private static final String CART_ID = "cart_id";
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_BANK = "bank";
    private static final String FIELD_CARD = "card";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_COUNTRY = "country";
    private static final String FIELD_CVC = "cvc";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_EXP_MONTH = "exp_month";
    private static final String FIELD_EXP_YEAR = "exp_year";
    private static final String FIELD_IBAN = "iban";
    private static final String FIELD_LINE_1 = "line1";
    private static final String FIELD_LINE_2 = "line2";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_NUMBER = "number";
    private static final String FIELD_POSTAL_CODE = "postal_code";
    private static final String FIELD_PREFERRED_LANGUAGE = "preferred_language";
    private static final String FIELD_RETURN_URL = "return_url";
    private static final String FIELD_STATE = "state";
    private static final String FIELD_STATEMENT_DESCRIPTOR = "statement_descriptor";
    private static final String MASTERPASS = "masterpass";
    private static final String TRANSACTION_ID = "transaction_id";
    private static final String VISA_CHECKOUT = "visa_checkout";
    private Long mAmount;
    private Map<String, Object> mApiParameterMap;
    private String mCurrency;
    private Map<String, Object> mExtraParams;
    private Map<String, String> mMetaData;
    private Map<String, Object> mOwner;
    private Map<String, Object> mRedirect;
    private String mToken;
    private String mType;
    private String mTypeRaw;
    private String mUsage;

    private SourceParams() {
    }

    public static SourceParams createP24Params(long j, String str, String str2, String str3, String str4) {
        SourceParams redirect = new SourceParams().setAmount(j).setType(Source.P24).setCurrency(str).setRedirect(createSimpleMap(FIELD_RETURN_URL, str4));
        HashMap hashMap = new HashMap();
        hashMap.put("name", str2);
        hashMap.put("email", str3);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        if (hashMap.keySet().size() > 0) {
            redirect.setOwner(hashMap);
        }
        return redirect;
    }

    public static SourceParams createAlipayReusableParams(String str, String str2, String str3, String str4) {
        SourceParams usage = new SourceParams().setType(Source.ALIPAY).setCurrency(str).setRedirect(createSimpleMap(FIELD_RETURN_URL, str4)).setUsage(Source.REUSABLE);
        HashMap hashMap = new HashMap();
        hashMap.put("name", str2);
        hashMap.put("email", str3);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        if (hashMap.keySet().size() > 0) {
            usage.setOwner(hashMap);
        }
        return usage;
    }

    public static SourceParams createAlipaySingleUseParams(long j, String str, String str2, String str3, String str4) {
        SourceParams redirect = new SourceParams().setType(Source.ALIPAY).setCurrency(str).setAmount(j).setRedirect(createSimpleMap(FIELD_RETURN_URL, str4));
        HashMap hashMap = new HashMap();
        hashMap.put("name", str2);
        hashMap.put("email", str3);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        if (hashMap.keySet().size() > 0) {
            redirect.setOwner(hashMap);
        }
        return redirect;
    }

    public static SourceParams createBancontactParams(long j, String str, String str2, String str3, String str4) {
        SourceParams redirect = new SourceParams().setType(Source.BANCONTACT).setCurrency("eur").setAmount(j).setOwner(createSimpleMap("name", str)).setRedirect(createSimpleMap(FIELD_RETURN_URL, str2));
        if (!(str3 == null && str4 == null)) {
            HashMap hashMap = new HashMap();
            hashMap.put(FIELD_STATEMENT_DESCRIPTOR, str3);
            hashMap.put(FIELD_PREFERRED_LANGUAGE, str4);
            StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
            redirect.setApiParameterMap(hashMap);
        }
        return redirect;
    }

    public static SourceParams createCustomParams() {
        return new SourceParams();
    }

    public static SourceParams createSourceFromTokenParams(String str) {
        SourceParams createCustomParams = createCustomParams();
        createCustomParams.setType("card");
        createCustomParams.setToken(str);
        return createCustomParams;
    }

    public static SourceParams createCardParams(Card card) {
        SourceParams type = new SourceParams().setType("card");
        HashMap hashMap = new HashMap();
        hashMap.put(FIELD_NUMBER, card.getNumber());
        hashMap.put(FIELD_EXP_MONTH, card.getExpMonth());
        hashMap.put(FIELD_EXP_YEAR, card.getExpYear());
        hashMap.put(FIELD_CVC, card.getCVC());
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        type.setApiParameterMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("line1", card.getAddressLine1());
        hashMap2.put("line2", card.getAddressLine2());
        hashMap2.put("city", card.getAddressCity());
        hashMap2.put("country", card.getAddressCountry());
        hashMap2.put("state", card.getAddressState());
        hashMap2.put("postal_code", card.getAddressZip());
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put("name", card.getName());
        if (hashMap2.keySet().size() > 0) {
            hashMap3.put(FIELD_ADDRESS, hashMap2);
        }
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap3);
        if (hashMap3.keySet().size() > 0) {
            type.setOwner(hashMap3);
        }
        Map<String, String> metadata = card.getMetadata();
        if (metadata != null) {
            type.setMetaData(metadata);
        }
        return type;
    }

    public static SourceParams createEPSParams(long j, String str, String str2, String str3) {
        SourceParams redirect = new SourceParams().setType(Source.EPS).setCurrency("eur").setAmount(j).setOwner(createSimpleMap("name", str)).setRedirect(createSimpleMap(FIELD_RETURN_URL, str2));
        if (str3 != null) {
            redirect.setApiParameterMap(createSimpleMap(FIELD_STATEMENT_DESCRIPTOR, str3));
        }
        return redirect;
    }

    public static SourceParams createGiropayParams(long j, String str, String str2, String str3) {
        SourceParams redirect = new SourceParams().setType(Source.GIROPAY).setCurrency("eur").setAmount(j).setOwner(createSimpleMap("name", str)).setRedirect(createSimpleMap(FIELD_RETURN_URL, str2));
        if (str3 != null) {
            redirect.setApiParameterMap(createSimpleMap(FIELD_STATEMENT_DESCRIPTOR, str3));
        }
        return redirect;
    }

    public static SourceParams createIdealParams(long j, String str, String str2, String str3, String str4) {
        SourceParams redirect = new SourceParams().setType(Source.IDEAL).setCurrency("eur").setAmount(j).setRedirect(createSimpleMap(FIELD_RETURN_URL, str2));
        if (str != null) {
            redirect.setOwner(createSimpleMap("name", str));
        }
        HashMap hashMap = new HashMap();
        if (str3 != null) {
            hashMap.put(FIELD_STATEMENT_DESCRIPTOR, str3);
        }
        if (str4 != null) {
            hashMap.put(FIELD_BANK, str4);
        }
        if (!hashMap.isEmpty()) {
            redirect.setApiParameterMap(hashMap);
        }
        return redirect;
    }

    public static SourceParams createMultibancoParams(long j, String str, String str2) {
        return new SourceParams().setType(Source.MULTIBANCO).setCurrency("eur").setAmount(j).setRedirect(createSimpleMap(FIELD_RETURN_URL, str)).setOwner(createSimpleMap("email", str2));
    }

    public static SourceParams createSepaDebitParams(String str, String str2, String str3, String str4, String str5, String str6) {
        return createSepaDebitParams(str, str2, (String) null, str3, str4, str5, str6);
    }

    public static SourceParams createSepaDebitParams(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        SourceParams currency = new SourceParams().setType(Source.SEPA_DEBIT).setCurrency("eur");
        HashMap hashMap = new HashMap();
        hashMap.put("line1", str4);
        hashMap.put("city", str5);
        hashMap.put("postal_code", str6);
        hashMap.put("country", str7);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("name", str);
        hashMap2.put("email", str3);
        hashMap2.put(FIELD_ADDRESS, hashMap);
        return currency.setOwner(hashMap2).setApiParameterMap(createSimpleMap(FIELD_IBAN, str2));
    }

    public static SourceParams createSofortParams(long j, String str, String str2, String str3) {
        SourceParams redirect = new SourceParams().setType(Source.SOFORT).setCurrency("eur").setAmount(j).setRedirect(createSimpleMap(FIELD_RETURN_URL, str));
        Map<String, Object> createSimpleMap = createSimpleMap("country", str2);
        if (str3 != null) {
            createSimpleMap.put(FIELD_STATEMENT_DESCRIPTOR, str3);
        }
        return redirect.setApiParameterMap(createSimpleMap);
    }

    public static SourceParams createThreeDSecureParams(long j, String str, String str2, String str3) {
        return new SourceParams().setType(Source.THREE_D_SECURE).setCurrency(str).setAmount(j).setRedirect(createSimpleMap(FIELD_RETURN_URL, str2)).setApiParameterMap(createSimpleMap("card", str3));
    }

    public static SourceParams createVisaCheckoutParams(String str) {
        return new SourceParams().setType("card").setApiParameterMap(createSimpleMap(VISA_CHECKOUT, createSimpleMap(CALL_ID, str)));
    }

    public static SourceParams createMasterpassParams(String str, String str2) {
        Map<String, Object> createSimpleMap = createSimpleMap("transaction_id", str);
        createSimpleMap.put(CART_ID, str2);
        return new SourceParams().setType("card").setApiParameterMap(createSimpleMap(MASTERPASS, createSimpleMap));
    }

    public static Map<String, Object> createRetrieveSourceParams(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(API_PARAM_CLIENT_SECRET, str);
        return hashMap;
    }

    public Long getAmount() {
        return this.mAmount;
    }

    public Map<String, Object> getApiParameterMap() {
        return this.mApiParameterMap;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public Map<String, Object> getOwner() {
        return this.mOwner;
    }

    public Map<String, Object> getRedirect() {
        return this.mRedirect;
    }

    public String getType() {
        return this.mType;
    }

    public String getTypeRaw() {
        return this.mTypeRaw;
    }

    public String getUsage() {
        return this.mUsage;
    }

    public Map<String, String> getMetaData() {
        return this.mMetaData;
    }

    public SourceParams setAmount(long j) {
        this.mAmount = Long.valueOf(j);
        return this;
    }

    public SourceParams setApiParameterMap(Map<String, Object> map) {
        this.mApiParameterMap = map;
        return this;
    }

    public SourceParams setCurrency(String str) {
        this.mCurrency = str;
        return this;
    }

    public SourceParams setOwner(Map<String, Object> map) {
        this.mOwner = map;
        return this;
    }

    public SourceParams setRedirect(Map<String, Object> map) {
        this.mRedirect = map;
        return this;
    }

    public SourceParams setExtraParams(Map<String, Object> map) {
        this.mExtraParams = map;
        return this;
    }

    public SourceParams setReturnUrl(String str) {
        Map<String, Object> map = this.mRedirect;
        if (map == null) {
            setRedirect(createSimpleMap(FIELD_RETURN_URL, str));
        } else {
            map.put(FIELD_RETURN_URL, str);
        }
        return this;
    }

    public SourceParams setType(String str) {
        this.mType = str;
        this.mTypeRaw = str;
        return this;
    }

    public SourceParams setTypeRaw(String str) {
        this.mType = Source.asSourceType(str);
        this.mTypeRaw = str;
        return this;
    }

    public SourceParams setMetaData(Map<String, String> map) {
        this.mMetaData = map;
        return this;
    }

    public SourceParams setToken(String str) {
        this.mToken = str;
        return this;
    }

    public SourceParams setUsage(String str) {
        this.mUsage = str;
        return this;
    }

    public Map<String, Object> toParamMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("type", this.mTypeRaw);
        hashMap.put(this.mTypeRaw, this.mApiParameterMap);
        hashMap.put(API_PARAM_AMOUNT, this.mAmount);
        hashMap.put(API_PARAM_CURRENCY, this.mCurrency);
        hashMap.put(API_PARAM_OWNER, this.mOwner);
        hashMap.put("redirect", this.mRedirect);
        hashMap.put("metadata", this.mMetaData);
        hashMap.put(API_PARAM_TOKEN, this.mToken);
        hashMap.put(API_PARAM_USAGE, this.mUsage);
        Map<String, Object> map = this.mExtraParams;
        if (map != null) {
            hashMap.putAll(map);
        }
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    private static Map<String, Object> createSimpleMap(String str, Object obj) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, obj);
        return hashMap;
    }

    private static Map<String, Object> createSimpleMap(String str, Object obj, String str2, Object obj2) {
        HashMap hashMap = new HashMap();
        hashMap.put(str, obj);
        hashMap.put(str2, obj2);
        return hashMap;
    }
}
