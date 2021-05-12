package org.bytedeco.opencv.opencv_phase_unwrapping;

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
import org.bytedeco.opencv.opencv_core.UMat;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_phase_unwrapping;

@Namespace("cv::phase_unwrapping")
@Properties(inherit = {opencv_phase_unwrapping.class})
public class HistogramPhaseUnwrapping extends PhaseUnwrapping {
    @opencv_core.Ptr
    public static native HistogramPhaseUnwrapping create();

    @opencv_core.Ptr
    public static native HistogramPhaseUnwrapping create(@ByRef(nullValue = "cv::phase_unwrapping::HistogramPhaseUnwrapping::Params()") @Const Params params);

    public native void getInverseReliabilityMap(@ByVal GpuMat gpuMat);

    public native void getInverseReliabilityMap(@ByVal Mat mat);

    public native void getInverseReliabilityMap(@ByVal UMat uMat);

    static {
        Loader.load();
    }

    public HistogramPhaseUnwrapping(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int height();

        public native Params height(int i);

        public native float histThresh();

        public native Params histThresh(float f);

        public native int nbrOfLargeBins();

        public native Params nbrOfLargeBins(int i);

        public native int nbrOfSmallBins();

        public native Params nbrOfSmallBins(int i);

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
