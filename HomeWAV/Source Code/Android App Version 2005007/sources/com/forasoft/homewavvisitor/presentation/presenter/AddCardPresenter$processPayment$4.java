package com.forasoft.homewavvisitor.presentation.presenter;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.AddCardView;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddCardPresenter.kt */
final class AddCardPresenter$processPayment$4<T> implements Consumer<ApiResponse<? extends PaymentProcessedResponse>> {
    final /* synthetic */ AddCardPresenter this$0;

    AddCardPresenter$processPayment$4(AddCardPresenter addCardPresenter) {
        this.this$0 = addCardPresenter;
    }

    public final void accept(ApiResponse<PaymentProcessedResponse> apiResponse) {
        if (apiResponse.getOk()) {
            InmateByVisitor value = this.this$0.paymentGateway.getSelectedConnectionSubject().getValue();
            BehaviorSubject<InmateByVisitor> selectedConnectionSubject = this.this$0.paymentGateway.getSelectedConnectionSubject();
            PaymentProcessedResponse body = apiResponse.getBody();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            selectedConnectionSubject.onNext(InmateByVisitor.copy$default(value, (String) null, body.getBalance(), (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, (String) null, (String) null, 2045, (Object) null));
            ((AddCardView) this.this$0.getViewState()).showMessage((int) R.string.funds_added);
            this.this$0.closeScreen();
            return;
        }
        ((AddCardView) this.this$0.getViewState()).showMessage((int) R.string.funds_not_added);
        this.this$0.closeScreen();
    }
}
