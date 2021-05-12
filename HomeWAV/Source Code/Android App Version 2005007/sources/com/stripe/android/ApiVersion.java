package com.stripe.android;

import com.stripe.android.utils.ObjectUtils;

class ApiVersion {
    static final String DEFAULT_API_VERSION = "2017-06-05";
    private static final ApiVersion DEFAULT_INSTANCE = new ApiVersion(DEFAULT_API_VERSION);
    private final String mCode;

    static ApiVersion create(String str) {
        return new ApiVersion(str);
    }

    static ApiVersion getDefault() {
        return DEFAULT_INSTANCE;
    }

    private ApiVersion(String str) {
        this.mCode = str;
    }

    /* access modifiers changed from: package-private */
    public String getCode() {
        return this.mCode;
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mCode);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ApiVersion) && typedEquals((ApiVersion) obj));
    }

    private boolean typedEquals(ApiVersion apiVersion) {
        return ObjectUtils.equals(this.mCode, apiVersion.mCode);
    }
}
