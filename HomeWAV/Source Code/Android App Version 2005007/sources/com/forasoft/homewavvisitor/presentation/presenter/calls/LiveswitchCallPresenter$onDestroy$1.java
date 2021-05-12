package com.forasoft.homewavvisitor.presentation.presenter.calls;

import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.model.data.CallEntity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: LiveswitchCallPresenter.kt */
final class LiveswitchCallPresenter$onDestroy$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ LiveswitchCallPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LiveswitchCallPresenter$onDestroy$1(LiveswitchCallPresenter liveswitchCallPresenter) {
        super(0);
        this.this$0 = liveswitchCallPresenter;
    }

    public final void invoke() {
        CallDao access$getCallDao$p = this.this$0.callDao;
        CallEntity call = this.this$0.wrapper.getCall();
        LocalDateTime connected = this.this$0.wrapper.getCall().getConnected();
        access$getCallDao$p.saveCall(CallEntity.copy$default(call, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, connected != null ? connected.plusSeconds(this.this$0.duration) : null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (LocalDateTime) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, -32769, 2047, (Object) null));
    }
}
