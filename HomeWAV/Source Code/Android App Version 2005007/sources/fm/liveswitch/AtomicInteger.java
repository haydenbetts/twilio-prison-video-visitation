package fm.liveswitch;

public class AtomicInteger {
    java.util.concurrent.atomic.AtomicInteger _integer;

    public AtomicInteger() {
        this._integer = new java.util.concurrent.atomic.AtomicInteger();
    }

    public AtomicInteger(int i) {
        this._integer = new java.util.concurrent.atomic.AtomicInteger(i);
    }

    public int compareAndSwap(int i, int i2) {
        int i3 = this._integer.get();
        this._integer.compareAndSet(i, i2);
        return i3;
    }

    public int getValue() {
        return this._integer.get();
    }

    public int add(int i) {
        return this._integer.addAndGet(i);
    }

    public int subtract(int i) {
        return this._integer.addAndGet(-i);
    }

    public int increment() {
        return this._integer.incrementAndGet();
    }

    public int decrement() {
        return this._integer.decrementAndGet();
    }
}
