package com.hannesdorfmann.adapterdelegates4;

import android.view.ViewGroup;
import androidx.recyclerview.widget.AdapterListUpdateCallback;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Objects;

public class AsyncListDifferDelegationAdapter<T> extends RecyclerView.Adapter {
    protected final AdapterDelegatesManager<List<T>> delegatesManager;
    protected final AsyncListDiffer<T> differ;

    public AsyncListDifferDelegationAdapter(DiffUtil.ItemCallback<T> itemCallback) {
        this(itemCallback, new AdapterDelegatesManager());
    }

    public AsyncListDifferDelegationAdapter(DiffUtil.ItemCallback<T> itemCallback, AdapterDelegatesManager<List<T>> adapterDelegatesManager) {
        Objects.requireNonNull(itemCallback, "ItemCallback is null");
        Objects.requireNonNull(adapterDelegatesManager, "AdapterDelegatesManager is null");
        this.differ = new AsyncListDiffer<>((RecyclerView.Adapter) this, itemCallback);
        this.delegatesManager = adapterDelegatesManager;
    }

    public AsyncListDifferDelegationAdapter(AsyncDifferConfig asyncDifferConfig, AdapterDelegatesManager<List<T>> adapterDelegatesManager) {
        Objects.requireNonNull(asyncDifferConfig, "AsyncDifferConfig is null");
        Objects.requireNonNull(adapterDelegatesManager, "AdapterDelegatesManager is null");
        this.differ = new AsyncListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.delegatesManager = adapterDelegatesManager;
    }

    public AsyncListDifferDelegationAdapter(DiffUtil.ItemCallback<T> itemCallback, AdapterDelegate<List<T>>... adapterDelegateArr) {
        Objects.requireNonNull(itemCallback, "ItemCallback is null");
        this.differ = new AsyncListDiffer<>((RecyclerView.Adapter) this, itemCallback);
        this.delegatesManager = new AdapterDelegatesManager<>(adapterDelegateArr);
    }

    public AsyncListDifferDelegationAdapter(AsyncDifferConfig asyncDifferConfig, AdapterDelegate<List<T>>... adapterDelegateArr) {
        Objects.requireNonNull(asyncDifferConfig, "AsyncDifferConfig is null");
        this.differ = new AsyncListDiffer<>((ListUpdateCallback) new AdapterListUpdateCallback(this), asyncDifferConfig);
        this.delegatesManager = new AdapterDelegatesManager<>(adapterDelegateArr);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return this.delegatesManager.onCreateViewHolder(viewGroup, i);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        this.delegatesManager.onBindViewHolder(this.differ.getCurrentList(), i, viewHolder, (List) null);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List list) {
        this.delegatesManager.onBindViewHolder(this.differ.getCurrentList(), i, viewHolder, list);
    }

    public int getItemViewType(int i) {
        return this.delegatesManager.getItemViewType(this.differ.getCurrentList(), i);
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

    public List<T> getItems() {
        return this.differ.getCurrentList();
    }

    public void setItems(List<T> list) {
        this.differ.submitList(list);
    }

    public int getItemCount() {
        return this.differ.getCurrentList().size();
    }
}
