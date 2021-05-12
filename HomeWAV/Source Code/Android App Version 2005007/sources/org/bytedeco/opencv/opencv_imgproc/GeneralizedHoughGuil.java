package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Namespace("cv")
@Properties(inherit = {opencv_imgproc.class})
public class GeneralizedHoughGuil extends GeneralizedHough {
    public native double getAngleEpsilon();

    public native double getAngleStep();

    public native int getAngleThresh();

    public native int getLevels();

    public native double getMaxAngle();

    public native double getMaxScale();

    public native double getMinAngle();

    public native double getMinScale();

    public native int getPosThresh();

    public native double getScaleStep();

    public native int getScaleThresh();

    public native double getXi();

    public native void setAngleEpsilon(double d);

    public native void setAngleStep(double d);

    public native void setAngleThresh(int i);

    public native void setLevels(int i);

    public native void setMaxAngle(double d);

    public native void setMaxScale(double d);

    public native void setMinAngle(double d);

    public native void setMinScale(double d);

    public native void setPosThresh(int i);

    public native void setScaleStep(double d);

    public native void setScaleThresh(int i);

    public native void setXi(double d);

    static {
        Loader.load();
    }

    public GeneralizedHoughGuil(Pointer pointer) {
        super(pointer);
    }
}
