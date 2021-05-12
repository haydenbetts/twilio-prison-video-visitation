package com.urbanairship.automation.deferred;

import com.microsoft.appcenter.Constants;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.automation.TriggerContext;
import com.urbanairship.automation.auth.AuthException;
import com.urbanairship.automation.auth.AuthManager;
import com.urbanairship.channel.AttributeMutation;
import com.urbanairship.channel.TagGroupsMutation;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.Request;
import com.urbanairship.http.RequestException;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.http.ResponseParser;
import com.urbanairship.iam.InAppMessage;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.UAHttpStatusUtil;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class DeferredScheduleClient {
    private static final String ATTRIBUTE_OVERRIDES_KEY = "attribute_overrides";
    private static final String AUDIENCE_MATCH_KEY = "audience_match";
    private static final String CHANNEL_ID_KEY = "channel_id";
    private static final String IN_APP_MESSAGE_TYPE = "in_app_message";
    private static final String MESSAGE_KEY = "message";
    private static final String PLATFORM_AMAZON = "amazon";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_KEY = "platform";
    private static final String RESPONSE_TYPE_KEY = "type";
    private static final String TAG_OVERRIDES_KEY = "tag_overrides";
    private static final String TRIGGER_EVENT_KEY = "event";
    private static final String TRIGGER_GOAL_KEY = "goal";
    private static final String TRIGGER_KEY = "trigger";
    private static final String TRIGGER_TYPE_KEY = "type";
    private final AuthManager authManager;
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    public DeferredScheduleClient(AirshipRuntimeConfig airshipRuntimeConfig, AuthManager authManager2) {
        this(airshipRuntimeConfig, authManager2, RequestFactory.DEFAULT_REQUEST_FACTORY);
    }

    DeferredScheduleClient(AirshipRuntimeConfig airshipRuntimeConfig, AuthManager authManager2, RequestFactory requestFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.authManager = authManager2;
        this.requestFactory = requestFactory2;
    }

    public Response<Result> performRequest(URL url, String str, TriggerContext triggerContext, List<TagGroupsMutation> list, List<AttributeMutation> list2) throws RequestException, AuthException {
        String token = this.authManager.getToken();
        JsonMap.Builder put = JsonMap.newBuilder().put(PLATFORM_KEY, this.runtimeConfig.getPlatform() == 1 ? "amazon" : "android").put("channel_id", str);
        if (triggerContext != null) {
            put.put(TRIGGER_KEY, (JsonSerializable) JsonMap.newBuilder().put("type", triggerContext.getTrigger().getTriggerName()).put(TRIGGER_GOAL_KEY, triggerContext.getTrigger().getGoal()).put("event", (JsonSerializable) triggerContext.getEvent()).build());
        }
        if (!list.isEmpty()) {
            put.put(TAG_OVERRIDES_KEY, (JsonSerializable) JsonValue.wrapOpt(list));
        }
        if (!list2.isEmpty()) {
            put.put(ATTRIBUTE_OVERRIDES_KEY, (JsonSerializable) JsonValue.wrapOpt(list2));
        }
        JsonMap build = put.build();
        Response<Result> performRequest = performRequest(url, token, build);
        if (performRequest.getStatus() != 401) {
            return performRequest;
        }
        this.authManager.tokenExpired(token);
        return performRequest(url, this.authManager.getToken(), build);
    }

    private Response<Result> performRequest(URL url, String str, JsonMap jsonMap) throws RequestException {
        Request operation = this.requestFactory.createRequest().setOperation(DefaultHttpClient.METHOD_POST, url);
        return operation.setHeader(Constants.AUTHORIZATION_HEADER, "Bearer " + str).setAirshipJsonAcceptsHeader().setRequestBody(jsonMap).execute(new ResponseParser<Result>() {
            public Result parseResponse(int i, Map<String, List<String>> map, String str) throws Exception {
                if (UAHttpStatusUtil.inSuccessRange(i)) {
                    return DeferredScheduleClient.this.parseResponseBody(str);
                }
                return null;
            }
        });
    }

    /* access modifiers changed from: private */
    public Result parseResponseBody(String str) throws JsonException {
        JsonMap optMap = JsonValue.parseString(str).optMap();
        return new Result(optMap.opt(AUDIENCE_MATCH_KEY).getBoolean(false), optMap.opt("type").optString().equals("in_app_message") ? InAppMessage.fromJson(optMap.opt("message")) : null);
    }

    public static class Result {
        private final boolean isAudienceMatch;
        private final InAppMessage message;

        public Result(boolean z, InAppMessage inAppMessage) {
            this.isAudienceMatch = z;
            this.message = inAppMessage;
        }

        public InAppMessage getMessage() {
            return this.message;
        }

        public boolean isAudienceMatch() {
            return this.isAudienceMatch;
        }
    }
}
