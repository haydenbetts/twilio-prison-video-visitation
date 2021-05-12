package com.forasoft.homewavvisitor.ui;

import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\"\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J \u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH\u0016R\u0012\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/ScrollToTopOnDataChangeObserver;", "Landroidx/recyclerview/widget/RecyclerView$AdapterDataObserver;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "adapter", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "(Landroidx/recyclerview/widget/RecyclerView;Landroidx/recyclerview/widget/RecyclerView$Adapter;)V", "onChanged", "", "onItemRangeChanged", "positionStart", "", "itemCount", "payload", "", "onItemRangeInserted", "onItemRangeMoved", "fromPosition", "toPosition", "onItemRangeRemoved", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ScrollToTopOnDataChangeObserver.kt */
public final class ScrollToTopOnDataChangeObserver extends RecyclerView.AdapterDataObserver {
    private final RecyclerView.Adapter<?> adapter;
    private final RecyclerView recyclerView;

    public ScrollToTopOnDataChangeObserver(RecyclerView recyclerView2, RecyclerView.Adapter<?> adapter2) {
        Intrinsics.checkParameterIsNotNull(recyclerView2, "recyclerView");
        Intrinsics.checkParameterIsNotNull(adapter2, "adapter");
        this.recyclerView = recyclerView2;
        this.adapter = adapter2;
    }

    public void onChanged() {
        if (this.adapter.getItemCount() > 0) {
            this.recyclerView.scrollToPosition(0);
        }
    }

    public void onItemRangeRemoved(int i, int i2) {
        if (this.adapter.getItemCount() > 0) {
            this.recyclerView.scrollToPosition(0);
        }
    }

    public void onItemRangeMoved(int i, int i2, int i3) {
        if (this.adapter.getItemCount() > 0) {
            this.recyclerView.scrollToPosition(0);
        }
    }

    public void onItemRangeInserted(int i, int i2) {
        if (this.adapter.getItemCount() > 0) {
            this.recyclerView.scrollToPosition(0);
        }
    }

    public void onItemRangeChanged(int i, int i2) {
        if (this.adapter.getItemCount() > 0) {
            this.recyclerView.scrollToPosition(0);
        }
    }

    public void onItemRangeChanged(int i, int i2, Object obj) {
        if (this.adapter.getItemCount() > 0) {
            this.recyclerView.scrollToPosition(0);
        }
    }
}
