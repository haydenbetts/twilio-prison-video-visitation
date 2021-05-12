package fm.liveswitch;

import fm.liveswitch.Asn1Any;

abstract class Asn1ArrayOf<T extends Asn1Any> extends Asn1Any {
    private T[] _values;

    protected Asn1ArrayOf() {
    }

    public T[] getValues() {
        return this._values;
    }

    public void setValues(T[] tArr) {
        this._values = tArr;
    }
}
