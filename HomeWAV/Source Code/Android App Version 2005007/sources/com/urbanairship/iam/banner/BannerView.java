package com.urbanairship.iam.banner;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.urbanairship.automation.R;
import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.assets.Assets;
import com.urbanairship.iam.banner.BannerDismissLayout;
import com.urbanairship.iam.view.BackgroundDrawableBuilder;
import com.urbanairship.iam.view.BorderRadius;
import com.urbanairship.iam.view.InAppButtonLayout;
import com.urbanairship.iam.view.InAppViewUtils;
import com.urbanairship.iam.view.MediaView;

public class BannerView extends FrameLayout implements InAppButtonLayout.ButtonClickListener, View.OnClickListener, BannerDismissLayout.Listener {
    private static final float PRESSED_ALPHA_PERCENT = 0.2f;
    private int animationIn;
    private int animationOut;
    private boolean applyLegacyWindowInsetFix = false;
    private final Assets assets;
    private final BannerDisplayContent displayContent;
    private boolean isDismissed = false;
    private boolean isResumed = false;
    /* access modifiers changed from: private */
    public Listener listener;
    private View subView;
    private final Timer timer;

    public interface Listener {
        void onBannerClicked(BannerView bannerView);

        void onButtonClicked(BannerView bannerView, ButtonInfo buttonInfo);

        void onTimedOut(BannerView bannerView);

        void onUserDismissed(BannerView bannerView);
    }

