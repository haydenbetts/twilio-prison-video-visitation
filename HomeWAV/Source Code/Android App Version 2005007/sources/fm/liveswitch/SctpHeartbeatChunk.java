package fm.liveswitch;

class SctpHeartbeatChunk extends SctpControlChunk {
    private SctpTlvParameter _heartbeatInfo;

    public static byte[] getBytes(SctpHeartbeatChunk sctpHeartbeatChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpHeartbeatChunk.getType());
        byteCollection.add((byte) 0);
        byteCollection.addRange(sctpHeartbeatChunk.getHeartbeatInfo().getBytes());
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpTlvParameter getHeartbeatInfo() {
        return this._heartbeatInfo;
    }

    public static SctpHeartbeatChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            if (Binary.fromBytes16(bArr, 2, false) < 4) {
                integerHolder.setValue(4);
                Log.debug("SCTP received improperly formatted Heartbeat chunk");
                return null;
            }
            integerHolder.setValue(integerHolder.getValue() + 4);
            return new SctpHeartbeatChunk((SctpHeartbeatInfoChunkParameter) SctpTlvParameter.parseBytes(bArr, 4, integerHolder));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.warn("Could not process SCTP Heartbeat chunk");
            return null;
        }
    }

    public SctpHeartbeatChunk(SctpTlvParameter sctpTlvParameter) {
        super.setType(SctpChunkType.getHeartbeat());
        setHeartbeatInfo(sctpTlvParameter);
        super.setCanBundleWithDataAndSackChunks(false);
        super.setUnrecognized(false);
    }

    public void setHeartbeatInfo(SctpTlvParameter sctpTlvParameter) {
        this._heartbeatInfo = sctpTlvParameter;
    }
}
