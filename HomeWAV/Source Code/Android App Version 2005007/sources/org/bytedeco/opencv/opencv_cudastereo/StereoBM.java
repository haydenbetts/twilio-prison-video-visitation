package org.bytedeco.opencv.opencv_cudastereo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_cudastereo;

@Properties(inherit = {opencv_cudastereo.class})
@Name({"cv::cuda::StereoBM"})
public class StereoBM extends org.bytedeco.opencv.opencv_calib3d.StereoBM {
    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef Stream stream);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef Stream stream);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef Stream stream);

    static {
        Loader.load();
    }

    public StereoBM(Pointer pointer) {
        super(pointer);
    }
}
