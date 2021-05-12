package com.forasoft.homewavvisitor.model.data.register;

import android.os.Parcel;
import android.os.Parcelable;
import com.urbanairship.util.Attributes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u001e\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\fHÆ\u0003J{\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\t\u0010*\u001a\u00020+HÖ\u0001J\u0013\u0010,\u001a\u00020\f2\b\u0010-\u001a\u0004\u0018\u00010.HÖ\u0003J\t\u0010/\u001a\u00020+HÖ\u0001J\t\u00100\u001a\u00020\u0003HÖ\u0001J\u0019\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020+HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0011\"\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011R\u0013\u0010\r\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0011¨\u00066"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "Landroid/os/Parcelable;", "approved", "", "credit_balance", "first_name", "id", "last_name", "occupant_id", "prison_id", "prison_payment_gateway", "is_fraud", "", "visitorEmail", "status", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;)V", "getApproved", "()Ljava/lang/String;", "getCredit_balance", "setCredit_balance", "(Ljava/lang/String;)V", "getFirst_name", "getId", "()Z", "getLast_name", "getOccupant_id", "getPrison_id", "getPrison_payment_gateway", "getStatus", "getVisitorEmail", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: InmateByVisitor.kt */
public final class InmateByVisitor implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private final String approved;
    private String credit_balance;
    private final String first_name;
    private final String id;
    private final boolean is_fraud;
    private final String last_name;
    private final String occupant_id;
    private final String prison_id;
    private final String prison_payment_gateway;
    private final String status;
    private final String visitorEmail;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public static class Creator implements Parcelable.Creator {
        public final Object createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "in");
            return new InmateByVisitor(parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readInt() != 0, parcel.readString(), parcel.readString());
        }

        public final Object[] newArray(int i) {
            return new InmateByVisitor[i];
        }
    }

    public static /* synthetic */ InmateByVisitor copy$default(InmateByVisitor inmateByVisitor, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, int i, Object obj) {
        InmateByVisitor inmateByVisitor2 = inmateByVisitor;
        int i2 = i;
        return inmateByVisitor.copy((i2 & 1) != 0 ? inmateByVisitor2.approved : str, (i2 & 2) != 0 ? inmateByVisitor2.credit_balance : str2, (i2 & 4) != 0 ? inmateByVisitor2.first_name : str3, (i2 & 8) != 0 ? inmateByVisitor2.id : str4, (i2 & 16) != 0 ? inmateByVisitor2.last_name : str5, (i2 & 32) != 0 ? inmateByVisitor2.occupant_id : str6, (i2 & 64) != 0 ? inmateByVisitor2.prison_id : str7, (i2 & 128) != 0 ? inmateByVisitor2.prison_payment_gateway : str8, (i2 & 256) != 0 ? inmateByVisitor2.is_fraud : z, (i2 & 512) != 0 ? inmateByVisitor2.visitorEmail : str9, (i2 & 1024) != 0 ? inmateByVisitor2.status : str10);
    }

    public final String component1() {
        return this.approved;
    }

    public final String component10() {
        return this.visitorEmail;
    }

    public final String component11() {
        return this.status;
    }

    public final String component2() {
        return this.credit_balance;
    }

    public final String component3() {
        return this.first_name;
    }

    public final String component4() {
        return this.id;
    }

    public final String component5() {
        return this.last_name;
    }

    public final String component6() {
        return this.occupant_id;
    }

    public final String component7() {
        return this.prison_id;
    }

    public final String component8() {
        return this.prison_payment_gateway;
    }

    public final boolean component9() {
        return this.is_fraud;
    }

    public final InmateByVisitor copy(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10) {
        Intrinsics.checkParameterIsNotNull(str, "approved");
        Intrinsics.checkParameterIsNotNull(str2, "credit_balance");
        String str11 = str3;
        Intrinsics.checkParameterIsNotNull(str11, Attributes.FIRST_NAME);
        String str12 = str4;
        Intrinsics.checkParameterIsNotNull(str12, "id");
        String str13 = str5;
        Intrinsics.checkParameterIsNotNull(str13, Attributes.LAST_NAME);
        String str14 = str6;
        Intrinsics.checkParameterIsNotNull(str14, "occupant_id");
        String str15 = str7;
        Intrinsics.checkParameterIsNotNull(str15, "prison_id");
        String str16 = str8;
        Intrinsics.checkParameterIsNotNull(str16, "prison_payment_gateway");
        return new InmateByVisitor(str, str2, str11, str12, str13, str14, str15, str16, z, str9, str10);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof InmateByVisitor)) {
            return false;
        }
        InmateByVisitor inmateByVisitor = (InmateByVisitor) obj;
        return Intrinsics.areEqual((Object) this.approved, (Object) inmateByVisitor.approved) && Intrinsics.areEqual((Object) this.credit_balance, (Object) inmateByVisitor.credit_balance) && Intrinsics.areEqual((Object) this.first_name, (Object) inmateByVisitor.first_name) && Intrinsics.areEqual((Object) this.id, (Object) inmateByVisitor.id) && Intrinsics.areEqual((Object) this.last_name, (Object) inmateByVisitor.last_name) && Intrinsics.areEqual((Object) this.occupant_id, (Object) inmateByVisitor.occupant_id) && Intrinsics.areEqual((Object) this.prison_id, (Object) inmateByVisitor.prison_id) && Intrinsics.areEqual((Object) this.prison_payment_gateway, (Object) inmateByVisitor.prison_payment_gateway) && this.is_fraud == inmateByVisitor.is_fraud && Intrinsics.areEqual((Object) this.visitorEmail, (Object) inmateByVisitor.visitorEmail) && Intrinsics.areEqual((Object) this.status, (Object) inmateByVisitor.status);
    }

    public int hashCode() {
        String str = this.approved;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.credit_balance;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.first_name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.id;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.last_name;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.occupant_id;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.prison_id;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.prison_payment_gateway;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        boolean z = this.is_fraud;
        if (z) {
            z = true;
        }
        int i2 = (hashCode8 + (z ? 1 : 0)) * 31;
        String str9 = this.visitorEmail;
        int hashCode9 = (i2 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.status;
        if (str10 != null) {
            i = str10.hashCode();
        }
        return hashCode9 + i;
    }

    public String toString() {
        return "InmateByVisitor(approved=" + this.approved + ", credit_balance=" + this.credit_balance + ", first_name=" + this.first_name + ", id=" + this.id + ", last_name=" + this.last_name + ", occupant_id=" + this.occupant_id + ", prison_id=" + this.prison_id + ", prison_payment_gateway=" + this.prison_payment_gateway + ", is_fraud=" + this.is_fraud + ", visitorEmail=" + this.visitorEmail + ", status=" + this.status + ")";
    }

    public void writeToParcel(Parcel parcel, int i) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.approved);
        parcel.writeString(this.credit_balance);
        parcel.writeString(this.first_name);
        parcel.writeString(this.id);
        parcel.writeString(this.last_name);
        parcel.writeString(this.occupant_id);
        parcel.writeString(this.prison_id);
        parcel.writeString(this.prison_payment_gateway);
        parcel.writeInt(this.is_fraud ? 1 : 0);
        parcel.writeString(this.visitorEmail);
        parcel.writeString(this.status);
    }

    public InmateByVisitor(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10) {
        Intrinsics.checkParameterIsNotNull(str, "approved");
        Intrinsics.checkParameterIsNotNull(str2, "credit_balance");
        Intrinsics.checkParameterIsNotNull(str3, Attributes.FIRST_NAME);
        Intrinsics.checkParameterIsNotNull(str4, "id");
        Intrinsics.checkParameterIsNotNull(str5, Attributes.LAST_NAME);
        Intrinsics.checkParameterIsNotNull(str6, "occupant_id");
        Intrinsics.checkParameterIsNotNull(str7, "prison_id");
        Intrinsics.checkParameterIsNotNull(str8, "prison_payment_gateway");
        this.approved = str;
        this.credit_balance = str2;
        this.first_name = str3;
        this.id = str4;
        this.last_name = str5;
        this.occupant_id = str6;
        this.prison_id = str7;
        this.prison_payment_gateway = str8;
        this.is_fraud = z;
        this.visitorEmail = str9;
        this.status = str10;
    }

    public final String getApproved() {
        return this.approved;
    }

    public final String getCredit_balance() {
        return this.credit_balance;
    }

    public final void setCredit_balance(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.credit_balance = str;
    }

    public final String getFirst_name() {
        return this.first_name;
    }

    public final String getId() {
        return this.id;
    }

    public final String getLast_name() {
        return this.last_name;
    }

    public final String getOccupant_id() {
        return this.occupant_id;
    }

    public final String getPrison_id() {
        return this.prison_id;
    }

    public final String getPrison_payment_gateway() {
        return this.prison_payment_gateway;
    }

    public final boolean is_fraud() {
        return this.is_fraud;
    }

    public final String getVisitorEmail() {
        return this.visitorEmail;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ InmateByVisitor(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z, String str9, String str10, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, str4, str5, str6, str7, str8, z, str9, (i & 1024) != 0 ? null : str10);
    }

    public final String getStatus() {
        return this.status;
    }
}
