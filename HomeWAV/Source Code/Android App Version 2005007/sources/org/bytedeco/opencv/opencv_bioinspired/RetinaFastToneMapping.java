package org.bytedeco.opencv.opencv_bioinspired;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_bioinspired;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::bioinspired")
@Properties(inherit = {opencv_bioinspired.class})
public class RetinaFastToneMapping extends Algorithm {
    @opencv_core.Ptr
    public static native RetinaFastToneMapping create(@ByVal Size size);

    public native void applyFastToneMapping(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void applyFastToneMapping(@ByVal Mat mat, @ByVal Mat mat2);

    public native void applyFastToneMapping(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void setup();

    public native void setup(float f, float f2, float f3);

    static {
        Loader.load();
    }

    public RetinaFastToneMapping(Pointer pointer) {
        super(pointer);
    }
}
