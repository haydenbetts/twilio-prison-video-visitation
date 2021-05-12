package com.forasoft.homewavvisitor.model.server.response;

import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0006HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ6\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0015\u0010\u0007\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "T", "", "ok", "", "errorCause", "Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "body", "(ZLcom/forasoft/homewavvisitor/model/data/ErrorCause;Ljava/lang/Object;)V", "getBody", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getErrorCause", "()Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "getOk", "()Z", "component1", "component2", "component3", "copy", "(ZLcom/forasoft/homewavvisitor/model/data/ErrorCause;Ljava/lang/Object;)Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "equals", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ApiResponse.kt */
public final class ApiResponse<T> {
    private final T body;
    @SerializedName("cause")
    private final ErrorCause errorCause;
    private final boolean ok;

    public static /* synthetic */ ApiResponse copy$default(ApiResponse apiResponse, boolean z, ErrorCause errorCause2, T t, int i, Object obj) {
        if ((i & 1) != 0) {
            z = apiResponse.ok;
        }
        if ((i & 2) != 0) {
            errorCause2 = apiResponse.errorCause;
        }
        if ((i & 4) != 0) {
            t = apiResponse.body;
        }
        return apiResponse.copy(z, errorCause2, t);
    }

    public final boolean component1() {
        return this.ok;
    }

    public final ErrorCause component2() {
        return this.errorCause;
    }

    public final T component3() {
        return this.body;
    }

    public final ApiResponse<T> copy(boolean z, ErrorCause errorCause2, T t) {
        return new ApiResponse<>(z, errorCause2, t);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ApiResponse)) {
            return false;
        }
        ApiResponse apiResponse = (ApiResponse) obj;
        return this.ok == apiResponse.ok && Intrinsics.areEqual((Object) this.errorCause, (Object) apiResponse.errorCause) && Intrinsics.areEqual((Object) this.body, (Object) apiResponse.body);
    }

    public int hashCode() {
        boolean z = this.ok;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        ErrorCause errorCause2 = this.errorCause;
        int i2 = 0;
        int hashCode = (i + (errorCause2 != null ? errorCause2.hashCode() : 0)) * 31;
        T t = this.body;
        if (t != null) {
            i2 = t.hashCode();
        }
        return hashCode + i2;
    }

    public String toString() {
        return "ApiResponse(ok=" + this.ok + ", errorCause=" + this.errorCause + ", body=" + this.body + ")";
    }

    public ApiResponse(boolean z, ErrorCause errorCause2, T t) {
        this.ok = z;
        this.errorCause = errorCause2;
        this.body = t;
    }

    public final boolean getOk() {
        return this.ok;
    }

    public final ErrorCause getErrorCause() {
        return this.errorCause;
    }

    public final T getBody() {
        return this.body;
    }
}
