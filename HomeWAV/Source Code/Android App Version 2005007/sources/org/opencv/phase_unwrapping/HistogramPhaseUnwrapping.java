package org.opencv.phase_unwrapping;

import org.opencv.core.Mat;

public class HistogramPhaseUnwrapping extends PhaseUnwrapping {
    private static native long create_0();

    private static native void delete(long j);

    private static native void getInverseReliabilityMap_0(long j, long j2);

    protected HistogramPhaseUnwrapping(long j) {
        super(j);
    }

    public static HistogramPhaseUnwrapping __fromPtr__(long j) {
        return new HistogramPhaseUnwrapping(j);
    }

    public static HistogramPhaseUnwrapping create() {
        return __fromPtr__(create_0());
    }

    public void getInverseReliabilityMap(Mat mat) {
        getInverseReliabilityMap_0(this.nativeObj, mat.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
