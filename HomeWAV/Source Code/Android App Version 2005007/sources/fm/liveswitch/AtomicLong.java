package fm.liveswitch;

public class AtomicLong {
    java.util.concurrent.atomic.AtomicLong _long;

    public AtomicLong() {
        this._long = new java.util.concurrent.atomic.AtomicLong();
    }

    public AtomicLong(long j) {
        this._long = new java.util.concurrent.atomic.AtomicLong(j);
    }

    public long compareAndSwap(long j, long j2) {
        long j3 = this._long.get();
        this._long.compareAndSet(j, j2);
        return j3;
    }

    public long getValue() {
        return this._long.get();
    }

    public long add(long j) {
        return this._long.addAndGet(j);
    }

    public long subtract(long j) {
        return this._long.addAndGet(-j);
    }

    public long increment() {
        return this._long.incrementAndGet();
    }

    public long decrement() {
        return this._long.decrementAndGet();
    }
}
