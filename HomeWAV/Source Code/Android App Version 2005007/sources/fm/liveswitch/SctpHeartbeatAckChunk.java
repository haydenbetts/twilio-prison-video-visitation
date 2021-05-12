package fm.liveswitch;

class SctpHeartbeatAckChunk extends SctpControlChunk {
    private SctpTlvParameter _heartbeatInfo;

    public static byte[] getBytes(SctpHeartbeatAckChunk sctpHeartbeatAckChunk) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add((byte) sctpHeartbeatAckChunk.getType());
        byteCollection.add((byte) 0);
        SctpTlvParameter heartbeatInfo = sctpHeartbeatAckChunk.getHeartbeatInfo();
        if (heartbeatInfo == null) {
            return null;
        }
        byteCollection.addRange(heartbeatInfo.getBytes());
        byteCollection.insertRange(2, Binary.toBytes16(byteCollection.getCount() + 2, false));
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public SctpTlvParameter getHeartbeatInfo() {
        return this._heartbeatInfo;
    }

    public static SctpHeartbeatAckChunk parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            if (Binary.fromBytes16(bArr, 2, false) < 4) {
                integerHolder.setValue(4);
                Log.debug("SCTP received improperly formatted Heartbeat Ack chunk");
                return null;
            }
            integerHolder.setValue(integerHolder.getValue() + 4);
            return new SctpHeartbeatAckChunk((SctpHeartbeatInfoChunkParameter) SctpTlvParameter.parseBytes(bArr, 4, integerHolder));
        } catch (Exception unused) {
            integerHolder.setValue(0);
            Log.warn("Could not process SCTP Heartbeat Ack chunk");
            return null;
        }
    }

    public SctpHeartbeatAckChunk(SctpTlvParameter sctpTlvParameter) {
        super.setType(SctpChunkType.getHeartbeatAck());
        setHeartbeatInfo(sctpTlvParameter);
        super.setCanBundleWithDataAndSackChunks(false);
        super.setUnrecognized(false);
    }

    public void setHeartbeatInfo(SctpTlvParameter sctpTlvParameter) {
        this._heartbeatInfo = sctpTlvParameter;
    }
}
