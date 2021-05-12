package com.stripe.android.model;

import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SourceCodeVerification extends StripeJsonModel {
    static final String FAILED = "failed";
    private static final String FIELD_ATTEMPTS_REMAINING = "attempts_remaining";
    private static final String FIELD_STATUS = "status";
    private static final int INVALID_ATTEMPTS_REMAINING = -1;
    static final String PENDING = "pending";
    static final String SUCCEEDED = "succeeded";
    private final int mAttemptsRemaining;
    private final String mStatus;

    private SourceCodeVerification(int i, String str) {
        this.mAttemptsRemaining = i;
        this.mStatus = str;
    }

    public int getAttemptsRemaining() {
        return this.mAttemptsRemaining;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(FIELD_ATTEMPTS_REMAINING, Integer.valueOf(this.mAttemptsRemaining));
        String str = this.mStatus;
        if (str != null) {
            hashMap.put("status", str);
        }
        return hashMap;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(FIELD_ATTEMPTS_REMAINING, this.mAttemptsRemaining);
            StripeJsonUtils.putStringIfNotNull(jSONObject, "status", this.mStatus);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static SourceCodeVerification fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static SourceCodeVerification fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new SourceCodeVerification(jSONObject.optInt(FIELD_ATTEMPTS_REMAINING, -1), asStatus(StripeJsonUtils.optString(jSONObject, "status")));
    }

    private static String asStatus(String str) {
        if ("pending".equals(str)) {
            return "pending";
        }
        if ("succeeded".equals(str)) {
            return "succeeded";
        }
        if ("failed".equals(str)) {
            return "failed";
        }
        return null;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SourceCodeVerification) && typedEquals((SourceCodeVerification) obj));
    }

    private boolean typedEquals(SourceCodeVerification sourceCodeVerification) {
        return this.mAttemptsRemaining == sourceCodeVerification.mAttemptsRemaining && ObjectUtils.equals(this.mStatus, sourceCodeVerification.mStatus);
    }

    public int hashCode() {
        return ObjectUtils.hash(Integer.valueOf(this.mAttemptsRemaining), this.mStatus);
    }
}
