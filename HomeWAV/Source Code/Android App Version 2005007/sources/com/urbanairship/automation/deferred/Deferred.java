package com.urbanairship.automation.deferred;

import com.urbanairship.automation.ScheduleData;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import java.net.MalformedURLException;
import java.net.URL;

public class Deferred implements ScheduleData {
    private static final String RETRY_ON_TIMEOUT = "retry_on_timeout";
    private static final String URL_KEY = "url";
    public boolean retryOnTimeout;
    public URL url;

    public Deferred(URL url2, boolean z) {
        this.url = url2;
        this.retryOnTimeout = z;
    }

    public URL getUrl() {
        return this.url;
    }

    public boolean isRetriableOnTimeout() {
        return this.retryOnTimeout;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put("url", this.url.toString()).put(RETRY_ON_TIMEOUT, this.retryOnTimeout).build().toJsonValue();
    }

    public static Deferred fromJson(JsonValue jsonValue) throws JsonException {
        String string = jsonValue.optMap().opt("url").getString();
        if (string != null) {
            try {
                return new Deferred(new URL(string), jsonValue.optMap().opt(RETRY_ON_TIMEOUT).getBoolean(true));
            } catch (MalformedURLException e) {
                throw new JsonException("Invalid URL " + string, e);
            }
        } else {
            throw new JsonException("Missing URL");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Deferred deferred = (Deferred) obj;
        if (this.retryOnTimeout != deferred.retryOnTimeout) {
            return false;
        }
        return this.url.equals(deferred.url);
    }

    public int hashCode() {
        return (this.url.hashCode() * 31) + (this.retryOnTimeout ? 1 : 0);
    }
}
