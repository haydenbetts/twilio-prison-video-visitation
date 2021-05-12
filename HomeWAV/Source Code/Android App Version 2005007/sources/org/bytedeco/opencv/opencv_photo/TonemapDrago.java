package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class TonemapDrago extends Tonemap {
    public native float getBias();

    public native float getSaturation();

    public native void setBias(float f);

    public native void setSaturation(float f);

    static {
        Loader.load();
    }

    public TonemapDrago(Pointer pointer) {
        super(pointer);
    }
}
