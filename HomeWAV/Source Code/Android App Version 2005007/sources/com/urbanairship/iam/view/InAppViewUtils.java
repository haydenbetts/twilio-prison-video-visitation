package com.urbanairship.iam.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.ImageSpan;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import androidx.core.graphics.ColorUtils;
import androidx.core.view.ViewCompat;
import com.urbanairship.Fonts;
import com.urbanairship.iam.ButtonInfo;
import com.urbanairship.iam.MediaInfo;
import com.urbanairship.iam.assets.Assets;
import com.urbanairship.json.JsonMap;
import com.urbanairship.util.UAStringUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

public class InAppViewUtils {
    private static final float DEFAULT_BORDER_RADIUS = 0.0f;
    private static final int DEFAULT_STROKE_WIDTH_DPS = 2;
    private static final float PRESSED_ALPHA_PERCENT = 0.2f;

    public static void applyButtonInfo(Button button, ButtonInfo buttonInfo, int i) {
        applyTextInfo(button, buttonInfo.getLabel());
        int currentTextColor = buttonInfo.getLabel().getColor() == null ? button.getCurrentTextColor() : buttonInfo.getLabel().getColor().intValue();
        int intValue = buttonInfo.getBackgroundColor() == null ? 0 : buttonInfo.getBackgroundColor().intValue();
        ViewCompat.setBackground(button, BackgroundDrawableBuilder.newBuilder(button.getContext()).setBackgroundColor(intValue).setBorderRadius(buttonInfo.getBorderRadius() == null ? 0.0f : buttonInfo.getBorderRadius().floatValue(), i).setPressedColor(ColorUtils.setAlphaComponent(currentTextColor, Math.round(((float) Color.alpha(currentTextColor)) * PRESSED_ALPHA_PERCENT))).setStrokeColor(buttonInfo.getBorderColor() == null ? intValue : buttonInfo.getBorderColor().intValue()).setStrokeWidth(2).build());
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0167, code lost:
        if (r5.equals("center") == false) goto L_0x0149;
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0135 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void applyTextInfo(android.widget.TextView r9, com.urbanairship.iam.TextInfo r10) {
        /*
            java.lang.Float r0 = r10.getFontSize()
            if (r0 == 0) goto L_0x0011
            java.lang.Float r0 = r10.getFontSize()
            float r0 = r0.floatValue()
            r9.setTextSize(r0)
        L_0x0011:
            java.lang.Integer r0 = r10.getColor()
            if (r0 == 0) goto L_0x0022
            java.lang.Integer r0 = r10.getColor()
            int r0 = r0.intValue()
            r9.setTextColor(r0)
        L_0x0022:
            android.content.Context r0 = r9.getContext()
            int r0 = r10.getDrawable(r0)
            r1 = 0
            r2 = 0
            if (r0 == 0) goto L_0x0052
            android.content.Context r3 = r9.getContext()     // Catch:{ NotFoundException -> 0x0037 }
            android.graphics.drawable.Drawable r0 = androidx.core.content.ContextCompat.getDrawable(r3, r0)     // Catch:{ NotFoundException -> 0x0037 }
            goto L_0x0053
        L_0x0037:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Drawable "
            r3.append(r4)
            r3.append(r0)
            java.lang.String r0 = " no longer exists."
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            java.lang.Object[] r3 = new java.lang.Object[r2]
            com.urbanairship.Logger.debug(r0, r3)
        L_0x0052:
            r0 = r1
        L_0x0053:
            r3 = 2
            r4 = 1
            if (r0 == 0) goto L_0x00c9
            float r5 = r9.getTextSize()
            int r5 = java.lang.Math.round(r5)
            int r6 = r9.getCurrentTextColor()
            android.graphics.drawable.Drawable r0 = androidx.core.graphics.drawable.DrawableCompat.wrap(r0)     // Catch:{ NotFoundException -> 0x00b9 }
            android.graphics.drawable.Drawable r0 = r0.mutate()     // Catch:{ NotFoundException -> 0x00b9 }
            r0.setBounds(r2, r2, r5, r5)     // Catch:{ NotFoundException -> 0x00b9 }
            android.graphics.PorterDuffColorFilter r5 = new android.graphics.PorterDuffColorFilter     // Catch:{ NotFoundException -> 0x00b9 }
            android.graphics.PorterDuff$Mode r7 = android.graphics.PorterDuff.Mode.MULTIPLY     // Catch:{ NotFoundException -> 0x00b9 }
            r5.<init>(r6, r7)     // Catch:{ NotFoundException -> 0x00b9 }
            r0.setColorFilter(r5)     // Catch:{ NotFoundException -> 0x00b9 }
            com.urbanairship.iam.view.InAppViewUtils$CenteredImageSpan r5 = new com.urbanairship.iam.view.InAppViewUtils$CenteredImageSpan     // Catch:{ NotFoundException -> 0x00b9 }
            r5.<init>(r0)     // Catch:{ NotFoundException -> 0x00b9 }
            java.lang.String r0 = r10.getText()     // Catch:{ NotFoundException -> 0x00b9 }
            r6 = 33
            if (r0 != 0) goto L_0x0090
            android.text.SpannableString r0 = new android.text.SpannableString     // Catch:{ NotFoundException -> 0x00b9 }
            java.lang.String r1 = " "
            r0.<init>(r1)     // Catch:{ NotFoundException -> 0x00b9 }
            r0.setSpan(r5, r2, r4, r6)     // Catch:{ NotFoundException -> 0x00b9 }
            goto L_0x00b5
        L_0x0090:
            android.text.SpannableString r0 = new android.text.SpannableString     // Catch:{ NotFoundException -> 0x00b9 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ NotFoundException -> 0x00b9 }
            r7.<init>()     // Catch:{ NotFoundException -> 0x00b9 }
            java.lang.String r8 = "  "
            r7.append(r8)     // Catch:{ NotFoundException -> 0x00b9 }
            java.lang.String r8 = r10.getText()     // Catch:{ NotFoundException -> 0x00b9 }
            r7.append(r8)     // Catch:{ NotFoundException -> 0x00b9 }
            java.lang.String r7 = r7.toString()     // Catch:{ NotFoundException -> 0x00b9 }
            r0.<init>(r7)     // Catch:{ NotFoundException -> 0x00b9 }
            r0.setSpan(r5, r2, r4, r6)     // Catch:{ NotFoundException -> 0x00b9 }
            com.urbanairship.iam.view.InAppViewUtils$RemoveUnderlineSpan r5 = new com.urbanairship.iam.view.InAppViewUtils$RemoveUnderlineSpan     // Catch:{ NotFoundException -> 0x00b9 }
            r5.<init>()     // Catch:{ NotFoundException -> 0x00b9 }
            r0.setSpan(r5, r4, r3, r6)     // Catch:{ NotFoundException -> 0x00b9 }
        L_0x00b5:
            r9.setText(r0)     // Catch:{ NotFoundException -> 0x00b9 }
            goto L_0x00d0
        L_0x00b9:
            r0 = move-exception
            java.lang.Object[] r1 = new java.lang.Object[r2]
            java.lang.String r5 = "Unable to find button drawable."
            com.urbanairship.Logger.error(r0, r5, r1)
            java.lang.String r0 = r10.getText()
            r9.setText(r0)
            goto L_0x00d0
        L_0x00c9:
            java.lang.String r0 = r10.getText()
            r9.setText(r0)
        L_0x00d0:
            android.graphics.Typeface r0 = r9.getTypeface()
            if (r0 != 0) goto L_0x00d8
            r0 = 0
            goto L_0x00e0
        L_0x00d8:
            android.graphics.Typeface r0 = r9.getTypeface()
            int r0 = r0.getStyle()
        L_0x00e0:
            int r1 = r9.getPaintFlags()
            r1 = r1 | r4
            r1 = r1 | 128(0x80, float:1.794E-43)
            java.util.List r5 = r10.getStyles()
            java.util.Iterator r5 = r5.iterator()
        L_0x00ef:
            boolean r6 = r5.hasNext()
            r7 = -1
            if (r6 == 0) goto L_0x0135
            java.lang.Object r6 = r5.next()
            java.lang.String r6 = (java.lang.String) r6
            r6.hashCode()
            int r8 = r6.hashCode()
            switch(r8) {
                case -1178781136: goto L_0x011e;
                case -1026963764: goto L_0x0112;
                case 3029637: goto L_0x0107;
                default: goto L_0x0106;
            }
        L_0x0106:
            goto L_0x0128
        L_0x0107:
            java.lang.String r8 = "bold"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0110
            goto L_0x0128
        L_0x0110:
            r7 = 2
            goto L_0x0128
        L_0x0112:
            java.lang.String r8 = "underline"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x011c
            goto L_0x0128
        L_0x011c:
            r7 = 1
            goto L_0x0128
        L_0x011e:
            java.lang.String r8 = "italic"
            boolean r6 = r6.equals(r8)
            if (r6 != 0) goto L_0x0127
            goto L_0x0128
        L_0x0127:
            r7 = 0
        L_0x0128:
            switch(r7) {
                case 0: goto L_0x0132;
                case 1: goto L_0x012f;
                case 2: goto L_0x012c;
                default: goto L_0x012b;
            }
        L_0x012b:
            goto L_0x00ef
        L_0x012c:
            r0 = r0 | 1
            goto L_0x00ef
        L_0x012f:
            r1 = r1 | 8
            goto L_0x00ef
        L_0x0132:
            r0 = r0 | 2
            goto L_0x00ef
        L_0x0135:
            java.lang.String r5 = r10.getAlignment()
            if (r5 == 0) goto L_0x017f
            java.lang.String r5 = r10.getAlignment()
            r5.hashCode()
            int r6 = r5.hashCode()
            switch(r6) {
                case -1364013995: goto L_0x0161;
                case 3317767: goto L_0x0156;
                case 108511772: goto L_0x014b;
                default: goto L_0x0149;
            }
        L_0x0149:
            r2 = -1
            goto L_0x016a
        L_0x014b:
            java.lang.String r2 = "right"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x0154
            goto L_0x0149
        L_0x0154:
            r2 = 2
            goto L_0x016a
        L_0x0156:
            java.lang.String r2 = "left"
            boolean r2 = r5.equals(r2)
            if (r2 != 0) goto L_0x015f
            goto L_0x0149
        L_0x015f:
            r2 = 1
            goto L_0x016a
        L_0x0161:
            java.lang.String r3 = "center"
            boolean r3 = r5.equals(r3)
            if (r3 != 0) goto L_0x016a
            goto L_0x0149
        L_0x016a:
            switch(r2) {
                case 0: goto L_0x017c;
                case 1: goto L_0x0175;
                case 2: goto L_0x016e;
                default: goto L_0x016d;
            }
        L_0x016d:
            goto L_0x017f
        L_0x016e:
            r2 = 8388613(0x800005, float:1.175495E-38)
            r9.setGravity(r2)
            goto L_0x017f
        L_0x0175:
            r2 = 8388611(0x800003, float:1.1754948E-38)
            r9.setGravity(r2)
            goto L_0x017f
        L_0x017c:
            r9.setGravity(r4)
        L_0x017f:
            android.content.Context r2 = r9.getContext()
            java.util.List r10 = r10.getFontFamilies()
            android.graphics.Typeface r10 = getTypeFace(r2, r10)
            if (r10 != 0) goto L_0x0191
            android.graphics.Typeface r10 = r9.getTypeface()
        L_0x0191:
            r9.setTypeface(r10, r0)
            r9.setPaintFlags(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.iam.view.InAppViewUtils.applyTextInfo(android.widget.TextView, com.urbanairship.iam.TextInfo):void");
    }

    private static class RemoveUnderlineSpan extends CharacterStyle {
        private RemoveUnderlineSpan() {
        }

        public void updateDrawState(TextPaint textPaint) {
            textPaint.setUnderlineText(false);
        }
    }

    private static class CenteredImageSpan extends ImageSpan {
        public CenteredImageSpan(Drawable drawable) {
            super(drawable);
        }

        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            canvas.save();
            Drawable drawable = getDrawable();
            canvas.translate(f, (float) ((i5 - drawable.getBounds().bottom) - (paint.getFontMetricsInt().descent / 2)));
            drawable.draw(canvas);
            canvas.restore();
        }
    }

    private static Typeface getTypeFace(Context context, List<String> list) {
        Typeface fontFamily;
        for (String next : list) {
            if (!UAStringUtil.isEmpty(next) && (fontFamily = Fonts.shared(context).getFontFamily(next)) != null) {
                return fontFamily;
            }
        }
        return null;
    }

    public static void loadMediaInfo(MediaView mediaView, final MediaInfo mediaInfo, final Assets assets) {
        if (mediaView.getWidth() == 0) {
            final WeakReference weakReference = new WeakReference(mediaView);
            mediaView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    MediaView mediaView = (MediaView) weakReference.get();
                    if (mediaView == null) {
                        return false;
                    }
                    InAppViewUtils.loadMediaInfo(mediaView, mediaInfo, assets);
                    mediaView.getViewTreeObserver().removeOnPreDrawListener(this);
                    return false;
                }
            });
            return;
        }
        int i = 16;
        int i2 = 9;
        String str = null;
        if (assets != null) {
            File file = assets.file(mediaInfo.getUrl());
            if (file.exists()) {
                JsonMap optMap = assets.getMetadata(mediaInfo.getUrl()).optMap();
                i = optMap.opt("width").getInt(16);
                i2 = optMap.opt("height").getInt(9);
                str = Uri.fromFile(file).toString();
            }
        }
        ViewGroup.LayoutParams layoutParams = mediaView.getLayoutParams();
        if (layoutParams.height == -2) {
            layoutParams.height = Math.round((((float) mediaView.getWidth()) / ((float) i)) * ((float) i2));
        } else {
            float f = ((float) i) / ((float) i2);
            if (f >= ((float) mediaView.getWidth()) / ((float) mediaView.getHeight())) {
                layoutParams.height = Math.round(((float) mediaView.getWidth()) / f);
            } else {
                layoutParams.width = Math.round(((float) mediaView.getHeight()) * f);
            }
        }
        mediaView.setLayoutParams(layoutParams);
        mediaView.setMediaInfo(mediaInfo, str);
    }

    public static float getLargestChildZValue(ViewGroup viewGroup) {
        float f = 0.0f;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            f = Math.max(viewGroup.getChildAt(0).getZ(), f);
        }
        return f;
    }
}
