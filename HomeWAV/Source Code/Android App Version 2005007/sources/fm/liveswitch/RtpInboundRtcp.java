package fm.liveswitch;

class RtpInboundRtcp {
    private MediaControlFrame[] __frames;
    private int _footprint;
    private long _networkSystemTimestamp;
    private long _transportSystemTimestamp;

    public int getFootprint() {
        return this._footprint;
    }

    public MediaControlFrame[] getFrames() {
        return this.__frames;
    }

    public long getNetworkSystemTimestamp() {
        return this._networkSystemTimestamp;
    }

    public long getTransportSystemTimestamp() {
        return this._transportSystemTimestamp;
    }

    private void setFootprint(int i) {
        this._footprint = i;
    }

    public void setFrames(MediaControlFrame[] mediaControlFrameArr) {
        int i = 0;
        if (mediaControlFrameArr != null) {
            int length = mediaControlFrameArr.length;
            int i2 = 0;
            while (i < length) {
                i2 += mediaControlFrameArr[i].getDataBuffer().getLength();
                i++;
            }
            i = i2;
        }
        setFootprint(i);
        this.__frames = mediaControlFrameArr;
    }

    public void setNetworkSystemTimestamp(long j) {
        this._networkSystemTimestamp = j;
    }

    public void setTransportSystemTimestamp(long j) {
        this._transportSystemTimestamp = j;
    }
}
