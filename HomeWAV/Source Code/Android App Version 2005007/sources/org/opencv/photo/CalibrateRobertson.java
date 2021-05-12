package org.opencv.photo;

import org.opencv.core.Mat;

public class CalibrateRobertson extends CalibrateCRF {
    private static native void delete(long j);

    private static native int getMaxIter_0(long j);

    private static native long getRadiance_0(long j);

    private static native float getThreshold_0(long j);

    private static native void setMaxIter_0(long j, int i);

    private static native void setThreshold_0(long j, float f);

    protected CalibrateRobertson(long j) {
        super(j);
    }

    public static CalibrateRobertson __fromPtr__(long j) {
        return new CalibrateRobertson(j);
    }

    public Mat getRadiance() {
        return new Mat(getRadiance_0(this.nativeObj));
    }

    public float getThreshold() {
        return getThreshold_0(this.nativeObj);
    }

    public int getMaxIter() {
        return getMaxIter_0(this.nativeObj);
    }

    public void setMaxIter(int i) {
        setMaxIter_0(this.nativeObj, i);
    }

    public void setThreshold(float f) {
        setThreshold_0(this.nativeObj, f);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
