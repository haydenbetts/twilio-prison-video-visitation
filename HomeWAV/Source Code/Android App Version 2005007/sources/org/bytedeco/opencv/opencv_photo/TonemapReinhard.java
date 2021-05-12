package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class TonemapReinhard extends Tonemap {
    public native float getColorAdaptation();

    public native float getIntensity();

    public native float getLightAdaptation();

    public native void setColorAdaptation(float f);

    public native void setIntensity(float f);

    public native void setLightAdaptation(float f);

    static {
        Loader.load();
    }

    public TonemapReinhard(Pointer pointer) {
        super(pointer);
    }
}
