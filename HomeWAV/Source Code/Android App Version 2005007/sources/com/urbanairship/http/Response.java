package com.urbanairship.http;

import com.urbanairship.util.UAHttpStatusUtil;
import java.util.List;
import java.util.Map;

public class Response<T> {
    public static final int HTTP_TOO_MANY_REQUESTS = 429;
    private final long lastModified;
    private final String responseBody;
    private final Map<String, List<String>> responseHeaders;
    private final T result;
    private final int status;

    private Response(Builder<T> builder) {
        this.status = builder.status;
        this.responseBody = builder.responseBody;
        this.responseHeaders = builder.responseHeaders;
        this.lastModified = builder.lastModified;
        this.result = builder.result;
    }

    protected Response(Response<T> response) {
        this.status = response.status;
        this.responseBody = response.responseBody;
        this.responseHeaders = response.responseHeaders;
        this.lastModified = response.lastModified;
        this.result = response.result;
    }

    public T getResult() {
        return this.result;
    }

    public String getResponseHeader(String str) {
        List list;
        Map<String, List<String>> map = this.responseHeaders;
        if (map == null || (list = map.get(str)) == null || list.size() <= 0) {
            return null;
        }
        return (String) list.get(0);
    }

    public String toString() {
        return "Response{responseBody='" + this.responseBody + '\'' + ", responseHeaders=" + this.responseHeaders + ", status=" + this.status + ", lastModified=" + this.lastModified + '}';
    }

    public int getStatus() {
        return this.status;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public long getLastModifiedTime() {
        return this.lastModified;
    }

    public boolean isSuccessful() {
        return UAHttpStatusUtil.inSuccessRange(this.status);
    }

    public boolean isServerError() {
        return UAHttpStatusUtil.inServerErrorRange(this.status);
    }

    public boolean isClientError() {
        return UAHttpStatusUtil.inClientErrorRange(this.status);
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.responseHeaders;
    }

    public boolean isTooManyRequestsError() {
        return this.status == 429;
    }

    public static class Builder<T> {
        /* access modifiers changed from: private */
        public long lastModified = 0;
        /* access modifiers changed from: private */
        public String responseBody;
        /* access modifiers changed from: private */
        public Map<String, List<String>> responseHeaders;
        /* access modifiers changed from: private */
        public T result;
        /* access modifiers changed from: private */
        public final int status;

        public Builder(int i) {
            this.status = i;
        }

        public Builder<T> setResponseBody(String str) {
            this.responseBody = str;
            return this;
        }

        public Builder<T> setResponseHeaders(Map<String, List<String>> map) {
            this.responseHeaders = map;
            return this;
        }

        public Builder<T> setLastModified(long j) {
            this.lastModified = j;
            return this;
        }

        public Builder<T> setResult(T t) {
            this.result = t;
            return this;
        }

        public Response<T> build() {
            return new Response<>(this);
        }
    }
}
