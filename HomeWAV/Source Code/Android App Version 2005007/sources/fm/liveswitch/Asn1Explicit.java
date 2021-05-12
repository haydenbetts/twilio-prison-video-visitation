package fm.liveswitch;

import fm.liveswitch.Asn1Any;

class Asn1Explicit<T extends Asn1Any> extends Asn1Any {
    private int _klass;
    private int _tag;
    private T _value;

    public Asn1Explicit() {
    }

    public Asn1Explicit(int i, int i2, T t) {
        setTag(i);
        setKlass(i2);
        setValue(t);
    }

    public byte[] getContents() {
        return getValue().getBytes();
    }

    public int getKlass() {
        return this._klass;
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(getTag());
        integerHolder2.setValue(getKlass());
        booleanHolder.setValue(true);
        booleanHolder2.setValue(false);
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

    public static <X extends Asn1Any> Asn1Explicit<X> wrap(int i, X x) {
        if (x == null) {
            return null;
        }
        return new Asn1Explicit<>(i, 128, x);
    }
}
