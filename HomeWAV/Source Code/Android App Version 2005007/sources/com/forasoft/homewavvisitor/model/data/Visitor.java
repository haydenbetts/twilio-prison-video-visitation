package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J]\u0010\u001a\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\t\u0010 \u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006!"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Visitor;", "", "id", "", "created", "first_name", "last_name", "full_name", "phone", "visitor_id", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCreated", "()Ljava/lang/String;", "getFirst_name", "getFull_name", "getId", "getLast_name", "getPhone", "getVisitor_id", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Visitor.kt */
public final class Visitor {
    private final String created;
    private final String first_name;
    private final String full_name;
    private final String id;
    private final String last_name;
    private final String phone;
    private final String visitor_id;

    public static /* synthetic */ Visitor copy$default(Visitor visitor, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = visitor.id;
        }
        if ((i & 2) != 0) {
            str2 = visitor.created;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = visitor.first_name;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = visitor.last_name;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = visitor.full_name;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = visitor.phone;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = visitor.visitor_id;
        }
        return visitor.copy(str, str8, str9, str10, str11, str12, str7);
    }

    public final String component1() {
        return this.id;
    }

    public final String component2() {
        return this.created;
    }

    public final String component3() {
        return this.first_name;
    }

    public final String component4() {
        return this.last_name;
    }

    public final String component5() {
        return this.full_name;
    }

    public final String component6() {
        return this.phone;
    }

    public final String component7() {
        return this.visitor_id;
    }

    public final Visitor copy(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        return new Visitor(str, str2, str3, str4, str5, str6, str7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Visitor)) {
            return false;
        }
        Visitor visitor = (Visitor) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) visitor.id) && Intrinsics.areEqual((Object) this.created, (Object) visitor.created) && Intrinsics.areEqual((Object) this.first_name, (Object) visitor.first_name) && Intrinsics.areEqual((Object) this.last_name, (Object) visitor.last_name) && Intrinsics.areEqual((Object) this.full_name, (Object) visitor.full_name) && Intrinsics.areEqual((Object) this.phone, (Object) visitor.phone) && Intrinsics.areEqual((Object) this.visitor_id, (Object) visitor.visitor_id);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.created;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.first_name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.last_name;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.full_name;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.phone;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.visitor_id;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode6 + i;
    }

    public String toString() {
        return "Visitor(id=" + this.id + ", created=" + this.created + ", first_name=" + this.first_name + ", last_name=" + this.last_name + ", full_name=" + this.full_name + ", phone=" + this.phone + ", visitor_id=" + this.visitor_id + ")";
    }

    public Visitor(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.id = str;
        this.created = str2;
        this.first_name = str3;
        this.last_name = str4;
        this.full_name = str5;
        this.phone = str6;
        this.visitor_id = str7;
    }

    public final String getId() {
        return this.id;
    }

    public final String getCreated() {
        return this.created;
    }

    public final String getFirst_name() {
        return this.first_name;
    }

    public final String getLast_name() {
        return this.last_name;
    }

    public final String getFull_name() {
        return this.full_name;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final String getVisitor_id() {
        return this.visitor_id;
    }
}
