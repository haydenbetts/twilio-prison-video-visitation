package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class MultiBandBlender extends Blender {
    private native void allocate();

    private native void allocate(int i, int i2, int i3);

    private native void allocateArray(long j);

    public native void blend(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void blend(@ByVal Mat mat, @ByVal Mat mat2);

    public native void blend(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void feed(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point point);

    public native void feed(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point point);

    public native void feed(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point point);

    public native int numBands();

    public native void prepare(@ByVal Rect rect);

    public native void setNumBands(int i);

    static {
        Loader.load();
    }

    public MultiBandBlender(Pointer pointer) {
        super(pointer);
    }

    public MultiBandBlender(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MultiBandBlender position(long j) {
        return (MultiBandBlender) super.position(j);
    }

    public MultiBandBlender getPointer(long j) {
        return new MultiBandBlender((Pointer) this).position(this.position + j);
    }

    public MultiBandBlender(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }

    public MultiBandBlender() {
        super((Pointer) null);
        allocate();
    }
}
