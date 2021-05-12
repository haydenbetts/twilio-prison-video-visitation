package com.forasoft.homewavvisitor.ui.fragment.home;

import android.os.Bundle;
import com.forasoft.homewavvisitor.model.data.Inmate;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/home/InmateDetailFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateDetailFragment.kt */
public final class InmateDetailFragment$installModules$1 extends Module {
    final /* synthetic */ InmateDetailFragment this$0;

    InmateDetailFragment$installModules$1(InmateDetailFragment inmateDetailFragment) {
        this.this$0 = inmateDetailFragment;
        Binding<T>.CanBeNamed bind = bind(Inmate.class);
        Bundle arguments = inmateDetailFragment.getArguments();
        bind.toInstance(arguments != null ? (Inmate) arguments.getParcelable("inmate") : null);
    }
}
