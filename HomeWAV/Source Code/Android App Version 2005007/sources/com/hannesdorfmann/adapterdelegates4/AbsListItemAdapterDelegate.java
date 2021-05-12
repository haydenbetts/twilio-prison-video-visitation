package com.hannesdorfmann.adapterdelegates4;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import java.util.List;

public abstract class AbsListItemAdapterDelegate<I extends T, T, VH extends RecyclerView.ViewHolder> extends AdapterDelegate<List<T>> {
    /* access modifiers changed from: protected */
    public abstract boolean isForViewType(T t, List<T> list, int i);

    /* access modifiers changed from: protected */
    public abstract void onBindViewHolder(I i, VH vh, List<Object> list);

    /* access modifiers changed from: protected */
    public abstract VH onCreateViewHolder(ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public final boolean isForViewType(List<T> list, int i) {
        return isForViewType(list.get(i), list, i);
    }

    /* access modifiers changed from: protected */
    public final void onBindViewHolder(List<T> list, int i, RecyclerView.ViewHolder viewHolder, List<Object> list2) {
        onBindViewHolder(list.get(i), viewHolder, list2);
    }
}
