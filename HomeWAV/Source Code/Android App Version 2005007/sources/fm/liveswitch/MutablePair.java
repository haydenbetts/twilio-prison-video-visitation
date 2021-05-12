package fm.liveswitch;

public class MutablePair<T1, T2> {
    private T1 _item1;
    private T2 _item2;

    public T1 getItem1() {
        return this._item1;
    }

    public T2 getItem2() {
        return this._item2;
    }

    public MutablePair(T1 t1, T2 t2) {
        setItem1(t1);
        setItem2(t2);
    }

    public void setItem1(T1 t1) {
        this._item1 = t1;
    }

    public void setItem2(T2 t2) {
        this._item2 = t2;
    }
}
