package com.synconset.cordovahttp;

import com.github.kevinsawicki.http.HttpRequest;
import org.apache.cordova.CallbackContext;
import org.json.JSONObject;

class CordovaHttpPatch extends CordovaHttp implements Runnable {
    public CordovaHttpPatch(String str, Object obj, String str2, JSONObject jSONObject, int i, CallbackContext callbackContext) {
        super(str, obj, str2, jSONObject, i, callbackContext);
    }

    public void run() {
        try {
            HttpRequest patch = HttpRequest.patch((CharSequence) getUrlString());
            setupDataSerializer(patch);
            prepareRequest(patch);
            prepareRequestBody(patch);
            returnResponseObject(patch);
        } catch (HttpRequest.HttpRequestException e) {
            handleHttpRequestException(e);
        } catch (Exception e2) {
            respondWithError(e2.getMessage());
        }
    }
}
