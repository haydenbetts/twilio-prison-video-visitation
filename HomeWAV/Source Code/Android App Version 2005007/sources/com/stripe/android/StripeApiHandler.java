package com.stripe.android;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.microsoft.appcenter.Constants;
import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.exception.APIConnectionException;
import com.stripe.android.exception.APIException;
import com.stripe.android.exception.AuthenticationException;
import com.stripe.android.exception.CardException;
import com.stripe.android.exception.InvalidRequestException;
import com.stripe.android.exception.PermissionException;
import com.stripe.android.exception.RateLimitException;
import com.stripe.android.exception.StripeException;
import com.stripe.android.model.Customer;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentIntentParams;
import com.stripe.android.model.PaymentMethod;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.model.ShippingInformation;
import com.stripe.android.model.Source;
import com.stripe.android.model.SourceParams;
import com.stripe.android.model.Token;
import com.urbanairship.channel.ChannelRegistrationPayload;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import wseemann.media.FFmpegMediaMetadataRetriever;

class StripeApiHandler {
    private static final String CHARSET = "UTF-8";
    private static final String CUSTOMERS = "customers";
    static final String DELETE = "DELETE";
    private static final String DNS_CACHE_TTL_PROPERTY_NAME = "networkaddress.cache.ttl";
    static final String GET = "GET";
    private static final String LIVE_API_BASE = "https://api.stripe.com";
    private static final String LIVE_LOGGING_BASE = "https://q.stripe.com";
    private static final String LOGGING_ENDPOINT = "https://m.stripe.com/4";
    private static final String PAYMENT_METHODS = "payment_methods";
    static final String POST = "POST";
    private static final String SOURCES = "sources";
    private static final SSLSocketFactory SSL_SOCKET_FACTORY = new StripeSSLSocketFactory();
    private static final String TOKENS = "tokens";
    private final ApiVersion mApiVersion;

    interface LoggingResponseListener {
        void onLoggingResponse(StripeResponse stripeResponse);

        void onStripeException(StripeException stripeException);

        boolean shouldLogTest();
    }

    interface StripeResponseListener {
        void onStripeResponse(StripeResponse stripeResponse);
    }

    StripeApiHandler() {
        this(ApiVersion.getDefault());
    }

    private StripeApiHandler(ApiVersion apiVersion) {
        this.mApiVersion = apiVersion;
    }

    /* access modifiers changed from: package-private */
    public void logApiCall(Map<String, Object> map, RequestOptions requestOptions, LoggingResponseListener loggingResponseListener) {
        String publishableApiKey;
        if (requestOptions == null) {
            return;
        }
        if ((loggingResponseListener == null || loggingResponseListener.shouldLogTest()) && (publishableApiKey = requestOptions.getPublishableApiKey()) != null && !publishableApiKey.trim().isEmpty()) {
            fireAndForgetApiCall(map, LIVE_LOGGING_BASE, "GET", requestOptions, loggingResponseListener);
        }
    }

