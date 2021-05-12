package com.forasoft.homewavvisitor.model.data;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R(\u0010\u0005\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "", "message", "", "(Ljava/lang/String;)V", "errorList", "", "getErrorList", "()Ljava/util/Map;", "setErrorList", "(Ljava/util/Map;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ErrorCause.kt */
public final class ErrorCause {
    private Map<String, String> errorList;
    private final String message;

    public static /* synthetic */ ErrorCause copy$default(ErrorCause errorCause, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = errorCause.message;
        }
        return errorCause.copy(str);
    }

    public final String component1() {
        return this.message;
    }

    public final ErrorCause copy(String str) {
        return new ErrorCause(str);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof ErrorCause) && Intrinsics.areEqual((Object) this.message, (Object) ((ErrorCause) obj).message);
        }
        return true;
    }

    public int hashCode() {
        String str = this.message;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ErrorCause(message=" + this.message + ")";
    }

    public ErrorCause(String str) {
        this.message = str;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Map<String, String> getErrorList() {
        return this.errorList;
    }

    public final void setErrorList(Map<String, String> map) {
        this.errorList = map;
    }
}
