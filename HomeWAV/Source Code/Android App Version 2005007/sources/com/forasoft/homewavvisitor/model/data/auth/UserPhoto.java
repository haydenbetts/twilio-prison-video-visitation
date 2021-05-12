package com.forasoft.homewavvisitor.model.data.auth;

import com.forasoft.homewavvisitor.model.UploadWorker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/auth/UserPhoto;", "", "userPhotoId", "", "created", "format", "pubid", "url", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCreated", "()Ljava/lang/String;", "getFormat", "getPubid", "getUrl", "getUserPhotoId", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UserPhoto.kt */
public final class UserPhoto {
    private final String created;
    private final String format;
    private final String pubid;
    private final String url;
    private final String userPhotoId;

    public static /* synthetic */ UserPhoto copy$default(UserPhoto userPhoto, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = userPhoto.userPhotoId;
        }
        if ((i & 2) != 0) {
            str2 = userPhoto.created;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = userPhoto.format;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = userPhoto.pubid;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = userPhoto.url;
        }
        return userPhoto.copy(str, str6, str7, str8, str5);
    }

    public final String component1() {
        return this.userPhotoId;
    }

    public final String component2() {
        return this.created;
    }

    public final String component3() {
        return this.format;
    }

    public final String component4() {
        return this.pubid;
    }

    public final String component5() {
        return this.url;
    }

    public final UserPhoto copy(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkParameterIsNotNull(str, "userPhotoId");
        Intrinsics.checkParameterIsNotNull(str2, "created");
        Intrinsics.checkParameterIsNotNull(str3, "format");
        Intrinsics.checkParameterIsNotNull(str4, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str5, "url");
        return new UserPhoto(str, str2, str3, str4, str5);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserPhoto)) {
            return false;
        }
        UserPhoto userPhoto = (UserPhoto) obj;
        return Intrinsics.areEqual((Object) this.userPhotoId, (Object) userPhoto.userPhotoId) && Intrinsics.areEqual((Object) this.created, (Object) userPhoto.created) && Intrinsics.areEqual((Object) this.format, (Object) userPhoto.format) && Intrinsics.areEqual((Object) this.pubid, (Object) userPhoto.pubid) && Intrinsics.areEqual((Object) this.url, (Object) userPhoto.url);
    }

    public int hashCode() {
        String str = this.userPhotoId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.created;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.format;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.pubid;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.url;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode4 + i;
    }

    public String toString() {
        return "UserPhoto(userPhotoId=" + this.userPhotoId + ", created=" + this.created + ", format=" + this.format + ", pubid=" + this.pubid + ", url=" + this.url + ")";
    }

    public UserPhoto(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkParameterIsNotNull(str, "userPhotoId");
        Intrinsics.checkParameterIsNotNull(str2, "created");
        Intrinsics.checkParameterIsNotNull(str3, "format");
        Intrinsics.checkParameterIsNotNull(str4, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str5, "url");
        this.userPhotoId = str;
        this.created = str2;
        this.format = str3;
        this.pubid = str4;
        this.url = str5;
    }

    public final String getUserPhotoId() {
        return this.userPhotoId;
    }

    public final String getCreated() {
        return this.created;
    }

    public final String getFormat() {
        return this.format;
    }

    public final String getPubid() {
        return this.pubid;
    }

    public final String getUrl() {
        return this.url;
    }
}
