package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class SuperpixelLSC extends Algorithm {
    private static native void delete(long j);

    private static native void enforceLabelConnectivity_0(long j, int i);

    private static native void enforceLabelConnectivity_1(long j);

    private static native void getLabelContourMask_0(long j, long j2, boolean z);

    private static native void getLabelContourMask_1(long j, long j2);

    private static native void getLabels_0(long j, long j2);

    private static native int getNumberOfSuperpixels_0(long j);

    private static native void iterate_0(long j, int i);

    private static native void iterate_1(long j);

    protected SuperpixelLSC(long j) {
        super(j);
    }

    public static SuperpixelLSC __fromPtr__(long j) {
        return new SuperpixelLSC(j);
    }

    public int getNumberOfSuperpixels() {
        return getNumberOfSuperpixels_0(this.nativeObj);
    }

    public void enforceLabelConnectivity(int i) {
        enforceLabelConnectivity_0(this.nativeObj, i);
    }

    public void enforceLabelConnectivity() {
        enforceLabelConnectivity_1(this.nativeObj);
    }

    public void getLabelContourMask(Mat mat, boolean z) {
        getLabelContourMask_0(this.nativeObj, mat.nativeObj, z);
    }

    public void getLabelContourMask(Mat mat) {
        getLabelContourMask_1(this.nativeObj, mat.nativeObj);
    }

    public void getLabels(Mat mat) {
        getLabels_0(this.nativeObj, mat.nativeObj);
    }

    public void iterate(int i) {
        iterate_0(this.nativeObj, i);
    }

    public void iterate() {
        iterate_1(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
