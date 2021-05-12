package fm.liveswitch;

class SctpCommonHeader {
    private long _checksum;
    private int _destinationPortNumber;
    private int _sourcePortNumber;
    private long _verificationTag;

    public static byte[] getBytes(SctpCommonHeader sctpCommonHeader) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpCommonHeader.getSourcePortNumber(), false));
        byteCollection.addRange(Binary.toBytes16(sctpCommonHeader.getDestinationPortNumber(), false));
        byteCollection.addRange(Binary.toBytes32(sctpCommonHeader.getVerificationTag(), false));
        byteCollection.addRange(Binary.toBytes32(sctpCommonHeader.getChecksum(), false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public long getChecksum() {
        return this._checksum;
    }

    public int getDestinationPortNumber() {
        return this._destinationPortNumber;
    }

    public int getSourcePortNumber() {
        return this._sourcePortNumber;
    }

    public long getVerificationTag() {
        return this._verificationTag;
    }

    public static SctpCommonHeader parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 0, false);
            int fromBytes162 = Binary.fromBytes16(bArr, 2, false);
            long fromBytes32 = Binary.fromBytes32(bArr, 4, false);
            long fromBytes322 = Binary.fromBytes32(bArr, 8, false);
            integerHolder.setValue(12);
            return new SctpCommonHeader(fromBytes16, fromBytes162, fromBytes32, fromBytes322);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpCommonHeader(int i, int i2, long j) {
        setSourcePortNumber(i);
        setDestinationPortNumber(i2);
        setVerificationTag(j);
        setChecksum(0);
    }

    public SctpCommonHeader(int i, int i2, long j, long j2) {
        setSourcePortNumber(i);
        setDestinationPortNumber(i2);
        setVerificationTag(j);
        setChecksum(j2);
    }

    public void setChecksum(long j) {
        this._checksum = j;
    }

    public void setDestinationPortNumber(int i) {
        this._destinationPortNumber = i;
    }

    public void setSourcePortNumber(int i) {
        this._sourcePortNumber = i;
    }

    public void setVerificationTag(long j) {
        this._verificationTag = j;
    }
}
