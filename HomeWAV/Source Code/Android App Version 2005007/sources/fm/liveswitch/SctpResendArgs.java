package fm.liveswitch;

class SctpResendArgs {
    private DataBuffer _packetBytes;
    private SctpTcbState _state;
    private byte _type;

    public DataBuffer getPacketBytes() {
        return this._packetBytes;
    }

    public SctpTcbState getState() {
        return this._state;
    }

    public byte getType() {
        return this._type;
    }

    public SctpResendArgs(DataBuffer dataBuffer, SctpTcbState sctpTcbState) {
        setPacketBytes(dataBuffer);
        setState(sctpTcbState);
    }

    public SctpResendArgs(SctpTcbState sctpTcbState) {
        this((DataBuffer) null, sctpTcbState);
    }

    public void setPacketBytes(DataBuffer dataBuffer) {
        this._packetBytes = dataBuffer;
    }

    /* access modifiers changed from: protected */
    public void setState(SctpTcbState sctpTcbState) {
        this._state = sctpTcbState;
    }

    public void setType(byte b) {
        this._type = b;
    }
}
