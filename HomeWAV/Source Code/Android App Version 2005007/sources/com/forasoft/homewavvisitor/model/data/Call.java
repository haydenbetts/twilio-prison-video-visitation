package com.forasoft.homewavvisitor.model.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.forasoft.homewavvisitor.model.UploadWorker;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BA\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003JS\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"HÖ\u0003J\t\u0010#\u001a\u00020\u001eHÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\u0019\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u001eHÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0016\u0010\n\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006*"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Call;", "Landroid/os/Parcelable;", "id", "", "protocol", "Lcom/forasoft/homewavvisitor/model/data/Protocol;", "visitorId", "inmateCredits", "inmateId", "pubid", "inmate_name", "(Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/Protocol;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getInmateCredits", "getInmateId", "getInmate_name", "getProtocol", "()Lcom/forasoft/homewavvisitor/model/data/Protocol;", "getPubid", "getVisitorId", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Call.kt */
public final class Call implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    @SerializedName(alternate = {"callId"}, value = "id")
    private final String id;
    @SerializedName("inmateCredits")
    private final String inmateCredits;
    @SerializedName(alternate = {"inmateId"}, value = "inmate_id")
    private final String inmateId;
    @SerializedName(alternate = {"inmateName"}, value = "inmate_name")
    private final String inmate_name;
    @SerializedName("protocol")
    private final Protocol protocol;
    @SerializedName("pubid")
    private final String pubid;
    @SerializedName("visitor_id")
    private final String visitorId;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new Call(parcel.readString(), (Protocol) Enum.valueOf(Protocol.class, parcel.readString()), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new Call[i];
        }
    }

    public static /* synthetic */ Call copy$default(Call call, String str, Protocol protocol2, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = call.id;
        }
        if ((i & 2) != 0) {
            protocol2 = call.protocol;
        }
        Protocol protocol3 = protocol2;
        if ((i & 4) != 0) {
            str2 = call.visitorId;
        }
        String str7 = str2;
        if ((i & 8) != 0) {
            str3 = call.inmateCredits;
        }
        String str8 = str3;
        if ((i & 16) != 0) {
            str4 = call.inmateId;
        }
        String str9 = str4;
        if ((i & 32) != 0) {
            str5 = call.pubid;
        }
        String str10 = str5;
        if ((i & 64) != 0) {
            str6 = call.inmate_name;
        }
        return call.copy(str, protocol3, str7, str8, str9, str10, str6);
    }

    public final String component1() {
        return this.id;
    }

    public final Protocol component2() {
        return this.protocol;
    }

    public final String component3() {
        return this.visitorId;
    }

    public final String component4() {
        return this.inmateCredits;
    }

    public final String component5() {
        return this.inmateId;
    }

    public final String component6() {
        return this.pubid;
    }

    public final String component7() {
        return this.inmate_name;
    }

    public final Call copy(String str, Protocol protocol2, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(protocol2, "protocol");
        Intrinsics.checkParameterIsNotNull(str4, "inmateId");
        Intrinsics.checkParameterIsNotNull(str5, UploadWorker.KEY_PUB_ID);
        String str7 = str6;
        Intrinsics.checkParameterIsNotNull(str7, "inmate_name");
        return new Call(str, protocol2, str2, str3, str4, str5, str7);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Call)) {
            return false;
        }
        Call call = (Call) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) call.id) && Intrinsics.areEqual((Object) this.protocol, (Object) call.protocol) && Intrinsics.areEqual((Object) this.visitorId, (Object) call.visitorId) && Intrinsics.areEqual((Object) this.inmateCredits, (Object) call.inmateCredits) && Intrinsics.areEqual((Object) this.inmateId, (Object) call.inmateId) && Intrinsics.areEqual((Object) this.pubid, (Object) call.pubid) && Intrinsics.areEqual((Object) this.inmate_name, (Object) call.inmate_name);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Protocol protocol2 = this.protocol;
        int hashCode2 = (hashCode + (protocol2 != null ? protocol2.hashCode() : 0)) * 31;
        String str2 = this.visitorId;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.inmateCredits;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.inmateId;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.pubid;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.inmate_name;
        if (str6 != null) {
            i = str6.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        return "Call(id=" + this.id + ", protocol=" + this.protocol + ", visitorId=" + this.visitorId + ", inmateCredits=" + this.inmateCredits + ", inmateId=" + this.inmateId + ", pubid=" + this.pubid + ", inmate_name=" + this.inmate_name + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.id);
        parcel.writeString(this.protocol.name());
        parcel.writeString(this.visitorId);
        parcel.writeString(this.inmateCredits);
        parcel.writeString(this.inmateId);
        parcel.writeString(this.pubid);
        parcel.writeString(this.inmate_name);
    }

    public Call(String str, Protocol protocol2, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        Intrinsics.checkParameterIsNotNull(protocol2, "protocol");
        Intrinsics.checkParameterIsNotNull(str4, "inmateId");
        Intrinsics.checkParameterIsNotNull(str5, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str6, "inmate_name");
        this.id = str;
        this.protocol = protocol2;
        this.visitorId = str2;
        this.inmateCredits = str3;
        this.inmateId = str4;
        this.pubid = str5;
        this.inmate_name = str6;
    }

    public final String getId() {
        return this.id;
    }

    public final Protocol getProtocol() {
        return this.protocol;
    }

    public final String getVisitorId() {
        return this.visitorId;
    }

    public final String getInmateCredits() {
        return this.inmateCredits;
    }

    public final String getInmateId() {
        return this.inmateId;
    }

    public final String getPubid() {
        return this.pubid;
    }

    public final String getInmate_name() {
        return this.inmate_name;
    }
}
