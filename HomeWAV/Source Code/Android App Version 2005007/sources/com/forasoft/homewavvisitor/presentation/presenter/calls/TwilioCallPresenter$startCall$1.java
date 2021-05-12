package com.forasoft.homewavvisitor.presentation.presenter.calls;

import com.forasoft.homewavvisitor.model.data.TwilioToken;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/TwilioToken;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TwilioCallPresenter.kt */
final class TwilioCallPresenter$startCall$1<T> implements Consumer<ApiResponse<? extends TwilioToken>> {
    final /* synthetic */ TwilioCallPresenter this$0;

    TwilioCallPresenter$startCall$1(TwilioCallPresenter twilioCallPresenter) {
        this.this$0 = twilioCallPresenter;
    }

    public final void accept(ApiResponse<TwilioToken> apiResponse) {
        ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new Function0<Unit>(this) {
            final /* synthetic */ TwilioCallPresenter$startCall$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void invoke() {
                this.this$0.this$0.callDao.saveCall(this.this$0.this$0.wrapper.getCall());
            }
        }, 31, (Object) null);
        TwilioCallPresenter twilioCallPresenter = this.this$0;
        TwilioToken body = apiResponse.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        twilioCallPresenter.connectToRoom(body.getToken());
        this.this$0.checkRemainingTime();
        this.this$0.startCheckIn();
        this.this$0.initPusherListener();
        this.this$0.initUI();
    }
}
