package fm.liveswitch;

class SctpStateCookieChunkParameter extends SctpTlvParameter {
    private SctpStateCookie _stateCookie;
    private byte[] _stateCookieBytes;

    public static byte[] getBytes(SctpStateCookieChunkParameter sctpStateCookieChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(7, false));
        byte[] bytes = sctpStateCookieChunkParameter.getStateCookie() != null ? sctpStateCookieChunkParameter.getStateCookie().getBytes() : sctpStateCookieChunkParameter.getStateCookieBytes();
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(bytes) + 4, false));
        byteCollection.addRange(bytes);
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpStateCookie getStateCookie() {
        return this._stateCookie;
    }

    public byte[] getStateCookieBytes() {
        return this._stateCookieBytes;
    }

    public static SctpStateCookieChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            byte[] bArr2 = new byte[(fromBytes16 - 4)];
            BitAssistant.copy(bArr, 4, bArr2, 0, ArrayExtensions.getLength(bArr2));
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpStateCookieChunkParameter(bArr2);
        } catch (Exception unused) {
            Log.debug("Could not read StateCookieChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public static SctpStateCookieChunkParameter parseBytes(byte[] bArr, int i, IntegerHolder integerHolder) {
        try {
            byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) - i)];
            BitAssistant.copy(bArr, i, bArr2, 0, ArrayExtensions.getLength(bArr2));
            return parseBytes(bArr2, integerHolder);
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpStateCookieChunkParameter(SctpStateCookie sctpStateCookie) {
        super.setType(7);
        setStateCookie(sctpStateCookie);
    }

    public SctpStateCookieChunkParameter(byte[] bArr) {
        super.setType(7);
        setStateCookieBytes(bArr);
    }

    public void setStateCookie(SctpStateCookie sctpStateCookie) {
        this._stateCookie = sctpStateCookie;
    }

    public void setStateCookieBytes(byte[] bArr) {
        this._stateCookieBytes = bArr;
    }
}
