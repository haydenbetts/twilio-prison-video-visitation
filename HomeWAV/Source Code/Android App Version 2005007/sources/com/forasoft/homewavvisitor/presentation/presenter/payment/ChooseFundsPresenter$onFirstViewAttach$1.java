package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsPresenter.kt */
final class ChooseFundsPresenter$onFirstViewAttach$1<T> implements Consumer<InmateByVisitor> {
    final /* synthetic */ ChooseFundsPresenter this$0;

    ChooseFundsPresenter$onFirstViewAttach$1(ChooseFundsPresenter chooseFundsPresenter) {
        this.this$0 = chooseFundsPresenter;
    }

    public final void accept(InmateByVisitor inmateByVisitor) {
        PaymentGateway access$getPaymentGateway$p = this.this$0.paymentGateway;
        Intrinsics.checkExpressionValueIsNotNull(inmateByVisitor, "it");
        access$getPaymentGateway$p.definePaymentGatewayType(inmateByVisitor);
        ((ChooseFundsView) this.this$0.getViewState()).setName(inmateByVisitor.getFirst_name() + ' ' + inmateByVisitor.getLast_name());
        ((ChooseFundsView) this.this$0.getViewState()).setBalance(inmateByVisitor.getCredit_balance());
        this.this$0.currentConnection = inmateByVisitor;
        this.this$0.setPaymentScope();
        if (inmateByVisitor.is_fraud()) {
            ((ChooseFundsView) this.this$0.getViewState()).setFraudState();
        }
    }
}
