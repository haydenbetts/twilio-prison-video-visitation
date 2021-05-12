package com.github.rubensousa.gravitysnaphelper;

import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;

public class GravityPagerSnapHelper extends PagerSnapHelper {
    private final GravityDelegate delegate;

    public GravityPagerSnapHelper(int i) {
        this(i, false, (GravitySnapHelper.SnapListener) null);
    }

    public GravityPagerSnapHelper(int i, boolean z) {
        this(i, z, (GravitySnapHelper.SnapListener) null);
    }

    public GravityPagerSnapHelper(int i, boolean z, GravitySnapHelper.SnapListener snapListener) {
        this.delegate = new GravityDelegate(i, z, snapListener);
    }

    public void attachToRecyclerView(RecyclerView recyclerView) throws IllegalStateException {
        if (recyclerView == null || ((recyclerView.getLayoutManager() instanceof LinearLayoutManager) && !(recyclerView.getLayoutManager() instanceof GridLayoutManager))) {
            this.delegate.attachToRecyclerView(recyclerView);
            super.attachToRecyclerView(recyclerView);
            return;
        }
        throw new IllegalStateException("GravityPagerSnapHelper needs a RecyclerView with a LinearLayoutManager");
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
