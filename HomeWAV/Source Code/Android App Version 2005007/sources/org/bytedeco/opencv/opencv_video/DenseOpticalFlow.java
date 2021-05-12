package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
public class DenseOpticalFlow extends Algorithm {
    public native void calc(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void calc(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void calc(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void collectGarbage();

    static {
        Loader.load();
    }

    public DenseOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
