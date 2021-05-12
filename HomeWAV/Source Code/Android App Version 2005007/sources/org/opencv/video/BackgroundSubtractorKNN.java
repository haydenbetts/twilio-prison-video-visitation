package org.opencv.video;

public class BackgroundSubtractorKNN extends BackgroundSubtractor {
    private static native void delete(long j);

    private static native boolean getDetectShadows_0(long j);

    private static native double getDist2Threshold_0(long j);

    private static native int getHistory_0(long j);

    private static native int getNSamples_0(long j);

    private static native double getShadowThreshold_0(long j);

    private static native int getShadowValue_0(long j);

    private static native int getkNNSamples_0(long j);

    private static native void setDetectShadows_0(long j, boolean z);

    private static native void setDist2Threshold_0(long j, double d);

    private static native void setHistory_0(long j, int i);

    private static native void setNSamples_0(long j, int i);

    private static native void setShadowThreshold_0(long j, double d);

    private static native void setShadowValue_0(long j, int i);

    private static native void setkNNSamples_0(long j, int i);

    protected BackgroundSubtractorKNN(long j) {
        super(j);
    }

    public static BackgroundSubtractorKNN __fromPtr__(long j) {
        return new BackgroundSubtractorKNN(j);
    }

    public boolean getDetectShadows() {
        return getDetectShadows_0(this.nativeObj);
    }

    public double getDist2Threshold() {
        return getDist2Threshold_0(this.nativeObj);
    }

    public double getShadowThreshold() {
        return getShadowThreshold_0(this.nativeObj);
    }

    public int getHistory() {
        return getHistory_0(this.nativeObj);
    }

    public int getNSamples() {
        return getNSamples_0(this.nativeObj);
    }

    public int getShadowValue() {
        return getShadowValue_0(this.nativeObj);
    }

    public int getkNNSamples() {
        return getkNNSamples_0(this.nativeObj);
    }

    public void setDetectShadows(boolean z) {
        setDetectShadows_0(this.nativeObj, z);
    }

    public void setDist2Threshold(double d) {
        setDist2Threshold_0(this.nativeObj, d);
    }

    public void setHistory(int i) {
        setHistory_0(this.nativeObj, i);
    }

    public void setNSamples(int i) {
        setNSamples_0(this.nativeObj, i);
    }

    public void setShadowThreshold(double d) {
        setShadowThreshold_0(this.nativeObj, d);
    }

    public void setShadowValue(int i) {
        setShadowValue_0(this.nativeObj, i);
    }

    public void setkNNSamples(int i) {
        setkNNSamples_0(this.nativeObj, i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
