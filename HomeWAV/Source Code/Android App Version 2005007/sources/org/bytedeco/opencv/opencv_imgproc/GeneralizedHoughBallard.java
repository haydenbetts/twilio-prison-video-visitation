package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Namespace("cv")
@Properties(inherit = {opencv_imgproc.class})
public class GeneralizedHoughBallard extends GeneralizedHough {
    public native int getLevels();

    public native int getVotesThreshold();

    public native void setLevels(int i);

    public native void setVotesThreshold(int i);

    static {
        Loader.load();
    }

    public GeneralizedHoughBallard(Pointer pointer) {
        super(pointer);
    }
}
