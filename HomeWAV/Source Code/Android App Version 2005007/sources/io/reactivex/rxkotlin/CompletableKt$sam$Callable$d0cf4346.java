package io.reactivex.rxkotlin;

import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 8})
/* compiled from: completable.kt */
final class CompletableKt$sam$Callable$d0cf4346 implements Callable {
    private final /* synthetic */ Function0 function;

    CompletableKt$sam$Callable$d0cf4346(Function0 function0) {
        this.function = function0;
    }

    public final /* synthetic */ V call() {
        return this.function.invoke();
    }
}
