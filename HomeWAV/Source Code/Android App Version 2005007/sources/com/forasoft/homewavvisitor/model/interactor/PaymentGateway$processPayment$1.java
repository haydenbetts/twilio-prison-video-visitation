package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "it", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
final class PaymentGateway$processPayment$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ String $amount;
    final /* synthetic */ String $idempotencyKey;
    final /* synthetic */ String $inmateId;
    final /* synthetic */ String $nonce;
    final /* synthetic */ String $occupantId;
    final /* synthetic */ String $scope;
    final /* synthetic */ Boolean $useSavedCard;
    final /* synthetic */ PaymentGateway this$0;

    PaymentGateway$processPayment$1(PaymentGateway paymentGateway, String str, String str2, String str3, String str4, String str5, String str6, Boolean bool) {
        this.this$0 = paymentGateway;
        this.$scope = str;
        this.$amount = str2;
        this.$nonce = str3;
        this.$inmateId = str4;
        this.$occupantId = str5;
        this.$idempotencyKey = str6;
        this.$useSavedCard = bool;
    }

    public final Single<ApiResponse<PaymentProcessedResponse>> apply(PaymentGatewayType paymentGatewayType) {
        Intrinsics.checkParameterIsNotNull(paymentGatewayType, "it");
        if (paymentGatewayType == PaymentGatewayType.STRIPE) {
            return this.this$0.paymentApi.processByStripe(this.$scope, this.$amount, this.$nonce, this.$inmateId, this.$occupantId, "stripe", this.$idempotencyKey, this.$useSavedCard);
        }
        return this.this$0.paymentApi.process(this.$scope, this.$amount, this.$nonce, this.$inmateId, this.$occupantId, "brainTree");
    }
}
