package org.bytedeco.opencv.opencv_img_hash;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_img_hash;

@Namespace("cv::img_hash")
@Properties(inherit = {opencv_img_hash.class})
public class RadialVarianceHash extends ImgHashBase {
    @opencv_core.Ptr
    public static native RadialVarianceHash create();

    @opencv_core.Ptr
    public static native RadialVarianceHash create(double d, int i);

    @StdVector
    public native DoublePointer getFeatures();

    @ByVal
    public native Mat getHash();

    public native int getNumOfAngleLine();

    @ByVal
    public native Mat getPixPerLine(@ByRef @Const Mat mat);

    @ByVal
    public native Mat getProjection();

    public native double getSigma();

    public native void setNumOfAngleLine(int i);

    public native void setSigma(double d);

    static {
        Loader.load();
    }

    public RadialVarianceHash(Pointer pointer) {
        super(pointer);
    }
}
