package org.bytedeco.opencv.opencv_cudacodec;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.presets.opencv_cudacodec;

@Properties(inherit = {opencv_cudacodec.class})
@Name({"cv::cudacodec::VideoReader"})
public class VideoReader extends Pointer {
    @ByVal
    public native FormatInfo format();

    @Cast({"bool"})
    public native boolean nextFrame(@ByRef GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean nextFrame(@ByRef GpuMat gpuMat, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    static {
        Loader.load();
    }

    public VideoReader(Pointer pointer) {
        super(pointer);
    }
}
