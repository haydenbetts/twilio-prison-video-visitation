package com.forasoft.homewavvisitor.presentation.adapter.visits;

import air.HomeWAV.R;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.model.data.TimeSlot;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import kotlin.ranges.RangesKt;
import org.threeten.bp.DayOfWeek;
import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001aB/\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0014\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0002\u0010\u000bJ\u0014\u0010\u000e\u001a\u00020\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0010J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u001c\u0010\u0013\u001a\u00020\n2\n\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u001c\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0012H\u0016R\u001c\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/visits/MonthAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/forasoft/homewavvisitor/presentation/adapter/visits/MonthAdapter$MonthVH;", "slots", "", "Lorg/threeten/bp/LocalDate;", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "itemClickListener", "Lkotlin/Function1;", "Lorg/threeten/bp/LocalDateTime;", "", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "recycledViewPool", "Landroidx/recyclerview/widget/RecyclerView$RecycledViewPool;", "addSlots", "daySlots", "", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "MonthVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MonthAdapter.kt */
public final class MonthAdapter extends RecyclerView.Adapter<MonthVH> {
    /* access modifiers changed from: private */
    public final Function1<LocalDateTime, Unit> itemClickListener;
    /* access modifiers changed from: private */
    public final RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
    /* access modifiers changed from: private */
    public final Map<LocalDate, DaySlot> slots;

    public MonthAdapter(Map<LocalDate, DaySlot> map, Function1<? super LocalDateTime, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(map, "slots");
        Intrinsics.checkParameterIsNotNull(function1, "itemClickListener");
        this.slots = map;
        this.itemClickListener = function1;
    }

