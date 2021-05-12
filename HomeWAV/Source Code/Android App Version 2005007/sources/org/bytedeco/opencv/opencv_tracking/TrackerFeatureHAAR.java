package org.bytedeco.opencv.opencv_tracking;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
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
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_tracking.CvHaarEvaluator;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class TrackerFeatureHAAR extends TrackerFeature {
    private native void allocate();

    private native void allocate(@ByRef(nullValue = "cv::TrackerFeatureHAAR::Params()") @Const Params params);

    private native void allocateArray(long j);

    @Cast({"bool"})
    public native boolean extractSelected(@StdVector IntBuffer intBuffer, @ByRef @Const MatVector matVector, @ByRef Mat mat);

    @Cast({"bool"})
    public native boolean extractSelected(@StdVector IntPointer intPointer, @ByRef @Const MatVector matVector, @ByRef Mat mat);

    @Cast({"bool"})
    public native boolean extractSelected(@StdVector int[] iArr, @ByRef @Const MatVector matVector, @ByRef Mat mat);

    @ByRef
    public native CvHaarEvaluator.FeatureHaar getFeatureAt(int i);

    public native void selection(@ByRef Mat mat, int i);

    @Cast({"bool"})
    public native boolean swapFeature(int i, int i2);

    @Cast({"bool"})
    public native boolean swapFeature(int i, @ByRef CvHaarEvaluator.FeatureHaar featureHaar);

    static {
        Loader.load();
    }

    public TrackerFeatureHAAR(Pointer pointer) {
        super(pointer);
    }

    public TrackerFeatureHAAR(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public TrackerFeatureHAAR position(long j) {
        return (TrackerFeatureHAAR) super.position(j);
    }

    public TrackerFeatureHAAR getPointer(long j) {
        return new TrackerFeatureHAAR((Pointer) this).position(this.position + j);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native Params isIntegral(boolean z);

        @Cast({"bool"})
        public native boolean isIntegral();

        public native int numFeatures();

        public native Params numFeatures(int i);

        @ByRef
        public native Size rectSize();

        public native Params rectSize(Size size);

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

    public TrackerFeatureHAAR(@ByRef(nullValue = "cv::TrackerFeatureHAAR::Params()") @Const Params params) {
        super((Pointer) null);
        allocate(params);
    }

    public TrackerFeatureHAAR() {
        super((Pointer) null);
        allocate();
    }
}
