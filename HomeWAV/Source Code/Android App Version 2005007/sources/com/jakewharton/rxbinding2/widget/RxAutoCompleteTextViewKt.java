package com.jakewharton.rxbinding2.widget;

import android.widget.AutoCompleteTextView;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0003H\b\u001a\u0015\u0010\u0007\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0001*\u00020\u0003H\b¨\u0006\t"}, d2 = {"completionHint", "Lio/reactivex/functions/Consumer;", "", "Landroid/widget/AutoCompleteTextView;", "itemClickEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding2/widget/AdapterViewItemClickEvent;", "threshold", "", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxAutoCompleteTextView.kt */
public final class RxAutoCompleteTextViewKt {
    public static final Observable<AdapterViewItemClickEvent> itemClickEvents(AutoCompleteTextView autoCompleteTextView) {
        Intrinsics.checkParameterIsNotNull(autoCompleteTextView, "$receiver");
        Observable<AdapterViewItemClickEvent> itemClickEvents = RxAutoCompleteTextView.itemClickEvents(autoCompleteTextView);
        Intrinsics.checkExpressionValueIsNotNull(itemClickEvents, "RxAutoCompleteTextView.itemClickEvents(this)");
        return itemClickEvents;
    }

    public static final Consumer<? super CharSequence> completionHint(AutoCompleteTextView autoCompleteTextView) {
        Intrinsics.checkParameterIsNotNull(autoCompleteTextView, "$receiver");
        Consumer<? super CharSequence> completionHint = RxAutoCompleteTextView.completionHint(autoCompleteTextView);
        Intrinsics.checkExpressionValueIsNotNull(completionHint, "RxAutoCompleteTextView.completionHint(this)");
        return completionHint;
    }

    public static final Consumer<? super Integer> threshold(AutoCompleteTextView autoCompleteTextView) {
        Intrinsics.checkParameterIsNotNull(autoCompleteTextView, "$receiver");
        Consumer<? super Integer> threshold = RxAutoCompleteTextView.threshold(autoCompleteTextView);
        Intrinsics.checkExpressionValueIsNotNull(threshold, "RxAutoCompleteTextView.threshold(this)");
        return threshold;
    }
}
