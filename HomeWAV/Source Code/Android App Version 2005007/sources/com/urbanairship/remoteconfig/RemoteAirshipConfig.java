package com.urbanairship.remoteconfig;

import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;

public class RemoteAirshipConfig implements JsonSerializable {
    private static final String ANALYTICS_URL_KEY = "analytics_url";
    private static final String DEVICE_API_URL_KEY = "device_api_url";
    private static final String REMOTE_DATA_URL_KEY = "remote_data_url";
    private static final String WALLET_URL_KEY = "wallet_url";
    private final String analyticsUrl;
    private final String deviceApiUrl;
    private final String remoteDataUrl;
    private final String walletUrl;

    public RemoteAirshipConfig(String str, String str2, String str3, String str4) {
        this.remoteDataUrl = str;
        this.deviceApiUrl = str2;
        this.walletUrl = str3;
        this.analyticsUrl = str4;
    }

    public static RemoteAirshipConfig fromJson(JsonValue jsonValue) {
        JsonMap optMap = jsonValue.optMap();
        return new RemoteAirshipConfig(optMap.opt(REMOTE_DATA_URL_KEY).getString(), optMap.opt(DEVICE_API_URL_KEY).getString(), optMap.opt(WALLET_URL_KEY).getString(), optMap.opt(ANALYTICS_URL_KEY).getString());
    }

    public String getRemoteDataUrl() {
        return this.remoteDataUrl;
    }

    public String getDeviceApiUrl() {
        return this.deviceApiUrl;
    }

    public String getWalletUrl() {
        return this.walletUrl;
    }

    public String getAnalyticsUrl() {
        return this.analyticsUrl;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put(REMOTE_DATA_URL_KEY, this.remoteDataUrl).put(DEVICE_API_URL_KEY, this.deviceApiUrl).put(ANALYTICS_URL_KEY, this.analyticsUrl).put(WALLET_URL_KEY, this.walletUrl).build().toJsonValue();
    }
}
