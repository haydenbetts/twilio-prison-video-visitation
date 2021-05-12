package com.forasoft.homewavvisitor.model.data.auth;

import com.forasoft.homewavvisitor.model.UploadWorker;
import com.stripe.android.view.ShippingInfoWidget;
import com.urbanairship.util.Attributes;
import java.util.Date;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\bM\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BÁ\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003\u0012\u0006\u0010\u0012\u001a\u00020\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001a\u001a\u00020\u0018¢\u0006\u0002\u0010\u001bJ\t\u0010L\u001a\u00020\u0003HÆ\u0003J\t\u0010M\u001a\u00020\u0003HÆ\u0003J\t\u0010N\u001a\u00020\u0003HÆ\u0003J\t\u0010O\u001a\u00020\u0003HÆ\u0003J\t\u0010P\u001a\u00020\u0003HÆ\u0003J\t\u0010Q\u001a\u00020\u0003HÆ\u0003J\t\u0010R\u001a\u00020\u0003HÆ\u0003J\u000b\u0010S\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010T\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010U\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010V\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010W\u001a\u00020\u0003HÆ\u0003J\t\u0010X\u001a\u00020\u0018HÆ\u0003J\t\u0010Y\u001a\u00020\u0018HÆ\u0003J\t\u0010Z\u001a\u00020\u0018HÆ\u0003J\t\u0010[\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\\\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010]\u001a\u00020\bHÆ\u0003J\t\u0010^\u001a\u00020\u0003HÆ\u0003J\t\u0010_\u001a\u00020\u0003HÆ\u0003J\t\u0010`\u001a\u00020\u0003HÆ\u0003J\t\u0010a\u001a\u00020\u0003HÆ\u0003Jï\u0001\u0010b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u00032\b\b\u0002\u0010\u0012\u001a\u00020\u00032\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u0018HÆ\u0001J\u0013\u0010c\u001a\u00020\u00182\b\u0010d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010e\u001a\u00020fHÖ\u0001J\t\u0010g\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010\u0010\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010!\"\u0004\b%\u0010#R\u001a\u0010\u000e\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010!\"\u0004\b'\u0010#R\u001a\u0010\n\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010!\"\u0004\b)\u0010#R\u001a\u0010\f\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010!\"\u0004\b+\u0010#R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010!\"\u0004\b-\u0010#R\u001a\u0010\u0019\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010.\"\u0004\b/\u00100R\u001a\u0010\u001a\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010.\"\u0004\b1\u00100R\u001a\u0010\u000b\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010!\"\u0004\b3\u0010#R\u001a\u0010\r\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u0010!\"\u0004\b5\u0010#R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010!\"\u0004\b7\u0010#R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010!\"\u0004\b9\u0010#R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010!\"\u0004\b;\u0010#R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010!\"\u0004\b=\u0010#R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010!\"\u0004\b?\u0010#R\u001a\u0010\u000f\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010!\"\u0004\bA\u0010#R\u001a\u0010\u0011\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010!\"\u0004\bC\u0010#R\u001a\u0010\t\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010!\"\u0004\bE\u0010#R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010.\"\u0004\bG\u00100R\u001a\u0010\u0004\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bH\u0010!\"\u0004\bI\u0010#R\u001a\u0010\u0012\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010!\"\u0004\bK\u0010#¨\u0006h"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/auth/User;", "", "id", "", "visitor_id", "created", "pin", "birthDate", "Ljava/util/Date;", "username", "firstName", "lastName", "fullName", "phone", "email", "state", "city", "street", "zip", "photoProfileUrl", "photoIdUrl", "photoProfile", "photoId", "verified", "", "isAdmin", "isNotificationSubscriptionEnabled", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZZZ)V", "getBirthDate", "()Ljava/util/Date;", "setBirthDate", "(Ljava/util/Date;)V", "getCity", "()Ljava/lang/String;", "setCity", "(Ljava/lang/String;)V", "getCreated", "setCreated", "getEmail", "setEmail", "getFirstName", "setFirstName", "getFullName", "setFullName", "getId", "setId", "()Z", "setAdmin", "(Z)V", "setNotificationSubscriptionEnabled", "getLastName", "setLastName", "getPhone", "setPhone", "getPhotoId", "setPhotoId", "getPhotoIdUrl", "setPhotoIdUrl", "getPhotoProfile", "setPhotoProfile", "getPhotoProfileUrl", "setPhotoProfileUrl", "getPin", "setPin", "getState", "setState", "getStreet", "setStreet", "getUsername", "setUsername", "getVerified", "setVerified", "getVisitor_id", "setVisitor_id", "getZip", "setZip", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component18", "component19", "component2", "component20", "component21", "component22", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: User.kt */
public final class User {
    private Date birthDate;
    private String city;
    private String created;
    private String email;
    private String firstName;
    private String fullName;
    private String id;
    private boolean isAdmin;
    private boolean isNotificationSubscriptionEnabled;
    private String lastName;
    private String phone;
    private String photoId;
    private String photoIdUrl;
    private String photoProfile;
    private String photoProfileUrl;
    private String pin;
    private String state;
    private String street;
    private String username;
    private boolean verified;
    private String visitor_id;
    private String zip;

    public static /* synthetic */ User copy$default(User user, String str, String str2, String str3, String str4, Date date, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, boolean z, boolean z2, boolean z3, int i, Object obj) {
        User user2 = user;
        int i2 = i;
        return user.copy((i2 & 1) != 0 ? user2.id : str, (i2 & 2) != 0 ? user2.visitor_id : str2, (i2 & 4) != 0 ? user2.created : str3, (i2 & 8) != 0 ? user2.pin : str4, (i2 & 16) != 0 ? user2.birthDate : date, (i2 & 32) != 0 ? user2.username : str5, (i2 & 64) != 0 ? user2.firstName : str6, (i2 & 128) != 0 ? user2.lastName : str7, (i2 & 256) != 0 ? user2.fullName : str8, (i2 & 512) != 0 ? user2.phone : str9, (i2 & 1024) != 0 ? user2.email : str10, (i2 & 2048) != 0 ? user2.state : str11, (i2 & 4096) != 0 ? user2.city : str12, (i2 & 8192) != 0 ? user2.street : str13, (i2 & 16384) != 0 ? user2.zip : str14, (i2 & 32768) != 0 ? user2.photoProfileUrl : str15, (i2 & 65536) != 0 ? user2.photoIdUrl : str16, (i2 & 131072) != 0 ? user2.photoProfile : str17, (i2 & 262144) != 0 ? user2.photoId : str18, (i2 & 524288) != 0 ? user2.verified : z, (i2 & 1048576) != 0 ? user2.isAdmin : z2, (i2 & 2097152) != 0 ? user2.isNotificationSubscriptionEnabled : z3);
    }

    public final String component1() {
        return this.id;
    }

    public final String component10() {
        return this.phone;
    }

    public final String component11() {
        return this.email;
    }

    public final String component12() {
        return this.state;
    }

    public final String component13() {
        return this.city;
    }

    public final String component14() {
        return this.street;
    }

    public final String component15() {
        return this.zip;
    }

    public final String component16() {
        return this.photoProfileUrl;
    }

    public final String component17() {
        return this.photoIdUrl;
    }

    public final String component18() {
        return this.photoProfile;
    }

    public final String component19() {
        return this.photoId;
    }

    public final String component2() {
        return this.visitor_id;
    }

    public final boolean component20() {
        return this.verified;
    }

    public final boolean component21() {
        return this.isAdmin;
    }

    public final boolean component22() {
        return this.isNotificationSubscriptionEnabled;
    }

    public final String component3() {
        return this.created;
    }

    public final String component4() {
        return this.pin;
    }

    public final Date component5() {
        return this.birthDate;
    }

    public final String component6() {
        return this.username;
    }

    public final String component7() {
        return this.firstName;
    }

    public final String component8() {
        return this.lastName;
    }

    public final String component9() {
        return this.fullName;
    }

    public final User copy(String str, String str2, String str3, String str4, Date date, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, boolean z, boolean z2, boolean z3) {
        String str19 = str;
        Intrinsics.checkParameterIsNotNull(str19, "id");
        Intrinsics.checkParameterIsNotNull(str2, UploadWorker.KEY_VISITOR_ID);
        Intrinsics.checkParameterIsNotNull(str3, "created");
        Intrinsics.checkParameterIsNotNull(date, "birthDate");
        Intrinsics.checkParameterIsNotNull(str5, Attributes.USERNAME);
        Intrinsics.checkParameterIsNotNull(str6, "firstName");
        Intrinsics.checkParameterIsNotNull(str7, "lastName");
        Intrinsics.checkParameterIsNotNull(str8, "fullName");
        Intrinsics.checkParameterIsNotNull(str9, ShippingInfoWidget.PHONE_FIELD);
        Intrinsics.checkParameterIsNotNull(str10, "email");
        Intrinsics.checkParameterIsNotNull(str11, "state");
        Intrinsics.checkParameterIsNotNull(str12, "city");
        Intrinsics.checkParameterIsNotNull(str13, "street");
        Intrinsics.checkParameterIsNotNull(str14, "zip");
        return new User(str19, str2, str3, str4, date, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, z, z2, z3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) user.id) && Intrinsics.areEqual((Object) this.visitor_id, (Object) user.visitor_id) && Intrinsics.areEqual((Object) this.created, (Object) user.created) && Intrinsics.areEqual((Object) this.pin, (Object) user.pin) && Intrinsics.areEqual((Object) this.birthDate, (Object) user.birthDate) && Intrinsics.areEqual((Object) this.username, (Object) user.username) && Intrinsics.areEqual((Object) this.firstName, (Object) user.firstName) && Intrinsics.areEqual((Object) this.lastName, (Object) user.lastName) && Intrinsics.areEqual((Object) this.fullName, (Object) user.fullName) && Intrinsics.areEqual((Object) this.phone, (Object) user.phone) && Intrinsics.areEqual((Object) this.email, (Object) user.email) && Intrinsics.areEqual((Object) this.state, (Object) user.state) && Intrinsics.areEqual((Object) this.city, (Object) user.city) && Intrinsics.areEqual((Object) this.street, (Object) user.street) && Intrinsics.areEqual((Object) this.zip, (Object) user.zip) && Intrinsics.areEqual((Object) this.photoProfileUrl, (Object) user.photoProfileUrl) && Intrinsics.areEqual((Object) this.photoIdUrl, (Object) user.photoIdUrl) && Intrinsics.areEqual((Object) this.photoProfile, (Object) user.photoProfile) && Intrinsics.areEqual((Object) this.photoId, (Object) user.photoId) && this.verified == user.verified && this.isAdmin == user.isAdmin && this.isNotificationSubscriptionEnabled == user.isNotificationSubscriptionEnabled;
    }

    public int hashCode() {
        String str = this.id;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.visitor_id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.created;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.pin;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        Date date = this.birthDate;
        int hashCode5 = (hashCode4 + (date != null ? date.hashCode() : 0)) * 31;
        String str5 = this.username;
        int hashCode6 = (hashCode5 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.firstName;
        int hashCode7 = (hashCode6 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.lastName;
        int hashCode8 = (hashCode7 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.fullName;
        int hashCode9 = (hashCode8 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.phone;
        int hashCode10 = (hashCode9 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.email;
        int hashCode11 = (hashCode10 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.state;
        int hashCode12 = (hashCode11 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.city;
        int hashCode13 = (hashCode12 + (str12 != null ? str12.hashCode() : 0)) * 31;
        String str13 = this.street;
        int hashCode14 = (hashCode13 + (str13 != null ? str13.hashCode() : 0)) * 31;
        String str14 = this.zip;
        int hashCode15 = (hashCode14 + (str14 != null ? str14.hashCode() : 0)) * 31;
        String str15 = this.photoProfileUrl;
        int hashCode16 = (hashCode15 + (str15 != null ? str15.hashCode() : 0)) * 31;
        String str16 = this.photoIdUrl;
        int hashCode17 = (hashCode16 + (str16 != null ? str16.hashCode() : 0)) * 31;
        String str17 = this.photoProfile;
        int hashCode18 = (hashCode17 + (str17 != null ? str17.hashCode() : 0)) * 31;
        String str18 = this.photoId;
        if (str18 != null) {
            i = str18.hashCode();
        }
        int i2 = (hashCode18 + i) * 31;
        boolean z = this.verified;
        boolean z2 = true;
        if (z) {
            z = true;
        }
        int i3 = (i2 + (z ? 1 : 0)) * 31;
        boolean z3 = this.isAdmin;
        if (z3) {
            z3 = true;
        }
        int i4 = (i3 + (z3 ? 1 : 0)) * 31;
        boolean z4 = this.isNotificationSubscriptionEnabled;
        if (!z4) {
            z2 = z4;
        }
        return i4 + (z2 ? 1 : 0);
    }

    public String toString() {
        return "User(id=" + this.id + ", visitor_id=" + this.visitor_id + ", created=" + this.created + ", pin=" + this.pin + ", birthDate=" + this.birthDate + ", username=" + this.username + ", firstName=" + this.firstName + ", lastName=" + this.lastName + ", fullName=" + this.fullName + ", phone=" + this.phone + ", email=" + this.email + ", state=" + this.state + ", city=" + this.city + ", street=" + this.street + ", zip=" + this.zip + ", photoProfileUrl=" + this.photoProfileUrl + ", photoIdUrl=" + this.photoIdUrl + ", photoProfile=" + this.photoProfile + ", photoId=" + this.photoId + ", verified=" + this.verified + ", isAdmin=" + this.isAdmin + ", isNotificationSubscriptionEnabled=" + this.isNotificationSubscriptionEnabled + ")";
    }

    public User(String str, String str2, String str3, String str4, Date date, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, boolean z, boolean z2, boolean z3) {
        String str19 = str;
        String str20 = str2;
        String str21 = str3;
        Date date2 = date;
        String str22 = str5;
        String str23 = str6;
        String str24 = str7;
        String str25 = str8;
        String str26 = str9;
        String str27 = str10;
        String str28 = str11;
        String str29 = str12;
        String str30 = str13;
        String str31 = str14;
        Intrinsics.checkParameterIsNotNull(str19, "id");
        Intrinsics.checkParameterIsNotNull(str20, UploadWorker.KEY_VISITOR_ID);
        Intrinsics.checkParameterIsNotNull(str21, "created");
        Intrinsics.checkParameterIsNotNull(date2, "birthDate");
        Intrinsics.checkParameterIsNotNull(str22, Attributes.USERNAME);
        Intrinsics.checkParameterIsNotNull(str23, "firstName");
        Intrinsics.checkParameterIsNotNull(str24, "lastName");
        Intrinsics.checkParameterIsNotNull(str25, "fullName");
        Intrinsics.checkParameterIsNotNull(str26, ShippingInfoWidget.PHONE_FIELD);
        Intrinsics.checkParameterIsNotNull(str27, "email");
        Intrinsics.checkParameterIsNotNull(str28, "state");
        Intrinsics.checkParameterIsNotNull(str29, "city");
        Intrinsics.checkParameterIsNotNull(str30, "street");
        Intrinsics.checkParameterIsNotNull(str31, "zip");
        this.id = str19;
        this.visitor_id = str20;
        this.created = str21;
        this.pin = str4;
        this.birthDate = date2;
        this.username = str22;
        this.firstName = str23;
        this.lastName = str24;
        this.fullName = str25;
        this.phone = str26;
        this.email = str27;
        this.state = str28;
        this.city = str29;
        this.street = str30;
        this.zip = str31;
        this.photoProfileUrl = str15;
        this.photoIdUrl = str16;
        this.photoProfile = str17;
        this.photoId = str18;
        this.verified = z;
        this.isAdmin = z2;
        this.isNotificationSubscriptionEnabled = z3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ User(String str, String str2, String str3, String str4, Date date, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, str2, str3, str4, date, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, z, z2, z3);
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final String getVisitor_id() {
        return this.visitor_id;
    }

    public final void setVisitor_id(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.visitor_id = str;
    }

    public final String getCreated() {
        return this.created;
    }

    public final void setCreated(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.created = str;
    }

    public final String getPin() {
        return this.pin;
    }

    public final void setPin(String str) {
        this.pin = str;
    }

    public final Date getBirthDate() {
        return this.birthDate;
    }

    public final void setBirthDate(Date date) {
        Intrinsics.checkParameterIsNotNull(date, "<set-?>");
        this.birthDate = date;
    }

    public final String getUsername() {
        return this.username;
    }

    public final void setUsername(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.username = str;
    }

    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.firstName = str;
    }

    public final String getLastName() {
        return this.lastName;
    }

    public final void setLastName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.lastName = str;
    }

    public final String getFullName() {
        return this.fullName;
    }

    public final void setFullName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.fullName = str;
    }

    public final String getPhone() {
        return this.phone;
    }

    public final void setPhone(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.phone = str;
    }

    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.email = str;
    }

    public final String getState() {
        return this.state;
    }

    public final void setState(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.state = str;
    }

    public final String getCity() {
        return this.city;
    }

    public final void setCity(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.city = str;
    }

    public final String getStreet() {
        return this.street;
    }

    public final void setStreet(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.street = str;
    }

    public final String getZip() {
        return this.zip;
    }

    public final void setZip(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
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

    public final String getPhotoProfile() {
        return this.photoProfile;
    }

    public final void setPhotoProfile(String str) {
        this.photoProfile = str;
    }

    public final String getPhotoId() {
        return this.photoId;
    }

    public final void setPhotoId(String str) {
        this.photoId = str;
    }

    public final boolean getVerified() {
        return this.verified;
    }

    public final void setVerified(boolean z) {
        this.verified = z;
    }

    public final boolean isAdmin() {
        return this.isAdmin;
    }

    public final void setAdmin(boolean z) {
        this.isAdmin = z;
    }

    public final boolean isNotificationSubscriptionEnabled() {
        return this.isNotificationSubscriptionEnabled;
    }

    public final void setNotificationSubscriptionEnabled(boolean z) {
        this.isNotificationSubscriptionEnabled = z;
    }
}
