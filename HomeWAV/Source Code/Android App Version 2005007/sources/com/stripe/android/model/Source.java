package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.utils.ObjectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public class Source extends StripeJsonModel implements StripePaymentSource {
    public static final String ALIPAY = "alipay";
    public static final String BANCONTACT = "bancontact";
    public static final String CANCELED = "canceled";
    public static final String CARD = "card";
    public static final String CHARGEABLE = "chargeable";
    public static final String CODE_VERIFICATION = "code_verification";
    public static final String CONSUMED = "consumed";
    public static final String EPS = "eps";
    static final String EURO = "eur";
    public static final String FAILED = "failed";
    private static final String FIELD_AMOUNT = "amount";
    private static final String FIELD_CLIENT_SECRET = "client_secret";
    private static final String FIELD_CODE_VERIFICATION = "code_verification";
    private static final String FIELD_CREATED = "created";
    private static final String FIELD_CURRENCY = "currency";
    private static final String FIELD_FLOW = "flow";
    private static final String FIELD_ID = "id";
    private static final String FIELD_LIVEMODE = "livemode";
    private static final String FIELD_METADATA = "metadata";
    private static final String FIELD_OBJECT = "object";
    private static final String FIELD_OWNER = "owner";
    private static final String FIELD_RECEIVER = "receiver";
    private static final String FIELD_REDIRECT = "redirect";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_USAGE = "usage";
    public static final String GIROPAY = "giropay";
    public static final String IDEAL = "ideal";
    private static final Set<String> MODELED_TYPES;
    public static final String MULTIBANCO = "multibanco";
    public static final String NONE = "none";
    public static final String P24 = "p24";
    public static final String PENDING = "pending";
    public static final String RECEIVER = "receiver";
    public static final String REDIRECT = "redirect";
    public static final String REUSABLE = "reusable";
    public static final String SEPA_DEBIT = "sepa_debit";
    public static final String SINGLE_USE = "single_use";
    public static final String SOFORT = "sofort";
    public static final String THREE_D_SECURE = "three_d_secure";
    public static final String UNKNOWN = "unknown";
    static final String USD = "usd";
    private static final String VALUE_CARD = "card";
    static final String VALUE_SOURCE = "source";
    private Long mAmount;
    private String mClientSecret;
    private SourceCodeVerification mCodeVerification;
    private Long mCreated;
    private String mCurrency;
    private String mFlow;
    private String mId;
    private Boolean mLiveMode;
    private Map<String, String> mMetaData;
    private SourceOwner mOwner;
    private SourceReceiver mReceiver;
    private SourceRedirect mRedirect;
    private Map<String, Object> mSourceTypeData;
    private StripeSourceTypeModel mSourceTypeModel;
    private String mStatus;
    private String mType;
    private String mTypeRaw;
    private String mUsage;

    @Retention(RetentionPolicy.SOURCE)
    public @interface SourceFlow {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SourceStatus {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SourceType {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Usage {
    }

    static {
        HashSet hashSet = new HashSet();
        MODELED_TYPES = hashSet;
        hashSet.add("card");
        hashSet.add(SEPA_DEBIT);
    }

    private Source(String str, SourceCardData sourceCardData) {
        this.mId = str;
        this.mType = "card";
        this.mSourceTypeModel = sourceCardData;
    }

    private Source(String str, Long l, String str2, SourceCodeVerification sourceCodeVerification, Long l2, String str3, String str4, Boolean bool, Map<String, String> map, SourceOwner sourceOwner, SourceReceiver sourceReceiver, SourceRedirect sourceRedirect, String str5, Map<String, Object> map2, StripeSourceTypeModel stripeSourceTypeModel, String str6, String str7, String str8) {
        this.mId = str;
        this.mAmount = l;
        this.mClientSecret = str2;
        this.mCodeVerification = sourceCodeVerification;
        this.mCreated = l2;
        this.mCurrency = str3;
        this.mFlow = str4;
        this.mLiveMode = bool;
        this.mMetaData = map;
        this.mOwner = sourceOwner;
        this.mReceiver = sourceReceiver;
        this.mRedirect = sourceRedirect;
        this.mStatus = str5;
        this.mSourceTypeData = map2;
        this.mSourceTypeModel = stripeSourceTypeModel;
        this.mType = str6;
        this.mTypeRaw = str7;
        this.mUsage = str8;
    }

    public String getId() {
        return this.mId;
    }

    public Long getAmount() {
        return this.mAmount;
    }

    public String getClientSecret() {
        return this.mClientSecret;
    }

    public SourceCodeVerification getCodeVerification() {
        return this.mCodeVerification;
    }

    public Long getCreated() {
        return this.mCreated;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public String getFlow() {
        return this.mFlow;
    }

    public Boolean isLiveMode() {
        return this.mLiveMode;
    }

    public Map<String, String> getMetaData() {
        return this.mMetaData;
    }

    public SourceOwner getOwner() {
        return this.mOwner;
    }

    public SourceReceiver getReceiver() {
        return this.mReceiver;
    }

    public SourceRedirect getRedirect() {
        return this.mRedirect;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public Map<String, Object> getSourceTypeData() {
        return this.mSourceTypeData;
    }

    public StripeSourceTypeModel getSourceTypeModel() {
        return this.mSourceTypeModel;
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

    public void setId(String str) {
        this.mId = str;
    }

    public void setAmount(long j) {
        this.mAmount = Long.valueOf(j);
    }

    public void setClientSecret(String str) {
        this.mClientSecret = str;
    }

    public void setCodeVerification(SourceCodeVerification sourceCodeVerification) {
        this.mCodeVerification = sourceCodeVerification;
    }

    public void setCreated(long j) {
        this.mCreated = Long.valueOf(j);
    }

    public void setCurrency(String str) {
        this.mCurrency = str;
    }

    public void setFlow(String str) {
        this.mFlow = str;
    }

    public void setLiveMode(boolean z) {
        this.mLiveMode = Boolean.valueOf(z);
    }

    public void setMetaData(Map<String, String> map) {
        this.mMetaData = map;
    }

    public void setOwner(SourceOwner sourceOwner) {
        this.mOwner = sourceOwner;
    }

    public void setReceiver(SourceReceiver sourceReceiver) {
        this.mReceiver = sourceReceiver;
    }

    public void setRedirect(SourceRedirect sourceRedirect) {
        this.mRedirect = sourceRedirect;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }

    public void setSourceTypeData(Map<String, Object> map) {
        this.mSourceTypeData = map;
    }

    public void setTypeRaw(String str) {
        this.mTypeRaw = str;
        setType("unknown");
    }

    public void setType(String str) {
        this.mType = str;
    }

    public void setUsage(String str) {
        this.mUsage = str;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.mId);
        hashMap.put(FIELD_AMOUNT, this.mAmount);
        hashMap.put(FIELD_CLIENT_SECRET, this.mClientSecret);
        putStripeJsonModelMapIfNotNull(hashMap, "code_verification", this.mCodeVerification);
        hashMap.put(FIELD_CREATED, this.mCreated);
        hashMap.put(FIELD_CURRENCY, this.mCurrency);
        hashMap.put(FIELD_FLOW, this.mFlow);
        hashMap.put(FIELD_LIVEMODE, this.mLiveMode);
        hashMap.put("metadata", this.mMetaData);
        putStripeJsonModelMapIfNotNull(hashMap, FIELD_OWNER, this.mOwner);
        putStripeJsonModelMapIfNotNull(hashMap, "receiver", this.mReceiver);
        putStripeJsonModelMapIfNotNull(hashMap, "redirect", this.mRedirect);
        hashMap.put(this.mTypeRaw, this.mSourceTypeData);
        hashMap.put("status", this.mStatus);
        hashMap.put("type", this.mTypeRaw);
        hashMap.put(FIELD_USAGE, this.mUsage);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            StripeJsonUtils.putStringIfNotNull(jSONObject, "id", this.mId);
            jSONObject.put(FIELD_OBJECT, "source");
            jSONObject.put(FIELD_AMOUNT, this.mAmount);
            StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CLIENT_SECRET, this.mClientSecret);
            putStripeJsonModelIfNotNull(jSONObject, "code_verification", this.mCodeVerification);
            jSONObject.put(FIELD_CREATED, this.mCreated);
            StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CURRENCY, this.mCurrency);
            StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_FLOW, this.mFlow);
            jSONObject.put(FIELD_LIVEMODE, this.mLiveMode);
            JSONObject mapToJsonObject = StripeJsonUtils.mapToJsonObject(this.mMetaData);
            if (mapToJsonObject != null) {
                jSONObject.put("metadata", mapToJsonObject);
            }
            JSONObject mapToJsonObject2 = StripeJsonUtils.mapToJsonObject(this.mSourceTypeData);
            if (mapToJsonObject2 != null) {
                jSONObject.put(this.mTypeRaw, mapToJsonObject2);
            }
            putStripeJsonModelIfNotNull(jSONObject, FIELD_OWNER, this.mOwner);
            putStripeJsonModelIfNotNull(jSONObject, "receiver", this.mReceiver);
            putStripeJsonModelIfNotNull(jSONObject, "redirect", this.mRedirect);
            StripeJsonUtils.putStringIfNotNull(jSONObject, "status", this.mStatus);
            StripeJsonUtils.putStringIfNotNull(jSONObject, "type", this.mTypeRaw);
            StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_USAGE, this.mUsage);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static Source fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Source fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString(FIELD_OBJECT);
        if ("card".equals(optString)) {
            return fromCardJson(jSONObject);
        }
        if ("source".equals(optString)) {
            return fromSourceJson(jSONObject);
        }
        return null;
    }

    private static Source fromCardJson(JSONObject jSONObject) {
        return new Source(StripeJsonUtils.optString(jSONObject, "id"), SourceCardData.fromJson(jSONObject));
    }

    private static Source fromSourceJson(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        String optString = StripeJsonUtils.optString(jSONObject2, "id");
        Long optLong = StripeJsonUtils.optLong(jSONObject2, FIELD_AMOUNT);
        String optString2 = StripeJsonUtils.optString(jSONObject2, FIELD_CLIENT_SECRET);
        SourceCodeVerification sourceCodeVerification = (SourceCodeVerification) optStripeJsonModel(jSONObject2, "code_verification", SourceCodeVerification.class);
        Long optLong2 = StripeJsonUtils.optLong(jSONObject2, FIELD_CREATED);
        String optString3 = StripeJsonUtils.optString(jSONObject2, FIELD_CURRENCY);
        String asSourceFlow = asSourceFlow(StripeJsonUtils.optString(jSONObject2, FIELD_FLOW));
        Boolean valueOf = Boolean.valueOf(jSONObject2.optBoolean(FIELD_LIVEMODE));
        Map<String, String> jsonObjectToStringMap = StripeJsonUtils.jsonObjectToStringMap(jSONObject2.optJSONObject("metadata"));
        SourceOwner sourceOwner = (SourceOwner) optStripeJsonModel(jSONObject2, FIELD_OWNER, SourceOwner.class);
        SourceReceiver sourceReceiver = (SourceReceiver) optStripeJsonModel(jSONObject2, "receiver", SourceReceiver.class);
        SourceRedirect sourceRedirect = (SourceRedirect) optStripeJsonModel(jSONObject2, "redirect", SourceRedirect.class);
        String asSourceStatus = asSourceStatus(StripeJsonUtils.optString(jSONObject2, "status"));
        String optString4 = StripeJsonUtils.optString(jSONObject2, "type");
        if (optString4 == null) {
            optString4 = "unknown";
        }
        return new Source(optString, optLong, optString2, sourceCodeVerification, optLong2, optString3, asSourceFlow, valueOf, jsonObjectToStringMap, sourceOwner, sourceReceiver, sourceRedirect, asSourceStatus, StripeJsonUtils.jsonObjectToMap(jSONObject2.optJSONObject(optString4)), MODELED_TYPES.contains(optString4) ? (StripeSourceTypeModel) optStripeJsonModel(jSONObject2, optString4, StripeSourceTypeModel.class) : null, asSourceType(optString4), optString4, asUsage(StripeJsonUtils.optString(jSONObject2, FIELD_USAGE)));
    }

    private static <T extends StripeJsonModel> T optStripeJsonModel(JSONObject jSONObject, String str, Class<T> cls) {
        if (!jSONObject.has(str)) {
            return null;
        }
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -808719889:
                if (str.equals("receiver")) {
                    c = 0;
                    break;
                }
                break;
            case -776144932:
                if (str.equals("redirect")) {
                    c = 1;
                    break;
                }
                break;
            case 3046160:
                if (str.equals("card")) {
                    c = 2;
                    break;
                }
                break;
            case 106164915:
                if (str.equals(FIELD_OWNER)) {
                    c = 3;
                    break;
                }
                break;
            case 1615551277:
                if (str.equals("code_verification")) {
                    c = 4;
                    break;
                }
                break;
            case 1636477296:
                if (str.equals(SEPA_DEBIT)) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return (StripeJsonModel) cls.cast(SourceReceiver.fromJson(jSONObject.optJSONObject("receiver")));
            case 1:
                return (StripeJsonModel) cls.cast(SourceRedirect.fromJson(jSONObject.optJSONObject("redirect")));
            case 2:
                return (StripeJsonModel) cls.cast(SourceCardData.fromJson(jSONObject.optJSONObject("card")));
            case 3:
                return (StripeJsonModel) cls.cast(SourceOwner.fromJson(jSONObject.optJSONObject(FIELD_OWNER)));
            case 4:
                return (StripeJsonModel) cls.cast(SourceCodeVerification.fromJson(jSONObject.optJSONObject("code_verification")));
            case 5:
                return (StripeJsonModel) cls.cast(SourceSepaDebitData.fromJson(jSONObject.optJSONObject(SEPA_DEBIT)));
            default:
                return null;
        }
    }

    private static String asSourceStatus(String str) {
        if ("pending".equals(str)) {
            return "pending";
        }
        if (CHARGEABLE.equals(str)) {
            return CHARGEABLE;
        }
        if (CONSUMED.equals(str)) {
            return CONSUMED;
        }
        if (CANCELED.equals(str)) {
            return CANCELED;
        }
        if ("failed".equals(str)) {
            return "failed";
        }
        return null;
    }

    static String asSourceType(String str) {
        if ("card".equals(str)) {
            return "card";
        }
        if (THREE_D_SECURE.equals(str)) {
            return THREE_D_SECURE;
        }
        if (GIROPAY.equals(str)) {
            return GIROPAY;
        }
        if (SEPA_DEBIT.equals(str)) {
            return SEPA_DEBIT;
        }
        if (IDEAL.equals(str)) {
            return IDEAL;
        }
        if (SOFORT.equals(str)) {
            return SOFORT;
        }
        if (BANCONTACT.equals(str)) {
            return BANCONTACT;
        }
        if (ALIPAY.equals(str)) {
            return ALIPAY;
        }
        if (P24.equals(str)) {
            return P24;
        }
        if ("unknown".equals(str)) {
        }
        return "unknown";
    }

    private static String asUsage(String str) {
        if (REUSABLE.equals(str)) {
            return REUSABLE;
        }
        if (SINGLE_USE.equals(str)) {
            return SINGLE_USE;
        }
        return null;
    }

    private static String asSourceFlow(String str) {
        if ("redirect".equals(str)) {
            return "redirect";
        }
        if ("receiver".equals(str)) {
            return "receiver";
        }
        if ("code_verification".equals(str)) {
            return "code_verification";
        }
        if ("none".equals(str)) {
            return "none";
        }
        return null;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Source) && typedEquals((Source) obj));
    }

    private boolean typedEquals(Source source) {
        return ObjectUtils.equals(this.mId, source.mId) && ObjectUtils.equals(this.mAmount, source.mAmount) && ObjectUtils.equals(this.mClientSecret, source.mClientSecret) && ObjectUtils.equals(this.mCodeVerification, source.mCodeVerification) && ObjectUtils.equals(this.mCreated, source.mCreated) && ObjectUtils.equals(this.mCurrency, source.mCurrency) && ObjectUtils.equals(this.mTypeRaw, source.mTypeRaw) && ObjectUtils.equals(this.mFlow, source.mFlow) && ObjectUtils.equals(this.mLiveMode, source.mLiveMode) && ObjectUtils.equals(this.mMetaData, source.mMetaData) && ObjectUtils.equals(this.mOwner, source.mOwner) && ObjectUtils.equals(this.mReceiver, source.mReceiver) && ObjectUtils.equals(this.mRedirect, source.mRedirect) && ObjectUtils.equals(this.mStatus, source.mStatus) && ObjectUtils.equals(this.mSourceTypeData, source.mSourceTypeData) && ObjectUtils.equals(this.mSourceTypeModel, source.mSourceTypeModel) && ObjectUtils.equals(this.mType, source.mType) && ObjectUtils.equals(this.mUsage, source.mUsage);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mId, this.mAmount, this.mClientSecret, this.mCodeVerification, this.mCreated, this.mCurrency, this.mTypeRaw, this.mFlow, this.mLiveMode, this.mMetaData, this.mOwner, this.mReceiver, this.mRedirect, this.mStatus, this.mSourceTypeData, this.mSourceTypeModel, this.mType, this.mUsage);
    }
}
