package org.bytedeco.opencv.opencv_cudastereo;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_cudastereo;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudastereo.class})
public class StereoConstantSpaceBP extends StereoBeliefPropagation {
    public static native void estimateRecommendedParams(int i, int i2, @ByRef IntBuffer intBuffer, @ByRef IntBuffer intBuffer2, @ByRef IntBuffer intBuffer3, @ByRef IntBuffer intBuffer4);

    public static native void estimateRecommendedParams(int i, int i2, @ByRef IntPointer intPointer, @ByRef IntPointer intPointer2, @ByRef IntPointer intPointer3, @ByRef IntPointer intPointer4);

    public static native void estimateRecommendedParams(int i, int i2, @ByRef int[] iArr, @ByRef int[] iArr2, @ByRef int[] iArr3, @ByRef int[] iArr4);

    public native int getNrPlane();

    @Cast({"bool"})
    public native boolean getUseLocalInitDataCost();

    public native void setNrPlane(int i);

    public native void setUseLocalInitDataCost(@Cast({"bool"}) boolean z);

    static {
        Loader.load();
    }

    public StereoConstantSpaceBP(Pointer pointer) {
        super(pointer);
    }
}
