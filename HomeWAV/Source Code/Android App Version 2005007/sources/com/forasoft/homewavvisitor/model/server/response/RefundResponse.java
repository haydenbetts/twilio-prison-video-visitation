package com.forasoft.homewavvisitor.model.server.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00032\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/RefundResponse;", "", "ok", "", "cause", "Lcom/forasoft/homewavvisitor/model/server/response/Cause;", "(ZLcom/forasoft/homewavvisitor/model/server/response/Cause;)V", "getCause", "()Lcom/forasoft/homewavvisitor/model/server/response/Cause;", "getOk", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RefundResponse.kt */
public final class RefundResponse {
    private final Cause cause;
    private final boolean ok;

    public static /* synthetic */ RefundResponse copy$default(RefundResponse refundResponse, boolean z, Cause cause2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = refundResponse.ok;
        }
        if ((i & 2) != 0) {
            cause2 = refundResponse.cause;
        }
        return refundResponse.copy(z, cause2);
    }

    public final boolean component1() {
        return this.ok;
    }

    public final Cause component2() {
        return this.cause;
    }

    public final RefundResponse copy(boolean z, Cause cause2) {
        return new RefundResponse(z, cause2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RefundResponse)) {
            return false;
        }
        RefundResponse refundResponse = (RefundResponse) obj;
        return this.ok == refundResponse.ok && Intrinsics.areEqual((Object) this.cause, (Object) refundResponse.cause);
    }

    public int hashCode() {
        boolean z = this.ok;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        Cause cause2 = this.cause;
        return i + (cause2 != null ? cause2.hashCode() : 0);
    }

    public String toString() {
        return "RefundResponse(ok=" + this.ok + ", cause=" + this.cause + ")";
    }

    public RefundResponse(boolean z, Cause cause2) {
        this.ok = z;
        this.cause = cause2;
    }

    public final Cause getCause() {
        return this.cause;
    }

    public final boolean getOk() {
        return this.ok;
    }
}
