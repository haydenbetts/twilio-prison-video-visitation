package com.forasoft.homewavvisitor.presentation.adapter.visits;

import android.view.View;
import com.forasoft.homewavvisitor.model.data.DaySlot;
import com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter;
import kotlin.Metadata;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005¨\u0006\u0006"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick", "com/forasoft/homewavvisitor/presentation/adapter/visits/DayAdapter$DayVH$bind$1$6"}, k = 3, mv = {1, 1, 16})
/* compiled from: DayAdapter.kt */
final class DayAdapter$DayVH$bind$$inlined$with$lambda$1 implements View.OnClickListener {
    final /* synthetic */ LocalDateTime $day$inlined;
    final /* synthetic */ DaySlot $daySlot$inlined;
    final /* synthetic */ DayAdapter.DayVH this$0;

    DayAdapter$DayVH$bind$$inlined$with$lambda$1(DayAdapter.DayVH dayVH, LocalDateTime localDateTime, DaySlot daySlot) {
        this.this$0 = dayVH;
        this.$day$inlined = localDateTime;
        this.$daySlot$inlined = daySlot;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0042  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onClick(android.view.View r5) {
        /*
            r4 = this;
            com.forasoft.homewavvisitor.model.data.DaySlot r5 = r4.$daySlot$inlined
            if (r5 == 0) goto L_0x0050
            boolean r5 = r5.getCan_schedule()
            if (r5 == 0) goto L_0x0050
            com.forasoft.homewavvisitor.model.data.DaySlot r5 = r4.$daySlot$inlined
            java.util.List r5 = r5.getSlots()
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            boolean r0 = r5 instanceof java.util.Collection
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0023
            r0 = r5
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0023
        L_0x0021:
            r1 = 0
            goto L_0x0040
        L_0x0023:
            java.util.Iterator r5 = r5.iterator()
        L_0x0027:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x0021
            java.lang.Object r0 = r5.next()
            com.forasoft.homewavvisitor.model.data.TimeSlot r0 = (com.forasoft.homewavvisitor.model.data.TimeSlot) r0
            com.forasoft.homewavvisitor.model.data.TimeSlot$Status r0 = r0.getStatus()
            com.forasoft.homewavvisitor.model.data.TimeSlot$Status r3 = com.forasoft.homewavvisitor.model.data.TimeSlot.Status.FREE
            if (r0 != r3) goto L_0x003d
            r0 = 1
            goto L_0x003e
        L_0x003d:
            r0 = 0
        L_0x003e:
            if (r0 == 0) goto L_0x0027
        L_0x0040:
            if (r1 == 0) goto L_0x0050
            com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter$DayVH r5 = r4.this$0
            com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter r5 = r5.this$0
            kotlin.jvm.functions.Function1 r5 = r5.itemClickListener
            org.threeten.bp.LocalDateTime r0 = r4.$day$inlined
            r5.invoke(r0)
            goto L_0x005c
        L_0x0050:
            com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter$DayVH r5 = r4.this$0
            com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter r5 = r5.this$0
            kotlin.jvm.functions.Function1 r5 = r5.itemClickListener
            r0 = 0
            r5.invoke(r0)
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.adapter.visits.DayAdapter$DayVH$bind$$inlined$with$lambda$1.onClick(android.view.View):void");
    }
}
