package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/ValidationError;", "", "error", "Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "(Lcom/forasoft/homewavvisitor/model/data/ErrorCause;)V", "getError", "()Lcom/forasoft/homewavvisitor/model/data/ErrorCause;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ValidationError.kt */
public final class ValidationError extends Throwable {
    private final ErrorCause error;

    public ValidationError(ErrorCause errorCause) {
        this.error = errorCause;
    }

    public final ErrorCause getError() {
        return this.error;
    }
}
