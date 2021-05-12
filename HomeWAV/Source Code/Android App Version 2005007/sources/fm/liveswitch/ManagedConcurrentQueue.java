package fm.liveswitch;

public class ManagedConcurrentQueue<T> {
    private InternalConcurrentQueue<T> __queue = new InternalConcurrentQueue<>();

    public void enqueue(T t) {
        this.__queue.enqueue(t);
    }

    public int getCount() {
        return this.__queue.getCount();
    }

    public boolean getIsEmpty() {
        return this.__queue.getIsEmpty();
    }

    public boolean tryDequeue(Holder<T> holder) {
        return this.__queue.tryDequeue(holder);
    }

    public boolean tryPeek(Holder<T> holder) {
        return this.__queue.tryPeek(holder);
    }
}
