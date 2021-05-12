package com.urbanairship.automation.tags;

import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.Logger;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.util.UAStringUtil;
import java.net.URL;
import java.util.Map;
import java.util.Set;

class TagGroupLookupApiClient {
    private static final String AMAZON_PLATFORM = "amazon";
    private static final String ANDROID_PLATFORM = "android";
    private static final String CHANNEL_ID_KEY = "channel_id";
    private static final String CHANNEL_TAG_LOOKUP_PATH = "api/channel-tags-lookup";
    private static final String DEVICE_TYPE_KEY = "device_type";
    private static final String IF_MODIFIED_SINCE_KEY = "if_modified_since";
    private static final String TAG_GROUPS_KEY = "tag_groups";
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    TagGroupLookupApiClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        this(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY);
    }

    TagGroupLookupApiClient(AirshipRuntimeConfig airshipRuntimeConfig, RequestFactory requestFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.requestFactory = requestFactory2;
    }

    /* access modifiers changed from: package-private */
    public TagGroupResponse lookupTagGroups(String str, Map<String, Set<String>> map, TagGroupResponse tagGroupResponse) {
        URL build = this.runtimeConfig.getUrlConfig().deviceUrl().appendEncodedPath(CHANNEL_TAG_LOOKUP_PATH).build();
        if (build == null) {
            Logger.debug("Tag Group URL is null, unable to fetch tag groups.", new Object[0]);
            return null;
        }
        String jsonMap = JsonMap.newBuilder().put("channel_id", str).put(DEVICE_TYPE_KEY, this.runtimeConfig.getPlatform() == 1 ? "amazon" : "android").putOpt(TAG_GROUPS_KEY, map).put(IF_MODIFIED_SINCE_KEY, tagGroupResponse != null ? tagGroupResponse.lastModifiedTime : null).build().toString();
        Logger.debug("Looking up tags with payload: %s", jsonMap);
        try {
            try {
                TagGroupResponse fromResponse = TagGroupResponse.fromResponse(this.requestFactory.createRequest().setOperation(DefaultHttpClient.METHOD_POST, build).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(jsonMap, "application/json").setHeader("Accept", "application/vnd.urbanairship+json; version=3;").execute());
                return (fromResponse.status != 200 || tagGroupResponse == null || fromResponse.lastModifiedTime == null || !UAStringUtil.equals(fromResponse.lastModifiedTime, tagGroupResponse.lastModifiedTime)) ? fromResponse : tagGroupResponse;
            } catch (JsonException e) {
                Logger.error(e, "Failed to parse tag group response.", new Object[0]);
                return null;
            }
        } catch (RequestException e2) {
            Logger.error(e2, "Failed to refresh the cache.", new Object[0]);
            return null;
        }
    }
}
