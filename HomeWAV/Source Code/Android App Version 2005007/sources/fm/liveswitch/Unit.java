package fm.liveswitch;

public class Unit<T> {
    private T _item;

    public T getItem() {
        return this._item;
    }

    private void setItem(T t) {
        this._item = t;
    }

    public Unit(T t) {
        setItem(t);
    }
}
