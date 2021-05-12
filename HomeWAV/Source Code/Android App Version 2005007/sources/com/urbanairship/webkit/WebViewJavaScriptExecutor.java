package com.urbanairship.webkit;

import android.os.Build;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.urbanairship.javascript.JavaScriptExecutor;
import java.lang.ref.WeakReference;

public class WebViewJavaScriptExecutor implements JavaScriptExecutor {
    private WeakReference<WebView> weakReference;

    public WebViewJavaScriptExecutor(WebView webView) {
        this.weakReference = new WeakReference<>(webView);
    }

    public void executeJavaScript(String str) {
        WebView webView = (WebView) this.weakReference.get();
        if (webView != null) {
            if (Build.VERSION.SDK_INT >= 19) {
                webView.evaluateJavascript(str, (ValueCallback) null);
                return;
            }
            webView.loadUrl("javascript:" + str);
        }
    }
}
