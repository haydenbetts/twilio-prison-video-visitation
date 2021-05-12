package com.urbanairship.iam.html;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Handler;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import com.urbanairship.Logger;
import com.urbanairship.automation.R;
import com.urbanairship.iam.InAppMessageActivity;
import com.urbanairship.webkit.AirshipWebView;
import java.lang.ref.WeakReference;

public class HtmlActivity extends InAppMessageActivity {
    private static final long RETRY_DELAY_MS = 20000;
    private final Runnable delayedLoadRunnable = new Runnable() {
        public void run() {
            HtmlActivity.this.load();
        }
    };
    /* access modifiers changed from: private */
    public Integer error = null;
    private Handler handler;
    private String url;
    /* access modifiers changed from: private */
    public AirshipWebView webView;

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0097  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreateMessage(android.os.Bundle r12) {
        /*
            r11 = this;
            com.urbanairship.iam.InAppMessage r12 = r11.getMessage()
            if (r12 != 0) goto L_0x000a
            r11.finish()
            return
        L_0x000a:
            com.urbanairship.iam.InAppMessage r12 = r11.getMessage()
            com.urbanairship.iam.DisplayContent r12 = r12.getDisplayContent()
            com.urbanairship.iam.html.HtmlDisplayContent r12 = (com.urbanairship.iam.html.HtmlDisplayContent) r12
            r0 = 0
            r1 = 1
            if (r12 != 0) goto L_0x002d
            java.lang.Object[] r12 = new java.lang.Object[r1]
            com.urbanairship.iam.InAppMessage r1 = r11.getMessage()
            com.urbanairship.iam.DisplayContent r1 = r1.getDisplayContent()
            r12[r0] = r1
            java.lang.String r0 = "HtmlActivity - Invalid display type: %s"
            com.urbanairship.Logger.error(r0, r12)
            r11.finish()
            return
        L_0x002d:
            boolean r2 = r11.isFullScreen(r12)
            r3 = 19
            r4 = 0
            if (r2 == 0) goto L_0x0041
            int r2 = com.urbanairship.automation.R.style.UrbanAirship_InAppHtml_Activity_Fullscreen
            r11.setTheme(r2)
            int r2 = com.urbanairship.automation.R.layout.ua_iam_html_fullscreen
            r11.setContentView((int) r2)
            goto L_0x004f
        L_0x0041:
            int r2 = com.urbanairship.automation.R.layout.ua_iam_html
            r11.setContentView((int) r2)
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 < r3) goto L_0x004f
            float r2 = r12.getBorderRadius()
            goto L_0x0050
        L_0x004f:
            r2 = 0
        L_0x0050:
            int r5 = com.urbanairship.automation.R.id.progress
            android.view.View r5 = r11.findViewById(r5)
            android.widget.ProgressBar r5 = (android.widget.ProgressBar) r5
            int r6 = com.urbanairship.automation.R.id.dismiss
            android.view.View r6 = r11.findViewById(r6)
            android.widget.ImageButton r6 = (android.widget.ImageButton) r6
            int r7 = com.urbanairship.automation.R.id.content_holder
            android.view.View r7 = r11.findViewById(r7)
            com.urbanairship.iam.view.BoundedFrameLayout r7 = (com.urbanairship.iam.view.BoundedFrameLayout) r7
            r11.applySizeConstraints(r12)
            int r8 = com.urbanairship.automation.R.id.web_view
            android.view.View r8 = r11.findViewById(r8)
            com.urbanairship.webkit.AirshipWebView r8 = (com.urbanairship.webkit.AirshipWebView) r8
            r11.webView = r8
            android.os.Handler r8 = new android.os.Handler
            android.os.Looper r9 = android.os.Looper.getMainLooper()
            r8.<init>(r9)
            r11.handler = r8
            java.lang.String r8 = r12.getUrl()
            r11.url = r8
            com.urbanairship.UAirship r8 = com.urbanairship.UAirship.shared()
            com.urbanairship.js.UrlAllowList r8 = r8.getUrlAllowList()
            java.lang.String r9 = r11.url
            r10 = 2
            boolean r8 = r8.isAllowed(r9, r10)
            if (r8 != 0) goto L_0x00a2
            java.lang.Object[] r12 = new java.lang.Object[r0]
            java.lang.String r0 = "HTML in-app message URL is not allowed. Unable to display message."
            com.urbanairship.Logger.error(r0, r12)
            r11.finish()
            return
        L_0x00a2:
            int r0 = android.os.Build.VERSION.SDK_INT
            r8 = 21
            if (r0 >= r8) goto L_0x00ae
            com.urbanairship.webkit.AirshipWebView r0 = r11.webView
            r8 = 0
            r0.setLayerType(r1, r8)
        L_0x00ae:
            com.urbanairship.webkit.AirshipWebView r0 = r11.webView
            com.urbanairship.iam.html.HtmlActivity$2 r8 = new com.urbanairship.iam.html.HtmlActivity$2
            r8.<init>(r5)
            r0.setWebViewClient(r8)
            com.urbanairship.webkit.AirshipWebView r0 = r11.webView
            r0.setAlpha(r4)
            com.urbanairship.webkit.AirshipWebView r0 = r11.webView
            android.webkit.WebSettings r0 = r0.getSettings()
            r0.setSupportMultipleWindows(r1)
            com.urbanairship.webkit.AirshipWebView r0 = r11.webView
            com.urbanairship.iam.html.HtmlActivity$3 r1 = new com.urbanairship.iam.html.HtmlActivity$3
            r1.<init>(r11)
            r0.setWebChromeClient(r1)
            android.graphics.drawable.Drawable r0 = r6.getDrawable()
            android.graphics.drawable.Drawable r0 = androidx.core.graphics.drawable.DrawableCompat.wrap(r0)
            android.graphics.drawable.Drawable r0 = r0.mutate()
            int r1 = r12.getDismissButtonColor()
            androidx.core.graphics.drawable.DrawableCompat.setTint(r0, r1)
            r6.setImageDrawable(r0)
            com.urbanairship.iam.html.HtmlActivity$4 r0 = new com.urbanairship.iam.html.HtmlActivity$4
            r0.<init>()
            r6.setOnClickListener(r0)
            int r12 = r12.getBackgroundColor()
            r7.setBackgroundColor(r12)
            int r12 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r12 <= 0) goto L_0x0100
            int r12 = android.os.Build.VERSION.SDK_INT
            if (r12 < r3) goto L_0x0100
            r7.setClipPathBorderRadius(r2)
        L_0x0100:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.html.HtmlActivity.onCreateMessage(android.os.Bundle):void");
    }

    private boolean isFullScreen(HtmlDisplayContent htmlDisplayContent) {
        if (!htmlDisplayContent.isFullscreenDisplayAllowed()) {
            return false;
        }
        return getResources().getBoolean(R.bool.ua_iam_html_allow_fullscreen_display);
    }

    public void onResume() {
        super.onResume();
        this.webView.onResume();
        load();
    }

    public void onPause() {
        super.onPause();
        this.webView.onPause();
        this.webView.stopLoading();
        this.handler.removeCallbacks(this.delayedLoadRunnable);
    }

    /* access modifiers changed from: private */
    public void crossFade(View view, final View view2) {
        if (view != null) {
            view.animate().alpha(1.0f).setDuration(200);
        }
        if (view2 != null) {
            view2.animate().alpha(0.0f).setDuration(200).setListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    view2.setVisibility(8);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void load() {
        load(0);
    }

    /* access modifiers changed from: protected */
    public void load(long j) {
        AirshipWebView airshipWebView = this.webView;
        if (airshipWebView != null) {
            airshipWebView.stopLoading();
            if (j > 0) {
                this.handler.postDelayed(this.delayedLoadRunnable, j);
                return;
            }
            Logger.info("Loading url: %s", this.url);
            this.error = null;
            this.webView.loadUrl(this.url);
        }
    }

    public void applySizeConstraints(HtmlDisplayContent htmlDisplayContent) {
        View findViewById;
        if ((htmlDisplayContent.getWidth() != 0 || htmlDisplayContent.getHeight() != 0) && (findViewById = findViewById(R.id.content_holder)) != null) {
            final int applyDimension = (int) TypedValue.applyDimension(1, (float) htmlDisplayContent.getWidth(), getResources().getDisplayMetrics());
            final int applyDimension2 = (int) TypedValue.applyDimension(1, (float) htmlDisplayContent.getHeight(), getResources().getDisplayMetrics());
            final boolean aspectRatioLock = htmlDisplayContent.getAspectRatioLock();
            final WeakReference weakReference = new WeakReference(findViewById);
            findViewById.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    int i;
                    View view = (View) weakReference.get();
                    if (view == null) {
                        return true;
                    }
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    int measuredWidth = view.getMeasuredWidth();
                    int measuredHeight = view.getMeasuredHeight();
                    int min = Math.min(measuredWidth, applyDimension);
                    int min2 = Math.min(measuredHeight, applyDimension2);
                    if (aspectRatioLock && !(min == (i = applyDimension) && min2 == applyDimension2)) {
                        int i2 = applyDimension2;
                        float f = (float) measuredWidth;
                        float f2 = (float) measuredHeight;
                        if (f / f2 > ((float) i) / ((float) i2)) {
                            min = (int) ((((float) i) * f2) / ((float) i2));
                        } else {
                            min2 = (int) ((((float) i2) * f) / ((float) i));
                        }
                    }
                    if (min2 > 0) {
                        layoutParams.height = min2;
                    }
                    if (min > 0) {
                        layoutParams.width = min;
                    }
                    view.setLayoutParams(layoutParams);
                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
        }
    }
}
