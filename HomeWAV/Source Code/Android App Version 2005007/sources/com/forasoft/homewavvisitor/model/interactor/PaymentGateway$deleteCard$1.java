package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u00030\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "", "it", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
final class PaymentGateway$deleteCard$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ String $token;
    final /* synthetic */ PaymentGateway this$0;

    PaymentGateway$deleteCard$1(PaymentGateway paymentGateway, String str) {
        this.this$0 = paymentGateway;
        this.$token = str;
    }

    public final Single<ApiResponse<Map<String, Boolean>>> apply(PaymentGatewayType paymentGatewayType) {
        Intrinsics.checkParameterIsNotNull(paymentGatewayType, "it");
        if (paymentGatewayType == PaymentGatewayType.STRIPE) {
            return this.this$0.paymentApi.deleteCardFromStripe(this.$token);
        }
        return this.this$0.paymentApi.deleteCard(this.$token);
    }
}
