package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv::tracking")
@Properties(inherit = {opencv_tracking.class})
public class UnscentedKalmanFilter extends Pointer {
    @ByVal
    public native Mat correct(@ByVal GpuMat gpuMat);

    @ByVal
    public native Mat correct(@ByVal Mat mat);

    @ByVal
    public native Mat correct(@ByVal UMat uMat);

    @ByVal
    public native Mat getErrorCov();

    @ByVal
    public native Mat getMeasurementNoiseCov();

    @ByVal
    public native Mat getProcessNoiseCov();

    @ByVal
    public native Mat getState();

    @ByVal
    public native Mat predict();

    @ByVal
    public native Mat predict(@ByVal(nullValue = "cv::InputArray(cv::noArray())") GpuMat gpuMat);

    @ByVal
    public native Mat predict(@ByVal(nullValue = "cv::InputArray(cv::noArray())") Mat mat);

    @ByVal
    public native Mat predict(@ByVal(nullValue = "cv::InputArray(cv::noArray())") UMat uMat);

    static {
        Loader.load();
    }

    public UnscentedKalmanFilter(Pointer pointer) {
        super(pointer);
    }
}
