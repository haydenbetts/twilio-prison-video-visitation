package fm.liveswitch;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ManagedLock {
    protected Lock _lock = new ReentrantLock();

    public void lock() {
        this._lock.lock();
    }

    public void unlock() {
        this._lock.unlock();
    }
}
