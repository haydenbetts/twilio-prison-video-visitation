package com.braintreepayments.api.models;

import com.urbanairship.channel.ChannelRegistrationPayload;
import org.json.JSONException;
import org.json.JSONObject;

public class MetadataBuilder {
    private static final String INTEGRATION_KEY = "integration";
    public static final String META_KEY = "_meta";
    private static final String PLATFORM_KEY = "platform";
    private static final String SESSION_ID_KEY = "sessionId";
    private static final String SOURCE_KEY = "source";
    private static final String VERSION_KEY = "version";
    private JSONObject mJson;

    public MetadataBuilder() {
        JSONObject jSONObject = new JSONObject();
        this.mJson = jSONObject;
        try {
            jSONObject.put(PLATFORM_KEY, ChannelRegistrationPayload.ANDROID_DEVICE_TYPE);
        } catch (JSONException unused) {
        }
    }

    public MetadataBuilder source(String str) {
        try {
            this.mJson.put("source", str);
        } catch (JSONException unused) {
        }
        return this;
    }

    public MetadataBuilder integration(String str) {
        try {
            this.mJson.put(INTEGRATION_KEY, str);
        } catch (JSONException unused) {
        }
        return this;
    }

    public MetadataBuilder sessionId(String str) {
        try {
            this.mJson.put(SESSION_ID_KEY, str);
        } catch (JSONException unused) {
        }
        return this;
    }

    public MetadataBuilder version() {
        try {
            this.mJson.put("version", "3.14.2");
        } catch (JSONException unused) {
        }
        return this;
    }

    public JSONObject build() {
        return this.mJson;
    }

    public String toString() {
        return this.mJson.toString();
    }
}
