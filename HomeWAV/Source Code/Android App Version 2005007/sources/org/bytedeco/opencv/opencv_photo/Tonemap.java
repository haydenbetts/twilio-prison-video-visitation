package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class Tonemap extends Algorithm {
    public native float getGamma();

    public native void process(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void process(@ByVal Mat mat, @ByVal Mat mat2);

    public native void process(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void setGamma(float f);

    static {
        Loader.load();
    }

    public Tonemap(Pointer pointer) {
        super(pointer);
    }
}
