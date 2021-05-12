package fm.liveswitch;

class RtpOutboundRtcp {
    private MediaControlFrame[] _frames;
    private long _transportSystemTimestamp;

    public MediaControlFrame[] getFrames() {
        return this._frames;
    }

    public long getTransportSystemTimestamp() {
        return this._transportSystemTimestamp;
    }

    public void setFrames(MediaControlFrame[] mediaControlFrameArr) {
        this._frames = mediaControlFrameArr;
    }

    public void setTransportSystemTimestamp(long j) {
        this._transportSystemTimestamp = j;
    }
}
