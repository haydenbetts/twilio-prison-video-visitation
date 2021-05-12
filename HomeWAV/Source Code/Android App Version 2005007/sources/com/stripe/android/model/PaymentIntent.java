package com.stripe.android.model;

import android.net.Uri;
import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentIntent extends StripeJsonModel {
    static final String FIELD_ALLOWED_SOURCE_TYPES = "allowed_source_types";
    static final String FIELD_AMOUNT = "amount";
    static final String FIELD_CANCELED = "canceled_at";
    static final String FIELD_CAPTURE_METHOD = "capture_method";
    static final String FIELD_CLIENT_SECRET = "client_secret";
    static final String FIELD_CONFIRMATION_METHOD = "confirmation_method";
    static final String FIELD_CREATED = "created";
    static final String FIELD_CURRENCY = "currency";
    static final String FIELD_DESCRIPTION = "description";
    static final String FIELD_ID = "id";
    static final String FIELD_LIVEMODE = "livemode";
    static final String FIELD_NEXT_ACTION = "next_action";
    static final String FIELD_NEXT_SOURCE_ACTION = "next_source_action";
    static final String FIELD_OBJECT = "object";
    static final String FIELD_PAYMENT_METHOD_TYPES = "payment_method_types";
    static final String FIELD_RECEIPT_EMAIL = "receipt_email";
    static final String FIELD_SOURCE = "source";
    static final String FIELD_STATUS = "status";
    static final String FIELD_TYPE = "type";
    private static final String VALUE_PAYMENT_INTENT = "payment_intent";
    private final List<String> mAllowedSourceTypes;
    private final Long mAmount;
    private final Long mCanceledAt;
    private final String mCaptureMethod;
    private final String mClientSecret;
    private final String mConfirmationMethod;
    private final Long mCreated;
    private final String mCurrency;
    private final String mDescription;
    private final String mId;
    private final Boolean mLiveMode;
    private final Map<String, Object> mNextAction;
    private final Map<String, Object> mNextSourceAction;
    private final String mObjectType;
    private final List<String> mPaymentMethodTypes;
    private final String mReceiptEmail;
    private final String mSource;
    private final String mStatus;

    public String getId() {
        return this.mId;
    }

    @Deprecated
    public List<String> getAllowedSourceTypes() {
        return this.mAllowedSourceTypes;
    }

    public List<String> getPaymentMethodTypes() {
        return this.mPaymentMethodTypes;
    }

    public Long getAmount() {
        return this.mAmount;
    }

    public Long getCanceledAt() {
        return this.mCanceledAt;
    }

    public String getCaptureMethod() {
        return this.mCaptureMethod;
    }

    public String getClientSecret() {
        return this.mClientSecret;
    }

    public String getConfirmationMethod() {
        return this.mConfirmationMethod;
    }

    public Long getCreated() {
        return this.mCreated;
    }

    public String getCurrency() {
        return this.mCurrency;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public Boolean isLiveMode() {
        return this.mLiveMode;
    }

    @Deprecated
    public Map<String, Object> getNextSourceAction() {
        return this.mNextSourceAction;
    }

    public Map<String, Object> getNextAction() {
        return this.mNextAction;
    }

    public Uri getRedirectUrl() {
        Map<String, Object> map;
        Status fromCode = Status.fromCode(this.mStatus);
        if (Status.RequiresAction == fromCode) {
            map = this.mNextAction;
        } else {
            map = Status.RequiresSourceAction == fromCode ? this.mNextSourceAction : null;
        }
        if (map == null) {
            return null;
        }
        NextActionType fromCode2 = NextActionType.fromCode((String) map.get("type"));
        if (NextActionType.RedirectToUrl == fromCode2 || NextActionType.AuthorizeWithUrl == fromCode2) {
            Object obj = map.get(fromCode2.code);
            if (obj instanceof Map) {
                Object obj2 = ((Map) obj).get("url");
                if (obj2 instanceof String) {
                    return Uri.parse((String) obj2);
                }
            }
        }
        return null;
    }

    @Deprecated
    public Uri getAuthorizationUrl() {
        return getRedirectUrl();
    }

    public String getReceiptEmail() {
        return this.mReceiptEmail;
    }

    public String getSource() {
        return this.mSource;
    }

    public String getStatus() {
        return this.mStatus;
    }

    private PaymentIntent(String str, String str2, List<String> list, List<String> list2, Long l, Long l2, String str3, String str4, String str5, Long l3, String str6, String str7, Boolean bool, Map<String, Object> map, Map<String, Object> map2, String str8, String str9, String str10) {
        this.mId = str;
        this.mObjectType = str2;
        this.mAllowedSourceTypes = list;
        this.mPaymentMethodTypes = list2;
        this.mAmount = l;
        this.mCanceledAt = l2;
        this.mCaptureMethod = str3;
        this.mClientSecret = str4;
        this.mConfirmationMethod = str5;
        this.mCreated = l3;
        this.mCurrency = str6;
        this.mDescription = str7;
        this.mLiveMode = bool;
        this.mNextSourceAction = map;
        this.mNextAction = map2;
        this.mReceiptEmail = str8;
        this.mSource = str9;
        this.mStatus = str10;
    }

    public static String parseIdFromClientSecret(String str) {
        return str.split("_secret")[0];
    }

    public static PaymentIntent fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static PaymentIntent fromJson(JSONObject jSONObject) {
        JSONObject jSONObject2 = jSONObject;
        if (jSONObject2 == null || !VALUE_PAYMENT_INTENT.equals(jSONObject2.optString(FIELD_OBJECT))) {
            return null;
        }
        return new PaymentIntent(StripeJsonUtils.optString(jSONObject2, "id"), StripeJsonUtils.optString(jSONObject2, FIELD_OBJECT), jsonArrayToList(jSONObject2.optJSONArray(FIELD_ALLOWED_SOURCE_TYPES)), jsonArrayToList(jSONObject2.optJSONArray(FIELD_PAYMENT_METHOD_TYPES)), StripeJsonUtils.optLong(jSONObject2, FIELD_AMOUNT), StripeJsonUtils.optLong(jSONObject2, FIELD_CANCELED), StripeJsonUtils.optString(jSONObject2, FIELD_CAPTURE_METHOD), StripeJsonUtils.optString(jSONObject2, FIELD_CLIENT_SECRET), StripeJsonUtils.optString(jSONObject2, FIELD_CONFIRMATION_METHOD), StripeJsonUtils.optLong(jSONObject2, FIELD_CREATED), StripeJsonUtils.optCurrency(jSONObject2, FIELD_CURRENCY), StripeJsonUtils.optString(jSONObject2, FIELD_DESCRIPTION), StripeJsonUtils.optBoolean(jSONObject2, FIELD_LIVEMODE), StripeJsonUtils.optMap(jSONObject2, FIELD_NEXT_SOURCE_ACTION), StripeJsonUtils.optMap(jSONObject2, FIELD_NEXT_ACTION), StripeJsonUtils.optString(jSONObject2, FIELD_RECEIPT_EMAIL), StripeJsonUtils.optString(jSONObject2, "source"), StripeJsonUtils.optString(jSONObject2, "status"));
    }

    private static List<String> jsonArrayToList(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (JSONException unused) {
                }
            }
        }
        return arrayList;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject, "id", this.mId);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_OBJECT, this.mObjectType);
        StripeJsonUtils.putArrayIfNotNull(jSONObject, FIELD_ALLOWED_SOURCE_TYPES, StripeJsonUtils.listToJsonArray(this.mAllowedSourceTypes));
        StripeJsonUtils.putArrayIfNotNull(jSONObject, FIELD_PAYMENT_METHOD_TYPES, StripeJsonUtils.listToJsonArray(this.mPaymentMethodTypes));
        StripeJsonUtils.putLongIfNotNull(jSONObject, FIELD_AMOUNT, this.mAmount);
        StripeJsonUtils.putLongIfNotNull(jSONObject, FIELD_CANCELED, this.mCanceledAt);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CAPTURE_METHOD, this.mCaptureMethod);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CLIENT_SECRET, this.mClientSecret);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CONFIRMATION_METHOD, this.mConfirmationMethod);
        StripeJsonUtils.putLongIfNotNull(jSONObject, FIELD_CREATED, this.mCreated);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_CURRENCY, this.mCurrency);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_DESCRIPTION, this.mDescription);
        StripeJsonUtils.putBooleanIfNotNull(jSONObject, FIELD_LIVEMODE, this.mLiveMode);
        StripeJsonUtils.putMapIfNotNull(jSONObject, FIELD_NEXT_SOURCE_ACTION, this.mNextSourceAction);
        StripeJsonUtils.putMapIfNotNull(jSONObject, FIELD_NEXT_ACTION, this.mNextAction);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_RECEIPT_EMAIL, this.mReceiptEmail);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "source", this.mSource);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "status", this.mStatus);
        return jSONObject;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.mId);
        hashMap.put(FIELD_OBJECT, this.mObjectType);
        hashMap.put(FIELD_ALLOWED_SOURCE_TYPES, this.mAllowedSourceTypes);
        hashMap.put(FIELD_PAYMENT_METHOD_TYPES, this.mPaymentMethodTypes);
        hashMap.put(FIELD_AMOUNT, this.mAmount);
        hashMap.put(FIELD_CANCELED, this.mCanceledAt);
        hashMap.put(FIELD_CLIENT_SECRET, this.mClientSecret);
        hashMap.put(FIELD_CAPTURE_METHOD, this.mCaptureMethod);
        hashMap.put(FIELD_CONFIRMATION_METHOD, this.mConfirmationMethod);
        hashMap.put(FIELD_CREATED, this.mCreated);
        hashMap.put(FIELD_CURRENCY, this.mCurrency);
        hashMap.put(FIELD_DESCRIPTION, this.mDescription);
        hashMap.put(FIELD_LIVEMODE, this.mLiveMode);
        hashMap.put(FIELD_NEXT_SOURCE_ACTION, this.mNextSourceAction);
        hashMap.put(FIELD_NEXT_ACTION, this.mNextAction);
        hashMap.put(FIELD_RECEIPT_EMAIL, this.mReceiptEmail);
        hashMap.put("status", this.mStatus);
        hashMap.put("source", this.mSource);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof PaymentIntent) && typedEquals((PaymentIntent) obj));
    }

    private boolean typedEquals(PaymentIntent paymentIntent) {
        return ObjectUtils.equals(this.mId, paymentIntent.mId) && ObjectUtils.equals(this.mObjectType, paymentIntent.mObjectType) && ObjectUtils.equals(this.mAllowedSourceTypes, paymentIntent.mAllowedSourceTypes) && ObjectUtils.equals(this.mAmount, paymentIntent.mAmount) && ObjectUtils.equals(this.mCanceledAt, paymentIntent.mCanceledAt) && ObjectUtils.equals(this.mCaptureMethod, paymentIntent.mCaptureMethod) && ObjectUtils.equals(this.mClientSecret, paymentIntent.mClientSecret) && ObjectUtils.equals(this.mConfirmationMethod, paymentIntent.mConfirmationMethod) && ObjectUtils.equals(this.mCreated, paymentIntent.mCreated) && ObjectUtils.equals(this.mCurrency, paymentIntent.mCurrency) && ObjectUtils.equals(this.mDescription, paymentIntent.mDescription) && ObjectUtils.equals(this.mLiveMode, paymentIntent.mLiveMode) && ObjectUtils.equals(this.mNextSourceAction, paymentIntent.mNextSourceAction) && ObjectUtils.equals(this.mReceiptEmail, paymentIntent.mReceiptEmail) && ObjectUtils.equals(this.mSource, paymentIntent.mSource) && ObjectUtils.equals(this.mStatus, paymentIntent.mStatus);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mId, this.mObjectType, this.mAllowedSourceTypes, this.mAmount, this.mCanceledAt, this.mCaptureMethod, this.mClientSecret, this.mConfirmationMethod, this.mCreated, this.mCurrency, this.mDescription, this.mLiveMode, this.mNextSourceAction, this.mReceiptEmail, this.mSource, this.mStatus);
    }

    public enum NextActionType {
        RedirectToUrl("redirect_to_url"),
        AuthorizeWithUrl("authorize_with_url");
        
        public final String code;

        private NextActionType(String str) {
            this.code = str;
        }

        public static NextActionType fromCode(String str) {
            if (str == null) {
                return null;
            }
            for (NextActionType nextActionType : values()) {
                if (nextActionType.code.equals(str)) {
                    return nextActionType;
                }
            }
            return null;
        }
    }

    public enum Status {
        Canceled(Source.CANCELED),
        Processing("processing"),
        RequiresAction("requires_action"),
        RequiresAuthorization("requires_authorization"),
        RequiresCapture("requires_capture"),
        RequiresConfirmation("requires_confirmation"),
        RequiresPaymentMethod("requires_payment_method"),
        Succeeded(SourceRedirect.SUCCEEDED),
        RequiresSource("requires_source"),
        RequiresSourceAction("requires_source_action");
        
        public final String code;

        private Status(String str) {
            this.code = str;
        }

        public static Status fromCode(String str) {
            if (str == null) {
                return null;
            }
            for (Status status : values()) {
                if (status.code.equals(str)) {
                    return status;
                }
            }
            return null;
        }
    }
}
