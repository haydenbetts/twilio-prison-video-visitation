package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class TrackerSamplerCSC extends TrackerSamplerAlgorithm {
    public static final int MODE_DETECT = 5;
    public static final int MODE_INIT_NEG = 2;
    public static final int MODE_INIT_POS = 1;
    public static final int MODE_TRACK_NEG = 4;
    public static final int MODE_TRACK_POS = 3;

    private native void allocate();

    private native void allocate(@ByRef(nullValue = "cv::TrackerSamplerCSC::Params()") @Const Params params);

    private native void allocateArray(long j);

    public native void setMode(int i);

    static {
        Loader.load();
    }

    public TrackerSamplerCSC(Pointer pointer) {
        super(pointer);
    }

    public TrackerSamplerCSC(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerSamplerCSC position(long j) {
        return (TrackerSamplerCSC) super.position(j);
    }

    public TrackerSamplerCSC getPointer(long j) {
        return new TrackerSamplerCSC((Pointer) this).position(this.position + j);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native float initInRad();

        public native Params initInRad(float f);

        public native int initMaxNegNum();

        public native Params initMaxNegNum(int i);

        public native float searchWinSize();

        public native Params searchWinSize(float f);

        public native float trackInPosRad();

        public native Params trackInPosRad(float f);

        public native int trackMaxNegNum();

        public native Params trackMaxNegNum(int i);

        public native int trackMaxPosNum();

        public native Params trackMaxPosNum(int i);

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

    public TrackerSamplerCSC(@ByRef(nullValue = "cv::TrackerSamplerCSC::Params()") @Const Params params) {
        super((Pointer) null);
        allocate(params);
    }

    public TrackerSamplerCSC() {
        super((Pointer) null);
        allocate();
    }
}
