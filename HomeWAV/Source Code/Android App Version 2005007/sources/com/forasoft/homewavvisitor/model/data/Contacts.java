package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Contacts;", "", "refundEmail", "", "supportEmail", "supportPhone", "workHours", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getRefundEmail", "()Ljava/lang/String;", "getSupportEmail", "getSupportPhone", "getWorkHours", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Contacts.kt */
public final class Contacts {
    private final String refundEmail;
    private final String supportEmail;
    private final String supportPhone;
    private final String workHours;

    public static /* synthetic */ Contacts copy$default(Contacts contacts, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = contacts.refundEmail;
        }
        if ((i & 2) != 0) {
            str2 = contacts.supportEmail;
        }
        if ((i & 4) != 0) {
            str3 = contacts.supportPhone;
        }
        if ((i & 8) != 0) {
            str4 = contacts.workHours;
        }
        return contacts.copy(str, str2, str3, str4);
    }

    public final String component1() {
        return this.refundEmail;
    }

    public final String component2() {
        return this.supportEmail;
    }

    public final String component3() {
        return this.supportPhone;
    }

    public final String component4() {
        return this.workHours;
    }

    public final Contacts copy(String str, String str2, String str3, String str4) {
        Intrinsics.checkParameterIsNotNull(str, "refundEmail");
        Intrinsics.checkParameterIsNotNull(str2, "supportEmail");
        Intrinsics.checkParameterIsNotNull(str3, "supportPhone");
        Intrinsics.checkParameterIsNotNull(str4, "workHours");
        return new Contacts(str, str2, str3, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Contacts)) {
            return false;
        }
        Contacts contacts = (Contacts) obj;
        return Intrinsics.areEqual((Object) this.refundEmail, (Object) contacts.refundEmail) && Intrinsics.areEqual((Object) this.supportEmail, (Object) contacts.supportEmail) && Intrinsics.areEqual((Object) this.supportPhone, (Object) contacts.supportPhone) && Intrinsics.areEqual((Object) this.workHours, (Object) contacts.workHours);
    }

    public int hashCode() {
        String str = this.refundEmail;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.supportEmail;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.supportPhone;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.workHours;
        if (str4 != null) {
            i = str4.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "Contacts(refundEmail=" + this.refundEmail + ", supportEmail=" + this.supportEmail + ", supportPhone=" + this.supportPhone + ", workHours=" + this.workHours + ")";
    }

    public Contacts(String str, String str2, String str3, String str4) {
        Intrinsics.checkParameterIsNotNull(str, "refundEmail");
        Intrinsics.checkParameterIsNotNull(str2, "supportEmail");
        Intrinsics.checkParameterIsNotNull(str3, "supportPhone");
        Intrinsics.checkParameterIsNotNull(str4, "workHours");
        this.refundEmail = str;
        this.supportEmail = str2;
        this.supportPhone = str3;
        this.workHours = str4;
    }

    public final String getRefundEmail() {
        return this.refundEmail;
    }

    public final String getSupportEmail() {
        return this.supportEmail;
    }

    public final String getSupportPhone() {
        return this.supportPhone;
    }

    public final String getWorkHours() {
        return this.workHours;
    }
}
