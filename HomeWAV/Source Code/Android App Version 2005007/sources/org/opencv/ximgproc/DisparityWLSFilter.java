package org.opencv.ximgproc;

import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class DisparityWLSFilter extends DisparityFilter {
    private static native void delete(long j);

    private static native long getConfidenceMap_0(long j);

    private static native int getDepthDiscontinuityRadius_0(long j);

    private static native int getLRCthresh_0(long j);

    private static native double getLambda_0(long j);

    private static native double[] getROI_0(long j);

    private static native double getSigmaColor_0(long j);

    private static native void setDepthDiscontinuityRadius_0(long j, int i);

    private static native void setLRCthresh_0(long j, int i);

    private static native void setLambda_0(long j, double d);

    private static native void setSigmaColor_0(long j, double d);

    protected DisparityWLSFilter(long j) {
        super(j);
    }

    public static DisparityWLSFilter __fromPtr__(long j) {
        return new DisparityWLSFilter(j);
    }

    public Mat getConfidenceMap() {
        return new Mat(getConfidenceMap_0(this.nativeObj));
    }

    public Rect getROI() {
        return new Rect(getROI_0(this.nativeObj));
    }

    public double getLambda() {
        return getLambda_0(this.nativeObj);
    }

    public double getSigmaColor() {
        return getSigmaColor_0(this.nativeObj);
    }

    public int getDepthDiscontinuityRadius() {
        return getDepthDiscontinuityRadius_0(this.nativeObj);
    }

    public int getLRCthresh() {
        return getLRCthresh_0(this.nativeObj);
    }

    public void setDepthDiscontinuityRadius(int i) {
        setDepthDiscontinuityRadius_0(this.nativeObj, i);
    }

    public void setLRCthresh(int i) {
        setLRCthresh_0(this.nativeObj, i);
    }

    public void setLambda(double d) {
        setLambda_0(this.nativeObj, d);
    }

    public void setSigmaColor(double d) {
        setSigmaColor_0(this.nativeObj, d);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
