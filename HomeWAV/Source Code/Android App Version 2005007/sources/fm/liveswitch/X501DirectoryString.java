package fm.liveswitch;

class X501DirectoryString {
    private String _value;

    public static X501DirectoryString fromAsn1(Asn1Any asn1Any) {
        Asn1Utf8String asn1Utf8String = (Asn1Utf8String) Global.tryCast(asn1Any, Asn1Utf8String.class);
        if (asn1Utf8String != null) {
            return new X501DirectoryString(asn1Utf8String.getValue());
        }
        Asn1PrintableString asn1PrintableString = (Asn1PrintableString) Global.tryCast(asn1Any, Asn1PrintableString.class);
        if (asn1PrintableString != null) {
            return new X501DirectoryString(asn1PrintableString.getValue());
        }
        Asn1BmpString asn1BmpString = (Asn1BmpString) Global.tryCast(asn1Any, Asn1BmpString.class);
        if (asn1BmpString != null) {
            return new X501DirectoryString(asn1BmpString.getValue());
        }
        Asn1UniversalString asn1UniversalString = (Asn1UniversalString) Global.tryCast(asn1Any, Asn1UniversalString.class);
        if (asn1UniversalString != null) {
            return new X501DirectoryString(asn1UniversalString.getValue());
        }
        return null;
    }

    public String getValue() {
        return this._value;
    }

    public void setValue(String str) {
        this._value = str;
    }

    public Asn1Any toAsn1Bmp() {
        return new Asn1BmpString(getValue());
    }

    public Asn1Any toAsn1Printable() {
        return new Asn1PrintableString(getValue());
    }

    public Asn1Any toAsn1Universal() {
        return new Asn1UniversalString(getValue());
    }

    public Asn1Any toAsn1Utf8() {
        return new Asn1Utf8String(getValue());
    }

    public X501DirectoryString() {
    }

    public X501DirectoryString(String str) {
        setValue(str);
    }
}
