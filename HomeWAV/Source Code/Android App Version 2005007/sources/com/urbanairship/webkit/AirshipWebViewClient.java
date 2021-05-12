package com.urbanairship.webkit;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.urbanairship.Cancelable;
import com.urbanairship.Logger;
import com.urbanairship.R;
import com.urbanairship.UAirship;
import com.urbanairship.actions.ActionCompletionCallback;
import com.urbanairship.actions.ActionRunRequest;
import com.urbanairship.actions.ActionRunRequestExtender;
import com.urbanairship.actions.ActionRunRequestFactory;
import com.urbanairship.javascript.JavaScriptEnvironment;
import com.urbanairship.javascript.NativeBridge;
import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class AirshipWebViewClient extends WebViewClient {
    private final Map<String, Credentials> authRequestCredentials;
    private boolean faviconEnabled;
    private final NativeBridge nativeBridge;
    private final Map<WebView, Cancelable> pendingNativeBridgeLoads;

    /* access modifiers changed from: protected */
    public ActionRunRequest extendActionRequest(ActionRunRequest actionRunRequest, WebView webView) {
        return actionRunRequest;
    }

    /* access modifiers changed from: protected */
    public void onAirshipCommand(WebView webView, String str, Uri uri) {
    }

    public AirshipWebViewClient() {
        this(new ActionRunRequestFactory());
    }

    public AirshipWebViewClient(ActionRunRequestFactory actionRunRequestFactory) {
        this(new NativeBridge(actionRunRequestFactory));
    }

    AirshipWebViewClient(NativeBridge nativeBridge2) {
        this.authRequestCredentials = new HashMap();
        this.pendingNativeBridgeLoads = new WeakHashMap();
        this.faviconEnabled = false;
        this.nativeBridge = nativeBridge2;
    }

    /* access modifiers changed from: protected */
    public JavaScriptEnvironment.Builder extendJavascriptEnvironment(JavaScriptEnvironment.Builder builder, WebView webView) {
        return builder.addGetter("getDeviceModel", Build.MODEL).addGetter("getChannelId", UAirship.shared().getChannel().getId()).addGetter("getAppKey", UAirship.shared().getAirshipConfigOptions().appKey).addGetter("getNamedUser", UAirship.shared().getNamedUser().getId());
    }

    /* access modifiers changed from: protected */
    public void onClose(WebView webView) {
        webView.getRootView().dispatchKeyEvent(new KeyEvent(0, 4));
        webView.getRootView().dispatchKeyEvent(new KeyEvent(1, 4));
    }

    public void setActionCompletionCallback(ActionCompletionCallback actionCompletionCallback) {
        this.nativeBridge.setActionCompletionCallback(actionCompletionCallback);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (interceptUrl(webView, str)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(webView, str);
    }

    public void setFaviconEnabled(boolean z) {
        this.faviconEnabled = z;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (!this.faviconEnabled && str.toLowerCase().endsWith("/favicon.ico")) {
            return generateEmptyFaviconResponse(webView);
        }
        return null;
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        String path;
        if (this.faviconEnabled) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        if (webResourceRequest.isForMainFrame() || (path = webResourceRequest.getUrl().getPath()) == null || !path.endsWith("/favicon.ico")) {
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }
        return generateEmptyFaviconResponse(webView);
    }

    public void onLoadResource(WebView webView, String str) {
        interceptUrl(webView, str);
    }

    private boolean interceptUrl(final WebView webView, String str) {
        if (!isAllowed(webView.getUrl())) {
            return false;
        }
        return this.nativeBridge.onHandleCommand(str, new WebViewJavaScriptExecutor(webView), new ActionRunRequestExtender() {
            public ActionRunRequest extend(ActionRunRequest actionRunRequest) {
                return AirshipWebViewClient.this.extendActionRequest(actionRunRequest, webView);
            }
        }, new NativeBridge.CommandDelegate() {
            public void onClose() {
                AirshipWebViewClient.this.onClose(webView);
            }

            public void onAirshipCommand(String str, Uri uri) {
                AirshipWebViewClient.this.onAirshipCommand(webView, str, uri);
            }
        });
    }

    private WebResourceResponse generateEmptyFaviconResponse(WebView webView) {
        try {
            return new WebResourceResponse("image/png", (String) null, new BufferedInputStream(webView.getContext().getResources().openRawResource(R.raw.ua_blank_favicon)));
        } catch (Exception e) {
            Logger.error(e, "Failed to read blank favicon with IOException.", new Object[0]);
            return null;
        }
    }

    public void onPageFinished(WebView webView, String str) {
        if (webView != null) {
            if (!isAllowed(str)) {
                Logger.debug("AirshipWebViewClient - %s is not an allowed URL. Airship Javascript interface will not be accessible.", str);
                return;
            }
            this.pendingNativeBridgeLoads.put(webView, this.nativeBridge.loadJavaScriptEnvironment(webView.getContext(), extendJavascriptEnvironment(JavaScriptEnvironment.newBuilder(), webView).build(), new WebViewJavaScriptExecutor(webView)));
        }
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        Cancelable cancelable = this.pendingNativeBridgeLoads.get(webView);
        if (cancelable != null) {
            cancelable.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public boolean isAllowed(String str) {
        return UAirship.shared().getUrlAllowList().isAllowed(str, 1);
    }

    public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        Credentials credentials = this.authRequestCredentials.get(str);
        if (credentials != null) {
            httpAuthHandler.proceed(credentials.username, credentials.password);
        }
    }

    public void addAuthRequestCredentials(String str, String str2, String str3) {
        this.authRequestCredentials.put(str, new Credentials(str2, str3));
    }

    public void removeAuthRequestCredentials(String str) {
        this.authRequestCredentials.remove(str);
    }

    private static class Credentials {
        final String password;
        final String username;

        Credentials(String str, String str2) {
            this.username = str;
            this.password = str2;
        }
    }
}
