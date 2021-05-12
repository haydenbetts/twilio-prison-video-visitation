package com.forasoft.homewavvisitor.presentation.presenter.calls;

import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "apply", "(Ljava/lang/Long;)J"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$checkRemainingTime$2<T, R> implements Function<T, R> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$checkRemainingTime$2(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public /* bridge */ /* synthetic */ Object apply(Object obj) {
        return Long.valueOf(apply((Long) obj));
    }

    public final long apply(Long l) {
        Intrinsics.checkParameterIsNotNull(l, "it");
        return this.this$0.wrapper.getMax_call_duration() - l.longValue();
    }
}
