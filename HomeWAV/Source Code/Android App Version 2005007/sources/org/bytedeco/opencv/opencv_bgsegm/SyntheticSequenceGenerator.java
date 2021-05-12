package org.bytedeco.opencv.opencv_bgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_bgsegm;

@Namespace("cv::bgsegm")
@Properties(inherit = {opencv_bgsegm.class})
@NoOffset
public class SyntheticSequenceGenerator extends Algorithm {
    private native void allocate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, double d3, double d4);

    private native void allocate(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, double d3, double d4);

    private native void allocate(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, double d3, double d4);

    public native void getNextFrame(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void getNextFrame(@ByVal Mat mat, @ByVal Mat mat2);

    public native void getNextFrame(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public SyntheticSequenceGenerator(Pointer pointer) {
        super(pointer);
    }

    public SyntheticSequenceGenerator(@ByVal Mat mat, @ByVal Mat mat2, double d, double d2, double d3, double d4) {
        super((Pointer) null);
        allocate(mat, mat2, d, d2, d3, d4);
    }

    public SyntheticSequenceGenerator(@ByVal UMat uMat, @ByVal UMat uMat2, double d, double d2, double d3, double d4) {
        super((Pointer) null);
        allocate(uMat, uMat2, d, d2, d3, d4);
    }

    public SyntheticSequenceGenerator(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, double d, double d2, double d3, double d4) {
        super((Pointer) null);
        allocate(gpuMat, gpuMat2, d, d2, d3, d4);
    }
}
