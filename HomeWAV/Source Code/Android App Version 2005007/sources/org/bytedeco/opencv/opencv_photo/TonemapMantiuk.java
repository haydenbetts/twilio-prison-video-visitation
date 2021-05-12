package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class TonemapMantiuk extends Tonemap {
    public native float getSaturation();

    public native float getScale();

    public native void setSaturation(float f);

    public native void setScale(float f);

    static {
        Loader.load();
    }

    public TonemapMantiuk(Pointer pointer) {
        super(pointer);
    }
}
