package org.opencv.ximgproc;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;

public class SelectiveSearchSegmentation extends Algorithm {
    private static native void addGraphSegmentation_0(long j, long j2);

    private static native void addImage_0(long j, long j2);

    private static native void addStrategy_0(long j, long j2);

    private static native void clearGraphSegmentations_0(long j);

    private static native void clearImages_0(long j);

    private static native void clearStrategies_0(long j);

    private static native void delete(long j);

    private static native void process_0(long j, long j2);

    private static native void setBaseImage_0(long j, long j2);

    private static native void switchToSelectiveSearchFast_0(long j, int i, int i2, float f);

    private static native void switchToSelectiveSearchFast_1(long j, int i, int i2);

    private static native void switchToSelectiveSearchFast_2(long j, int i);

    private static native void switchToSelectiveSearchFast_3(long j);

    private static native void switchToSelectiveSearchQuality_0(long j, int i, int i2, float f);

    private static native void switchToSelectiveSearchQuality_1(long j, int i, int i2);

    private static native void switchToSelectiveSearchQuality_2(long j, int i);

    private static native void switchToSelectiveSearchQuality_3(long j);

    private static native void switchToSingleStrategy_0(long j, int i, float f);

    private static native void switchToSingleStrategy_1(long j, int i);

    private static native void switchToSingleStrategy_2(long j);

    protected SelectiveSearchSegmentation(long j) {
        super(j);
    }

    public static SelectiveSearchSegmentation __fromPtr__(long j) {
        return new SelectiveSearchSegmentation(j);
    }

    public void addGraphSegmentation(GraphSegmentation graphSegmentation) {
        addGraphSegmentation_0(this.nativeObj, graphSegmentation.getNativeObjAddr());
    }

    public void addImage(Mat mat) {
        addImage_0(this.nativeObj, mat.nativeObj);
    }

    public void addStrategy(SelectiveSearchSegmentationStrategy selectiveSearchSegmentationStrategy) {
        addStrategy_0(this.nativeObj, selectiveSearchSegmentationStrategy.getNativeObjAddr());
    }

    public void clearGraphSegmentations() {
        clearGraphSegmentations_0(this.nativeObj);
    }

    public void clearImages() {
        clearImages_0(this.nativeObj);
    }

    public void clearStrategies() {
        clearStrategies_0(this.nativeObj);
    }

    public void process(MatOfRect matOfRect) {
        process_0(this.nativeObj, matOfRect.nativeObj);
    }

    public void setBaseImage(Mat mat) {
        setBaseImage_0(this.nativeObj, mat.nativeObj);
    }

    public void switchToSelectiveSearchFast(int i, int i2, float f) {
        switchToSelectiveSearchFast_0(this.nativeObj, i, i2, f);
    }

    public void switchToSelectiveSearchFast(int i, int i2) {
        switchToSelectiveSearchFast_1(this.nativeObj, i, i2);
    }

    public void switchToSelectiveSearchFast(int i) {
        switchToSelectiveSearchFast_2(this.nativeObj, i);
    }

    public void switchToSelectiveSearchFast() {
        switchToSelectiveSearchFast_3(this.nativeObj);
    }

    public void switchToSelectiveSearchQuality(int i, int i2, float f) {
        switchToSelectiveSearchQuality_0(this.nativeObj, i, i2, f);
    }

    public void switchToSelectiveSearchQuality(int i, int i2) {
        switchToSelectiveSearchQuality_1(this.nativeObj, i, i2);
    }

    public void switchToSelectiveSearchQuality(int i) {
        switchToSelectiveSearchQuality_2(this.nativeObj, i);
    }

    public void switchToSelectiveSearchQuality() {
        switchToSelectiveSearchQuality_3(this.nativeObj);
    }

    public void switchToSingleStrategy(int i, float f) {
        switchToSingleStrategy_0(this.nativeObj, i, f);
    }

    public void switchToSingleStrategy(int i) {
        switchToSingleStrategy_1(this.nativeObj, i);
    }

    public void switchToSingleStrategy() {
        switchToSingleStrategy_2(this.nativeObj);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
