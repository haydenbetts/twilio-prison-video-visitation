package fm.liveswitch;

class SctpGenericChunk extends SctpChunk {
    private byte[] _chunkBytes;

    public static byte[] getBytes(SctpGenericChunk sctpGenericChunk) {
        return sctpGenericChunk.getChunkBytes();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getChunkBytes() {
        return this._chunkBytes;
    }

    public static SctpGenericChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            integerHolder.setValue(SctpChunk.calculatePaddingBytes(fromBytes16) + fromBytes16);
            byte[] bArr2 = new byte[integerHolder.getValue()];
            BitAssistant.copy(bArr, 0, bArr2, 0, fromBytes16);
            return new SctpGenericChunk(bArr2);
        } catch (Exception unused) {
            Log.warn("Could not parse an unknown SCTP chunk.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpGenericChunk(byte[] bArr) {
        setChunkBytes(bArr);
        super.setType(bArr[0]);
        super.setUnrecognized(true);
    }

    public void setChunkBytes(byte[] bArr) {
        this._chunkBytes = bArr;
    }
}
