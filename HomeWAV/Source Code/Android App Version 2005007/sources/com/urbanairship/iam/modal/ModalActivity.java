package com.urbanairship.iam.modal;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewCompat;
import com.urbanairship.automation.R;
import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.InAppActionUtils;
import com.urbanairship.iam.InAppMessageActivity;
import com.urbanairship.iam.ResolutionInfo;
import com.urbanairship.iam.view.BackgroundDrawableBuilder;
import com.urbanairship.iam.view.BoundedLinearLayout;
import com.urbanairship.iam.view.InAppButtonLayout;
import com.urbanairship.iam.view.InAppViewUtils;
import com.urbanairship.iam.view.MediaView;
import com.urbanairship.webkit.AirshipWebChromeClient;

public class ModalActivity extends InAppMessageActivity implements InAppButtonLayout.ButtonClickListener {
    private MediaView mediaView;

    /* access modifiers changed from: protected */
    public void onCreateMessage(Bundle bundle) {
        float f;
        if (getMessage() == null) {
            finish();
            return;
        }
        final ModalDisplayContent modalDisplayContent = (ModalDisplayContent) getMessage().getDisplayContent();
        if (modalDisplayContent == null) {
            finish();
            return;
        }
        if (!modalDisplayContent.isFullscreenDisplayAllowed() || !getResources().getBoolean(R.bool.ua_iam_modal_allow_fullscreen_display)) {
            if (modalDisplayContent.getMedia() == null || Build.VERSION.SDK_INT >= 19) {
                f = modalDisplayContent.getBorderRadius();
            } else {
                f = 0.0f;
            }
            setContentView(R.layout.ua_iam_modal);
        } else {
            setTheme(R.style.UrbanAirship_InAppModal_Activity_Fullscreen);
            setContentView(R.layout.ua_iam_modal_fullscreen);
            f = 0.0f;
        }
        String normalizeTemplate = normalizeTemplate(modalDisplayContent);
        ViewStub viewStub = (ViewStub) findViewById(R.id.modal_content);
        viewStub.setLayoutResource(getTemplate(normalizeTemplate));
        viewStub.inflate();
        BoundedLinearLayout boundedLinearLayout = (BoundedLinearLayout) findViewById(R.id.modal);
        TextView textView = (TextView) findViewById(R.id.heading);
        TextView textView2 = (TextView) findViewById(R.id.body);
        InAppButtonLayout inAppButtonLayout = (InAppButtonLayout) findViewById(R.id.buttons);
        this.mediaView = (MediaView) findViewById(R.id.media);
        Button button = (Button) findViewById(R.id.footer);
        ImageButton imageButton = (ImageButton) findViewById(R.id.dismiss);
        if (modalDisplayContent.getHeading() != null) {
            InAppViewUtils.applyTextInfo(textView, modalDisplayContent.getHeading());
            if ("center".equals(modalDisplayContent.getHeading().getAlignment())) {
                normalizeHorizontalPadding(textView);
            }
        } else {
            textView.setVisibility(8);
        }
        if (modalDisplayContent.getBody() != null) {
            InAppViewUtils.applyTextInfo(textView2, modalDisplayContent.getBody());
        } else {
            textView2.setVisibility(8);
        }
        if (modalDisplayContent.getMedia() != null) {
            this.mediaView.setChromeClient(new AirshipWebChromeClient(this));
            InAppViewUtils.loadMediaInfo(this.mediaView, modalDisplayContent.getMedia(), getMessageAssets());
        } else {
            this.mediaView.setVisibility(8);
        }
        if (!modalDisplayContent.getButtons().isEmpty()) {
            inAppButtonLayout.setButtons(modalDisplayContent.getButtonLayout(), modalDisplayContent.getButtons());
            inAppButtonLayout.setButtonClickListener(this);
        } else {
            inAppButtonLayout.setVisibility(8);
        }
        if (modalDisplayContent.getFooter() != null) {
            InAppViewUtils.applyButtonInfo(button, modalDisplayContent.getFooter(), 0);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ModalActivity.this.onButtonClicked(view, modalDisplayContent.getFooter());
                }
            });
        } else {
            button.setVisibility(8);
        }
        ViewCompat.setBackground(boundedLinearLayout, BackgroundDrawableBuilder.newBuilder(this).setBackgroundColor(modalDisplayContent.getBackgroundColor()).setBorderRadius(f, 15).build());
        if (f > 0.0f && Build.VERSION.SDK_INT >= 19) {
            boundedLinearLayout.setClipPathBorderRadius(f);
        }
        Drawable mutate = DrawableCompat.wrap(imageButton.getDrawable()).mutate();
        DrawableCompat.setTint(mutate, modalDisplayContent.getDismissButtonColor());
        imageButton.setImageDrawable(mutate);
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ModalActivity.this.getDisplayHandler() != null) {
                    ModalActivity.this.getDisplayHandler().finished(ResolutionInfo.dismissed(), ModalActivity.this.getDisplayTime());
                }
                ModalActivity.this.finish();
            }
        });
    }

    public void onButtonClicked(View view, ButtonInfo buttonInfo) {
        if (getDisplayHandler() != null) {
            InAppActionUtils.runActions(buttonInfo);
            getDisplayHandler().finished(ResolutionInfo.buttonPressed(buttonInfo), getDisplayTime());
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
            int r4 = com.urbanairship.automation.R.layout.ua_iam_modal_media_header_body
            return r4
        L_0x003b:
            int r4 = com.urbanairship.automation.R.layout.ua_iam_modal_header_media_body
            return r4
        L_0x003e:
            int r4 = com.urbanairship.automation.R.layout.ua_iam_modal_header_body_media
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.modal.ModalActivity.getTemplate(java.lang.String):int");
    }

    /* access modifiers changed from: protected */
    public String normalizeTemplate(ModalDisplayContent modalDisplayContent) {
        String template = modalDisplayContent.getTemplate();
        if (modalDisplayContent.getMedia() == null) {
            return "header_body_media";
        }
        return (!template.equals("header_media_body") || modalDisplayContent.getHeading() != null || modalDisplayContent.getMedia() == null) ? template : "media_header_body";
    }

    private void normalizeHorizontalPadding(TextView textView) {
        int max = Math.max(ViewCompat.getPaddingEnd(textView), ViewCompat.getPaddingStart(textView));
        textView.setPadding(max, textView.getPaddingTop(), max, textView.getPaddingBottom());
        textView.requestLayout();
    }
}
