package fm.liveswitch;

abstract class ReliableRtcDcepMessage {
    private int _messageType;

    public abstract byte[] getBytes();

    public static int getByteFromChannelType(ReliableChannelType reliableChannelType) {
        if (Global.equals(reliableChannelType, ReliableChannelType.Reliable)) {
            return 0;
        }
        if (Global.equals(reliableChannelType, ReliableChannelType.ReliableUnordered)) {
            return BitAssistant.castByte(128);
        }
        if (Global.equals(reliableChannelType, ReliableChannelType.PartialReliableREXMIT)) {
            return 1;
        }
        if (Global.equals(reliableChannelType, ReliableChannelType.PartialReliableREXMITUnordered)) {
            return BitAssistant.castByte(129);
        }
        if (Global.equals(reliableChannelType, ReliableChannelType.PartialReliableTimed)) {
            return 2;
        }
        if (Global.equals(reliableChannelType, ReliableChannelType.PartialReliableTimedUnordered)) {
            return BitAssistant.castByte(130);
        }
        throw new RuntimeException(new Exception("Reliable Data: unknown channel type."));
    }

    public int getMessageType() {
        return this._messageType;
    }

    public static ReliableRtcDcepMessage parseBytes(DataBuffer dataBuffer) {
        int read8 = dataBuffer.read8(0);
        if (read8 == 3) {
            return ReliableRtcDcepDataChannelOpen.parseBytes(dataBuffer);
        }
        if (read8 == 2) {
            return ReliableRtcDcepDataChannelAck.parseBytes(dataBuffer);
        }
        return null;
    }

    public static ReliableChannelType parseChannelTypeByte(int i) {
        if (i == 0) {
            return ReliableChannelType.Reliable;
        }
        if (i == 128) {
            return ReliableChannelType.ReliableUnordered;
        }
        if (i == 1) {
            return ReliableChannelType.PartialReliableREXMIT;
        }
        if (i == 129) {
            return ReliableChannelType.PartialReliableREXMITUnordered;
        }
        if (i == 2) {
            return ReliableChannelType.PartialReliableTimed;
        }
        if (i == 130) {
            return ReliableChannelType.PartialReliableTimedUnordered;
        }
        throw new RuntimeException(new Exception("Reliable Data: unknow channel type."));
    }

    protected ReliableRtcDcepMessage() {
    }

    /* access modifiers changed from: protected */
    public void setMessageType(int i) {
        this._messageType = i;
    }

    static ReliableRtcDcepDataChannelOpen tryParseAsDataChannelOpenMessage(SctpMessage sctpMessage) {
        ReliableRtcDcepMessage parseBytes = parseBytes(sctpMessage.getPayload());
        if (parseBytes == null || parseBytes.getMessageType() != 3) {
            return null;
        }
        return (ReliableRtcDcepDataChannelOpen) parseBytes;
    }
}
