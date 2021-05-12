package com.hannesdorfmann.adapterdelegates4.dsl;

import android.view.View;
import android.view.ViewGroup;
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001aá\u0001\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\n\b\u0000\u0010\u0004\u0018\u0001*\u0002H\u0003\"\u0004\b\u0001\u0010\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062S\b\n\u0010\u0007\u001aM\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u0002H\u00030\u0002¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u000e0\b28\b\n\u0010\u000f\u001a2\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0012\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u0013\u0012\u0004\u0012\u00020\u00140\u00102\u001f\b\b\u0010\u0015\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0017\u0012\u0004\u0012\u00020\u00180\u0016¢\u0006\u0002\b\u0019H\b¨\u0006\u001a"}, d2 = {"adapterDelegate", "Lcom/hannesdorfmann/adapterdelegates4/AdapterDelegate;", "", "T", "I", "layout", "", "on", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "item", "items", "position", "", "layoutInflater", "Lkotlin/Function2;", "Landroid/view/ViewGroup;", "parent", "layoutRes", "Landroid/view/View;", "block", "Lkotlin/Function1;", "Lcom/hannesdorfmann/adapterdelegates4/dsl/AdapterDelegateViewHolder;", "", "Lkotlin/ExtensionFunctionType;", "kotlin-dsl_release"}, k = 2, mv = {1, 1, 15})
/* compiled from: ListAdapterDelegateDsl.kt */
public final class ListAdapterDelegateDslKt {
    public static /* synthetic */ AdapterDelegate adapterDelegate$default(int i, Function3 function3, Function2 function2, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            Intrinsics.needClassReification();
            function3 = ListAdapterDelegateDslKt$adapterDelegate$1.INSTANCE;
        }
        if ((i2 & 4) != 0) {
            function2 = ListAdapterDelegateDslKt$adapterDelegate$2.INSTANCE;
        }
        Intrinsics.checkParameterIsNotNull(function3, DebugKt.DEBUG_PROPERTY_VALUE_ON);
        Intrinsics.checkParameterIsNotNull(function2, "layoutInflater");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        return new DslListAdapterDelegate(i, function3, function1, function2);
    }

    public static final /* synthetic */ <I extends T, T> AdapterDelegate<List<T>> adapterDelegate(int i, Function3<? super T, ? super List<? extends T>, ? super Integer, Boolean> function3, Function2<? super ViewGroup, ? super Integer, ? extends View> function2, Function1<? super AdapterDelegateViewHolder<I>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function3, DebugKt.DEBUG_PROPERTY_VALUE_ON);
        Intrinsics.checkParameterIsNotNull(function2, "layoutInflater");
        Intrinsics.checkParameterIsNotNull(function1, "block");
        return new DslListAdapterDelegate<>(i, function3, function1, function2);
    }
}
