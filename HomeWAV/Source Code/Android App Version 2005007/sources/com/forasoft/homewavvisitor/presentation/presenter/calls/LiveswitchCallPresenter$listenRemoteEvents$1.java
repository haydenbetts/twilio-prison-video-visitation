package com.forasoft.homewavvisitor.presentation.presenter.calls;

import fm.liveswitch.ConnectionInfo;
import fm.liveswitch.IAction1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "remoteInfo", "Lfm/liveswitch/ConnectionInfo;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$listenRemoteEvents$1<T> implements IAction1<ConnectionInfo> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$listenRemoteEvents$1(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public final void invoke(ConnectionInfo connectionInfo) {
        LiveswitchCallPresenter liveswitchCallPresenter = this.this$0;
        Intrinsics.checkExpressionValueIsNotNull(connectionInfo, "remoteInfo");
        liveswitchCallPresenter.receiveRemoteMedia(connectionInfo);
    }
}
