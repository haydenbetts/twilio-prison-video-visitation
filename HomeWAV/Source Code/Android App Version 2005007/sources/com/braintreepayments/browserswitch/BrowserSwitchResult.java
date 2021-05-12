package com.braintreepayments.browserswitch;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONObject;

public class BrowserSwitchResult {
    public static final int STATUS_CANCELED = 2;
    public static final int STATUS_ERROR = 3;
    public static final int STATUS_OK = 1;
    private final String errorMessage;
    final JSONObject requestMetadata;
    private final int status;

    @Retention(RetentionPolicy.SOURCE)
    public @interface BrowserSwitchStatus {
    }

    BrowserSwitchResult(int i, String str) {
        this(i, str, (JSONObject) null);
    }

    BrowserSwitchResult(int i, String str, JSONObject jSONObject) {
        this.status = i;
        this.errorMessage = str;
        this.requestMetadata = jSONObject;
    }

    public int getStatus() {
        return this.status;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public JSONObject getRequestMetadata() {
        return this.requestMetadata;
    }
}
