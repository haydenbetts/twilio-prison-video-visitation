package com.urbanairship.analytics.data;

import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.Logger;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.Request;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonValue;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

public class EventApiClient {
    private static final String WARP9_PATH = "warp9/";
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    public EventApiClient(AirshipRuntimeConfig airshipRuntimeConfig) {
        this(airshipRuntimeConfig, RequestFactory.DEFAULT_REQUEST_FACTORY);
    }

    EventApiClient(AirshipRuntimeConfig airshipRuntimeConfig, RequestFactory requestFactory2) {
        this.runtimeConfig = airshipRuntimeConfig;
        this.requestFactory = requestFactory2;
    }

    /* access modifiers changed from: package-private */
    public EventResponse sendEvents(Collection<String> collection, Map<String, String> map) {
        if (collection.isEmpty()) {
            Logger.verbose("EventApiClient - No analytics events to send.", new Object[0]);
            return null;
        }
        URL build = this.runtimeConfig.getUrlConfig().analyticsUrl().appendEncodedPath(WARP9_PATH).build();
        if (build == null) {
            Logger.debug("Analytics URL is null, unable to send events.", new Object[0]);
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (String parseString : collection) {
            try {
                arrayList.add(JsonValue.parseString(parseString));
            } catch (JsonException e) {
                Logger.error(e, "EventApiClient - Invalid eventPayload.", new Object[0]);
            }
        }
        Request addHeaders = this.requestFactory.createRequest(DefaultHttpClient.METHOD_POST, build).setRequestBody(new JsonList(arrayList).toString(), "application/json").setCompressRequestBody(true).setHeader("X-UA-Sent-At", String.format(Locale.US, "%.3f", new Object[]{Double.valueOf(((double) System.currentTimeMillis()) / 1000.0d)})).addHeaders(map);
        Logger.debug("EventApiClient - Sending analytics events. Request: %s Events: %s", addHeaders, collection);
        Response<Void> safeExecute = addHeaders.safeExecute();
        Logger.debug("EventApiClient - Analytics event response: %s", safeExecute);
        if (safeExecute == null) {
            return null;
        }
        return new EventResponse(safeExecute);
    }
}
