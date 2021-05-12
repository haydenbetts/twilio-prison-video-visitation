package com.forasoft.homewavvisitor.ui.fragment.calls;

import android.os.Bundle;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/calls/TwilioCallFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TwilioCallFragment.kt */
public final class TwilioCallFragment$installModules$1 extends Module {
    final /* synthetic */ TwilioCallFragment this$0;

    TwilioCallFragment$installModules$1(TwilioCallFragment twilioCallFragment) {
        this.this$0 = twilioCallFragment;
        Binding<T>.CanBeNamed bind = bind(CallWrapper.class);
        Bundle arguments = twilioCallFragment.getArguments();
        bind.toInstance(arguments != null ? (CallWrapper) arguments.getParcelable("call wrapper") : null);
    }
}
