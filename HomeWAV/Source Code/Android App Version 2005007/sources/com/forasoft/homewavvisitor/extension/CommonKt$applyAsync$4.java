package com.forasoft.homewavvisitor.extension;

import io.reactivex.Completable;
import io.reactivex.CompletableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lio/reactivex/Completable;", "kotlin.jvm.PlatformType", "upstream", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: common.kt */
final class CommonKt$applyAsync$4 implements CompletableTransformer {
    public static final CommonKt$applyAsync$4 INSTANCE = new CommonKt$applyAsync$4();

    CommonKt$applyAsync$4() {
    }

    public final Completable apply(Completable completable) {
        Intrinsics.checkParameterIsNotNull(completable, "upstream");
        return completable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
