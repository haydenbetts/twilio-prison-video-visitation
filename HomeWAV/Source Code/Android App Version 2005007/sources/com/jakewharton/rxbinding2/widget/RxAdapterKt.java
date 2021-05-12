package com.jakewharton.rxbinding2.widget;

import android.widget.Adapter;
import com.jakewharton.rxbinding2.InitialValueObservable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\u0002H\u0002H\b¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"dataChanges", "Lcom/jakewharton/rxbinding2/InitialValueObservable;", "T", "Landroid/widget/Adapter;", "(Landroid/widget/Adapter;)Lcom/jakewharton/rxbinding2/InitialValueObservable;", "rxbinding2-kotlin"}, k = 2, mv = {1, 1, 1})
/* compiled from: RxAdapter.kt */
public final class RxAdapterKt {
    public static final <T extends Adapter> InitialValueObservable<T> dataChanges(T t) {
        Intrinsics.checkParameterIsNotNull(t, "$receiver");
        InitialValueObservable<T> dataChanges = RxAdapter.dataChanges(t);
        Intrinsics.checkExpressionValueIsNotNull(dataChanges, "RxAdapter.dataChanges(this)");
        return dataChanges;
    }
}
