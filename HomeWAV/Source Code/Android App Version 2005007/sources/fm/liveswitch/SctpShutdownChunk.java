package fm.liveswitch;

class SctpShutdownChunk extends SctpControlChunk {
    private long _cumulativeTsnAck;

    public static byte[] getBytes(SctpShutdownChunk sctpShutdownChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpShutdownChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.addRange(Binary.toBytes16(8, false));
        byteCollection.addRange(Binary.toBytes32(sctpShutdownChunk.getCumulativeTsnAck(), false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public long getCumulativeTsnAck() {
        return this._cumulativeTsnAck;
    }

    public static SctpShutdownChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            integerHolder.setValue(8);
            return new SctpShutdownChunk(Binary.fromBytes32(bArr, 4, false));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpShutdownChunk(long j) {
        super.setType(SctpChunkType.getShutdown());
        setCumulativeTsnAck(j);
        super.setCanBundleWithDataAndSackChunks(false);
        super.setUnrecognized(false);
    }

    public void setCumulativeTsnAck(long j) {
        this._cumulativeTsnAck = j;
    }
}
