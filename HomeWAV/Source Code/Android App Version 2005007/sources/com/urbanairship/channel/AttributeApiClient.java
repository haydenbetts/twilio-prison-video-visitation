package com.urbanairship.channel;

import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.Logger;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.json.JsonMap;
import java.net.URL;
import java.util.List;

class AttributeApiClient {
    private static final String ATTRIBUTE_PARAM = "attributes";
    private static final String ATTRIBUTE_PAYLOAD_KEY = "attributes";
    private static final String ATTRIBUTE_PLATFORM_AMAZON = "amazon";
    private static final String ATTRIBUTE_PLATFORM_ANDROID = "android";
    private static final String ATTRIBUTE_PLATFORM_QUERY_PARAM = "platform";
    private static final String CHANNEL_API_PATH = "api/channels/";
    static final UrlFactory CHANNEL_URL_FACTORY = new UrlFactory() {
        public URL createUrl(AirshipRuntimeConfig airshipRuntimeConfig, String str) {
            return airshipRuntimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(AttributeApiClient.CHANNEL_API_PATH).appendPath(str).appendPath("attributes").appendQueryParameter(AttributeApiClient.ATTRIBUTE_PLATFORM_QUERY_PARAM, airshipRuntimeConfig.getPlatform() == 1 ? "amazon" : "android").build();
        }
    };
    private static final String NAMED_USER_API_PATH = "api/named_users/";
    static final UrlFactory NAMED_USER_URL_FACTORY = new UrlFactory() {
        public URL createUrl(AirshipRuntimeConfig airshipRuntimeConfig, String str) {
            return airshipRuntimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(AttributeApiClient.NAMED_USER_API_PATH).appendPath(str).appendPath("attributes").build();
        }
    };
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;
    private final UrlFactory urlFactory;

    interface UrlFactory {
        URL createUrl(AirshipRuntimeConfig airshipRuntimeConfig, String str);
    }

    AttributeApiClient(AirshipRuntimeConfig airshipRuntimeConfig, RequestFactory requestFactory2, UrlFactory urlFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.requestFactory = requestFactory2;
        this.urlFactory = urlFactory2;
    }

    public static AttributeApiClient namedUserClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        return new AttributeApiClient(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY, NAMED_USER_URL_FACTORY);
    }

    public static AttributeApiClient channelClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        return new AttributeApiClient(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY, CHANNEL_URL_FACTORY);
    }

    /* access modifiers changed from: package-private */
    public Response<Void> updateAttributes(String str, List<AttributeMutation> list) throws RequestException {
        URL createUrl = this.urlFactory.createUrl(this.runtimeConfig, str);
        JsonMap build = JsonMap.newBuilder().putOpt("attributes", list).build();
        Logger.verbose("Updating attributes for Id:%s with payload: %s", str, build);
        return this.requestFactory.createRequest().setOperation(DefaultHttpClient.METHOD_POST, createUrl).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(build).setAirshipJsonAcceptsHeader().execute();
    }
}
