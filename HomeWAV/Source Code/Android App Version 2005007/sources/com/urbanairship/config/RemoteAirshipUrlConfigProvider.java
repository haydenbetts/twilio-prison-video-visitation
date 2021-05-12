package com.urbanairship.config;

import com.urbanairship.AirshipConfigOptions;
import com.urbanairship.PreferenceDataStore;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.remoteconfig.RemoteAirshipConfig;
import com.urbanairship.remoteconfig.RemoteAirshipConfigListener;
import com.urbanairship.util.UAStringUtil;

public class RemoteAirshipUrlConfigProvider implements AirshipUrlConfigProvider, RemoteAirshipConfigListener {
    private static final String DISABLE_URL_FALLBACK_KEY = "com.urbanairship.config.DISABLE_URL_FALLBACK_KEY";
    private static final String REMOTE_CONFIG_KEY = "com.urbanairship.config.REMOTE_CONFIG_KEY";
    private final AirshipConfigOptions configOptions;
    private final Object lock = new Object();
    private final PreferenceDataStore preferenceDataStore;
    private AirshipUrlConfig urlConfig;

    public RemoteAirshipUrlConfigProvider(AirshipConfigOptions airshipConfigOptions, PreferenceDataStore preferenceDataStore2) {
        this.configOptions = airshipConfigOptions;
        this.preferenceDataStore = preferenceDataStore2;
    }

    public void disableFallbackUrls() {
        this.preferenceDataStore.put(DISABLE_URL_FALLBACK_KEY, true);
        refreshConfig();
    }

    private void refreshConfig() {
        updateConfig(RemoteAirshipConfig.fromJson(this.preferenceDataStore.getJsonValue(REMOTE_CONFIG_KEY)));
    }

    private void updateConfig(RemoteAirshipConfig remoteAirshipConfig) {
        AirshipUrlConfig airshipUrlConfig;
        if (this.preferenceDataStore.getBoolean(DISABLE_URL_FALLBACK_KEY, false)) {
            airshipUrlConfig = AirshipUrlConfig.newBuilder().setRemoteDataUrl(firstOrNull(remoteAirshipConfig.getRemoteDataUrl(), this.configOptions.remoteDataUrl)).setWalletUrl(remoteAirshipConfig.getWalletUrl()).setAnalyticsUrl(remoteAirshipConfig.getAnalyticsUrl()).setDeviceUrl(remoteAirshipConfig.getDeviceApiUrl()).build();
        } else {
            airshipUrlConfig = AirshipUrlConfig.newBuilder().setRemoteDataUrl(firstOrNull(remoteAirshipConfig.getRemoteDataUrl(), this.configOptions.remoteDataUrl)).setWalletUrl(firstOrNull(remoteAirshipConfig.getWalletUrl(), this.configOptions.walletUrl)).setAnalyticsUrl(firstOrNull(remoteAirshipConfig.getAnalyticsUrl(), this.configOptions.analyticsUrl)).setDeviceUrl(firstOrNull(remoteAirshipConfig.getDeviceApiUrl(), this.configOptions.deviceUrl)).build();
        }
        synchronized (this.lock) {
            this.urlConfig = airshipUrlConfig;
        }
    }

    public AirshipUrlConfig getConfig() {
        AirshipUrlConfig airshipUrlConfig;
        synchronized (this.lock) {
            if (this.urlConfig == null) {
                refreshConfig();
            }
            airshipUrlConfig = this.urlConfig;
        }
        return airshipUrlConfig;
    }

    public void onRemoteConfigUpdated(RemoteAirshipConfig remoteAirshipConfig) {
        updateConfig(remoteAirshipConfig);
        this.preferenceDataStore.put(REMOTE_CONFIG_KEY, (JsonSerializable) remoteAirshipConfig);
    }

    private static String firstOrNull(String... strArr) {
        for (String str : strArr) {
            if (!UAStringUtil.isEmpty(str)) {
                return str;
            }
        }
        return null;
    }
}
