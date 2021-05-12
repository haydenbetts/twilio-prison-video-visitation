package fm.liveswitch;

class SctpShutdownAckChunk extends SctpControlChunk {
    public static byte[] getBytes(SctpShutdownAckChunk sctpShutdownAckChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpShutdownAckChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.insertRange(2, Binary.toBytes16(4, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpShutdownAckChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        integerHolder.setValue(4);
        return new SctpShutdownAckChunk();
    }

    public SctpShutdownAckChunk() {
        super.setCanBundleWithDataAndSackChunks(false);
        super.setType(SctpChunkType.getShutdownAck());
        super.setUnrecognized(false);
    }
}
