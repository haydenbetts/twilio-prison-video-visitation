package fm.liveswitch;

class ReliableRtcDcepDataChannelAck extends ReliableRtcDcepMessage {
    public static byte[] getBytes(ReliableRtcDcepDataChannelAck reliableRtcDcepDataChannelAck) {
        return new byte[]{BitAssistant.castByte(reliableRtcDcepDataChannelAck.getMessageType())};
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public static ReliableRtcDcepDataChannelAck parseBytes(DataBuffer dataBuffer) {
        return new ReliableRtcDcepDataChannelAck();
    }

    public ReliableRtcDcepDataChannelAck() {
        super.setMessageType(2);
    }
}
