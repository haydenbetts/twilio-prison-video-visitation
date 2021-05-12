package com.synconset.cordovahttp;

import com.github.kevinsawicki.http.HttpRequest;
import org.apache.cordova.CallbackContext;
import org.json.JSONObject;

class CordovaHttpPut extends CordovaHttp implements Runnable {
    public CordovaHttpPut(String str, Object obj, String str2, JSONObject jSONObject, int i, CallbackContext callbackContext) {
        super(str, obj, str2, jSONObject, i, callbackContext);
    }

    public void run() {
        try {
            HttpRequest put = HttpRequest.put((CharSequence) getUrlString());
            setupDataSerializer(put);
            prepareRequest(put);
            prepareRequestBody(put);
            returnResponseObject(put);
        } catch (HttpRequest.HttpRequestException e) {
            handleHttpRequestException(e);
        } catch (Exception e2) {
            respondWithError(e2.getMessage());
        }
    }
}
