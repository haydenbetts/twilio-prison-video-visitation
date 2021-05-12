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
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class TrackerStateEstimatorMILBoosting extends TrackerStateEstimator {
    private native void allocate();

    private native void allocate(int i);

    public native void setCurrentConfidenceMap(@ByRef ConfidenceMap confidenceMap);

    static {
        Loader.load();
    }

    public TrackerStateEstimatorMILBoosting(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class TrackerMILTargetState extends TrackerTargetState {
        private native void allocate(@ByRef @Const Point2f point2f, int i, int i2, @Cast({"bool"}) boolean z, @ByRef @Const Mat mat);

        @ByVal
        public native Mat getFeatures();

        @Cast({"bool"})
        public native boolean isTargetFg();

        public native void setFeatures(@ByRef @Const Mat mat);

        public native void setTargetFg(@Cast({"bool"}) boolean z);

        static {
            Loader.load();
        }

        public TrackerMILTargetState(Pointer pointer) {
            super(pointer);
        }

        public TrackerMILTargetState(@ByRef @Const Point2f point2f, int i, int i2, @Cast({"bool"}) boolean z, @ByRef @Const Mat mat) {
            super((Pointer) null);
            allocate(point2f, i, i2, z, mat);
        }
    }

    public TrackerStateEstimatorMILBoosting(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public TrackerStateEstimatorMILBoosting() {
        super((Pointer) null);
        allocate();
    }
}
