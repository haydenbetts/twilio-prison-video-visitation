package com.forasoft.homewavvisitor.model;

import io.reactivex.functions.BiFunction;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\t\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00012\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\u0005\u001a\u0002H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "R", "T", "U", "t", "u", "apply", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/reactivex/rxkotlin/Singles$zip$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: Singles.kt */
public final class S3MediaLoader$loadMedia$2$$special$$inlined$zip$1<T1, T2, R> implements BiFunction<List<? extends Media>, List<? extends Media>, R> {
    public final R apply(List<? extends Media> list, List<? extends Media> list2) {
        List list3 = list2;
        List list4 = list;
        Intrinsics.checkExpressionValueIsNotNull(list4, "images");
        Intrinsics.checkExpressionValueIsNotNull(list3, "videos");
        return CollectionsKt.plus(list4, list3);
    }
}
