package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.MinProblemSolver;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class DownhillSolver extends MinProblemSolver {
    @opencv_core.Ptr
    public static native DownhillSolver create();

    @opencv_core.Ptr
    public static native DownhillSolver create(@opencv_core.Ptr MinProblemSolver.Function function, @ByVal(nullValue = "cv::InputArray(cv::Mat_<double>(1,1,0.0))") GpuMat gpuMat, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER+cv::TermCriteria::EPS,5000,0.000001)") TermCriteria termCriteria);

    @opencv_core.Ptr
    public static native DownhillSolver create(@opencv_core.Ptr MinProblemSolver.Function function, @ByVal(nullValue = "cv::InputArray(cv::Mat_<double>(1,1,0.0))") Mat mat, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER+cv::TermCriteria::EPS,5000,0.000001)") TermCriteria termCriteria);

    @opencv_core.Ptr
    public static native DownhillSolver create(@opencv_core.Ptr MinProblemSolver.Function function, @ByVal(nullValue = "cv::InputArray(cv::Mat_<double>(1,1,0.0))") UMat uMat, @ByVal(nullValue = "cv::TermCriteria(cv::TermCriteria::MAX_ITER+cv::TermCriteria::EPS,5000,0.000001)") TermCriteria termCriteria);

    public native void getInitStep(@ByVal GpuMat gpuMat);

    public native void getInitStep(@ByVal Mat mat);

    public native void getInitStep(@ByVal UMat uMat);

    public native void setInitStep(@ByVal GpuMat gpuMat);

    public native void setInitStep(@ByVal Mat mat);

    public native void setInitStep(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public DownhillSolver(Pointer pointer) {
        super(pointer);
    }
}
