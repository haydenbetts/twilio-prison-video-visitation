package com.jakewharton.rxbinding2.widget;

import android.widget.RadioGroup;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005*\u00020\u0003H\b¨\u0006\u0006"}, d2 = {"checked", "Lio/reactivex/functions/Consumer;", "", "Landroid/widget/RadioGroup;", "checkedChanges", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxRadioGroup.kt */
public final class RxRadioGroupKt {
    public static final InitialValueObservable<Integer> checkedChanges(RadioGroup radioGroup) {
        Intrinsics.checkParameterIsNotNull(radioGroup, "$receiver");
        InitialValueObservable<Integer> checkedChanges = RxRadioGroup.checkedChanges(radioGroup);
        Intrinsics.checkExpressionValueIsNotNull(checkedChanges, "RxRadioGroup.checkedChanges(this)");
        return checkedChanges;
    }

    public static final Consumer<? super Integer> checked(RadioGroup radioGroup) {
        Intrinsics.checkParameterIsNotNull(radioGroup, "$receiver");
        Consumer<? super Integer> checked = RxRadioGroup.checked(radioGroup);
        Intrinsics.checkExpressionValueIsNotNull(checked, "RxRadioGroup.checked(this)");
        return checked;
    }
}
