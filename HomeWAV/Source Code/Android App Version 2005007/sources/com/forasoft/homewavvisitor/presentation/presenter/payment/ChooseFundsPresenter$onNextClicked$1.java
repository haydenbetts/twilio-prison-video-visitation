package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.model.data.Card;
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

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "it", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsPresenter.kt */
final class ChooseFundsPresenter$onNextClicked$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ Card $card;
    final /* synthetic */ float $realAmount;
    final /* synthetic */ ChooseFundsPresenter this$0;

    ChooseFundsPresenter$onNextClicked$1(ChooseFundsPresenter chooseFundsPresenter, float f, Card card) {
        this.this$0 = chooseFundsPresenter;
        this.$realAmount = f;
        this.$card = card;
    }

    public final Single<ApiResponse<PaymentProcessedResponse>> apply(InmateByVisitor inmateByVisitor) {
        Intrinsics.checkParameterIsNotNull(inmateByVisitor, "it");
        PaymentGateway access$getPaymentGateway$p = this.this$0.paymentGateway;
        String access$getCurrentPaymentScope$p = this.this$0.currentPaymentScope;
        if (access$getCurrentPaymentScope$p == null) {
            Intrinsics.throwNpe();
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format("%.2f", Arrays.copyOf(new Object[]{Float.valueOf(this.$realAmount)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        return access$getPaymentGateway$p.processPayment(access$getCurrentPaymentScope$p, StringsKt.replace$default(format, ",", ".", false, 4, (Object) null), this.$card.getToken(), inmateByVisitor.getId(), inmateByVisitor.getOccupant_id(), this.this$0.idempotencyKey, true);
    }
}
