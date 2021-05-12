package fm.liveswitch;

import fm.liveswitch.Asn1Any;

class Asn1Implicit<T extends Asn1Any> extends Asn1Any {
    private int _klass;
    private int _tag;
    private T _value;

    public Asn1Implicit() {
    }

    public Asn1Implicit(int i, int i2, T t) {
        setTag(i);
        setKlass(i2);
        setValue(t);
    }

    public byte[] getContents() {
        return getValue().getContents();
    }

    public int getKlass() {
        return this._klass;
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        getValue().getProperties(integerHolder, integerHolder2, booleanHolder, booleanHolder2);
        integerHolder.setValue(getTag());
        integerHolder2.setValue(getKlass());
    }

    public int getTag() {
        return this._tag;
    }

    public T getValue() {
        return this._value;
    }

    public void setKlass(int i) {
        this._klass = i;
    }

    public void setTag(int i) {
        this._tag = i;
    }

    public void setValue(T t) {
        this._value = t;
    }

    public static <X extends Asn1Any> Asn1Implicit<X> wrap(int i, X x) {
        if (x == null) {
            return null;
        }
        return new Asn1Implicit<>(i, 128, x);
    }
}
