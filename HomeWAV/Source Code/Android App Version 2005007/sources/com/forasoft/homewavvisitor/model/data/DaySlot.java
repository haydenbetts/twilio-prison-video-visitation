package com.forasoft.homewavvisitor.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0006HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0013\u0010\u0011\u001a\u00020\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\u0019\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/DaySlot;", "Landroid/os/Parcelable;", "slots", "", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "can_schedule", "", "(Ljava/util/List;Z)V", "getCan_schedule", "()Z", "getSlots", "()Ljava/util/List;", "component1", "component2", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: DaySlot.kt */
public final class DaySlot implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final boolean can_schedule;
    private final List<TimeSlot> slots;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            int readInt = parcel.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((TimeSlot) TimeSlot.CREATOR.createFromParcel(parcel));
                readInt--;
            }
            return new DaySlot(arrayList, parcel.readInt() != 0);
        }

        public final Object[] newArray(int i) {
            return new DaySlot[i];
        }
    }

    public static /* synthetic */ DaySlot copy$default(DaySlot daySlot, List<TimeSlot> list, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            list = daySlot.slots;
        }
        if ((i & 2) != 0) {
            z = daySlot.can_schedule;
        }
        return daySlot.copy(list, z);
    }

    public final List<TimeSlot> component1() {
        return this.slots;
    }

    public final boolean component2() {
        return this.can_schedule;
    }

    public final DaySlot copy(List<TimeSlot> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(list, "slots");
        return new DaySlot(list, z);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DaySlot)) {
            return false;
        }
        DaySlot daySlot = (DaySlot) obj;
        return Intrinsics.areEqual((Object) this.slots, (Object) daySlot.slots) && this.can_schedule == daySlot.can_schedule;
    }

    public int hashCode() {
        List<TimeSlot> list = this.slots;
        int hashCode = (list != null ? list.hashCode() : 0) * 31;
        boolean z = this.can_schedule;
        if (z) {
            z = true;
        }
        return hashCode + (z ? 1 : 0);
    }

    public String toString() {
        return "DaySlot(slots=" + this.slots + ", can_schedule=" + this.can_schedule + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        List<TimeSlot> list = this.slots;
        parcel.writeInt(list.size());
        for (TimeSlot writeToParcel : list) {
            writeToParcel.writeToParcel(parcel, 0);
        }
        parcel.writeInt(this.can_schedule ? 1 : 0);
    }

    public DaySlot(List<TimeSlot> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(list, "slots");
        this.slots = list;
        this.can_schedule = z;
    }

    public final List<TimeSlot> getSlots() {
        return this.slots;
    }

    public final boolean getCan_schedule() {
        return this.can_schedule;
    }
}
