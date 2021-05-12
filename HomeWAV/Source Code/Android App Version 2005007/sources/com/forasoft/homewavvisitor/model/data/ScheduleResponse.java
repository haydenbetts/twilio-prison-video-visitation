package com.forasoft.homewavvisitor.model.data;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/ScheduleResponse;", "", "schedule", "", "Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "(Ljava/util/List;)V", "getSchedule", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ScheduleResponse.kt */
public final class ScheduleResponse {
    private final List<DaySlot> schedule;

    public static /* synthetic */ ScheduleResponse copy$default(ScheduleResponse scheduleResponse, List<DaySlot> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = scheduleResponse.schedule;
        }
        return scheduleResponse.copy(list);
    }

    public final List<DaySlot> component1() {
        return this.schedule;
    }

    public final ScheduleResponse copy(List<DaySlot> list) {
        Intrinsics.checkParameterIsNotNull(list, "schedule");
        return new ScheduleResponse(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ScheduleResponse) && Intrinsics.areEqual((Object) this.schedule, (Object) ((ScheduleResponse) obj).schedule);
        }
        return true;
    }

    public int hashCode() {
        List<DaySlot> list = this.schedule;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ScheduleResponse(schedule=" + this.schedule + ")";
    }

    public ScheduleResponse(List<DaySlot> list) {
        Intrinsics.checkParameterIsNotNull(list, "schedule");
        this.schedule = list;
    }

    public final List<DaySlot> getSchedule() {
        return this.schedule;
    }
}
