package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class PredictCollector extends Pointer {
    @Cast({"bool"})
    public native boolean collect(int i, double d);

    public native void init(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public PredictCollector(Pointer pointer) {
        super(pointer);
    }
}
