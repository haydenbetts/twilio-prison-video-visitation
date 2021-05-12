package fm.liveswitch;

class SctpNoUserData extends SctpErrorCause {
    private long _tsn;

    public static byte[] getBytes(SctpNoUserData sctpNoUserData) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpNoUserData.getCauseCode(), false));
        byteCollection.addRange(Binary.toBytes16(8, false));
        byteCollection.addRange(Binary.toBytes32(sctpNoUserData.getTsn(), false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public long getTsn() {
        return this._tsn;
    }

    public static SctpNoUserData parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(8);
            Log.debug(StringExtensions.format("SCTP Error: no user data transmitted in DATA chunk.", new Object[0]));
            return new SctpNoUserData(Binary.fromBytes32(bArr, 4, false));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpNoUserData(long j) {
        super.setCauseCode(9);
        setTsn(j);
    }

    public void setTsn(long j) {
        this._tsn = j;
    }
}
