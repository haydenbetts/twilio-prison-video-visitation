package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.extension.CommonKt;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "", "kotlin.jvm.PlatformType", "paymentGatewayType", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
final class PaymentGateway$saveCard$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ boolean $makeDefault;
    final /* synthetic */ PaymentGateway this$0;

    PaymentGateway$saveCard$1(PaymentGateway paymentGateway, boolean z) {
        this.this$0 = paymentGateway;
        this.$makeDefault = z;
    }

    public final Single<Boolean> apply(final PaymentGatewayType paymentGatewayType) {
        Intrinsics.checkParameterIsNotNull(paymentGatewayType, "paymentGatewayType");
        return this.this$0.getPaymentTokenSubject().take(1).singleOrError().flatMap(new Function<T, SingleSource<? extends R>>(this) {
            final /* synthetic */ PaymentGateway$saveCard$1 this$0;

            {
                this.this$0 = r1;
            }

            public final Single<Boolean> apply(String str) {
                Intrinsics.checkParameterIsNotNull(str, "token");
                if (paymentGatewayType == PaymentGatewayType.STRIPE) {
                    return CommonKt.applyAsync(this.this$0.this$0.paymentApi.saveCardInStripe(str, this.this$0.this$0.toInt(this.this$0.$makeDefault))).flatMap(AnonymousClass1.INSTANCE);
                }
                return CommonKt.applyAsync(this.this$0.this$0.paymentApi.saveCard(str, this.this$0.$makeDefault)).flatMap(AnonymousClass2.INSTANCE);
            }
        });
    }
}
