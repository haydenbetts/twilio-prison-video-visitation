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
            this.deviationBytes = (this.deviationBytes * ((double) i)) / ((double) this.targetBitrateBps);
        }
        super.setTargets(i, i2);
    }

    public void reportEncodedFrame(int i) {
        if (this.targetFps != 0) {
            this.deviationBytes += ((double) i) - ((((double) this.targetBitrateBps) / BITS_PER_BYTE) / ((double) this.targetFps));
            this.timeSinceLastAdjustmentMs += 1000.0d / ((double) this.targetFps);
            double d = ((double) this.targetBitrateBps) / BITS_PER_BYTE;
            double d2 = BITRATE_ADJUSTMENT_SEC * d;
            double min = Math.min(this.deviationBytes, d2);
            this.deviationBytes = min;
            double max = Math.max(min, -d2);
            this.deviationBytes = max;
            if (this.timeSinceLastAdjustmentMs > 3000.0d) {
                if (max > d) {
                    int i2 = this.bitrateAdjustmentScaleExp - ((int) ((max / d) + 0.5d));
                    this.bitrateAdjustmentScaleExp = i2;
                    this.bitrateAdjustmentScaleExp = Math.max(i2, -20);
                    this.deviationBytes = d;
                } else {
                    double d3 = -d;
                    if (max < d3) {
                        int i3 = this.bitrateAdjustmentScaleExp + ((int) (((-max) / d) + 0.5d));
                        this.bitrateAdjustmentScaleExp = i3;
                        this.bitrateAdjustmentScaleExp = Math.min(i3, 20);
                        this.deviationBytes = d3;
                    }
                }
                this.timeSinceLastAdjustmentMs = 0.0d;
            }
        }
    }

    private double getBitrateAdjustmentScale() {
        return Math.pow(BITRATE_ADJUSTMENT_MAX_SCALE, ((double) this.bitrateAdjustmentScaleExp) / 20.0d);
    }

    public int getAdjustedBitrateBps() {
        return (int) (((double) this.targetBitrateBps) * getBitrateAdjustmentScale());
    }
}
