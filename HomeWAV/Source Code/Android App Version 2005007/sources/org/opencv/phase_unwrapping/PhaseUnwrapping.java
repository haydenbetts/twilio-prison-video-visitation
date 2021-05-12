package org.opencv.phase_unwrapping;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;

public class PhaseUnwrapping extends Algorithm {
    private static native void delete(long j);

    private static native void unwrapPhaseMap_0(long j, long j2, long j3, long j4);

    private static native void unwrapPhaseMap_1(long j, long j2, long j3);

    protected PhaseUnwrapping(long j) {
        super(j);
    }

    public static PhaseUnwrapping __fromPtr__(long j) {
        return new PhaseUnwrapping(j);
    }

    public void unwrapPhaseMap(Mat mat, Mat mat2, Mat mat3) {
        unwrapPhaseMap_0(this.nativeObj, mat.nativeObj, mat2.nativeObj, mat3.nativeObj);
    }

    public void unwrapPhaseMap(Mat mat, Mat mat2) {
        unwrapPhaseMap_1(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
