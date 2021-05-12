package com.forasoft.homewavvisitor.ui.fragment.payment;

import com.braintreepayments.api.interfaces.BraintreeErrorListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/Exception;", "kotlin.jvm.PlatformType", "onError", "com/forasoft/homewavvisitor/ui/fragment/payment/ChooseFundsFragment$initPayment$2$3"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsFragment.kt */
final class ChooseFundsFragment$initPayment$$inlined$apply$lambda$3 implements BraintreeErrorListener {
    final /* synthetic */ ChooseFundsFragment this$0;

    ChooseFundsFragment$initPayment$$inlined$apply$lambda$3(ChooseFundsFragment chooseFundsFragment) {
        this.this$0 = chooseFundsFragment;
    }

    public final void onError(Exception exc) {
        this.this$0.getPresenter().onBraintreeError();
    }
}
