package com.synconset.cordovahttp;

import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.android.gms.common.internal.ImagesContract;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLHandshakeException;
import org.apache.cordova.CallbackContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class CordovaHttp {
    protected static final String[] ACCEPTED_CHARSETS = {HttpRequest.CHARSET_UTF8, HttpRequest.CHARSET_LATIN1};
    protected static final String TAG = "CordovaHTTP";
    private static AtomicBoolean disableRedirect = new AtomicBoolean(false);
    private CallbackContext callbackContext;
    private JSONObject headers;
    private Object params;
    private String serializerName;
    private int timeoutInMilliseconds;
    private String urlString;

    public CordovaHttp(String str, Object obj, JSONObject jSONObject, int i, CallbackContext callbackContext2) {
        this(str, obj, HttpRequest.CERT_MODE_DEFAULT, jSONObject, i, callbackContext2);
    }

    public CordovaHttp(String str, Object obj, String str2, JSONObject jSONObject, int i, CallbackContext callbackContext2) {
        this.urlString = str;
        this.params = obj;
        this.serializerName = str2;
        this.headers = jSONObject;
        this.timeoutInMilliseconds = i;
        this.callbackContext = callbackContext2;
    }

    public static void disableRedirect(boolean z) {
        disableRedirect.set(z);
    }

    /* access modifiers changed from: protected */
    public String getUrlString() {
        return this.urlString;
    }

    /* access modifiers changed from: protected */
    public Object getParamsObject() {
        return this.params;
    }

    /* access modifiers changed from: protected */
    public String getSerializerName() {
        return this.serializerName;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getParamsMap() throws JSONException, Exception {
        Object obj = this.params;
        if (obj instanceof JSONObject) {
            return getMapFromJSONObject((JSONObject) obj);
        }
        throw new Exception("unsupported params type, needs to be a JSON object");
    }

    /* access modifiers changed from: protected */
    public JSONObject getHeadersObject() {
        return this.headers;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> getHeadersMap() throws JSONException {
        return getStringMapFromJSONObject(this.headers);
    }

    /* access modifiers changed from: protected */
    public int getRequestTimeout() {
        return this.timeoutInMilliseconds;
    }

    /* access modifiers changed from: protected */
    public CallbackContext getCallbackContext() {
        return this.callbackContext;
    }

    /* access modifiers changed from: protected */
    public HttpRequest setupRedirect(HttpRequest httpRequest) {
        if (disableRedirect.get()) {
            httpRequest.followRedirects(false);
        }
        return httpRequest;
    }

    /* access modifiers changed from: protected */
    public void setupDataSerializer(HttpRequest httpRequest) throws JSONException, Exception {
        if ("json".equals(getSerializerName())) {
            httpRequest.contentType(HttpRequest.CONTENT_TYPE_JSON, HttpRequest.CHARSET_UTF8);
        } else if ("utf8".equals(getSerializerName())) {
            httpRequest.contentType("text/plain", HttpRequest.CHARSET_UTF8);
        }
    }

    /* access modifiers changed from: protected */
    public void respondWithError(int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, i);
            jSONObject.put("error", str);
            this.callbackContext.error(jSONObject);
        } catch (JSONException unused) {
            this.callbackContext.error(str);
        }
    }

    /* access modifiers changed from: protected */
    public void respondWithError(String str) {
        respondWithError(-1, str);
    }

    /* access modifiers changed from: protected */
    public void addResponseHeaders(HttpRequest httpRequest, JSONObject jSONObject) throws JSONException {
        Map<String, List<String>> headers2 = httpRequest.headers();
        HashMap hashMap = new HashMap();
        for (Map.Entry next : headers2.entrySet()) {
            String str = (String) next.getKey();
            List list = (List) next.getValue();
            if (str != null && !list.isEmpty()) {
                hashMap.put(str.toLowerCase(), TextUtils.join(", ", list));
            }
        }
        jSONObject.put("headers", new JSONObject(hashMap));
    }

    /* access modifiers changed from: protected */
    public HashMap<String, String> getStringMapFromJSONObject(JSONObject jSONObject) throws JSONException {
        HashMap<String, String> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            hashMap.put(next, jSONObject.getString(next));
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public ArrayList<Object> getListFromJSONArray(JSONArray jSONArray) throws JSONException {
        ArrayList<Object> arrayList = new ArrayList<>();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.get(i));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Object> getMapFromJSONObject(JSONObject jSONObject) throws JSONException {
        HashMap<String, Object> hashMap = new HashMap<>();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof JSONArray) {
                hashMap.put(next, getListFromJSONArray((JSONArray) obj));
            } else {
                hashMap.put(next, jSONObject.get(next));
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: protected */
    public void prepareRequest(HttpRequest httpRequest) throws HttpRequest.HttpRequestException, JSONException {
        setupRedirect(httpRequest);
        httpRequest.readTimeout(getRequestTimeout());
        httpRequest.acceptCharset(ACCEPTED_CHARSETS);
        httpRequest.headers((Map<String, String>) getHeadersMap());
        httpRequest.uncompress(true);
    }

    /* access modifiers changed from: protected */
    public void prepareRequestBody(HttpRequest httpRequest) throws JSONException, Exception {
        if ("json".equals(getSerializerName())) {
            httpRequest.send((CharSequence) getParamsObject().toString());
        } else if ("utf8".equals(getSerializerName())) {
            httpRequest.send((CharSequence) getParamsMap().get("text").toString());
        } else {
            httpRequest.form((Map<?, ?>) getParamsMap());
        }
    }

    private CharsetDecoder createCharsetDecoder(String str) {
        return Charset.forName(str).newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
    }

    private String decodeBody(AtomicReference<ByteBuffer> atomicReference, String str) throws CharacterCodingException, MalformedInputException {
        if (str == null) {
            return tryDecodeByteBuffer(atomicReference);
        }
        return decodeByteBuffer(atomicReference, str);
    }

    private String tryDecodeByteBuffer(AtomicReference<ByteBuffer> atomicReference) throws CharacterCodingException, MalformedInputException {
        int i = 0;
        while (true) {
            String[] strArr = ACCEPTED_CHARSETS;
            if (i >= strArr.length - 1) {
                return decodeBody(atomicReference, strArr[strArr.length - 1]);
            }
            try {
                return decodeByteBuffer(atomicReference, strArr[i]);
            } catch (CharacterCodingException | MalformedInputException unused) {
                i++;
            }
        }
    }

    private String decodeByteBuffer(AtomicReference<ByteBuffer> atomicReference, String str) throws CharacterCodingException, MalformedInputException {
        return createCharsetDecoder(str).decode(atomicReference.get()).toString();
    }

    /* access modifiers changed from: protected */
    public void returnResponseObject(HttpRequest httpRequest) throws HttpRequest.HttpRequestException {
        try {
            JSONObject jSONObject = new JSONObject();
            int code = httpRequest.code();
            AtomicReference atomicReference = new AtomicReference();
            httpRequest.body((AtomicReference<ByteBuffer>) atomicReference);
            jSONObject.put(NotificationCompat.CATEGORY_STATUS, code);
            jSONObject.put(ImagesContract.URL, httpRequest.url().toString());
            addResponseHeaders(httpRequest, jSONObject);
            if (code < 200 || code >= 300) {
                jSONObject.put("error", decodeBody(atomicReference, httpRequest.charset()));
                getCallbackContext().error(jSONObject);
                return;
            }
            jSONObject.put("data", decodeBody(atomicReference, httpRequest.charset()));
            getCallbackContext().success(jSONObject);
        } catch (JSONException unused) {
            respondWithError("There was an error generating the response");
        } catch (MalformedInputException unused2) {
            respondWithError("Could not decode response data due to malformed data");
        } catch (CharacterCodingException unused3) {
            respondWithError("Could not decode response data due to invalid or unknown charset encoding");
        }
    }

    /* access modifiers changed from: protected */
    public void handleHttpRequestException(HttpRequest.HttpRequestException httpRequestException) {
        if (httpRequestException.getCause() instanceof UnknownHostException) {
            respondWithError(0, "The host could not be resolved");
        } else if (httpRequestException.getCause() instanceof SocketTimeoutException) {
            respondWithError(1, "The request timed out");
        } else if (httpRequestException.getCause() instanceof SSLHandshakeException) {
            respondWithError("SSL handshake failed");
        } else {
            respondWithError("There was an error with the request: " + httpRequestException.getMessage());
        }
    }
}
