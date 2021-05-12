package com.forasoft.homewavvisitor.presentation.presenter.calls;

import io.reactivex.functions.Predicate;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "", "test", "(Ljava/lang/Long;)Z"}, k = 3, mv = {1, 1, 16})
/* compiled from: TwilioCallPresenter.kt */
final class TwilioCallPresenter$checkRemainingTime$3<T> implements Predicate<Long> {
    final /* synthetic */ TwilioCallPresenter this$0;

    TwilioCallPresenter$checkRemainingTime$3(TwilioCallPresenter twilioCallPresenter) {
        this.this$0 = twilioCallPresenter;
    }

    public final boolean test(Long l) {
        Intrinsics.checkParameterIsNotNull(l, "it");
        return l.longValue() > ((long) -1) && !this.this$0.isDisconnectRequested;
    }
}
