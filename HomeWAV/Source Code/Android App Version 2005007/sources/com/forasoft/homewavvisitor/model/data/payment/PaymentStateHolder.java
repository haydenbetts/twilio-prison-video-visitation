package com.forasoft.homewavvisitor.model.data.payment;

import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.interactor.PaymentGatewayType;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/payment/PaymentStateHolder;", "", "paymentGateway", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;", "(Lcom/forasoft/homewavvisitor/model/interactor/PaymentGateway;)V", "getPaymentState", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentState;", "setPaymentState", "", "paymentState", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentStateHolder.kt */
public final class PaymentStateHolder {
    private final PaymentGateway paymentGateway;

    @Inject
    public PaymentStateHolder(PaymentGateway paymentGateway2) {
        Intrinsics.checkParameterIsNotNull(paymentGateway2, "paymentGateway");
        this.paymentGateway = paymentGateway2;
    }

    public final PaymentState getPaymentState() {
        PaymentGatewayType value = this.paymentGateway.getGatewayTypeSubject().getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "paymentGateway.gatewayTypeSubject.value");
        return new PaymentState(value, this.paymentGateway.getPaymentTokenSubject().getValue(), this.paymentGateway.getSelectedConnectionSubject().getValue(), this.paymentGateway.getPaymentAmountSubject().getValue());
    }

    public final void setPaymentState(PaymentState paymentState) {
        Intrinsics.checkParameterIsNotNull(paymentState, "paymentState");
        this.paymentGateway.getGatewayTypeSubject().onNext(paymentState.getGatewayType());
        if (paymentState.getToken() != null) {
            this.paymentGateway.getPaymentTokenSubject().onNext(paymentState.getToken());
        }
        if (paymentState.getSelectedConnection() != null) {
            this.paymentGateway.getSelectedConnectionSubject().onNext(paymentState.getSelectedConnection());
        }
        if (paymentState.getPaymentAmount() != null) {
            this.paymentGateway.getPaymentAmountSubject().onNext(paymentState.getPaymentAmount());
        }
    }
}
