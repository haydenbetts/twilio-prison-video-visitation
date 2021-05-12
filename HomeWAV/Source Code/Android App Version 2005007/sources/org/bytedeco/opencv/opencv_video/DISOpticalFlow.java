package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
public class DISOpticalFlow extends DenseOpticalFlow {
    public static final int PRESET_FAST = 1;
    public static final int PRESET_MEDIUM = 2;
    public static final int PRESET_ULTRAFAST = 0;

    @opencv_core.Ptr
    public static native DISOpticalFlow create();

    @opencv_core.Ptr
    public static native DISOpticalFlow create(int i);

    public native int getFinestScale();

    public native int getGradientDescentIterations();

    public native int getPatchSize();

    public native int getPatchStride();

    @Cast({"bool"})
    public native boolean getUseMeanNormalization();

    @Cast({"bool"})
    public native boolean getUseSpatialPropagation();

    public native float getVariationalRefinementAlpha();

    public native float getVariationalRefinementDelta();

    public native float getVariationalRefinementGamma();

    public native int getVariationalRefinementIterations();

    public native void setFinestScale(int i);

    public native void setGradientDescentIterations(int i);

    public native void setPatchSize(int i);

    public native void setPatchStride(int i);

    public native void setUseMeanNormalization(@Cast({"bool"}) boolean z);

    public native void setUseSpatialPropagation(@Cast({"bool"}) boolean z);

    public native void setVariationalRefinementAlpha(float f);

    public native void setVariationalRefinementDelta(float f);

    public native void setVariationalRefinementGamma(float f);

    public native void setVariationalRefinementIterations(int i);

    static {
        Loader.load();
    }

    public DISOpticalFlow(Pointer pointer) {
        super(pointer);
    }
}
