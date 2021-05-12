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
public class ShapeDistanceExtractor extends Algorithm {
    public native float computeDistance(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native float computeDistance(@ByVal Mat mat, @ByVal Mat mat2);

    public native float computeDistance(@ByVal UMat uMat, @ByVal UMat uMat2);

    static {
        Loader.load();
    }

    public ShapeDistanceExtractor(Pointer pointer) {
        super(pointer);
    }
}
