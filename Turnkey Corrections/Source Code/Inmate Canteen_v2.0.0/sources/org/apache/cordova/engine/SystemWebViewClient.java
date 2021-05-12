package org.apache.cordova.engine;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.github.kevinsawicki.http.HttpRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import org.apache.cordova.AuthenticationToken;
import org.apache.cordova.CordovaClientCertRequest;
import org.apache.cordova.CordovaHttpAuthHandler;
import org.apache.cordova.CordovaResourceApi;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;

public class SystemWebViewClient extends WebViewClient {
    private static final String TAG = "SystemWebViewClient";
    private Hashtable<String, AuthenticationToken> authenticationTokens = new Hashtable<>();
    private boolean doClearHistory = false;
    boolean isCurrentlyLoading;
    protected final SystemWebViewEngine parentEngine;

    public SystemWebViewClient(SystemWebViewEngine systemWebViewEngine) {
        this.parentEngine = systemWebViewEngine;
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return this.parentEngine.client.onNavigationAttempt(str);
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        AuthenticationToken authenticationToken = getAuthenticationToken(str, str2);
        if (authenticationToken != null) {
            httpAuthHandler.proceed(authenticationToken.getUserName(), authenticationToken.getPassword());
            return;
        }
        PluginManager pluginManager = this.parentEngine.pluginManager;
        if (pluginManager == null || !pluginManager.onReceivedHttpAuthRequest((CordovaWebView) null, new CordovaHttpAuthHandler(httpAuthHandler), str, str2)) {
            super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        } else {
            this.parentEngine.client.clearLoadTimeoutTimer();
        }
    }

    @TargetApi(21)
    public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
        PluginManager pluginManager = this.parentEngine.pluginManager;
        if (pluginManager == null || !pluginManager.onReceivedClientCertRequest((CordovaWebView) null, new CordovaClientCertRequest(clientCertRequest))) {
            super.onReceivedClientCertRequest(webView, clientCertRequest);
        } else {
            this.parentEngine.client.clearLoadTimeoutTimer();
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.isCurrentlyLoading = true;
        this.parentEngine.bridge.reset();
        this.parentEngine.client.onPageStarted(str);
    }

    public void onPageFinished(WebView webView, String str) {
        super.onPageFinished(webView, str);
        if (this.isCurrentlyLoading || str.startsWith("about:")) {
            this.isCurrentlyLoading = false;
            if (this.doClearHistory) {
                webView.clearHistory();
                this.doClearHistory = false;
            }
            this.parentEngine.client.onPageFinishedLoading(str);
        }
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.isCurrentlyLoading) {
            LOG.d(TAG, "CordovaWebViewClient.onReceivedError: Error code=%s Description=%s URL=%s", Integer.valueOf(i), str, str2);
            if (i == -10) {
                this.parentEngine.client.clearLoadTimeoutTimer();
                if (webView.canGoBack()) {
                    webView.goBack();
                    return;
                }
                super.onReceivedError(webView, i, str, str2);
            }
            this.parentEngine.client.onReceivedError(i, str, str2);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        try {
            if ((this.parentEngine.cordova.getActivity().getPackageManager().getApplicationInfo(this.parentEngine.cordova.getActivity().getPackageName(), 128).flags & 2) != 0) {
                sslErrorHandler.proceed();
            } else {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }

    public void setAuthenticationToken(AuthenticationToken authenticationToken, String str, String str2) {
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        this.authenticationTokens.put(str.concat(str2), authenticationToken);
    }

    public AuthenticationToken removeAuthenticationToken(String str, String str2) {
        return this.authenticationTokens.remove(str.concat(str2));
    }

    public AuthenticationToken getAuthenticationToken(String str, String str2) {
        AuthenticationToken authenticationToken = this.authenticationTokens.get(str.concat(str2));
        if (authenticationToken != null) {
            return authenticationToken;
        }
        AuthenticationToken authenticationToken2 = this.authenticationTokens.get(str);
        AuthenticationToken authenticationToken3 = authenticationToken2 == null ? this.authenticationTokens.get(str2) : authenticationToken2;
        return authenticationToken3 == null ? this.authenticationTokens.get("") : authenticationToken3;
    }

    public void clearAuthenticationTokens() {
        this.authenticationTokens.clear();
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        try {
            if (!this.parentEngine.pluginManager.shouldAllowRequest(str)) {
                LOG.w(TAG, "URL blocked by whitelist: " + str);
                return new WebResourceResponse("text/plain", HttpRequest.CHARSET_UTF8, (InputStream) null);
            }
            CordovaResourceApi cordovaResourceApi = this.parentEngine.resourceApi;
            Uri parse = Uri.parse(str);
            Uri remapUri = cordovaResourceApi.remapUri(parse);
            if (parse.equals(remapUri) && !needsSpecialsInAssetUrlFix(parse)) {
                if (!needsKitKatContentUrlFix(parse)) {
                    return null;
                }
            }
            CordovaResourceApi.OpenForReadResult openForRead = cordovaResourceApi.openForRead(remapUri, true);
            return new WebResourceResponse(openForRead.mimeType, HttpRequest.CHARSET_UTF8, openForRead.inputStream);
        } catch (IOException e) {
            if (!(e instanceof FileNotFoundException)) {
                LOG.e(TAG, "Error occurred while loading a file (returning a 404).", (Throwable) e);
            }
            return new WebResourceResponse("text/plain", HttpRequest.CHARSET_UTF8, (InputStream) null);
        }
    }

    private static boolean needsKitKatContentUrlFix(Uri uri) {
        return "content".equals(uri.getScheme());
    }

    private static boolean needsSpecialsInAssetUrlFix(Uri uri) {
        if (CordovaResourceApi.getUriType(uri) != 1) {
            return false;
        }
        if (uri.getQuery() == null && uri.getFragment() == null) {
            return !uri.toString().contains("%") ? false : false;
        }
        return true;
    }
}
