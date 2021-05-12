package com.jakewharton.rxbinding2.view;

import android.view.ViewGroup;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b¨\u0006\u0004"}, d2 = {"changeEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding2/view/ViewGroupHierarchyChangeEvent;", "Landroid/view/ViewGroup;", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxViewGroup.kt */
public final class RxViewGroupKt {
    public static final Observable<ViewGroupHierarchyChangeEvent> changeEvents(ViewGroup viewGroup) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "$receiver");
        Observable<ViewGroupHierarchyChangeEvent> changeEvents = RxViewGroup.changeEvents(viewGroup);
        Intrinsics.checkExpressionValueIsNotNull(changeEvents, "RxViewGroup.changeEvents(this)");
        return changeEvents;
    }
}
