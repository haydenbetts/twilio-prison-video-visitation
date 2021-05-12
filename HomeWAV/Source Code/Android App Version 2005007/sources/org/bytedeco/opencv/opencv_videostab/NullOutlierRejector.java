package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
public class NullOutlierRejector extends IOutlierRejector {
    private native void allocate();

    private native void allocateArray(long j);

    public native void process(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void process(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void process(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    static {
        Loader.load();
    }

    public NullOutlierRejector() {
        super((Pointer) null);
        allocate();
    }

    public NullOutlierRejector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public NullOutlierRejector(Pointer pointer) {
        super(pointer);
    }

    public NullOutlierRejector position(long j) {
        return (NullOutlierRejector) super.position(j);
    }

    public NullOutlierRejector getPointer(long j) {
        return new NullOutlierRejector((Pointer) this).position(this.position + j);
    }
}
