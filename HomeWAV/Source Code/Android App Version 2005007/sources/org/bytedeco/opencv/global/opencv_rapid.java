package org.bytedeco.opencv.global;

import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;

public class opencv_rapid extends org.bytedeco.opencv.presets.opencv_rapid {
    @Namespace("cv::rapid")
    public static native void convertCorrespondencies(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Namespace("cv::rapid")
    public static native void convertCorrespondencies(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") GpuMat gpuMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat5);

    @Namespace("cv::rapid")
    public static native void convertCorrespondencies(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @Namespace("cv::rapid")
    public static native void convertCorrespondencies(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") Mat mat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat5);

    @Namespace("cv::rapid")
    public static native void convertCorrespondencies(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Namespace("cv::rapid")
    public static native void convertCorrespondencies(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::InputOutputArray(cv::noArray())") UMat uMat4, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat5);

    @Namespace("cv::rapid")
    public static native void drawCorrespondencies(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::rapid")
    public static native void drawCorrespondencies(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv::rapid")
    public static native void drawCorrespondencies(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::rapid")
    public static native void drawCorrespondencies(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3);

    @Namespace("cv::rapid")
    public static native void drawCorrespondencies(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::rapid")
    public static native void drawCorrespondencies(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv::rapid")
    public static native void drawSearchLines(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef @Const Scalar scalar);

    @Namespace("cv::rapid")
    public static native void drawSearchLines(@ByVal Mat mat, @ByVal Mat mat2, @ByRef @Const Scalar scalar);

    @Namespace("cv::rapid")
    public static native void drawSearchLines(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef @Const Scalar scalar);

    @Namespace("cv::rapid")
    public static native void drawWireframe(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef @Const Scalar scalar);

    @Namespace("cv::rapid")
    public static native void drawWireframe(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef @Const Scalar scalar, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::rapid")
    public static native void drawWireframe(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef @Const Scalar scalar);

    @Namespace("cv::rapid")
    public static native void drawWireframe(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef @Const Scalar scalar, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::rapid")
    public static native void drawWireframe(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef @Const Scalar scalar);

    @Namespace("cv::rapid")
    public static native void drawWireframe(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef @Const Scalar scalar, int i, @Cast({"bool"}) boolean z);

    @Namespace("cv::rapid")
    public static native void extractControlPoints(int i, int i2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByRef @Const Size size, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, @ByVal GpuMat gpuMat7);

    @Namespace("cv::rapid")
    public static native void extractControlPoints(int i, int i2, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByRef @Const Size size, @ByVal Mat mat5, @ByVal Mat mat6, @ByVal Mat mat7);

    @Namespace("cv::rapid")
    public static native void extractControlPoints(int i, int i2, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByRef @Const Size size, @ByVal UMat uMat5, @ByVal UMat uMat6, @ByVal UMat uMat7);

    @Namespace("cv::rapid")
    public static native void extractLineBundle(int i, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @Namespace("cv::rapid")
    public static native void extractLineBundle(int i, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @Namespace("cv::rapid")
    public static native void extractLineBundle(int i, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @Namespace("cv::rapid")
    public static native void findCorrespondencies(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::rapid")
    public static native void findCorrespondencies(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    @Namespace("cv::rapid")
    public static native void findCorrespondencies(@ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::rapid")
    public static native void findCorrespondencies(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    @Namespace("cv::rapid")
    public static native void findCorrespondencies(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::rapid")
    public static native void findCorrespondencies(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal GpuMat gpuMat, int i, int i2, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal GpuMat gpuMat, int i, int i2, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, DoubleBuffer doubleBuffer);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal GpuMat gpuMat, int i, int i2, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, DoublePointer doublePointer);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal GpuMat gpuMat, int i, int i2, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6, double[] dArr);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal Mat mat, int i, int i2, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal Mat mat, int i, int i2, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, DoubleBuffer doubleBuffer);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal Mat mat, int i, int i2, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, DoublePointer doublePointer);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal Mat mat, int i, int i2, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6, double[] dArr);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal UMat uMat, int i, int i2, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal UMat uMat, int i, int i2, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, DoubleBuffer doubleBuffer);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal UMat uMat, int i, int i2, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, DoublePointer doublePointer);

    @Namespace("cv::rapid")
    public static native float rapid(@ByVal UMat uMat, int i, int i2, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6, double[] dArr);

    static {
        Loader.load();
    }

    @Namespace("cv::rapid")
    public static class Tracker extends Algorithm {
        public native void clearState();

        public native float compute(@ByVal GpuMat gpuMat, int i, int i2, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

        public native float compute(@ByVal GpuMat gpuMat, int i, int i2, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByRef(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER | cv::TermCriteria::EPS, 5, 1.5)") @Const TermCriteria termCriteria);

        public native float compute(@ByVal Mat mat, int i, int i2, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

        public native float compute(@ByVal Mat mat, int i, int i2, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByRef(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER | cv::TermCriteria::EPS, 5, 1.5)") @Const TermCriteria termCriteria);

        public native float compute(@ByVal UMat uMat, int i, int i2, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

        public native float compute(@ByVal UMat uMat, int i, int i2, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByRef(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER | cv::TermCriteria::EPS, 5, 1.5)") @Const TermCriteria termCriteria);

        static {
            Loader.load();
        }

        public Tracker(Pointer pointer) {
            super(pointer);
        }
    }

    @Namespace("cv::rapid")
    public static class Rapid extends Tracker {
        @opencv_core.Ptr
        public static native Rapid create(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

        @opencv_core.Ptr
        public static native Rapid create(@ByVal Mat mat, @ByVal Mat mat2);

        @opencv_core.Ptr
        public static native Rapid create(@ByVal UMat uMat, @ByVal UMat uMat2);

        static {
            Loader.load();
        }

        public Rapid(Pointer pointer) {
            super(pointer);
        }
    }

    @Namespace("cv::rapid")
    public static class OLSTracker extends Tracker {
        @opencv_core.Ptr
        public static native OLSTracker create(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

        @opencv_core.Ptr
        public static native OLSTracker create(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @Cast({"uchar"}) byte b);

        @opencv_core.Ptr
        public static native OLSTracker create(@ByVal Mat mat, @ByVal Mat mat2);

        @opencv_core.Ptr
        public static native OLSTracker create(@ByVal Mat mat, @ByVal Mat mat2, int i, @Cast({"uchar"}) byte b);

        @opencv_core.Ptr
        public static native OLSTracker create(@ByVal UMat uMat, @ByVal UMat uMat2);

        @opencv_core.Ptr
        public static native OLSTracker create(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @Cast({"uchar"}) byte b);

        static {
            Loader.load();
        }

        public OLSTracker(Pointer pointer) {
            super(pointer);
        }
    }
}
