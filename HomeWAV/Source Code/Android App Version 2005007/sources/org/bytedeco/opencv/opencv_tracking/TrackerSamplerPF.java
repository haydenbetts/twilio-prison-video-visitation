package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class TrackerSamplerPF extends TrackerSamplerAlgorithm {
    private native void allocate(@ByRef @Const Mat mat);

    private native void allocate(@ByRef @Const Mat mat, @ByRef(nullValue = "cv::TrackerSamplerPF::Params()") @Const Params params);

    static {
        Loader.load();
    }

    public TrackerSamplerPF(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native double alpha();

        public native Params alpha(double d);

        public native int iterationNum();

        public native Params iterationNum(int i);

        public native int particlesNum();

        public native Params particlesNum(int i);

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

    public TrackerSamplerPF(@ByRef @Const Mat mat, @ByRef(nullValue = "cv::TrackerSamplerPF::Params()") @Const Params params) {
        super((Pointer) null);
        allocate(mat, params);
    }

    public TrackerSamplerPF(@ByRef @Const Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }
}
