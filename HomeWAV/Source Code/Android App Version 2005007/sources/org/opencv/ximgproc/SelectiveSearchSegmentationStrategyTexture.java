package org.opencv.ximgproc;

public class SelectiveSearchSegmentationStrategyTexture extends SelectiveSearchSegmentationStrategy {
    private static native void delete(long j);

    protected SelectiveSearchSegmentationStrategyTexture(long j) {
        super(j);
    }

    public static SelectiveSearchSegmentationStrategyTexture __fromPtr__(long j) {
        return new SelectiveSearchSegmentationStrategyTexture(j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
