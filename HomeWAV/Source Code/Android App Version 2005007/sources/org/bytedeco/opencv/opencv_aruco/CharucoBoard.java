package org.bytedeco.opencv.opencv_aruco;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.IntVectorVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point3fVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_aruco;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::aruco")
@Properties(inherit = {opencv_aruco.class})
@NoOffset
public class CharucoBoard extends Board {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native CharucoBoard create(int i, int i2, float f, float f2, @opencv_core.Ptr Dictionary dictionary);

    public native CharucoBoard chessboardCorners(Point3fVector point3fVector);

    @ByRef
    @Cast({"std::vector<cv::Point3f>*"})
    public native Point3fVector chessboardCorners();

    public native void draw(@ByVal Size size, @ByVal GpuMat gpuMat);

    public native void draw(@ByVal Size size, @ByVal GpuMat gpuMat, int i, int i2);

    public native void draw(@ByVal Size size, @ByVal Mat mat);

    public native void draw(@ByVal Size size, @ByVal Mat mat, int i, int i2);

    public native void draw(@ByVal Size size, @ByVal UMat uMat);

    public native void draw(@ByVal Size size, @ByVal UMat uMat, int i, int i2);

    @ByVal
    public native Size getChessboardSize();

    public native float getMarkerLength();

    public native float getSquareLength();

    public native CharucoBoard nearestMarkerCorners(IntVectorVector intVectorVector);

    @ByRef
    public native IntVectorVector nearestMarkerCorners();

    public native CharucoBoard nearestMarkerIdx(IntVectorVector intVectorVector);

    @ByRef
    public native IntVectorVector nearestMarkerIdx();

    static {
        Loader.load();
    }

    public CharucoBoard() {
        super((Pointer) null);
        allocate();
    }

    public CharucoBoard(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CharucoBoard(Pointer pointer) {
        super(pointer);
    }

    public CharucoBoard position(long j) {
        return (CharucoBoard) super.position(j);
    }

    public CharucoBoard getPointer(long j) {
        return new CharucoBoard((Pointer) this).position(this.position + j);
    }
}
