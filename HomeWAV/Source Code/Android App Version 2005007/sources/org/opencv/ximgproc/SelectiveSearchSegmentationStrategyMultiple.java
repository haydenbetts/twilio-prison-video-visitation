package org.opencv.ximgproc;

public class SelectiveSearchSegmentationStrategyMultiple extends SelectiveSearchSegmentationStrategy {
    private static native void addStrategy_0(long j, long j2, float f);

    private static native void clearStrategies_0(long j);

    private static native void delete(long j);

    protected SelectiveSearchSegmentationStrategyMultiple(long j) {
        super(j);
    }

    public static SelectiveSearchSegmentationStrategyMultiple __fromPtr__(long j) {
        return new SelectiveSearchSegmentationStrategyMultiple(j);
    }

    public void addStrategy(SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy, float f) {
        addStrategy_0(this.nativeObj, selectiveSearchSegmentationStrategy.getNativeObjAddr(), f);
    }

    public void clearStrategies() {
        clearStrategies_0(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
