package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u00010\u00060\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/view/View;", "kotlin.jvm.PlatformType", "event", "Landroid/view/MotionEvent;", "onTouch"}, k = 3, mv = {1, 1, 16})
/* compiled from: SwipeCallBack.kt */
final class SwipeCallBack$onTouchListener$1 implements View.OnTouchListener {
    final /* synthetic */ SwipeCallBack this$0;

    SwipeCallBack$onTouchListener$1(SwipeCallBack swipeCallBack) {
        this.this$0 = swipeCallBack;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.this$0.swipedPos < 0) {
            return false;
        }
        Intrinsics.checkExpressionValueIsNotNull(motionEvent, "event");
        Point point = new Point((int) motionEvent.getRawX(), (int) motionEvent.getRawY());
        RecyclerView.ViewHolder findViewHolderForAdapterPosition = this.this$0.getRecyclerView().findViewHolderForAdapterPosition(this.this$0.swipedPos);
        View view2 = findViewHolderForAdapterPosition != null ? findViewHolderForAdapterPosition.itemView : null;
        Rect rect = new Rect();
        if (view2 != null) {
            view2.getGlobalVisibleRect(rect);
        }
        if (motionEvent.getAction() == 0 || motionEvent.getAction() == 1 || motionEvent.getAction() == 2) {
            if (rect.top >= point.y || rect.bottom <= point.y) {
                this.this$0.recoverQueue.add(this.this$0.swipedPos);
                this.this$0.swipedPos = -1;
                this.this$0.recoverSwipedItem();
            } else {
                this.this$0.gestureDetector.onTouchEvent(motionEvent);
            }
        }
        return false;
    }
}
