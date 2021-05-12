package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Properties(inherit = {opencv_stitching.class})
@Name({"cv::detail::SphericalWarperGpu"})
@NoOffset
public class DetailSphericalWarperGpu extends RotationWarper {
    private native void allocate(float f);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByRef GpuMat gpuMat, @ByRef GpuMat gpuMat2);

    @ByVal
    public native Rect buildMaps(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    @ByVal
    public native Point warp(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, @ByVal GpuMat gpuMat4);

    @ByVal
    public native Point warp(@ByRef @Const GpuMat gpuMat, @ByVal Mat mat, @ByVal Mat mat2, int i, int i2, @ByRef GpuMat gpuMat2);

    @ByVal
    public native Point warp(@ByRef @Const GpuMat gpuMat, @ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2, @ByRef GpuMat gpuMat2);

    @ByVal
    public native Point warp(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, @ByVal Mat mat4);

    @ByVal
    public native Point warp(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, @ByVal UMat uMat4);

    static {
        Loader.load();
    }

    public DetailSphericalWarperGpu(Pointer pointer) {
        super(pointer);
    }

    public DetailSphericalWarperGpu(float f) {
        super((Pointer) null);
        allocate(f);
    }
}
