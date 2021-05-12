package com.forasoft.homewavvisitor.presentation.adapter.visits;

import air.HomeWAV.R;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.presentation.extensions.ContextExtensionsKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0018BE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\r0\f¢\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\r2\n\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0010H\u0016J\u001c\u0010\u0014\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010H\u0016R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\r0\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/visits/DayAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/forasoft/homewavvisitor/presentation/adapter/visits/DayAdapter$DayVH;", "month", "Lorg/threeten/bp/LocalDateTime;", "days", "", "slots", "", "Lorg/threeten/bp/LocalDate;", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "itemClickListener", "Lkotlin/Function1;", "", "(Lorg/threeten/bp/LocalDateTime;Ljava/util/List;Ljava/util/Map;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "DayVH", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DayAdapter.kt */
public final class DayAdapter extends RecyclerView.Adapter<DayVH> {
    private final List<LocalDateTime> days;
    /* access modifiers changed from: private */
    public final Function1<LocalDateTime, Unit> itemClickListener;
    private final LocalDateTime month;
    private final Map<LocalDate, DaySlot> slots;

    public DayAdapter(LocalDateTime localDateTime, List<LocalDateTime> list, Map<LocalDate, DaySlot> map, Function1<? super LocalDateTime, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(localDateTime, "month");
        Intrinsics.checkParameterIsNotNull(list, "days");
        Intrinsics.checkParameterIsNotNull(map, "slots");
        Intrinsics.checkParameterIsNotNull(function1, "itemClickListener");
        this.month = localDateTime;
        this.days = list;
        this.slots = map;
        this.itemClickListener = function1;
    }

    public DayVH onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        return new DayVH(this, ContextExtensionsKt.inflate$default(viewGroup, R.layout.item_day, false, 2, (Object) null));
    }

    public void onBindViewHolder(DayVH dayVH, int i) {
        Intrinsics.checkParameterIsNotNull(dayVH, "holder");
        dayVH.bind(this.days.get(i), this.slots.get(this.days.get(i).toLocalDate()));
    }

    public int getItemCount() {
        return this.days.size();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/adapter/visits/DayAdapter$DayVH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/forasoft/homewavvisitor/presentation/adapter/visits/DayAdapter;Landroid/view/View;)V", "bind", "", "day", "Lorg/threeten/bp/LocalDateTime;", "daySlot", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: DayAdapter.kt */
    public final class DayVH extends RecyclerView.ViewHolder {
        final /* synthetic */ DayAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public DayVH(DayAdapter dayAdapter, View view) {
            super(view);
            Intrinsics.checkParameterIsNotNull(view, "itemView");
            this.this$0 = dayAdapter;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: com.forasoft.homewavvisitor.model.data.TimeSlot} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v19, resolved type: com.forasoft.homewavvisitor.model.data.TimeSlot} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v3, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v39, resolved type: com.forasoft.homewavvisitor.model.data.TimeSlot} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v21, resolved type: com.forasoft.homewavvisitor.model.data.TimeSlot} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Removed duplicated region for block: B:105:0x033f  */
        /* JADX WARNING: Removed duplicated region for block: B:131:0x029f A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void bind(org.threeten.bp.LocalDateTime r14, com.forasoft.homewavvisitor.model.data.DaySlot r15) {
            /*
                r13 = this;
                java.lang.String r0 = "day"
                kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
                android.view.View r0 = r13.itemView
                int r1 = com.forasoft.homewavvisitor.R.id.tv_day_of_month
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                java.lang.String r2 = "tv_day_of_month"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                int r2 = r14.getDayOfMonth()
                java.lang.String r2 = java.lang.String.valueOf(r2)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                org.threeten.bp.LocalDateTime r1 = org.threeten.bp.LocalDateTime.now()
                java.lang.String r2 = "today"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
                int r2 = r1.getDayOfMonth()
                int r3 = r14.getDayOfMonth()
                if (r2 != r3) goto L_0x0061
                int r2 = r1.getMonthValue()
                int r3 = r14.getMonthValue()
                if (r2 != r3) goto L_0x0061
                int r1 = r1.getYear()
                int r2 = r14.getYear()
                if (r1 != r2) goto L_0x0061
                int r1 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                com.forasoft.homewavvisitor.extension.CommonKt.show(r1)
                int r1 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.ImageView r1 = (android.widget.ImageView) r1
                com.forasoft.homewavvisitor.extension.CommonKt.show(r1)
                goto L_0x0077
            L_0x0061:
                int r1 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                com.forasoft.homewavvisitor.extension.CommonKt.hide(r1)
                int r1 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.ImageView r1 = (android.widget.ImageView) r1
                com.forasoft.homewavvisitor.extension.CommonKt.hide(r1)
            L_0x0077:
                java.lang.String r1 = ""
                java.lang.String r2 = "#666666"
                java.lang.String r3 = "tv_notes"
                java.lang.String r4 = "iv_today"
                java.lang.String r5 = "null cannot be cast to non-null type android.graphics.drawable.LevelListDrawable"
                r6 = 0
                if (r15 != 0) goto L_0x00e4
                android.graphics.drawable.Drawable r7 = r0.getBackground()
                if (r7 == 0) goto L_0x00de
                android.graphics.drawable.LevelListDrawable r7 = (android.graphics.drawable.LevelListDrawable) r7
                r7.setLevel(r6)
                int r7 = com.forasoft.homewavvisitor.R.id.tv_day_of_month
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                java.lang.String r8 = "#48666666"
                int r8 = android.graphics.Color.parseColor(r8)
                r7.setTextColor(r8)
                int r7 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r7 = r0.findViewById(r7)
                android.widget.ImageView r7 = (android.widget.ImageView) r7
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r4)
                android.graphics.drawable.Drawable r4 = r7.getBackground()
                if (r4 == 0) goto L_0x00d8
                android.graphics.drawable.LevelListDrawable r4 = (android.graphics.drawable.LevelListDrawable) r4
                r4.setLevel(r6)
                int r4 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r4 = r0.findViewById(r4)
                android.widget.TextView r4 = (android.widget.TextView) r4
                int r2 = android.graphics.Color.parseColor(r2)
                r4.setTextColor(r2)
                int r2 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r2.setText(r1)
                goto L_0x03fb
            L_0x00d8:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x00de:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x00e4:
                java.util.List r7 = r15.getSlots()
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.ArrayList r8 = new java.util.ArrayList
                r9 = 10
                int r10 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r9)
                r8.<init>(r10)
                java.util.Collection r8 = (java.util.Collection) r8
                java.util.Iterator r7 = r7.iterator()
            L_0x00fb:
                boolean r10 = r7.hasNext()
                if (r10 == 0) goto L_0x010f
                java.lang.Object r10 = r7.next()
                com.forasoft.homewavvisitor.model.data.TimeSlot r10 = (com.forasoft.homewavvisitor.model.data.TimeSlot) r10
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r10 = r10.getStatus()
                r8.add(r10)
                goto L_0x00fb
            L_0x010f:
                java.util.List r8 = (java.util.List) r8
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r7 = com.forasoft.homewavvisitor.model.data.TimeSlot.Status.CONFIRMED
                boolean r7 = r8.contains(r7)
                r8 = 0
                r10 = -1
                r11 = 1
                if (r7 == 0) goto L_0x01ba
                android.graphics.drawable.Drawable r1 = r0.getBackground()
                if (r1 == 0) goto L_0x01b4
                android.graphics.drawable.LevelListDrawable r1 = (android.graphics.drawable.LevelListDrawable) r1
                r1.setLevel(r11)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_day_of_month
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
                java.util.List r2 = r15.getSlots()
                java.lang.Iterable r2 = (java.lang.Iterable) r2
                java.util.Iterator r2 = r2.iterator()
            L_0x015d:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x0178
                java.lang.Object r3 = r2.next()
                r7 = r3
                com.forasoft.homewavvisitor.model.data.TimeSlot r7 = (com.forasoft.homewavvisitor.model.data.TimeSlot) r7
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r7 = r7.getStatus()
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r9 = com.forasoft.homewavvisitor.model.data.TimeSlot.Status.CONFIRMED
                if (r7 != r9) goto L_0x0174
                r7 = 1
                goto L_0x0175
            L_0x0174:
                r7 = 0
            L_0x0175:
                if (r7 == 0) goto L_0x015d
                r8 = r3
            L_0x0178:
                if (r8 != 0) goto L_0x017d
                kotlin.jvm.internal.Intrinsics.throwNpe()
            L_0x017d:
                com.forasoft.homewavvisitor.model.data.TimeSlot r8 = (com.forasoft.homewavvisitor.model.data.TimeSlot) r8
                long r2 = r8.getTimestamp()
                r6 = 1000(0x3e8, float:1.401E-42)
                long r6 = (long) r6
                long r2 = r2 * r6
                java.util.Date r6 = new java.util.Date
                r6.<init>(r2)
                java.lang.String r2 = com.forasoft.homewavvisitor.presentation.extensions.DateExtensionsKt.getAsShortTime((java.util.Date) r6)
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                int r1 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.ImageView r1 = (android.widget.ImageView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                android.graphics.drawable.Drawable r1 = r1.getBackground()
                if (r1 == 0) goto L_0x01ae
                android.graphics.drawable.LevelListDrawable r1 = (android.graphics.drawable.LevelListDrawable) r1
                r1.setLevel(r11)
                goto L_0x03fb
            L_0x01ae:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x01b4:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x01ba:
                java.util.List r7 = r15.getSlots()
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.ArrayList r12 = new java.util.ArrayList
                int r9 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r9)
                r12.<init>(r9)
                java.util.Collection r12 = (java.util.Collection) r12
                java.util.Iterator r7 = r7.iterator()
            L_0x01cf:
                boolean r9 = r7.hasNext()
                if (r9 == 0) goto L_0x01e3
                java.lang.Object r9 = r7.next()
                com.forasoft.homewavvisitor.model.data.TimeSlot r9 = (com.forasoft.homewavvisitor.model.data.TimeSlot) r9
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r9 = r9.getStatus()
                r12.add(r9)
                goto L_0x01cf
            L_0x01e3:
                java.util.List r12 = (java.util.List) r12
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r7 = com.forasoft.homewavvisitor.model.data.TimeSlot.Status.REQUESTED
                boolean r7 = r12.contains(r7)
                if (r7 == 0) goto L_0x0250
                android.graphics.drawable.Drawable r1 = r0.getBackground()
                if (r1 == 0) goto L_0x024a
                android.graphics.drawable.LevelListDrawable r1 = (android.graphics.drawable.LevelListDrawable) r1
                r2 = 2
                r1.setLevel(r2)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_day_of_month
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
                java.lang.String r2 = "INVITE SENT"
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                int r1 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.ImageView r1 = (android.widget.ImageView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                android.graphics.drawable.Drawable r1 = r1.getBackground()
                if (r1 == 0) goto L_0x0244
                android.graphics.drawable.LevelListDrawable r1 = (android.graphics.drawable.LevelListDrawable) r1
                r1.setLevel(r11)
                goto L_0x03fb
            L_0x0244:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x024a:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x0250:
                java.util.List r7 = r15.getSlots()
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                boolean r9 = r7 instanceof java.util.Collection
                if (r9 == 0) goto L_0x0265
                r9 = r7
                java.util.Collection r9 = (java.util.Collection) r9
                boolean r9 = r9.isEmpty()
                if (r9 == 0) goto L_0x0265
            L_0x0263:
                r7 = 1
                goto L_0x02a0
            L_0x0265:
                java.util.Iterator r7 = r7.iterator()
            L_0x0269:
                boolean r9 = r7.hasNext()
                if (r9 == 0) goto L_0x0263
                java.lang.Object r9 = r7.next()
                com.forasoft.homewavvisitor.model.data.TimeSlot r9 = (com.forasoft.homewavvisitor.model.data.TimeSlot) r9
                java.lang.Integer r12 = r9.getNumReserved()
                if (r12 == 0) goto L_0x0288
                java.lang.Integer r9 = r9.getNumStations()
            L_0x027f:
                boolean r9 = r12.equals(r9)
                java.lang.Boolean r9 = java.lang.Boolean.valueOf(r9)
                goto L_0x0294
            L_0x0288:
                java.lang.Integer r12 = r9.getNumStations()
                if (r12 == 0) goto L_0x0293
                java.lang.Integer r9 = r9.getNumReserved()
                goto L_0x027f
            L_0x0293:
                r9 = r8
            L_0x0294:
                java.lang.Boolean r12 = java.lang.Boolean.valueOf(r6)
                boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r12)
                r9 = r9 ^ r11
                if (r9 != 0) goto L_0x0269
                r7 = 0
            L_0x02a0:
                if (r7 == 0) goto L_0x0305
                android.graphics.drawable.Drawable r1 = r0.getBackground()
                if (r1 == 0) goto L_0x02ff
                android.graphics.drawable.LevelListDrawable r1 = (android.graphics.drawable.LevelListDrawable) r1
                r2 = 3
                r1.setLevel(r2)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_day_of_month
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                r1.setTextColor(r10)
                int r1 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r1 = r0.findViewById(r1)
                android.widget.TextView r1 = (android.widget.TextView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
                java.lang.String r2 = "DAY FULL"
                java.lang.CharSequence r2 = (java.lang.CharSequence) r2
                r1.setText(r2)
                int r1 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r1 = r0.findViewById(r1)
                android.widget.ImageView r1 = (android.widget.ImageView) r1
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r4)
                android.graphics.drawable.Drawable r1 = r1.getBackground()
                if (r1 == 0) goto L_0x02f9
                android.graphics.drawable.LevelListDrawable r1 = (android.graphics.drawable.LevelListDrawable) r1
                r1.setLevel(r11)
                goto L_0x03fb
            L_0x02f9:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x02ff:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x0305:
                boolean r7 = r15.getCan_schedule()
                if (r7 == 0) goto L_0x03aa
                java.util.List r7 = r15.getSlots()
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                boolean r8 = r7 instanceof java.util.Collection
                if (r8 == 0) goto L_0x0320
                r8 = r7
                java.util.Collection r8 = (java.util.Collection) r8
                boolean r8 = r8.isEmpty()
                if (r8 == 0) goto L_0x0320
            L_0x031e:
                r11 = 0
                goto L_0x033d
            L_0x0320:
                java.util.Iterator r7 = r7.iterator()
            L_0x0324:
                boolean r8 = r7.hasNext()
                if (r8 == 0) goto L_0x031e
                java.lang.Object r8 = r7.next()
                com.forasoft.homewavvisitor.model.data.TimeSlot r8 = (com.forasoft.homewavvisitor.model.data.TimeSlot) r8
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r8 = r8.getStatus()
                com.forasoft.homewavvisitor.model.data.TimeSlot$Status r9 = com.forasoft.homewavvisitor.model.data.TimeSlot.Status.FREE
                if (r8 != r9) goto L_0x033a
                r8 = 1
                goto L_0x033b
            L_0x033a:
                r8 = 0
            L_0x033b:
                if (r8 == 0) goto L_0x0324
            L_0x033d:
                if (r11 == 0) goto L_0x03aa
                android.graphics.drawable.Drawable r7 = r0.getBackground()
                if (r7 == 0) goto L_0x03a4
                android.graphics.drawable.LevelListDrawable r7 = (android.graphics.drawable.LevelListDrawable) r7
                r7.setLevel(r6)
                int r7 = com.forasoft.homewavvisitor.R.id.tv_day_of_month
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                int r8 = android.graphics.Color.parseColor(r2)
                r7.setTextColor(r8)
                int r7 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                int r8 = android.graphics.Color.parseColor(r2)
                r7.setTextColor(r8)
                int r7 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                int r2 = android.graphics.Color.parseColor(r2)
                r7.setTextColor(r2)
                int r2 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r2 = r0.findViewById(r2)
                android.widget.ImageView r2 = (android.widget.ImageView) r2
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
                android.graphics.drawable.Drawable r2 = r2.getBackground()
                if (r2 == 0) goto L_0x039e
                android.graphics.drawable.LevelListDrawable r2 = (android.graphics.drawable.LevelListDrawable) r2
                r2.setLevel(r6)
                int r2 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r2.setText(r1)
                goto L_0x03fb
            L_0x039e:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x03a4:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x03aa:
                android.graphics.drawable.Drawable r7 = r0.getBackground()
                if (r7 == 0) goto L_0x040c
                android.graphics.drawable.LevelListDrawable r7 = (android.graphics.drawable.LevelListDrawable) r7
                r7.setLevel(r6)
                int r7 = com.forasoft.homewavvisitor.R.id.tv_day_of_month
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                java.lang.String r8 = "#C1C1C1"
                int r8 = android.graphics.Color.parseColor(r8)
                r7.setTextColor(r8)
                int r7 = com.forasoft.homewavvisitor.R.id.tv_today
                android.view.View r7 = r0.findViewById(r7)
                android.widget.TextView r7 = (android.widget.TextView) r7
                int r2 = android.graphics.Color.parseColor(r2)
                r7.setTextColor(r2)
                int r2 = com.forasoft.homewavvisitor.R.id.iv_today
                android.view.View r2 = r0.findViewById(r2)
                android.widget.ImageView r2 = (android.widget.ImageView) r2
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r4)
                android.graphics.drawable.Drawable r2 = r2.getBackground()
                if (r2 == 0) goto L_0x0406
                android.graphics.drawable.LevelListDrawable r2 = (android.graphics.drawable.LevelListDrawable) r2
                r2.setLevel(r6)
                int r2 = com.forasoft.homewavvisitor.R.id.tv_notes
                android.view.View r2 = r0.findViewById(r2)
                android.widget.TextView r2 = (android.widget.TextView) r2
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                java.lang.CharSequence r1 = (java.lang.CharSequence) r1
                r2.setText(r1)
            L_0x03fb:
                com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter$DayVH$bind$$inlined$with$lambda$1 r1 = new com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter$DayVH$bind$$inlined$with$lambda$1
                r1.<init>(r13, r14, r15)
                android.view.View$OnClickListener r1 = (android.view.View.OnClickListener) r1
                r0.setOnClickListener(r1)
                return
            L_0x0406:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            L_0x040c:
                kotlin.TypeCastException r14 = new kotlin.TypeCastException
                r14.<init>(r5)
                throw r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter.DayVH.bind(org.threeten.bp.LocalDateTime, com.forasoft.homewavvisitor.model.data.DaySlot):void");
        }
    }
}
