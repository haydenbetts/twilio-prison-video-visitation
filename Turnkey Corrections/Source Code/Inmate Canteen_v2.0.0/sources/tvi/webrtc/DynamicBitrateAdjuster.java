package tvi.webrtc;

class DynamicBitrateAdjuster extends BaseBitrateAdjuster {
    private static final double BITRATE_ADJUSTMENT_MAX_SCALE = 4.0d;
    private static final double BITRATE_ADJUSTMENT_SEC = 3.0d;
    private static final int BITRATE_ADJUSTMENT_STEPS = 20;
    private static final double BITS_PER_BYTE = 8.0d;
    private int bitrateAdjustmentScaleExp = 0;
    private double deviationBytes = 0.0d;
    private double timeSinceLastAdjustmentMs = 0.0d;

    DynamicBitrateAdjuster() {
    }

    public void setTargets(int i, int i2) {
        if (this.targetBitrateBps > 0 && i < this.targetBitrateBps) {
            double d = this.deviationBytes;
            double d2 = (double) i;
            Double.isNaN(d2);
            double d3 = d * d2;
            double d4 = (double) this.targetBitrateBps;
            Double.isNaN(d4);
            this.deviationBytes = d3 / d4;
        }
        super.setTargets(i, i2);
    }

    public void reportEncodedFrame(int i) {
        if (this.targetFps != 0) {
            double d = (double) this.targetBitrateBps;
            Double.isNaN(d);
            double d2 = d / BITS_PER_BYTE;
            double d3 = (double) this.targetFps;
            Double.isNaN(d3);
            double d4 = d2 / d3;
            double d5 = this.deviationBytes;
            double d6 = (double) i;
            Double.isNaN(d6);
            this.deviationBytes = d5 + (d6 - d4);
            double d7 = this.timeSinceLastAdjustmentMs;
            double d8 = (double) this.targetFps;
            Double.isNaN(d8);
            this.timeSinceLastAdjustmentMs = d7 + (1000.0d / d8);
            double d9 = (double) this.targetBitrateBps;
            Double.isNaN(d9);
            double d10 = d9 / BITS_PER_BYTE;
            double d11 = BITRATE_ADJUSTMENT_SEC * d10;
            this.deviationBytes = Math.min(this.deviationBytes, d11);
            this.deviationBytes = Math.max(this.deviationBytes, -d11);
            if (this.timeSinceLastAdjustmentMs > 3000.0d) {
                double d12 = this.deviationBytes;
                if (d12 > d10) {
                    this.bitrateAdjustmentScaleExp -= (int) ((d12 / d10) + 0.5d);
                    this.bitrateAdjustmentScaleExp = Math.max(this.bitrateAdjustmentScaleExp, -20);
                    this.deviationBytes = d10;
                } else {
                    double d13 = -d10;
                    if (d12 < d13) {
                        this.bitrateAdjustmentScaleExp += (int) (((-d12) / d10) + 0.5d);
                        this.bitrateAdjustmentScaleExp = Math.min(this.bitrateAdjustmentScaleExp, 20);
                        this.deviationBytes = d13;
                    }
                }
                this.timeSinceLastAdjustmentMs = 0.0d;
            }
        }
    }

    private double getBitrateAdjustmentScale() {
        double d = (double) this.bitrateAdjustmentScaleExp;
        Double.isNaN(d);
        return Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, d / 20.0d);
    }

    public int getAdjustedBitrateBps() {
        double d = (double) this.targetBitrateBps;
        double bitrateAdjustmentScale = getBitrateAdjustmentScale();
        Double.isNaN(d);
        return (int) (d * bitrateAdjustmentScale);
    }
}
