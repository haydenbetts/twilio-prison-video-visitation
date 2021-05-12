package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.model.interactor.PaymentGateway;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "it", "", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddCardPresenter.kt */
final class AddCardPresenter$processPayment$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ AddCardPresenter this$0;

    AddCardPresenter$processPayment$1(AddCardPresenter addCardPresenter) {
        this.this$0 = addCardPresenter;
    }

    public final Single<ApiResponse<PaymentProcessedResponse>> apply(String str) {
        Intrinsics.checkParameterIsNotNull(str, "it");
        InmateByVisitor value = this.this$0.paymentGateway.getSelectedConnectionSubject().getValue();
        PaymentGateway access$getPaymentGateway$p = this.this$0.paymentGateway;
        String paymentScope = this.this$0.getPaymentScope();
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.2f", Arrays.copyOf(new Object[]{this.this$0.paymentGateway.getPaymentAmountSubject().getValue()}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return PaymentGateway.processPayment$default(access$getPaymentGateway$p, paymentScope, StringsKt.replace$default(format, ",", ".", false, 4, (Object) null), str, value.getId(), value.getOccupant_id(), this.this$0.idempotencyKey, (Boolean) null, 64, (Object) null);
    }
}
