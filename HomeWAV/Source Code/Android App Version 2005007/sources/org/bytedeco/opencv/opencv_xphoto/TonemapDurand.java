package org.bytedeco.opencv.opencv_xphoto;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_photo.Tonemap;
import org.bytedeco.opencv.presets.opencv_xphoto;

@Namespace("cv::xphoto")
@Properties(inherit = {opencv_xphoto.class})
public class TonemapDurand extends Tonemap {
    public native float getContrast();

    public native float getSaturation();

    public native float getSigmaColor();

    public native float getSigmaSpace();

    public native void setContrast(float f);

    public native void setSaturation(float f);

    public native void setSigmaColor(float f);

    public native void setSigmaSpace(float f);

    static {
        Loader.load();
    }

    public TonemapDurand(Pointer pointer) {
        super(pointer);
    }
}
