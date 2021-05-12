package org.bytedeco.opencv.opencv_videostab;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_videostab;

@Namespace("cv::videostab")
@Properties(inherit = {opencv_videostab.class})
@NoOffset
public class TranslationBasedLocalOutlierRejector extends IOutlierRejector {
    private native void allocate();

    private native void allocateArray(long j);

    @ByVal
    public native Size cellSize();

    public native void process(@ByVal Size size, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    public native void process(@ByVal Size size, @ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    public native void process(@ByVal Size size, @ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @ByVal
    public native RansacParams ransacParams();

    public native void setCellSize(@ByVal Size size);

    public native void setRansacParams(@ByVal RansacParams ransacParams);

    static {
        Loader.load();
    }

    public TranslationBasedLocalOutlierRejector(Pointer pointer) {
        super(pointer);
    }

    public TranslationBasedLocalOutlierRejector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TranslationBasedLocalOutlierRejector position(long j) {
        return (TranslationBasedLocalOutlierRejector) super.position(j);
    }

    public TranslationBasedLocalOutlierRejector getPointer(long j) {
        return new TranslationBasedLocalOutlierRejector((Pointer) this).position(this.position + j);
    }

    public TranslationBasedLocalOutlierRejector() {
        super((Pointer) null);
        allocate();
    }
}
