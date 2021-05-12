package org.bytedeco.opencv.opencv_superres;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_superres;

@Properties(inherit = {opencv_superres.class})
@Name({"cv::superres::DualTVL1OpticalFlow"})
public class SuperResDualTVL1OpticalFlow extends DenseOpticalFlowExt {
    public native double getEpsilon();

    public native int getIterations();

    public native double getLambda();

    public native int getScalesNumber();

    public native double getTau();

    public native double getTheta();

    @Cast({"bool"})
    public native boolean getUseInitialFlow();

    public native int getWarpingsNumber();

    public native void setEpsilon(double d);

    public native void setIterations(int i);

    public native void setLambda(double d);

    public native void setScalesNumber(int i);

    public native void setTau(double d);

    public native void setTheta(double d);

    public native void setUseInitialFlow(@Cast({"bool"}) boolean z);

    public native void setWarpingsNumber(int i);

    static {
        Loader.load();
    }

    public SuperResDualTVL1OpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
