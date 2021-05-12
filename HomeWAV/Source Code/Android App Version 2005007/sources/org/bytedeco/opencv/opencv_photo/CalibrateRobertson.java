package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class CalibrateRobertson extends CalibrateCRF {
    public native int getMaxIter();

    @ByVal
    public native Mat getRadiance();

    public native float getThreshold();

    public native void setMaxIter(int i);

    public native void setThreshold(float f);

    static {
        Loader.load();
    }

    public CalibrateRobertson(Pointer pointer) {
        super(pointer);
    }
}
