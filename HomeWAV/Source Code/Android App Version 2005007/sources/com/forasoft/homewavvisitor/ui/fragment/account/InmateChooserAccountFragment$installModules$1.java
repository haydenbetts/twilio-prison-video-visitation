package com.forasoft.homewavvisitor.ui.fragment.account;

import android.os.Bundle;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.toothpick.PrimitiveWrapper;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/account/InmateChooserAccountFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateChooserAccountFragment.kt */
public final class InmateChooserAccountFragment$installModules$1 extends Module {
    final /* synthetic */ InmateChooserAccountFragment this$0;

    InmateChooserAccountFragment$installModules$1(InmateChooserAccountFragment inmateChooserAccountFragment) {
        this.this$0 = inmateChooserAccountFragment;
        Binding<T>.CanBeNamed bind = bind(PrimitiveWrapper.class);
        Bundle arguments = inmateChooserAccountFragment.getArguments();
        bind.toInstance(new PrimitiveWrapper(arguments != null ? (Inmate) arguments.getParcelable("inmate") : null));
    }
}
