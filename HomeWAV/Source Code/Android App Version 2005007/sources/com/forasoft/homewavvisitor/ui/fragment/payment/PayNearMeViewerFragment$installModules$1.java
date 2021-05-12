package com.forasoft.homewavvisitor.ui.fragment.payment;

import android.os.Bundle;
import com.forasoft.homewavvisitor.toothpick.qualifier.PaynearmeOrderUrl;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/payment/PayNearMeViewerFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PayNearMeViewerFragment.kt */
public final class PayNearMeViewerFragment$installModules$1 extends Module {
    final /* synthetic */ PayNearMeViewerFragment this$0;

    PayNearMeViewerFragment$installModules$1(PayNearMeViewerFragment payNearMeViewerFragment) {
        this.this$0 = payNearMeViewerFragment;
        Binding<T>.CanBeBound withName = bind(String.class).withName(PaynearmeOrderUrl.class);
        Bundle arguments = payNearMeViewerFragment.getArguments();
        withName.toInstance(arguments != null ? arguments.getString("paynearme order url") : null);
    }
}
