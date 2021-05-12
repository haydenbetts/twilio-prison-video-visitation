package fm.liveswitch;

class RtpLastSenderReportInfo {
    private long _ntpTimestamp;
    private long _transportSystemTimestamp;

    public long getNtpTimestamp() {
        return this._ntpTimestamp;
    }

    public long getTransportSystemTimestamp() {
        return this._transportSystemTimestamp;
    }

    public void setNtpTimestamp(long j) {
        this._ntpTimestamp = j;
    }

    public void setTransportSystemTimestamp(long j) {
        this._transportSystemTimestamp = j;
    }
}
