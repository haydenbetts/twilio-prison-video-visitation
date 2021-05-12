package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatBytePairVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class ExposureCompensator extends Pointer {
    public static final int CHANNELS = 3;
    public static final int CHANNELS_BLOCKS = 4;
    public static final int GAIN = 1;
    public static final int GAIN_BLOCKS = 2;
    public static final int NO = 0;

    @opencv_core.Ptr
    public static native ExposureCompensator createDefault(int i);

    public native void apply(int i, @ByVal Point point, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(int i, @ByVal Point point, @ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(int i, @ByVal Point point, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void feed(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector, @ByRef @Const UMatBytePairVector uMatBytePairVector);

    public native void feed(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector, @ByRef @Const UMatVector uMatVector2);

    public native void getMatGains(@ByRef MatVector matVector);

    @Cast({"bool"})
    public native boolean getUpdateGain();

    public native void setMatGains(@ByRef MatVector matVector);

    public native void setUpdateGain(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public ExposureCompensator(Pointer pointer) {
        super(pointer);
    }
}
