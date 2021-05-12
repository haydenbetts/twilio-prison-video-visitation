package org.bytedeco.opencv.opencv_cudaarithm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_cudaarithm;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaarithm.class})
public class DFT extends Algorithm {
    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }

    public DFT(Pointer pointer) {
        super(pointer);
    }
}
