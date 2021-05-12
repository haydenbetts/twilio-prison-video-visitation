package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv::tracking")
@Properties(inherit = {opencv_tracking.class})
public class AugmentedUnscentedKalmanFilterParams extends UnscentedKalmanFilterParams {
    private native void allocate();

    private native void allocate(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel);

    private native void allocate(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel, int i4);

    private native void allocateArray(long j);

    public native void init(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel);

    public native void init(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel, int i4);

    static {
        Loader.load();
    }

    public AugmentedUnscentedKalmanFilterParams(Pointer pointer) {
        super(pointer);
    }

    public AugmentedUnscentedKalmanFilterParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AugmentedUnscentedKalmanFilterParams position(long j) {
        return (AugmentedUnscentedKalmanFilterParams) super.position(j);
    }

    public AugmentedUnscentedKalmanFilterParams getPointer(long j) {
        return new AugmentedUnscentedKalmanFilterParams((Pointer) this).position(this.position + j);
    }

    public AugmentedUnscentedKalmanFilterParams() {
        super((Pointer) null);
        allocate();
    }

    public AugmentedUnscentedKalmanFilterParams(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel, int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, d, d2, ukfSystemModel, i4);
    }

    public AugmentedUnscentedKalmanFilterParams(int i, int i2, int i3, double d, double d2, @opencv_core.Ptr UkfSystemModel ukfSystemModel) {
        super((Pointer) null);
        allocate(i, i2, i3, d, d2, ukfSystemModel);
    }
}
