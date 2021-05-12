package com.synconset.cordovahttp;

import com.github.kevinsawicki.http.HttpRequest;
import org.apache.cordova.CallbackContext;
import org.json.JSONObject;

class CordovaHttpPost extends CordovaHttp implements Runnable {
    public CordovaHttpPost(String str, Object obj, String str2, JSONObject jSONObject, int i, CallbackContext callbackContext) {
        super(str, obj, str2, jSONObject, i, callbackContext);
    }

    public void run() {
        try {
            HttpRequest post = HttpRequest.post((CharSequence) getUrlString());
            setupDataSerializer(post);
            prepareRequest(post);
            prepareRequestBody(post);
            returnResponseObject(post);
        } catch (HttpRequest.HttpRequestException e) {
            handleHttpRequestException(e);
        } catch (Exception e2) {
            respondWithError(e2.getMessage());
        }
    }
}
