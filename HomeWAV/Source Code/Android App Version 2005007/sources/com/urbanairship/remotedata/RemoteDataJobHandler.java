package com.urbanairship.remotedata;

import android.content.Context;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.http.Response;
import com.urbanairship.job.JobInfo;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.locale.LocaleManager;
import com.urbanairship.util.UAStringUtil;
import java.util.Locale;

public class RemoteDataJobHandler {
    static final String ACTION_REFRESH = "ACTION_REFRESH";
    private final RemoteDataApiClient apiClient;
    private final LocaleManager localeManager;
    private final RemoteData remoteData;

    RemoteDataJobHandler(Context context, UAirship uAirship) {
        this(uAirship.getRemoteData(), new RemoteDataApiClient(uAirship.getRuntimeConfig(), uAirship.getPushProviders()), UAirship.shared().getLocaleManager());
    }

    RemoteDataJobHandler(RemoteData remoteData2, RemoteDataApiClient remoteDataApiClient, LocaleManager localeManager2) {
        this.apiClient = remoteDataApiClient;
        this.remoteData = remoteData2;
        this.localeManager = localeManager2;
    }

    /* access modifiers changed from: protected */
    public int performJob(JobInfo jobInfo) {
        String action = jobInfo.getAction();
        action.hashCode();
        if (!action.equals(ACTION_REFRESH)) {
            return 0;
        }
        return onRefresh();
    }

    private int onRefresh() {
        String lastModified = this.remoteData.getLastModified();
        Locale locale = this.localeManager.getLocale();
        Response fetchRemoteData = this.apiClient.fetchRemoteData(lastModified, locale);
        if (fetchRemoteData == null) {
            Logger.debug("Unable to connect to remote data server, retrying later", new Object[0]);
            return 1;
        }
        int status = fetchRemoteData.getStatus();
        if (status == 200) {
            String responseBody = fetchRemoteData.getResponseBody();
            if (UAStringUtil.isEmpty(responseBody)) {
                Logger.error("Remote data missing response body", new Object[0]);
                return 0;
            }
            Logger.debug("Received remote data response: %s", responseBody);
            String responseHeader = fetchRemoteData.getResponseHeader("Last-Modified");
            JsonMap createMetadata = RemoteData.createMetadata(locale);
            try {
                JsonMap optMap = JsonValue.parseString(responseBody).optMap();
                if (optMap.containsKey("payloads")) {
                    this.remoteData.onNewData(RemoteDataPayload.parsePayloads(optMap.opt("payloads"), createMetadata), responseHeader, createMetadata);
                    this.remoteData.onRefreshFinished();
                }
                return 0;
            } catch (JsonException unused) {
                Logger.error("Unable to parse body: %s", responseBody);
                return 0;
            }
        } else if (status == 304) {
            Logger.debug("Remote data not modified since last refresh", new Object[0]);
            this.remoteData.onRefreshFinished();
            return 0;
        } else {
            Logger.debug("Error fetching remote data: %s", String.valueOf(status));
            return 1;
        }
    }
}
