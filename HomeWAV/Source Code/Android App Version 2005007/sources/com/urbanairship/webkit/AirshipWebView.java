package com.urbanairship.webkit;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Base64;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.microsoft.appcenter.Constants;
import com.urbanairship.Logger;
import com.urbanairship.R;
import com.urbanairship.UAirship;
import com.urbanairship.util.ManifestUtils;
import java.io.File;
import java.util.Map;

public class AirshipWebView extends WebView {
    private static final String CACHE_DIRECTORY = "urbanairship";
    private String currentClientAuthRequestUrl;
    private WebViewClient webViewClient;

    /* access modifiers changed from: protected */
    public void initializeView() {
    }

    /* access modifiers changed from: protected */
    public void populateCustomJavascriptInterfaces() {
    }

    public AirshipWebView(Context context) {
        this(context, (AttributeSet) null);
    }

    public AirshipWebView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842885);
    }

    public AirshipWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        if (!isInEditMode()) {
            init(context, attributeSet, i, 0);
        }
    }

    public AirshipWebView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        if (!isInEditMode()) {
            init(context, attributeSet, i, i2);
        }
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        WebSettings settings = getSettings();
        settings.setAppCacheEnabled(true);
        settings.setAppCachePath(getCachePath());
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21 && attributeSet != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.AirshipWebView, i, i2);
            try {
                settings.setMixedContentMode(obtainStyledAttributes.getInteger(R.styleable.AirshipWebView_mixed_content_mode, 2));
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        settings.setAllowFileAccess(true);
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(-1);
        if (ManifestUtils.shouldEnableLocalStorage()) {
            Logger.verbose("AirshipWebView - Application contains metadata to enable local storage", new Object[0]);
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            if (Build.VERSION.SDK_INT < 19) {
                settings.setDatabasePath(UAirship.getApplicationContext().getDir(ManifestUtils.LOCAL_STORAGE_DATABASE_DIRECTORY, 0).getPath());
            }
        }
        initializeView();
        populateCustomJavascriptInterfaces();
    }

    public void loadData(String str, String str2, String str3) {
        onPreLoad();
        super.loadData(str, str2, str3);
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        onPreLoad();
        super.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        onPreLoad();
        super.loadUrl(str);
    }

    public void loadUrl(String str, Map<String, String> map) {
        onPreLoad();
        super.loadUrl(str, map);
    }

    public void setWebViewClient(WebViewClient webViewClient2) {
        if (webViewClient2 != null && !(webViewClient2 instanceof AirshipWebViewClient)) {
            Logger.warn("The web view client should extend AirshipWebViewClient to support Airship url overrides and triggering actions from.", new Object[0]);
        }
        this.webViewClient = webViewClient2;
        super.setWebViewClient(webViewClient2);
    }

    /* access modifiers changed from: protected */
    public void onPreLoad() {
        if (getWebViewClientCompat() == null) {
            Logger.debug("No web view client set, setting a default AirshipWebViewClient for landing page view.", new Object[0]);
            setWebViewClient(new AirshipWebViewClient());
        }
        if (this.currentClientAuthRequestUrl != null && getWebViewClientCompat() != null && (getWebViewClientCompat() instanceof AirshipWebViewClient)) {
            ((AirshipWebViewClient) getWebViewClientCompat()).removeAuthRequestCredentials(this.currentClientAuthRequestUrl);
            this.currentClientAuthRequestUrl = null;
        }
    }

    /* access modifiers changed from: package-private */
    public WebViewClient getWebViewClientCompat() {
        return this.webViewClient;
    }

    private String getCachePath() {
        File file = new File(UAirship.getApplicationContext().getCacheDir(), CACHE_DIRECTORY);
        if (!file.exists() && !file.mkdirs()) {
            Logger.error("Failed to create the web cache directory.", new Object[0]);
        }
        return file.getAbsolutePath();
    }

    /* access modifiers changed from: protected */
    public void setClientAuthRequest(String str, String str2, String str3) {
        this.currentClientAuthRequestUrl = str;
        if (getWebViewClientCompat() != null && (getWebViewClientCompat() instanceof AirshipWebViewClient)) {
            AirshipWebViewClient airshipWebViewClient = (AirshipWebViewClient) getWebViewClientCompat();
            String host = Uri.parse(str).getHost();
            if (host != null) {
                airshipWebViewClient.addAuthRequestCredentials(host, str2, str3);
            }
        }
    }

    /* access modifiers changed from: protected */
    public String createBasicAuth(String str, String str2) {
        String str3 = str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR + str2;
        return "Basic " + Base64.encodeToString(str3.getBytes(), 2);
    }
}
