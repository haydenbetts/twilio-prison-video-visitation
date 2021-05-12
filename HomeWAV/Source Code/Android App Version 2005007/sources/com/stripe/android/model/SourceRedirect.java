package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SourceRedirect extends StripeJsonModel {
    public static final String FAILED = "failed";
    private static final String FIELD_RETURN_URL = "return_url";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_URL = "url";
    public static final String NOT_REQUIRED = "not_required";
    public static final String PENDING = "pending";
    public static final String SUCCEEDED = "succeeded";
    private String mReturnUrl;
    private String mStatus;
    private String mUrl;

    private SourceRedirect(String str, String str2, String str3) {
        this.mReturnUrl = str;
        this.mStatus = str2;
        this.mUrl = str3;
    }

    public String getReturnUrl() {
        return this.mReturnUrl;
    }

    public void setReturnUrl(String str) {
        this.mReturnUrl = str;
    }

    public String getStatus() {
        return this.mStatus;
    }

    public void setStatus(String str) {
        this.mStatus = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(FIELD_RETURN_URL, this.mReturnUrl);
        hashMap.put("status", this.mStatus);
        hashMap.put("url", this.mUrl);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_RETURN_URL, this.mReturnUrl);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "status", this.mStatus);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "url", this.mUrl);
        return jSONObject;
    }

    public static SourceRedirect fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static SourceRedirect fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new SourceRedirect(StripeJsonUtils.optString(jSONObject, FIELD_RETURN_URL), asStatus(StripeJsonUtils.optString(jSONObject, "status")), StripeJsonUtils.optString(jSONObject, "url"));
    }

    static String asStatus(String str) {
        if ("pending".equals(str)) {
            return "pending";
        }
        if (SUCCEEDED.equals(str)) {
            return SUCCEEDED;
        }
        if ("failed".equals(str)) {
            return "failed";
        }
        if (NOT_REQUIRED.equals(str)) {
            return NOT_REQUIRED;
        }
        return null;
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SourceRedirect) && typedEquals((SourceRedirect) obj));
    }

    private boolean typedEquals(SourceRedirect sourceRedirect) {
        return ObjectUtils.equals(this.mReturnUrl, sourceRedirect.mReturnUrl) && ObjectUtils.equals(this.mStatus, sourceRedirect.mStatus) && ObjectUtils.equals(this.mUrl, sourceRedirect.mUrl);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mReturnUrl, this.mStatus, this.mUrl);
    }
}
