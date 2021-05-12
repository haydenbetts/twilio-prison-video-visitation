package fm.liveswitch;

class RtpMissingFrame {
    private long __lastNackSystemTimestamp = -1;
    private int __nackCount = 0;
    private long _sequenceNumber;

    public long getLastNackSystemTimestamp() {
        return this.__lastNackSystemTimestamp;
    }

    public int getNackCount() {
        return this.__nackCount;
    }

    public long getSequenceNumber() {
        return this._sequenceNumber;
    }

    public boolean nackable(long j, long j2, long j3) {
        if (getLastNackSystemTimestamp() == -1) {
            return j2 < j3;
        }
        if (getLastNackSystemTimestamp() + j2 <= j) {
            return true;
        }
        return false;
    }

    public void nacked(long j) {
        this.__lastNackSystemTimestamp = j;
        this.__nackCount++;
    }

    public RtpMissingFrame(long j) {
        setSequenceNumber(j);
    }

    private void setSequenceNumber(long j) {
        this._sequenceNumber = j;
    }
}
