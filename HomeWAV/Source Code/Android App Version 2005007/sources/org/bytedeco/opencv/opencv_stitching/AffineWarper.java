package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class AffineWarper extends DetailPlaneWarper {
    private native void allocate();

    private native void allocate(float f);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @ByVal
    public native Point warp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Point warp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, @ByVal Mat mat4);

    @ByVal
    public native Point warp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, @ByVal UMat uMat4);

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

    public AffineWarper(Pointer pointer) {
        super(pointer);
    }

    public AffineWarper(float f) {
        super((Pointer) null);
        allocate(f);
    }

    public AffineWarper() {
        super((Pointer) null);
        allocate();
    }
}
