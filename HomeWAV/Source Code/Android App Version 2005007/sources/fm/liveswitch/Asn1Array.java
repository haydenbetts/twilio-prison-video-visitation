package fm.liveswitch;

abstract class Asn1Array extends Asn1Any {
    private Asn1Any[] _values;

    protected Asn1Array() {
    }

    public Asn1Any[] getValues() {
        return this._values;
    }

    public void setValues(Asn1Any[] asn1AnyArr) {
        this._values = asn1AnyArr;
    }
}
