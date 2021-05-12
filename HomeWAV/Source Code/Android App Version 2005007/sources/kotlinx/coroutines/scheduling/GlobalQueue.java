package kotlinx.coroutines.scheduling;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeMPMCQueue;
import kotlinx.coroutines.internal.LockFreeMPMCQueueNode;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002J\n\u0010\u0007\u001a\u0004\u0018\u00010\u0002H\u0016J\b\u0010\b\u001a\u0004\u0018\u00010\u0002¨\u0006\t"}, d2 = {"Lkotlinx/coroutines/scheduling/GlobalQueue;", "Lkotlinx/coroutines/internal/LockFreeMPMCQueue;", "Lkotlinx/coroutines/scheduling/Task;", "()V", "add", "", "task", "removeFirstBlockingModeOrNull", "removeFirstIfNotClosed", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: Tasks.kt */
public class GlobalQueue extends LockFreeMPMCQueue<Task> {
    public final boolean add(Task task) {
        Intrinsics.checkParameterIsNotNull(task, "task");
        while (true) {
            LockFreeMPMCQueueNode tailValue = getTailValue();
            LockFreeMPMCQueueNode lockFreeMPMCQueueNode = (LockFreeMPMCQueueNode) tailValue.getNextValue();
            if (lockFreeMPMCQueueNode != null) {
                tailCas(tailValue, lockFreeMPMCQueueNode);
            } else {
                if (!(tailValue != TasksKt.getCLOSED_TASK())) {
                    return false;
                }
                LockFreeMPMCQueueNode lockFreeMPMCQueueNode2 = task;
                if (tailValue.nextCas(null, lockFreeMPMCQueueNode2)) {
                    tailCas(tailValue, lockFreeMPMCQueueNode2);
                    return true;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final kotlinx.coroutines.scheduling.Task removeFirstIfNotClosed() {
        /*
            r5 = this;
        L_0x0000:
            kotlinx.coroutines.internal.LockFreeMPMCQueueNode r0 = r5.getHeadValue()
            java.lang.Object r1 = r0.getNextValue()
            kotlinx.coroutines.internal.LockFreeMPMCQueueNode r1 = (kotlinx.coroutines.internal.LockFreeMPMCQueueNode) r1
            r2 = 0
            if (r1 == 0) goto L_0x0023
            r3 = r1
            kotlinx.coroutines.scheduling.Task r3 = (kotlinx.coroutines.scheduling.Task) r3
            kotlinx.coroutines.scheduling.Task r4 = kotlinx.coroutines.scheduling.TasksKt.getCLOSED_TASK()
            if (r3 == r4) goto L_0x0018
            r3 = 1
            goto L_0x0019
        L_0x0018:
            r3 = 0
        L_0x0019:
            if (r3 != 0) goto L_0x001c
            goto L_0x0023
        L_0x001c:
            boolean r0 = r5.headCas(r0, r1)
            if (r0 == 0) goto L_0x0000
            goto L_0x0024
        L_0x0023:
            r1 = r2
        L_0x0024:
            kotlinx.coroutines.scheduling.Task r1 = (kotlinx.coroutines.scheduling.Task) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.GlobalQueue.removeFirstIfNotClosed():kotlinx.coroutines.scheduling.Task");
    }

    /* JADX WARNING: Removed duplicated region for block: B:0:0x0000 A[LOOP_START, MTH_ENTER_BLOCK] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public kotlinx.coroutines.scheduling.Task removeFirstBlockingModeOrNull() {
        /*
            r5 = this;
        L_0x0000:
            kotlinx.coroutines.internal.LockFreeMPMCQueueNode r0 = r5.getHeadValue()
            java.lang.Object r1 = r0.getNextValue()
            kotlinx.coroutines.internal.LockFreeMPMCQueueNode r1 = (kotlinx.coroutines.internal.LockFreeMPMCQueueNode) r1
            r2 = 0
            if (r1 == 0) goto L_0x0025
            r3 = r1
            kotlinx.coroutines.scheduling.Task r3 = (kotlinx.coroutines.scheduling.Task) r3
            kotlinx.coroutines.scheduling.TaskMode r3 = r3.getMode()
            kotlinx.coroutines.scheduling.TaskMode r4 = kotlinx.coroutines.scheduling.TaskMode.PROBABLY_BLOCKING
            if (r3 != r4) goto L_0x001a
            r3 = 1
            goto L_0x001b
        L_0x001a:
            r3 = 0
        L_0x001b:
            if (r3 != 0) goto L_0x001e
            goto L_0x0025
        L_0x001e:
            boolean r0 = r5.headCas(r0, r1)
            if (r0 == 0) goto L_0x0000
            goto L_0x0026
        L_0x0025:
            r1 = r2
        L_0x0026:
            kotlinx.coroutines.scheduling.Task r1 = (kotlinx.coroutines.scheduling.Task) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.scheduling.GlobalQueue.removeFirstBlockingModeOrNull():kotlinx.coroutines.scheduling.Task");
    }
}
