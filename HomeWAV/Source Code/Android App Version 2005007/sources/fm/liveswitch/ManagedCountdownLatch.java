package fm.liveswitch;

public class ManagedCountdownLatch {
    private AtomicInteger __counter;
    private Promise<Object> __waitPromise;

    public void decrement() {
        if (this.__counter.decrement() == 0) {
            this.__waitPromise.resolveAsync(null);
        }
    }

    public int getCount() {
        return this.__counter.getValue();
    }

    public ManagedCountdownLatch() {
        reset();
    }

    public ManagedCountdownLatch(int i) {
        reset(i);
    }

    public void reset() {
        this.__counter = new AtomicInteger();
        this.__waitPromise = new Promise<>();
    }

    public void reset(int i) {
        this.__counter = new AtomicInteger(i);
        this.__waitPromise = new Promise<>();
    }

    public void setCount(int i) {
        if (this.__counter.add(i) == 0) {
            this.__waitPromise.resolveAsync(null);
        }
    }

    public String toString() {
        return StringExtensions.format("Countdown Latch ({0})", (Object) IntegerExtensions.toString(Integer.valueOf(getCount())));
    }

    public Future<Object> waitAsync() {
        if (this.__counter.getValue() <= 0) {
            return PromiseBase.resolveNow(null);
        }
        return this.__waitPromise;
    }

    public void waitSync() {
        this.__waitPromise.waitForPromise();
    }
}
