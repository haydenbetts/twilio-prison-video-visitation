package fm.liveswitch;

class X501Attribute {
    private long[] _attributeType;
    private byte[] _attributeValue;

    public X501DirectoryString attributeValueAsDirectoryString() {
        Asn1Any parseBytes = Asn1Any.parseBytes(getAttributeValue());
        if (parseBytes != null) {
            return X501DirectoryString.fromAsn1(parseBytes);
        }
        return null;
    }

    public String attributeValueAsString() {
        X501DirectoryString attributeValueAsDirectoryString = attributeValueAsDirectoryString();
        if (attributeValueAsDirectoryString != null) {
            return attributeValueAsDirectoryString.getValue();
        }
        return null;
    }

    public static X501Attribute fromAsn1(Asn1Any asn1Any) {
        Asn1Sequence asn1Sequence = (Asn1Sequence) Global.tryCast(asn1Any, Asn1Sequence.class);
        if (asn1Sequence == null || ArrayExtensions.getLength((Object[]) asn1Sequence.getValues()) < 2) {
            return null;
        }
        X501Attribute x501Attribute = new X501Attribute();
        x501Attribute.setAttributeType(((Asn1ObjectIdentifier) asn1Sequence.getValues()[0]).getValues());
        x501Attribute.setAttributeValue(asn1Sequence.getValues()[1].getBytes());
        return x501Attribute;
    }

    public long[] getAttributeType() {
        return this._attributeType;
    }

    public byte[] getAttributeValue() {
        return this._attributeValue;
    }

    public void setAttributeType(long[] jArr) {
        this._attributeType = jArr;
    }

    public void setAttributeValue(byte[] bArr) {
        this._attributeValue = bArr;
    }

    public Asn1Sequence toAsn1() {
        return new Asn1Sequence(new Asn1Any[]{new Asn1ObjectIdentifier(getAttributeType()), Asn1Any.parseBytes(getAttributeValue())});
    }

    public X501Attribute() {
    }

    public X501Attribute(long[] jArr, byte[] bArr) {
        setAttributeType(jArr);
        setAttributeValue(bArr);
    }
}
