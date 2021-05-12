package org.opencv.ximgproc;

public class SelectiveSearchSegmentationStrategyFill extends SelectiveSearchSegmentationStrategy {
    private static native void delete(long j);

    protected SelectiveSearchSegmentationStrategyFill(long j) {
        super(j);
    }

    public static SelectiveSearchSegmentationStrategyFill __fromPtr__(long j) {
        return new SelectiveSearchSegmentationStrategyFill(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
