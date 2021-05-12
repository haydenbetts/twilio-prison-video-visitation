package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import org.apache.cordova.ICordovaCookieManager;

class SystemCookieManager implements ICordovaCookieManager {
    private final CookieManager cookieManager = CookieManager.getInstance();
    protected final WebView webView;

    @TargetApi(21)
    public SystemCookieManager(WebView webView2) {
        this.webView = webView2;
        CookieManager cookieManager2 = this.cookieManager;
        CookieManager.setAcceptFileSchemeCookies(true);
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.setAcceptThirdPartyCookies(this.webView, true);
        }
    }

    public void setCookiesEnabled(boolean z) {
        this.cookieManager.setAcceptCookie(z);
    }

    public void setCookie(String str, String str2) {
        this.cookieManager.setCookie(str, str2);
    }

    public String getCookie(String str) {
        return this.cookieManager.getCookie(str);
    }

    public void clearCookies() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.removeAllCookies((ValueCallback) null);
        } else {
            this.cookieManager.removeAllCookie();
        }
    }

    public void flush() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.cookieManager.flush();
        }
    }
}
