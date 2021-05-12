package com.urbanairship.webkit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.urbanairship.util.UriUtils;
import java.lang.ref.WeakReference;

public class AirshipWebChromeClient extends WebChromeClient {
    private View customView;
    private final WeakReference<Activity> weakActivity;

    public AirshipWebChromeClient(Activity activity) {
        this.weakActivity = new WeakReference<>(activity);
    }

    public Bitmap getDefaultVideoPoster() {
        return Bitmap.createBitmap(new int[]{0}, 1, 1, Bitmap.Config.ARGB_8888);
    }

    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        Activity activity = (Activity) this.weakActivity.get();
        if (activity != null) {
            View view2 = this.customView;
            if (view2 != null) {
                ((ViewGroup) view2.getParent()).removeView(this.customView);
            }
            this.customView = view;
            view.setBackgroundColor(-16777216);
            activity.getWindow().setFlags(1024, 1024);
            activity.getWindow().addContentView(this.customView, new FrameLayout.LayoutParams(-1, -1, 17));
        }
    }

    public void onHideCustomView() {
        Activity activity = (Activity) this.weakActivity.get();
        if (activity != null && this.customView != null) {
            activity.getWindow().clearFlags(1024);
            ((ViewGroup) this.customView.getParent()).removeView(this.customView);
            this.customView = null;
        }
    }

    public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
        if (!z2 || message == null || !(message.obj instanceof WebView.WebViewTransport)) {
            return false;
        }
        WebView webView2 = new WebView(webView.getContext());
        webView2.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return true;
                }
                Intent intent = new Intent("android.intent.action.VIEW", UriUtils.parse(str));
                intent.addFlags(268435456);
                webView.getContext().startActivity(intent);
                return true;
            }
        });
        ((WebView.WebViewTransport) message.obj).setWebView(webView2);
        message.sendToTarget();
        return true;
    }
}
