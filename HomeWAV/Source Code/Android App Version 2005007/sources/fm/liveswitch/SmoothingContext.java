package fm.liveswitch;

class SmoothingContext {
    private double _averageValue;
    private long _sampleCount;
    private double _smoothingFactor;

    public static double getDefaultSmoothingFactor() {
        return 0.875d;
    }

    public double getAverageValue() {
        return this._averageValue;
    }

    public long getSampleCount() {
        return this._sampleCount;
    }

    public double getSmoothingFactor() {
        return this._smoothingFactor;
    }

    public void processValue(double d) {
        setSampleCount(getSampleCount() + 1);
        setAverageValue((getSmoothingFactor() * getAverageValue()) + ((1.0d - getSmoothingFactor()) * d));
    }

    public void reset() {
        setAverageValue(0.0d);
        setSampleCount(0);
    }

    private void setAverageValue(double d) {
        this._averageValue = d;
    }

    private void setSampleCount(long j) {
        this._sampleCount = j;
    }

    public void setSmoothingFactor(double d) {
        this._smoothingFactor = d;
    }

    public SmoothingContext() {
        this(0.875d);
    }

    public SmoothingContext(double d) {
        this(d, 0.0d);
    }

    public SmoothingContext(double d, double d2) {
        setSmoothingFactor(d);
        setAverageValue(d2);
        setSampleCount(0);
    }

    public double testValue(double d) {
        return (getSmoothingFactor() * getAverageValue()) + ((1.0d - getSmoothingFactor()) * d);
    }
}
