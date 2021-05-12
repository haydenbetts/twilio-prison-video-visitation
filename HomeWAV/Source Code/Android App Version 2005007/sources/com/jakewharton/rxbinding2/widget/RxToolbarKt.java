package com.jakewharton.rxbinding2.widget;

import android.view.MenuItem;
import android.widget.Toolbar;
import com.jakewharton.rxbinding2.internal.VoidToUnit;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0003H\b\u001a\u0017\u0010\u0006\u001a\f\u0012\b\b\u0000\u0012\u0004\u0018\u00010\b0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00020\n0\u0007*\u00020\u0003H\b\u001a\u0017\u0010\u000b\u001a\f\u0012\b\b\u0000\u0012\u0004\u0018\u00010\b0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\n0\u0007*\u00020\u0003H\b¨\u0006\r"}, d2 = {"itemClicks", "Lio/reactivex/Observable;", "Landroid/view/MenuItem;", "Landroid/widget/Toolbar;", "navigationClicks", "", "subtitle", "Lio/reactivex/functions/Consumer;", "", "subtitleRes", "", "title", "titleRes", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxToolbar.kt */
public final class RxToolbarKt {
    public static final Observable<MenuItem> itemClicks(Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "$receiver");
        Observable<MenuItem> itemClicks = RxToolbar.itemClicks(toolbar);
        Intrinsics.checkExpressionValueIsNotNull(itemClicks, "RxToolbar.itemClicks(this)");
        return itemClicks;
    }

    public static final Observable<Unit> navigationClicks(Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "$receiver");
        Observable<R> map = RxToolbar.navigationClicks(toolbar).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxToolbar.navigationClicks(this).map(VoidToUnit)");
        return map;
    }

    public static final Consumer<? super CharSequence> title(Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "$receiver");
        Consumer<? super CharSequence> title = RxToolbar.title(toolbar);
        Intrinsics.checkExpressionValueIsNotNull(title, "RxToolbar.title(this)");
        return title;
    }

    public static final Consumer<? super Integer> titleRes(Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "$receiver");
        Consumer<? super Integer> titleRes = RxToolbar.titleRes(toolbar);
        Intrinsics.checkExpressionValueIsNotNull(titleRes, "RxToolbar.titleRes(this)");
        return titleRes;
    }

    public static final Consumer<? super CharSequence> subtitle(Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "$receiver");
        Consumer<? super CharSequence> subtitle = RxToolbar.subtitle(toolbar);
        Intrinsics.checkExpressionValueIsNotNull(subtitle, "RxToolbar.subtitle(this)");
        return subtitle;
    }

    public static final Consumer<? super Integer> subtitleRes(Toolbar toolbar) {
        Intrinsics.checkParameterIsNotNull(toolbar, "$receiver");
        Consumer<? super Integer> subtitleRes = RxToolbar.subtitleRes(toolbar);
        Intrinsics.checkExpressionValueIsNotNull(subtitleRes, "RxToolbar.subtitleRes(this)");
        return subtitleRes;
    }
}
