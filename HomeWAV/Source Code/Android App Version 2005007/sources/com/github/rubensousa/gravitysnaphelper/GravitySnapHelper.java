package com.github.rubensousa.gravitysnaphelper;

import android.view.View;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

public class GravitySnapHelper extends LinearSnapHelper {
    private final GravityDelegate delegate;

    public interface SnapListener {
        void onSnap(int i);
    }

    public GravitySnapHelper(int i) {
        this(i, false, (SnapListener) null);
    }

    public GravitySnapHelper(int i, boolean z) {
        this(i, z, (SnapListener) null);
    }

    public GravitySnapHelper(int i, boolean z, SnapListener snapListener) {
        this.delegate = new GravityDelegate(i, z, snapListener);
    }

    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        this.delegate.attachToRecyclerView(recyclerView);
        super.attachToRecyclerView(recyclerView);
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        return this.delegate.calculateDistanceToFinalSnap(layoutManager, view);
    }

    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return this.delegate.findSnapView(layoutManager);
    }

    public void enableLastItemSnap(boolean z) {
        this.delegate.enableLastItemSnap(z);
    }
}
