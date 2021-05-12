package com.forasoft.homewavvisitor.presentation.presenter.calls;

import fm.liveswitch.IAction1;
import fm.liveswitch.LocalMedia;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lfm/liveswitch/LocalMedia;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$startCallFlow$1<T> implements IAction1<LocalMedia> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$startCallFlow$1(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public final void invoke(LocalMedia localMedia) {
        this.this$0.startMediaSession();
        this.this$0.initPusherListener();
        this.this$0.initUI();
    }
}
