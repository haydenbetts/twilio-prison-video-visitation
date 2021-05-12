package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatBytePairVector;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
public class BlocksGainCompensator extends BlocksCompensator {
    private native void allocate();

    private native void allocate(int i, int i2);

    private native void allocate(int i, int i2, int i3);

    private native void allocateArray(long j);

    public native void apply(int i, @ByVal Point point, @ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void apply(int i, @ByVal Point point, @ByVal Mat mat, @ByVal Mat mat2);

    public native void apply(int i, @ByVal Point point, @ByVal UMat uMat, @ByVal UMat uMat2);

    public native void feed(@ByRef @Const PointVector pointVector, @ByRef @Const UMatVector uMatVector, @ByRef @Const UMatBytePairVector uMatBytePairVector);

    public native void getMatGains(@ByRef MatVector matVector);

    public native void setMatGains(@ByRef MatVector matVector);

    static {
        Loader.load();
    }

    public BlocksGainCompensator(Pointer pointer) {
        super(pointer);
    }

    public BlocksGainCompensator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public BlocksGainCompensator position(long j) {
        return (BlocksGainCompensator) super.position(j);
    }

    public BlocksGainCompensator getPointer(long j) {
        return new BlocksGainCompensator((Pointer) this).position(this.position + j);
    }

    public BlocksGainCompensator(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }

    public BlocksGainCompensator() {
        super((Pointer) null);
        allocate();
    }

    public BlocksGainCompensator(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }
}
