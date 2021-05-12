package org.bytedeco.opencv.opencv_calib3d;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_calib3d;

@Namespace("cv")
@Properties(inherit = {opencv_calib3d.class})
public class StereoMatcher extends Algorithm {
    public static final int DISP_SCALE = 16;
    public static final int DISP_SHIFT = 4;

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native int getBlockSize();

    public native int getDisp12MaxDiff();

    public native int getMinDisparity();

    public native int getNumDisparities();

    public native int getSpeckleRange();

    public native int getSpeckleWindowSize();

    public native void setBlockSize(int i);

    public native void setDisp12MaxDiff(int i);

    public native void setMinDisparity(int i);

    public native void setNumDisparities(int i);

    public native void setSpeckleRange(int i);

    public native void setSpeckleWindowSize(int i);

    static {
        Loader.load();
    }

    public StereoMatcher(Pointer pointer) {
        super(pointer);
    }
}
