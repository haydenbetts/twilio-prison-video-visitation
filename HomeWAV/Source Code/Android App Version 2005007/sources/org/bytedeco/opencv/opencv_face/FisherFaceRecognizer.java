package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Namespace("cv::face")
@Properties(inherit = {opencv_face.class})
public class FisherFaceRecognizer extends BasicFaceRecognizer {
    @opencv_core.Ptr
    public static native FisherFaceRecognizer create();

    @opencv_core.Ptr
    public static native FisherFaceRecognizer create(int i, double d);

    static {
        Loader.load();
    }

    public FisherFaceRecognizer(Pointer pointer) {
        super(pointer);
    }
}
