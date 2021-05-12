package fm.liveswitch;

class SctpInvalidStreamIdentifier extends SctpErrorCause {
    private int _streamIdentifier;

    public static byte[] getBytes(SctpInvalidStreamIdentifier sctpInvalidStreamIdentifier) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpInvalidStreamIdentifier.getCauseCode(), false));
        byteCollection.addRange(Binary.toBytes16(8, false));
        byteCollection.addRange(Binary.toBytes16(sctpInvalidStreamIdentifier.getStreamIdentifier(), false));
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public int getStreamIdentifier() {
        return this._streamIdentifier;
    }

    public static SctpInvalidStreamIdentifier parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 4, false);
            integerHolder.setValue(8);
            Log.debug(StringExtensions.format("SCTP Error: invalid stream identifier: {0}.", (Object) IntegerExtensions.toString(Integer.valueOf(fromBytes16))));
            return new SctpInvalidStreamIdentifier(fromBytes16);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpInvalidStreamIdentifier(int i) {
        super.setCauseCode(1);
        setStreamIdentifier(i);
    }

    public void setStreamIdentifier(int i) {
        this._streamIdentifier = i;
    }
}
