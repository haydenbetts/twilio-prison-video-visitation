package org.bytedeco.opencv.opencv_cudaimgproc;

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
import org.bytedeco.opencv.opencv_imgproc.CLAHE;
import org.bytedeco.opencv.presets.opencv_cudaimgproc;

@Properties(inherit = {opencv_cudaimgproc.class})
@Name({"cv::cuda::CLAHE"})
public class CudaCLAHE extends CLAHE {
    public native void apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef Stream stream);

    public native void apply(@ByVal Mat mat, @ByVal Mat mat2, @ByRef Stream stream);

    public native void apply(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef Stream stream);

    static {
        Loader.load();
    }

    public CudaCLAHE(Pointer pointer) {
        super(pointer);
    }
}
