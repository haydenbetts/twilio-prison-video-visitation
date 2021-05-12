package com.urbanairship.iam.fullscreen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.urbanairship.automation.R;
import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.InAppActionUtils;
import com.urbanairship.iam.InAppMessageActivity;
import com.urbanairship.iam.ResolutionInfo;
import com.urbanairship.iam.view.InAppButtonLayout;
import com.urbanairship.iam.view.InAppViewUtils;
import com.urbanairship.iam.view.MediaView;
import com.urbanairship.webkit.AirshipWebChromeClient;

public class FullScreenActivity extends InAppMessageActivity implements InAppButtonLayout.ButtonClickListener {
    protected FullScreenDisplayContent displayContent;
    private MediaView mediaView;

    /* access modifiers changed from: protected */
    public void onCreateMessage(Bundle bundle) {
        if (getMessage() == null) {
            finish();
            return;
        }
        FullScreenDisplayContent fullScreenDisplayContent = (FullScreenDisplayContent) getMessage().getDisplayContent();
        this.displayContent = fullScreenDisplayContent;
        if (fullScreenDisplayContent == null) {
            finish();
            return;
        }
        setContentView(getTemplate(normalizeTemplate(fullScreenDisplayContent)));
        hideActionBar();
        TextView textView = (TextView) findViewById(R.id.heading);
        TextView textView2 = (TextView) findViewById(R.id.body);
        InAppButtonLayout inAppButtonLayout = (InAppButtonLayout) findViewById(R.id.buttons);
        this.mediaView = (MediaView) findViewById(R.id.media);
        Button button = (Button) findViewById(R.id.footer);
        ImageButton imageButton = (ImageButton) findViewById(R.id.dismiss);
        View findViewById = findViewById(R.id.content_holder);
        if (this.displayContent.getHeading() != null) {
            InAppViewUtils.applyTextInfo(textView, this.displayContent.getHeading());
            if ("center".equals(this.displayContent.getHeading().getAlignment())) {
                normalizeHorizontalPadding(textView);
            }
        } else {
            textView.setVisibility(8);
        }
        if (this.displayContent.getBody() != null) {
            InAppViewUtils.applyTextInfo(textView2, this.displayContent.getBody());
        } else {
            textView2.setVisibility(8);
        }
        if (this.displayContent.getMedia() != null) {
            this.mediaView.setChromeClient(new AirshipWebChromeClient(this));
            InAppViewUtils.loadMediaInfo(this.mediaView, this.displayContent.getMedia(), getMessageAssets());
        } else {
            this.mediaView.setVisibility(8);
        }
        if (!this.displayContent.getButtons().isEmpty()) {
            inAppButtonLayout.setButtons(this.displayContent.getButtonLayout(), this.displayContent.getButtons());
            inAppButtonLayout.setButtonClickListener(this);
        } else {
            inAppButtonLayout.setVisibility(8);
        }
        if (this.displayContent.getFooter() != null) {
            InAppViewUtils.applyButtonInfo(button, this.displayContent.getFooter(), 0);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    FullScreenActivity fullScreenActivity = FullScreenActivity.this;
                    fullScreenActivity.onButtonClicked(view, fullScreenActivity.displayContent.getFooter());
                }
            });
        } else {
            button.setVisibility(8);
        }
        Drawable mutate = DrawableCompat.wrap(imageButton.getDrawable()).mutate();
        DrawableCompat.setTint(mutate, this.displayContent.getDismissButtonColor());
        imageButton.setImageDrawable(mutate);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (FullScreenActivity.this.getDisplayHandler() != null) {
                    FullScreenActivity.this.getDisplayHandler().finished(ResolutionInfo.dismissed(), FullScreenActivity.this.getDisplayTime());
                }
                FullScreenActivity.this.finish();
            }
        });
        getWindow().getDecorView().setBackgroundColor(this.displayContent.getBackgroundColor());
        if (ViewCompat.getFitsSystemWindows(findViewById)) {
            ViewCompat.setOnApplyWindowInsetsListener(findViewById, new OnApplyWindowInsetsListener() {
                public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                    ViewCompat.onApplyWindowInsets(view, windowInsetsCompat);
                    return windowInsetsCompat;
                }
            });
        }
    }

    public void onButtonClicked(View view, ButtonInfo buttonInfo) {
        if (getDisplayHandler() != null) {
            InAppActionUtils.runActions(buttonInfo);
            getDisplayHandler().finished(ResolutionInfo.dismissed(), getDisplayTime());
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mediaView.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mediaView.onPause();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int getTemplate(java.lang.String r4) {
        /*
            r3 = this;
            int r0 = r4.hashCode()
            r1 = -1783908295(0xffffffff95abb839, float:-6.9357E-26)
            r2 = 1
            if (r0 == r1) goto L_0x0029
            r1 = -589491207(0xffffffffdcdd13f9, float:-4.9782344E17)
            if (r0 == r1) goto L_0x001f
            r1 = 1167596047(0x45981a0f, float:4867.2573)
            if (r0 == r1) goto L_0x0015
            goto L_0x0033
        L_0x0015:
            java.lang.String r0 = "header_media_body"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0033
            r4 = 1
            goto L_0x0034
        L_0x001f:
            java.lang.String r0 = "header_body_media"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0033
            r4 = 0
            goto L_0x0034
        L_0x0029:
            java.lang.String r0 = "media_header_body"
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0033
            r4 = 2
            goto L_0x0034
        L_0x0033:
            r4 = -1
        L_0x0034:
            if (r4 == 0) goto L_0x003e
            if (r4 == r2) goto L_0x003b
            int r4 = com.urbanairship.automation.R.layout.ua_iam_fullscreen_media_header_body
            return r4
        L_0x003b:
            int r4 = com.urbanairship.automation.R.layout.ua_iam_fullscreen_header_media_body
            return r4
        L_0x003e:
            int r4 = com.urbanairship.automation.R.layout.ua_iam_fullscreen_header_body_media
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.fullscreen.FullScreenActivity.getTemplate(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    public String normalizeTemplate(FullScreenDisplayContent fullScreenDisplayContent) {
        String template = fullScreenDisplayContent.getTemplate();
        if (fullScreenDisplayContent.getMedia() == null) {
            return "header_body_media";
        }
        return (!template.equals("header_media_body") || fullScreenDisplayContent.getHeading() != null || fullScreenDisplayContent.getMedia() == null) ? template : "media_header_body";
    }

    private void normalizeHorizontalPadding(TextView textView) {
        int max = Math.max(ViewCompat.getPaddingEnd(textView), ViewCompat.getPaddingStart(textView));
        textView.setPadding(max, textView.getPaddingTop(), max, textView.getPaddingBottom());
        textView.requestLayout();
    }
}
