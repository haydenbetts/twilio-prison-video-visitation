package com.forasoft.homewavvisitor.model.server.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/Errors;", "", "refund", "Lcom/forasoft/homewavvisitor/model/server/response/RefundError;", "(Lcom/forasoft/homewavvisitor/model/server/response/RefundError;)V", "getRefund", "()Lcom/forasoft/homewavvisitor/model/server/response/RefundError;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RefundResponse.kt */
public final class Errors {
    private final RefundError refund;

    public static /* synthetic */ Errors copy$default(Errors errors, RefundError refundError, int i, Object obj) {
        if ((i & 1) != 0) {
            refundError = errors.refund;
        }
        return errors.copy(refundError);
    }

    public final RefundError component1() {
        return this.refund;
    }

    public final Errors copy(RefundError refundError) {
        Intrinsics.checkParameterIsNotNull(refundError, "refund");
        return new Errors(refundError);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof Errors) && Intrinsics.areEqual((Object) this.refund, (Object) ((Errors) obj).refund);
        }
        return true;
    }

    public int hashCode() {
        RefundError refundError = this.refund;
        if (refundError != null) {
            return refundError.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "Errors(refund=" + this.refund + ")";
    }

    public Errors(RefundError refundError) {
        Intrinsics.checkParameterIsNotNull(refundError, "refund");
        this.refund = refundError;
    }

    public final RefundError getRefund() {
        return this.refund;
    }
}
