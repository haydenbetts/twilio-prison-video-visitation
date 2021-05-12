package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
public class KeyPointsFilter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public static native void removeDuplicated(@ByRef KeyPointVector keyPointVector);

    public static native void removeDuplicatedSorted(@ByRef KeyPointVector keyPointVector);

    public static native void retainBest(@ByRef KeyPointVector keyPointVector, int i);

    public static native void runByImageBorder(@ByRef KeyPointVector keyPointVector, @ByVal Size size, int i);

    public static native void runByKeypointSize(@ByRef KeyPointVector keyPointVector, float f);

    public static native void runByKeypointSize(@ByRef KeyPointVector keyPointVector, float f, float f2);

    public static native void runByPixelsMask(@ByRef KeyPointVector keyPointVector, @ByRef @Const Mat mat);

    static {
        Loader.load();
    }

    public KeyPointsFilter(Pointer pointer) {
        super(pointer);
    }

    public KeyPointsFilter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public KeyPointsFilter position(long j) {
        return (KeyPointsFilter) super.position(j);
    }

    public KeyPointsFilter getPointer(long j) {
        return new KeyPointsFilter((Pointer) this).position(this.position + j);
    }

    public KeyPointsFilter() {
        super((Pointer) null);
        allocate();
    }
}
