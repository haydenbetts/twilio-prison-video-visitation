package org.bytedeco.opencv.global;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.ByteVectorVector;
import org.bytedeco.opencv.opencv_core.DMatchVector;
import org.bytedeco.opencv.opencv_core.DMatchVectorVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_features2d extends org.bytedeco.opencv.presets.opencv_features2d {
    public static final int DEFAULT = 0;
    public static final int DRAW_OVER_OUTIMG = 1;
    public static final int DRAW_RICH_KEYPOINTS = 4;
    public static final int NOT_DRAW_SINGLE_POINTS = 2;

    @Namespace("cv")
    public static native void AGAST(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv")
    public static native void AGAST(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void AGAST(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::AgastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv")
    public static native void AGAST(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv")
    public static native void AGAST(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void AGAST(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::AgastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv")
    public static native void AGAST(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv")
    public static native void AGAST(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void AGAST(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::AgastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv")
    public static native void FAST(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv")
    public static native void FAST(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void FAST(@ByVal GpuMat gpuMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::FastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv")
    public static native void FAST(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv")
    public static native void FAST(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void FAST(@ByVal Mat mat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::FastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv")
    public static native void FAST(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i);

    @Namespace("cv")
    public static native void FAST(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv")
    public static native void FAST(@ByVal UMat uMat, @ByRef KeyPointVector keyPointVector, int i, @Cast({"bool"}) boolean z, @Cast({"cv::FastFeatureDetector::DetectorType"}) int i2);

    @Namespace("cv")
    @Cast({"cv::DrawMatchesFlags"})
    @Name({"operator &"})
    public static native int and(@Cast({"const cv::DrawMatchesFlags"}) int i, @Cast({"const cv::DrawMatchesFlags"}) int i2);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator &="})
    @Namespace("cv")
    public static native IntBuffer andPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) IntBuffer intBuffer, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator &="})
    @Namespace("cv")
    public static native IntPointer andPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) IntPointer intPointer, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator &="})
    @Namespace("cv")
    public static native int[] andPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) int[] iArr, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void computeRecallPrecisionCurve(@ByRef @Const DMatchVectorVector dMatchVectorVector, @ByRef @Cast({"const std::vector<std::vector<uchar> >*"}) ByteVectorVector byteVectorVector, @ByRef Point2fVector point2fVector);

    @Namespace("cv")
    public static native void drawKeypoints(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2);

    @Namespace("cv")
    public static native void drawKeypoints(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawKeypoints(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2);

    @Namespace("cv")
    public static native void drawKeypoints(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawKeypoints(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2);

    @Namespace("cv")
    public static native void drawKeypoints(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    public static native void drawMatches(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector ByteBuffer byteBuffer, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector BytePointer bytePointer, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector byte[] bArr, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal Mat mat3);

    @Namespace("cv")
    public static native void drawMatches(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal Mat mat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector ByteBuffer byteBuffer, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal Mat mat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector BytePointer bytePointer, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal Mat mat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector byte[] bArr, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal UMat uMat3);

    @Namespace("cv")
    public static native void drawMatches(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal UMat uMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector ByteBuffer byteBuffer, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal UMat uMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector BytePointer bytePointer, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    public static native void drawMatches(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVector dMatchVector, @ByVal UMat uMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @Cast({"char*"}) @StdVector byte[] bArr, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    @Name({"drawMatches"})
    public static native void drawMatchesKnn(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVectorVector dMatchVectorVector, @ByVal GpuMat gpuMat3);

    @Namespace("cv")
    @Name({"drawMatches"})
    public static native void drawMatchesKnn(@ByVal GpuMat gpuMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal GpuMat gpuMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVectorVector dMatchVectorVector, @ByVal GpuMat gpuMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @ByRef(nullValue = "std::vector<std::vector<char> >()") @Cast({"const std::vector<std::vector<char> >*"}) ByteVectorVector byteVectorVector, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    @Name({"drawMatches"})
    public static native void drawMatchesKnn(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVectorVector dMatchVectorVector, @ByVal Mat mat3);

    @Namespace("cv")
    @Name({"drawMatches"})
    public static native void drawMatchesKnn(@ByVal Mat mat, @ByRef @Const KeyPointVector keyPointVector, @ByVal Mat mat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVectorVector dMatchVectorVector, @ByVal Mat mat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @ByRef(nullValue = "std::vector<std::vector<char> >()") @Cast({"const std::vector<std::vector<char> >*"}) ByteVectorVector byteVectorVector, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    @Name({"drawMatches"})
    public static native void drawMatchesKnn(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVectorVector dMatchVectorVector, @ByVal UMat uMat3);

    @Namespace("cv")
    @Name({"drawMatches"})
    public static native void drawMatchesKnn(@ByVal UMat uMat, @ByRef @Const KeyPointVector keyPointVector, @ByVal UMat uMat2, @ByRef @Const KeyPointVector keyPointVector2, @ByRef @Const DMatchVectorVector dMatchVectorVector, @ByVal UMat uMat3, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar, @ByRef(nullValue = "cv::Scalar::all(-1)") @Const Scalar scalar2, @ByRef(nullValue = "std::vector<std::vector<char> >()") @Cast({"const std::vector<std::vector<char> >*"}) ByteVectorVector byteVectorVector, @Cast({"cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator =="})
    public static native boolean equals(@Cast({"const cv::DrawMatchesFlags"}) int i, int i2);

    @Namespace("cv")
    public static native void evaluateFeatureDetector(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, KeyPointVector keyPointVector, KeyPointVector keyPointVector2, @ByRef FloatBuffer floatBuffer, @ByRef IntBuffer intBuffer);

    @Namespace("cv")
    public static native void evaluateFeatureDetector(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, KeyPointVector keyPointVector, KeyPointVector keyPointVector2, @ByRef FloatBuffer floatBuffer, @ByRef IntBuffer intBuffer, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    @Namespace("cv")
    public static native void evaluateFeatureDetector(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, KeyPointVector keyPointVector, KeyPointVector keyPointVector2, @ByRef FloatPointer floatPointer, @ByRef IntPointer intPointer);

    @Namespace("cv")
    public static native void evaluateFeatureDetector(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, KeyPointVector keyPointVector, KeyPointVector keyPointVector2, @ByRef FloatPointer floatPointer, @ByRef IntPointer intPointer, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    @Namespace("cv")
    public static native void evaluateFeatureDetector(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, KeyPointVector keyPointVector, KeyPointVector keyPointVector2, @ByRef float[] fArr, @ByRef int[] iArr);

    @Namespace("cv")
    public static native void evaluateFeatureDetector(@ByRef @Const Mat mat, @ByRef @Const Mat mat2, @ByRef @Const Mat mat3, KeyPointVector keyPointVector, KeyPointVector keyPointVector2, @ByRef float[] fArr, @ByRef int[] iArr, @Cast({"cv::FeatureDetector*"}) @opencv_core.Ptr Feature2D feature2D);

    @Namespace("cv")
    public static native int getNearestPoint(@ByRef @Const Point2fVector point2fVector, float f);

    @Namespace("cv")
    public static native float getRecall(@ByRef @Const Point2fVector point2fVector, float f);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator !"})
    public static native boolean not(@Cast({"const cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    @Cast({"bool"})
    @Name({"operator !="})
    public static native boolean notEquals(@Cast({"const cv::DrawMatchesFlags"}) int i, int i2);

    @Namespace("cv")
    @Cast({"cv::DrawMatchesFlags"})
    @Name({"operator |"})
    public static native int or(@Cast({"const cv::DrawMatchesFlags"}) int i, @Cast({"const cv::DrawMatchesFlags"}) int i2);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator |="})
    @Namespace("cv")
    public static native IntBuffer orPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) IntBuffer intBuffer, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator |="})
    @Namespace("cv")
    public static native IntPointer orPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) IntPointer intPointer, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator |="})
    @Namespace("cv")
    public static native int[] orPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) int[] iArr, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @Namespace("cv")
    @Cast({"cv::DrawMatchesFlags"})
    @Name({"operator ^"})
    public static native int xor(@Cast({"const cv::DrawMatchesFlags"}) int i, @Cast({"const cv::DrawMatchesFlags"}) int i2);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator ^="})
    @Namespace("cv")
    public static native IntBuffer xorPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) IntBuffer intBuffer, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator ^="})
    @Namespace("cv")
    public static native IntPointer xorPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) IntPointer intPointer, @Cast({"const cv::DrawMatchesFlags"}) int i);

    @ByRef
    @Cast({"cv::DrawMatchesFlags*"})
    @Name({"operator ^="})
    @Namespace("cv")
    public static native int[] xorPut(@ByRef @Cast({"cv::DrawMatchesFlags*"}) int[] iArr, @Cast({"const cv::DrawMatchesFlags"}) int i);

    static {
        Loader.load();
    }
}
