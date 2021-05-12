package org.bytedeco.opencv.opencv_superres;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_superres;

@Namespace("cv::superres")
@Properties(inherit = {opencv_superres.class})
public class PyrLKOpticalFlow extends DenseOpticalFlowExt {
    public native int getIterations();

    public native int getMaxLevel();

    public native int getWindowSize();

    public native void setIterations(int i);

    public native void setMaxLevel(int i);

    public native void setWindowSize(int i);

    static {
        Loader.load();
    }

    public PyrLKOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
