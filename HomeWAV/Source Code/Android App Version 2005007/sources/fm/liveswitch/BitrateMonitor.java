package fm.liveswitch;

public class BitrateMonitor {
    private long __lastBitCount;
    private long __lastBitrateTimestamp;
    private double _bitrate;
    private double _smoothedBitrate;
    private ISystemClock _systemClock;

    public BitrateMonitor(ISystemClock iSystemClock) {
        setSystemClock(iSystemClock);
        reset();
    }

    public double getBitrate() {
        return this._bitrate;
    }

    public double getSmoothedBitrate() {
        return this._smoothedBitrate;
    }

    public ISystemClock getSystemClock() {
        return this._systemClock;
    }

    public void reset() {
        setBitrate(0.0d);
        setSmoothedBitrate(-1.0d);
        this.__lastBitCount = 0;
        this.__lastBitrateTimestamp = -1;
    }

    private void setBitrate(double d) {
        this._bitrate = d;
    }

    private void setSmoothedBitrate(double d) {
        this._smoothedBitrate = d;
    }

    private void setSystemClock(ISystemClock iSystemClock) {
        this._systemClock = iSystemClock;
    }

    public double testBitrate(long j) {
        double bitrate = getBitrate();
        long timestamp = getSystemClock().getTimestamp();
        long j2 = this.__lastBitrateTimestamp;
        return ((j2 == -1 || timestamp - j2 >= ((long) Constants.getTicksPerSecond())) && this.__lastBitrateTimestamp != -1) ? (((double) ((j - this.__lastBitCount) * ((long) Constants.getTicksPerSecond()))) / ((double) (timestamp - this.__lastBitrateTimestamp))) / 1000.0d : bitrate;
    }

    public double testSmoothedBitrate(long j) {
        double smoothedBitrate = getSmoothedBitrate();
        long timestamp = getSystemClock().getTimestamp();
        long j2 = this.__lastBitrateTimestamp;
        if ((j2 != -1 && timestamp - j2 < ((long) Constants.getTicksPerSecond())) || this.__lastBitrateTimestamp == -1) {
            return smoothedBitrate;
        }
        double ticksPerSecond = (((double) ((j - this.__lastBitCount) * ((long) Constants.getTicksPerSecond()))) / ((double) (timestamp - this.__lastBitrateTimestamp))) / 1000.0d;
        return smoothedBitrate == -1.0d ? ticksPerSecond : (smoothedBitrate * 0.85d) + (ticksPerSecond * 0.15d);
    }

    public void update(long j) {
        long timestamp = getSystemClock().getTimestamp();
        long j2 = this.__lastBitrateTimestamp;
        if (j2 == -1 || timestamp - j2 >= ((long) Constants.getTicksPerSecond())) {
            if (this.__lastBitrateTimestamp != -1) {
                setBitrate((((double) ((j - this.__lastBitCount) * ((long) Constants.getTicksPerSecond()))) / ((double) (timestamp - this.__lastBitrateTimestamp))) / 1000.0d);
                if (getSmoothedBitrate() == -1.0d) {
                    setSmoothedBitrate(getBitrate());
                } else {
                    setSmoothedBitrate((getSmoothedBitrate() * 0.85d) + (getBitrate() * 0.15d));
                }
            }
            this.__lastBitCount = j;
            this.__lastBitrateTimestamp = timestamp;
        }
    }
}
