package com.urbanairship.automation.alarms;

public interface OperationScheduler {
    void schedule(long j, Runnable runnable);
}
