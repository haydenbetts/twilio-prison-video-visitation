package fm.liveswitch;

public class ManagedConcurrentStack<TValue> {
    private InternalConcurrentStack<TValue> __stack = new InternalConcurrentStack<>();

    public void clear() {
        this.__stack.clear();
    }

    public int getCount() {
        return this.__stack.getCount();
    }

    public boolean getIsEmpty() {
        return this.__stack.getIsEmpty();
    }

    public void push(TValue tvalue) {
        this.__stack.push(tvalue);
    }

    public boolean tryPeek(Holder<TValue> holder) {
        return this.__stack.tryPeek(holder);
    }

    public boolean tryPop(Holder<TValue> holder) {
        return this.__stack.tryPop(holder);
    }
}
