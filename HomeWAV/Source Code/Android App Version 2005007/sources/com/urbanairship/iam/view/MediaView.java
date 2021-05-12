package com.urbanairship.iam.view;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.iam.MediaInfo;
import com.urbanairship.images.ImageRequestOptions;
import com.urbanairship.util.ManifestUtils;
import java.lang.ref.WeakReference;
import java.util.Locale;

public class MediaView extends FrameLayout {
    private static final String VIDEO_HTML_FORMAT = "<body style=\"margin:0\"><video playsinline controls height=\"100%%\" width=\"100%%\" src=\"%s\"></video></body>";
    private WebChromeClient chromeClient;
    private WebView webView;

    public MediaView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MediaView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MediaView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public MediaView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    public void setChromeClient(WebChromeClient webChromeClient) {
        this.chromeClient = webChromeClient;
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.setWebChromeClient(webChromeClient);
        }
    }

    public void onPause() {
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.onPause();
        }
    }

    public void onResume() {
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.onResume();
        }
    }

    public void setMediaInfo(MediaInfo mediaInfo, String str) {
        removeAllViewsInLayout();
        WebView webView2 = this.webView;
        if (webView2 != null) {
            webView2.stopLoading();
            this.webView.setWebChromeClient((WebChromeClient) null);
            this.webView.setWebViewClient((WebViewClient) null);
            this.webView.destroy();
            this.webView = null;
        }
        String type = mediaInfo.getType();
        type.hashCode();
        char c = 65535;
        switch (type.hashCode()) {
            case -991745245:
                if (type.equals(MediaInfo.TYPE_YOUTUBE)) {
                    c = 0;
                    break;
                }
                break;
            case 100313435:
                if (type.equals("image")) {
                    c = 1;
                    break;
                }
                break;
            case 112202875:
                if (type.equals("video")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 2:
                loadWebView(mediaInfo);
                return;
            case 1:
                ImageView imageView = new ImageView(getContext());
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setAdjustViewBounds(true);
                imageView.setContentDescription(mediaInfo.getDescription());
                addView(imageView);
                if (str == null) {
                    str = mediaInfo.getUrl();
                }
                UAirship.shared().getImageLoader().load(getContext(), imageView, ImageRequestOptions.newBuilder(str).build());
                return;
            default:
                return;
        }
    }

    private void loadWebView(final MediaInfo mediaInfo) {
        this.webView = new WebView(getContext());
        FrameLayout frameLayout = new FrameLayout(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        frameLayout.addView(this.webView, layoutParams);
        final ProgressBar progressBar = new ProgressBar(getContext());
        progressBar.setIndeterminate(true);
        progressBar.setId(16908301);
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 17;
        frameLayout.addView(progressBar, layoutParams2);
        WebSettings settings = this.webView.getSettings();
        if (Build.VERSION.SDK_INT >= 17) {
            settings.setMediaPlaybackRequiresUserGesture(true);
        }
        settings.setJavaScriptEnabled(true);
        if (ManifestUtils.shouldEnableLocalStorage()) {
            settings.setDomStorageEnabled(true);
            settings.setDatabaseEnabled(true);
            if (Build.VERSION.SDK_INT < 19) {
                settings.setDatabasePath(UAirship.getApplicationContext().getDir(ManifestUtils.LOCAL_STORAGE_DATABASE_DIRECTORY, 0).getPath());
            }
        }
        final WeakReference weakReference = new WeakReference(this.webView);
        AnonymousClass1 r4 = new Runnable() {
            public void run() {
                WebView webView = (WebView) weakReference.get();
                if (webView != null) {
                    if ("video".equals(mediaInfo.getType())) {
                        webView.loadData(String.format(Locale.ROOT, MediaView.VIDEO_HTML_FORMAT, new Object[]{mediaInfo.getUrl()}), "text/html", "UTF-8");
                        return;
                    }
                    webView.loadUrl(mediaInfo.getUrl());
                }
            }
        };
        this.webView.setWebChromeClient(this.chromeClient);
        this.webView.setContentDescription(mediaInfo.getDescription());
        this.webView.setVisibility(4);
        this.webView.setWebViewClient(new MediaWebViewClient(r4) {
            /* access modifiers changed from: protected */
            public void onPageFinished(WebView webView) {
                webView.setVisibility(0);
                progressBar.setVisibility(8);
            }
        });
        addView(frameLayout);
        if (!UAirship.shared().getUrlAllowList().isAllowed(mediaInfo.getUrl(), 2)) {
            Logger.error("URL not allowed. Unable to load: %s", mediaInfo.getUrl());
            return;
        }
        r4.run();
    }

    private static abstract class MediaWebViewClient extends WebViewClient {
        static final long START_RETRY_DELAY = 1000;
        boolean error;
        private final Runnable onRetry;
        long retryDelay;

        /* access modifiers changed from: protected */
        public abstract void onPageFinished(WebView webView);

        private MediaWebViewClient(Runnable runnable) {
            this.error = false;
            this.retryDelay = 1000;
            this.onRetry = runnable;
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            if (this.error) {
                webView.postDelayed(this.onRetry, this.retryDelay);
                this.retryDelay *= 2;
            } else {
                onPageFinished(webView);
            }
            this.error = false;
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            super.onReceivedError(webView, webResourceRequest, webResourceError);
            this.error = true;
        }
    }
}
