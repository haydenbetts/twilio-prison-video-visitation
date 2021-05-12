package com.urbanairship.channel;

import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.json.JsonMap;
import java.net.URL;

class NamedUserApiClient {
    private static final String ASSOCIATE_PATH = "api/named_users/associate/";
    private static final String CHANNEL_KEY = "channel_id";
    private static final String DEVICE_TYPE_KEY = "device_type";
    private static final String DISASSOCIATE_PATH = "api/named_users/disassociate/";
    private static final String NAMED_USER_ID_KEY = "named_user_id";
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    NamedUserApiClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        this(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY);
    }

    NamedUserApiClient(AirshipRuntimeConfig airshipRuntimeConfig, RequestFactory requestFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.requestFactory = requestFactory2;
    }

    /* access modifiers changed from: package-private */
    public Response<Void> associate(String str, String str2) throws RequestException {
        URL build = this.runtimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(ASSOCIATE_PATH).build();
        return this.requestFactory.createRequest().setOperation(DefaultHttpClient.METHOD_POST, build).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(JsonMap.newBuilder().put("channel_id", str2).put(DEVICE_TYPE_KEY, getDeviceType()).put(NAMED_USER_ID_KEY, str).build()).setAirshipJsonAcceptsHeader().execute();
    }

    /* access modifiers changed from: package-private */
    public Response<Void> disassociate(String str) throws RequestException {
        URL build = this.runtimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(DISASSOCIATE_PATH).build();
        return this.requestFactory.createRequest().setOperation(DefaultHttpClient.METHOD_POST, build).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(JsonMap.newBuilder().put("channel_id", str).put(DEVICE_TYPE_KEY, getDeviceType()).build()).setAirshipJsonAcceptsHeader().execute();
    }

    /* access modifiers changed from: package-private */
    public String getDeviceType() {
        return this.runtimeConfig.getPlatform() != 1 ? ChannelRegistrationPayload.ANDROID_DEVICE_TYPE : ChannelRegistrationPayload.AMAZON_DEVICE_TYPE;
    }
}
