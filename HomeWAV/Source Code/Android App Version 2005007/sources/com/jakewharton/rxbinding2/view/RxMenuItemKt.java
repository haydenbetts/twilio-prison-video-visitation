package com.jakewharton.rxbinding2.view;

import android.graphics.drawable.Drawable;
import android.view.MenuItem;
import com.jakewharton.rxbinding2.internal.VoidToUnit;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0005H\b\u001a\u0015\u0010\u0006\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0007*\u00020\u0003H\b\u001a\u0013\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u0003H\b\u001a#\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0001*\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00030\u0005H\b\u001a\u0015\u0010\u000b\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\f\u001a\n\u0012\u0006\b\u0000\u0012\u00020\r0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u000e\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u000f0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u0010\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00110\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u0012\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u000f0\u0007*\u00020\u0003H\b\u001a\u0015\u0010\u0013\u001a\n\u0012\u0006\b\u0000\u0012\u00020\b0\u0007*\u00020\u0003H\b¨\u0006\u0014"}, d2 = {"actionViewEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding2/view/MenuItemActionViewEvent;", "Landroid/view/MenuItem;", "handled", "Lio/reactivex/functions/Predicate;", "checked", "Lio/reactivex/functions/Consumer;", "", "clicks", "", "enabled", "icon", "Landroid/graphics/drawable/Drawable;", "iconRes", "", "title", "", "titleRes", "visible", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxMenuItem.kt */
public final class RxMenuItemKt {
    public static final Observable<Unit> clicks(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Observable<R> map = RxMenuItem.clicks(menuItem).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxMenuItem.clicks(this).map(VoidToUnit)");
        return map;
    }

    public static final Observable<Unit> clicks(MenuItem menuItem, Predicate<? super MenuItem> predicate) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<R> map = RxMenuItem.clicks(menuItem, predicate).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxMenuItem.clicks(this, handled).map(VoidToUnit)");
        return map;
    }

    public static final Observable<MenuItemActionViewEvent> actionViewEvents(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Observable<MenuItemActionViewEvent> actionViewEvents = RxMenuItem.actionViewEvents(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(actionViewEvents, "RxMenuItem.actionViewEvents(this)");
        return actionViewEvents;
    }

    public static final Observable<MenuItemActionViewEvent> actionViewEvents(MenuItem menuItem, Predicate<? super MenuItemActionViewEvent> predicate) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<MenuItemActionViewEvent> actionViewEvents = RxMenuItem.actionViewEvents(menuItem, predicate);
        Intrinsics.checkExpressionValueIsNotNull(actionViewEvents, "RxMenuItem.actionViewEvents(this, handled)");
        return actionViewEvents;
    }

    public static final Consumer<? super Boolean> checked(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Consumer<? super Boolean> checked = RxMenuItem.checked(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(checked, "RxMenuItem.checked(this)");
        return checked;
    }

    public static final Consumer<? super Boolean> enabled(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Consumer<? super Boolean> enabled = RxMenuItem.enabled(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(enabled, "RxMenuItem.enabled(this)");
        return enabled;
    }

    public static final Consumer<? super Drawable> icon(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Consumer<? super Drawable> icon = RxMenuItem.icon(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(icon, "RxMenuItem.icon(this)");
        return icon;
    }

    public static final Consumer<? super Integer> iconRes(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Consumer<? super Integer> iconRes = RxMenuItem.iconRes(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(iconRes, "RxMenuItem.iconRes(this)");
        return iconRes;
    }

    public static final Consumer<? super CharSequence> title(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Consumer<? super CharSequence> title = RxMenuItem.title(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(title, "RxMenuItem.title(this)");
        return title;
    }

    public static final Consumer<? super Integer> titleRes(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Consumer<? super Integer> titleRes = RxMenuItem.titleRes(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(titleRes, "RxMenuItem.titleRes(this)");
        return titleRes;
    }

    public static final Consumer<? super Boolean> visible(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$receiver");
        Consumer<? super Boolean> visible = RxMenuItem.visible(menuItem);
        Intrinsics.checkExpressionValueIsNotNull(visible, "RxMenuItem.visible(this)");
        return visible;
    }
}
