package com.forasoft.homewavvisitor.ui.fragment.payment;

import android.os.Bundle;
import com.forasoft.homewavvisitor.model.data.PaymentRequestData;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PayNearMeFragment.kt */
public final class PayNearMeFragment$installModules$1 extends Module {
    final /* synthetic */ PayNearMeFragment this$0;

    PayNearMeFragment$installModules$1(PayNearMeFragment payNearMeFragment) {
        this.this$0 = payNearMeFragment;
        Binding<T>.CanBeNamed bind = bind(PaymentRequestData.class);
        Bundle arguments = payNearMeFragment.getArguments();
        bind.toInstance(arguments != null ? (PaymentRequestData) arguments.getParcelable("payment request data") : null);
    }
}
