package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class ProjectorBase extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float k(int i);

    @MemberGetter
    public native FloatPointer k();

    public native ProjectorBase k(int i, float f);

    public native float k_rinv(int i);

    @MemberGetter
    public native FloatPointer k_rinv();

    public native ProjectorBase k_rinv(int i, float f);

    public native float r_kinv(int i);

    @MemberGetter
    public native FloatPointer r_kinv();

    public native ProjectorBase r_kinv(int i, float f);

    public native float rinv(int i);

    @MemberGetter
    public native FloatPointer rinv();

    public native ProjectorBase rinv(int i, float f);

    public native float scale();

    public native ProjectorBase scale(float f);

    public native void setCameraParams();

    public native void setCameraParams(@ByVal(nullValue = "cv::InputArray(cv::Mat::eye(3, 3, CV_32F))") GpuMat gpuMat, @ByVal(nullValue = "cv::InputArray(cv::Mat::eye(3, 3, CV_32F))") GpuMat gpuMat2, @ByVal(nullValue = "cv::InputArray(cv::Mat::zeros(3, 1, CV_32F))") GpuMat gpuMat3);

    public native void setCameraParams(@ByVal(nullValue = "cv::InputArray(cv::Mat::eye(3, 3, CV_32F))") Mat mat, @ByVal(nullValue = "cv::InputArray(cv::Mat::eye(3, 3, CV_32F))") Mat mat2, @ByVal(nullValue = "cv::InputArray(cv::Mat::zeros(3, 1, CV_32F))") Mat mat3);

    public native void setCameraParams(@ByVal(nullValue = "cv::InputArray(cv::Mat::eye(3, 3, CV_32F))") UMat uMat, @ByVal(nullValue = "cv::InputArray(cv::Mat::eye(3, 3, CV_32F))") UMat uMat2, @ByVal(nullValue = "cv::InputArray(cv::Mat::zeros(3, 1, CV_32F))") UMat uMat3);

    public native float t(int i);

    @MemberGetter
    public native FloatPointer t();

    public native ProjectorBase t(int i, float f);

    static {
        Loader.load();
    }

    public ProjectorBase() {
        super((Pointer) null);
        allocate();
    }

    public ProjectorBase(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ProjectorBase(Pointer pointer) {
        super(pointer);
    }

    public ProjectorBase position(long j) {
        return (ProjectorBase) super.position(j);
    }

    public ProjectorBase getPointer(long j) {
        return new ProjectorBase((Pointer) this).position(this.position + j);
    }
}
