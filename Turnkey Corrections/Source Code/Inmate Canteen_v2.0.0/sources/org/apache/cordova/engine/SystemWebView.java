package org.apache.cordova.engine;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewEngine;

public class SystemWebView extends WebView implements CordovaWebViewEngine.EngineView {
    SystemWebChromeClient chromeClient;
    private CordovaInterface cordova;
    private SystemWebViewEngine parentEngine;
    private SystemWebViewClient viewClient;

    public SystemWebView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SystemWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    /* access modifiers changed from: package-private */
    public void init(SystemWebViewEngine systemWebViewEngine, CordovaInterface cordovaInterface) {
        this.cordova = cordovaInterface;
        this.parentEngine = systemWebViewEngine;
        if (this.viewClient == null) {
            setWebViewClient(new SystemWebViewClient(systemWebViewEngine));
        }
        if (this.chromeClient == null) {
            setWebChromeClient(new SystemWebChromeClient(systemWebViewEngine));
        }
    }

    public CordovaWebView getCordovaWebView() {
        SystemWebViewEngine systemWebViewEngine = this.parentEngine;
        if (systemWebViewEngine != null) {
            return systemWebViewEngine.getCordovaWebView();
        }
        return null;
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.viewClient = (SystemWebViewClient) webViewClient;
        super.setWebViewClient(webViewClient);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.chromeClient = (SystemWebChromeClient) webChromeClient;
        super.setWebChromeClient(webChromeClient);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Boolean onDispatchKeyEvent = this.parentEngine.client.onDispatchKeyEvent(keyEvent);
        if (onDispatchKeyEvent != null) {
            return onDispatchKeyEvent.booleanValue();
        }
        return super.dispatchKeyEvent(keyEvent);
    }
}
