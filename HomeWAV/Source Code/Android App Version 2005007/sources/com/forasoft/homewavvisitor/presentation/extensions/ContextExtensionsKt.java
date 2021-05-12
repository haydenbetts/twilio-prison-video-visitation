package com.forasoft.homewavvisitor.presentation.extensions;

import air.HomeWAV.R;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.amulyakhare.textdrawable.TextDrawable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a0\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006\u001a0\u0010\t\u001a\u00020\n*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\u0006\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f\u001a\u0012\u0010\u0010\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0006\u001a\u0012\u0010\u0012\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u0006\u001a\n\u0010\u0015\u001a\u00020\f*\u00020\u0016\u001a\u001c\u0010\u0017\u001a\u00020\r*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00062\b\b\u0002\u0010\u001a\u001a\u00020\u000f\u001a\u0012\u0010\u001b\u001a\u00020\f*\u00020\r2\u0006\u0010\u001c\u001a\u00020\u0006\u001a\n\u0010\u001d\u001a\u00020\f*\u00020\u0016\u001a\f\u0010\u001e\u001a\u00020\u0001*\u00020\u0013H\u0002¨\u0006\u001f"}, d2 = {"createTextBitmap", "Landroid/graphics/Bitmap;", "Landroid/content/Context;", "text", "", "fontSize", "", "textColor", "backgroundColor", "createTextDrawable", "Lcom/amulyakhare/textdrawable/TextDrawable;", "disableTouchEvents", "", "Landroid/view/View;", "isDisabled", "", "getColorResource", "color", "getDrawableResource", "Landroid/graphics/drawable/Drawable;", "drawable", "hideKeyboard", "Landroidx/fragment/app/Fragment;", "inflate", "Landroid/view/ViewGroup;", "resource", "attachToRoot", "setMarginBottom", "bottom", "showKeyboard", "toBitmap", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: ContextExtensions.kt */
public final class ContextExtensionsKt {
    public static /* synthetic */ View inflate$default(ViewGroup viewGroup, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        return inflate(viewGroup, i, z);
    }

    public static final View inflate(ViewGroup viewGroup, int i, boolean z) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "$this$inflate");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, z);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "LayoutInflater.from(cont…urce, this, attachToRoot)");
        return inflate;
    }

    public static /* synthetic */ TextDrawable createTextDrawable$default(Context context, String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = R.dimen.initials_size_medium;
        }
        if ((i4 & 4) != 0) {
            i2 = R.color.white;
        }
        if ((i4 & 8) != 0) {
            i3 = R.color.offlineGray;
        }
        return createTextDrawable(context, str, i, i2, i3);
    }

    public static final TextDrawable createTextDrawable(Context context, String str, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(context, "$this$createTextDrawable");
        Intrinsics.checkParameterIsNotNull(str, "text");
        TextDrawable buildRound = TextDrawable.builder().beginConfig().useFont(Typeface.DEFAULT).textColor(getColorResource(context, i2)).bold().endConfig().buildRound(str, getColorResource(context, i3));
        Intrinsics.checkExpressionValueIsNotNull(buildRound, "TextDrawable.builder()\n …esource(backgroundColor))");
        return buildRound;
    }

    public static /* synthetic */ Bitmap createTextBitmap$default(Context context, String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 2) != 0) {
            i = R.dimen.initials_size_medium;
        }
        if ((i4 & 4) != 0) {
            i2 = R.color.white;
        }
        if ((i4 & 8) != 0) {
            i3 = R.color.offlineGray;
        }
        return createTextBitmap(context, str, i, i2, i3);
    }

    public static final Bitmap createTextBitmap(Context context, String str, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(context, "$this$createTextBitmap");
        Intrinsics.checkParameterIsNotNull(str, "text");
        return toBitmap(createTextDrawable(context, str, i, i2, i3));
    }

    private static final Bitmap toBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "drawable.bitmap");
            return bitmap;
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int i = 96;
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 96;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Bitmap.Config.ARGB_8888);
        Intrinsics.checkExpressionValueIsNotNull(createBitmap, "Bitmap.createBitmap(widt… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static final int getColorResource(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "$this$getColorResource");
        return ContextCompat.getColor(context, i);
    }

    public static final Drawable getDrawableResource(Context context, int i) {
        Intrinsics.checkParameterIsNotNull(context, "$this$getDrawableResource");
        Drawable drawable = ContextCompat.getDrawable(context, i);
        if (drawable == null) {
            Intrinsics.throwNpe();
        }
        return drawable;
    }

    public static final void hideKeyboard(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$hideKeyboard");
        Context context = fragment.getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Object systemService = context.getSystemService("input_method");
        if (systemService != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            View view = fragment.getView();
            if (view == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(view, "view!!");
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
    }

    public static final void showKeyboard(Fragment fragment) {
        Intrinsics.checkParameterIsNotNull(fragment, "$this$showKeyboard");
        Context context = fragment.getContext();
        if (context == null) {
            Intrinsics.throwNpe();
        }
        Object systemService = context.getSystemService("input_method");
        if (systemService != null) {
            ((InputMethodManager) systemService).toggleSoftInput(2, 0);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
    }

    public static final void disableTouchEvents(View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "$this$disableTouchEvents");
        view.setEnabled(!z);
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = viewGroup.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(idx)");
                disableTouchEvents(childAt, !z);
            }
        }
    }

    public static final void setMarginBottom(View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "$this$setMarginBottom");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
            Resources resources = view.getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            marginLayoutParams.bottomMargin = (int) (((float) i) * resources.getDisplayMetrics().density);
            view.setLayoutParams(marginLayoutParams);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
    }
}