    public MonthVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new MonthVH(this, ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_month, false, 2, (Object) null));
    }

    public void onBindViewHolder(MonthVH monthVH, int i) {
        Intrinsics.checkParameterIsNotNull(monthVH, "holder");
        monthVH.bind(i);
    }

    public int getItemCount() {
        Iterable<LocalDate> keySet = this.slots.keySet();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(keySet, 10));
        for (LocalDate monthValue : keySet) {
            arrayList.add(Integer.valueOf(monthValue.getMonthValue()));
        }
        int size = CollectionsKt.toSet((List) arrayList).size();
        if (size == 0) {
            return 1;
        }
        return size;
    }

    public final void addSlots(List<DaySlot> list) {
        Intrinsics.checkParameterIsNotNull(list, "daySlots");
        for (DaySlot next : list) {
            if (!next.getSlots().isEmpty()) {
                Map linkedHashMap = new LinkedHashMap();
                for (Object next2 : next.getSlots()) {
                    LocalDate localDate = Instant.ofEpochMilli(((TimeSlot) next2).getTimestamp() * ((long) 1000)).atZone(ZoneId.systemDefault()).toLocalDate();
                    Intrinsics.checkExpressionValueIsNotNull(localDate, "Instant.ofEpochMilli(it.…           .toLocalDate()");
                    Integer valueOf = Integer.valueOf(localDate.getDayOfMonth());
                    Object obj = linkedHashMap.get(valueOf);
                    if (obj == null) {
                        obj = new ArrayList();
                        linkedHashMap.put(valueOf, obj);
                    }
                    ((List) obj).add(next2);
                }
                for (Map.Entry value : linkedHashMap.entrySet()) {
                    List list2 = (List) value.getValue();
                    LocalDate localDate2 = Instant.ofEpochMilli(((TimeSlot) CollectionsKt.first(list2)).getTimestamp() * ((long) 1000)).atZone(ZoneId.systemDefault()).toLocalDate();
                    if (this.slots.get(localDate2) == null) {
                        Map<LocalDate, DaySlot> map = this.slots;
                        Intrinsics.checkExpressionValueIsNotNull(localDate2, "day");
                        map.put(localDate2, new DaySlot(list2, true));
                    } else {
                        Map<LocalDate, DaySlot> map2 = this.slots;
                        Intrinsics.checkExpressionValueIsNotNull(localDate2, "day");
                        SpreadBuilder spreadBuilder = new SpreadBuilder(2);
                        DaySlot daySlot = this.slots.get(localDate2);
                        if (daySlot == null) {
                            Intrinsics.throwNpe();
                        }
                        Object[] array = daySlot.getSlots().toArray(new TimeSlot[0]);
                        if (array != null) {
                            spreadBuilder.addSpread(array);
                            Object[] array2 = list2.toArray(new TimeSlot[0]);
                            if (array2 != null) {
                                spreadBuilder.addSpread(array2);
                                map2.put(localDate2, new DaySlot(CollectionsKt.listOf((TimeSlot[]) spreadBuilder.toArray(new TimeSlot[spreadBuilder.size()])), true));
                            } else {
                                throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                            }
                        } else {
                            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                        }
                    }
                }
                continue;
            }
        }
        notifyDataSetChanged();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\u000bH\u0002¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/visits/MonthAdapter$MonthVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/visits/MonthAdapter;Landroid/view/View;)V", "bind", "", "position", "", "computeDaysForMonth", "", "Lorg/threeten/bp/LocalDateTime;", "month", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: MonthAdapter.kt */
    public final class MonthVH extends RecyclerView.ViewHolder {
        final /* synthetic */ MonthAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MonthVH(MonthAdapter monthAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = monthAdapter;
        }

        public final void bind(int i) {
            View view = this.itemView;
            LocalDateTime now = LocalDateTime.now();
            ((RecyclerView) view.findViewById(com.forasoft.homewavvisitor.R.id.rv_month)).setHasFixedSize(true);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(com.forasoft.homewavvisitor.R.id.rv_month);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView, "rv_month");
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 7));
            RecyclerView recyclerView2 = (RecyclerView) view.findViewById(com.forasoft.homewavvisitor.R.id.rv_month);
            Intrinsics.checkExpressionValueIsNotNull(recyclerView2, "rv_month");
            long j = (long) i;
            LocalDateTime plusMonths = now.plusMonths(j);
            Intrinsics.checkExpressionValueIsNotNull(plusMonths, "now.plusMonths(position.toLong())");
            LocalDateTime plusMonths2 = now.plusMonths(j);
            Intrinsics.checkExpressionValueIsNotNull(plusMonths2, "now.plusMonths(position.toLong())");
            recyclerView2.setAdapter(new DayAdapter(plusMonths, computeDaysForMonth(plusMonths2), this.this$0.slots, this.this$0.itemClickListener));
            ((RecyclerView) view.findViewById(com.forasoft.homewavvisitor.R.id.rv_month)).setRecycledViewPool(this.this$0.recycledViewPool);
        }

        private final List<LocalDateTime> computeDaysForMonth(LocalDateTime localDateTime) {
            int i;
            LocalDateTime minusDays = localDateTime.minusDays(((long) localDateTime.getDayOfMonth()) - 1);
            Intrinsics.checkExpressionValueIsNotNull(minusDays, TtmlNode.START);
            DayOfWeek dayOfWeek = minusDays.getDayOfWeek();
            Intrinsics.checkExpressionValueIsNotNull(dayOfWeek, "start.dayOfWeek");
            if (dayOfWeek.getValue() == DayOfWeek.SUNDAY.getValue()) {
                i = 0;
            } else {
                DayOfWeek dayOfWeek2 = minusDays.getDayOfWeek();
                Intrinsics.checkExpressionValueIsNotNull(dayOfWeek2, "start.dayOfWeek");
                i = dayOfWeek2.getValue();
            }
            LocalDateTime withDayOfMonth = localDateTime.withDayOfMonth(localDateTime.toLocalDate().lengthOfMonth());
            Intrinsics.checkExpressionValueIsNotNull(withDayOfMonth, TtmlNode.END);
            DayOfWeek dayOfWeek3 = withDayOfMonth.getDayOfWeek();
            Intrinsics.checkExpressionValueIsNotNull(dayOfWeek3, "end.dayOfWeek");
            int i2 = 6;
            if (dayOfWeek3.getValue() != DayOfWeek.SUNDAY.getValue()) {
                DayOfWeek dayOfWeek4 = withDayOfMonth.getDayOfWeek();
                Intrinsics.checkExpressionValueIsNotNull(dayOfWeek4, "end.dayOfWeek");
                i2 = 6 - dayOfWeek4.getValue();
            }
            LocalDateTime minusDays2 = minusDays.minusDays((long) i);
            List<LocalDateTime> arrayList = new ArrayList<>();
            Iterator it = RangesKt.until(0, localDateTime.toLocalDate().lengthOfMonth() + i + i2).iterator();
            while (it.hasNext()) {
                ((IntIterator) it).nextInt();
                Intrinsics.checkExpressionValueIsNotNull(minusDays2, "current");
                arrayList.add(minusDays2);
                minusDays2 = minusDays2.plusDays(1);
            }
            return arrayList;
        }
    }
}
