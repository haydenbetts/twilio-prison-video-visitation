package com.forasoft.homewavvisitor.ui.fragment;

import android.view.MenuItem;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: BaseFragment.kt */
final class BaseFragment$onCreateOptionsMenu$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ MenuItem $item;
    final /* synthetic */ BaseFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseFragment$onCreateOptionsMenu$1(BaseFragment baseFragment, MenuItem menuItem) {
        super(0);
        this.this$0 = baseFragment;
        this.$item = menuItem;
    }

    public final void invoke() {
        this.this$0.onOptionsItemSelected(this.$item);
    }
}
