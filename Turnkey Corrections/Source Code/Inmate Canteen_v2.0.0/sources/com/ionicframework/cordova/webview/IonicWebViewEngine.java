package com.ionicframework.cordova.webview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.RequiresApi;
import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.PluginManager;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;

public class IonicWebViewEngine extends SystemWebViewEngine {
    private static final String LAST_BINARY_VERSION_CODE = "lastBinaryVersionCode";
    private static final String LAST_BINARY_VERSION_NAME = "lastBinaryVersionName";
    public static final String TAG = "IonicWebViewEngine";
    /* access modifiers changed from: private */
    public String CDV_LOCAL_SERVER;
    /* access modifiers changed from: private */
    public WebViewLocalServer localServer;
    /* access modifiers changed from: private */
    public String scheme;

    public IonicWebViewEngine(Context context, CordovaPreferences cordovaPreferences) {
        super(new SystemWebView(context), cordovaPreferences);
        Log.d(TAG, "Ionic Web View Engine Starting Right Up 1...");
    }

    public IonicWebViewEngine(SystemWebView systemWebView) {
        super(systemWebView, (CordovaPreferences) null);
        Log.d(TAG, "Ionic Web View Engine Starting Right Up 2...");
    }

    public IonicWebViewEngine(SystemWebView systemWebView, CordovaPreferences cordovaPreferences) {
        super(systemWebView, cordovaPreferences);
        Log.d(TAG, "Ionic Web View Engine Starting Right Up 3...");
    }

    public void init(CordovaWebView cordovaWebView, CordovaInterface cordovaInterface, CordovaWebViewEngine.Client client, CordovaResourceApi cordovaResourceApi, PluginManager pluginManager, NativeToJsMessageQueue nativeToJsMessageQueue) {
        ConfigXmlParser configXmlParser = new ConfigXmlParser();
        configXmlParser.parse((Context) cordovaInterface.getActivity());
        String string = this.preferences.getString("Hostname", "localhost");
        this.scheme = this.preferences.getString("Scheme", WebViewLocalServer.httpScheme);
        this.CDV_LOCAL_SERVER = this.scheme + "://" + string;
        this.localServer = new WebViewLocalServer(cordovaInterface.getActivity(), string, true, configXmlParser, this.scheme);
        this.localServer.hostAssets("www");
        this.webView.setWebViewClient(new ServerClient(this, configXmlParser));
        super.init(cordovaWebView, cordovaInterface, client, cordovaResourceApi, pluginManager, nativeToJsMessageQueue);
        if (Build.VERSION.SDK_INT >= 21) {
            this.webView.getSettings().setMixedContentMode(this.preferences.getInteger("MixedContentMode", 0));
        }
        String string2 = cordovaInterface.getActivity().getApplicationContext().getSharedPreferences(IonicWebView.WEBVIEW_PREFS_NAME, 0).getString(IonicWebView.CDV_SERVER_PATH, (String) null);
        if (!isDeployDisabled() && !isNewBinary() && string2 != null && !string2.isEmpty()) {
            setServerBasePath(string2);
        }
        if (this.preferences.getBoolean("ResolveServiceWorkerRequests", false) && Build.VERSION.SDK_INT >= 24) {
            ServiceWorkerController.getInstance().setServiceWorkerClient(new ServiceWorkerClient() {
                public WebResourceResponse shouldInterceptRequest(WebResourceRequest webResourceRequest) {
                    return IonicWebViewEngine.this.localServer.shouldInterceptRequest(webResourceRequest.getUrl(), webResourceRequest);
                }
            });
        }
    }

    private boolean isNewBinary() {
        String str = "";
        String str2 = "";
        SharedPreferences sharedPreferences = this.cordova.getActivity().getApplicationContext().getSharedPreferences(IonicWebView.WEBVIEW_PREFS_NAME, 0);
        String string = sharedPreferences.getString(LAST_BINARY_VERSION_CODE, (String) null);
        String string2 = sharedPreferences.getString(LAST_BINARY_VERSION_NAME, (String) null);
        try {
            PackageInfo packageInfo = this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getActivity().getPackageName(), 0);
            str = Integer.toString(packageInfo.versionCode);
            str2 = packageInfo.versionName;
        } catch (Exception e) {
            Log.e(TAG, "Unable to get package info", e);
        }
        if (str.equals(string) && str2.equals(string2)) {
            return false;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(LAST_BINARY_VERSION_CODE, str);
        edit.putString(LAST_BINARY_VERSION_NAME, str2);
        edit.putString(IonicWebView.CDV_SERVER_PATH, "");
        edit.apply();
        return true;
    }

    private boolean isDeployDisabled() {
        return this.preferences.getBoolean("DisableDeploy", false);
    }

    private class ServerClient extends SystemWebViewClient {
        private ConfigXmlParser parser;

        public ServerClient(SystemWebViewEngine systemWebViewEngine, ConfigXmlParser configXmlParser) {
            super(systemWebViewEngine);
            this.parser = configXmlParser;
        }

        @RequiresApi(21)
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            return IonicWebViewEngine.this.localServer.shouldInterceptRequest(webResourceRequest.getUrl(), webResourceRequest);
        }

        @TargetApi(19)
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            return IonicWebViewEngine.this.localServer.shouldInterceptRequest(Uri.parse(str), (WebResourceRequest) null);
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            super.onPageStarted(webView, str, bitmap);
            String launchUrl = this.parser.getLaunchUrl();
            if (!launchUrl.contains(WebViewLocalServer.httpsScheme) && !launchUrl.contains(WebViewLocalServer.httpScheme) && str.equals(launchUrl)) {
                webView.stopLoading();
                String access$100 = IonicWebViewEngine.this.CDV_LOCAL_SERVER;
                if (!IonicWebViewEngine.this.scheme.equalsIgnoreCase(WebViewLocalServer.httpsScheme) && !IonicWebViewEngine.this.scheme.equalsIgnoreCase(WebViewLocalServer.httpScheme)) {
                    access$100 = access$100 + "/";
                }
                webView.loadUrl(access$100);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            webView.loadUrl("javascript:(function() { window.WEBVIEW_SERVER_URL = '" + IonicWebViewEngine.this.CDV_LOCAL_SERVER + "';})()");
        }
    }

    public void setServerBasePath(String str) {
        this.localServer.hostFiles(str);
        this.webView.loadUrl(this.CDV_LOCAL_SERVER);
    }

    public String getServerBasePath() {
        return this.localServer.getBasePath();
    }
}
