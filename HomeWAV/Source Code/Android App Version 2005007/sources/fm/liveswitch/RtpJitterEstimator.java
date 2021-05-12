package fm.liveswitch;

class RtpJitterEstimator {
    private static ILog __log = Log.getLogger(RtpJitterEstimator.class);
    private int __alphaCount;
    private double __avgFrameSize;
    private double __avgNoise;
    private double __filterJitterEstimate;
    private int __frameSizeCount;
    private long __frameSizeSum;
    private String __logScope;
    private double __maxFrameSize;
    private double __prevEstimate;
    private int __prevFrameSize;
    private double[][] __qCov;
    private int __startupCount;
    private double[] __theta = new double[2];
    private double[][] __thetaCov;
    private double __varFrameSize;
    private double __varNoise;
    private int _frameSizeAccuStartupSamples = 5;
    private int _startupDelaySamples = 30;

    private double calculateEstimate() {
        double noiseThreshold = (this.__theta[0] * (this.__maxFrameSize - this.__avgFrameSize)) + getNoiseThreshold();
        if (noiseThreshold < 1.0d) {
            noiseThreshold = this.__prevEstimate;
            if (noiseThreshold <= 0.01d) {
                noiseThreshold = 1.0d;
            }
        }
        if (noiseThreshold > 10000.0d) {
            noiseThreshold = 10000.0d;
        }
        this.__prevEstimate = noiseThreshold;
        return noiseThreshold;
    }

    private double deviationFromExpectedDelay(long j, int i) {
        double[] dArr = this.__theta;
        return ((double) j) - ((dArr[0] * ((double) i)) + dArr[1]);
    }

    private boolean estimateRandomJitter(double d, boolean z) {
        int i = this.__alphaCount;
        if (i == 0) {
            __log.warn(this.__logScope, "Alpha count cannot be zero.");
            return false;
        }
        double d2 = ((double) (i - 1)) / ((double) i);
        int i2 = i + 1;
        this.__alphaCount = i2;
        if (i2 > 400) {
            this.__alphaCount = 400;
        }
        double d3 = this.__avgNoise;
        double d4 = 1.0d - d2;
        double d5 = (d2 * d3) + (d4 * d);
        double d6 = this.__varNoise;
        double d7 = (d2 * d6) + (d4 * (d - d3) * (d - d3));
        if (!z || d7 > d6) {
            this.__avgNoise = d5;
            this.__varNoise = d7;
        }
        if (this.__varNoise < 1.0d) {
            this.__varNoise = 1.0d;
        }
        return true;
    }

    public int getJitterEstimate() {
        double calculateEstimate = calculateEstimate() + 10.0d;
        double d = this.__filterJitterEstimate;
        if (d > calculateEstimate) {
            calculateEstimate = d;
        }
        return (int) MathAssistant.ceil(calculateEstimate);
    }

    private double getNoiseThreshold() {
        double sqrt = (MathAssistant.sqrt(this.__varNoise) * 2.33d) - 30.0d;
        if (sqrt < 1.0d) {
            return 1.0d;
        }
        return sqrt;
    }

    private boolean kalmanEstimateChannel(long j, int i) {
        double[][] dArr = this.__thetaCov;
        double[] dArr2 = dArr[0];
        double d = dArr[0][0];
        double[][] dArr3 = this.__qCov;
        dArr2[0] = d + dArr3[0][0];
        dArr[0][1] = dArr[0][1] + dArr3[0][1];
        dArr[1][0] = dArr[1][0] + dArr3[1][0];
        dArr[1][1] = dArr[1][1] + dArr3[1][1];
        double d2 = (double) i;
        double[] dArr4 = {(dArr[0][0] * d2) + dArr[0][1], (dArr[1][0] * d2) + dArr[1][1]};
        if (this.__maxFrameSize < 1.0d) {
            return false;
        }
        double exp = ((MathAssistant.exp((-MathAssistant.abs(d2)) / (this.__maxFrameSize * 1.0d)) * 300.0d) + 1.0d) * MathAssistant.sqrt(this.__varNoise);
        if (exp < 1.0d) {
            exp = 1.0d;
        }
        double d3 = (dArr4[0] * d2) + dArr4[1] + exp;
        if (d3 < 1.0E-9d && d3 >= 0.0d) {
            return false;
        }
        if (d3 > -1.0E-9d && d3 <= 0.0d) {
            return false;
        }
        double[] dArr5 = {dArr4[0] / d3, dArr4[1] / d3};
        double[] dArr6 = this.__theta;
        double d4 = ((double) j) - ((dArr6[0] * d2) + dArr6[1]);
        dArr6[0] = dArr6[0] + (dArr5[0] * d4);
        dArr6[1] = dArr6[1] + (dArr5[1] * d4);
        if (dArr6[0] < 1.0E-6d) {
            dArr6[0] = 1.0E-6d;
        }
        double[][] dArr7 = this.__thetaCov;
        double d5 = dArr7[0][0];
        double d6 = dArr7[0][1];
        dArr7[0][0] = ((1.0d - (dArr5[0] * d2)) * d5) - (dArr5[0] * dArr7[1][0]);
        dArr7[0][1] = ((1.0d - (dArr5[0] * d2)) * d6) - (dArr5[0] * dArr7[1][1]);
        dArr7[1][0] = (dArr7[1][0] * (1.0d - dArr5[1])) - ((dArr5[1] * d2) * d5);
        dArr7[1][1] = (dArr7[1][1] * (1.0d - dArr5[1])) - ((dArr5[1] * d2) * d6);
        return dArr7[0][0] + dArr7[1][1] >= 0.0d && (dArr7[0][0] * dArr7[1][1]) - (dArr7[0][1] * dArr7[1][0]) >= 0.0d && dArr7[0][0] >= 0.0d;
    }

