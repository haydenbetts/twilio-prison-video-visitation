package fm.liveswitch;

public class Holder<T> {
    public T value;

    public Holder(T t) {
        this.value = t;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T t) {
        this.value = t;
    }
}
