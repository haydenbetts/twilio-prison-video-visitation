package com.urbanairship;

import com.urbanairship.util.AirshipThreadFactory;
import com.urbanairship.util.SerialExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AirshipExecutors {
    public static final ExecutorService THREAD_POOL_EXECUTOR = Executors.newCachedThreadPool(AirshipThreadFactory.DEFAULT_THREAD_FACTORY);

    public static Executor newSerialExecutor() {
        return new SerialExecutor(THREAD_POOL_EXECUTOR);
    }
}
