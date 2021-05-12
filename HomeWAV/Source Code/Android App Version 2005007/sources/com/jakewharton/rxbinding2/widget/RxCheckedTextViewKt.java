package com.jakewharton.rxbinding2.widget;

import android.widget.CheckedTextView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b¨\u0006\u0004"}, d2 = {"check", "Lio/reactivex/functions/Consumer;", "", "Landroid/widget/CheckedTextView;", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxCheckedTextView.kt */
public final class RxCheckedTextViewKt {
    public static final Consumer<? super Boolean> check(CheckedTextView checkedTextView) {
        Intrinsics.checkParameterIsNotNull(checkedTextView, "$receiver");
        Consumer<? super Boolean> check = RxCheckedTextView.check(checkedTextView);
        Intrinsics.checkExpressionValueIsNotNull(check, "RxCheckedTextView.check(this)");
        return check;
    }
}
