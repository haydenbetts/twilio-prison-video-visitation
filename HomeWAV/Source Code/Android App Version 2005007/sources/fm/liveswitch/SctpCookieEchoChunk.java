package fm.liveswitch;

class SctpCookieEchoChunk extends SctpControlChunk {
    private byte[] _cookieBytes;

    public static byte[] getBytes(SctpCookieEchoChunk sctpCookieEchoChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpCookieEchoChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.addRange(sctpCookieEchoChunk.getCookieBytes());
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getCookieBytes() {
        return this._cookieBytes;
    }

    public static SctpCookieEchoChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            Log.debug("SCTP manager received COOKIE_ECHO chunk from the other party");
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            byte[] bArr2 = new byte[(fromBytes16 - 4)];
            BitAssistant.copy(bArr, 4, bArr2, 0, ArrayExtensions.getLength(bArr2));
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpCookieEchoChunk(bArr2);
        } catch (Exception unused) {
            Log.warn("Could not parse SCTP Cookie Echo chunk");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpCookieEchoChunk(SctpStateCookie sctpStateCookie) {
        super.setCanBundleWithDataAndSackChunks(true);
        super.setType(SctpChunkType.getCookieEcho());
        setCookieBytes(sctpStateCookie.getBytes());
    }

    public SctpCookieEchoChunk(byte[] bArr) {
        super.setCanBundleWithDataAndSackChunks(true);
        super.setType(SctpChunkType.getCookieEcho());
        setCookieBytes(bArr);
        super.setUnrecognized(false);
    }

    public void setCookieBytes(byte[] bArr) {
        this._cookieBytes = bArr;
    }
}
