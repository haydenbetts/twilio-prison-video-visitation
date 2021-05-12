package fm.liveswitch;

class SctpStaleCookieError extends SctpErrorCause {
    private long _measureOfStaleness;

    public static byte[] getBytes(SctpStaleCookieError sctpStaleCookieError) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpStaleCookieError.getCauseCode(), false));
        byteCollection.addRange(Binary.toBytes16(8, false));
        byteCollection.addRange(Binary.toBytes32(sctpStaleCookieError.getMeasureOfStaleness(), false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public long getMeasureOfStaleness() {
        return this._measureOfStaleness;
    }

    public static SctpStaleCookieError parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(8);
            return new SctpStaleCookieError(new NullableLong(Binary.fromBytes32(bArr, 4, false)));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpStaleCookieError(NullableLong nullableLong) {
        super.setCauseCode(3);
        setMeasureOfStaleness(nullableLong.getHasValue() ? nullableLong.getValueOrDefault() : 0);
        Log.debug(StringExtensions.format("SCTP Error: stale cookie by {0}", (Object) nullableLong.toString()));
    }

    public void setMeasureOfStaleness(long j) {
        this._measureOfStaleness = j;
    }
}