    /* access modifiers changed from: package-private */
    public PaymentIntent confirmPaymentIntent(StripeNetworkUtils.UidProvider uidProvider, Context context, PaymentIntentParams paymentIntentParams, String str, String str2, LoggingResponseListener loggingResponseListener) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Map<String, Object> paramMap = paymentIntentParams.toParamMap();
        StripeNetworkUtils.addUidParamsToPaymentIntent(uidProvider, context, paramMap);
        RequestOptions build = RequestOptions.builder(str, str2, "source").build();
        try {
            String publishableApiKey = build.getPublishableApiKey();
            if (StripeTextUtils.isBlank(publishableApiKey)) {
                return null;
            }
            setTelemetryData(context, loggingResponseListener);
            SourceParams sourceParams = paymentIntentParams.getSourceParams();
            logApiCall(LoggingUtils.getPaymentIntentConfirmationParams(context, (List<String>) null, publishableApiKey, sourceParams != null ? sourceParams.getType() : null), RequestOptions.builder(str).build(), loggingResponseListener);
            return PaymentIntent.fromString(requestData("POST", confirmPaymentIntentUrl(PaymentIntent.parseIdFromClientSecret(paymentIntentParams.getClientSecret())), paramMap, build).getResponseBody());
        } catch (CardException e) {
            CardException cardException = e;
            throw new APIException(cardException.getMessage(), cardException.getRequestId(), cardException.getStatusCode(), (StripeError) null, cardException);
        }
    }

    /* access modifiers changed from: package-private */
    public PaymentIntent retrievePaymentIntent(Context context, PaymentIntentParams paymentIntentParams, String str, String str2, LoggingResponseListener loggingResponseListener) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Map<String, Object> paramMap = paymentIntentParams.toParamMap();
        RequestOptions build = RequestOptions.builder(str, str2, "source").build();
        try {
            String publishableApiKey = build.getPublishableApiKey();
            if (StripeTextUtils.isBlank(publishableApiKey)) {
                return null;
            }
            setTelemetryData(context, loggingResponseListener);
            logApiCall(LoggingUtils.getPaymentIntentRetrieveParams(context, (List<String>) null, publishableApiKey), RequestOptions.builder(str).build(), loggingResponseListener);
            return PaymentIntent.fromString(requestData("GET", retrievePaymentIntentUrl(PaymentIntent.parseIdFromClientSecret(paymentIntentParams.getClientSecret())), paramMap, build).getResponseBody());
        } catch (CardException e) {
            CardException cardException = e;
            throw new APIException(cardException.getMessage(), cardException.getRequestId(), cardException.getStatusCode(), (StripeError) null, cardException);
        }
    }

    /* access modifiers changed from: package-private */
    public Source createSource(StripeNetworkUtils.UidProvider uidProvider, Context context, SourceParams sourceParams, String str, String str2, LoggingResponseListener loggingResponseListener) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        return createSource(uidProvider, context, sourceParams, str, str2, loggingResponseListener, (StripeResponseListener) null);
    }

    /* access modifiers changed from: package-private */
    public Source createSource(StripeNetworkUtils.UidProvider uidProvider, Context context, SourceParams sourceParams, String str, String str2, LoggingResponseListener loggingResponseListener, StripeResponseListener stripeResponseListener) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Map<String, Object> paramMap = sourceParams.toParamMap();
        StripeNetworkUtils.addUidParams(uidProvider, context, paramMap);
        RequestOptions build = RequestOptions.builder(str, str2, "source").build();
        try {
            String publishableApiKey = build.getPublishableApiKey();
            if (StripeTextUtils.isBlank(publishableApiKey)) {
                return null;
            }
            setTelemetryData(context, loggingResponseListener);
            logApiCall(LoggingUtils.getSourceCreationParams(context, (List<String>) null, publishableApiKey, sourceParams.getType()), RequestOptions.builder(str).build(), loggingResponseListener);
            StripeResponse requestData = requestData("POST", getSourcesUrl(), paramMap, build);
            if (stripeResponseListener != null) {
                stripeResponseListener.onStripeResponse(requestData);
            }
            return Source.fromString(requestData.getResponseBody());
        } catch (CardException e) {
            CardException cardException = e;
            throw new APIException(cardException.getMessage(), cardException.getRequestId(), cardException.getStatusCode(), (StripeError) null, cardException);
        }
    }

    /* access modifiers changed from: package-private */
    public Source retrieveSource(String str, String str2, String str3, String str4) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        RequestOptions requestOptions;
        Map<String, Object> createRetrieveSourceParams = SourceParams.createRetrieveSourceParams(str2);
        if (str4 == null) {
            requestOptions = RequestOptions.builder(str3).build();
        } else {
            requestOptions = RequestOptions.builder(str3, str4, "source").build();
        }
        try {
            return Source.fromString(requestData("GET", getRetrieveSourceApiUrl(str), createRetrieveSourceParams, requestOptions).getResponseBody());
        } catch (CardException e) {
            CardException cardException = e;
            throw new APIException(cardException.getMessage(), cardException.getRequestId(), cardException.getStatusCode(), (StripeError) null, cardException);
        }
    }

    /* access modifiers changed from: package-private */
    public PaymentMethod createPaymentMethod(PaymentMethodCreateParams paymentMethodCreateParams, Context context, String str, String str2, LoggingResponseListener loggingResponseListener) throws AuthenticationException, InvalidRequestException, APIConnectionException, APIException {
        Map<String, Object> paramMap = paymentMethodCreateParams.toParamMap();
        StripeNetworkUtils.addUidParams((StripeNetworkUtils.UidProvider) null, context, paramMap);
        RequestOptions build = RequestOptions.builder(str, str2, "source").build();
        String publishableApiKey = build.getPublishableApiKey();
        if (StripeTextUtils.isBlank(publishableApiKey)) {
            return null;
        }
        setTelemetryData(context, loggingResponseListener);
        logApiCall(LoggingUtils.getPaymentMethodCreationParams(context, (List<String>) null, publishableApiKey), RequestOptions.builder(str).build(), loggingResponseListener);
        try {
            return PaymentMethod.fromString(requestData("POST", getPaymentMethodsUrl(), paramMap, build).getResponseBody());
        } catch (CardException e) {
            CardException cardException = e;
            throw new APIException(cardException.getMessage(), cardException.getRequestId(), cardException.getStatusCode(), (StripeError) null, cardException);
        }
    }

    /* access modifiers changed from: package-private */
    public Token createToken(Context context, Map<String, Object> map, RequestOptions requestOptions, String str, LoggingResponseListener loggingResponseListener) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        try {
            String publishableApiKey = requestOptions.getPublishableApiKey();
            if (StripeTextUtils.isBlank(publishableApiKey)) {
                return null;
            }
            map.remove("product_usage");
            setTelemetryData(context, loggingResponseListener);
            logApiCall(LoggingUtils.getTokenCreationParams(context, (List) map.get("product_usage"), publishableApiKey, str), requestOptions, loggingResponseListener);
            return requestToken(getTokensUrl(), map, requestOptions);
        } catch (ClassCastException unused) {
            map.remove("product_usage");
        }
    }

    /* access modifiers changed from: package-private */
    public Source addCustomerSource(Context context, String str, String str2, List<String> list, String str3, String str4, String str5, LoggingResponseListener loggingResponseListener) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        hashMap.put("source", str3);
        if (context != null) {
            logApiCall(LoggingUtils.getAddSourceParams(context, list, str2, str4), RequestOptions.builder(str2).build(), loggingResponseListener);
        }
        StripeResponse stripeResponse = getStripeResponse("POST", getAddCustomerSourceUrl(str), hashMap, RequestOptions.builder(str5).build());
        convertErrorsToExceptionsAndThrowIfNecessary(stripeResponse);
        return Source.fromString(stripeResponse.getResponseBody());
    }

    /* access modifiers changed from: package-private */
    public Source deleteCustomerSource(Context context, String str, String str2, List<String> list, String str3, String str4, LoggingResponseListener loggingResponseListener) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        if (context != null) {
            logApiCall(LoggingUtils.getDeleteSourceParams(context, list, str2), RequestOptions.builder(str2).build(), loggingResponseListener);
        }
        StripeResponse stripeResponse = getStripeResponse("DELETE", getDeleteCustomerSourceUrl(str, str3), hashMap, RequestOptions.builder(str4).build());
        convertErrorsToExceptionsAndThrowIfNecessary(stripeResponse);
        return Source.fromString(stripeResponse.getResponseBody());
    }

    /* access modifiers changed from: package-private */
    public Customer setDefaultCustomerSource(Context context, String str, String str2, List<String> list, String str3, String str4, String str5, LoggingResponseListener loggingResponseListener) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        hashMap.put("default_source", str3);
        if (context != null) {
            logApiCall(LoggingUtils.getEventLoggingParams(context.getApplicationContext(), list, str4, (String) null, str2, "default_source"), RequestOptions.builder(str2).build(), loggingResponseListener);
        }
        StripeResponse stripeResponse = getStripeResponse("POST", getRetrieveCustomerUrl(str), hashMap, RequestOptions.builder(str5).build());
        convertErrorsToExceptionsAndThrowIfNecessary(stripeResponse);
        return Customer.fromString(stripeResponse.getResponseBody());
    }

    /* access modifiers changed from: package-private */
    public Customer setCustomerShippingInfo(Context context, String str, String str2, List<String> list, ShippingInformation shippingInformation, String str3, LoggingResponseListener loggingResponseListener) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        HashMap hashMap = new HashMap();
        hashMap.put("shipping", shippingInformation.toMap());
        if (context != null) {
            logApiCall(LoggingUtils.getEventLoggingParams(context.getApplicationContext(), list, (String) null, (String) null, str2, "set_shipping_info"), RequestOptions.builder(str2).build(), loggingResponseListener);
        }
        StripeResponse stripeResponse = getStripeResponse("POST", getRetrieveCustomerUrl(str), hashMap, RequestOptions.builder(str3).build());
        convertErrorsToExceptionsAndThrowIfNecessary(stripeResponse);
        return Customer.fromString(stripeResponse.getResponseBody());
    }

    /* access modifiers changed from: package-private */
    public Customer retrieveCustomer(String str, String str2) throws InvalidRequestException, APIConnectionException, APIException, AuthenticationException, CardException {
        StripeResponse stripeResponse = getStripeResponse("GET", getRetrieveCustomerUrl(str), (Map<String, Object>) null, RequestOptions.builder(str2).build());
        convertErrorsToExceptionsAndThrowIfNecessary(stripeResponse);
        return Customer.fromString(stripeResponse.getResponseBody());
    }

    /* access modifiers changed from: package-private */
    public String createQuery(Map<String, Object> map) throws UnsupportedEncodingException, InvalidRequestException {
        StringBuilder sb = new StringBuilder();
        for (Parameter next : flattenParams(map)) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(urlEncodePair(next.key, next.value));
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getHeaders(RequestOptions requestOptions) {
        HashMap hashMap = new HashMap();
        hashMap.put("Accept-Charset", "UTF-8");
        hashMap.put("Accept", "application/json");
        hashMap.put("User-Agent", String.format(Locale.ROOT, "Stripe/v1 AndroidBindings/%s", new Object[]{BuildConfig.VERSION_NAME}));
        if (requestOptions != null) {
            hashMap.put(Constants.AUTHORIZATION_HEADER, String.format(Locale.ENGLISH, "Bearer %s", new Object[]{requestOptions.getPublishableApiKey()}));
        }
        HashMap hashMap2 = new HashMap();
        hashMap2.put("java.version", System.getProperty("java.version"));
        hashMap2.put("os.name", ChannelRegistrationPayload.ANDROID_DEVICE_TYPE);
        hashMap2.put("os.version", String.valueOf(Build.VERSION.SDK_INT));
        hashMap2.put("bindings.version", BuildConfig.VERSION_NAME);
        hashMap2.put("lang", "Java");
        hashMap2.put(FFmpegMediaMetadataRetriever.METADATA_KEY_PUBLISHER, "Stripe");
        hashMap.put("X-Stripe-Client-User-Agent", new JSONObject(hashMap2).toString());
        hashMap.put("Stripe-Version", this.mApiVersion.getCode());
        if (!(requestOptions == null || requestOptions.getStripeAccount() == null)) {
            hashMap.put("Stripe-Account", requestOptions.getStripeAccount());
        }
        if (!(requestOptions == null || requestOptions.getIdempotencyKey() == null)) {
            hashMap.put("Idempotency-Key", requestOptions.getIdempotencyKey());
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public String getTokensUrl() {
        return String.format(Locale.ENGLISH, "%s/v1/%s", new Object[]{LIVE_API_BASE, TOKENS});
    }

    /* access modifiers changed from: package-private */
    public String getSourcesUrl() {
        return String.format(Locale.ENGLISH, "%s/v1/%s", new Object[]{LIVE_API_BASE, SOURCES});
    }

    /* access modifiers changed from: package-private */
    public String getPaymentMethodsUrl() {
        return String.format(Locale.ENGLISH, "%s/v1/%s", new Object[]{LIVE_API_BASE, PAYMENT_METHODS});
    }

    private String createPaymentIntentUrl() {
        return String.format(Locale.ENGLISH, "%s/v1/payment_intents", new Object[]{LIVE_API_BASE});
    }

    private String retrievePaymentIntentUrl(String str) {
        return String.format(Locale.ENGLISH, "%s/v1/payment_intents/%s", new Object[]{LIVE_API_BASE, str});
    }

    private String confirmPaymentIntentUrl(String str) {
        return String.format(Locale.ENGLISH, "%s/v1/payment_intents/%s/confirm", new Object[]{LIVE_API_BASE, str});
    }

    private String getCustomersUrl() {
        return String.format(Locale.ENGLISH, "%s/v1/%s", new Object[]{LIVE_API_BASE, CUSTOMERS});
    }

    /* access modifiers changed from: package-private */
    public String getAddCustomerSourceUrl(String str) {
        return String.format(Locale.ENGLISH, "%s/%s", new Object[]{getRetrieveCustomerUrl(str), SOURCES});
    }

    /* access modifiers changed from: package-private */
    public String getDeleteCustomerSourceUrl(String str, String str2) {
        return String.format(Locale.ENGLISH, "%s/%s", new Object[]{getAddCustomerSourceUrl(str), str2});
    }

    /* access modifiers changed from: package-private */
    public String getRetrieveCustomerUrl(String str) {
        return String.format(Locale.ENGLISH, "%s/%s", new Object[]{getCustomersUrl(), str});
    }

    /* access modifiers changed from: package-private */
    public String getRetrieveSourceApiUrl(String str) {
        return String.format(Locale.ENGLISH, "%s/%s", new Object[]{getSourcesUrl(), str});
    }

    /* access modifiers changed from: package-private */
    public String getRetrieveTokenApiUrl(String str) {
        return String.format(Locale.ROOT, "%s/%s", new Object[]{getTokensUrl(), str});
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v3, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void convertErrorsToExceptionsAndThrowIfNecessary(com.stripe.android.StripeResponse r5) throws com.stripe.android.exception.InvalidRequestException, com.stripe.android.exception.APIException, com.stripe.android.exception.AuthenticationException, com.stripe.android.exception.CardException {
        /*
            r4 = this;
            int r0 = r5.getResponseCode()
            java.lang.String r1 = r5.getResponseBody()
            java.util.Map r5 = r5.getResponseHeaders()
            r2 = 0
            if (r5 != 0) goto L_0x0011
            r5 = r2
            goto L_0x0019
        L_0x0011:
            java.lang.String r3 = "Request-Id"
            java.lang.Object r5 = r5.get(r3)
            java.util.List r5 = (java.util.List) r5
        L_0x0019:
            if (r5 == 0) goto L_0x0029
            int r3 = r5.size()
            if (r3 <= 0) goto L_0x0029
            r2 = 0
            java.lang.Object r5 = r5.get(r2)
            r2 = r5
            java.lang.String r2 = (java.lang.String) r2
        L_0x0029:
            r5 = 200(0xc8, float:2.8E-43)
            if (r0 < r5) goto L_0x0031
            r5 = 300(0x12c, float:4.2E-43)
            if (r0 < r5) goto L_0x0034
        L_0x0031:
            r4.handleAPIError(r1, r0, r2)
        L_0x0034:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.StripeApiHandler.convertErrorsToExceptionsAndThrowIfNecessary(com.stripe.android.StripeResponse):void");
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONObject mapToJsonObject(java.util.Map<java.lang.String, ?> r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L_0x0004
            r6 = 0
            return r6
        L_0x0004:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.util.Set r1 = r6.keySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x0011:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r1.next()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r6.get(r2)
            if (r3 != 0) goto L_0x0024
            goto L_0x0011
        L_0x0024:
            boolean r4 = r3 instanceof java.util.Map     // Catch:{  }
            if (r4 == 0) goto L_0x0034
            java.util.Map r3 = (java.util.Map) r3     // Catch:{ JSONException -> 0x0032 }
            org.json.JSONObject r3 = r5.mapToJsonObject(r3)     // Catch:{ JSONException -> 0x0032 }
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0032 }
            goto L_0x0011
        L_0x0032:
            goto L_0x0011
        L_0x0034:
            boolean r4 = r3 instanceof java.util.List     // Catch:{  }
            if (r4 == 0) goto L_0x0042
            java.util.List r3 = (java.util.List) r3     // Catch:{  }
            org.json.JSONArray r3 = r5.listToJsonArray(r3)     // Catch:{  }
            r0.put(r2, r3)     // Catch:{  }
            goto L_0x0011
        L_0x0042:
            boolean r4 = r3 instanceof java.lang.Number     // Catch:{  }
            if (r4 != 0) goto L_0x0053
            boolean r4 = r3 instanceof java.lang.Boolean     // Catch:{  }
            if (r4 == 0) goto L_0x004b
            goto L_0x0053
        L_0x004b:
            java.lang.String r3 = r3.toString()     // Catch:{  }
            r0.put(r2, r3)     // Catch:{  }
            goto L_0x0011
        L_0x0053:
            r0.put(r2, r3)     // Catch:{  }
            goto L_0x0011
        L_0x0057:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.StripeApiHandler.mapToJsonObject(java.util.Map):org.json.JSONObject");
    }

    private JSONArray listToJsonArray(List<?> list) {
        if (list == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object next : list) {
            if (next instanceof Map) {
                jSONArray.put(mapToJsonObject((Map) next));
            } else if (next instanceof List) {
                jSONArray.put(listToJsonArray((List) next));
            } else if ((next instanceof Number) || (next instanceof Boolean)) {
                jSONArray.put(next);
            } else {
                jSONArray.put(next.toString());
            }
        }
        return jSONArray;
    }

    private void attachPseudoCookie(HttpURLConnection httpURLConnection, RequestOptions requestOptions) {
        if (requestOptions.getGuid() != null && !TextUtils.isEmpty(requestOptions.getGuid())) {
            httpURLConnection.setRequestProperty("Cookie", "m=" + requestOptions.getGuid());
        }
    }

    private HttpURLConnection createDeleteConnection(String str, RequestOptions requestOptions) throws IOException {
        HttpURLConnection createStripeConnection = createStripeConnection(str, requestOptions);
        createStripeConnection.setRequestMethod("DELETE");
        return createStripeConnection;
    }

    private HttpURLConnection createGetConnection(String str, String str2, RequestOptions requestOptions) throws IOException {
        HttpURLConnection createStripeConnection = createStripeConnection(formatURL(str, str2), requestOptions);
        createStripeConnection.setRequestMethod("GET");
        return createStripeConnection;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.HttpURLConnection createPostConnection(java.lang.String r3, java.util.Map<java.lang.String, java.lang.Object> r4, com.stripe.android.RequestOptions r5) throws java.io.IOException, com.stripe.android.exception.InvalidRequestException {
        /*
            r2 = this;
            java.net.HttpURLConnection r3 = r2.createStripeConnection(r3, r5)
            r0 = 1
            r3.setDoOutput(r0)
            java.lang.String r0 = "POST"
            r3.setRequestMethod(r0)
            java.lang.String r0 = r2.getContentType(r5)
            java.lang.String r1 = "Content-Type"
            r3.setRequestProperty(r1, r0)
            java.io.OutputStream r0 = r3.getOutputStream()     // Catch:{ all -> 0x0029 }
            byte[] r4 = r2.getOutputBytes(r4, r5)     // Catch:{ all -> 0x0027 }
            r0.write(r4)     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x0026
            r0.close()
        L_0x0026:
            return r3
        L_0x0027:
            r3 = move-exception
            goto L_0x002b
        L_0x0029:
            r3 = move-exception
            r0 = 0
        L_0x002b:
            if (r0 == 0) goto L_0x0030
            r0.close()
        L_0x0030:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.StripeApiHandler.createPostConnection(java.lang.String, java.util.Map, com.stripe.android.RequestOptions):java.net.HttpURLConnection");
    }

    private HttpURLConnection createStripeConnection(String str, RequestOptions requestOptions) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(DefaultLoadControl.DEFAULT_MAX_BUFFER_MS);
        httpURLConnection.setReadTimeout(80000);
        httpURLConnection.setUseCaches(false);
        if (urlNeedsHeaderData(str)) {
            for (Map.Entry next : getHeaders(requestOptions).entrySet()) {
                httpURLConnection.setRequestProperty((String) next.getKey(), (String) next.getValue());
            }
        }
        if (urlNeedsPseudoCookie(str)) {
            attachPseudoCookie(httpURLConnection, requestOptions);
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(SSL_SOCKET_FACTORY);
        }
        return httpURLConnection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        if (r2 == null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, "-1");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0022, code lost:
        java.security.Security.setProperty(DNS_CACHE_TTL_PROPERTY_NAME, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003d, code lost:
        if (r2 == null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void fireAndForgetApiCall(java.util.Map<java.lang.String, java.lang.Object> r5, java.lang.String r6, java.lang.String r7, com.stripe.android.RequestOptions r8, com.stripe.android.StripeApiHandler.LoggingResponseListener r9) {
        /*
            r4 = this;
            java.lang.String r0 = "-1"
            java.lang.String r1 = "networkaddress.cache.ttl"
            r2 = 0
            java.lang.String r2 = java.security.Security.getProperty(r1)     // Catch:{ SecurityException -> 0x0010 }
            java.lang.String r3 = "0"
            java.security.Security.setProperty(r1, r3)     // Catch:{ SecurityException -> 0x0010 }
            r3 = 1
            goto L_0x0011
        L_0x0010:
            r3 = 0
        L_0x0011:
            com.stripe.android.StripeResponse r5 = r4.getStripeResponse(r7, r6, r5, r8)     // Catch:{ StripeException -> 0x0028 }
            if (r9 == 0) goto L_0x001a
            r9.onLoggingResponse(r5)     // Catch:{ StripeException -> 0x0028 }
        L_0x001a:
            if (r3 == 0) goto L_0x0040
            if (r2 != 0) goto L_0x0022
        L_0x001e:
            java.security.Security.setProperty(r1, r0)
            goto L_0x0040
        L_0x0022:
            java.security.Security.setProperty(r1, r2)
            goto L_0x0040
        L_0x0026:
            r5 = move-exception
            goto L_0x002f
        L_0x0028:
            r5 = move-exception
            if (r9 == 0) goto L_0x003b
            r9.onStripeException(r5)     // Catch:{ all -> 0x0026 }
            goto L_0x003b
        L_0x002f:
            if (r3 == 0) goto L_0x003a
            if (r2 != 0) goto L_0x0037
            java.security.Security.setProperty(r1, r0)
            goto L_0x003a
        L_0x0037:
            java.security.Security.setProperty(r1, r2)
        L_0x003a:
            throw r5
        L_0x003b:
            if (r3 == 0) goto L_0x0040
            if (r2 != 0) goto L_0x0022
            goto L_0x001e
        L_0x0040:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.StripeApiHandler.fireAndForgetApiCall(java.util.Map, java.lang.String, java.lang.String, com.stripe.android.RequestOptions, com.stripe.android.StripeApiHandler$LoggingResponseListener):void");
    }

    private List<Parameter> flattenParams(Map<String, Object> map) throws InvalidRequestException {
        return flattenParamsMap(map, (String) null);
    }

    private List<Parameter> flattenParamsList(List<?> list, String str) throws InvalidRequestException {
        LinkedList linkedList = new LinkedList();
        if (list.isEmpty()) {
            linkedList.add(new Parameter(str, ""));
        } else {
            String format = String.format(Locale.ROOT, "%s[]", new Object[]{str});
            for (Object flattenParamsValue : list) {
                linkedList.addAll(flattenParamsValue(flattenParamsValue, format));
            }
        }
        return linkedList;
    }

    private List<Parameter> flattenParamsMap(Map<String, Object> map, String str) throws InvalidRequestException {
        LinkedList linkedList = new LinkedList();
        if (map == null) {
            return linkedList;
        }
        for (Map.Entry next : map.entrySet()) {
            String str2 = (String) next.getKey();
            Object value = next.getValue();
            if (str != null) {
                str2 = String.format(Locale.ROOT, "%s[%s]", new Object[]{str, str2});
            }
            linkedList.addAll(flattenParamsValue(value, str2));
        }
        return linkedList;
    }

    private List<Parameter> flattenParamsValue(Object obj, String str) throws InvalidRequestException {
        if (obj instanceof Map) {
            return flattenParamsMap((Map) obj, str);
        }
        if (obj instanceof List) {
            return flattenParamsList((List) obj, str);
        }
        if ("".equals(obj)) {
            throw new InvalidRequestException("You cannot set '" + str + "' to an empty string. We interpret empty strings as null in requests. You may set '" + str + "' to null to delete the property.", str, (String) null, 0, (String) null, (String) null, (StripeError) null, (Throwable) null);
        } else if (obj == null) {
            LinkedList linkedList = new LinkedList();
            linkedList.add(new Parameter(str, ""));
            return linkedList;
        } else {
            LinkedList linkedList2 = new LinkedList();
            linkedList2.add(new Parameter(str, obj.toString()));
            return linkedList2;
        }
    }

    private String formatURL(String str, String str2) {
        if (str2 == null || str2.isEmpty()) {
            return str;
        }
        String str3 = "?";
        if (str.contains(str3)) {
            str3 = "&";
        }
        return String.format(Locale.ROOT, "%s%s%s", new Object[]{str, str3, str2});
    }

    private String getContentType(RequestOptions requestOptions) {
        if (RequestOptions.TYPE_JSON.equals(requestOptions.getRequestType())) {
            return String.format(Locale.ROOT, "application/json; charset=%s", new Object[]{"UTF-8"});
        }
        return String.format(Locale.ROOT, "application/x-www-form-urlencoded;charset=%s", new Object[]{"UTF-8"});
    }

    private byte[] getOutputBytes(Map<String, Object> map, RequestOptions requestOptions) throws InvalidRequestException {
        try {
            if (!RequestOptions.TYPE_JSON.equals(requestOptions.getRequestType())) {
                return createQuery(map).getBytes("UTF-8");
            }
            JSONObject mapToJsonObject = mapToJsonObject(map);
            if (mapToJsonObject != null) {
                return mapToJsonObject.toString().getBytes("UTF-8");
            }
            throw new InvalidRequestException("Unable to create JSON data from parameters. Please contact support@stripe.com for assistance.", (String) null, (String) null, 0, (String) null, (String) null, (StripeError) null, (Throwable) null);
        } catch (UnsupportedEncodingException e) {
            throw new InvalidRequestException("Unable to encode parameters to UTF-8. Please contact support@stripe.com for assistance.", (String) null, (String) null, 0, (String) null, (String) null, (StripeError) null, e);
        }
    }

    private String getResponseBody(InputStream inputStream) throws IOException {
        Scanner useDelimiter = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String next = useDelimiter.hasNext() ? useDelimiter.next() : null;
        inputStream.close();
        return next;
    }

    private StripeResponse getStripeResponse(String str, String str2, Map<String, Object> map, RequestOptions requestOptions) throws InvalidRequestException, APIConnectionException {
        HttpURLConnection createGetConnection;
        String str3;
        char c = 65535;
        HttpURLConnection httpURLConnection = null;
        try {
            int hashCode = str.hashCode();
            if (hashCode != 70454) {
                if (hashCode != 2461856) {
                    if (hashCode == 2012838315) {
                        if (str.equals("DELETE")) {
                            c = 2;
                        }
                    }
                } else if (str.equals("POST")) {
                    c = 1;
                }
            } else if (str.equals("GET")) {
                c = 0;
            }
            if (c == 0) {
                createGetConnection = createGetConnection(str2, createQuery(map), requestOptions);
            } else if (c == 1) {
                createGetConnection = createPostConnection(str2, map, requestOptions);
            } else if (c == 2) {
                createGetConnection = createDeleteConnection(str2, requestOptions);
            } else {
                throw new APIConnectionException(String.format(Locale.ENGLISH, "Unrecognized HTTP method %s. This indicates a bug in the Stripe bindings. Please contact support@stripe.com for assistance.", new Object[]{str}));
            }
            HttpURLConnection httpURLConnection2 = createGetConnection;
            int responseCode = httpURLConnection2.getResponseCode();
            if (responseCode < 200 || responseCode >= 300) {
                str3 = getResponseBody(httpURLConnection2.getErrorStream());
            } else {
                str3 = getResponseBody(httpURLConnection2.getInputStream());
            }
            StripeResponse stripeResponse = new StripeResponse(responseCode, str3, httpURLConnection2.getHeaderFields());
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            return stripeResponse;
        } catch (IOException e) {
            throw new APIConnectionException(String.format(Locale.ENGLISH, "IOException during API request to Stripe (%s): %s Please check your internet connection and try again. If this problem persists, you should check Stripe's service status at https://twitter.com/stripestatus, or let us know at support@stripe.com.", new Object[]{getTokensUrl(), e.getMessage()}), e);
        } catch (Throwable th) {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private void handleAPIError(String str, int i, String str2) throws InvalidRequestException, AuthenticationException, CardException, APIException {
        StripeError parseError = ErrorParser.parseError(str);
        if (i != 429) {
            switch (i) {
                case 400:
                case 404:
                    throw new InvalidRequestException(parseError.message, parseError.param, str2, Integer.valueOf(i), parseError.code, parseError.declineCode, parseError, (Throwable) null);
                case 401:
                    throw new AuthenticationException(parseError.message, str2, Integer.valueOf(i), parseError);
                case 402:
                    throw new CardException(parseError.message, str2, parseError.code, parseError.param, parseError.declineCode, parseError.charge, Integer.valueOf(i), parseError);
                case 403:
                    throw new PermissionException(parseError.message, str2, Integer.valueOf(i), parseError);
                default:
                    throw new APIException(parseError.message, str2, Integer.valueOf(i), parseError, (Throwable) null);
            }
        } else {
            throw new RateLimitException(parseError.message, parseError.param, str2, Integer.valueOf(i), parseError);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v5, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x001b  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.stripe.android.StripeResponse requestData(java.lang.String r7, java.lang.String r8, java.util.Map<java.lang.String, java.lang.Object> r9, com.stripe.android.RequestOptions r10) throws com.stripe.android.exception.AuthenticationException, com.stripe.android.exception.InvalidRequestException, com.stripe.android.exception.APIConnectionException, com.stripe.android.exception.CardException, com.stripe.android.exception.APIException {
        /*
            r6 = this;
            java.lang.String r0 = "networkaddress.cache.ttl"
            r1 = 0
            r2 = 0
            java.lang.String r3 = java.security.Security.getProperty(r0)     // Catch:{ SecurityException -> 0x000f }
            java.lang.String r4 = "0"
            java.security.Security.setProperty(r0, r4)     // Catch:{ SecurityException -> 0x0010 }
            r4 = 1
            goto L_0x0011
        L_0x000f:
            r3 = r2
        L_0x0010:
            r4 = 0
        L_0x0011:
            java.lang.String r5 = r10.getPublishableApiKey()
            boolean r5 = com.stripe.android.StripeTextUtils.isBlank(r5)
            if (r5 != 0) goto L_0x005f
            com.stripe.android.StripeResponse r7 = r6.getStripeResponse(r7, r8, r9, r10)
            int r8 = r7.getResponseCode()
            java.lang.String r9 = r7.getResponseBody()
            java.util.Map r10 = r7.getResponseHeaders()
            if (r10 != 0) goto L_0x002f
            r10 = r2
            goto L_0x0037
        L_0x002f:
            java.lang.String r5 = "Request-Id"
            java.lang.Object r10 = r10.get(r5)
            java.util.List r10 = (java.util.List) r10
        L_0x0037:
            if (r10 == 0) goto L_0x0046
            int r5 = r10.size()
            if (r5 <= 0) goto L_0x0046
            java.lang.Object r10 = r10.get(r1)
            r2 = r10
            java.lang.String r2 = (java.lang.String) r2
        L_0x0046:
            r10 = 200(0xc8, float:2.8E-43)
            if (r8 < r10) goto L_0x004e
            r10 = 300(0x12c, float:4.2E-43)
            if (r8 < r10) goto L_0x0051
        L_0x004e:
            r6.handleAPIError(r9, r8, r2)
        L_0x0051:
            if (r4 == 0) goto L_0x005e
            if (r3 != 0) goto L_0x005b
            java.lang.String r8 = "-1"
            java.security.Security.setProperty(r0, r8)
            goto L_0x005e
        L_0x005b:
            java.security.Security.setProperty(r0, r3)
        L_0x005e:
            return r7
        L_0x005f:
            com.stripe.android.exception.AuthenticationException r7 = new com.stripe.android.exception.AuthenticationException
            java.lang.Integer r8 = java.lang.Integer.valueOf(r1)
            java.lang.String r9 = "No API key provided. (HINT: set your API key using 'Stripe.apiKey = <API-KEY>'. You can generate API keys from the Stripe web interface. See https://stripe.com/api for details or email support@stripe.com if you have questions."
            r7.<init>(r9, r2, r8, r2)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.StripeApiHandler.requestData(java.lang.String, java.lang.String, java.util.Map, com.stripe.android.RequestOptions):com.stripe.android.StripeResponse");
    }

    private Token requestToken(String str, Map<String, Object> map, RequestOptions requestOptions) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
        return Token.fromString(requestData("POST", str, map, requestOptions).getResponseBody());
    }

    private void setTelemetryData(Context context, LoggingResponseListener loggingResponseListener) {
        Map<String, Object> createTelemetryMap = TelemetryClientUtil.createTelemetryMap(context);
        StripeNetworkUtils.removeNullAndEmptyParams(createTelemetryMap);
        if (loggingResponseListener == null || loggingResponseListener.shouldLogTest()) {
            fireAndForgetApiCall(createTelemetryMap, LOGGING_ENDPOINT, "POST", RequestOptions.builder((String) null, RequestOptions.TYPE_JSON).setGuid(TelemetryClientUtil.getHashedId(context)).build(), loggingResponseListener);
        }
    }

    private boolean urlNeedsHeaderData(String str) {
        return str.startsWith(LIVE_API_BASE) || str.startsWith(LIVE_LOGGING_BASE);
    }

    private boolean urlNeedsPseudoCookie(String str) {
        return str.startsWith(LOGGING_ENDPOINT);
    }

    private String urlEncodePair(String str, String str2) throws UnsupportedEncodingException {
        return String.format(Locale.ROOT, "%s=%s", new Object[]{urlEncode(str), urlEncode(str2)});
    }

    private String urlEncode(String str) throws UnsupportedEncodingException {
        if (str == null) {
            return null;
        }
        return URLEncoder.encode(str, "UTF-8");
    }

    private final class Parameter {
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final String value;

        Parameter(String str, String str2) {
            this.key = str;
            this.value = str2;
        }
    }
}
