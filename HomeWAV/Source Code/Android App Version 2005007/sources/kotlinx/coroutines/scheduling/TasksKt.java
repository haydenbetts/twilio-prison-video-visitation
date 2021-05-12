package kotlinx.coroutines.scheduling;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.internal.SystemPropsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bXT¢\u0006\u0002\n\u0000\"\u0012\u0010\t\u001a\u00060\nj\u0002`\u000bX\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\f\u001a\u00020\r8\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000e\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000f\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0010\u001a\u00020\r8\u0000X\u0004¢\u0006\u0002\n\u0000\"\u0012\u0010\u0011\u001a\u00020\u00128\u0000@\u0000X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"BLOCKING_DEFAULT_PARALLELISM", "", "CLOSED_TASK", "Lkotlinx/coroutines/scheduling/Task;", "getCLOSED_TASK", "()Lkotlinx/coroutines/scheduling/Task;", "CORE_POOL_SIZE", "DEFAULT_SCHEDULER_NAME", "", "EMPTY_RUNNABLE", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "IDLE_WORKER_KEEP_ALIVE_NS", "", "MAX_POOL_SIZE", "QUEUE_SIZE_OFFLOAD_THRESHOLD", "WORK_STEALING_TIME_RESOLUTION_NS", "schedulerTimeSource", "Lkotlinx/coroutines/scheduling/TimeSource;", "kotlinx-coroutines-core"}, k = 2, mv = {1, 1, 13})
/* compiled from: Tasks.kt */
public final class TasksKt {
    public static final int BLOCKING_DEFAULT_PARALLELISM = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.blocking.parallelism", 16, 0, 0, 12, (Object) null);
    private static final Task CLOSED_TASK;
    public static final int CORE_POOL_SIZE;
    public static final String DEFAULT_SCHEDULER_NAME = "DefaultDispatcher";
    private static final Runnable EMPTY_RUNNABLE;
    public static final long IDLE_WORKER_KEEP_ALIVE_NS = TimeUnit.SECONDS.toNanos(SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.keep.alive.sec", 5, 0, 0, 12, (Object) null));
    public static final int MAX_POOL_SIZE;
    public static final int QUEUE_SIZE_OFFLOAD_THRESHOLD = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.offload.threshold", 96, 0, 128, 4, (Object) null);
    public static final long WORK_STEALING_TIME_RESOLUTION_NS = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.resolution.ns", 100000, 0, 0, 12, (Object) null);
    public static TimeSource schedulerTimeSource = NanoTimeSource.INSTANCE;

    static {
        int systemProp$default = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.core.pool.size", RangesKt.coerceAtLeast(SystemPropsKt.getAVAILABLE_PROCESSORS(), 2), 1, 0, 8, (Object) null);
        CORE_POOL_SIZE = systemProp$default;
        MAX_POOL_SIZE = SystemPropsKt.systemProp$default("kotlinx.coroutines.scheduler.max.pool.size", RangesKt.coerceIn(SystemPropsKt.getAVAILABLE_PROCESSORS() * 128, systemProp$default, (int) CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE), 0, (int) CoroutineScheduler.MAX_SUPPORTED_POOL_SIZE, 4, (Object) null);
        Runnable tasksKt$$special$$inlined$Runnable$1 = new TasksKt$$special$$inlined$Runnable$1();
        EMPTY_RUNNABLE = tasksKt$$special$$inlined$Runnable$1;
        CLOSED_TASK = new Task(tasksKt$$special$$inlined$Runnable$1, 0, NonBlockingContext.INSTANCE);
    }

    public static final Task getCLOSED_TASK() {
        return CLOSED_TASK;
    }
}
