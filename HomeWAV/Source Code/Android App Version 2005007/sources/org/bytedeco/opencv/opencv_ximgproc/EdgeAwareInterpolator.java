package org.bytedeco.opencv.opencv_ximgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_ximgproc;

@Namespace("cv::ximgproc")
@Properties(inherit = {opencv_ximgproc.class})
public class EdgeAwareInterpolator extends SparseMatchInterpolator {
    public native float getFGSLambda();

    public native float getFGSSigma();

    public native int getK();

    public native float getLambda();

    public native float getSigma();

    @Cast({"bool"})
    public native boolean getUsePostProcessing();

    public native void setCostMap(@ByRef @Const Mat mat);

    public native void setFGSLambda(float f);

    public native void setFGSSigma(float f);

    public native void setK(int i);

    public native void setLambda(float f);

    public native void setSigma(float f);

    public native void setUsePostProcessing(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public EdgeAwareInterpolator(Pointer pointer) {
        super(pointer);
    }
}
