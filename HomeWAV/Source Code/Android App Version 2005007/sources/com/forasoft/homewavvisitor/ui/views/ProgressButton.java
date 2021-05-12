package com.forasoft.homewavvisitor.ui.views;

import air.HomeWAV.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016R$\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\r@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/ProgressButton;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "value", "", "isProgressVisible", "()Z", "setProgressVisible", "(Z)V", "", "text", "getText", "()Ljava/lang/CharSequence;", "setText", "(Ljava/lang/CharSequence;)V", "setOnClickListener", "", "l", "Landroid/view/View$OnClickListener;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ProgressButton.kt */
public final class ProgressButton extends FrameLayout {
    private HashMap _$_findViewCache;
    private boolean isProgressVisible;
    private CharSequence text = "";

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
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ProgressButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        LayoutInflater.from(context).inflate(R.layout.view_progress_button, this);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.forasoft.homewavvisitor.R.styleable.ProgressButton);
        setText(String.valueOf(obtainStyledAttributes.getString(0)));
        setProgressVisible(obtainStyledAttributes.getBoolean(1, false));
        obtainStyledAttributes.recycle();
    }

    public final CharSequence getText() {
        return this.text;
    }

    public final void setText(CharSequence charSequence) {
        Intrinsics.checkParameterIsNotNull(charSequence, CommonProperties.VALUE);
        this.text = charSequence;
        Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_action);
        Intrinsics.checkExpressionValueIsNotNull(button, "btn_action");
        button.setText(charSequence);
    }

    public final boolean isProgressVisible() {
        return this.isProgressVisible;
    }

    public final void setProgressVisible(boolean z) {
        this.isProgressVisible = z;
        if (z) {
            Button button = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_action);
            Intrinsics.checkExpressionValueIsNotNull(button, "btn_action");
            button.setText("");
            Button button2 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_action);
            Intrinsics.checkExpressionValueIsNotNull(button2, "btn_action");
            button2.setEnabled(false);
            CommonKt.show((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
            return;
        }
        Button button3 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_action);
        Intrinsics.checkExpressionValueIsNotNull(button3, "btn_action");
        button3.setText(this.text);
        Button button4 = (Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_action);
        Intrinsics.checkExpressionValueIsNotNull(button4, "btn_action");
        button4.setEnabled(true);
        CommonKt.hide((ProgressBar) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.progressBar));
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        ((Button) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.btn_action)).setOnClickListener(onClickListener);
    }
}
