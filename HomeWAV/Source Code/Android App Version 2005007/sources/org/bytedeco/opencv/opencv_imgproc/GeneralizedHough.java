package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Namespace("cv")
@Properties(inherit = {opencv_imgproc.class})
public class GeneralizedHough extends Algorithm {
    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat3);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    public native void detect(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") GpuMat gpuMat5);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat3);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    public native void detect(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") Mat mat5);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat3);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    public native void detect(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal(nullValue = "cv::OutputArray(cv::noArray())") UMat uMat5);

    public native int getCannyHighThresh();

    public native int getCannyLowThresh();

    public native double getDp();

    public native int getMaxBufferSize();

    public native double getMinDist();

    public native void setCannyHighThresh(int i);

    public native void setCannyLowThresh(int i);

    public native void setDp(double d);

    public native void setMaxBufferSize(int i);

    public native void setMinDist(double d);

    public native void setTemplate(@ByVal GpuMat gpuMat);

    public native void setTemplate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void setTemplate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal(nullValue = "cv::Point(-1, -1)") Point point);

    public native void setTemplate(@ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point);

    public native void setTemplate(@ByVal Mat mat);

    public native void setTemplate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void setTemplate(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal(nullValue = "cv::Point(-1, -1)") Point point);

    public native void setTemplate(@ByVal Mat mat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point);

    public native void setTemplate(@ByVal UMat uMat);

    public native void setTemplate(@ByVal UMat uMat, @ByVal(nullValue = "cv::Point(-1, -1)") Point point);

    public native void setTemplate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void setTemplate(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal(nullValue = "cv::Point(-1, -1)") Point point);

    static {
        Loader.load();
    }

    public GeneralizedHough(Pointer pointer) {
        super(pointer);
    }
}
