package org.bytedeco.opencv.opencv_shape;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_shape;

@Namespace("cv")
@Properties(inherit = {opencv_shape.class})
public class HistogramCostExtractor extends Algorithm {
    public native void buildCostMatrix(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void buildCostMatrix(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void buildCostMatrix(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native float getDefaultCost();

    public native int getNDummies();

    public native void setDefaultCost(float f);

    public native void setNDummies(int i);

    static {
        Loader.load();
    }

    public HistogramCostExtractor(Pointer pointer) {
        super(pointer);
    }
}
