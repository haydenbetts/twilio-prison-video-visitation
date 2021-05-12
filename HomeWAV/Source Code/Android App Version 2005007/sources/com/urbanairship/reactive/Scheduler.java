package com.urbanairship.reactive;

public interface Scheduler {
    Subscription schedule(long j, Runnable runnable);

    Subscription schedule(Runnable runnable);
}
