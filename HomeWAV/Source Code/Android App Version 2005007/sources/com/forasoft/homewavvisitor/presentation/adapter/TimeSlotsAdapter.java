package com.forasoft.homewavvisitor.presentation.adapter;

import air.HomeWAV.R;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.TimeSlot;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt;
import java.util.Date;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.sdk27.coroutines.Sdk27CoroutinesListenersWithCoroutinesKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002\u0012\u0013B\u0019\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\u001c\u0010\u000b\u001a\u00020\u00072\n\u0010\f\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\r\u001a\u00020\nH\u0016J\u001c\u0010\u000e\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/TimeSlotsAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "Lcom/forasoft/homewavvisitor/presentation/adapter/TimeSlotsAdapter$TimeSlotVH;", "onClickListener", "Lkotlin/Function1;", "", "", "(Lkotlin/jvm/functions/Function1;)V", "selectedPosition", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "SLOT_COMPARATOR", "TimeSlotVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TimeSlotsAdapter.kt */
public final class TimeSlotsAdapter extends ListAdapter<TimeSlot, TimeSlotVH> {
    /* access modifiers changed from: private */
    public final Function1<Long, Unit> onClickListener;
    /* access modifiers changed from: private */
    public int selectedPosition = -1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public TimeSlotsAdapter(Function1<? super Long, Unit> function1) {
        super(SLOT_COMPARATOR.INSTANCE);
        Intrinsics.checkParameterIsNotNull(function1, "onClickListener");
        this.onClickListener = function1;
    }

    public TimeSlotVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new TimeSlotVH(this, ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_slot, false, 2, (Object) null));
    }

    public void onBindViewHolder(TimeSlotVH timeSlotVH, int i) {
        Intrinsics.checkParameterIsNotNull(timeSlotVH, "holder");
        Object item = getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(item, "getItem(position)");
        timeSlotVH.bindSlot((TimeSlot) item);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/TimeSlotsAdapter$TimeSlotVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/TimeSlotsAdapter;Landroid/view/View;)V", "bindSlot", "", "timeSlot", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TimeSlotsAdapter.kt */
    public final class TimeSlotVH extends RecyclerView.ViewHolder {
        final /* synthetic */ TimeSlotsAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TimeSlotVH(TimeSlotsAdapter timeSlotsAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = timeSlotsAdapter;
        }

        public final void bindSlot(TimeSlot timeSlot) {
            Intrinsics.checkParameterIsNotNull(timeSlot, "timeSlot");
            View view = this.itemView;
            if (getAdapterPosition() == this.this$0.selectedPosition) {
                TextView textView = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_time);
                Intrinsics.checkExpressionValueIsNotNull(textView, "tv_time");
                textView.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bg_slot_active));
                ((TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_time)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.white));
                CommonKt.show((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_done));
            } else {
                TextView textView2 = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_time);
                Intrinsics.checkExpressionValueIsNotNull(textView2, "tv_time");
                textView2.setBackground(ContextCompat.getDrawable(view.getContext(), R.drawable.bg_slot_inactive));
                ((TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_time)).setTextColor(ContextCompat.getColor(view.getContext(), R.color.stepperColor));
                CommonKt.hide((ImageView) view.findViewById(com.forasoft.homewavvisitor.R.id.iv_done));
            }
            TextView textView3 = (TextView) view.findViewById(com.forasoft.homewavvisitor.R.id.tv_time);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "tv_time");
            textView3.setText(DateExtensionsKt.getAsShortTime(new Date(timeSlot.getTimestamp() * ((long) 1000))));
            Sdk27CoroutinesListenersWithCoroutinesKt.onClick$default(view, (CoroutineContext) null, new TimeSlotsAdapter$TimeSlotVH$bindSlot$$inlined$with$lambda$1((Continuation) null, this, timeSlot), 1, (Object) null);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/TimeSlotsAdapter$SLOT_COMPARATOR;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "()V", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TimeSlotsAdapter.kt */
    public static final class SLOT_COMPARATOR extends DiffUtil.ItemCallback<TimeSlot> {
        public static final SLOT_COMPARATOR INSTANCE = new SLOT_COMPARATOR();

        private SLOT_COMPARATOR() {
        }

        public boolean areItemsTheSame(TimeSlot timeSlot, TimeSlot timeSlot2) {
            Intrinsics.checkParameterIsNotNull(timeSlot, "oldItem");
            Intrinsics.checkParameterIsNotNull(timeSlot2, "newItem");
            return Intrinsics.areEqual((Object) timeSlot.getStart(), (Object) timeSlot2.getStart());
        }

        public boolean areContentsTheSame(TimeSlot timeSlot, TimeSlot timeSlot2) {
            Intrinsics.checkParameterIsNotNull(timeSlot, "oldItem");
            Intrinsics.checkParameterIsNotNull(timeSlot2, "newItem");
            return Intrinsics.areEqual((Object) timeSlot, (Object) timeSlot2);
        }
    }
}
