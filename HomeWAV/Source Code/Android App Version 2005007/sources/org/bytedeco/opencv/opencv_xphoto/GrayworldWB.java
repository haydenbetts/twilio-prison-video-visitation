package org.bytedeco.opencv.opencv_xphoto;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_xphoto;

@Namespace("cv::xphoto")
@Properties(inherit = {opencv_xphoto.class})
public class GrayworldWB extends WhiteBalancer {
    public native float getSaturationThreshold();

    public native void setSaturationThreshold(float f);

    static {
        Loader.load();
    }

    public GrayworldWB(Pointer pointer) {
        super(pointer);
    }
}
