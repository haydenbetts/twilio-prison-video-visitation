package org.opencv.structured_light;

import java.util.List;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.utils.Converters;

public class GrayCodePattern extends StructuredLightPattern {
    private static native long create_0(int i, int i2);

    private static native void delete(long j);

    private static native void getImagesForShadowMasks_0(long j, long j2, long j3);

    private static native long getNumberOfPatternImages_0(long j);

    private static native boolean getProjPixel_0(long j, long j2, int i, int i2, double[] dArr);

    private static native void setBlackThreshold_0(long j, long j2);

    private static native void setWhiteThreshold_0(long j, long j2);

    protected GrayCodePattern(long j) {
        super(j);
    }

    public static GrayCodePattern __fromPtr__(long j) {
        return new GrayCodePattern(j);
    }

    public static GrayCodePattern create(int i, int i2) {
        return __fromPtr__(create_0(i, i2));
    }

    public boolean getProjPixel(List<Mat> list, int i, int i2, Point point) {
        double[] dArr = new double[2];
        boolean projPixel_0 = getProjPixel_0(this.nativeObj, Converters.vector_Mat_to_Mat(list).nativeObj, i, i2, dArr);
        if (point != null) {
            point.x = dArr[0];
            point.y = dArr[1];
        }
        return projPixel_0;
    }

    public long getNumberOfPatternImages() {
        return getNumberOfPatternImages_0(this.nativeObj);
    }

    public void getImagesForShadowMasks(Mat mat, Mat mat2) {
        getImagesForShadowMasks_0(this.nativeObj, mat.nativeObj, mat2.nativeObj);
    }

    public void setBlackThreshold(long j) {
        setBlackThreshold_0(this.nativeObj, j);
    }

    public void setWhiteThreshold(long j) {
        setWhiteThreshold_0(this.nativeObj, j);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
