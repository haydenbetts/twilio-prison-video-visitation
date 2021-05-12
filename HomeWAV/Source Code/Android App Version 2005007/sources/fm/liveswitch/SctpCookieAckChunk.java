package fm.liveswitch;

class SctpCookieAckChunk extends SctpControlChunk {
    public static byte[] getBytes(SctpCookieAckChunk sctpCookieAckChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpCookieAckChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.insertRange(2, Binary.toBytes16(4, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static SctpCookieAckChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        Log.debug("SCTP manager received COOKIE_ACK chunk from the other party");
        integerHolder.setValue(4);
        return new SctpCookieAckChunk();
    }

    public SctpCookieAckChunk() {
        super.setCanBundleWithDataAndSackChunks(true);
        super.setType(SctpChunkType.getCookieAck());
        super.setUnrecognized(false);
    }
}
