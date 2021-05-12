package com.forasoft.homewavvisitor.ui.fragment.payment;

import com.braintreepayments.api.interfaces.PaymentMethodNoncesUpdatedListener;
import com.braintreepayments.api.models.PaymentMethodNonce;
import com.forasoft.homewavvisitor.presentation.presenter.payment.ChooseFundsPresenter;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "it", "", "Lcom/braintreepayments/api/models/PaymentMethodNonce;", "kotlin.jvm.PlatformType", "", "onPaymentMethodNoncesUpdated", "com/forasoft/homewavvisitor/ui/fragment/payment/ChooseFundsFragment$initPayment$2$2"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsFragment.kt */
final class ChooseFundsFragment$initPayment$$inlined$apply$lambda$2 implements PaymentMethodNoncesUpdatedListener {
    final /* synthetic */ ChooseFundsFragment this$0;

    ChooseFundsFragment$initPayment$$inlined$apply$lambda$2(ChooseFundsFragment chooseFundsFragment) {
        this.this$0 = chooseFundsFragment;
    }

    public final void onPaymentMethodNoncesUpdated(List<PaymentMethodNonce> list) {
        ChooseFundsPresenter presenter = this.this$0.getPresenter();
        Intrinsics.checkExpressionValueIsNotNull(list, "it");
        presenter.onReceivePaymentMethods(CollectionsKt.sortedWith(list, new Comparator<T>() {
            public final int compare(T t, T t2) {
                PaymentMethodNonce paymentMethodNonce = (PaymentMethodNonce) t2;
                Intrinsics.checkExpressionValueIsNotNull(paymentMethodNonce, "it");
                PaymentMethodNonce paymentMethodNonce2 = (PaymentMethodNonce) t;
                Intrinsics.checkExpressionValueIsNotNull(paymentMethodNonce2, "it");
                return ComparisonsKt.compareValues(Boolean.valueOf(paymentMethodNonce.isDefault()), Boolean.valueOf(paymentMethodNonce2.isDefault()));
            }
        }));
    }
}
