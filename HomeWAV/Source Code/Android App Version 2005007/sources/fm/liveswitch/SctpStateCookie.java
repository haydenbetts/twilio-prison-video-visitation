package fm.liveswitch;

class SctpStateCookie {
    private long _lastReceivedTsnFromPeer;
    private long _myVerificationTag;
    private int _numberOfOutboundStreams;
    private boolean _partialReliabilityExtensionSupported;
    private long _peerReceiverWindowCredit;
    private long _peerVerificationTag;
    private DataBuffer _secretKey;
    private long _timestamp;

    public static byte[] getBytes(SctpStateCookie sctpStateCookie) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes64(sctpStateCookie.getTimestamp(), false));
        byteCollection.addRange(Binary.toBytes32(sctpStateCookie.getMyVerificationTag(), false));
        byteCollection.addRange(Binary.toBytes32(sctpStateCookie.getPeerVerificationTag(), false));
        byteCollection.addRange(Binary.toBytes32(sctpStateCookie.getPeerReceiverWindowCredit(), false));
        byteCollection.addRange(Binary.toBytes32(sctpStateCookie.getLastReceivedTsnFromPeer(), false));
        byteCollection.addRange(Binary.toBytes16(sctpStateCookie.getNumberOfOutboundStreams(), false));
        if (sctpStateCookie.getPartialReliabilityExtensionSupported()) {
            byteCollection.addRange(Binary.toBytes8(1));
        } else {
            byteCollection.addRange(Binary.toBytes8(0));
        }
        byteCollection.addRange(MacContextBase.compute(MacType.HmacSha1, sctpStateCookie.getSecretKey(), DataBuffer.wrap(byteCollection.toArray())).toArray());
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public long getLastReceivedTsnFromPeer() {
        return this._lastReceivedTsnFromPeer;
    }

    public long getMyVerificationTag() {
        return this._myVerificationTag;
    }

    public int getNumberOfOutboundStreams() {
        return this._numberOfOutboundStreams;
    }

    public boolean getPartialReliabilityExtensionSupported() {
        return this._partialReliabilityExtensionSupported;
    }

    public long getPeerReceiverWindowCredit() {
        return this._peerReceiverWindowCredit;
    }

    public long getPeerVerificationTag() {
        return this._peerVerificationTag;
    }

    public DataBuffer getSecretKey() {
        return this._secretKey;
    }

    public long getTimestamp() {
        return this._timestamp;
    }

    public static SctpStateCookie parseBytes(byte[] bArr, IntegerHolder integerHolder, DataBuffer dataBuffer) {
        byte[] bArr2 = bArr;
        IntegerHolder integerHolder2 = integerHolder;
        long fromBytes64 = Binary.fromBytes64(bArr2, 0, false);
        long fromBytes32 = Binary.fromBytes32(bArr2, 8, false);
        long fromBytes322 = Binary.fromBytes32(bArr2, 12, false);
        long fromBytes323 = Binary.fromBytes32(bArr2, 16, false);
        long fromBytes324 = Binary.fromBytes32(bArr2, 20, false);
        int fromBytes16 = Binary.fromBytes16(bArr2, 24, false);
        boolean z = Binary.fromBytes8(bArr2, 26) != 0;
        byte[] bArr3 = new byte[27];
        BitAssistant.copy(bArr2, 0, bArr3, 0, ArrayExtensions.getLength(bArr3));
        byte[] array = MacContextBase.compute(MacType.HmacSha1, dataBuffer, DataBuffer.wrap(bArr3)).toArray();
        int length = ArrayExtensions.getLength(array);
        byte[] bArr4 = new byte[length];
        long j = fromBytes64;
        int i = fromBytes16;
        BitAssistant.copy(bArr2, 27, bArr4, 0, ArrayExtensions.getLength(bArr4));
        if (BitAssistant.sequencesAreEqualConstantTime(bArr4, array)) {
            integerHolder2.setValue(length + 27);
            return new SctpStateCookie(fromBytes32, fromBytes322, fromBytes323, fromBytes324, i, j, dataBuffer, z);
        }
        integerHolder2.setValue(0);
        return null;
    }

    public static SctpStateCookie parseBytes(byte[] bArr, int i, IntegerHolder integerHolder, DataBuffer dataBuffer) {
        byte[] bArr2 = new byte[(ArrayExtensions.getLength(bArr) - i)];
        BitAssistant.copy(bArr, i, bArr2, 0, ArrayExtensions.getLength(bArr2));
        return parseBytes(bArr2, integerHolder, dataBuffer);
    }

    public SctpStateCookie(long j, long j2, long j3, long j4, int i, long j5, DataBuffer dataBuffer, boolean z) {
        setMyVerificationTag(j);
        setPeerVerificationTag(j2);
        setPeerReceiverWindowCredit(j3);
        setLastReceivedTsnFromPeer(j4);
        setNumberOfOutboundStreams(i);
        setTimestamp(j5);
        setSecretKey(dataBuffer);
        setPartialReliabilityExtensionSupported(z);
    }

    public void setLastReceivedTsnFromPeer(long j) {
        this._lastReceivedTsnFromPeer = j;
    }

    public void setMyVerificationTag(long j) {
        this._myVerificationTag = j;
    }

    public void setNumberOfOutboundStreams(int i) {
        this._numberOfOutboundStreams = i;
    }

    public void setPartialReliabilityExtensionSupported(boolean z) {
        this._partialReliabilityExtensionSupported = z;
    }

    public void setPeerReceiverWindowCredit(long j) {
        this._peerReceiverWindowCredit = j;
    }

    public void setPeerVerificationTag(long j) {
        this._peerVerificationTag = j;
    }

    public void setSecretKey(DataBuffer dataBuffer) {
        this._secretKey = dataBuffer;
    }

    public void setTimestamp(long j) {
        this._timestamp = j;
    }
}
