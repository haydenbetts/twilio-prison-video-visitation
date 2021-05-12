package org.bytedeco.opencv.global;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;

public class opencv_ml extends org.bytedeco.opencv.presets.opencv_ml {
    public static final int COL_SAMPLE = 1;
    public static final int ROW_SAMPLE = 0;
    public static final int TEST_ERROR = 0;
    public static final int TRAIN_ERROR = 1;
    public static final int VAR_CATEGORICAL = 1;
    public static final int VAR_NUMERICAL = 0;
    public static final int VAR_ORDERED = 0;

    @Namespace("cv::ml")
    public static native void createConcentricSpheresTestSet(int i, int i2, int i3, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Namespace("cv::ml")
    public static native void createConcentricSpheresTestSet(int i, int i2, int i3, @ByVal Mat mat, @ByVal Mat mat2);

    @Namespace("cv::ml")
    public static native void createConcentricSpheresTestSet(int i, int i2, int i3, @ByVal UMat uMat, @ByVal UMat uMat2);

    @Namespace("cv::ml")
    public static native void randMVNormal(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, @ByVal GpuMat gpuMat3);

    @Namespace("cv::ml")
    public static native void randMVNormal(@ByVal Mat mat, @ByVal Mat mat2, int i, @ByVal Mat mat3);

    @Namespace("cv::ml")
    public static native void randMVNormal(@ByVal UMat uMat, @ByVal UMat uMat2, int i, @ByVal UMat uMat3);

    static {
        Loader.load();
    }
}
