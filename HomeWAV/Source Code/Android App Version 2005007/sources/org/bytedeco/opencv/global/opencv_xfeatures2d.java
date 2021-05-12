package org.bytedeco.opencv.global;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.DMatchVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;

public class opencv_xfeatures2d extends org.bytedeco.opencv.presets.opencv_xfeatures2d {
    @Namespace("cv::xfeatures2d")
    public static native void FASTForPointSet(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv::xfeatures2d")
    public static native void FASTForPointSet(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::FastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv::xfeatures2d")
    public static native void FASTForPointSet(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv::xfeatures2d")
    public static native void FASTForPointSet(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::FastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv::xfeatures2d")
    public static native void FASTForPointSet(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv::xfeatures2d")
    public static native void FASTForPointSet(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::FastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv::xfeatures2d")
    public static native void matchGMS(@ByRef @Const Size size, @ByRef @Const Size size2, @ByRef @Const KeyPointVector keyPointVector, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByRef DMatchVector dMatchVector2);

    @Namespace("cv::xfeatures2d")
    public static native void matchGMS(@ByRef @Const Size size, @ByRef @Const Size size2, @ByRef @Const KeyPointVector keyPointVector, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByRef DMatchVector dMatchVector2, @Cast({"const bool"}) boolean z, @Cast({"const bool"}) boolean z2, double d);

    @Namespace("cv::xfeatures2d")
    public static native void matchLOGOS(@ByRef @Const KeyPointVector keyPointVector, @ByRef @Const KeyPointVector keyPointVector2, @StdVector IntBuffer intBuffer, @StdVector IntBuffer intBuffer2, @ByRef DMatchVector dMatchVector);

    @Namespace("cv::xfeatures2d")
    public static native void matchLOGOS(@ByRef @Const KeyPointVector keyPointVector, @ByRef @Const KeyPointVector keyPointVector2, @StdVector IntPointer intPointer, @StdVector IntPointer intPointer2, @ByRef DMatchVector dMatchVector);

    @Namespace("cv::xfeatures2d")
    public static native void matchLOGOS(@ByRef @Const KeyPointVector keyPointVector, @ByRef @Const KeyPointVector keyPointVector2, @StdVector int[] iArr, @StdVector int[] iArr2, @ByRef DMatchVector dMatchVector);

    static {
        Loader.load();
    }
}
