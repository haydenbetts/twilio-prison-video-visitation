package fm.liveswitch;

class Asn1ArrayElement {
    private byte[] _default;
    private boolean _optional;
    private Class _type;

    public Asn1ArrayElement(Class cls) {
        this(cls, (byte[]) null, false);
    }

    public Asn1ArrayElement(Class cls, byte[] bArr) {
        this(cls, bArr, false);
    }

    public Asn1ArrayElement(Class cls, byte[] bArr, boolean z) {
        setType(cls);
        setOptional(z);
        setDefault(bArr);
    }

    public byte[] getDefault() {
        return this._default;
    }

    public boolean getOptional() {
        return this._optional;
    }

    public Class getType() {
        return this._type;
    }

    public void setDefault(byte[] bArr) {
        this._default = bArr;
    }

    public void setOptional(boolean z) {
        this._optional = z;
    }

    public void setType(Class cls) {
        this._type = cls;
    }
}
