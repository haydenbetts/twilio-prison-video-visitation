package com.jakewharton.rxbinding2.widget;

import android.widget.CompoundButton;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005*\u00020\u0003H\b\u001a\u0015\u0010\u0006\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0001*\u00020\u0003H\b¨\u0006\b"}, d2 = {"checked", "Lio/reactivex/functions/Consumer;", "", "Landroid/widget/CompoundButton;", "checkedChanges", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "toggle", "", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxCompoundButton.kt */
public final class RxCompoundButtonKt {
    public static final InitialValueObservable<Boolean> checkedChanges(CompoundButton compoundButton) {
        Intrinsics.checkParameterIsNotNull(compoundButton, "$receiver");
        InitialValueObservable<Boolean> checkedChanges = RxCompoundButton.checkedChanges(compoundButton);
        Intrinsics.checkExpressionValueIsNotNull(checkedChanges, "RxCompoundButton.checkedChanges(this)");
        return checkedChanges;
    }

    public static final Consumer<? super Boolean> checked(CompoundButton compoundButton) {
        Intrinsics.checkParameterIsNotNull(compoundButton, "$receiver");
        Consumer<? super Boolean> checked = RxCompoundButton.checked(compoundButton);
        Intrinsics.checkExpressionValueIsNotNull(checked, "RxCompoundButton.checked(this)");
        return checked;
    }

    public static final Consumer<? super Object> toggle(CompoundButton compoundButton) {
        Intrinsics.checkParameterIsNotNull(compoundButton, "$receiver");
        Consumer<? super Object> consumer = RxCompoundButton.toggle(compoundButton);
        Intrinsics.checkExpressionValueIsNotNull(consumer, "RxCompoundButton.toggle(this)");
        return consumer;
    }
}
