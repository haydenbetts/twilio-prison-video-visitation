package org.bytedeco.opencv.opencv_img_hash;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_img_hash;

@Namespace("cv::img_hash")
@Properties(inherit = {opencv_img_hash.class})
public class MarrHildrethHash extends ImgHashBase {
    @opencv_core.Ptr
    public static native MarrHildrethHash create();

    @opencv_core.Ptr
    public static native MarrHildrethHash create(float f, float f2);

    public native float getAlpha();

    public native float getScale();

    public native void setKernelParam(float f, float f2);

    static {
        Loader.load();
    }

    public MarrHildrethHash(Pointer pointer) {
        super(pointer);
    }
}
