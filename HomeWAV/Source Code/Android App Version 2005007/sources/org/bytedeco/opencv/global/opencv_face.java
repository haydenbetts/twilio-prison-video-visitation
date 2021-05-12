package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_face.CParams;
import org.bytedeco.opencv.opencv_face.Facemark;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_face extends org.bytedeco.opencv.presets.opencv_face {
    @Namespace("cv::face")
    @opencv_core.Ptr
    public static native Facemark createFacemarkAAM();

    @Namespace("cv::face")
    @opencv_core.Ptr
    public static native Facemark createFacemarkKazemi();

    @Namespace("cv::face")
    @opencv_core.Ptr
    public static native Facemark createFacemarkLBF();

    @Namespace("cv::face")
    public static native void drawFacemarks(@ByVal Mat mat, @ByRef Point2fVector point2fVector, @ByVal(nullValue = "cv::Scalar(255,0,0)") Scalar scalar);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean getFaces(@ByVal Mat mat, @ByRef RectVector rectVector, CParams cParams);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean getFacesHAAR(@ByVal Mat mat, @ByRef RectVector rectVector, @opencv_core.Str String str);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean loadDatasetList(@opencv_core.Str String str, @opencv_core.Str String str2, @ByRef StringVector stringVector, @ByRef StringVector stringVector2);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean loadDatasetList(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2, @ByRef StringVector stringVector, @ByRef StringVector stringVector2);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean loadFacePoints(@opencv_core.Str String str, @ByRef Point2fVectorVector point2fVectorVector, float f);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean loadTrainingData(@opencv_core.Str String str, @opencv_core.Str String str2, @ByRef StringVector stringVector, @ByRef Point2fVectorVector point2fVectorVector, float f);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean loadTrainingData(@opencv_core.Str String str, @ByRef StringVector stringVector, @ByRef Point2fVectorVector point2fVectorVector, @Cast({"char"}) byte b, float f);

    @Namespace("cv::face")
    @Cast({"bool"})
    public static native boolean loadTrainingData(@ByVal StringVector stringVector, @ByRef Point2fVectorVector point2fVectorVector, @ByRef StringVector stringVector2);

    static {
        Loader.load();
    }
}
