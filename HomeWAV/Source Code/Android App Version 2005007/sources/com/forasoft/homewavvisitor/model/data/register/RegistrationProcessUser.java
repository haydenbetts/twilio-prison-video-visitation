package com.forasoft.homewavvisitor.model.data.register;

import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.bytedeco.ffmpeg.global.avutil;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b@\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B·\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0013J\t\u00106\u001a\u00020\u0003HÆ\u0003J\u000b\u00107\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00109\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010:\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010<\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010=\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010>\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u000b\u0010?\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010@\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010A\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010B\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010C\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010D\u001a\u0004\u0018\u00010\u0003HÆ\u0003J»\u0001\u0010E\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010F\u001a\u00020G2\b\u0010H\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010I\u001a\u00020JHÖ\u0001J\t\u0010K\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0019\"\u0004\b\u001d\u0010\u001bR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0019\"\u0004\b!\u0010\u001bR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0019\"\u0004\b#\u0010\u001bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0019\"\u0004\b%\u0010\u001bR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0019\"\u0004\b'\u0010\u001bR\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0019\"\u0004\b)\u0010\u001bR\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0019\"\u0004\b+\u0010\u001bR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0019\"\u0004\b-\u0010\u001bR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0019\"\u0004\b/\u0010\u001bR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u0019\"\u0004\b1\u0010\u001bR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0019\"\u0004\b3\u0010\u001bR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u0019\"\u0004\b5\u0010\u001b¨\u0006L"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/register/RegistrationProcessUser;", "", "id", "", "visitor_id", "birthDate", "Ljava/util/Date;", "username", "first_name", "last_name", "full_name", "phone", "email", "state", "city", "street", "zip", "photoProfileUrl", "photoIdUrl", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getBirthDate", "()Ljava/util/Date;", "setBirthDate", "(Ljava/util/Date;)V", "getCity", "()Ljava/lang/String;", "setCity", "(Ljava/lang/String;)V", "getEmail", "setEmail", "getFirst_name", "setFirst_name", "getFull_name", "setFull_name", "getId", "setId", "getLast_name", "setLast_name", "getPhone", "setPhone", "getPhotoIdUrl", "setPhotoIdUrl", "getPhotoProfileUrl", "setPhotoProfileUrl", "getState", "setState", "getStreet", "setStreet", "getUsername", "setUsername", "getVisitor_id", "setVisitor_id", "getZip", "setZip", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RegistrationProcessUser.kt */
public final class RegistrationProcessUser {
    private Date birthDate;
    private String city;
    private String email;
    private String first_name;
    private String full_name;
    private String id;
    private String last_name;
    private String phone;
    private String photoIdUrl;
    private String photoProfileUrl;
    private String state;
    private String street;
    private String username;
    private String visitor_id;
    private String zip;

    public RegistrationProcessUser() {
        this((String) null, (String) null, (Date) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, avutil.FF_LAMBDA_MAX, (DefaultConstructorMarker) null);
    }

    public static /* synthetic */ RegistrationProcessUser copy$default(RegistrationProcessUser registrationProcessUser, String str, String str2, Date date, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, int i, Object obj) {
        RegistrationProcessUser registrationProcessUser2 = registrationProcessUser;
        int i2 = i;
        return registrationProcessUser.copy((i2 & 1) != 0 ? registrationProcessUser2.id : str, (i2 & 2) != 0 ? registrationProcessUser2.visitor_id : str2, (i2 & 4) != 0 ? registrationProcessUser2.birthDate : date, (i2 & 8) != 0 ? registrationProcessUser2.username : str3, (i2 & 16) != 0 ? registrationProcessUser2.first_name : str4, (i2 & 32) != 0 ? registrationProcessUser2.last_name : str5, (i2 & 64) != 0 ? registrationProcessUser2.full_name : str6, (i2 & 128) != 0 ? registrationProcessUser2.phone : str7, (i2 & 256) != 0 ? registrationProcessUser2.email : str8, (i2 & 512) != 0 ? registrationProcessUser2.state : str9, (i2 & 1024) != 0 ? registrationProcessUser2.city : str10, (i2 & 2048) != 0 ? registrationProcessUser2.street : str11, (i2 & 4096) != 0 ? registrationProcessUser2.zip : str12, (i2 & 8192) != 0 ? registrationProcessUser2.photoProfileUrl : str13, (i2 & 16384) != 0 ? registrationProcessUser2.photoIdUrl : str14);
    }

    public final String component1() {
        return this.id;
    }

    public final String component10() {
        return this.state;
    }

    public final String component11() {
        return this.city;
    }

    public final String component12() {
        return this.street;
    }

    public final String component13() {
        return this.zip;
    }

    public final String component14() {
        return this.photoProfileUrl;
    }

    public final String component15() {
        return this.photoIdUrl;
    }

    public final String component2() {
        return this.visitor_id;
    }

    public final Date component3() {
        return this.birthDate;
    }

