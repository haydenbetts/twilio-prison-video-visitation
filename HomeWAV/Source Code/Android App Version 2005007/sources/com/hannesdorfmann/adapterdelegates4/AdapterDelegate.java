package com.hannesdorfmann.adapterdelegates4;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public abstract class AdapterDelegate<T> {
    /* access modifiers changed from: protected */
    public abstract boolean isForViewType(T t, int i);

    /* access modifiers changed from: protected */
    public abstract void onBindViewHolder(T t, int i, RecyclerView.ViewHolder viewHolder, List<Object> list);

    /* access modifiers changed from: protected */
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup);

    /* access modifiers changed from: protected */
    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
    }

    /* access modifiers changed from: protected */
    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
    }
}
