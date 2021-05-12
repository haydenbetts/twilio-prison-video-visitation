package org.apache.cordova.inappbrowser;

import android.webkit.GeolocationPermissions;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

public class InAppChromeClient extends WebChromeClient {
    private String LOG_TAG = "InAppChromeClient";
    private long MAX_QUOTA = 104857600;
    private CordovaWebView webView;

    public InAppChromeClient(CordovaWebView cordovaWebView) {
        this.webView = cordovaWebView;
    }

    public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, WebStorage.QuotaUpdater quotaUpdater) {
        LOG.d(this.LOG_TAG, "onExceededDatabaseQuota estimatedSize: %d  currentQuota: %d  totalUsedQuota: %d", Long.valueOf(j2), Long.valueOf(j), Long.valueOf(j3));
        quotaUpdater.updateQuota(this.MAX_QUOTA);
    }

    public void onGeolocationPermissionsShowPrompt(String str, GeolocationPermissions.Callback callback) {
        super.onGeolocationPermissionsShowPrompt(str, callback);
        callback.invoke(str, true, false);
    }

    public boolean onJsPrompt(WebView webView2, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        PluginResult pluginResult;
        if (str3 == null || !str3.startsWith("gap")) {
            return false;
        }
        if (str3.startsWith("gap-iab://")) {
            String substring = str3.substring(10);
            if (!substring.startsWith("InAppBrowser")) {
                return false;
            }
            if (str2 == null || str2.length() == 0) {
                pluginResult = new PluginResult(PluginResult.Status.OK, new JSONArray());
            } else {
                try {
                    pluginResult = new PluginResult(PluginResult.Status.OK, new JSONArray(str2));
                } catch (JSONException e) {
                    pluginResult = new PluginResult(PluginResult.Status.JSON_EXCEPTION, e.getMessage());
                }
            }
            this.webView.sendPluginResult(pluginResult, substring);
            jsPromptResult.confirm("");
            return true;
        }
        String str4 = this.LOG_TAG;
        LOG.w(str4, "InAppBrowser does not support Cordova API calls: " + str + " " + str3);
        jsPromptResult.cancel();
        return true;
    }
}
