package com.urbanairship.config;

import com.urbanairship.AirshipConfigOptions;

public class AirshipRuntimeConfig {
    private final AirshipConfigOptions configOptions;
    private final int platform;
    private final AirshipUrlConfigProvider urlConfigProvider;

    public AirshipRuntimeConfig(int i, AirshipConfigOptions airshipConfigOptions, AirshipUrlConfigProvider airshipUrlConfigProvider) {
        this.platform = i;
        this.configOptions = airshipConfigOptions;
        this.urlConfigProvider = airshipUrlConfigProvider;
    }

    public int getPlatform() {
        return this.platform;
    }

    public AirshipUrlConfig getUrlConfig() {
        return this.urlConfigProvider.getConfig();
    }

    public AirshipConfigOptions getConfigOptions() {
        return this.configOptions;
    }
}
