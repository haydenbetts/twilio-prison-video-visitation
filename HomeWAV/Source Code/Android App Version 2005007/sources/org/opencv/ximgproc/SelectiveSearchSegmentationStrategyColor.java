package org.opencv.ximgproc;

public class SelectiveSearchSegmentationStrategyColor extends SelectiveSearchSegmentationStrategy {
    private static native void delete(long j);

    protected SelectiveSearchSegmentationStrategyColor(long j) {
        super(j);
    }

    public static SelectiveSearchSegmentationStrategyColor __fromPtr__(long j) {
        return new SelectiveSearchSegmentationStrategyColor(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
