package com.forasoft.homewavvisitor.presentation.presenter.calls;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$exitWithMessage$1 implements Runnable {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$exitWithMessage$1(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public final void run() {
        this.this$0.router.exit();
    }
}
