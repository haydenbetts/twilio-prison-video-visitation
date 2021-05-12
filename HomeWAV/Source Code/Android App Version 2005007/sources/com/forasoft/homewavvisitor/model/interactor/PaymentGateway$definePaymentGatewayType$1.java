package com.forasoft.homewavvisitor.model.interactor;

import com.forasoft.homewavvisitor.model.data.Inmate;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentGateway.kt */
final class PaymentGateway$definePaymentGatewayType$1<T> implements Consumer<List<? extends Inmate>> {
    final /* synthetic */ PaymentGateway this$0;

    PaymentGateway$definePaymentGatewayType$1(PaymentGateway paymentGateway) {
        this.this$0 = paymentGateway;
    }

    public final void accept(List<Inmate> list) {
        int i = 0;
        int i2 = 0;
        for (Inmate prison_payment_gateway : list) {
            if (StringsKt.equals$default(prison_payment_gateway.getPrison_payment_gateway(), "stripe", false, 2, (Object) null)) {
                i++;
            } else {
                i2++;
            }
        }
        if (i >= i2) {
            this.this$0.getGatewayTypeSubject().onNext(PaymentGatewayType.STRIPE);
        } else {
            this.this$0.getGatewayTypeSubject().onNext(PaymentGatewayType.BRAINTREE);
        }
    }
}
