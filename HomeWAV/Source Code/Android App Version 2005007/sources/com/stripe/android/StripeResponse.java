package com.stripe.android;

import java.util.List;
import java.util.Map;

class StripeResponse {
    private final String mResponseBody;
    private final int mResponseCode;
    private final Map<String, List<String>> mResponseHeaders;

    StripeResponse(int i, String str, Map<String, List<String>> map) {
        this.mResponseCode = i;
        this.mResponseBody = str;
        this.mResponseHeaders = map;
    }

    /* access modifiers changed from: package-private */
    public int getResponseCode() {
        return this.mResponseCode;
    }

    /* access modifiers changed from: package-private */
    public String getResponseBody() {
        return this.mResponseBody;
    }

    /* access modifiers changed from: package-private */
    public Map<String, List<String>> getResponseHeaders() {
        return this.mResponseHeaders;
    }
}
