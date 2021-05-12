package org.bytedeco.opencv.opencv_superres;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_superres;

@Namespace("cv::superres")
@Properties(inherit = {opencv_superres.class})
public class BroxOpticalFlow extends DenseOpticalFlowExt {
    public native double getAlpha();

    public native double getGamma();

    public native int getInnerIterations();

    public native int getOuterIterations();

    public native double getScaleFactor();

    public native int getSolverIterations();

    public native void setAlpha(double d);

    public native void setGamma(double d);

    public native void setInnerIterations(int i);

    public native void setOuterIterations(int i);

    public native void setScaleFactor(double d);

    public native void setSolverIterations(int i);

    static {
        Loader.load();
    }

    public BroxOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
