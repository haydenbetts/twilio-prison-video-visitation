package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc")
@Properties(inherit = {opencv_ximgproc.class})
public class StructuredEdgeDetection extends Algorithm {
    public native void computeOrientation(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void computeOrientation(@ByVal Mat mat, @ByVal Mat mat2);

    public native void computeOrientation(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void detectEdges(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void detectEdges(@ByVal Mat mat, @ByVal Mat mat2);

    public native void detectEdges(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void edgesNms(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void edgesNms(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i, int i2, float f, @Cast({"bool"}) boolean z);

    public native void edgesNms(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void edgesNms(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i, int i2, float f, @Cast({"bool"}) boolean z);

    public native void edgesNms(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void edgesNms(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i, int i2, float f, @Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public StructuredEdgeDetection(Pointer pointer) {
        super(pointer);
    }
}
