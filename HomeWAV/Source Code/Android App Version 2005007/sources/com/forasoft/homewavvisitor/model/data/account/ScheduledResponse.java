package com.forasoft.homewavvisitor.model.data.account;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/account/ScheduledResponse;", "", "scheduled", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "(Ljava/util/List;)V", "getScheduled", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ScheduledResponse.kt */
public final class ScheduledResponse {
    private final List<ScheduledVisit> scheduled;

    public static /* synthetic */ ScheduledResponse copy$default(ScheduledResponse scheduledResponse, List<ScheduledVisit> list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = scheduledResponse.scheduled;
        }
        return scheduledResponse.copy(list);
    }

    public final List<ScheduledVisit> component1() {
        return this.scheduled;
    }

    public final ScheduledResponse copy(List<ScheduledVisit> list) {
        Intrinsics.checkParameterIsNotNull(list, "scheduled");
        return new ScheduledResponse(list);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ScheduledResponse) && Intrinsics.areEqual((Object) this.scheduled, (Object) ((ScheduledResponse) obj).scheduled);
        }
        return true;
    }

    public int hashCode() {
        List<ScheduledVisit> list = this.scheduled;
        if (list != null) {
            return list.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ScheduledResponse(scheduled=" + this.scheduled + ")";
    }

    public ScheduledResponse(List<ScheduledVisit> list) {
        Intrinsics.checkParameterIsNotNull(list, "scheduled");
        this.scheduled = list;
    }

    public final List<ScheduledVisit> getScheduled() {
        return this.scheduled;
    }
}
