package org.bytedeco.opencv.opencv_stitching;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.PointVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_stitching;

@Namespace("cv::detail")
@Properties(inherit = {opencv_stitching.class})
@NoOffset
public class FeatherBlender extends Blender {
    private native void allocate();

    private native void allocate(float f);

    public native void blend(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void blend(@ByVal Mat mat, @ByVal Mat mat2);

    public native void blend(@ByVal UMat uMat, @ByVal UMat uMat2);

    @ByVal
    public native Rect createWeightMaps(@ByRef @Const UMatVector uMatVector, @ByRef @Const PointVector pointVector, @ByRef UMatVector uMatVector2);

    public native void feed(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal Point point);

    public native void feed(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Point point);

    public native void feed(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal Point point);

    public native void prepare(@ByVal Rect rect);

    public native void setSharpness(float f);

    public native float sharpness();

    static {
        Loader.load();
    }

    public FeatherBlender(Pointer pointer) {
        super(pointer);
    }

    public FeatherBlender(float f) {
        super((Pointer) null);
        allocate(f);
    }

    public FeatherBlender() {
        super((Pointer) null);
        allocate();
    }
}
