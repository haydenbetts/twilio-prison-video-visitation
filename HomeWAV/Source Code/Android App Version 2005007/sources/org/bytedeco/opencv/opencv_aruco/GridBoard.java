package org.bytedeco.opencv.opencv_aruco;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_aruco;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::aruco")
@Properties(inherit = {opencv_aruco.class})
@NoOffset
public class GridBoard extends Board {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native GridBoard create(int i, int i2, float f, float f2, @opencv_core.Ptr Dictionary dictionary);

    @opencv_core.Ptr
    public static native GridBoard create(int i, int i2, float f, float f2, @opencv_core.Ptr Dictionary dictionary, int i3);

    public native void draw(@ByVal Size size, @ByVal GpuMat gpuMat);

    public native void draw(@ByVal Size size, @ByVal GpuMat gpuMat, int i, int i2);

    public native void draw(@ByVal Size size, @ByVal Mat mat);

    public native void draw(@ByVal Size size, @ByVal Mat mat, int i, int i2);

    public native void draw(@ByVal Size size, @ByVal UMat uMat);

    public native void draw(@ByVal Size size, @ByVal UMat uMat, int i, int i2);

    @ByVal
    public native Size getGridSize();

    public native float getMarkerLength();

    public native float getMarkerSeparation();

    static {
        Loader.load();
    }

    public GridBoard() {
        super((Pointer) null);
        allocate();
    }

    public GridBoard(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public GridBoard(Pointer pointer) {
        super(pointer);
    }

    public GridBoard position(long j) {
        return (GridBoard) super.position(j);
    }

    public GridBoard getPointer(long j) {
        return new GridBoard((Pointer) this).position(this.position + j);
    }
}
