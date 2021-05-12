package fm.liveswitch;

class Asn1Unknown extends Asn1Any {
    private boolean _isConstructed;
    private boolean _isIndefinite;
    private int _klass;
    private int _tag;
    private byte[] _value;

    public Asn1Unknown(int i, int i2, boolean z, boolean z2) {
        this(i, i2, z, z2, (byte[]) null);
    }

    public Asn1Unknown(int i, int i2, boolean z, boolean z2, byte[] bArr) {
        setTag(i);
        setKlass(i2);
        setIsConstructed(z);
        setIsIndefinite(z2);
        setValue(bArr);
    }

    public byte[] getContents() {
        return getValue();
    }

    public boolean getIsConstructed() {
        return this._isConstructed;
    }

    public boolean getIsIndefinite() {
        return this._isIndefinite;
    }

    public int getKlass() {
        return this._klass;
    }

    public void getProperties(IntegerHolder integerHolder, IntegerHolder integerHolder2, BooleanHolder booleanHolder, BooleanHolder booleanHolder2) {
        integerHolder.setValue(getTag());
        integerHolder2.setValue(getKlass());
        booleanHolder.setValue(getIsConstructed());
        booleanHolder2.setValue(getIsIndefinite());
    }

    public int getTag() {
        return this._tag;
    }

    public byte[] getValue() {
        return this._value;
    }

    public static Asn1Unknown parseContents(int i, int i2, boolean z, boolean z2, byte[] bArr) {
        return new Asn1Unknown(i, i2, z, z2, bArr);
    }

    public void setIsConstructed(boolean z) {
        this._isConstructed = z;
    }

    public void setIsIndefinite(boolean z) {
        this._isIndefinite = z;
    }

    public void setKlass(int i) {
        this._klass = i;
    }

    public void setTag(int i) {
        this._tag = i;
    }

    public void setValue(byte[] bArr) {
        this._value = bArr;
    }

    public static Asn1Any unwrap(Asn1Any asn1Any, IntegerHolder integerHolder) {
        Asn1Unknown asn1Unknown = (Asn1Unknown) Global.tryCast(asn1Any, Asn1Unknown.class);
        if (asn1Unknown == null) {
            integerHolder.setValue(-1);
            return null;
        }
        integerHolder.setValue(asn1Unknown.getTag());
        return Asn1Any.parseBytes(asn1Unknown.getValue());
    }
}
