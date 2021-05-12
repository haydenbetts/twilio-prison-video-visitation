package org.bytedeco.opencv.opencv_cudaimgproc;

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
import org.bytedeco.opencv.presets.opencv_cudaimgproc;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudaimgproc.class})
public class CornersDetector extends Algorithm {
    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat3, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }

    public CornersDetector(Pointer pointer) {
        super(pointer);
    }
}
