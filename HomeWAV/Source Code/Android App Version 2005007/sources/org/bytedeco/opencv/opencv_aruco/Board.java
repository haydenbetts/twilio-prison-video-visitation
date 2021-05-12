package org.bytedeco.opencv.opencv_aruco;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point3fVectorVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_aruco;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::aruco")
@Properties(inherit = {opencv_aruco.class})
public class Board extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native Board create(@ByVal GpuMatVector gpuMatVector, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMat gpuMat);

    @opencv_core.Ptr
    public static native Board create(@ByVal GpuMatVector gpuMatVector, @opencv_core.Ptr Dictionary dictionary, @ByVal Mat mat);

    @opencv_core.Ptr
    public static native Board create(@ByVal GpuMatVector gpuMatVector, @opencv_core.Ptr Dictionary dictionary, @ByVal UMat uMat);

    @opencv_core.Ptr
    public static native Board create(@ByVal MatVector matVector, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMat gpuMat);

    @opencv_core.Ptr
    public static native Board create(@ByVal MatVector matVector, @opencv_core.Ptr Dictionary dictionary, @ByVal Mat mat);

    @opencv_core.Ptr
    public static native Board create(@ByVal MatVector matVector, @opencv_core.Ptr Dictionary dictionary, @ByVal UMat uMat);

    @opencv_core.Ptr
    public static native Board create(@ByVal UMatVector uMatVector, @opencv_core.Ptr Dictionary dictionary, @ByVal GpuMat gpuMat);

    @opencv_core.Ptr
    public static native Board create(@ByVal UMatVector uMatVector, @opencv_core.Ptr Dictionary dictionary, @ByVal Mat mat);

    @opencv_core.Ptr
    public static native Board create(@ByVal UMatVector uMatVector, @opencv_core.Ptr Dictionary dictionary, @ByVal UMat uMat);

    public native Board dictionary(Dictionary dictionary);

    @opencv_core.Ptr
    public native Dictionary dictionary();

    @StdVector
    public native IntPointer ids();

    public native Board ids(IntPointer intPointer);

    public native Board objPoints(Point3fVectorVector point3fVectorVector);

    @ByRef
    public native Point3fVectorVector objPoints();

    static {
        Loader.load();
    }

    public Board() {
        super((Pointer) null);
        allocate();
    }

    public Board(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Board(Pointer pointer) {
        super(pointer);
    }

    public Board position(long j) {
        return (Board) super.position(j);
    }

    public Board getPointer(long j) {
        return new Board((Pointer) this).position(this.position + j);
    }
}
