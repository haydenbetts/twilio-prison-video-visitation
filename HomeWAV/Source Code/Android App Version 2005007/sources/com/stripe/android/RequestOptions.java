package com.stripe.android;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RequestOptions {
    public static final String TYPE_JSON = "json_data";
    public static final String TYPE_QUERY = "source";
    private final String mGuid;
    private final String mIdempotencyKey;
    private final String mPublishableApiKey;
    private final String mRequestType;
    private final String mStripeAccount;

    @Retention(RetentionPolicy.SOURCE)
    public @interface RequestType {
    }

    private RequestOptions(String str, String str2, String str3, String str4, String str5) {
        this.mGuid = str;
        this.mIdempotencyKey = str2;
        this.mPublishableApiKey = str3;
        this.mRequestType = str4;
        this.mStripeAccount = str5;
    }

    /* access modifiers changed from: package-private */
    public String getGuid() {
        return this.mGuid;
    }

    /* access modifiers changed from: package-private */
    public String getIdempotencyKey() {
        return this.mIdempotencyKey;
    }

    /* access modifiers changed from: package-private */
    public String getPublishableApiKey() {
        return this.mPublishableApiKey;
    }

    /* access modifiers changed from: package-private */
    public String getRequestType() {
        return this.mRequestType;
    }

    /* access modifiers changed from: package-private */
    public String getStripeAccount() {
        return this.mStripeAccount;
    }

    public static RequestOptionsBuilder builder(String str) {
        return builder(str, "source");
    }

    public static RequestOptionsBuilder builder(String str, String str2, String str3) {
        return new RequestOptionsBuilder(str, str3).setStripeAccount(str2);
    }

    public static RequestOptionsBuilder builder(String str, String str2) {
        return new RequestOptionsBuilder(str, str2);
    }

    public static final class RequestOptionsBuilder {
        private String guid;
        private String idempotencyKey;
        private String publishableApiKey;
        private String requestType;
        private String stripeAccount;

        RequestOptionsBuilder(String str, String str2) {
            this.publishableApiKey = str;
            this.requestType = str2;
        }

        /* access modifiers changed from: package-private */
        public RequestOptionsBuilder setPublishableApiKey(String str) {
            this.publishableApiKey = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public RequestOptionsBuilder setIdempotencyKey(String str) {
            this.idempotencyKey = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public RequestOptionsBuilder setGuid(String str) {
            this.guid = str;
            return this;
        }

        /* access modifiers changed from: package-private */
        public RequestOptionsBuilder setStripeAccount(String str) {
            this.stripeAccount = str;
            return this;
        }

        public RequestOptions build() {
            return new RequestOptions(this.guid, this.idempotencyKey, this.publishableApiKey, this.requestType, this.stripeAccount);
        }
    }
}
