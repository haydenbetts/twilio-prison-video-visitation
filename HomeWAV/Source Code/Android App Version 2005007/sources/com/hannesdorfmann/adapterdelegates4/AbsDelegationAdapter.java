package com.hannesdorfmann.adapterdelegates4;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Objects;

public abstract class AbsDelegationAdapter<T> extends RecyclerView.Adapter {
    protected AdapterDelegatesManager<T> delegatesManager;
    protected T items;

    public AbsDelegationAdapter() {
        this(new AdapterDelegatesManager());
    }

    public AbsDelegationAdapter(AdapterDelegatesManager<T> adapterDelegatesManager) {
        Objects.requireNonNull(adapterDelegatesManager, "AdapterDelegatesManager is null");
        this.delegatesManager = adapterDelegatesManager;
    }

    public AbsDelegationAdapter(AdapterDelegate<T>... adapterDelegateArr) {
        this(new AdapterDelegatesManager(adapterDelegateArr));
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        this.delegatesManager.onBindViewHolder(this.items, i, viewHolder, (List) null);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        this.delegatesManager.onBindViewHolder(this.items, i, viewHolder, list);
    }

    public int getItemViewType(int i) {
        return this.delegatesManager.getItemViewType(this.items, i);
    }

    public void onViewRecycled(RecyclerView.ViewHolder viewHolder) {
        this.delegatesManager.onViewRecycled(viewHolder);
    }

    public boolean onFailedToRecycleView(RecyclerView.ViewHolder viewHolder) {
        return this.delegatesManager.onFailedToRecycleView(viewHolder);
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        this.delegatesManager.onViewAttachedToWindow(viewHolder);
    }

    public void onViewDetachedFromWindow(RecyclerView.ViewHolder viewHolder) {
        this.delegatesManager.onViewDetachedFromWindow(viewHolder);
    }

    public T getItems() {
        return this.items;
    }

    public void setItems(T t) {
        this.items = t;
    }
}
