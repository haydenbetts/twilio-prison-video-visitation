package com.jakewharton.rxbinding2.widget;

import android.view.MenuItem;
import android.widget.PopupMenu;
import com.jakewharton.rxbinding2.internal.VoidToUnit;
import io.reactivex.Observable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003H\b¨\u0006\u0006"}, d2 = {"dismisses", "Lio/reactivex/Observable;", "", "Landroid/widget/PopupMenu;", "itemClicks", "Landroid/view/MenuItem;", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxPopupMenu.kt */
public final class RxPopupMenuKt {
    public static final Observable<MenuItem> itemClicks(PopupMenu popupMenu) {
        Intrinsics.checkParameterIsNotNull(popupMenu, "$receiver");
        Observable<MenuItem> itemClicks = RxPopupMenu.itemClicks(popupMenu);
        Intrinsics.checkExpressionValueIsNotNull(itemClicks, "RxPopupMenu.itemClicks(this)");
        return itemClicks;
    }

    public static final Observable<Unit> dismisses(PopupMenu popupMenu) {
        Intrinsics.checkParameterIsNotNull(popupMenu, "$receiver");
        Observable<R> map = RxPopupMenu.dismisses(popupMenu).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxPopupMenu.dismisses(this).map(VoidToUnit)");
        return map;
    }
}
