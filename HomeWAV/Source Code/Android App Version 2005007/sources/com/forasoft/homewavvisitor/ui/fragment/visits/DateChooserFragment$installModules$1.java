package com.forasoft.homewavvisitor.ui.fragment.visits;

import android.os.Bundle;
import com.forasoft.homewavvisitor.model.data.Inmate;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import toothpick.config.Binding;
import toothpick.config.Module;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"com/forasoft/homewavvisitor/ui/fragment/visits/DateChooserFragment$installModules$1", "Ltoothpick/config/Module;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DateChooserFragment.kt */
public final class DateChooserFragment$installModules$1 extends Module {
    final /* synthetic */ DateChooserFragment this$0;

    DateChooserFragment$installModules$1(DateChooserFragment dateChooserFragment) {
        this.this$0 = dateChooserFragment;
        Binding<T>.CanBeNamed bind = bind(Inmate.class);
        Bundle arguments = dateChooserFragment.getArguments();
        bind.toInstance(arguments != null ? (Inmate) arguments.getParcelable("inmate") : null);
        bind(Map.class).toInstance(new LinkedHashMap());
    }
}
