package com.forasoft.homewavvisitor.model.server.response;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0004HÆ\u0003J\u0010\u0010\u0011\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J6\u0010\u0013\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0007HÖ\u0001R\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/Response;", "T", "", "ok", "", "body", "error", "", "(ZLjava/lang/Object;Ljava/lang/String;)V", "getBody", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getError", "()Ljava/lang/String;", "getOk", "()Z", "component1", "component2", "component3", "copy", "(ZLjava/lang/Object;Ljava/lang/String;)Lcom/forasoft/homewavvisitor/model/server/response/Response;", "equals", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Response.kt */
public final class Response<T> {
    private final T body;
    @SerializedName("cause")
    private final String error;
    private final boolean ok;

    public static /* synthetic */ Response copy$default(Response response, boolean z, T t, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            z = response.ok;
        }
        if ((i & 2) != 0) {
            t = response.body;
        }
        if ((i & 4) != 0) {
            str = response.error;
        }
        return response.copy(z, t, str);
    }

    public final boolean component1() {
        return this.ok;
    }

    public final T component2() {
        return this.body;
    }

    public final String component3() {
        return this.error;
    }

    public final Response<T> copy(boolean z, T t, String str) {
        return new Response<>(z, t, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Response)) {
            return false;
        }
        Response response = (Response) obj;
        return this.ok == response.ok && Intrinsics.areEqual((Object) this.body, (Object) response.body) && Intrinsics.areEqual((Object) this.error, (Object) response.error);
    }

    public int hashCode() {
        boolean z = this.ok;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        T t = this.body;
        int i2 = 0;
        int hashCode = (i + (t != null ? t.hashCode() : 0)) * 31;
        String str = this.error;
        if (str != null) {
            i2 = str.hashCode();
        }
        return hashCode + i2;
    }

    public String toString() {
        return "Response(ok=" + this.ok + ", body=" + this.body + ", error=" + this.error + ")";
    }

    public Response(boolean z, T t, String str) {
        this.ok = z;
        this.body = t;
        this.error = str;
    }

    public final boolean getOk() {
        return this.ok;
    }

    public final T getBody() {
        return this.body;
    }

    public final String getError() {
        return this.error;
    }
}
