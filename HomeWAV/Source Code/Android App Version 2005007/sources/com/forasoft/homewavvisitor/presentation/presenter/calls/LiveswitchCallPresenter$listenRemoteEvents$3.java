package com.forasoft.homewavvisitor.presentation.presenter.calls;

import fm.liveswitch.ClientInfo;
import fm.liveswitch.IAction2;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lfm/liveswitch/ClientInfo;", "kotlin.jvm.PlatformType", "message", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$listenRemoteEvents$3<T1, T2> implements IAction2<ClientInfo, String> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    LiveswitchCallPresenter$listenRemoteEvents$3(LiveswitchCallPresenter liveswitchCallPresenter) {
        this.this$0 = liveswitchCallPresenter;
    }

    public final void invoke(ClientInfo clientInfo, String str) {
        JSONObject jSONObject = new JSONObject(str);
        Object obj = jSONObject.get("author");
        Object obj2 = jSONObject.get("body");
        if (Intrinsics.areEqual(obj, (Object) "operator") && Intrinsics.areEqual(obj2, (Object) "operator-disconnect_and_disable")) {
            LiveswitchCallPresenter.onBackPressed$default(this.this$0, "RoomDisconnected", (String) null, 2, (Object) null);
        }
    }
}
