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
public class HoughCirclesDetector extends Algorithm {
    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native int getCannyThreshold();

    public native float getDp();

    public native int getMaxCircles();

    public native int getMaxRadius();

    public native float getMinDist();

    public native int getMinRadius();

    public native int getVotesThreshold();

    public native void setCannyThreshold(int i);

    public native void setDp(float f);

    public native void setMaxCircles(int i);

    public native void setMaxRadius(int i);

    public native void setMinDist(float f);

    public native void setMinRadius(int i);

    public native void setVotesThreshold(int i);

    static {
        Loader.load();
    }

    public HoughCirclesDetector(Pointer pointer) {
        super(pointer);
    }
}
