package com.forasoft.homewavvisitor.presentation.presenter.calls;

import com.forasoft.homewavvisitor.model.liveswitch.RemoteMedia;
import com.forasoft.homewavvisitor.presentation.presenter.calls.LiveswitchCallPresenter;
import com.forasoft.homewavvisitor.presentation.view.calls.LiveswitchCallView;
import fm.liveswitch.ConnectionState;
import fm.liveswitch.IAction1;
import fm.liveswitch.ManagedConnection;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lfm/liveswitch/ManagedConnection;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$receiveRemoteMedia$1<T> implements IAction1<ManagedConnection> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$receiveRemoteMedia$1(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public final void invoke(ManagedConnection managedConnection) {
        Intrinsics.checkExpressionValueIsNotNull(managedConnection, "it");
        ConnectionState state = managedConnection.getState();
        if (state != null) {
            int i = LiveswitchCallPresenter.WhenMappings.$EnumSwitchMapping$0[state.ordinal()];
            if (i == 1 || i == 2) {
                LiveswitchCallView liveswitchCallView = (LiveswitchCallView) this.this$0.getViewState();
                RemoteMedia access$getRemoteMedia$p = this.this$0.remoteMedia;
                if (access$getRemoteMedia$p == null) {
                    Intrinsics.throwNpe();
                }
                String id = access$getRemoteMedia$p.getId();
                Intrinsics.checkExpressionValueIsNotNull(id, "remoteMedia!!.id");
                liveswitchCallView.removeInmateVideo(id);
                LiveswitchCallPresenter.onBackPressed$default(this.this$0, (String) null, (String) null, 3, (Object) null);
            }
        }
    }
}
