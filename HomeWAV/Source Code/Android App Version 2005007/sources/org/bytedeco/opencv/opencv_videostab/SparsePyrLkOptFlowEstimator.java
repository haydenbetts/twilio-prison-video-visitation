package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class SparsePyrLkOptFlowEstimator extends PyrLkOptFlowEstimatorBase {
    private native void allocate();

    private native void allocateArray(long j);

    @Namespace
    @Name({"static_cast<cv::videostab::ISparseOptFlowEstimator*>"})
    public static native ISparseOptFlowEstimator asISparseOptFlowEstimator(SparsePyrLkOptFlowEstimator sparsePyrLkOptFlowEstimator);

    public native void run(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5, @ByVal GpuMat gpuMat6);

    public native void run(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5, @ByVal Mat mat6);

    public native void run(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5, @ByVal UMat uMat6);

    static {
        Loader.load();
    }

    public SparsePyrLkOptFlowEstimator() {
        super((Pointer) null);
        allocate();
    }

    public SparsePyrLkOptFlowEstimator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SparsePyrLkOptFlowEstimator(Pointer pointer) {
        super(pointer);
    }

    public SparsePyrLkOptFlowEstimator position(long j) {
        return (SparsePyrLkOptFlowEstimator) super.position(j);
    }

    public SparsePyrLkOptFlowEstimator getPointer(long j) {
        return new SparsePyrLkOptFlowEstimator((Pointer) this).position(this.position + j);
    }

    public ISparseOptFlowEstimator asISparseOptFlowEstimator() {
        return asISparseOptFlowEstimator(this);
    }
}
