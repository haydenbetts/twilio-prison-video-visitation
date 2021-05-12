package fm.liveswitch;

class ReliableRtcDcepDataChannelOpen extends ReliableRtcDcepMessage {
    private int _channelPriority;
    private ReliableChannelType _channelType;
    private String _label;
    private long _reliabilityParameter;
    private String _subProtocol;

    public static byte[] getBytes(ReliableRtcDcepDataChannelOpen reliableRtcDcepDataChannelOpen) {
        ByteCollection byteCollection = new ByteCollection();
        byteCollection.add(BitAssistant.castByte(reliableRtcDcepDataChannelOpen.getMessageType()));
        byteCollection.add(BitAssistant.castByte(ReliableRtcDcepMessage.getByteFromChannelType(reliableRtcDcepDataChannelOpen.getChannelType())));
        byteCollection.addRange(Binary.toBytes16(reliableRtcDcepDataChannelOpen.getChannelPriority(), false));
        byteCollection.addRange(Binary.toBytes32(reliableRtcDcepDataChannelOpen.getReliabilityParameter(), false));
        byte[] bytes = Encoding.getUtf8().getBytes(reliableRtcDcepDataChannelOpen.getLabel());
        byte[] bytes2 = Encoding.getUtf8().getBytes(reliableRtcDcepDataChannelOpen.getSubProtocol());
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(bytes), false));
        byteCollection.addRange(Binary.toBytes16(ArrayExtensions.getLength(bytes2), false));
        byteCollection.addRange(bytes);
        byteCollection.addRange(bytes2);
        return byteCollection.toArray();
    }

    public byte[] getBytes() {
        return getBytes(this);
    }

    public int getChannelPriority() {
        return this._channelPriority;
    }

    public ReliableChannelType getChannelType() {
        return this._channelType;
    }

    public String getLabel() {
        return this._label;
    }

    public long getReliabilityParameter() {
        return this._reliabilityParameter;
    }

    public String getSubProtocol() {
        return this._subProtocol;
    }

    public static ReliableRtcDcepDataChannelOpen parseBytes(DataBuffer dataBuffer) {
        try {
            ReliableChannelType parseChannelTypeByte = ReliableRtcDcepMessage.parseChannelTypeByte(dataBuffer.read8(1));
            int read16 = dataBuffer.read16(8);
            return new ReliableRtcDcepDataChannelOpen(parseChannelTypeByte, dataBuffer.readUtf8String(12, read16), dataBuffer.readUtf8String(read16 + 12, dataBuffer.read16(10)));
        } catch (Exception unused) {
            return null;
        }
    }

    public ReliableRtcDcepDataChannelOpen(ReliableChannelType reliableChannelType) {
        this(reliableChannelType, "", "");
    }

    public ReliableRtcDcepDataChannelOpen(ReliableChannelType reliableChannelType, String str, String str2) {
        setChannelType(reliableChannelType);
        setSubProtocol(str2);
        super.setMessageType(3);
        setLabel(str);
        setChannelPriority(256);
        setReliabilityParameter(0);
    }

    private void setChannelPriority(int i) {
        this._channelPriority = i;
    }

    private void setChannelType(ReliableChannelType reliableChannelType) {
        this._channelType = reliableChannelType;
    }

    private void setLabel(String str) {
        this._label = str;
    }

    private void setReliabilityParameter(long j) {
        this._reliabilityParameter = j;
    }

    private void setSubProtocol(String str) {
        this._subProtocol = str;
    }
}
