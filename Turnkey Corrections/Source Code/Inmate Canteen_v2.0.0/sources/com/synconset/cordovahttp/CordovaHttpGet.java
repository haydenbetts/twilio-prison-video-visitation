package com.synconset.cordovahttp;

import com.github.kevinsawicki.http.HttpRequest;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.json.JSONObject;

class CordovaHttpGet extends CordovaHttp implements Runnable {
    public CordovaHttpGet(String str, Object obj, JSONObject jSONObject, int i, CallbackContext callbackContext) {
        super(str, obj, jSONObject, i, callbackContext);
    }

    public void run() {
        try {
            HttpRequest httpRequest = HttpRequest.get((CharSequence) getUrlString(), (Map<?, ?>) getParamsMap(), false);
            prepareRequest(httpRequest);
            returnResponseObject(httpRequest);
        } catch (HttpRequest.HttpRequestException e) {
            handleHttpRequestException(e);
        } catch (Exception e2) {
            respondWithError(e2.getMessage());
        }
    }
}
