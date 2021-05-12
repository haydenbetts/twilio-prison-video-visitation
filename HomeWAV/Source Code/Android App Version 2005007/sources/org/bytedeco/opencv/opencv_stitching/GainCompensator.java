package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatBytePairVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class GainCompensator extends ExposureCompensator {
    private native void allocate();

    private native void allocate(int i);

    public native void apply(int i, @ByVal Point point, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(int i, @ByVal Point point, @ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(int i, @ByVal Point point, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void feed(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector, @ByRef @Const UMatBytePairVector uMatBytePairVector);

    @StdVector
    public native DoublePointer gains();

    public native void getMatGains(@ByRef MatVector matVector);

    public native int getNrFeeds();

    public native void setMatGains(@ByRef MatVector matVector);

    public native void setNrFeeds(int i);

    public native void singleFeed(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector, @ByRef @Const UMatBytePairVector uMatBytePairVector);

    static {
        Loader.load();
    }

    public GainCompensator(Pointer pointer) {
        super(pointer);
    }

    public GainCompensator() {
        super((Pointer) null);
        allocate();
    }

    public GainCompensator(int i) {
        super((Pointer) null);
        allocate(i);
    }
}
