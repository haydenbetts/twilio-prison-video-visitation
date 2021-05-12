package com.jakewharton.rxbinding2.widget;

import android.widget.Adapter;
import android.widget.AdapterView;
import com.jakewharton.rxbinding2.InitialValueObservable;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a#\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\b\u001a#\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\b\u001a#\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\b\u001a3\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u00052\u000e\u0010\n\u001a\n\u0012\u0006\b\u0000\u0012\u00020\t0\u000bH\b\u001a#\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\b\u001a1\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\b\u001a#\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0010\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\b\u001a%\u0010\u0011\u001a\n\u0012\u0006\b\u0000\u0012\u00020\u00070\u0012\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\b\u001a#\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0010\"\b\b\u0000\u0010\u0003*\u00020\u0004*\b\u0012\u0004\u0012\u0002H\u00030\u0005H\b¨\u0006\u0015"}, d2 = {"itemClickEvents", "Lio/reactivex/Observable;", "Lcom/jakewharton/rxbinding2/widget/AdapterViewItemClickEvent;", "T", "Landroid/widget/Adapter;", "Landroid/widget/AdapterView;", "itemClicks", "", "itemLongClickEvents", "Lcom/jakewharton/rxbinding2/widget/AdapterViewItemLongClickEvent;", "handled", "Lio/reactivex/functions/Predicate;", "itemLongClicks", "Ljava/util/concurrent/Callable;", "", "itemSelections", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "selection", "Lio/reactivex/functions/Consumer;", "selectionEvents", "Lcom/jakewharton/rxbinding2/widget/AdapterViewSelectionEvent;", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxAdapterView.kt */
public final class RxAdapterViewKt {
    public static final <T extends Adapter> InitialValueObservable<Integer> itemSelections(AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        InitialValueObservable<Integer> itemSelections = RxAdapterView.itemSelections(adapterView);
        Intrinsics.checkExpressionValueIsNotNull(itemSelections, "RxAdapterView.itemSelections(this)");
        return itemSelections;
    }

    public static final <T extends Adapter> InitialValueObservable<AdapterViewSelectionEvent> selectionEvents(AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        InitialValueObservable<AdapterViewSelectionEvent> selectionEvents = RxAdapterView.selectionEvents(adapterView);
        Intrinsics.checkExpressionValueIsNotNull(selectionEvents, "RxAdapterView.selectionEvents(this)");
        return selectionEvents;
    }

    public static final <T extends Adapter> Observable<Integer> itemClicks(AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        Observable<Integer> itemClicks = RxAdapterView.itemClicks(adapterView);
        Intrinsics.checkExpressionValueIsNotNull(itemClicks, "RxAdapterView.itemClicks(this)");
        return itemClicks;
    }

    public static final <T extends Adapter> Observable<AdapterViewItemClickEvent> itemClickEvents(AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        Observable<AdapterViewItemClickEvent> itemClickEvents = RxAdapterView.itemClickEvents(adapterView);
        Intrinsics.checkExpressionValueIsNotNull(itemClickEvents, "RxAdapterView.itemClickEvents(this)");
        return itemClickEvents;
    }

    public static final <T extends Adapter> Observable<Integer> itemLongClicks(AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        Observable<Integer> itemLongClicks = RxAdapterView.itemLongClicks(adapterView);
        Intrinsics.checkExpressionValueIsNotNull(itemLongClicks, "RxAdapterView.itemLongClicks(this)");
        return itemLongClicks;
    }

    public static final <T extends Adapter> Observable<Integer> itemLongClicks(AdapterView<T> adapterView, Callable<Boolean> callable) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        Intrinsics.checkParameterIsNotNull(callable, "handled");
        Observable<Integer> itemLongClicks = RxAdapterView.itemLongClicks(adapterView, callable);
        Intrinsics.checkExpressionValueIsNotNull(itemLongClicks, "RxAdapterView.itemLongClicks(this, handled)");
        return itemLongClicks;
    }

    public static final <T extends Adapter> Observable<AdapterViewItemLongClickEvent> itemLongClickEvents(AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        Observable<AdapterViewItemLongClickEvent> itemLongClickEvents = RxAdapterView.itemLongClickEvents(adapterView);
        Intrinsics.checkExpressionValueIsNotNull(itemLongClickEvents, "RxAdapterView.itemLongClickEvents(this)");
        return itemLongClickEvents;
    }

    public static final <T extends Adapter> Observable<AdapterViewItemLongClickEvent> itemLongClickEvents(AdapterView<T> adapterView, Predicate<? super AdapterViewItemLongClickEvent> predicate) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "handled");
        Observable<AdapterViewItemLongClickEvent> itemLongClickEvents = RxAdapterView.itemLongClickEvents(adapterView, predicate);
        Intrinsics.checkExpressionValueIsNotNull(itemLongClickEvents, "RxAdapterView.itemLongClickEvents(this, handled)");
        return itemLongClickEvents;
    }

    public static final <T extends Adapter> Consumer<? super Integer> selection(AdapterView<T> adapterView) {
        Intrinsics.checkParameterIsNotNull(adapterView, "$receiver");
        Consumer<? super Integer> selection = RxAdapterView.selection(adapterView);
        Intrinsics.checkExpressionValueIsNotNull(selection, "RxAdapterView.selection(this)");
        return selection;
    }
}
