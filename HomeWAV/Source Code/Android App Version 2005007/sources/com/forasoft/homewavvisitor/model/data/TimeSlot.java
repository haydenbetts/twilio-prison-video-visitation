package com.forasoft.homewavvisitor.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001:\u0001,B=\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\fJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\nHÆ\u0003¢\u0006\u0002\u0010\u0010JN\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0002\u0010\u001fJ\t\u0010 \u001a\u00020\nHÖ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010$HÖ\u0003J\t\u0010%\u001a\u00020\nHÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\u0019\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\nHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\t\u001a\u0004\u0018\u00010\n8\u0006X\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006X\u0004¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u0012\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006-"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "Landroid/os/Parcelable;", "start", "", "duration", "status", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot$Status;", "timestamp", "", "numReserved", "", "numStations", "(Ljava/lang/String;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/TimeSlot$Status;JLjava/lang/Integer;Ljava/lang/Integer;)V", "getDuration", "()Ljava/lang/String;", "getNumReserved", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getNumStations", "getStart", "getStatus", "()Lcom/forasoft/homewavvisitor/model/data/TimeSlot$Status;", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/TimeSlot$Status;JLjava/lang/Integer;Ljava/lang/Integer;)Lcom/forasoft/homewavvisitor/model/data/TimeSlot;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Status", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TimeSlot.kt */
public final class TimeSlot implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final String duration;
    @SerializedName("num_reserved")
    private final Integer numReserved;
    @SerializedName("num_stations")
    private final Integer numStations;
    private final String start;
    private final Status status;
    private final long timestamp;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new TimeSlot(parcel.readString(), parcel.readString(), (Status) Enum.valueOf(Status.class, parcel.readString()), parcel.readLong(), parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null, parcel.readInt() != 0 ? Integer.valueOf(parcel.readInt()) : null);
        }

        public final Object[] newArray(int i) {
            return new TimeSlot[i];
        }
    }

    public static /* synthetic */ TimeSlot copy$default(TimeSlot timeSlot, String str, String str2, Status status2, long j, Integer num, Integer num2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = timeSlot.start;
        }
        if ((i & 2) != 0) {
            str2 = timeSlot.duration;
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            status2 = timeSlot.status;
        }
        Status status3 = status2;
        if ((i & 8) != 0) {
            j = timeSlot.timestamp;
        }
        long j2 = j;
        if ((i & 16) != 0) {
            num = timeSlot.numReserved;
        }
        Integer num3 = num;
        if ((i & 32) != 0) {
            num2 = timeSlot.numStations;
        }
        return timeSlot.copy(str, str3, status3, j2, num3, num2);
    }

    public final String component1() {
        return this.start;
    }

    public final String component2() {
        return this.duration;
    }

    public final Status component3() {
        return this.status;
    }

    public final long component4() {
        return this.timestamp;
    }

    public final Integer component5() {
        return this.numReserved;
    }

    public final Integer component6() {
        return this.numStations;
    }

    public final TimeSlot copy(String str, String str2, Status status2, long j, Integer num, Integer num2) {
        Intrinsics.checkParameterIsNotNull(str, TtmlNode.START);
        Intrinsics.checkParameterIsNotNull(str2, "duration");
        Intrinsics.checkParameterIsNotNull(status2, NotificationCompat.CATEGORY_STATUS);
        return new TimeSlot(str, str2, status2, j, num, num2);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TimeSlot)) {
            return false;
        }
        TimeSlot timeSlot = (TimeSlot) obj;
        return Intrinsics.areEqual((Object) this.start, (Object) timeSlot.start) && Intrinsics.areEqual((Object) this.duration, (Object) timeSlot.duration) && Intrinsics.areEqual((Object) this.status, (Object) timeSlot.status) && this.timestamp == timeSlot.timestamp && Intrinsics.areEqual((Object) this.numReserved, (Object) timeSlot.numReserved) && Intrinsics.areEqual((Object) this.numStations, (Object) timeSlot.numStations);
    }

    public int hashCode() {
        String str = this.start;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.duration;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Status status2 = this.status;
        int hashCode3 = status2 != null ? status2.hashCode() : 0;
        long j = this.timestamp;
        int i2 = (((hashCode2 + hashCode3) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        Integer num = this.numReserved;
        int hashCode4 = (i2 + (num != null ? num.hashCode() : 0)) * 31;
        Integer num2 = this.numStations;
        if (num2 != null) {
            i = num2.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "TimeSlot(start=" + this.start + ", duration=" + this.duration + ", status=" + this.status + ", timestamp=" + this.timestamp + ", numReserved=" + this.numReserved + ", numStations=" + this.numStations + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.start);
        parcel.writeString(this.duration);
        parcel.writeString(this.status.name());
        parcel.writeLong(this.timestamp);
        Integer num = this.numReserved;
        if (num != null) {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        } else {
            parcel.writeInt(0);
        }
        Integer num2 = this.numStations;
        if (num2 != null) {
            parcel.writeInt(1);
            parcel.writeInt(num2.intValue());
            return;
        }
        parcel.writeInt(0);
    }

    public TimeSlot(String str, String str2, Status status2, long j, Integer num, Integer num2) {
        Intrinsics.checkParameterIsNotNull(str, TtmlNode.START);
        Intrinsics.checkParameterIsNotNull(str2, "duration");
        Intrinsics.checkParameterIsNotNull(status2, NotificationCompat.CATEGORY_STATUS);
        this.start = str;
        this.duration = str2;
        this.status = status2;
        this.timestamp = j;
        this.numReserved = num;
        this.numStations = num2;
    }

    public final String getStart() {
        return this.start;
    }

    public final String getDuration() {
        return this.duration;
    }

    public final Status getStatus() {
        return this.status;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ TimeSlot(String str, String str2, Status status2, long j, Integer num, Integer num2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, status2, j, (i & 16) != 0 ? null : num, (i & 32) != 0 ? null : num2);
    }

    public final Integer getNumReserved() {
        return this.numReserved;
    }

    public final Integer getNumStations() {
        return this.numStations;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0001\u0018\u0000 \b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/TimeSlot$Status;", "", "(Ljava/lang/String;I)V", "UNAVAILABLE", "FREE", "REQUESTED", "CONFIRMED", "RESERVED", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: TimeSlot.kt */
    public enum Status {
        UNAVAILABLE,
        FREE,
        REQUESTED,
        CONFIRMED,
        RESERVED;
        
        public static final Companion Companion = null;

        static {
            Companion = new Companion((DefaultConstructorMarker) null);
        }

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/TimeSlot$Status$Companion;", "", "()V", "getStatusByName", "Lcom/forasoft/homewavvisitor/model/data/TimeSlot$Status;", "name", "", "app_release"}, k = 1, mv = {1, 1, 16})
        /* compiled from: TimeSlot.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final Status getStatusByName(String str) {
                Intrinsics.checkParameterIsNotNull(str, "name");
                String upperCase = str.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                return Status.valueOf(upperCase);
            }
        }
    }
}
