package com.jakewharton.rxbinding2.widget;

import android.widget.AbsListView;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b¨\u0006\u0004"}, d2 = {"scrollEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding2/widget/AbsListViewScrollEvent;", "Landroid/widget/AbsListView;", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxAbsListView.kt */
public final class RxAbsListViewKt {
    public static final Observable<AbsListViewScrollEvent> scrollEvents(AbsListView absListView) {
        Intrinsics.checkParameterIsNotNull(absListView, "$receiver");
        Observable<AbsListViewScrollEvent> scrollEvents = RxAbsListView.scrollEvents(absListView);
        Intrinsics.checkExpressionValueIsNotNull(scrollEvents, "RxAbsListView.scrollEvents(this)");
        return scrollEvents;
    }
}
