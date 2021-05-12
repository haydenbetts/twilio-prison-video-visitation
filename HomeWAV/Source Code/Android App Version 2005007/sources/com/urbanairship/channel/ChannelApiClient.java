package com.urbanairship.channel;

import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.Logger;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.config.UrlBuilder;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.http.ResponseParser;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAHttpStatusUtil;
import java.net.URL;
import java.util.List;
import java.util.Map;

class ChannelApiClient {
    private static final String CHANNEL_API_PATH = "api/channels/";
    private static final String CHANNEL_ID_KEY = "channel_id";
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    ChannelApiClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        this(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY);
    }

    ChannelApiClient(AirshipRuntimeConfig airshipRuntimeConfig, RequestFactory requestFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.requestFactory = requestFactory2;
    }

    /* access modifiers changed from: package-private */
    public Response<String> createChannelWithPayload(ChannelRegistrationPayload channelRegistrationPayload) throws RequestException {
        Logger.verbose("ChannelApiClient - Creating channel with payload: %s", channelRegistrationPayload);
        return this.requestFactory.createRequest().setOperation(DefaultHttpClient.METHOD_POST, getDeviceUrl((String) null)).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(channelRegistrationPayload).setAirshipJsonAcceptsHeader().execute(new ResponseParser<String>() {
            public String parseResponse(int i, Map<String, List<String>> map, String str) throws Exception {
                if (UAHttpStatusUtil.inSuccessRange(i)) {
                    return JsonValue.parseString(str).optMap().opt("channel_id").getString();
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: package-private */
    public Response<Void> updateChannelWithPayload(String str, ChannelRegistrationPayload channelRegistrationPayload) throws RequestException {
        Logger.verbose("ChannelApiClient - Updating channel with payload: %s", channelRegistrationPayload);
        return this.requestFactory.createRequest().setOperation("PUT", getDeviceUrl(str)).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(channelRegistrationPayload).setAirshipJsonAcceptsHeader().execute();
    }

    private URL getDeviceUrl(String str) {
        UrlBuilder appendEncodedPath = this.runtimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(CHANNEL_API_PATH);
        if (str != null) {
            appendEncodedPath.appendPath(str);
        }
        return appendEncodedPath.build();
    }
}
