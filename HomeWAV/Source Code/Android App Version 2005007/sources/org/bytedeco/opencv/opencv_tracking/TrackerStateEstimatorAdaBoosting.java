package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_tracking;

@Properties(inherit = {opencv_tracking.class})
@Namespace("cv")
@NoOffset
public class TrackerStateEstimatorAdaBoosting extends TrackerStateEstimator {
    private native void allocate(int i, int i2, int i3, @ByVal Size size, @ByRef @Const Rect rect);

    @StdVector
    public native IntPointer computeReplacedClassifier();

    @StdVector
    public native IntPointer computeSelectedWeakClassifier();

    @StdVector
    public native IntPointer computeSwappedClassifier();

    @ByVal
    public native Rect getSampleROI();

    public native void setCurrentConfidenceMap(@ByRef ConfidenceMap confidenceMap);

    public native void setSampleROI(@ByRef @Const Rect rect);

    static {
        Loader.load();
    }

    public TrackerStateEstimatorAdaBoosting(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class TrackerAdaBoostingTargetState extends TrackerTargetState {
        private native void allocate(@ByRef @Const Point2f point2f, int i, int i2, @Cast({"bool"}) boolean z, @ByRef @Const Mat mat);

        @ByVal
        public native Mat getTargetResponses();

        @Cast({"bool"})
        public native boolean isTargetFg();

        public native void setTargetFg(@Cast({"bool"}) boolean z);

        public native void setTargetResponses(@ByRef @Const Mat mat);

        static {
            Loader.load();
        }

        public TrackerAdaBoostingTargetState(Pointer pointer) {
            super(pointer);
        }

        public TrackerAdaBoostingTargetState(@ByRef @Const Point2f point2f, int i, int i2, @Cast({"bool"}) boolean z, @ByRef @Const Mat mat) {
            super((Pointer) null);
            allocate(point2f, i, i2, z, mat);
        }
    }

    public TrackerStateEstimatorAdaBoosting(int i, int i2, int i3, @ByVal Size size, @ByRef @Const Rect rect) {
        super((Pointer) null);
        allocate(i, i2, i3, size, rect);
    }
}
