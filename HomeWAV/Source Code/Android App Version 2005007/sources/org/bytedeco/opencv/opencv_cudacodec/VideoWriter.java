package org.bytedeco.opencv.opencv_cudacodec;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_cudacodec;

@Properties(inherit = {opencv_cudacodec.class})
@Name({"cv::cudacodec::VideoWriter"})
public class VideoWriter extends Pointer {
    @ByVal
    public native EncoderParams getEncoderParams();

    public native void write(@ByVal GpuMat gpuMat);

    public native void write(@ByVal GpuMat gpuMat, @Cast({"bool"}) boolean z);

    public native void write(@ByVal Mat mat);

    public native void write(@ByVal Mat mat, @Cast({"bool"}) boolean z);

    public native void write(@ByVal UMat uMat);

    public native void write(@ByVal UMat uMat, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public VideoWriter(Pointer pointer) {
        super(pointer);
    }
}
