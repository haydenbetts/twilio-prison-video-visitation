package org.bytedeco.opencv.opencv_photo;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_photo;

@Namespace("cv")
@Properties(inherit = {opencv_photo.class})
public class AlignMTB extends AlignExposures {
    @ByVal
    public native Point calculateShift(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @ByVal
    public native Point calculateShift(@ByVal Mat mat, @ByVal Mat mat2);

    @ByVal
    public native Point calculateShift(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void computeBitmaps(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void computeBitmaps(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void computeBitmaps(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Cast({"bool"})
    public native boolean getCut();

    public native int getExcludeRange();

    public native int getMaxBits();

    public native void process(@ByVal GpuMatVector gpuMatVector, @ByRef MatVector matVector);

    public native void process(@ByVal GpuMatVector gpuMatVector, @ByRef MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void process(@ByVal GpuMatVector gpuMatVector, @ByRef MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2);

    public native void process(@ByVal GpuMatVector gpuMatVector, @ByRef MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void process(@ByVal MatVector matVector, @ByRef MatVector matVector2);

    public native void process(@ByVal MatVector matVector, @ByRef MatVector matVector2, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void process(@ByVal MatVector matVector, @ByRef MatVector matVector2, @ByVal Mat mat, @ByVal Mat mat2);

    public native void process(@ByVal MatVector matVector, @ByRef MatVector matVector2, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void process(@ByVal UMatVector uMatVector, @ByRef MatVector matVector);

    public native void process(@ByVal UMatVector uMatVector, @ByRef MatVector matVector, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void process(@ByVal UMatVector uMatVector, @ByRef MatVector matVector, @ByVal Mat mat, @ByVal Mat mat2);

    public native void process(@ByVal UMatVector uMatVector, @ByRef MatVector matVector, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void setCut(@Cast({"bool"}) boolean z);

    public native void setExcludeRange(int i);

    public native void setMaxBits(int i);

    public native void shiftMat(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @Const @ByVal Point point);

    public native void shiftMat(@ByVal Mat mat, @ByVal Mat mat2, @Const @ByVal Point point);

    public native void shiftMat(@ByVal UMat uMat, @ByVal UMat uMat2, @Const @ByVal Point point);

    static {
        Loader.load();
    }

    public AlignMTB(Pointer pointer) {
        super(pointer);
    }
}
