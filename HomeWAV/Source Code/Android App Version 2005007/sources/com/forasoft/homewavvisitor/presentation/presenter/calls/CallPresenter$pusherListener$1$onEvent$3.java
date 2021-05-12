package com.forasoft.homewavvisitor.presentation.presenter.calls;

import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u000e\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "V", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: CallPresenter.kt */
final class CallPresenter$pusherListener$1$onEvent$3<T> implements Consumer<Throwable> {
    final /* synthetic */ CallPresenter$pusherListener$1 this$0;

    CallPresenter$pusherListener$1$onEvent$3(CallPresenter$pusherListener$1 callPresenter$pusherListener$1) {
        this.this$0 = callPresenter$pusherListener$1;
    }

    public final void accept(Throwable th) {
        this.this$0.this$0.stopCall();
    }
}
