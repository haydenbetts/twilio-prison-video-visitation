package com.forasoft.homewavvisitor.ui.fragment.conversations;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.GestureDetector;
import android.view.View;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.presentation.extensions.DimensionsKt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bytedeco.opencv.global.opencv_tracking;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000y\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0015\b&\u0018\u00002\u00020\u0001:\u0002:;B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J6\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u000b0%2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\u001cH\u0002J\u0010\u0010(\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001cH\u0016J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010+\u001a\u00020,H\u0016J\u0010\u0010-\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001cH\u0016J\u001e\u0010.\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J@\u00100\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,2\u0006\u0010'\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\u001c2\u0006\u00102\u001a\u00020\b2\u0006\u00103\u001a\u000204H\u0016J \u00105\u001a\u0002042\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010+\u001a\u00020,2\u0006\u00106\u001a\u00020,H\u0016J\u0018\u00107\u001a\u00020\u001f2\u0006\u0010+\u001a\u00020,2\u0006\u00108\u001a\u00020\bH\u0016J\b\u00109\u001a\u00020\u001fH\u0002R\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u0002\n\u0000R \u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u0015X\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000¨\u0006<"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack;", "Landroidx/recyclerview/widget/ItemTouchHelper$SimpleCallback;", "context", "Landroid/content/Context;", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "(Landroid/content/Context;Landroidx/recyclerview/widget/RecyclerView;)V", "buttonWidth", "", "buttons", "Ljava/util/ArrayList;", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$UnderlayButton;", "buttonsBuffer", "Ljava/util/HashMap;", "gestureDetector", "Landroid/view/GestureDetector;", "gestureListener", "Landroid/view/GestureDetector$SimpleOnGestureListener;", "onTouchListener", "Landroid/view/View$OnTouchListener;", "recoverQueue", "com/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$recoverQueue$1", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$recoverQueue$1;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "swipeThreshold", "", "swipedPos", "drawButtons", "", "c", "Landroid/graphics/Canvas;", "itemView", "Landroid/view/View;", "buffer", "", "position", "dX", "getSwipeEscapeVelocity", "defaultValue", "getSwipeThreshold", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "getSwipeVelocityThreshold", "instantiateUnderlayButton", "underlayButtons", "onChildDraw", "dY", "actionState", "isCurrentlyActive", "", "onMove", "target", "onSwiped", "direction", "recoverSwipedItem", "UnderlayButton", "UnderlayButtonClickListener", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SwipeCallBack.kt */
public abstract class SwipeCallBack extends ItemTouchHelper.SimpleCallback {
    private final int buttonWidth = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    /* access modifiers changed from: private */
    public ArrayList<UnderlayButton> buttons = new ArrayList<>();
    private final HashMap<Integer, ArrayList<UnderlayButton>> buttonsBuffer = new HashMap<>();
    /* access modifiers changed from: private */
    public final GestureDetector gestureDetector;
    private final GestureDetector.SimpleOnGestureListener gestureListener;
    private final View.OnTouchListener onTouchListener;
    /* access modifiers changed from: private */
    public final SwipeCallBack$recoverQueue$1 recoverQueue = new SwipeCallBack$recoverQueue$1();
    private RecyclerView recyclerView;
    private float swipeThreshold = 0.5f;
    /* access modifiers changed from: private */
    public int swipedPos = -1;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$UnderlayButtonClickListener;", "", "onClick", "", "position", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: SwipeCallBack.kt */
    public interface UnderlayButtonClickListener {
        void onClick(int i);
    }

    public float getSwipeEscapeVelocity(float f) {
        return f * 0.1f;
    }

    public float getSwipeVelocityThreshold(float f) {
        return f * 5.0f;
    }

    public abstract void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, ArrayList<UnderlayButton> arrayList);

    public boolean onMove(RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
        Intrinsics.checkParameterIsNotNull(recyclerView2, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        Intrinsics.checkParameterIsNotNull(viewHolder2, "target");
        return false;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SwipeCallBack(Context context, RecyclerView recyclerView2) {
        super(0, 4);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(recyclerView2, "recyclerView");
        this.recyclerView = recyclerView2;
        GestureDetector.SimpleOnGestureListener swipeCallBack$gestureListener$1 = new SwipeCallBack$gestureListener$1(this);
        this.gestureListener = swipeCallBack$gestureListener$1;
        this.gestureDetector = new GestureDetector(context, swipeCallBack$gestureListener$1);
        View.OnTouchListener swipeCallBack$onTouchListener$1 = new SwipeCallBack$onTouchListener$1(this);
        this.onTouchListener = swipeCallBack$onTouchListener$1;
        this.recyclerView.setOnTouchListener(swipeCallBack$onTouchListener$1);
    }

    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public final void setRecyclerView(RecyclerView recyclerView2) {
        Intrinsics.checkParameterIsNotNull(recyclerView2, "<set-?>");
        this.recyclerView = recyclerView2;
    }

    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        int adapterPosition = viewHolder.getAdapterPosition();
        int i2 = this.swipedPos;
        if (i2 != adapterPosition) {
            this.recoverQueue.add(i2);
        }
        this.swipedPos = adapterPosition;
        if (this.buttonsBuffer.containsKey(Integer.valueOf(adapterPosition))) {
            ArrayList<UnderlayButton> arrayList = this.buttonsBuffer.get(Integer.valueOf(this.swipedPos));
            if (arrayList == null) {
                Intrinsics.throwNpe();
            }
            this.buttons = arrayList;
        } else {
            this.buttons.clear();
        }
        this.buttonsBuffer.clear();
        this.swipeThreshold = ((float) this.buttons.size()) * 0.5f * ((float) this.buttonWidth);
        recoverSwipedItem();
    }

    /* access modifiers changed from: private */
    public final void recoverSwipedItem() {
        Integer num;
        while (!this.recoverQueue.isEmpty() && (num = (Integer) this.recoverQueue.poll()) != null) {
            int intValue = num.intValue();
            RecyclerView.Adapter adapter = this.recyclerView.getAdapter();
            if (adapter != null) {
                adapter.notifyItemChanged(intValue);
            }
        }
    }

    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        return this.swipeThreshold;
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView2, RecyclerView.ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        float f3;
        Canvas canvas2 = canvas;
        Intrinsics.checkParameterIsNotNull(canvas, "c");
        RecyclerView recyclerView3 = recyclerView2;
        Intrinsics.checkParameterIsNotNull(recyclerView2, "recyclerView");
        Intrinsics.checkParameterIsNotNull(viewHolder, "viewHolder");
        int adapterPosition = viewHolder.getAdapterPosition();
        View view = viewHolder.itemView;
        Intrinsics.checkExpressionValueIsNotNull(view, "viewHolder.itemView");
        if (adapterPosition < 0) {
            this.swipedPos = adapterPosition;
            return;
        }
        if (i != 1 || f >= ((float) 0)) {
            f3 = f;
        } else {
            ArrayList arrayList = new ArrayList();
            if (this.buttonsBuffer.containsKey(Integer.valueOf(adapterPosition))) {
                ArrayList<UnderlayButton> arrayList2 = this.buttonsBuffer.get(Integer.valueOf(adapterPosition));
                if (arrayList2 == null) {
                    Intrinsics.throwNpe();
                }
                arrayList = arrayList2;
            } else {
                instantiateUnderlayButton(viewHolder, arrayList);
                this.buttonsBuffer.put(Integer.valueOf(adapterPosition), arrayList);
            }
            float size = ((((float) arrayList.size()) * f) * ((float) this.buttonWidth)) / ((float) view.getWidth());
            drawButtons(canvas, view, arrayList, adapterPosition, size);
            f3 = size;
        }
        super.onChildDraw(canvas, recyclerView2, viewHolder, f3, f2, i, z);
    }

    private final void drawButtons(Canvas canvas, View view, List<UnderlayButton> list, int i, float f) {
        float right = (float) view.getRight();
        float size = (((float) -1) * f) / ((float) list.size());
        for (UnderlayButton draw : list) {
            float f2 = right - size;
            draw.draw(canvas, new RectF(f2, (float) view.getTop(), right, (float) view.getBottom()), i);
            right = f2;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u001e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$UnderlayButton;", "", "text", "", "bitmap", "Landroid/graphics/Bitmap;", "color", "", "clickListener", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$UnderlayButtonClickListener;", "(Ljava/lang/String;Landroid/graphics/Bitmap;ILcom/forasoft/homewavvisitor/ui/fragment/conversations/SwipeCallBack$UnderlayButtonClickListener;)V", "clickRegion", "Landroid/graphics/RectF;", "position", "draw", "", "c", "Landroid/graphics/Canvas;", "rect", "onClick", "", "x", "", "y", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: SwipeCallBack.kt */
    public static final class UnderlayButton {
        private final Bitmap bitmap;
        private final UnderlayButtonClickListener clickListener;
        private RectF clickRegion;
        private final int color;
        private int position;
        private final String text;

        public UnderlayButton(String str, Bitmap bitmap2, int i, UnderlayButtonClickListener underlayButtonClickListener) {
            Intrinsics.checkParameterIsNotNull(str, "text");
            Intrinsics.checkParameterIsNotNull(bitmap2, "bitmap");
            Intrinsics.checkParameterIsNotNull(underlayButtonClickListener, "clickListener");
            this.text = str;
            this.bitmap = bitmap2;
            this.color = i;
            this.clickListener = underlayButtonClickListener;
        }

        public final boolean onClick(float f, float f2) {
            RectF rectF = this.clickRegion;
            if (rectF == null) {
                return false;
            }
            if (rectF == null) {
                Intrinsics.throwNpe();
            }
            if (!rectF.contains(f, f2)) {
                return false;
            }
            this.clickListener.onClick(this.position);
            return true;
        }

        public final void draw(Canvas canvas, RectF rectF, int i) {
            Intrinsics.checkParameterIsNotNull(canvas, "c");
            Intrinsics.checkParameterIsNotNull(rectF, opencv_tracking.CC_RECT);
            Paint paint = new Paint();
            paint.setColor(this.color);
            canvas.drawRect(rectF, paint);
            paint.setColor(-1);
            paint.setTextSize((float) DimensionsKt.dpToPx(14));
            float measureText = paint.measureText(this.text);
            Rect rect = new Rect();
            String str = this.text;
            paint.getTextBounds(str, 0, str.length(), rect);
            float f = (float) 2;
            float height = ((((float) this.bitmap.getHeight()) + ((float) DimensionsKt.dpToPx(8))) + ((float) rect.height())) / f;
            canvas.drawBitmap(this.bitmap, rectF.centerX() - ((float) (this.bitmap.getWidth() / 2)), rectF.centerY() - height, (Paint) null);
            canvas.drawText(this.text, rectF.centerX() - (measureText / f), rectF.centerY() + height, paint);
            this.clickRegion = rectF;
            this.position = i;
        }
    }
}
