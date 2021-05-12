package com.urbanairship.wallet;

import android.os.Looper;
import android.text.TextUtils;
import com.microsoft.appcenter.http.DefaultHttpClient;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.config.UrlBuilder;
import com.urbanairship.http.Request;
import com.urbanairship.http.RequestFactory;
import com.urbanairship.http.Response;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executor;

public class PassRequest {
    private static final String API_KEY_QUERY_PARAM = "api_key";
    private static final String API_REVISION = "1.2";
    private static final String API_REVISION_HEADER_NAME = "Api-Revision";
    private static final Executor DEFAULT_REQUEST_EXECUTOR = AirshipExecutors.newSerialExecutor();
    private static final String EXTERNAL_ID_KEY = "externalId";
    private static final String FIELDS_KEY = "fields";
    private static final String HEADERS_KEY = "headers";
    private static final String PASS_PATH = "v1/pass";
    private static final String PUBLIC_URL_KEY = "publicURL";
    private static final String PUBLIC_URL_TYPE_KEY = "type";
    private static final String TAG_KEY = "tag";
    /* access modifiers changed from: private */
    public final String apiKey;
    /* access modifiers changed from: private */
    public final String externalId;
    /* access modifiers changed from: private */
    public final Collection<Field> fields;
    /* access modifiers changed from: private */
    public final Collection<Field> headers;
    /* access modifiers changed from: private */
    public CancelableCallback requestCallback;
    private final Executor requestExecutor;
    /* access modifiers changed from: private */
    public final RequestFactory requestFactory;
    /* access modifiers changed from: private */
    public final String tag;
    /* access modifiers changed from: private */
    public final String templateId;
    /* access modifiers changed from: private */
    public final String userName;

    PassRequest(Builder builder, RequestFactory requestFactory2, Executor executor) {
        this.apiKey = builder.apiKey;
        this.userName = builder.userName;
        this.templateId = builder.templateId;
        this.fields = builder.fields;
        this.headers = builder.headers;
        this.tag = builder.tag;
        this.externalId = builder.externalId;
        this.requestFactory = requestFactory2;
        this.requestExecutor = executor;
    }