    private void postProcessEstimate() {
        this.__filterJitterEstimate = calculateEstimate();
    }

    public void reset() {
        double[] dArr = this.__theta;
        dArr[0] = 1.5625E-5d;
        dArr[1] = 0.0d;
        this.__varNoise = 4.0d;
        double[][] dArr2 = this.__thetaCov;
        dArr2[0][0] = 1.0E-4d;
        dArr2[1][1] = 100.0d;
        dArr2[0][1] = 0.0d;
        dArr2[1][0] = 0.0d;
        double[][] dArr3 = this.__qCov;
        dArr3[0][0] = 2.5E-10d;
        dArr3[1][1] = 1.0E-10d;
        dArr3[0][1] = 0.0d;
        dArr3[1][0] = 0.0d;
        this.__avgFrameSize = 500.0d;
        this.__maxFrameSize = 500.0d;
        this.__varFrameSize = 100.0d;
        this.__prevEstimate = -1.0d;
        this.__prevFrameSize = 0;
        this.__avgNoise = 0.0d;
        this.__alphaCount = 1;
        this.__filterJitterEstimate = 0.0d;
        this.__frameSizeSum = 0;
        this.__frameSizeCount = 0;
        this.__startupCount = 0;
    }

    public RtpJitterEstimator(String str) {
        this.__logScope = str;
        this.__thetaCov = new double[2][];
        for (int i = 0; i < ArrayExtensions.getLength((Object[]) this.__thetaCov); i++) {
            this.__thetaCov[i] = new double[2];
        }
        this.__qCov = new double[2][];
        for (int i2 = 0; i2 < ArrayExtensions.getLength((Object[]) this.__qCov); i2++) {
            this.__qCov[i2] = new double[2];
        }
        reset();
    }

    public void updateEstimate(long j, int i) {
        updateEstimate(j, i, false);
    }

    public void updateEstimate(long j, int i, boolean z) {
        long j2 = j;
        int i2 = i;
        boolean z2 = z;
        if (i2 != 0) {
            int i3 = i2 - this.__prevFrameSize;
            int i4 = this.__frameSizeCount;
            int i5 = this._frameSizeAccuStartupSamples;
            if (i4 < i5) {
                this.__frameSizeSum += (long) i2;
                this.__frameSizeCount = i4 + 1;
            } else if (i4 == i5) {
                this.__avgFrameSize = ((double) this.__frameSizeSum) / ((double) i4);
                this.__frameSizeCount = i4 + 1;
            }
            if (!z2 || ((double) i2) > this.__avgFrameSize) {
                double d = this.__avgFrameSize;
                double d2 = (double) i2;
                double d3 = (d * 0.97d) + (d2 * 0.03d);
                if (d2 < d + (MathAssistant.sqrt(this.__varFrameSize) * 2.0d)) {
                    this.__avgFrameSize = d3;
                }
                double d4 = d2 - d3;
                this.__varFrameSize = MathAssistant.max((this.__varFrameSize * 0.97d) + (d4 * 0.03d * d4), 1.0d);
            }
            double d5 = (double) i2;
            this.__maxFrameSize = MathAssistant.max(this.__maxFrameSize * 0.9999d, d5);
            if (this.__prevFrameSize == 0) {
                this.__prevFrameSize = i2;
                return;
            }
            this.__prevFrameSize = i2;
            double deviationFromExpectedDelay = deviationFromExpectedDelay(j2, i3);
            if (MathAssistant.abs(deviationFromExpectedDelay) < MathAssistant.sqrt(this.__varNoise) * 15.0d || d5 > this.__avgFrameSize + (MathAssistant.sqrt(this.__varFrameSize) * 3.0d)) {
                estimateRandomJitter(deviationFromExpectedDelay, z2);
                if ((!z2 || deviationFromExpectedDelay >= 0.0d) && ((double) i3) > this.__maxFrameSize * -0.25d) {
                    kalmanEstimateChannel(j2, i3);
                }
            } else {
                estimateRandomJitter(((double) (deviationFromExpectedDelay >= 0.0d ? 15 : -15)) * MathAssistant.sqrt(this.__varNoise), z2);
            }
            int i6 = this.__startupCount;
            if (i6 >= this._startupDelaySamples) {
                postProcessEstimate();
            } else {
                this.__startupCount = i6 + 1;
            }
        }
    }

    public void updateMaxFrameSize(int i) {
        double d = (double) i;
        if (this.__maxFrameSize < d) {
            this.__maxFrameSize = d;
        }
    }
}
