package kotlinx.coroutines.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeMPMCQueueNode;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0010\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0013\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000¢\u0006\u0002\u0010\u0016J9\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00028\u00002!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00180\u001aH\b¢\u0006\u0002\u0010\u001eJE\u0010\u001f\u001a\u0002H \"\u0004\b\u0001\u0010 2\u0006\u0010!\u001a\u0002H 2'\u0010\"\u001a#\u0012\u0013\u0012\u0011H ¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b($\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H 0#H\b¢\u0006\u0002\u0010%J\u001d\u0010&\u001a\u00020\u00182\u0006\u0010'\u001a\u00028\u00002\u0006\u0010(\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010)J\u0006\u0010*\u001a\u00020\u0018J\r\u0010+\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\nJ3\u0010,\u001a\u0004\u0018\u00018\u00002!\u0010\u0019\u001a\u001d\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00180\u001aH\b¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00020\u00182\u0006\u00100\u001a\u00028\u00002\u0006\u0010(\u001a\u00028\u0000H\u0001¢\u0006\u0002\u0010)R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00028\u00008@X\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u00028\u00008@X\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0004\u001a\u0004\b\u0012\u0010\n¨\u00061"}, d2 = {"Lkotlinx/coroutines/internal/LockFreeMPMCQueue;", "T", "Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;", "", "()V", "head", "Lkotlinx/atomicfu/AtomicRef;", "headValue", "headValue$annotations", "getHeadValue", "()Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;", "size", "", "getSize", "()I", "tail", "tailValue", "tailValue$annotations", "getTailValue", "addLast", "", "node", "(Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;)V", "addLastIfPrev", "", "predicate", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "prev", "(Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;Lkotlin/jvm/functions/Function1;)Z", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "acc", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "headCas", "curHead", "update", "(Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;)Z", "isEmpty", "removeFirstOrNull", "removeFirstOrNullIf", "first", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/internal/LockFreeMPMCQueueNode;", "tailCas", "curTail", "kotlinx-coroutines-core"}, k = 1, mv = {1, 1, 13})
/* compiled from: LockFreeMPMCQueue.kt */
public class LockFreeMPMCQueue<T extends LockFreeMPMCQueueNode<T>> {
    private static final AtomicReferenceFieldUpdater head$FU;
    private static final AtomicReferenceFieldUpdater tail$FU;
    private volatile Object head = new LockFreeMPMCQueueNode();
    private volatile Object tail = this.head;

    static {
        Class<LockFreeMPMCQueue> cls = LockFreeMPMCQueue.class;
        head$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, TtmlNode.TAG_HEAD);
        tail$FU = AtomicReferenceFieldUpdater.newUpdater(cls, Object.class, "tail");
    }

    public static /* synthetic */ void headValue$annotations() {
    }

    public static /* synthetic */ void tailValue$annotations() {
    }

    public final T getHeadValue() {
        return (LockFreeMPMCQueueNode) this.head;
    }

    public final T getTailValue() {
        return (LockFreeMPMCQueueNode) this.tail;
    }

    public final boolean headCas(T t, T t2) {
        Intrinsics.checkParameterIsNotNull(t, "curHead");
        Intrinsics.checkParameterIsNotNull(t2, "update");
        return head$FU.compareAndSet(this, t, t2);
    }

    public final boolean tailCas(T t, T t2) {
        Intrinsics.checkParameterIsNotNull(t, "curTail");
        Intrinsics.checkParameterIsNotNull(t2, "update");
        return tail$FU.compareAndSet(this, t, t2);
    }

    public final boolean addLastIfPrev(T t, Function1<Object, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        while (true) {
            LockFreeMPMCQueueNode tailValue = getTailValue();
            LockFreeMPMCQueueNode lockFreeMPMCQueueNode = (LockFreeMPMCQueueNode) tailValue.getNextValue();
            if (lockFreeMPMCQueueNode != null) {
                tailCas(tailValue, lockFreeMPMCQueueNode);
            } else if (!function1.invoke(tailValue).booleanValue()) {
                return false;
            } else {
                if (tailValue.nextCas(null, t)) {
                    tailCas(tailValue, t);
                    return true;
                }
            }
        }
    }

    public final T removeFirstOrNullIf(Function1<? super T, Boolean> function1) {
        LockFreeMPMCQueueNode headValue;
        T t;
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        do {
            headValue = getHeadValue();
            t = (LockFreeMPMCQueueNode) headValue.getNextValue();
            if (t == null || !function1.invoke(t).booleanValue()) {
                return null;
            }
        } while (!headCas(headValue, t));
        return t;
    }

    public final boolean isEmpty() {
        return getSize() == 0;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.lang.Object, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2<? super R, ? super T, ? extends R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <R> R fold(R r2, kotlin.jvm.functions.Function2<? super R, ? super T, ? extends R> r3) {
        /*
            r1 = this;
            java.lang.String r0 = "operation"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            kotlinx.coroutines.internal.LockFreeMPMCQueueNode r0 = r1.getHeadValue()
        L_0x0009:
            java.lang.Object r0 = r0.getNextValue()
            kotlinx.coroutines.internal.LockFreeMPMCQueueNode r0 = (kotlinx.coroutines.internal.LockFreeMPMCQueueNode) r0
            if (r0 == 0) goto L_0x0016
            java.lang.Object r2 = r3.invoke(r2, r0)
            goto L_0x0009
        L_0x0016:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeMPMCQueue.fold(java.lang.Object, kotlin.jvm.functions.Function2):java.lang.Object");
    }

    public final void addLast(T t) {
        Intrinsics.checkParameterIsNotNull(t, "node");
        while (true) {
            LockFreeMPMCQueueNode lockFreeMPMCQueueNode = (LockFreeMPMCQueueNode) this.tail;
            LockFreeMPMCQueueNode lockFreeMPMCQueueNode2 = (LockFreeMPMCQueueNode) lockFreeMPMCQueueNode.next;
            if (lockFreeMPMCQueueNode2 != null) {
                tail$FU.compareAndSet(this, lockFreeMPMCQueueNode, lockFreeMPMCQueueNode2);
            } else if (LockFreeMPMCQueueNode.next$FU.compareAndSet(lockFreeMPMCQueueNode, (Object) null, t)) {
                tail$FU.compareAndSet(this, lockFreeMPMCQueueNode, t);
                return;
            }
        }
    }

    public final T removeFirstOrNull() {
        LockFreeMPMCQueueNode lockFreeMPMCQueueNode;
        T t;
        do {
            lockFreeMPMCQueueNode = (LockFreeMPMCQueueNode) this.head;
            t = (LockFreeMPMCQueueNode) lockFreeMPMCQueueNode.next;
            if (t == null) {
                return null;
            }
        } while (!head$FU.compareAndSet(this, lockFreeMPMCQueueNode, t));
        return t;
    }

    public final int getSize() {
        LockFreeMPMCQueueNode headValue = getHeadValue();
        int i = 0;
        while (true) {
            headValue = (LockFreeMPMCQueueNode) headValue.getNextValue();
            if (headValue == null) {
                return i;
            }
            i++;
        }
    }
}
