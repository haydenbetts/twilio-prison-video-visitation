package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class ClfMilBoost extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @StdVector
    public native FloatPointer classify(@ByRef @Const Mat mat);

    @StdVector
    public native FloatPointer classify(@ByRef @Const Mat mat, @Cast({"bool"}) boolean z);

    public native void init();

    public native void init(@ByRef(nullValue = "cv::ClfMilBoost::Params()") @Const Params params);

    public native float sigmoid(float f);

    public native void update(@ByRef @Const Mat mat, @ByRef @Const Mat mat2);

    static {
        Loader.load();
    }

    public ClfMilBoost(Pointer pointer) {
        super(pointer);
    }

    public ClfMilBoost(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ClfMilBoost position(long j) {
        return (ClfMilBoost) super.position(j);
    }

    public ClfMilBoost getPointer(long j) {
        return new ClfMilBoost((Pointer) this).position(this.position + j);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native float _lRate();

        public native Params _lRate(float f);

        public native int _numFeat();

        public native Params _numFeat(int i);

        public native int _numSel();

        public native Params _numSel(int i);

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

    public ClfMilBoost() {
        super((Pointer) null);
        allocate();
    }
}
