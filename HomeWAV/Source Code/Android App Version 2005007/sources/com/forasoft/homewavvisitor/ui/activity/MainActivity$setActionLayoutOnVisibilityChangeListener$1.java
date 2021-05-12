package com.forasoft.homewavvisitor.ui.activity;

import android.view.ViewTreeObserver;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.forasoft.homewavvisitor.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "onGlobalLayout"}, k = 3, mv = {1, 1, 16})
/* compiled from: MainActivity.kt */
final class MainActivity$setActionLayoutOnVisibilityChangeListener$1 implements ViewTreeObserver.OnGlobalLayoutListener {
    final /* synthetic */ MainActivity this$0;

    MainActivity$setActionLayoutOnVisibilityChangeListener$1(MainActivity mainActivity) {
        this.this$0 = mainActivity;
    }

    public final void onGlobalLayout() {
        ConstraintLayout constraintLayout = (ConstraintLayout) this.this$0._$_findCachedViewById(R.id.layout_action);
        Intrinsics.checkExpressionValueIsNotNull(constraintLayout, "layout_action");
        int visibility = constraintLayout.getVisibility();
        if (visibility != this.this$0.actionLayoutVisibility) {
            this.this$0.actionLayoutVisibility = visibility;
            if (visibility == 0) {
                CommonKt.hide((BottomNavigationViewEx) this.this$0._$_findCachedViewById(R.id.bnv_main));
                return;
            }
            CommonKt.show((BottomNavigationViewEx) this.this$0._$_findCachedViewById(R.id.bnv_main));
            BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) this.this$0._$_findCachedViewById(R.id.bnv_main);
            Intrinsics.checkExpressionValueIsNotNull(bottomNavigationViewEx, "bnv_main");
            bottomNavigationViewEx.setSelectedItemId(this.this$0.currentSelectedAction);
        }
    }
}
