package io.reactivex.rxkotlin;

import io.reactivex.functions.Function3;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 8})
/* compiled from: flowable.kt */
final class FlowableKt$sam$Function3$7083077a implements Function3 {
    private final /* synthetic */ kotlin.jvm.functions.Function3 function;

    FlowableKt$sam$Function3$7083077a(kotlin.jvm.functions.Function3 function3) {
        this.function = function3;
    }

    public final /* synthetic */ R apply(T1 t1, T2 t2, T3 t3) {
        return this.function.invoke(t1, t2, t3);
    }
}
