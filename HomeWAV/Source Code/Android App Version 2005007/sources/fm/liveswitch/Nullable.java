package fm.liveswitch;

public class Nullable<T> {
    protected T value;

    public Nullable() {
        this.value = null;
    }

    public Nullable(T t) {
        this.value = t;
    }

    public boolean hasValue() {
        return this.value != null;
    }

    public boolean getHasValue() {
        return hasValue();
    }
}
