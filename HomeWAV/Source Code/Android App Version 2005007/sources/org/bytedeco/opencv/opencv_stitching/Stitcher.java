package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.opencv_features2d.Feature2D;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv")
@Properties(inherit = {opencv_stitching.class})
public class Stitcher extends Pointer {
    public static final int ERR_CAMERA_PARAMS_ADJUST_FAIL = 3;
    public static final int ERR_HOMOGRAPHY_EST_FAIL = 2;
    public static final int ERR_NEED_MORE_IMGS = 1;
    public static final int OK = 0;
    public static final double ORIG_RESOL = ORIG_RESOL();
    public static final int PANORAMA = 0;
    public static final int SCANS = 1;

    @MemberGetter
    public static native double ORIG_RESOL();

    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native Stitcher create();

    @opencv_core.Ptr
    public static native Stitcher create(@Cast({"cv::Stitcher::Mode"}) int i);

    @opencv_core.Ptr
    public native Blender blender();

    @opencv_core.Ptr
    public native BundleAdjusterBase bundleAdjuster();

    @StdVector
    public native CameraParams cameras();

    @StdVector
    public native IntPointer component();

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal MatVector matVector, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal MatVector matVector, @ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int composePanorama(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    public native double compositingResol();

    @Cast({"cv::Stitcher::Status"})
    public native int estimateTransform(@ByVal GpuMatVector gpuMatVector);

    @Cast({"cv::Stitcher::Status"})
    public native int estimateTransform(@ByVal GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2);

    @Cast({"cv::Stitcher::Status"})
    public native int estimateTransform(@ByVal MatVector matVector);

    @Cast({"cv::Stitcher::Status"})
    public native int estimateTransform(@ByVal MatVector matVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector2);

    @Cast({"cv::Stitcher::Status"})
    public native int estimateTransform(@ByVal UMatVector uMatVector);

    @Cast({"cv::Stitcher::Status"})
    public native int estimateTransform(@ByVal UMatVector uMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector2);

    @opencv_core.Ptr
    public native Estimator estimator();

    @opencv_core.Ptr
    public native ExposureCompensator exposureCompensator();

    @opencv_core.Ptr
    public native Feature2D featuresFinder();

    @opencv_core.Ptr
    public native FeaturesMatcher featuresMatcher();

    @Cast({"cv::InterpolationFlags"})
    public native int interpolationFlags();

    @ByRef
    @Const
    public native UMat matchingMask();

    public native double panoConfidenceThresh();

    public native double registrationResol();

    @ByVal
    public native UMat resultMask();

    public native double seamEstimationResol();

    @opencv_core.Ptr
    public native SeamFinder seamFinder();

    public native void setBlender(@opencv_core.Ptr Blender blender);

    public native void setBundleAdjuster(@opencv_core.Ptr BundleAdjusterBase bundleAdjusterBase);

    public native void setCompositingResol(double d);

    public native void setEstimator(@opencv_core.Ptr Estimator estimator);

    public native void setExposureCompensator(@opencv_core.Ptr ExposureCompensator exposureCompensator);

    public native void setFeaturesFinder(@opencv_core.Ptr Feature2D feature2D);

    public native void setFeaturesMatcher(@opencv_core.Ptr FeaturesMatcher featuresMatcher);

    public native void setInterpolationFlags(@Cast({"cv::InterpolationFlags"}) int i);

    public native void setMatchingMask(@ByRef @Const UMat uMat);

    public native void setPanoConfidenceThresh(double d);

    public native void setRegistrationResol(double d);

    public native void setSeamEstimationResol(double d);

    public native void setSeamFinder(@opencv_core.Ptr SeamFinder seamFinder);

    public native void setWarper(@opencv_core.Ptr WarperCreator warperCreator);

    public native void setWaveCorrectKind(@Cast({"cv::detail::WaveCorrectKind"}) int i);

    public native void setWaveCorrection(@Cast({"bool"}) boolean z);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMatVector gpuMatVector2, @ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal MatVector matVector, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal MatVector matVector, @ByVal MatVector matVector2, @ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal MatVector matVector, @ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal GpuMat gpuMat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal Mat mat);

    @Cast({"cv::Stitcher::Status"})
    public native int stitch(@ByVal UMatVector uMatVector, @ByVal UMatVector uMatVector2, @ByVal UMat uMat);

    @opencv_core.Ptr
    public native WarperCreator warper();

    @Cast({"cv::detail::WaveCorrectKind"})
    public native int waveCorrectKind();

    @Cast({"bool"})
    public native boolean waveCorrection();

    public native double workScale();

    static {
        Loader.load();
    }

    public Stitcher() {
        super((Pointer) null);
        allocate();
    }

    public Stitcher(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Stitcher(Pointer pointer) {
        super(pointer);
    }

    public Stitcher position(long j) {
        return (Stitcher) super.position(j);
    }

    public Stitcher getPointer(long j) {
        return new Stitcher((Pointer) this).position(this.position + j);
    }
}
