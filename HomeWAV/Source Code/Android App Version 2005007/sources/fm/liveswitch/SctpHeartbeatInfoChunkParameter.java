package fm.liveswitch;

class SctpHeartbeatInfoChunkParameter extends SctpTlvParameter {
    private byte[] _senderSpecificHeartbeatInfo;

    public static byte[] getBytes(SctpHeartbeatInfoChunkParameter sctpHeartbeatInfoChunkParameter) {
        ByteCollection byteCollection = new ByteCollection();
        try {
            byteCollection.addRange(Binary.toBytes16(sctpHeartbeatInfoChunkParameter.getType(), false));
            byte[] senderSpecificHeartbeatInfo = sctpHeartbeatInfoChunkParameter.getSenderSpecificHeartbeatInfo();
            byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(senderSpecificHeartbeatInfo) + 4, false));
            byteCollection.addRange(senderSpecificHeartbeatInfo);
            SctpChunk.addPadding(byteCollection);
            return byteCollection.toArray();
        } catch (Exception unused) {
            Log.error("SCTP: could not generate IPv4ChunkParameter");
            return null;
        }
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public byte[] getSenderSpecificHeartbeatInfo() {
        return this._senderSpecificHeartbeatInfo;
    }

    public static SctpHeartbeatInfoChunkParameter parseBytes(byte[] bArr, IntegerHolder integerHolder) {
        try {
            int fromBytes16 = Binary.fromBytes16(bArr, 2, false);
            int i = fromBytes16 - 4;
            byte[] bArr2 = new byte[i];
            BitAssistant.copy(bArr, 4, bArr2, 0, i);
            integerHolder.setValue(fromBytes16 + SctpChunk.calculatePaddingBytes(fromBytes16));
            return new SctpHeartbeatInfoChunkParameter(bArr2);
        } catch (Exception unused) {
            Log.debug("Could not read HeartbeatInfoChunkParameter.");
            integerHolder.setValue(0);
            return null;
        }
    }

    public SctpHeartbeatInfoChunkParameter(byte[] bArr) {
        super.setType(1);
        setSenderSpecificHeartbeatInfo(bArr);
    }

    public void setSenderSpecificHeartbeatInfo(byte[] bArr) {
        this._senderSpecificHeartbeatInfo = bArr;
    }
}
