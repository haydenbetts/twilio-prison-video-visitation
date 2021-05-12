package com.hannesdorfmann.adapterdelegates4.dsl;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u0002H\u0003\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u00032\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0004\b\t\u0010\n"}, d2 = {"<anonymous>", "", "I", "T", "item", "<anonymous parameter 1>", "", "<anonymous parameter 2>", "", "invoke", "(Ljava/lang/Object;Ljava/util/List;I)Z"}, k = 3, mv = {1, 1, 15})
/* compiled from: ListAdapterDelegateDsl.kt */
public final class ListAdapterDelegateDslKt$adapterDelegate$1 extends Lambda implements Function3<T, List<? extends T>, Integer, Boolean> {
    public static final ListAdapterDelegateDslKt$adapterDelegate$1 INSTANCE = new ListAdapterDelegateDslKt$adapterDelegate$1();

    public ListAdapterDelegateDslKt$adapterDelegate$1() {
        super(3);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return Boolean.valueOf(invoke(obj, (List) obj2, ((Number) obj3).intValue()));
    }

    public final boolean invoke(T t, List<? extends T> list, int i) {
        Intrinsics.checkParameterIsNotNull(list, "<anonymous parameter 1>");
        Intrinsics.reifiedOperationMarker(3, "I");
        return t instanceof Object;
    }
}
