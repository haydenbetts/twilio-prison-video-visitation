package org.bytedeco.opencv.opencv_aruco;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_aruco;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::aruco")
@Properties(inherit = {opencv_aruco.class})
@NoOffset
public class Dictionary extends Pointer {
    private native void allocate();

    private native void allocate(@opencv_core.Ptr Dictionary dictionary);

    private native void allocate(@ByRef(nullValue = "cv::Mat()") @Const Mat mat, int i, int i2);

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native Dictionary create(int i, int i2);

    @opencv_core.Ptr
    public static native Dictionary create(int i, int i2, int i3);

    @opencv_core.Ptr
    @Name({"create"})
    public static native Dictionary create_from(int i, int i2, @opencv_core.Ptr Dictionary dictionary);

    @opencv_core.Ptr
    @Name({"create"})
    public static native Dictionary create_from(int i, int i2, @opencv_core.Ptr Dictionary dictionary, int i3);

    @opencv_core.Ptr
    public static native Dictionary get(int i);

    @ByVal
    public static native Mat getBitsFromByteList(@ByRef @Const Mat mat, int i);

    @ByVal
    public static native Mat getByteListFromBits(@ByRef @Const Mat mat);

    public native Dictionary bytesList(Mat mat);

    @ByRef
    public native Mat bytesList();

    public native void drawMarker(int i, int i2, @ByVal GpuMat gpuMat);

    public native void drawMarker(int i, int i2, @ByVal GpuMat gpuMat, int i3);

    public native void drawMarker(int i, int i2, @ByVal Mat mat);

    public native void drawMarker(int i, int i2, @ByVal Mat mat, int i3);

    public native void drawMarker(int i, int i2, @ByVal UMat uMat);

    public native void drawMarker(int i, int i2, @ByVal UMat uMat, int i3);

    public native int getDistanceToId(@ByVal GpuMat gpuMat, int i);

    public native int getDistanceToId(@ByVal GpuMat gpuMat, int i, @Cast({"bool"}) boolean z);

    public native int getDistanceToId(@ByVal Mat mat, int i);

    public native int getDistanceToId(@ByVal Mat mat, int i, @Cast({"bool"}) boolean z);

    public native int getDistanceToId(@ByVal UMat uMat, int i);

    public native int getDistanceToId(@ByVal UMat uMat, int i, @Cast({"bool"}) boolean z);

    @Cast({"bool"})
    public native boolean identify(@ByRef @Const Mat mat, @ByRef IntBuffer intBuffer, @ByRef IntBuffer intBuffer2, double d);

    @Cast({"bool"})
    public native boolean identify(@ByRef @Const Mat mat, @ByRef IntPointer intPointer, @ByRef IntPointer intPointer2, double d);

    @Cast({"bool"})
    public native boolean identify(@ByRef @Const Mat mat, @ByRef int[] iArr, @ByRef int[] iArr2, double d);

    public native int markerSize();

    public native Dictionary markerSize(int i);

    public native int maxCorrectionBits();

    public native Dictionary maxCorrectionBits(int i);

    static {
        Loader.load();
    }

    public Dictionary(Pointer pointer) {
        super(pointer);
    }

    public Dictionary(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Dictionary position(long j) {
        return (Dictionary) super.position(j);
    }

    public Dictionary getPointer(long j) {
        return new Dictionary(this).position(this.position + j);
    }

    public Dictionary(@ByRef(nullValue = "cv::Mat()") @Const Mat mat, int i, int i2) {
        super((Pointer) null);
        allocate(mat, i, i2);
    }

    public Dictionary() {
        super((Pointer) null);
        allocate();
    }

    public Dictionary(@opencv_core.Ptr Dictionary dictionary) {
        super((Pointer) null);
        allocate(dictionary);
    }
}
