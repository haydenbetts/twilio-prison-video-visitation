package com.forasoft.homewavvisitor.model.data.account;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\tHÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003JY\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006&"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "", "slotId", "", "inmateId", "inmate", "facility", "station", "timestamp", "", "timezone", "status", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;)V", "getFacility", "()Ljava/lang/String;", "getInmate", "getInmateId", "getSlotId", "getStation", "getStatus", "getTimestamp", "()J", "getTimezone", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ScheduledVisit.kt */
public final class ScheduledVisit {
    private final String facility;
    private final String inmate;
    @SerializedName("inmate_id")
    private final String inmateId;
    private final String slotId;
    private final String station;
    private final String status;
    private final long timestamp;
    private final String timezone;

    public static /* synthetic */ ScheduledVisit copy$default(ScheduledVisit scheduledVisit, String str, String str2, String str3, String str4, String str5, long j, String str6, String str7, int i, Object obj) {
        ScheduledVisit scheduledVisit2 = scheduledVisit;
        int i2 = i;
        return scheduledVisit.copy((i2 & 1) != 0 ? scheduledVisit2.slotId : str, (i2 & 2) != 0 ? scheduledVisit2.inmateId : str2, (i2 & 4) != 0 ? scheduledVisit2.inmate : str3, (i2 & 8) != 0 ? scheduledVisit2.facility : str4, (i2 & 16) != 0 ? scheduledVisit2.station : str5, (i2 & 32) != 0 ? scheduledVisit2.timestamp : j, (i2 & 64) != 0 ? scheduledVisit2.timezone : str6, (i2 & 128) != 0 ? scheduledVisit2.status : str7);
    }

    public final String component1() {
        return this.slotId;
    }

    public final String component2() {
        return this.inmateId;
    }

    public final String component3() {
        return this.inmate;
    }

    public final String component4() {
        return this.facility;
    }

    public final String component5() {
        return this.station;
    }

    public final long component6() {
        return this.timestamp;
    }

    public final String component7() {
        return this.timezone;
    }

    public final String component8() {
        return this.status;
    }

    public final ScheduledVisit copy(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7) {
        Intrinsics.checkParameterIsNotNull(str, "slotId");
        Intrinsics.checkParameterIsNotNull(str2, "inmateId");
        Intrinsics.checkParameterIsNotNull(str3, "inmate");
        Intrinsics.checkParameterIsNotNull(str4, "facility");
        String str8 = str5;
        Intrinsics.checkParameterIsNotNull(str8, "station");
        String str9 = str6;
        Intrinsics.checkParameterIsNotNull(str9, "timezone");
        String str10 = str7;
        Intrinsics.checkParameterIsNotNull(str10, NotificationCompat.CATEGORY_STATUS);
        return new ScheduledVisit(str, str2, str3, str4, str8, j, str9, str10);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScheduledVisit)) {
            return false;
        }
        ScheduledVisit scheduledVisit = (ScheduledVisit) obj;
        return Intrinsics.areEqual((Object) this.slotId, (Object) scheduledVisit.slotId) && Intrinsics.areEqual((Object) this.inmateId, (Object) scheduledVisit.inmateId) && Intrinsics.areEqual((Object) this.inmate, (Object) scheduledVisit.inmate) && Intrinsics.areEqual((Object) this.facility, (Object) scheduledVisit.facility) && Intrinsics.areEqual((Object) this.station, (Object) scheduledVisit.station) && this.timestamp == scheduledVisit.timestamp && Intrinsics.areEqual((Object) this.timezone, (Object) scheduledVisit.timezone) && Intrinsics.areEqual((Object) this.status, (Object) scheduledVisit.status);
    }

    public int hashCode() {
        String str = this.slotId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.inmateId;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.inmate;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.facility;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.station;
        int hashCode5 = str5 != null ? str5.hashCode() : 0;
        long j = this.timestamp;
        int i2 = (((hashCode4 + hashCode5) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        String str6 = this.timezone;
        int hashCode6 = (i2 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.status;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        return "ScheduledVisit(slotId=" + this.slotId + ", inmateId=" + this.inmateId + ", inmate=" + this.inmate + ", facility=" + this.facility + ", station=" + this.station + ", timestamp=" + this.timestamp + ", timezone=" + this.timezone + ", status=" + this.status + ")";
    }

    public ScheduledVisit(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7) {
        Intrinsics.checkParameterIsNotNull(str, "slotId");
        Intrinsics.checkParameterIsNotNull(str2, "inmateId");
        Intrinsics.checkParameterIsNotNull(str3, "inmate");
        Intrinsics.checkParameterIsNotNull(str4, "facility");
        Intrinsics.checkParameterIsNotNull(str5, "station");
        Intrinsics.checkParameterIsNotNull(str6, "timezone");
        Intrinsics.checkParameterIsNotNull(str7, NotificationCompat.CATEGORY_STATUS);
        this.slotId = str;
        this.inmateId = str2;
        this.inmate = str3;
        this.facility = str4;
        this.station = str5;
        this.timestamp = j;
        this.timezone = str6;
        this.status = str7;
    }

    public final String getSlotId() {
        return this.slotId;
    }

    public final String getInmateId() {
        return this.inmateId;
    }

    public final String getInmate() {
        return this.inmate;
    }

    public final String getFacility() {
        return this.facility;
    }

    public final String getStation() {
        return this.station;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final String getTimezone() {
        return this.timezone;
    }

    public final String getStatus() {
        return this.status;
    }
}
