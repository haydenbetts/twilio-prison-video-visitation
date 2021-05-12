package com.urbanairship.actions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ActionResult {
    public static final int STATUS_ACTION_NOT_FOUND = 3;
    public static final int STATUS_COMPLETED = 1;
    public static final int STATUS_EXECUTION_ERROR = 4;
    public static final int STATUS_REJECTED_ARGUMENTS = 2;
    private final Exception exception;
    private final int status;
    private final ActionValue value;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public static ActionResult newEmptyResult() {
        return new ActionResult((ActionValue) null, (Exception) null, 1);
    }

    public static ActionResult newResult(ActionValue actionValue) {
        return new ActionResult(actionValue, (Exception) null, 1);
    }

    public static ActionResult newErrorResult(Exception exc) {
        return new ActionResult((ActionValue) null, exc, 4);
    }

    static ActionResult newEmptyResultWithStatus(int i) {
        return new ActionResult((ActionValue) null, (Exception) null, i);
    }

    ActionResult(ActionValue actionValue, Exception exc, int i) {
        this.value = actionValue == null ? new ActionValue() : actionValue;
        this.exception = exc;
        this.status = i;
    }

    public ActionValue getValue() {
        return this.value;
    }

    public Exception getException() {
        return this.exception;
    }

    public int getStatus() {
        return this.status;
    }
}
