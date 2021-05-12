package org.bytedeco.opencv.opencv_xfeatures2d;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
public class PCTSignaturesSQFD extends Algorithm {
    @opencv_core.Ptr
    public static native PCTSignaturesSQFD create();

    @opencv_core.Ptr
    public static native PCTSignaturesSQFD create(int i, int i2, float f);

    public native float computeQuadraticFormDistance(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native float computeQuadraticFormDistance(@ByVal Mat mat, @ByVal Mat mat2);

    public native float computeQuadraticFormDistance(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void computeQuadraticFormDistances(@ByRef @Const Mat mat, @ByRef @Const MatVector matVector, @StdVector FloatBuffer floatBuffer);

    public native void computeQuadraticFormDistances(@ByRef @Const Mat mat, @ByRef @Const MatVector matVector, @StdVector FloatPointer floatPointer);

    public native void computeQuadraticFormDistances(@ByRef @Const Mat mat, @ByRef @Const MatVector matVector, @StdVector float[] fArr);

    static {
        Loader.load();
    }

    public PCTSignaturesSQFD(Pointer pointer) {
        super(pointer);
    }
}