    public final String component4() {
        return this.username;
    }

    public final String component5() {
        return this.first_name;
    }

    public final String component6() {
        return this.last_name;
    }

    public final String component7() {
        return this.full_name;
    }

    public final String component8() {
        return this.phone;
    }

    public final String component9() {
        return this.email;
    }

    public final RegistrationProcessUser copy(String str, String str2, Date date, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        String str15 = str;
        Intrinsics.checkParameterIsNotNull(str15, "id");
        return new RegistrationProcessUser(str15, str2, date, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RegistrationProcessUser)) {
            return false;
        }
        RegistrationProcessUser registrationProcessUser = (RegistrationProcessUser) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) registrationProcessUser.id) && Intrinsics.areEqual((Object) this.visitor_id, (Object) registrationProcessUser.visitor_id) && Intrinsics.areEqual((Object) this.birthDate, (Object) registrationProcessUser.birthDate) && Intrinsics.areEqual((Object) this.username, (Object) registrationProcessUser.username) && Intrinsics.areEqual((Object) this.first_name, (Object) registrationProcessUser.first_name) && Intrinsics.areEqual((Object) this.last_name, (Object) registrationProcessUser.last_name) && Intrinsics.areEqual((Object) this.full_name, (Object) registrationProcessUser.full_name) && Intrinsics.areEqual((Object) this.phone, (Object) registrationProcessUser.phone) && Intrinsics.areEqual((Object) this.email, (Object) registrationProcessUser.email) && Intrinsics.areEqual((Object) this.state, (Object) registrationProcessUser.state) && Intrinsics.areEqual((Object) this.city, (Object) registrationProcessUser.city) && Intrinsics.areEqual((Object) this.street, (Object) registrationProcessUser.street) && Intrinsics.areEqual((Object) this.zip, (Object) registrationProcessUser.zip) && Intrinsics.areEqual((Object) this.photoProfileUrl, (Object) registrationProcessUser.photoProfileUrl) && Intrinsics.areEqual((Object) this.photoIdUrl, (Object) registrationProcessUser.photoIdUrl);
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.visitor_id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Date date = this.birthDate;
        int hashCode3 = (hashCode2 + (date != null ? date.hashCode() : 0)) * 31;
        String str3 = this.username;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.first_name;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.last_name;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.full_name;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.phone;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.email;
        int hashCode9 = (hashCode8 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.state;
        int hashCode10 = (hashCode9 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.city;
        int hashCode11 = (hashCode10 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.street;
        int hashCode12 = (hashCode11 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.zip;
        int hashCode13 = (hashCode12 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.photoProfileUrl;
        int hashCode14 = (hashCode13 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.photoIdUrl;
        if (str14 != null) {
            i = str14.hashCode();
        }
        return hashCode14 + i;
    }

    public String toString() {
        return "RegistrationProcessUser(id=" + this.id + ", visitor_id=" + this.visitor_id + ", birthDate=" + this.birthDate + ", username=" + this.username + ", first_name=" + this.first_name + ", last_name=" + this.last_name + ", full_name=" + this.full_name + ", phone=" + this.phone + ", email=" + this.email + ", state=" + this.state + ", city=" + this.city + ", street=" + this.street + ", zip=" + this.zip + ", photoProfileUrl=" + this.photoProfileUrl + ", photoIdUrl=" + this.photoIdUrl + ")";
    }

    public RegistrationProcessUser(String str, String str2, Date date, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        Intrinsics.checkParameterIsNotNull(str, "id");
        this.id = str;
        this.visitor_id = str2;
        this.birthDate = date;
        this.username = str3;
        this.first_name = str4;
        this.last_name = str5;
        this.full_name = str6;
        this.phone = str7;
        this.email = str8;
        this.state = str9;
        this.city = str10;
        this.street = str11;
        this.zip = str12;
        this.photoProfileUrl = str13;
        this.photoIdUrl = str14;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ RegistrationProcessUser(java.lang.String r17, java.lang.String r18, java.util.Date r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, java.lang.String r29, java.lang.String r30, java.lang.String r31, int r32, kotlin.jvm.internal.DefaultConstructorMarker r33) {
        /*
            r16 = this;
            r0 = r32
            r1 = r0 & 1
            if (r1 == 0) goto L_0x0009
            java.lang.String r1 = ""
            goto L_0x000b
        L_0x0009:
            r1 = r17
        L_0x000b:
            r2 = r0 & 2
            r3 = 0
            if (r2 == 0) goto L_0x0014
            r2 = r3
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x0016
        L_0x0014:
            r2 = r18
        L_0x0016:
            r4 = r0 & 4
            if (r4 == 0) goto L_0x001e
            r4 = r3
            java.util.Date r4 = (java.util.Date) r4
            goto L_0x0020
        L_0x001e:
            r4 = r19
        L_0x0020:
            r5 = r0 & 8
            if (r5 == 0) goto L_0x0028
            r5 = r3
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x002a
        L_0x0028:
            r5 = r20
        L_0x002a:
            r6 = r0 & 16
            if (r6 == 0) goto L_0x0032
            r6 = r3
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0034
        L_0x0032:
            r6 = r21
        L_0x0034:
            r7 = r0 & 32
            if (r7 == 0) goto L_0x003c
            r7 = r3
            java.lang.String r7 = (java.lang.String) r7
            goto L_0x003e
        L_0x003c:
            r7 = r22
        L_0x003e:
            r8 = r0 & 64
            if (r8 == 0) goto L_0x0046
            r8 = r3
            java.lang.String r8 = (java.lang.String) r8
            goto L_0x0048
        L_0x0046:
            r8 = r23
        L_0x0048:
            r9 = r0 & 128(0x80, float:1.794E-43)
            if (r9 == 0) goto L_0x0050
            r9 = r3
            java.lang.String r9 = (java.lang.String) r9
            goto L_0x0052
        L_0x0050:
            r9 = r24
        L_0x0052:
            r10 = r0 & 256(0x100, float:3.59E-43)
            if (r10 == 0) goto L_0x005a
            r10 = r3
            java.lang.String r10 = (java.lang.String) r10
            goto L_0x005c
        L_0x005a:
            r10 = r25
        L_0x005c:
            r11 = r0 & 512(0x200, float:7.175E-43)
            if (r11 == 0) goto L_0x0064
            r11 = r3
            java.lang.String r11 = (java.lang.String) r11
            goto L_0x0066
        L_0x0064:
            r11 = r26
        L_0x0066:
            r12 = r0 & 1024(0x400, float:1.435E-42)
            if (r12 == 0) goto L_0x006e
            r12 = r3
            java.lang.String r12 = (java.lang.String) r12
            goto L_0x0070
        L_0x006e:
            r12 = r27
        L_0x0070:
            r13 = r0 & 2048(0x800, float:2.87E-42)
            if (r13 == 0) goto L_0x0078
            r13 = r3
            java.lang.String r13 = (java.lang.String) r13
            goto L_0x007a
        L_0x0078:
            r13 = r28
        L_0x007a:
            r14 = r0 & 4096(0x1000, float:5.74E-42)
            if (r14 == 0) goto L_0x0082
            r14 = r3
            java.lang.String r14 = (java.lang.String) r14
            goto L_0x0084
        L_0x0082:
            r14 = r29
        L_0x0084:
            r15 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r15 == 0) goto L_0x008c
            r15 = r3
            java.lang.String r15 = (java.lang.String) r15
            goto L_0x008e
        L_0x008c:
            r15 = r30
        L_0x008e:
            r0 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r0 == 0) goto L_0x0096
            r0 = r3
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x0098
        L_0x0096:
            r0 = r31
        L_0x0098:
            r17 = r16
            r18 = r1
            r19 = r2
            r20 = r4
            r21 = r5
            r22 = r6
            r23 = r7
            r24 = r8
            r25 = r9
            r26 = r10
            r27 = r11
            r28 = r12
            r29 = r13
            r30 = r14
            r31 = r15
            r32 = r0
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.data.register.RegistrationProcessUser.<init>(java.lang.String, java.lang.String, java.util.Date, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getVisitor_id() {
        return this.visitor_id;
    }

    public final void setVisitor_id(String str) {
        this.visitor_id = str;
    }

    public final Date getBirthDate() {
        return this.birthDate;
    }

    public final void setBirthDate(Date date) {
        this.birthDate = date;
    }

    public final String getUsername() {
        return this.username;
    }

    public final void setUsername(String str) {
        this.username = str;
    }

    public final String getFirst_name() {
        return this.first_name;
    }

    public final void setFirst_name(String str) {
        this.first_name = str;
    }

    public final String getLast_name() {
        return this.last_name;
    }

    public final void setLast_name(String str) {
        this.last_name = str;
    }

    public final String getFull_name() {
        return this.full_name;
    }

    public final void setFull_name(String str) {
        this.full_name = str;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(String str) {
        this.phone = str;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        this.email = str;
    }

    public final String getState() {
        return this.state;
    }

    public final void setState(String str) {
        this.state = str;
    }

    public final String getCity() {
        return this.city;
    }

    public final void setCity(String str) {
        this.city = str;
    }

    public final String getStreet() {
        return this.street;
    }

    public final void setStreet(String str) {
        this.street = str;
    }

    public final String getZip() {
        return this.zip;
    }

    public final void setZip(String str) {
        this.zip = str;
    }

    public final String getPhotoProfileUrl() {
        return this.photoProfileUrl;
    }

    public final void setPhotoProfileUrl(String str) {
        this.photoProfileUrl = str;
    }

    public final String getPhotoIdUrl() {
        return this.photoIdUrl;
    }

    public final void setPhotoIdUrl(String str) {
        this.photoIdUrl = str;
    }
}
