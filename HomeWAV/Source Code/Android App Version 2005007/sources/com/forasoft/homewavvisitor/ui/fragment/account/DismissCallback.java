package com.forasoft.homewavvisitor.ui.fragment.account;

import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/account/DismissCallback;", "Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "listener", "Lcom/forasoft/homewavvisitor/ui/fragment/account/ItemDismissListener;", "(Lcom/forasoft/homewavvisitor/ui/fragment/account/ItemDismissListener;)V", "onMove", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "target", "onSwiped", "", "direction", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DismissCallback.kt */
public final class DismissCallback extends ItemTouchHelper.SimpleCallback {
    private final ItemDismissListener listener;

    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        Intrinsics.checkParameterIsNotNull(recyclerView, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(viewHolder2, "target");
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DismissCallback(ItemDismissListener itemDismissListener) {
        super(0, 4);
        Intrinsics.checkParameterIsNotNull(itemDismissListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.listener = itemDismissListener;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        this.listener.onItemDismissed(viewHolder.getAdapterPosition());
    }
}
