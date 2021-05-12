package org.apache.cordova.engine;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import org.apache.cordova.CordovaBridge;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPreferences;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;
import org.apache.cordova.ICordovaCookieManager;
import org.apache.cordova.LOG;
import org.apache.cordova.NativeToJsMessageQueue;
import org.apache.cordova.PluginManager;

public class SystemWebViewEngine implements CordovaWebViewEngine {
    public static final String TAG = "SystemWebViewEngine";
    protected CordovaBridge bridge;
    protected CordovaWebViewEngine.Client client;
    protected final SystemCookieManager cookieManager;
    protected CordovaInterface cordova;
    protected NativeToJsMessageQueue nativeToJsMessageQueue;
    protected CordovaWebView parentWebView;
    protected PluginManager pluginManager;
    protected CordovaPreferences preferences;
    private BroadcastReceiver receiver;
    protected CordovaResourceApi resourceApi;
    protected final SystemWebView webView;

    public SystemWebViewEngine(Context context, CordovaPreferences cordovaPreferences) {
        this(new SystemWebView(context), cordovaPreferences);
    }

    public SystemWebViewEngine(SystemWebView systemWebView) {
        this(systemWebView, (CordovaPreferences) null);
    }

    public SystemWebViewEngine(SystemWebView systemWebView, CordovaPreferences cordovaPreferences) {
        this.preferences = cordovaPreferences;
        this.webView = systemWebView;
        this.cookieManager = new SystemCookieManager(systemWebView);
    }

    public void init(CordovaWebView cordovaWebView, CordovaInterface cordovaInterface, CordovaWebViewEngine.Client client2, CordovaResourceApi cordovaResourceApi, PluginManager pluginManager2, NativeToJsMessageQueue nativeToJsMessageQueue2) {
        if (this.cordova == null) {
            if (this.preferences == null) {
                this.preferences = cordovaWebView.getPreferences();
            }
            this.parentWebView = cordovaWebView;
            this.cordova = cordovaInterface;
            this.client = client2;
            this.resourceApi = cordovaResourceApi;
            this.pluginManager = pluginManager2;
            this.nativeToJsMessageQueue = nativeToJsMessageQueue2;
            this.webView.init(this, cordovaInterface);
            initWebViewSettings();
            nativeToJsMessageQueue2.addBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode(new NativeToJsMessageQueue.OnlineEventsBridgeMode.OnlineEventsBridgeModeDelegate() {
                public void setNetworkAvailable(boolean z) {
                    if (SystemWebViewEngine.this.webView != null) {
                        SystemWebViewEngine.this.webView.setNetworkAvailable(z);
                    }
                }

                public void runOnUiThread(Runnable runnable) {
                    SystemWebViewEngine.this.cordova.getActivity().runOnUiThread(runnable);
                }
            }));
            nativeToJsMessageQueue2.addBridgeMode(new NativeToJsMessageQueue.EvalBridgeMode(this, cordovaInterface));
            this.bridge = new CordovaBridge(pluginManager2, nativeToJsMessageQueue2);
            exposeJsInterface(this.webView, this.bridge);
            return;
        }
        throw new IllegalStateException();
    }

    public CordovaWebView getCordovaWebView() {
        return this.parentWebView;
    }

    public ICordovaCookieManager getCookieManager() {
        return this.cookieManager;
    }

    public View getView() {
        return this.webView;
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    private void initWebViewSettings() {
        this.webView.setInitialScale(0);
        this.webView.setVerticalScrollBarEnabled(false);
        final WebSettings settings = this.webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        String str = Build.MANUFACTURER;
        LOG.d(TAG, "CordovaWebView is running on device made by: " + str);
        settings.setSaveFormData(false);
        settings.setSavePassword(false);
        settings.setAllowUniversalAccessFromFileURLs(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        String path = this.webView.getContext().getApplicationContext().getDir("database", 0).getPath();
        settings.setDatabaseEnabled(true);
        settings.setDatabasePath(path);
        if ((this.webView.getContext().getApplicationContext().getApplicationInfo().flags & 2) != 0) {
            enableRemoteDebugging();
        }
        settings.setGeolocationDatabasePath(path);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(true);
        settings.setAppCacheMaxSize(5242880);
        settings.setAppCachePath(path);
        settings.setAppCacheEnabled(true);
        String userAgentString = settings.getUserAgentString();
        String string = this.preferences.getString("OverrideUserAgent", (String) null);
        if (string != null) {
            settings.setUserAgentString(string);
        } else {
            String string2 = this.preferences.getString("AppendUserAgent", (String) null);
            if (string2 != null) {
                settings.setUserAgentString(userAgentString + " " + string2);
            }
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
        if (this.receiver == null) {
            this.receiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    settings.getUserAgentString();
                }
            };
            this.webView.getContext().registerReceiver(this.receiver, intentFilter);
        }
    }

    private void enableRemoteDebugging() {
        try {
            WebView.setWebContentsDebuggingEnabled(true);
        } catch (IllegalArgumentException e) {
            LOG.d(TAG, "You have one job! To turn on Remote Web Debugging! YOU HAVE FAILED! ");
            e.printStackTrace();
        }
    }

    @SuppressLint({"AddJavascriptInterface"})
    private static void exposeJsInterface(WebView webView2, CordovaBridge cordovaBridge) {
        webView2.addJavascriptInterface(new SystemExposedJsApi(cordovaBridge), "_cordovaNative");
    }

    public void loadUrl(String str, boolean z) {
        this.webView.loadUrl(str);
    }

    public String getUrl() {
        return this.webView.getUrl();
    }

    public void stopLoading() {
        this.webView.stopLoading();
    }

    public void clearCache() {
        this.webView.clearCache(true);
    }

    public void clearHistory() {
        this.webView.clearHistory();
    }

    public boolean canGoBack() {
        return this.webView.canGoBack();
    }

    public boolean goBack() {
        if (!this.webView.canGoBack()) {
            return false;
        }
        this.webView.goBack();
        return true;
    }

    public void setPaused(boolean z) {
        if (z) {
            this.webView.onPause();
            this.webView.pauseTimers();
            return;
        }
        this.webView.onResume();
        this.webView.resumeTimers();
    }

    public void destroy() {
        this.webView.chromeClient.destroyLastDialog();
        this.webView.destroy();
        if (this.receiver != null) {
            try {
                this.webView.getContext().unregisterReceiver(this.receiver);
            } catch (Exception e) {
                LOG.e(TAG, "Error unregistering configuration receiver: " + e.getMessage(), (Throwable) e);
            }
        }
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        this.webView.evaluateJavascript(str, valueCallback);
    }
}
