package com.forasoft.homewavvisitor.ui.views;

import air.HomeWAV.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.Sdk27PropertiesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020\tH\u0002J\u0006\u0010/\u001a\u00020 J\u0012\u00100\u001a\u00020 2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002R$\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@FX\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\u0014@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001a\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR4\u0010!\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f2\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020 \u0018\u00010\u001f@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R$\u0010&\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@FX\u000e¢\u0006\f\u001a\u0004\b'\u0010\u000f\"\u0004\b(\u0010\u0011R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u00061"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/TernaryView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "value", "", "caption", "getCaption", "()Ljava/lang/String;", "setCaption", "(Ljava/lang/String;)V", "grayColor", "greenColor", "", "highlighted", "getHighlighted", "()Z", "setHighlighted", "(Z)V", "imageId", "getImageId", "()I", "setImageId", "(I)V", "Lkotlin/Function0;", "", "onClickListener", "getOnClickListener", "()Lkotlin/jvm/functions/Function0;", "setOnClickListener", "(Lkotlin/jvm/functions/Function0;)V", "text", "getText", "setText", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "chaneFieldsColor", "color", "hideBorders", "manageAttributes", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TernaryView.kt */
public final class TernaryView extends FrameLayout {
    private HashMap _$_findViewCache;
    private final int grayColor;
    private final int greenColor;
    private boolean highlighted;
    private int imageId;
    private Function0<Unit> onClickListener;
    private final View view;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view2 = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view2 != null) {
            return view2;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TernaryView(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Object systemService = context2.getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.view_ternary, this, true);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…view_ternary, this, true)");
            this.view = inflate;
            Context context3 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            this.greenColor = context3.getResources().getColor(R.color.highlightGreenLight);
            Context context4 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context4, "context");
            this.grayColor = context4.getResources().getColor(R.color.offlineGray);
            this.imageId = R.drawable.ic_money;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TernaryView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Object systemService = context2.getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.view_ternary, this, true);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…view_ternary, this, true)");
            this.view = inflate;
            Context context3 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            this.greenColor = context3.getResources().getColor(R.color.highlightGreenLight);
            Context context4 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context4, "context");
            this.grayColor = context4.getResources().getColor(R.color.offlineGray);
            this.imageId = R.drawable.ic_money;
            manageAttributes(attributeSet);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TernaryView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Context context2 = getContext();
        Intrinsics.checkExpressionValueIsNotNull(context2, "context");
        Object systemService = context2.getSystemService("layout_inflater");
        if (systemService != null) {
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.view_ternary, this, true);
            Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…view_ternary, this, true)");
            this.view = inflate;
            Context context3 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context3, "context");
            this.greenColor = context3.getResources().getColor(R.color.highlightGreenLight);
            Context context4 = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context4, "context");
            this.grayColor = context4.getResources().getColor(R.color.offlineGray);
            this.imageId = R.drawable.ic_money;
            manageAttributes(attributeSet);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    private final void manageAttributes(AttributeSet attributeSet) {
        if (attributeSet != null) {
            Context context = getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "context");
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, com.forasoft.homewavvisitor.R.styleable.TernaryView, 0, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(2);
            String string = obtainStyledAttributes.getString(3);
            boolean z = obtainStyledAttributes.getBoolean(1, true);
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.image)).setImageDrawable(drawable);
            if (string == null) {
                string = "";
            }
            setText(string);
            if (!z) {
                ((ConstraintLayout) this.view.findViewById(com.forasoft.homewavvisitor.R.id.root)).setBackgroundResource(R.color.transparent);
            }
        }
    }

    public final View getView() {
        return this.view;
    }

    public final void hideBorders() {
        this.view.setBackground(getResources().getDrawable(R.color.white));
    }

    public final int getImageId() {
        return this.imageId;
    }

    public final void setImageId(int i) {
        ((ImageView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.image)).setImageResource(i);
        this.imageId = i;
    }

    public final String getText() {
        TextView textView = (TextView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.text);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.text");
        return textView.getText().toString();
    }

    public final void setText(String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.VALUE);
        TextView textView = (TextView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.text);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.text");
        textView.setText(str);
    }

    public final String getCaption() {
        TextView textView = (TextView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.caption);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.caption");
        return textView.getText().toString();
    }

    public final void setCaption(String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.VALUE);
        TextView textView = (TextView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.caption);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.caption");
        textView.setText(str);
    }

    public final Function0<Unit> getOnClickListener() {
        return this.onClickListener;
    }

    public final void setOnClickListener(Function0<Unit> function0) {
        ConstraintLayout constraintLayout = (ConstraintLayout) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.root);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "root");
        constraintLayout.setOnClickListener(new TernaryView$inlined$sam$i$android_view_View_OnClickListener$0(new TernaryView$onClickListener$1(function0)));
        this.onClickListener = function0;
    }

    public final boolean getHighlighted() {
        return this.highlighted;
    }

    public final void setHighlighted(boolean z) {
        if (z) {
            chaneFieldsColor(this.greenColor);
        } else {
            chaneFieldsColor(this.grayColor);
        }
        this.highlighted = z;
    }

    private final void chaneFieldsColor(int i) {
        ((ImageView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.image)).setColorFilter(i);
        TextView textView = (TextView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.caption);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.caption");
        Sdk27PropertiesKt.setTextColor(textView, i);
        TextView textView2 = (TextView) this.view.findViewById(com.forasoft.homewavvisitor.R.id.text);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "view.text");
        Sdk27PropertiesKt.setTextColor(textView2, i);
    }
}
