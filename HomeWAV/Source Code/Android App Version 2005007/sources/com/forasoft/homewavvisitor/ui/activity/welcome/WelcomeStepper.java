package com.forasoft.homewavvisitor.ui.activity.welcome;

import air.HomeWAV.R;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001a\u0010\u0013\u001a\u00020\u00142\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\b\u001a\u00020\tH\u0002J\b\u0010\u0015\u001a\u00020\u0014H\u0002J\u000e\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\tR\u000e\u0010\u000b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeStepper;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyle", "", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "currentStep", "currentTheme", "Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeStepTheme;", "getCurrentTheme", "()Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeStepTheme;", "darkDrawables", "Lcom/forasoft/homewavvisitor/ui/activity/welcome/WelcomeStepThemeDrawables;", "lightDrawables", "init", "", "setCircleDrawables", "setStep", "step", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: WelcomeStepper.kt */
public final class WelcomeStepper extends LinearLayout {
    private HashMap _$_findViewCache;
    private int currentStep = 1;
    private final WelcomeStepThemeDrawables darkDrawables = new WelcomeStepThemeDrawables(R.drawable.circle_white, R.drawable.circle_green_dark);
    private final WelcomeStepThemeDrawables lightDrawables = new WelcomeStepThemeDrawables(R.drawable.circle_green, R.drawable.circle_gray);

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

    private final WelcomeStepTheme getCurrentTheme() {
        int i = this.currentStep;
        if (i != 1) {
            if (i == 2) {
                return WelcomeStepTheme.LIGHT;
            }
            if (i != 3) {
                return WelcomeStepTheme.DARK;
            }
        }
        return WelcomeStepTheme.DARK;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WelcomeStepper(Context context) {
        super(context);
        Intrinsics.checkParameterIsNotNull(context, "context");
        init((AttributeSet) null, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WelcomeStepper(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        init(attributeSet, 0);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WelcomeStepper(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(attributeSet, "attrs");
        init(attributeSet, i);
    }

    private final void init(AttributeSet attributeSet, int i) {
        Object systemService = getContext().getSystemService("layout_inflater");
        if (systemService != null) {
            ((LayoutInflater) systemService).inflate(R.layout.welcome_stepper, this, true);
            setCircleDrawables();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.LayoutInflater");
    }

    public final void setStep(int i) {
        this.currentStep = i;
        setCircleDrawables();
    }

    private final void setCircleDrawables() {
        WelcomeStepThemeDrawables welcomeStepThemeDrawables = getCurrentTheme() == WelcomeStepTheme.DARK ? this.darkDrawables : this.lightDrawables;
        int i = this.currentStep;
        if (i == 1) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_1)).setImageResource(welcomeStepThemeDrawables.getActive());
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_2)).setImageResource(welcomeStepThemeDrawables.getInactive());
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_3)).setImageResource(welcomeStepThemeDrawables.getInactive());
        } else if (i == 2) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_1)).setImageResource(welcomeStepThemeDrawables.getInactive());
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_2)).setImageResource(welcomeStepThemeDrawables.getActive());
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_3)).setImageResource(welcomeStepThemeDrawables.getInactive());
        } else if (i == 3) {
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_1)).setImageResource(welcomeStepThemeDrawables.getInactive());
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_2)).setImageResource(welcomeStepThemeDrawables.getInactive());
            ((ImageView) _$_findCachedViewById(com.forasoft.homewavvisitor.R.id.step_3)).setImageResource(welcomeStepThemeDrawables.getActive());
        }
    }
}
