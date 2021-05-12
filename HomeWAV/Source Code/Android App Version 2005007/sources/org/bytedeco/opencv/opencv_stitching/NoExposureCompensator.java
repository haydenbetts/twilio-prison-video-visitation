package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
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
public class NoExposureCompensator extends ExposureCompensator {
    private native void allocate();

    private native void allocateArray(long j);

    public native void apply(int i, @ByVal Point point, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(int i, @ByVal Point point, @ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(int i, @ByVal Point point, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void feed(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector, @ByRef @Const UMatBytePairVector uMatBytePairVector);

    public native void getMatGains(@ByRef MatVector matVector);

    public native void setMatGains(@ByRef MatVector matVector);

    static {
        Loader.load();
    }

    public NoExposureCompensator() {
        super((Pointer) null);
        allocate();
    }

    public NoExposureCompensator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NoExposureCompensator(Pointer pointer) {
        super(pointer);
    }

    public NoExposureCompensator position(long j) {
        return (NoExposureCompensator) super.position(j);
    }

    public NoExposureCompensator getPointer(long j) {
        return new NoExposureCompensator((Pointer) this).position(this.position + j);
    }
}
