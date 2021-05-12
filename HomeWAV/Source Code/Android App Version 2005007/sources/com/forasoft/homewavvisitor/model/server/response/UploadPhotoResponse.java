package com.forasoft.homewavvisitor.model.server.response;

import com.forasoft.homewavvisitor.model.UploadWorker;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/UploadPhotoResponse;", "", "pubid", "", "url", "(Ljava/lang/String;Ljava/lang/String;)V", "getPubid", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UploadPhotoResponse.kt */
public final class UploadPhotoResponse {
    private final String pubid;
    private final String url;

    public static /* synthetic */ UploadPhotoResponse copy$default(UploadPhotoResponse uploadPhotoResponse, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uploadPhotoResponse.pubid;
        }
        if ((i & 2) != 0) {
            str2 = uploadPhotoResponse.url;
        }
        return uploadPhotoResponse.copy(str, str2);
    }

    public final String component1() {
        return this.pubid;
    }

    public final String component2() {
        return this.url;
    }

    public final UploadPhotoResponse copy(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str2, "url");
        return new UploadPhotoResponse(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UploadPhotoResponse)) {
            return false;
        }
        UploadPhotoResponse uploadPhotoResponse = (UploadPhotoResponse) obj;
        return Intrinsics.areEqual((Object) this.pubid, (Object) uploadPhotoResponse.pubid) && Intrinsics.areEqual((Object) this.url, (Object) uploadPhotoResponse.url);
    }

    public int hashCode() {
        String str = this.pubid;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "UploadPhotoResponse(pubid=" + this.pubid + ", url=" + this.url + ")";
    }

    public UploadPhotoResponse(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, UploadWorker.KEY_PUB_ID);
        Intrinsics.checkParameterIsNotNull(str2, "url");
        this.pubid = str;
        this.url = str2;
    }

    public final String getPubid() {
        return this.pubid;
    }

    public final String getUrl() {
        return this.url;
    }
}
