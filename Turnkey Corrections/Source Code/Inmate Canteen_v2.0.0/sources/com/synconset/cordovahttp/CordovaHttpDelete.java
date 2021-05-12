package com.synconset.cordovahttp;

import com.github.kevinsawicki.http.HttpRequest;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.json.JSONObject;

class CordovaHttpDelete extends CordovaHttp implements Runnable {
    public CordovaHttpDelete(String str, Object obj, JSONObject jSONObject, int i, CallbackContext callbackContext) {
        super(str, obj, jSONObject, i, callbackContext);
    }

    public void run() {
        try {
            HttpRequest delete = HttpRequest.delete((CharSequence) getUrlString(), (Map<?, ?>) getParamsMap(), false);
            prepareRequest(delete);
            returnResponseObject(delete);
        } catch (HttpRequest.HttpRequestException e) {
            handleHttpRequestException(e);
        } catch (Exception e2) {
            respondWithError(e2.getMessage());
        }
    }
}
