package fm.liveswitch;

public class Triple<T1, T2, T3> {
    private T1 _item1;
    private T2 _item2;
    private T3 _item3;

    public T1 getItem1() {
        return this._item1;
    }

    public T2 getItem2() {
        return this._item2;
    }

    public T3 getItem3() {
        return this._item3;
    }

    private void setItem1(T1 t1) {
        this._item1 = t1;
    }

    private void setItem2(T2 t2) {
        this._item2 = t2;
    }

    private void setItem3(T3 t3) {
        this._item3 = t3;
    }

    public Triple(T1 t1, T2 t2, T3 t3) {
        setItem1(t1);
        setItem2(t2);
        setItem3(t3);
    }
}
