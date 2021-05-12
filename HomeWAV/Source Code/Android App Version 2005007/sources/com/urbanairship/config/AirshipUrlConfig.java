package com.urbanairship.config;

public class AirshipUrlConfig {
    private String analyticsUrl;
    private String deviceUrl;
    private String remoteDataUrl;
    private String walletUrl;

    private AirshipUrlConfig(Builder builder) {
        this.deviceUrl = builder.deviceUrl;
        this.analyticsUrl = builder.analyticsUrl;
        this.walletUrl = builder.walletUrl;
        this.remoteDataUrl = builder.remoteDataUrl;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public UrlBuilder deviceUrl() {
        return new UrlBuilder(this.deviceUrl);
    }

    public UrlBuilder walletUrl() {
        return new UrlBuilder(this.walletUrl);
    }

    public UrlBuilder analyticsUrl() {
        return new UrlBuilder(this.analyticsUrl);
    }

    public UrlBuilder remoteDataUrl() {
        return new UrlBuilder(this.remoteDataUrl);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String analyticsUrl;
        /* access modifiers changed from: private */
        public String deviceUrl;
        /* access modifiers changed from: private */
        public String remoteDataUrl;
        /* access modifiers changed from: private */
        public String walletUrl;

        public Builder setDeviceUrl(String str) {
            this.deviceUrl = str;
            return this;
        }

        public Builder setRemoteDataUrl(String str) {
            this.remoteDataUrl = str;
            return this;
        }

        public Builder setWalletUrl(String str) {
            this.walletUrl = str;
            return this;
        }

        public Builder setAnalyticsUrl(String str) {
            this.analyticsUrl = str;
            return this;
        }

        public AirshipUrlConfig build() {
            return new AirshipUrlConfig(this);
        }
    }
}
