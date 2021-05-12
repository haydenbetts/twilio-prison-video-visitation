package org.bytedeco.opencv.opencv_cudastereo;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_calib3d.StereoMatcher;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Stream;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_cudastereo;

@Namespace("cv::cuda")
@Properties(inherit = {opencv_cudastereo.class})
public class StereoBeliefPropagation extends StereoMatcher {
    public static native void estimateRecommendedParams(int i, int i2, @ByRef IntBuffer intBuffer, @ByRef IntBuffer intBuffer2, @ByRef IntBuffer intBuffer3);

    public static native void estimateRecommendedParams(int i, int i2, @ByRef IntPointer intPointer, @ByRef IntPointer intPointer2, @ByRef IntPointer intPointer3);

    public static native void estimateRecommendedParams(int i, int i2, @ByRef int[] iArr, @ByRef int[] iArr2, @ByRef int[] iArr3);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByRef Stream stream);

    public native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByRef Stream stream);

    public native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByRef Stream stream);

    public native double getDataWeight();

    public native double getDiscSingleJump();

    public native double getMaxDataTerm();

    public native double getMaxDiscTerm();

    public native int getMsgType();

    public native int getNumIters();

    public native int getNumLevels();

    public native void setDataWeight(double d);

    public native void setDiscSingleJump(double d);

    public native void setMaxDataTerm(double d);

    public native void setMaxDiscTerm(double d);

    public native void setMsgType(int i);

    public native void setNumIters(int i);

    public native void setNumLevels(int i);

    static {
        Loader.load();
    }

    public StereoBeliefPropagation(Pointer pointer) {
        super(pointer);
    }
}
