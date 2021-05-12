package com.forasoft.homewavvisitor.model.server.response;

import com.braintreepayments.api.internal.GraphQLConstants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/model/server/response/Cause;", "", "message", "", "errors", "Lcom/forasoft/homewavvisitor/model/server/response/Errors;", "(Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/server/response/Errors;)V", "getErrors", "()Lcom/forasoft/homewavvisitor/model/server/response/Errors;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RefundResponse.kt */
public final class Cause {
    private final Errors errors;
    private final String message;

    public static /* synthetic */ Cause copy$default(Cause cause, String str, Errors errors2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = cause.message;
        }
        if ((i & 2) != 0) {
            errors2 = cause.errors;
        }
        return cause.copy(str, errors2);
    }

    public final String component1() {
        return this.message;
    }

    public final Errors component2() {
        return this.errors;
    }

    public final Cause copy(String str, Errors errors2) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(errors2, GraphQLConstants.Keys.ERRORS);
        return new Cause(str, errors2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cause)) {
            return false;
        }
        Cause cause = (Cause) obj;
        return Intrinsics.areEqual((Object) this.message, (Object) cause.message) && Intrinsics.areEqual((Object) this.errors, (Object) cause.errors);
    }

    public int hashCode() {
        String str = this.message;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Errors errors2 = this.errors;
        if (errors2 != null) {
            i = errors2.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Cause(message=" + this.message + ", errors=" + this.errors + ")";
    }

    public Cause(String str, Errors errors2) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        Intrinsics.checkParameterIsNotNull(errors2, GraphQLConstants.Keys.ERRORS);
        this.message = str;
        this.errors = errors2;
    }

    public final Errors getErrors() {
        return this.errors;
    }

    public final String getMessage() {
        return this.message;
    }
}
