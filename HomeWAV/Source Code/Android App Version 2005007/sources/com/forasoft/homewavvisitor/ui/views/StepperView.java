package com.forasoft.homewavvisitor.ui.views;

import air.HomeWAV.R;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u000f\u0018\u0000 !2\u00020\u0001:\u0001!B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007B!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0010\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\tH\u0002J\u0010\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\tH\u0002J\u0010\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\tH\u0002J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\tH\u0002J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\u000e\u0010 \u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX.¢\u0006\u0002\n\u0000R(\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/StepperView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "backgroundActive", "Landroid/graphics/drawable/Drawable;", "backgroundInactive", "completedViews", "", "Landroid/view/View;", "onStepperClickListener", "Lkotlin/Function1;", "", "getOnStepperClickListener", "()Lkotlin/jvm/functions/Function1;", "setOnStepperClickListener", "(Lkotlin/jvm/functions/Function1;)V", "steps", "init", "onStepClicked", "step", "setActive", "setCompleted", "setInactive", "setOnStepClickListeners", "setStep", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: StepperView.kt */
public final class StepperView extends FrameLayout {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int STEPS_COUNT = 4;
    private HashMap _$_findViewCache;
    private Drawable backgroundActive;
    private Drawable backgroundInactive;
    private List<? extends View> completedViews;
    private Function1<? super Integer, Unit> onStepperClickListener;
    private List<? extends View> steps;

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

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/views/StepperView$Companion;", "", "()V", "STEPS_COUNT", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: StepperView.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StepperView(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StepperView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StepperView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
    }

    public final Function1<Integer, Unit> getOnStepperClickListener() {
        return this.onStepperClickListener;
    }

    public final void setOnStepperClickListener(Function1<? super Integer, Unit> function1) {
        this.onStepperClickListener = function1;
    }

    private final void init(Context context) {
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService != null) {
            ((LayoutInflater) systemService).inflate(R.layout.stepper_view, this, true);
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.stepper_step_background_active);
            if (drawable == null) {
                Intrinsics.throwNpe();
            }
            this.backgroundActive = drawable;
            Drawable drawable2 = ContextCompat.getDrawable(context, R.drawable.stepper_step_background_inactive);
            if (drawable2 == null) {
                Intrinsics.throwNpe();
            }
            this.backgroundInactive = drawable2;
            this.steps = CollectionsKt.arrayListOf((TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_1), (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_2), (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_3), (TextView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_4));
            this.completedViews = CollectionsKt.arrayListOf((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_1_completed), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_2_completed), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_3_completed), (ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_4_completed));
            setOnStepClickListeners();
            setStep(1);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    private final void setOnStepClickListeners() {
        for (int i = 1; i <= 4; i++) {
            List<? extends View> list = this.steps;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("steps");
            }
            ((View) list.get(i - 1)).setOnClickListener(new StepperView$inlined$sam$i$android_view_View_OnClickListener$0(new StepperView$setOnStepClickListeners$1(this, i)));
        }
    }

    /* access modifiers changed from: private */
    public final void onStepClicked(int i) {
        Function1<? super Integer, Unit> function1 = this.onStepperClickListener;
        if (function1 != null) {
            Unit invoke = function1.invoke(Integer.valueOf(i));
        }
    }

    public final void setStep(int i) {
        for (int i2 = 1; i2 < i; i2++) {
            setCompleted(i2);
        }
        setActive(i);
        for (int i3 = i + 1; i3 <= 4; i3++) {
            setInactive(i3);
        }
    }

    private final void setActive(int i) {
        int i2 = i - 1;
        List<? extends View> list = this.steps;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("steps");
        }
        View view = (View) list.get(i2);
        Drawable drawable = this.backgroundActive;
        if (drawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundActive");
        }
        view.setBackground(drawable);
        List<? extends View> list2 = this.completedViews;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("completedViews");
        }
        ((View) list2.get(i2)).setVisibility(8);
    }

    private final void setInactive(int i) {
        int i2 = i - 1;
        List<? extends View> list = this.steps;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("steps");
        }
        View view = (View) list.get(i2);
        Drawable drawable = this.backgroundInactive;
        if (drawable == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundInactive");
        }
        view.setBackground(drawable);
        List<? extends View> list2 = this.completedViews;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("completedViews");
        }
        ((View) list2.get(i2)).setVisibility(8);
    }

    private final void setCompleted(int i) {
        int i2 = i - 1;
        List<? extends View> list = this.completedViews;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("completedViews");
        }
        ((View) list.get(i2)).setVisibility(0);
    }
}
