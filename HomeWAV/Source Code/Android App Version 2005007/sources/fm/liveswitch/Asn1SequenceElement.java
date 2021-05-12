package fm.liveswitch;

class Asn1SequenceElement {
    private byte[] _default;
    private boolean _optional;
    private Asn1Any _value;

    public Asn1SequenceElement(Asn1Any asn1Any) {
        this(asn1Any, (byte[]) null, false);
    }

    public Asn1SequenceElement(Asn1Any asn1Any, byte[] bArr) {
        this(asn1Any, bArr, false);
    }

    public Asn1SequenceElement(Asn1Any asn1Any, byte[] bArr, boolean z) {
        setValue(asn1Any);
        setOptional(z);
        setDefault(bArr);
    }

    public byte[] getDefault() {
        return this._default;
    }

    public boolean getOptional() {
        return this._optional;
    }

    public Asn1Any getValue() {
        return this._value;
    }

    public void setDefault(byte[] bArr) {
        this._default = bArr;
    }

    public void setOptional(boolean z) {
        this._optional = z;
    }

    public void setValue(Asn1Any asn1Any) {
        this._value = asn1Any;
    }
}
