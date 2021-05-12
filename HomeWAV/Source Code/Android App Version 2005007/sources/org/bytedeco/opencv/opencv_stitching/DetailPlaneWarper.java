package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Properties(inherit = {opencv_stitching.class})
@Name({"cv::detail::PlaneWarper"})
public class DetailPlaneWarper extends RotationWarper {
    private native void allocate();

    private native void allocate(float f);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    @ByVal
    public native Point warp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Point warp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i, int i2, @ByVal GpuMat gpuMat5);

    @ByVal
    public native Point warp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, @ByVal Mat mat4);

    @ByVal
    public native Point warp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i, int i2, @ByVal Mat mat5);

    @ByVal
    public native Point warp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, @ByVal UMat uMat4);

    @ByVal
    public native Point warp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i, int i2, @ByVal UMat uMat5);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal Mat mat, @ByVal Mat mat2);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal UMat uMat, @ByVal UMat uMat2);

    @ByVal
    public native Point2f warpPoint(@ByRef @Const Point2f point2f, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2);

    @ByVal
    public native Rect warpRoi(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    static {
        Loader.load();
    }

    public DetailPlaneWarper(Pointer pointer) {
        super(pointer);
    }

    public DetailPlaneWarper(float f) {
        super((Pointer) null);
        allocate(f);
    }

    public DetailPlaneWarper() {
        super((Pointer) null);
        allocate();
    }
}
