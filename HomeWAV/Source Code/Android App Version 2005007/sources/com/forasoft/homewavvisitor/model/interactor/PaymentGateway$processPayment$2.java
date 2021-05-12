package com.forasoft.homewavvisitor.model.interactor;

import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a>\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002 \u0004*\u001e\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0003 \u0004*\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/PaymentProcessedResponse;", "kotlin.jvm.PlatformType", "response", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
final class PaymentGateway$processPayment$2<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ PaymentGateway this$0;

    PaymentGateway$processPayment$2(PaymentGateway paymentGateway) {
        this.this$0 = paymentGateway;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0013, code lost:
        r0 = r0.getCredit();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final io.reactivex.Single<com.forasoft.homewavvisitor.model.server.response.ApiResponse<com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse>> apply(final com.forasoft.homewavvisitor.model.server.response.ApiResponse<com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse> r3) {
        /*
            r2 = this;
            java.lang.String r0 = "response"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            boolean r0 = r3.getOk()
            if (r0 == 0) goto L_0x0041
            java.lang.Object r0 = r3.getBody()
            com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse r0 = (com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse) r0
            if (r0 == 0) goto L_0x001e
            com.forasoft.homewavvisitor.model.data.payment.Credit r0 = r0.getCredit()
            if (r0 == 0) goto L_0x001e
            java.lang.String r0 = r0.getInmate_id()
            goto L_0x001f
        L_0x001e:
            r0 = 0
        L_0x001f:
            if (r0 == 0) goto L_0x0041
            com.forasoft.homewavvisitor.model.interactor.PaymentGateway r0 = r2.this$0
            com.forasoft.homewavvisitor.model.repository.PaymentRepository r0 = r0.paymentRepository
            java.lang.Object r1 = r3.getBody()
            com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse r1 = (com.forasoft.homewavvisitor.model.data.payment.PaymentProcessedResponse) r1
            com.forasoft.homewavvisitor.model.data.payment.Credit r1 = r1.getCredit()
            io.reactivex.Single r0 = r0.saveCredit(r1)
            com.forasoft.homewavvisitor.model.interactor.PaymentGateway$processPayment$2$1 r1 = new com.forasoft.homewavvisitor.model.interactor.PaymentGateway$processPayment$2$1
            r1.<init>(r3)
            io.reactivex.functions.Function r1 = (io.reactivex.functions.Function) r1
            io.reactivex.Single r3 = r0.map(r1)
            goto L_0x0045
        L_0x0041:
            io.reactivex.Single r3 = io.reactivex.Single.just(r3)
        L_0x0045:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.interactor.PaymentGateway$processPayment$2.apply(com.forasoft.homewavvisitor.model.server.response.ApiResponse):io.reactivex.Single");
    }
}
