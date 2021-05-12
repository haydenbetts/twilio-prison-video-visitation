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
public class RICInterpolator extends SparseMatchInterpolator {
    public native float getAlpha();

    public native float getFGSLambda();

    public native float getFGSSigma();

    public native int getK();

    public native float getMaxFlow();

    public native int getModelIter();

    @Cast({"bool"})
    public native boolean getRefineModels();

    public native int getSuperpixelMode();

    public native int getSuperpixelNNCnt();

    public native float getSuperpixelRuler();

    public native int getSuperpixelSize();

    @Cast({"bool"})
    public native boolean getUseGlobalSmootherFilter();

    @Cast({"bool"})
    public native boolean getUseVariationalRefinement();

    public native void setAlpha();

    public native void setAlpha(float f);

    public native void setCostMap(@ByRef @Const Mat mat);

    public native void setFGSLambda();

    public native void setFGSLambda(float f);

    public native void setFGSSigma();

    public native void setFGSSigma(float f);

    public native void setK();

    public native void setK(int i);

    public native void setMaxFlow();

    public native void setMaxFlow(float f);

    public native void setModelIter();

    public native void setModelIter(int i);

    public native void setRefineModels();

    public native void setRefineModels(@Cast({"bool"}) boolean z);

    public native void setSuperpixelMode();

    public native void setSuperpixelMode(int i);

    public native void setSuperpixelNNCnt();

    public native void setSuperpixelNNCnt(int i);

    public native void setSuperpixelRuler();

    public native void setSuperpixelRuler(float f);

    public native void setSuperpixelSize();

    public native void setSuperpixelSize(int i);

    public native void setUseGlobalSmootherFilter();

    public native void setUseGlobalSmootherFilter(@Cast({"bool"}) boolean z);

    public native void setUseVariationalRefinement();

    public native void setUseVariationalRefinement(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public RICInterpolator(Pointer pointer) {
        super(pointer);
    }
}
