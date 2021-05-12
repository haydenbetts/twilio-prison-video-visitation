package com.urbanairship.channel;

import com.urbanairship.Logger;
import com.urbanairship.config.AirshipRuntimeConfig;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import java.net.URL;

abstract class BaseApiClient {
    private final RequestFactory requestFactory;
    private final AirshipRuntimeConfig runtimeConfig;

    BaseApiClient(AirshipRuntimeConfig airshipRuntimeConfig, RequestFactory requestFactory2) {
        this.requestFactory = requestFactory2;
        this.runtimeConfig = airshipRuntimeConfig;
    }

    /* access modifiers changed from: protected */
    public Response performRequest(URL url, String str, String str2) {
        if (url != null) {
            return this.requestFactory.createRequest(str, url).setCredentials(this.runtimeConfig.getConfigOptions().appKey, this.runtimeConfig.getConfigOptions().appSecret).setRequestBody(str2, "application/json").setAirshipJsonAcceptsHeader().safeExecute();
        }
        Logger.error("Unable to perform request, invalid URL.", new Object[0]);
        return null;
    }
}
