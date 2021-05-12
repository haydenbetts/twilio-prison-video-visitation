package org.bytedeco.opencv.opencv_structured_light;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Algorithm;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.MatVectorVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_structured_light;

@Namespace("cv::structured_light")
@Properties(inherit = {opencv_structured_light.class})
public class StructuredLightPattern extends Algorithm {
    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal GpuMat gpuMat);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal GpuMat gpuMat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal Mat mat);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal Mat mat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal Mat mat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal Mat mat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal UMat uMat);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal UMat uMat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") GpuMatVector gpuMatVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal UMat uMat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") MatVector matVector2, int i);

    @Cast({"bool"})
    public native boolean decode(@ByRef @Const MatVectorVector matVectorVector, @ByVal UMat uMat, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector, @ByVal(nullValue = "cv::InputArrayOfArrays(cv::noArray())") UMatVector uMatVector2, int i);

    @Cast({"bool"})
    public native boolean generate(@ByVal GpuMatVector gpuMatVector);

    @Cast({"bool"})
    public native boolean generate(@ByVal MatVector matVector);

    @Cast({"bool"})
    public native boolean generate(@ByVal UMatVector uMatVector);

    static {
        Loader.load();
    }

    public StructuredLightPattern(Pointer pointer) {
        super(pointer);
    }
}
