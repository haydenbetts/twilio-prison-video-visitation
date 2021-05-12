package fm.liveswitch;

class SctpChunkListChunkParameter extends SctpTlvParameter {
    private byte[] _chunkList;

    public static byte[] getBytes(SctpChunkListChunkParameter sctpChunkListChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.addRange(Binary.toBytes16(sctpChunkListChunkParameter.getType(), false));
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(sctpChunkListChunkParameter.getChunkList()) + 4, false));
        byteCollection.addRange(sctpChunkListChunkParameter.getChunkList());
        SctpChunk.addPadding(byteCollection);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getChunkList() {
        return this._chunkList;
    }

    public static SctpChunkListChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            byte[] bArr2 = new byte[(fromBytes16 - 4)];
            BitAssistant.copy(bArr, 4, bArr2, 0, ArrayExtensions.getLength(bArr2));
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpChunkListChunkParameter(bArr2);
        } catch (Exception unused) {
            Log.debug("Could not read ChunkListChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpChunkListChunkParameter(byte[] bArr) {
        super.setType(32771);
        setChunkList(bArr);
    }

    public void setChunkList(byte[] bArr) {
        this._chunkList = bArr;
    }
}
