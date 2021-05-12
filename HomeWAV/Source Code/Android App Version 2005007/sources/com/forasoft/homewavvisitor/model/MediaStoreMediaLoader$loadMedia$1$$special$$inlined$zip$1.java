package com.forasoft.homewavvisitor.model;

import io.reactivex.functions.BiFunction;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SpreadBuilder;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\t\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00012\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\u0005\u001a\u0002H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "R", "T1", "T2", "t1", "t2", "apply", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/reactivex/rxkotlin/Observables$zip$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: Observables.kt */
public final class MediaStoreMediaLoader$loadMedia$1$$special$$inlined$zip$1<T1, T2, R> implements BiFunction<T1, T2, R> {
    public final R apply(T1 t1, T2 t2) {
        List list = (List) t2;
        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
        Object[] array = ((List) t1).toArray(new Media[0]);
        if (array != null) {
            spreadBuilder.addSpread(array);
            Object[] array2 = list.toArray(new Media[0]);
            if (array2 != null) {
                spreadBuilder.addSpread(array2);
                return CollectionsKt.listOf((Media[]) spreadBuilder.toArray(new Media[spreadBuilder.size()]));
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
}
