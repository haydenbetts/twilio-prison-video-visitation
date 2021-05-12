package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv::tracking")
@Properties(inherit = {opencv_tracking.class})
@NoOffset
public class UnscentedKalmanFilterParams extends Pointer {
    private native void allocate();

    private native void allocate(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel);

    private native void allocate(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel, int i4);

    private native void allocateArray(long j);

    public native int CP();

    public native UnscentedKalmanFilterParams CP(int i);

    public native int DP();

    public native UnscentedKalmanFilterParams DP(int i);

    public native int MP();

    public native UnscentedKalmanFilterParams MP(int i);

    public native double alpha();

    public native UnscentedKalmanFilterParams alpha(double d);

    public native double beta();

    public native UnscentedKalmanFilterParams beta(double d);

    public native int dataType();

    public native UnscentedKalmanFilterParams dataType(int i);

    @ByRef
    public native Mat errorCovInit();

    public native UnscentedKalmanFilterParams errorCovInit(Mat mat);

    public native void init(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel);

    public native void init(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel, int i4);

    public native double k();

    public native UnscentedKalmanFilterParams k(double d);

    @ByRef
    public native Mat measurementNoiseCov();

    public native UnscentedKalmanFilterParams measurementNoiseCov(Mat mat);

    @opencv_core.Ptr
    public native UkfSystemModel model();

    public native UnscentedKalmanFilterParams model(UkfSystemModel ukfSystemModel);

    @ByRef
    public native Mat processNoiseCov();

    public native UnscentedKalmanFilterParams processNoiseCov(Mat mat);

    @ByRef
    public native Mat stateInit();

    public native UnscentedKalmanFilterParams stateInit(Mat mat);

    static {
        Loader.load();
    }

    public UnscentedKalmanFilterParams(Pointer pointer) {
        super(pointer);
    }

    public UnscentedKalmanFilterParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public UnscentedKalmanFilterParams position(long j) {
        return (UnscentedKalmanFilterParams) super.position(j);
    }

    public UnscentedKalmanFilterParams getPointer(long j) {
        return new UnscentedKalmanFilterParams((Pointer) this).position(this.position + j);
    }

    public UnscentedKalmanFilterParams() {
        super((Pointer) null);
        allocate();
    }

    public UnscentedKalmanFilterParams(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel, int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, d, d2, ukfSystemModel, i4);
    }

    public UnscentedKalmanFilterParams(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel) {
        super((Pointer) null);
        allocate(i, i2, i3, d, d2, ukfSystemModel);
    }
}