    PassRequest(Builder builder) {
        this(builder, RequestFactory.DEFAULT_REQUEST_FACTORY, DEFAULT_REQUEST_EXECUTOR);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public void execute(Callback callback) {
        execute(callback, (Looper) null);
    }

    public void execute(Callback callback, Looper looper) {
        if (this.requestCallback == null) {
            this.requestCallback = new CancelableCallback(callback, looper);
            this.requestExecutor.execute(new Runnable() {
                public void run() {
                    JsonMap jsonMap;
                    Logger.info("Requesting pass %s", PassRequest.this.templateId);
                    URL passUrl = PassRequest.this.getPassUrl();
                    if (passUrl == null) {
                        Logger.error("PassRequest - Invalid pass URL", new Object[0]);
                        PassRequest.this.requestCallback.setResult(-1, (Pass) null);
                        return;
                    }
                    JsonMap.Builder newBuilder = JsonMap.newBuilder();
                    for (Field field : PassRequest.this.fields) {
                        newBuilder.putOpt(field.getName(), field.toJsonValue());
                    }
                    if (!PassRequest.this.headers.isEmpty()) {
                        JsonMap.Builder newBuilder2 = JsonMap.newBuilder();
                        for (Field field2 : PassRequest.this.headers) {
                            newBuilder2.putOpt(field2.getName(), field2.toJsonValue());
                        }
                        jsonMap = newBuilder2.build();
                    } else {
                        jsonMap = null;
                    }
                    JsonMap build = JsonMap.newBuilder().putOpt(PassRequest.HEADERS_KEY, jsonMap).put(PassRequest.FIELDS_KEY, (JsonSerializable) newBuilder.build()).putOpt(PassRequest.TAG_KEY, PassRequest.this.tag).put(PassRequest.PUBLIC_URL_KEY, (JsonSerializable) JsonMap.newBuilder().put("type", "multiple").build()).putOpt(PassRequest.EXTERNAL_ID_KEY, PassRequest.this.externalId).build();
                    Request requestBody = PassRequest.this.requestFactory.createRequest(DefaultHttpClient.METHOD_POST, passUrl).setHeader(PassRequest.API_REVISION_HEADER_NAME, PassRequest.API_REVISION).setRequestBody(build.toString(), "application/json");
                    if (PassRequest.this.userName != null) {
                        requestBody.setCredentials(PassRequest.this.userName, PassRequest.this.apiKey);
                    }
                    Logger.debug("PassRequest - Requesting pass %s with payload: %s", passUrl, build);
                    Response<Void> safeExecute = requestBody.safeExecute();
                    if (safeExecute == null) {
                        Logger.error("PassRequest - Failed to get a response.", new Object[0]);
                        PassRequest.this.requestCallback.setResult(-1, (Pass) null);
                        return;
                    }
                    if (safeExecute.getStatus() == 200) {
                        try {
                            JsonValue parseString = JsonValue.parseString(safeExecute.getResponseBody());
                            Logger.debug("PassRequest - Received pass response: %s for pass %s", parseString, passUrl);
                            PassRequest.this.requestCallback.setResult(safeExecute.getStatus(), Pass.parsePass(parseString));
                        } catch (JsonException unused) {
                            Logger.error("PassRequest - Failed to parse response body %s", safeExecute.getResponseBody());
                            return;
                        }
                    } else {
                        Logger.debug("PassRequest - Pass %s request failed with status %s", PassRequest.this.templateId, Integer.valueOf(safeExecute.getStatus()));
                        PassRequest.this.requestCallback.setResult(safeExecute.getStatus(), (Pass) null);
                    }
                    PassRequest.this.requestCallback.run();
                }
            });
            return;
        }
        throw new IllegalStateException("PassRequest can only be executed once.");
    }

    public void cancel() {
        CancelableCallback cancelableCallback = this.requestCallback;
        if (cancelableCallback != null) {
            cancelableCallback.cancel();
        }
    }

    /* access modifiers changed from: package-private */
    public URL getPassUrl() {
        UrlBuilder appendEncodedPath = UAirship.shared().getRuntimeConfig().getUrlConfig().walletUrl().appendEncodedPath(PASS_PATH).appendEncodedPath(this.templateId);
        if (this.userName == null) {
            appendEncodedPath.appendQueryParameter(API_KEY_QUERY_PARAM, this.apiKey);
        }
        return appendEncodedPath.build();
    }

    public String toString() {
        return "PassRequest{ templateId: " + this.templateId + ", fields: " + this.fields + ", tag: " + this.tag + ", externalId: " + this.externalId + ", headers: " + this.headers + " }";
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String apiKey;
        /* access modifiers changed from: private */
        public String externalId;
        /* access modifiers changed from: private */
        public final List<Field> fields = new ArrayList();
        /* access modifiers changed from: private */
        public final List<Field> headers = new ArrayList();
        /* access modifiers changed from: private */
        public String tag;
        /* access modifiers changed from: private */
        public String templateId;
        /* access modifiers changed from: private */
        public String userName;

        public Builder setAuth(String str, String str2) {
            this.apiKey = str2;
            this.userName = str;
            return this;
        }

        public Builder setTemplateId(String str) {
            this.templateId = str;
            return this;
        }

        public Builder addField(Field field) {
            this.fields.add(field);
            return this;
        }

        public Builder setExpirationDate(String str, String str2) {
            this.headers.add(Field.newBuilder().setName("expirationDate").setValue(str).setLabel(str2).build());
            return this;
        }

        public Builder setBarcodeValue(String str, String str2) {
            this.headers.add(Field.newBuilder().setName("barcode_value").setValue(str).setLabel(str2).build());
            return this;
        }

        public Builder setBarcodeAltText(String str, String str2) {
            this.headers.add(Field.newBuilder().setName("barcodeAltText").setValue(str).setLabel(str2).build());
            return this;
        }

        public Builder setTag(String str) {
            this.tag = str;
            return this;
        }

        public Builder setExternalId(String str) {
            this.externalId = str;
            return this;
        }

        public PassRequest build() {
            if (!TextUtils.isEmpty(this.apiKey) && !TextUtils.isEmpty(this.templateId)) {
                return new PassRequest(this);
            }
            throw new IllegalStateException("The apiKey or templateId is missing.");
        }
    }
}
