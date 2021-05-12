package com.jakewharton.rxbinding2.support.v7.widget;

import android.view.MenuItem;
import androidx.appcompat.widget.ActionMenuView;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b¨\u0006\u0004"}, d2 = {"itemClicks", "Lio/reactivex/Observable;", "Landroid/view/MenuItem;", "Landroidx/appcompat/widget/ActionMenuView;", "rxbinding2-appcompat-v7-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxActionMenuView.kt */
public final class RxActionMenuViewKt {
    public static final Observable<MenuItem> itemClicks(ActionMenuView actionMenuView) {
        Intrinsics.checkParameterIsNotNull(actionMenuView, "$receiver");
        Observable<MenuItem> itemClicks = RxActionMenuView.itemClicks(actionMenuView);
        Intrinsics.checkExpressionValueIsNotNull(itemClicks, "RxActionMenuView.itemClicks(this)");
        return itemClicks;
    }
}
