package fm.liveswitch;

class RtpLossController {
    private long __lastUpdateTimestamp;
    private long __lossGracePeriodStartTimestamp;
    private double __maxCurrentBitrate;
    private double __percentLostAverage;
    private double __percentLostWindow;
    private long __softBandwidthEstimateStartTimestamp;
    private double _bandwidthEstimate;
    private double _currentBitrate;
    private double _initialBandwidthEstimate;
    private double _maxBandwidthEstimate;
    private double _softInitialBandwidthEstimateMultiplier;
    private double _softMaxBandwidthEstimateMultiplier;
    private ISystemClock _systemClock;

    public double getBandwidthEstimate() {
        return this._bandwidthEstimate;
    }

    public double getCurrentBitrate() {
        return this._currentBitrate;
    }

    public double getInitialBandwidthEstimate() {
        return this._initialBandwidthEstimate;
    }

    public double getMaxBandwidthEstimate() {
        return this._maxBandwidthEstimate;
    }

    public double getSoftInitialBandwidthEstimateMultiplier() {
        return this._softInitialBandwidthEstimateMultiplier;
    }

    public double getSoftMaxBandwidthEstimateMultiplier() {
        return this._softMaxBandwidthEstimateMultiplier;
    }

    public ISystemClock getSystemClock() {
        return this._systemClock;
    }

    public void hardReset(double d) {
        if (d != -1.0d) {
            reset(d, 1.0d, 1.5d);
            return;
        }
        throw new RuntimeException(new Exception("Hard resets require a positive initial bandwidth estimate."));
    }

    private void reset(double d, double d2, double d3) {
        setCurrentBitrate(0.0d);
        setInitialBandwidthEstimate(d);
        if (d == -1.0d) {
            setBandwidthEstimate(-1.0d);
            setMaxBandwidthEstimate(ConstraintUtility.max(getMaxBandwidthEstimate(), -1.0d));
        } else {
            setBandwidthEstimate(d2 * d);
            setMaxBandwidthEstimate(ConstraintUtility.max(getMaxBandwidthEstimate(), d * d3));
        }
        this.__maxCurrentBitrate = 0.0d;
        this.__softBandwidthEstimateStartTimestamp = -1;
        this.__lossGracePeriodStartTimestamp = -1;
        this.__lastUpdateTimestamp = -1;
        resetPacketLoss();
    }

    private void resetPacketLoss() {
        this.__percentLostAverage = -1.0d;
        this.__percentLostWindow = 0.0d;
    }

    public RtpLossController(ISystemClock iSystemClock, double d) {
        this(iSystemClock, d, 1.05d);
    }

    public RtpLossController(ISystemClock iSystemClock, double d, double d2) {
        this(iSystemClock, d, d2, 1.5d);
    }

    public RtpLossController(ISystemClock iSystemClock, double d, double d2, double d3) {
        this.__lastUpdateTimestamp = -1;
        setSystemClock(iSystemClock);
        setSoftInitialBandwidthEstimateMultiplier(d2);
        setSoftMaxBandwidthEstimateMultiplier(d3);
        setMaxBandwidthEstimate(-1.0d);
        if (d == -1.0d) {
            softReset(d);
        } else {
            hardReset(d);
        }
    }

    private void setBandwidthEstimate(double d) {
        this._bandwidthEstimate = d;
    }

    public void setCurrentBitrate(double d) {
        this._currentBitrate = d;
    }

    private void setInitialBandwidthEstimate(double d) {
        this._initialBandwidthEstimate = d;
    }

    private void setMaxBandwidthEstimate(double d) {
        this._maxBandwidthEstimate = d;
    }

    private void setSoftInitialBandwidthEstimateMultiplier(double d) {
        this._softInitialBandwidthEstimateMultiplier = d;
    }

    private void setSoftMaxBandwidthEstimateMultiplier(double d) {
        this._softMaxBandwidthEstimateMultiplier = d;
    }

    private void setSystemClock(ISystemClock iSystemClock) {
        this._systemClock = iSystemClock;
    }

    public void softReset(double d) {
        reset(d, getSoftInitialBandwidthEstimateMultiplier(), getSoftMaxBandwidthEstimateMultiplier());
    }

    public void update(double d) {
        long timestamp = getSystemClock().getTimestamp();
        try {
            double bandwidthEstimate = getBandwidthEstimate();
            if (bandwidthEstimate == -1.0d) {
                double currentBitrate = getCurrentBitrate();
                if (currentBitrate != 0.0d) {
                    double max = MathAssistant.max(currentBitrate, this.__maxCurrentBitrate);
                    this.__maxCurrentBitrate = max;
                    long j = this.__softBandwidthEstimateStartTimestamp;
                    if (j == -1) {
                        this.__softBandwidthEstimateStartTimestamp = timestamp;
                    } else if (timestamp - j >= 70000000) {
                        softReset(MathAssistant.floor(max));
                        this.__lossGracePeriodStartTimestamp = timestamp;
                    }
                } else if (this.__softBandwidthEstimateStartTimestamp != -1) {
                    this.__softBandwidthEstimateStartTimestamp = -1;
                    this.__maxCurrentBitrate = 0.0d;
                }
            } else {
                long j2 = this.__lossGracePeriodStartTimestamp;
                if (j2 == -1) {
                    this.__lossGracePeriodStartTimestamp = timestamp;
                } else if (timestamp - j2 > 30000000) {
                    long j3 = this.__lastUpdateTimestamp;
                    if (j3 != -1) {
                        double ticksPerSecond = ((double) (timestamp - j3)) / ((double) Constants.getTicksPerSecond());
                        double d2 = this.__percentLostAverage;
                        double d3 = this.__percentLostWindow;
                        double d4 = d3 + ticksPerSecond;
                        if (d4 != 0.0d) {
                            this.__percentLostAverage = ((d2 * d3) + (ticksPerSecond * d)) / d4;
                            this.__percentLostWindow = d4;
                        }
                        double round = MathAssistant.round(this.__percentLostAverage * 1.0E8d) / 1.0E8d;
                        double round2 = MathAssistant.round(this.__percentLostWindow * 1.0E8d) / 1.0E8d;
                        if (round < 0.02d) {
                            if (round2 >= 5.0d) {
                                updateBandwidthEstimate(bandwidthEstimate * 1.05d);
                            }
                        } else if (round >= 0.1d) {
                            updateBandwidthEstimate(bandwidthEstimate * (1.0d - ((MathAssistant.round(((round * round2) / 0.5d) * 1.0E8d) / 1.0E8d) * 0.5d)));
                        } else if (round2 >= 1.0d) {
                            updateBandwidthEstimate(bandwidthEstimate);
                        }
                    }
                }
            }
        } finally {
            this.__lastUpdateTimestamp = timestamp;
        }
    }

    private void updateBandwidthEstimate(double d) {
        setBandwidthEstimate(ConstraintUtility.min(d, getMaxBandwidthEstimate()));
        resetPacketLoss();
    }
}
