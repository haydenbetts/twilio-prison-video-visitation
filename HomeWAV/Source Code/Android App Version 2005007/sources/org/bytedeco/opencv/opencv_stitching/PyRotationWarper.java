package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class PyRotationWarper extends Pointer {
    private native void allocate();

    private native void allocate(@opencv_core.Str String str, float f);

    private native void allocate(@opencv_core.Str BytePointer bytePointer, float f);

    private native void allocateArray(long j);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    public native float getScale();

    public native void setScale(float f);

    @ByVal
    public native Point warp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Point warp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, @ByVal Mat mat4);

    @ByVal
    public native Point warp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, @ByVal UMat uMat4);

    public native void warpBackward(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, @ByVal Size size, @ByVal GpuMat gpuMat4);

    public native void warpBackward(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, @ByVal Size size, @ByVal Mat mat4);

    public native void warpBackward(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, @ByVal Size size, @ByVal UMat uMat4);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal Mat mat, @ByVal Mat mat2);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal UMat uMat, @ByVal UMat uMat2);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public PyRotationWarper(Pointer pointer) {
        super(pointer);
    }

    public PyRotationWarper(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public PyRotationWarper position(long j) {
        return (PyRotationWarper) super.position(j);
    }

    public PyRotationWarper getPointer(long j) {
        return new PyRotationWarper((Pointer) this).position(this.position + j);
    }

    public PyRotationWarper(@opencv_core.Str BytePointer bytePointer, float f) {
        super((Pointer) null);
        allocate(bytePointer, f);
    }

    public PyRotationWarper(@opencv_core.Str String str, float f) {
        super((Pointer) null);
        allocate(str, f);
    }

    public PyRotationWarper() {
        super((Pointer) null);
        allocate();
    }
}
