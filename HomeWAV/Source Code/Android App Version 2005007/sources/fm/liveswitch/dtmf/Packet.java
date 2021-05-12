package fm.liveswitch.dtmf;

import fm.liveswitch.DataBuffer;

public class Packet {
    private int _duration;
    private boolean _end;
    private int _eventCode;
    private boolean _start;
    private int _startDuration;
    private long _startTimestamp;
    private int _volume;

    public int getLength() {
        return 4;
    }

    public int getDuration() {
        return this._duration;
    }

    public boolean getEnd() {
        return this._end;
    }

    public int getEventCode() {
        return this._eventCode;
    }

    /* access modifiers changed from: package-private */
    public boolean getStart() {
        return this._start;
    }

    /* access modifiers changed from: package-private */
    public int getStartDuration() {
        return this._startDuration;
    }

    /* access modifiers changed from: package-private */
    public long getStartTimestamp() {
        return this._startTimestamp;
    }

    public int getVolume() {
        return this._volume;
    }

    public Packet() {
        setEventCode(0);
        setEnd(true);
        setVolume(0);
        setDuration(800);
    }

    public static Packet readFrom(DataBuffer dataBuffer) {
        if (dataBuffer.getLength() < 4) {
            return null;
        }
        Packet packet = new Packet();
        packet.setEventCode(dataBuffer.read8(0));
        packet.setEnd(dataBuffer.read1(1, 0));
        packet.setVolume(dataBuffer.read6(1, 2));
        packet.setDuration(dataBuffer.read16(2));
        return packet;
    }

    public void setDuration(int i) {
        this._duration = i;
    }

    public void setEnd(boolean z) {
        this._end = z;
    }

    public void setEventCode(int i) {
        this._eventCode = i;
    }

    /* access modifiers changed from: package-private */
    public void setStart(boolean z) {
        this._start = z;
    }

    /* access modifiers changed from: package-private */
    public void setStartDuration(int i) {
        this._startDuration = i;
    }

    /* access modifiers changed from: package-private */
    public void setStartTimestamp(long j) {
        this._startTimestamp = j;
    }

    public void setVolume(int i) {
        this._volume = i;
    }

    public void writeTo(DataBuffer dataBuffer) {
        dataBuffer.write8(getEventCode(), 0);
        dataBuffer.write1(getEnd(), 1, 0);
        dataBuffer.write6(getVolume(), 1, 2);
        dataBuffer.write16(getDuration(), 2);
    }
}
