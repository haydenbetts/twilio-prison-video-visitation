package org.bytedeco.opencv.opencv_saliency;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_saliency;

@Namespace("cv::saliency")
@Properties(inherit = {opencv_saliency.class})
@NoOffset
public class StaticSaliencySpectralResidual extends StaticSaliency {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native StaticSaliencySpectralResidual create();

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal Mat mat, @ByVal Mat mat2);

    @Cast({"bool"})
    public native boolean computeSaliency(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native int getImageHeight();

    public native int getImageWidth();

    public native void read(@ByRef @Const FileNode fileNode);

    public native void setImageHeight(int i);

    public native void setImageWidth(int i);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public StaticSaliencySpectralResidual(Pointer pointer) {
        super(pointer);
    }

    public StaticSaliencySpectralResidual(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public StaticSaliencySpectralResidual position(long j) {
        return (StaticSaliencySpectralResidual) super.position(j);
    }

    public StaticSaliencySpectralResidual getPointer(long j) {
        return new StaticSaliencySpectralResidual((Pointer) this).position(this.position + j);
    }

    public StaticSaliencySpectralResidual() {
        super((Pointer) null);
        allocate();
    }
}
