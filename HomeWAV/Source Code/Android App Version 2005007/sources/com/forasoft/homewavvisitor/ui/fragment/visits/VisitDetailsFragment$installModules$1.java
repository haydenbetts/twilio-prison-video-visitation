package com.forasoft.homewavvisitor.ui.fragment.visits;

import android.os.Bundle;
import com.forasoft.homewavvisitor.toothpick.qualifier.VisitId;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/visits/VisitDetailsFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VisitDetailsFragment.kt */
public final class VisitDetailsFragment$installModules$1 extends Module {
    final /* synthetic */ VisitDetailsFragment this$0;

    VisitDetailsFragment$installModules$1(VisitDetailsFragment visitDetailsFragment) {
        this.this$0 = visitDetailsFragment;
        Binding<T>.CanBeBound withName = bind(String.class).withName(VisitId.class);
        Bundle arguments = visitDetailsFragment.getArguments();
        withName.toInstance(arguments != null ? arguments.getString("visit id") : null);
    }
}
