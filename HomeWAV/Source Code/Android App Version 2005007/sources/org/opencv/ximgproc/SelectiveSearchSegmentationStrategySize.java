package org.opencv.ximgproc;

public class SelectiveSearchSegmentationStrategySize extends SelectiveSearchSegmentationStrategy {
    private static native void delete(long j);

    protected SelectiveSearchSegmentationStrategySize(long j) {
        super(j);
    }

    public static SelectiveSearchSegmentationStrategySize __fromPtr__(long j) {
        return new SelectiveSearchSegmentationStrategySize(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
