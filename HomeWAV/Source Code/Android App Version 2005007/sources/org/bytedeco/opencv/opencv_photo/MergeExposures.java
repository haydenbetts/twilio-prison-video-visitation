package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class MergeExposures extends Algorithm {
    public native void process(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void process(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void process(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void process(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void process(@ByVal MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void process(@ByVal MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void process(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void process(@ByVal UMatVector uMatVector, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void process(@ByVal UMatVector uMatVector, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    static {
        Loader.load();
    }

    public MergeExposures(Pointer pointer) {
        super(pointer);
    }
}
