package com.forasoft.homewavvisitor.presentation.presenter.calls;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.CallStatus;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.CallStatusResponse;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept", "(Ljava/lang/Long;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: TwilioCallPresenter.kt */
final class TwilioCallPresenter$startCheckIn$2<T> implements Consumer<Long> {
    final /* synthetic */ TwilioCallPresenter this$0;

    TwilioCallPresenter$startCheckIn$2(TwilioCallPresenter twilioCallPresenter) {
        this.this$0 = twilioCallPresenter;
    }

    public final void accept(Long l) {
        CommonKt.applyAsync(HomewavApi.DefaultImpls.checkinWebrtcCall$default(this.this$0.api, this.this$0.wrapper.getCall().getPubid(), (String) null, 2, (Object) null)).subscribe(new Consumer<ApiResponse<? extends CallStatusResponse>>(this) {
            final /* synthetic */ TwilioCallPresenter$startCheckIn$2 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(ApiResponse<CallStatusResponse> apiResponse) {
                CallStatusResponse body = apiResponse.getBody();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                if (body.getStatus() == CallStatus.COMPLETE) {
                    TwilioCallPresenter.onBackPressed$default(this.this$0.this$0, (String) null, "Inmate disconnected", 1, (Object) null);
                }
            }
        }, new Consumer<Throwable>(this) {
            final /* synthetic */ TwilioCallPresenter$startCheckIn$2 this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(Throwable th) {
                TwilioCallPresenter.onBackPressed$default(this.this$0.this$0, "CheckInFailed", (String) null, 2, (Object) null);
            }
        });
    }
}
