package com.forasoft.homewavvisitor.ui.fragment.home;

import com.forasoft.homewavvisitor.presentation.adapter.InmatesAdapter;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomeFragment.kt */
final class HomeFragment$showApprovedInmates$1 implements Runnable {
    final /* synthetic */ InmatesAdapter $adapter;
    final /* synthetic */ List $inmates;

    HomeFragment$showApprovedInmates$1(InmatesAdapter inmatesAdapter, List list) {
        this.$adapter = inmatesAdapter;
        this.$inmates = list;
    }

    public final void run() {
        this.$adapter.submitList(this.$inmates);
    }
}
