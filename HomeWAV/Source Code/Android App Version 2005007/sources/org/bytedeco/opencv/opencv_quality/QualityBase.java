package org.bytedeco.opencv.opencv_quality;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_quality;

@Namespace("cv::quality")
@Properties(inherit = {opencv_quality.class})
@NoOffset
public class QualityBase extends Algorithm {
    public native void clear();

    @ByVal
    public native Scalar compute(@ByVal GpuMat gpuMat);

    @ByVal
    public native Scalar compute(@ByVal Mat mat);

    @ByVal
    public native Scalar compute(@ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean empty();

    public native void getQualityMap(@ByVal GpuMat gpuMat);

    public native void getQualityMap(@ByVal Mat mat);

    public native void getQualityMap(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public QualityBase(Pointer pointer) {
        super(pointer);
    }
}
