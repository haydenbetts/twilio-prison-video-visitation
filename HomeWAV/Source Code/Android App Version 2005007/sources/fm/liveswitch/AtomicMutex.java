package fm.liveswitch;

public class AtomicMutex {
    private AtomicInteger __lock = new AtomicInteger(0);

    public boolean getIsLocked() {
        return this.__lock.getValue() == 1;
    }

    public void release() {
        this.__lock.compareAndSwap(1, 0);
    }

    public boolean tryLock() {
        return this.__lock.compareAndSwap(0, 1) == 0;
    }
}
