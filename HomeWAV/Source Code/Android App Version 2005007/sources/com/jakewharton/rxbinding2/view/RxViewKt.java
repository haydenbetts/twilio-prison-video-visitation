package com.jakewharton.rxbinding2.view;

import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import com.jakewharton.rxbinding2.InitialValueObservable;
import com.jakewharton.rxbinding2.internal.VoidToUnit;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000d\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u001a\u0015\u0010\u0000\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005*\u00020\u0003H\b\u001a\u0013\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u0003H\b\u001a\u0015\u0010\t\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u0003H\b\u001a\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u0003H\b\u001a\u0013\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0005*\u00020\u0003H\b\u001a#\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0005*\u00020\u00032\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0000\u0012\u00020\r0\u000fH\b\u001a\u0013\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u0003H\b\u001a\u0015\u0010\u0011\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00020\u0013*\u00020\u0003H\b\u001a\u0013\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u0003H\b\u001a\u0013\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005*\u00020\u0003H\b\u001a#\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005*\u00020\u00032\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00160\u000fH\b\u001a\u0013\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005*\u00020\u0003H\b\u001a#\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0005*\u00020\u00032\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00180\u000fH\b\u001a\u0013\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0005*\u00020\u0003H\b\u001a\u0013\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u0003H\b\u001a\u0013\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u0003H\b\u001a!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001dH\b\u001a!\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\b0\u0005*\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00020\u001dH\b\u001a\u0015\u0010 \u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u0005*\u00020\u0003H\b\u001a\u0015\u0010#\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u0013\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0005*\u00020\u0003H\b\u001a\u0013\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005*\u00020\u0003H\b\u001a#\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00160\u0005*\u00020\u00032\u000e\u0010\u000e\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00160\u000fH\b\u001a\u0015\u0010'\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u0003H\b\u001a\u001d\u0010'\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010(\u001a\u00020%H\b¨\u0006)"}, d2 = {"activated", "Lio/reactivex/functions/Consumer;", "", "Landroid/view/View;", "attachEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding2/view/ViewAttachEvent;", "attaches", "", "clickable", "clicks", "detaches", "drags", "Landroid/view/DragEvent;", "handled", "Lio/reactivex/functions/Predicate;", "draws", "enabled", "focusChanges", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "globalLayouts", "hovers", "Landroid/view/MotionEvent;", "keys", "Landroid/view/KeyEvent;", "layoutChangeEvents", "Lcom/jakewharton/rxbinding2/view/ViewLayoutChangeEvent;", "layoutChanges", "longClicks", "Ljava/util/concurrent/Callable;", "preDraws", "proceedDrawingPass", "pressed", "scrollChangeEvents", "Lcom/jakewharton/rxbinding2/view/ViewScrollChangeEvent;", "selected", "systemUiVisibilityChanges", "", "touches", "visibility", "visibilityWhenFalse", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxView.kt */
public final class RxViewKt {
    public static final Observable<Unit> attaches(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<R> map = RxView.attaches(view).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.attaches(this).map(VoidToUnit)");
        return map;
    }

    public static final Observable<ViewAttachEvent> attachEvents(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<ViewAttachEvent> attachEvents = RxView.attachEvents(view);
        Intrinsics.checkExpressionValueIsNotNull(attachEvents, "RxView.attachEvents(this)");
        return attachEvents;
    }

    public static final Observable<Unit> detaches(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<R> map = RxView.detaches(view).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.detaches(this).map(VoidToUnit)");
        return map;
    }

    public static final Observable<Unit> clicks(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<R> map = RxView.clicks(view).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.clicks(this).map(VoidToUnit)");
        return map;
    }

    public static final Observable<DragEvent> drags(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<DragEvent> drags = RxView.drags(view);
        Intrinsics.checkExpressionValueIsNotNull(drags, "RxView.drags(this)");
        return drags;
    }

