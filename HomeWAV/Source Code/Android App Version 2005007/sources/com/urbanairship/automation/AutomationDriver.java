package com.urbanairship.automation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface AutomationDriver {
    public static final int PREPARE_RESULT_CANCEL = 1;
    public static final int PREPARE_RESULT_CONTINUE = 0;
    public static final int PREPARE_RESULT_INVALIDATE = 4;
    public static final int PREPARE_RESULT_PENALIZE = 2;
    public static final int PREPARE_RESULT_SKIP = 3;
    public static final int READY_RESULT_CONTINUE = 1;
    public static final int READY_RESULT_INVALIDATE = -1;
    public static final int READY_RESULT_NOT_READY = 0;

    public interface ExecutionCallback {
        void onFinish();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface PrepareResult {
    }

    public interface PrepareScheduleCallback {
        void onFinish(int i);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface ReadyResult {
    }

    int onCheckExecutionReadiness(Schedule schedule);

    void onExecuteTriggeredSchedule(Schedule schedule, ExecutionCallback executionCallback);

    void onPrepareSchedule(Schedule schedule, TriggerContext triggerContext, PrepareScheduleCallback prepareScheduleCallback);
}
