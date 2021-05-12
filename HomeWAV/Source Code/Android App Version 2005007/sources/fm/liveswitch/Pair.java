package fm.liveswitch;

public class Pair<T1, T2> {
    private T1 _item1;
    private T2 _item2;

    public boolean equals(Object obj) {
        Pair pair = (Pair) Global.tryCast(obj, Pair.class);
        return obj != null && (pair.getItem1() != null ? !(!pair.getItem1().equals(getItem1()) || pair.getItem2() != null ? !pair.getItem2().equals(getItem2()) : getItem2() != null) : getItem1() == null);
    }

    public T1 getItem1() {
        return this._item1;
    }

    public T2 getItem2() {
        return this._item2;
    }

    public int hashCode() {
        return getItem1().hashCode() + getItem2().hashCode();
    }

    public Pair(T1 t1, T2 t2) {
        setItem1(t1);
        setItem2(t2);
    }

    private void setItem1(T1 t1) {
        this._item1 = t1;
    }

    private void setItem2(T2 t2) {
        this._item2 = t2;
    }
}
