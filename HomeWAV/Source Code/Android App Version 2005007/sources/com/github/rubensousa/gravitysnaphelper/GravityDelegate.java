package com.github.rubensousa.gravitysnaphelper;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.github.rubensousa.gravitysnaphelper.GravitySnapHelper;
import java.util.Locale;

class GravityDelegate {
    private int gravity;
    private OrientationHelper horizontalHelper;
    private boolean isRtlHorizontal;
    /* access modifiers changed from: private */
    public GravitySnapHelper.SnapListener listener;
    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            if (i == 2) {
                boolean unused = GravityDelegate.this.snapping = false;
            }
            if (i == 0 && GravityDelegate.this.snapping && GravityDelegate.this.listener != null) {
                int access$200 = GravityDelegate.this.getSnappedPosition(recyclerView);
                if (access$200 != -1) {
                    GravityDelegate.this.listener.onSnap(access$200);
                }
                boolean unused2 = GravityDelegate.this.snapping = false;
            }
        }
    };
    private boolean snapLastItem;
    /* access modifiers changed from: private */
    public boolean snapping;
    private OrientationHelper verticalHelper;

    public GravityDelegate(int i, boolean z, GravitySnapHelper.SnapListener snapListener) {
        if (i == 8388611 || i == 8388613 || i == 80 || i == 48) {
            this.snapLastItem = z;
            this.gravity = i;
            this.listener = snapListener;
            return;
        }
        throw new IllegalArgumentException("Invalid gravity value. Use START | END | BOTTOM | TOP constants");
    }

    public void attachToRecyclerView(RecyclerView recyclerView) {
        if (recyclerView != null) {
            recyclerView.setOnFlingListener((RecyclerView.OnFlingListener) null);
            int i = this.gravity;
            if (i == 8388611 || i == 8388613) {
                this.isRtlHorizontal = isRtl();
            }
            if (this.listener != null) {
                recyclerView.addOnScrollListener(this.mScrollListener);
            }
        }
    }

    private boolean isRtl() {
        if (Build.VERSION.SDK_INT >= 17 && TextUtils.getLayoutDirectionFromLocale(Locale.getDefault()) == 1) {
            return true;
        }
        return false;
    }

    public int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (!layoutManager.canScrollHorizontally()) {
            iArr[0] = 0;
        } else if (this.gravity == 8388611) {
            iArr[0] = distanceToStart(view, getHorizontalHelper(layoutManager), false);
        } else {
            iArr[0] = distanceToEnd(view, getHorizontalHelper(layoutManager), false);
        }
        if (!layoutManager.canScrollVertically()) {
            iArr[1] = 0;
        } else if (this.gravity == 48) {
            iArr[1] = distanceToStart(view, getVerticalHelper(layoutManager), false);
        } else {
            iArr[1] = distanceToEnd(view, getVerticalHelper(layoutManager), false);
        }
        return iArr;
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.view.View findSnapView(androidx.recyclerview.widget.RecyclerView.LayoutManager r3) {
        /*
            r2 = this;
            boolean r0 = r3 instanceof androidx.recyclerview.widget.LinearLayoutManager
            if (r0 == 0) goto L_0x003d
            int r0 = r2.gravity
            r1 = 48
            if (r0 == r1) goto L_0x0034
            r1 = 80
            if (r0 == r1) goto L_0x002b
            r1 = 8388611(0x800003, float:1.1754948E-38)
            if (r0 == r1) goto L_0x0022
            r1 = 8388613(0x800005, float:1.175495E-38)
            if (r0 == r1) goto L_0x0019
            goto L_0x003d
        L_0x0019:
            androidx.recyclerview.widget.OrientationHelper r0 = r2.getHorizontalHelper(r3)
            android.view.View r3 = r2.findEndView(r3, r0)
            goto L_0x003e
        L_0x0022:
            androidx.recyclerview.widget.OrientationHelper r0 = r2.getHorizontalHelper(r3)
            android.view.View r3 = r2.findStartView(r3, r0)
            goto L_0x003e
        L_0x002b:
            androidx.recyclerview.widget.OrientationHelper r0 = r2.getVerticalHelper(r3)
            android.view.View r3 = r2.findEndView(r3, r0)
            goto L_0x003e
        L_0x0034:
            androidx.recyclerview.widget.OrientationHelper r0 = r2.getVerticalHelper(r3)
            android.view.View r3 = r2.findStartView(r3, r0)
            goto L_0x003e
        L_0x003d:
            r3 = 0
        L_0x003e:
            if (r3 == 0) goto L_0x0042
            r0 = 1
            goto L_0x0043
        L_0x0042:
            r0 = 0
        L_0x0043:
            r2.snapping = r0
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.github.rubensousa.gravitysnaphelper.GravityDelegate.findSnapView(androidx.recyclerview.widget.RecyclerView$LayoutManager):android.view.View");
    }

    public void enableLastItemSnap(boolean z) {
        this.snapLastItem = z;
    }

    private int distanceToStart(View view, OrientationHelper orientationHelper, boolean z) {
        if (!this.isRtlHorizontal || z) {
            return orientationHelper.getDecoratedStart(view) - orientationHelper.getStartAfterPadding();
        }
        return distanceToEnd(view, orientationHelper, true);
    }

    private int distanceToEnd(View view, OrientationHelper orientationHelper, boolean z) {
        if (!this.isRtlHorizontal || z) {
            return orientationHelper.getDecoratedEnd(view) - orientationHelper.getEndAfterPadding();
        }
        return distanceToStart(view, orientationHelper, true);
    }

    private View findStartView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int i;
        int i2;
        float f;
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return null;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        boolean reverseLayout = linearLayoutManager.getReverseLayout();
        if (reverseLayout) {
            i = linearLayoutManager.findLastVisibleItemPosition();
        } else {
            i = linearLayoutManager.findFirstVisibleItemPosition();
        }
        boolean z = true;
        int spanCount = layoutManager instanceof GridLayoutManager ? (((GridLayoutManager) layoutManager).getSpanCount() - 1) + 1 : 1;
        if (i == -1) {
            return null;
        }
        View findViewByPosition = layoutManager.findViewByPosition(i);
        if (this.isRtlHorizontal) {
            f = (float) (orientationHelper.getTotalSpace() - orientationHelper.getDecoratedStart(findViewByPosition));
            i2 = orientationHelper.getDecoratedMeasurement(findViewByPosition);
        } else {
            f = (float) orientationHelper.getDecoratedEnd(findViewByPosition);
            i2 = orientationHelper.getDecoratedMeasurement(findViewByPosition);
        }
        float f2 = f / ((float) i2);
        if (reverseLayout ? linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0 : linearLayoutManager.findLastCompletelyVisibleItemPosition() != layoutManager.getItemCount() - 1) {
            z = false;
        }
        if (f2 > 0.5f && !z) {
            return findViewByPosition;
        }
        if (this.snapLastItem && z) {
            return findViewByPosition;
        }
        if (z) {
            return null;
        }
        if (reverseLayout) {
            return layoutManager.findViewByPosition(i - spanCount);
        }
        return layoutManager.findViewByPosition(i + spanCount);
    }

    private View findEndView(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int i;
        int i2;
        float f;
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return null;
        }
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
        boolean reverseLayout = linearLayoutManager.getReverseLayout();
        if (reverseLayout) {
            i = linearLayoutManager.findFirstVisibleItemPosition();
        } else {
            i = linearLayoutManager.findLastVisibleItemPosition();
        }
        boolean z = true;
        int spanCount = layoutManager instanceof GridLayoutManager ? (((GridLayoutManager) layoutManager).getSpanCount() - 1) + 1 : 1;
        if (i == -1) {
            return null;
        }
        View findViewByPosition = layoutManager.findViewByPosition(i);
        if (this.isRtlHorizontal) {
            f = (float) orientationHelper.getDecoratedEnd(findViewByPosition);
            i2 = orientationHelper.getDecoratedMeasurement(findViewByPosition);
        } else {
            f = (float) (orientationHelper.getTotalSpace() - orientationHelper.getDecoratedStart(findViewByPosition));
            i2 = orientationHelper.getDecoratedMeasurement(findViewByPosition);
        }
        float f2 = f / ((float) i2);
        if (reverseLayout ? linearLayoutManager.findLastCompletelyVisibleItemPosition() != layoutManager.getItemCount() - 1 : linearLayoutManager.findFirstCompletelyVisibleItemPosition() != 0) {
            z = false;
        }
        if (f2 > 0.5f && !z) {
            return findViewByPosition;
        }
        if (this.snapLastItem && z) {
            return findViewByPosition;
        }
        if (z) {
            return null;
        }
        if (reverseLayout) {
            return layoutManager.findViewByPosition(i + spanCount);
        }
        return layoutManager.findViewByPosition(i - spanCount);
    }

    /* access modifiers changed from: private */
    public int getSnappedPosition(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (!(layoutManager instanceof LinearLayoutManager)) {
            return -1;
        }
        int i = this.gravity;
        if (i == 8388611 || i == 48) {
            return ((LinearLayoutManager) layoutManager).findFirstCompletelyVisibleItemPosition();
        }
        if (i == 8388613 || i == 80) {
            return ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
        }
        return -1;
    }

    private OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.verticalHelper == null) {
            this.verticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.verticalHelper;
    }

    private OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        if (this.horizontalHelper == null) {
            this.horizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.horizontalHelper;
    }
}
