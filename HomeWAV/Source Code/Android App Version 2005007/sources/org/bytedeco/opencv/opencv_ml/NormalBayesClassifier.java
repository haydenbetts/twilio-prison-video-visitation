package org.bytedeco.opencv.opencv_ml;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_ml;

@Namespace("cv::ml")
@Properties(inherit = {opencv_ml.class})
public class NormalBayesClassifier extends StatModel {
    @opencv_core.Ptr
    public static native NormalBayesClassifier create();

    @opencv_core.Ptr
    public static native NormalBayesClassifier load(@opencv_core.Str String str);

    @opencv_core.Ptr
    public static native NormalBayesClassifier load(@opencv_core.Str String str, @opencv_core.Str String str2);

    @opencv_core.Ptr
    public static native NormalBayesClassifier load(@opencv_core.Str BytePointer bytePointer);

    @opencv_core.Ptr
    public static native NormalBayesClassifier load(@opencv_core.Str BytePointer bytePointer, @opencv_core.Str BytePointer bytePointer2);

    public native float predictProb(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native float predictProb(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, int i);

    public native float predictProb(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native float predictProb(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, int i);

    public native float predictProb(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native float predictProb(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, int i);

    static {
        Loader.load();
    }

    public NormalBayesClassifier(Pointer pointer) {
        super(pointer);
    }
}
