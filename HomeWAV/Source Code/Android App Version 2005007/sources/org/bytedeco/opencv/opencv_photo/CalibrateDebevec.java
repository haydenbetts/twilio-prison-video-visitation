package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class CalibrateDebevec extends CalibrateCRF {
    public native float getLambda();

    @Cast({"bool"})
    public native boolean getRandom();

    public native int getSamples();

    public native void setLambda(float f);

    public native void setRandom(@Cast({"bool"}) boolean z);

    public native void setSamples(int i);

    static {
        Loader.load();
    }

    public CalibrateDebevec(Pointer pointer) {
        super(pointer);
    }
}
