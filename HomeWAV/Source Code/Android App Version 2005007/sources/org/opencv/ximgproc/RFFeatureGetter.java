package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class RFFeatureGetter extends Algorithm {
    private static native void delete(long j);

    private static native void getFeatures_0(long j, long j2, long j3, int i, int i2, int i3, int i4, int i5);

    protected RFFeatureGetter(long j) {
        super(j);
    }

    public static RFFeatureGetter __fromPtr__(long j) {
        return new RFFeatureGetter(j);
    }

    public void getFeatures(Mat mat, Mat mat2, int i, int i2, int i3, int i4, int i5) {
        getFeatures_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, i, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
