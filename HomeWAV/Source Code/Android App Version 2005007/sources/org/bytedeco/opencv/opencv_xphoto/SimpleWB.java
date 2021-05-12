package org.bytedeco.opencv.opencv_xphoto;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_xphoto;

@Namespace("cv::xphoto")
@Properties(inherit = {opencv_xphoto.class})
public class SimpleWB extends WhiteBalancer {
    public native float getInputMax();

    public native float getInputMin();

    public native float getOutputMax();

    public native float getOutputMin();

    public native float getP();

    public native void setInputMax(float f);

    public native void setInputMin(float f);

    public native void setOutputMax(float f);

    public native void setOutputMin(float f);

    public native void setP(float f);

    static {
        Loader.load();
    }

    public SimpleWB(Pointer pointer) {
        super(pointer);
    }
}