    public BannerView(Context context, BannerDisplayContent bannerDisplayContent, Assets assets2) {
        super(context);
        this.displayContent = bannerDisplayContent;
        this.assets = assets2;
        this.timer = new Timer(bannerDisplayContent.getDuration()) {
            /* access modifiers changed from: protected */
            public void onFinish() {
                BannerView.this.dismiss(true);
                Listener access$000 = BannerView.this.listener;
                if (access$000 != null) {
                    access$000.onTimedOut(BannerView.this);
                }
            }
        };
        ViewCompat.setOnApplyWindowInsetsListener(this, new OnApplyWindowInsetsListener() {
            public WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                for (int i = 0; i < BannerView.this.getChildCount(); i++) {
                    ViewCompat.dispatchApplyWindowInsets(BannerView.this.getChildAt(i), new WindowInsetsCompat(windowInsetsCompat));
                }
                return windowInsetsCompat;
            }
        });
    }

    public void setListener(Listener listener2) {
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        ViewCompat.requestApplyInsets(this);
    }

    /* access modifiers changed from: protected */
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        BannerDismissLayout bannerDismissLayout = (BannerDismissLayout) layoutInflater.inflate(getLayout(), viewGroup, false);
        bannerDismissLayout.setPlacement(this.displayContent.getPlacement());
        bannerDismissLayout.setListener(this);
        ViewStub viewStub = (ViewStub) bannerDismissLayout.findViewById(R.id.banner_content);
        viewStub.setLayoutResource(getContentLayout());
        viewStub.inflate();
        LinearLayout linearLayout = (LinearLayout) bannerDismissLayout.findViewById(R.id.banner);
        ViewCompat.setBackground(linearLayout, createBannerBackground());
        if (this.displayContent.getBorderRadius() > 0.0f) {
            BorderRadius.applyBorderRadiusPadding(linearLayout, this.displayContent.getBorderRadius(), BannerDisplayContent.PLACEMENT_TOP.equals(this.displayContent.getPlacement()) ? 12 : 3);
        }
        if (!this.displayContent.getActions().isEmpty()) {
            linearLayout.setClickable(true);
            linearLayout.setOnClickListener(this);
        }
        TextView textView = (TextView) bannerDismissLayout.findViewById(R.id.heading);
        if (this.displayContent.getHeading() != null) {
            InAppViewUtils.applyTextInfo(textView, this.displayContent.getHeading());
        } else {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) bannerDismissLayout.findViewById(R.id.body);
        if (this.displayContent.getBody() != null) {
            InAppViewUtils.applyTextInfo(textView2, this.displayContent.getBody());
        } else {
            textView2.setVisibility(8);
        }
        MediaView mediaView = (MediaView) bannerDismissLayout.findViewById(R.id.media);
        if (this.displayContent.getMedia() != null) {
            InAppViewUtils.loadMediaInfo(mediaView, this.displayContent.getMedia(), this.assets);
        } else {
            mediaView.setVisibility(8);
        }
        InAppButtonLayout inAppButtonLayout = (InAppButtonLayout) bannerDismissLayout.findViewById(R.id.buttons);
        if (this.displayContent.getButtons().isEmpty()) {
            inAppButtonLayout.setVisibility(8);
        } else {
            inAppButtonLayout.setButtons(this.displayContent.getButtonLayout(), this.displayContent.getButtons());
            inAppButtonLayout.setButtonClickListener(this);
        }
        View findViewById = bannerDismissLayout.findViewById(R.id.banner_pull);
        Drawable mutate = DrawableCompat.wrap(findViewById.getBackground()).mutate();
        DrawableCompat.setTint(mutate, this.displayContent.getDismissButtonColor());
        ViewCompat.setBackground(findViewById, mutate);
        return bannerDismissLayout;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.isResumed = true;
        if (!this.isDismissed) {
            getTimer().start();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.isResumed = false;
        getTimer().stop();
    }

    /* access modifiers changed from: protected */
    public void dismiss(boolean z) {
        this.isDismissed = true;
        getTimer().stop();
        if (!z || this.subView == null || this.animationOut == 0) {
            removeSelf();
            return;
        }
        clearAnimation();
        Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), this.animationOut);
        loadAnimator.setTarget(this.subView);
        loadAnimator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                BannerView.this.removeSelf();
            }
        });
        loadAnimator.start();
    }

    /* access modifiers changed from: private */
    public void removeSelf() {
        if (getParent() instanceof ViewGroup) {
            ((ViewGroup) getParent()).removeView(this);
            this.subView = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        if (i == 0 && !this.isDismissed && this.subView == null) {
            View onCreateView = onCreateView(LayoutInflater.from(getContext()), this);
            this.subView = onCreateView;
            if (this.applyLegacyWindowInsetFix) {
                applyLegacyWindowInsetFix(onCreateView);
            }
            addView(this.subView);
            if (this.animationIn != 0) {
                Animator loadAnimator = AnimatorInflater.loadAnimator(getContext(), this.animationIn);
                loadAnimator.setTarget(this.subView);
                loadAnimator.start();
            }
            onResume();
        }
    }

    public void setAnimations(int i, int i2) {
        this.animationIn = i;
        this.animationOut = i2;
    }

    public void onButtonClicked(View view, ButtonInfo buttonInfo) {
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onButtonClicked(this, buttonInfo);
        }
        dismiss(true);
    }

    public void onDismissed(View view) {
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onUserDismissed(this);
        }
        dismiss(false);
    }

    public void onDragStateChanged(View view, int i) {
        if (i != 0) {
            if (i == 1) {
                getTimer().stop();
            }
        } else if (this.isResumed) {
            getTimer().start();
        }
    }

    public void onClick(View view) {
        Listener listener2 = this.listener;
        if (listener2 != null) {
            listener2.onBannerClicked(this);
        }
        dismiss(true);
    }

    /* access modifiers changed from: protected */
    public Timer getTimer() {
        return this.timer;
    }

    /* access modifiers changed from: protected */
    public BannerDisplayContent getDisplayContent() {
        return this.displayContent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getLayout() {
        /*
            r3 = this;
            com.urbanairship.iam.banner.BannerDisplayContent r0 = r3.displayContent
            java.lang.String r0 = r0.getPlacement()
            int r1 = r0.hashCode()
            r2 = -1383228885(0xffffffffad8d9a2b, float:-1.6098308E-11)
            if (r1 == r2) goto L_0x001f
            r2 = 115029(0x1c155, float:1.6119E-40)
            if (r1 == r2) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r1 = "top"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = 0
            goto L_0x002a
        L_0x001f:
            java.lang.String r1 = "bottom"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = 1
            goto L_0x002a
        L_0x0029:
            r0 = -1
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            int r0 = com.urbanairship.automation.R.layout.ua_iam_banner_bottom
            return r0
        L_0x002f:
            int r0 = com.urbanairship.automation.R.layout.ua_iam_banner_top
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.banner.BannerView.getLayout():int");
    }

    private Drawable createBannerBackground() {
        return BackgroundDrawableBuilder.newBuilder(getContext()).setBackgroundColor(this.displayContent.getBackgroundColor()).setPressedColor(ColorUtils.setAlphaComponent(this.displayContent.getDismissButtonColor(), Math.round(((float) Color.alpha(this.displayContent.getDismissButtonColor())) * PRESSED_ALPHA_PERCENT))).setBorderRadius(this.displayContent.getBorderRadius(), BannerDisplayContent.PLACEMENT_TOP.equals(this.displayContent.getPlacement()) ? 12 : 3).build();
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int getContentLayout() {
        /*
            r3 = this;
            com.urbanairship.iam.banner.BannerDisplayContent r0 = r3.displayContent
            java.lang.String r0 = r0.getTemplate()
            int r1 = r0.hashCode()
            r2 = 4266497(0x411a01, float:5.978636E-39)
            if (r1 == r2) goto L_0x001f
            r2 = 1939617666(0x739c3782, float:2.4753544E31)
            if (r1 == r2) goto L_0x0015
            goto L_0x0029
        L_0x0015:
            java.lang.String r1 = "media_left"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = 1
            goto L_0x002a
        L_0x001f:
            java.lang.String r1 = "media_right"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0029
            r0 = 0
            goto L_0x002a
        L_0x0029:
            r0 = -1
        L_0x002a:
            if (r0 == 0) goto L_0x002f
            int r0 = com.urbanairship.automation.R.layout.ua_iam_banner_content_left_media
            return r0
        L_0x002f:
            int r0 = com.urbanairship.automation.R.layout.ua_iam_banner_content_right_media
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.banner.BannerView.getContentLayout():int");
    }

    public void applyLegacyWindowInsetFix() {
        this.applyLegacyWindowInsetFix = true;
        View view = this.subView;
        if (view != null) {
            applyLegacyWindowInsetFix(view);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0026, code lost:
        r2 = getResources().getIdentifier("status_bar_height", "dimen", com.urbanairship.channel.ChannelRegistrationPayload.ANDROID_DEVICE_TYPE);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyLegacyWindowInsetFix(android.view.View r6) {
        /*
            r5 = this;
            android.view.View r6 = r5.subView
            r0 = 0
            r6.setFitsSystemWindows(r0)
            android.content.Context r6 = r5.getContext()
            r1 = 2
            int[] r1 = new int[r1]
            r1 = {16843760, 16843759} // fill-array
            android.content.res.TypedArray r6 = r6.obtainStyledAttributes(r1)
            boolean r1 = r6.getBoolean(r0, r0)
            r2 = 1
            boolean r2 = r6.getBoolean(r2, r0)
            r6.recycle()
            java.lang.String r6 = "android"
            java.lang.String r3 = "dimen"
            if (r2 == 0) goto L_0x003b
            android.content.res.Resources r2 = r5.getResources()
            java.lang.String r4 = "status_bar_height"
            int r2 = r2.getIdentifier(r4, r3, r6)
            if (r2 <= 0) goto L_0x003b
            android.content.res.Resources r4 = r5.getResources()
            int r2 = r4.getDimensionPixelSize(r2)
            goto L_0x003c
        L_0x003b:
            r2 = 0
        L_0x003c:
            if (r1 == 0) goto L_0x0053
            android.content.res.Resources r1 = r5.getResources()
            java.lang.String r4 = "navigation_bar_height"
            int r6 = r1.getIdentifier(r4, r3, r6)
            if (r6 <= 0) goto L_0x0053
            android.content.res.Resources r1 = r5.getResources()
            int r6 = r1.getDimensionPixelSize(r6)
            goto L_0x0054
        L_0x0053:
            r6 = 0
        L_0x0054:
            android.view.View r1 = r5.subView
            androidx.core.view.ViewCompat.setPaddingRelative(r1, r0, r2, r0, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.banner.BannerView.applyLegacyWindowInsetFix(android.view.View):void");
    }
}
