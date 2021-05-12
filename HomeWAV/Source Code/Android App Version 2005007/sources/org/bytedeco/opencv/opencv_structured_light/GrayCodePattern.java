package org.bytedeco.opencv.opencv_structured_light;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.GpuMat;
import org.bytedeco.opencv.opencv_core.GpuMatVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.opencv_core.UMatVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_structured_light;

@Namespace("cv::structured_light")
@Properties(inherit = {opencv_structured_light.class})
public class GrayCodePattern extends StructuredLightPattern {
    @opencv_core.Ptr
    public static native GrayCodePattern create();

    @opencv_core.Ptr
    public static native GrayCodePattern create(int i, int i2);

    @opencv_core.Ptr
    public static native GrayCodePattern create(@ByRef(nullValue = "cv::structured_light::GrayCodePattern::Params()") @Const Params params);

    public native void getImagesForShadowMasks(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void getImagesForShadowMasks(@ByVal Mat mat, @ByVal Mat mat2);

    public native void getImagesForShadowMasks(@ByVal UMat uMat, @ByVal UMat uMat2);

    @Cast({"size_t"})
    public native long getNumberOfPatternImages();

    @Cast({"bool"})
    public native boolean getProjPixel(@ByVal GpuMatVector gpuMatVector, int i, int i2, @ByRef Point point);

    @Cast({"bool"})
    public native boolean getProjPixel(@ByVal MatVector matVector, int i, int i2, @ByRef Point point);

    @Cast({"bool"})
    public native boolean getProjPixel(@ByVal UMatVector uMatVector, int i, int i2, @ByRef Point point);

    public native void setBlackThreshold(@Cast({"size_t"}) long j);

    public native void setWhiteThreshold(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public GrayCodePattern(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int height();

        public native Params height(int i);

        public native int width();

        public native Params width(int i);

        static {
            Loader.load();
        }

        public Params(Pointer pointer) {
            super(pointer);
        }

        public Params(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Params position(long j) {
            return (Params) super.position(j);
        }

        public Params getPointer(long j) {
            return new Params((Pointer) this).position(this.position + j);
        }

        public Params() {
            super((Pointer) null);
            allocate();
        }
    }
}
