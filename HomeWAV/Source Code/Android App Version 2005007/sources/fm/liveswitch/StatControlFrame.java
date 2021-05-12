package fm.liveswitch;

class StatControlFrame extends MediaControlFrame {
    private long _timestamp;
    private StatControlFrameType _type;

    public long getTimestamp() {
        return this._timestamp;
    }

    public StatControlFrameType getType() {
        return this._type;
    }

    public void setTimestamp(long j) {
        this._timestamp = j;
    }

    public void setType(StatControlFrameType statControlFrameType) {
        this._type = statControlFrameType;
    }
}
