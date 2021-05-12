package org.bytedeco.opencv.opencv_bgsegm;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_video.BackgroundSubtractor;
import org.bytedeco.opencv.presets.opencv_bgsegm;

@Namespace("cv::bgsegm")
@Properties(inherit = {opencv_bgsegm.class})
public class BackgroundSubtractorMOG extends BackgroundSubtractor {
    public native double getBackgroundRatio();

    public native int getHistory();

    public native int getNMixtures();

    public native double getNoiseSigma();

    public native void setBackgroundRatio(double d);

    public native void setHistory(int i);

    public native void setNMixtures(int i);

    public native void setNoiseSigma(double d);

    static {
        Loader.load();
    }

    public BackgroundSubtractorMOG(Pointer pointer) {
        super(pointer);
    }
}
