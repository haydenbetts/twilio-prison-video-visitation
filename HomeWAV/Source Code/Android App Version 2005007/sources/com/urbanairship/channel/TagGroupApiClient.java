package com.urbanairship.channel;

import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.Logger;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.net.URL;
import java.util.Iterator;

class TagGroupApiClient {
    private static final String AMAZON_CHANNEL_KEY = "amazon_channel";
    private static final String ANDROID_CHANNEL_KEY = "android_channel";
    private static final String AUDIENCE_KEY = "audience";
    private static final String CHANNEL_TAGS_PATH = "api/channels/tags/";
    private static final String NAMED_USER_ID_KEY = "named_user_id";
    private static final String NAMED_USER_TAG_GROUP_PATH = "api/named_users/tags/";
    private final String audienceKey;
    private final String path;
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    TagGroupApiClient(AirshipRuntimeConfig airshipRuntimeConfig, RequestFactory requestFactory2, String str, String str2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.requestFactory = requestFactory2;
        this.audienceKey = str;
        this.path = str2;
    }

    public static TagGroupApiClient namedUserClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        return new TagGroupApiClient(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY, NAMED_USER_ID_KEY, NAMED_USER_TAG_GROUP_PATH);
    }

    public static TagGroupApiClient channelClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        return new TagGroupApiClient(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY, airshipRuntimeConfig.getPlatform() == 1 ? AMAZON_CHANNEL_KEY : ANDROID_CHANNEL_KEY, CHANNEL_TAGS_PATH);
    }

    /* access modifiers changed from: package-private */
    public Response<Void> updateTags(String str, TagGroupsMutation tagGroupsMutation) throws RequestException {
        URL build = this.runtimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(this.path).build();
        JsonMap build2 = JsonMap.newBuilder().putAll(tagGroupsMutation.toJsonValue().optMap()).put(AUDIENCE_KEY, (JsonSerializable) JsonMap.newBuilder().put(this.audienceKey, str).build()).build();
        Logger.verbose("Updating tag groups with path: %s, payload: %s", this.path, build2);
        Response<Void> execute = this.requestFactory.createRequest(DefaultHttpClient.METHOD_POST, build).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(build2).setAirshipJsonAcceptsHeader().execute();
        logTagGroupResponseIssues(execute);
        return execute;
    }

    /* access modifiers changed from: package-private */
    public String getPath() {
        return this.path;
    }

    /* access modifiers changed from: package-private */
    public String getAudienceKey() {
        return this.audienceKey;
    }

    private void logTagGroupResponseIssues(Response response) {
        if (response != null && response.getResponseBody() != null) {
            try {
                JsonValue parseString = JsonValue.parseString(response.getResponseBody());
                if (parseString.isJsonMap()) {
                    if (parseString.optMap().containsKey("warnings")) {
                        Iterator<JsonValue> it = parseString.optMap().opt("warnings").optList().iterator();
                        while (it.hasNext()) {
                            Logger.warn("Tag Groups warnings: %s", it.next());
                        }
                    }
                    if (parseString.optMap().containsKey("error")) {
                        Logger.error("Tag Groups error: %s", parseString.optMap().get("error"));
                    }
                }
            } catch (JsonException e) {
                Logger.error(e, "Unable to parse tag group response", new Object[0]);
            }
        }
    }
}
