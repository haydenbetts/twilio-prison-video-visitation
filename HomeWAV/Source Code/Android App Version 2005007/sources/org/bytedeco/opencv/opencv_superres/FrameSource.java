package org.bytedeco.opencv.opencv_superres;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_superres;

@Namespace("cv::superres")
@Properties(inherit = {opencv_superres.class})
public class FrameSource extends Pointer {
    public native void nextFrame(@ByVal GpuMat gpuMat);

    public native void nextFrame(@ByVal Mat mat);

    public native void nextFrame(@ByVal UMat uMat);

    public native void reset();

    static {
        Loader.load();
    }

    public FrameSource(Pointer pointer) {
        super(pointer);
    }
}
