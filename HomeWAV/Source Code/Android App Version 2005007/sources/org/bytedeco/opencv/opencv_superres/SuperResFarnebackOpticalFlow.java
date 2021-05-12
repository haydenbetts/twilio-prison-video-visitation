package org.bytedeco.opencv.opencv_superres;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_superres;

@Properties(inherit = {opencv_superres.class})
@Name({"cv::superres::FarnebackOpticalFlow"})
public class SuperResFarnebackOpticalFlow extends DenseOpticalFlowExt {
    public native int getFlags();

    public native int getIterations();

    public native int getLevelsNumber();

    public native int getPolyN();

    public native double getPolySigma();

    public native double getPyrScale();

    public native int getWindowSize();

    public native void setFlags(int i);

    public native void setIterations(int i);

    public native void setLevelsNumber(int i);

    public native void setPolyN(int i);

    public native void setPolySigma(double d);

    public native void setPyrScale(double d);

    public native void setWindowSize(int i);

    static {
        Loader.load();
    }

    public SuperResFarnebackOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
