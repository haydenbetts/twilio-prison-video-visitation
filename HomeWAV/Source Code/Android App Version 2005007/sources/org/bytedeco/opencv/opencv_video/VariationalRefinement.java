package org.bytedeco.opencv.opencv_video;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_video;

@Namespace("cv")
@Properties(inherit = {opencv_video.class})
public class VariationalRefinement extends DenseOpticalFlow {
    @opencv_core.Ptr
    public static native VariationalRefinement create();

    public native void calcUV(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    public native void calcUV(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    public native void calcUV(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    public native float getAlpha();

    public native float getDelta();

    public native int getFixedPointIterations();

    public native float getGamma();

    public native float getOmega();

    public native int getSorIterations();

    public native void setAlpha(float f);

    public native void setDelta(float f);

    public native void setFixedPointIterations(int i);

    public native void setGamma(float f);

    public native void setOmega(float f);

    public native void setSorIterations(int i);

    static {
        Loader.load();
    }

    public VariationalRefinement(Pointer pointer) {
        super(pointer);
    }
}
