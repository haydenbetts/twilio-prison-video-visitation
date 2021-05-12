package fm.liveswitch;

class SctpCookiePreservativeChunkParameter extends SctpTlvParameter {
    private long _suggestedCookieLifeSpanIncrement;

    public static byte[] getBytes(SctpCookiePreservativeChunkParameter sctpCookiePreservativeChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(9, false));
        byteCollection.addRange(Binary.toBytes16(8, false));
        byteCollection.addRange(Binary.toBytes32(sctpCookiePreservativeChunkParameter.getSuggestedCookieLifeSpanIncrement(), false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public long getSuggestedCookieLifeSpanIncrement() {
        return this._suggestedCookieLifeSpanIncrement;
    }

    public static SctpCookiePreservativeChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(8);
            return new SctpCookiePreservativeChunkParameter(Binary.fromBytes32(bArr, 4, false));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.debug("Could not read CookiePreservativeChunkParameter.");
            return null;
        }
    }

    public SctpCookiePreservativeChunkParameter(long j) {
        long min = MathAssistant.min(4294967295L, j);
        super.setType(9);
        setSuggestedCookieLifeSpanIncrement(min);
    }

    public void setSuggestedCookieLifeSpanIncrement(long j) {
        this._suggestedCookieLifeSpanIncrement = j;
    }
}