    public static final Observable<DragEvent> drags(View view, Predicate<? super DragEvent> predicate) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<DragEvent> drags = RxView.drags(view, predicate);
        Intrinsics.checkExpressionValueIsNotNull(drags, "RxView.drags(this, handled)");
        return drags;
    }

    public static final Observable<Unit> draws(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<R> map = RxView.draws(view).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.draws(this).map(VoidToUnit)");
        return map;
    }

    public static final InitialValueObservable<Boolean> focusChanges(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        InitialValueObservable<Boolean> focusChanges = RxView.focusChanges(view);
        Intrinsics.checkExpressionValueIsNotNull(focusChanges, "RxView.focusChanges(this)");
        return focusChanges;
    }

    public static final Observable<Unit> globalLayouts(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<R> map = RxView.globalLayouts(view).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.globalLayouts(this).map(VoidToUnit)");
        return map;
    }

    public static final Observable<MotionEvent> hovers(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<MotionEvent> hovers = RxView.hovers(view);
        Intrinsics.checkExpressionValueIsNotNull(hovers, "RxView.hovers(this)");
        return hovers;
    }

    public static final Observable<MotionEvent> hovers(View view, Predicate<? super MotionEvent> predicate) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<MotionEvent> hovers = RxView.hovers(view, predicate);
        Intrinsics.checkExpressionValueIsNotNull(hovers, "RxView.hovers(this, handled)");
        return hovers;
    }

    public static final Observable<Unit> layoutChanges(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<R> map = RxView.layoutChanges(view).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.layoutChanges(this).map(VoidToUnit)");
        return map;
    }

    public static final Observable<ViewLayoutChangeEvent> layoutChangeEvents(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<ViewLayoutChangeEvent> layoutChangeEvents = RxView.layoutChangeEvents(view);
        Intrinsics.checkExpressionValueIsNotNull(layoutChangeEvents, "RxView.layoutChangeEvents(this)");
        return layoutChangeEvents;
    }

    public static final Observable<Unit> longClicks(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<R> map = RxView.longClicks(view).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.longClicks(this).map(VoidToUnit)");
        return map;
    }

    public static final Observable<Unit> longClicks(View view, Callable<Boolean> callable) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Intrinsics.checkParameterIsNotNull(callable, "handled");
        Observable<R> map = RxView.longClicks(view, callable).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.longClicks(this, handled).map(VoidToUnit)");
        return map;
    }

    public static final Observable<Unit> preDraws(View view, Callable<Boolean> callable) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Intrinsics.checkParameterIsNotNull(callable, "proceedDrawingPass");
        Observable<R> map = RxView.preDraws(view, callable).map(VoidToUnit.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(map, "RxView.preDraws(this, pr…wingPass).map(VoidToUnit)");
        return map;
    }

    public static final Observable<ViewScrollChangeEvent> scrollChangeEvents(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<ViewScrollChangeEvent> scrollChangeEvents = RxView.scrollChangeEvents(view);
        Intrinsics.checkExpressionValueIsNotNull(scrollChangeEvents, "RxView.scrollChangeEvents(this)");
        return scrollChangeEvents;
    }

    public static final Observable<Integer> systemUiVisibilityChanges(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<Integer> systemUiVisibilityChanges = RxView.systemUiVisibilityChanges(view);
        Intrinsics.checkExpressionValueIsNotNull(systemUiVisibilityChanges, "RxView.systemUiVisibilityChanges(this)");
        return systemUiVisibilityChanges;
    }

    public static final Observable<MotionEvent> touches(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<MotionEvent> observable = RxView.touches(view);
        Intrinsics.checkExpressionValueIsNotNull(observable, "RxView.touches(this)");
        return observable;
    }

    public static final Observable<MotionEvent> touches(View view, Predicate<? super MotionEvent> predicate) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<MotionEvent> observable = RxView.touches(view, predicate);
        Intrinsics.checkExpressionValueIsNotNull(observable, "RxView.touches(this, handled)");
        return observable;
    }

    public static final Observable<KeyEvent> keys(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Observable<KeyEvent> keys = RxView.keys(view);
        Intrinsics.checkExpressionValueIsNotNull(keys, "RxView.keys(this)");
        return keys;
    }

    public static final Observable<KeyEvent> keys(View view, Predicate<? super KeyEvent> predicate) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<KeyEvent> keys = RxView.keys(view, predicate);
        Intrinsics.checkExpressionValueIsNotNull(keys, "RxView.keys(this, handled)");
        return keys;
    }

    public static final Consumer<? super Boolean> activated(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Consumer<? super Boolean> activated = RxView.activated(view);
        Intrinsics.checkExpressionValueIsNotNull(activated, "RxView.activated(this)");
        return activated;
    }

    public static final Consumer<? super Boolean> clickable(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Consumer<? super Boolean> clickable = RxView.clickable(view);
        Intrinsics.checkExpressionValueIsNotNull(clickable, "RxView.clickable(this)");
        return clickable;
    }

    public static final Consumer<? super Boolean> enabled(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Consumer<? super Boolean> enabled = RxView.enabled(view);
        Intrinsics.checkExpressionValueIsNotNull(enabled, "RxView.enabled(this)");
        return enabled;
    }

    public static final Consumer<? super Boolean> pressed(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Consumer<? super Boolean> pressed = RxView.pressed(view);
        Intrinsics.checkExpressionValueIsNotNull(pressed, "RxView.pressed(this)");
        return pressed;
    }

    public static final Consumer<? super Boolean> selected(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Consumer<? super Boolean> selected = RxView.selected(view);
        Intrinsics.checkExpressionValueIsNotNull(selected, "RxView.selected(this)");
        return selected;
    }

    public static final Consumer<? super Boolean> visibility(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Consumer<? super Boolean> visibility = RxView.visibility(view);
        Intrinsics.checkExpressionValueIsNotNull(visibility, "RxView.visibility(this)");
        return visibility;
    }

    public static final Consumer<? super Boolean> visibility(View view, int i) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        Consumer<? super Boolean> visibility = RxView.visibility(view, i);
        Intrinsics.checkExpressionValueIsNotNull(visibility, "RxView.visibility(this, visibilityWhenFalse)");
        return visibility;
    }
}
