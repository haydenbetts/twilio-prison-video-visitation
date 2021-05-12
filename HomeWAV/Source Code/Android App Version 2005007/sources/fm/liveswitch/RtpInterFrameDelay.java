package fm.liveswitch;

class RtpInterFrameDelay {
    private int __clockRate;
    private long __lastSystemTimestamp = -1;
    private long __lastTimestamp = -1;

    public int calculateDelayMillis(long j, long j2) {
        return (int) (calculateDelayTicks(j, j2) / ((long) Constants.getTicksPerMillisecond()));
    }

    public long calculateDelayTicks(long j, long j2) {
        long j3 = this.__lastTimestamp;
        if (j3 == -1) {
            this.__lastTimestamp = j;
            this.__lastSystemTimestamp = j2;
            return 0;
        }
        long ticksPerSecond = (j2 - this.__lastSystemTimestamp) - (((j - j3) * ((long) Constants.getTicksPerSecond())) / ((long) this.__clockRate));
        if (j < this.__lastTimestamp) {
            return -ticksPerSecond;
        }
        this.__lastTimestamp = j;
        this.__lastSystemTimestamp = j2;
        return ticksPerSecond;
    }

    public void reset() {
        this.__lastTimestamp = -1;
        this.__lastSystemTimestamp = -1;
    }

    public RtpInterFrameDelay(int i) {
        if (i > 0) {
            this.__clockRate = i;
            return;
        }
        throw new RuntimeException(new Exception("Clock rate must be positive."));
    }
}
