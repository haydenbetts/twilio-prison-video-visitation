package fm.liveswitch;

public class MutableUnit<T> {
    private T _item;

    public T getItem() {
        return this._item;
    }

    public MutableUnit(T t) {
        setItem(t);
    }

    public void setItem(T t) {
        this._item = t;
    }
}
