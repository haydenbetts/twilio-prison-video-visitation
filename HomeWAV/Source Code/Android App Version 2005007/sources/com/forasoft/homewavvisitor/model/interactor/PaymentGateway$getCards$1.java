package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "it", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
final class PaymentGateway$getCards$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ PaymentGateway this$0;

    PaymentGateway$getCards$1(PaymentGateway paymentGateway) {
        this.this$0 = paymentGateway;
    }

    public final Single<ApiResponse<List<Card>>> apply(PaymentGatewayType paymentGatewayType) {
        Intrinsics.checkParameterIsNotNull(paymentGatewayType, "it");
        if (paymentGatewayType == PaymentGatewayType.STRIPE) {
            return this.this$0.paymentApi.getCardsFromStripe();
        }
        return this.this$0.paymentApi.getCards();
    }
}
