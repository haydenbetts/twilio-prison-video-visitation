package org.apache.cordova.engine;

import android.webkit.JavascriptInterface;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.ExposedJsApi;
import org.json.JSONException;

class SystemExposedJsApi implements ExposedJsApi {
    private final CordovaBridge bridge;

    SystemExposedJsApi(CordovaBridge cordovaBridge) {
        this.bridge = cordovaBridge;
    }

    @JavascriptInterface
    public String exec(int i, String str, String str2, String str3, String str4) throws JSONException, IllegalAccessException {
        return this.bridge.jsExec(i, str, str2, str3, str4);
    }

    @JavascriptInterface
    public void setNativeToJsBridgeMode(int i, int i2) throws IllegalAccessException {
        this.bridge.jsSetNativeToJsBridgeMode(i, i2);
    }

    @JavascriptInterface
    public String retrieveJsMessages(int i, boolean z) throws IllegalAccessException {
        return this.bridge.jsRetrieveJsMessages(i, z);
    }
}
