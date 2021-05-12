package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class TrackerSamplerCS extends TrackerSamplerAlgorithm {
    public static final int MODE_CLASSIFY = 3;
    public static final int MODE_NEGATIVE = 2;
    public static final int MODE_POSITIVE = 1;

    private native void allocate();

    private native void allocate(@ByRef(nullValue = "cv::TrackerSamplerCS::Params()") @Const Params params);

    private native void allocateArray(long j);

    @ByVal
    public native Rect getROI();

    @Cast({"bool"})
    public native boolean samplingImpl(@ByRef @Const Mat mat, @ByVal Rect rect, @ByRef MatVector matVector);

    public native void setMode(int i);

    static {
        Loader.load();
    }

    public TrackerSamplerCS(Pointer pointer) {
        super(pointer);
    }

    public TrackerSamplerCS(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerSamplerCS position(long j) {
        return (TrackerSamplerCS) super.position(j);
    }

    public TrackerSamplerCS getPointer(long j) {
        return new TrackerSamplerCS((Pointer) this).position(this.position + j);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native float overlap();

        public native Params overlap(float f);

        public native float searchFactor();

        public native Params searchFactor(float f);

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

    public TrackerSamplerCS(@ByRef(nullValue = "cv::TrackerSamplerCS::Params()") @Const Params params) {
        super((Pointer) null);
        allocate(params);
    }

    public TrackerSamplerCS() {
        super((Pointer) null);
        allocate();
    }
}
