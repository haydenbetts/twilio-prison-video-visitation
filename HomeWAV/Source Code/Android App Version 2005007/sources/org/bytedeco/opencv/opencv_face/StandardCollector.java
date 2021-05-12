package org.bytedeco.opencv.opencv_face;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.IntDoubleMap;
import org.bytedeco.opencv.opencv_core.IntDoublePairVector;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_face;

@Properties(inherit = {opencv_face.class})
@Namespace("cv::face")
@NoOffset
public class StandardCollector extends PredictCollector {
    private native void allocate();

    private native void allocate(double d);

    @opencv_core.Ptr
    public static native StandardCollector create();

    @opencv_core.Ptr
    public static native StandardCollector create(double d);

    @Cast({"bool"})
    public native boolean collect(int i, double d);

    public native double getMinDist();

    public native int getMinLabel();

    @ByVal
    public native IntDoublePairVector getResults();

    @ByVal
    public native IntDoublePairVector getResults(@Cast({"bool"}) boolean z);

    @ByVal
    public native IntDoubleMap getResultsMap();

    public native void init(@Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public StandardCollector(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class PredictResult extends Pointer {
        private native void allocate();

        private native void allocate(int i, double d);

        private native void allocateArray(long j);

        public native double distance();

        public native PredictResult distance(double d);

        public native int label();

        public native PredictResult label(int i);

        static {
            Loader.load();
        }

        public PredictResult(Pointer pointer) {
            super(pointer);
        }

        public PredictResult(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public PredictResult position(long j) {
            return (PredictResult) super.position(j);
        }

        public PredictResult getPointer(long j) {
            return new PredictResult((Pointer) this).position(this.position + j);
        }

        public PredictResult(int i, double d) {
            super((Pointer) null);
            allocate(i, d);
        }

        public PredictResult() {
            super((Pointer) null);
            allocate();
        }
    }

    public StandardCollector(double d) {
        super((Pointer) null);
        allocate(d);
    }

    public StandardCollector() {
        super((Pointer) null);
        allocate();
    }
}
