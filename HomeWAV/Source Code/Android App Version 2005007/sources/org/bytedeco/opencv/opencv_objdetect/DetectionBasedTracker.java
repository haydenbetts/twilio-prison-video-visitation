package org.bytedeco.opencv.opencv_objdetect;

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
import org.bytedeco.opencv.opencv_core.IntIntPair;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.RectVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_objdetect;

@Properties(inherit = {opencv_objdetect.class})
@Namespace("cv")
@NoOffset
public class DetectionBasedTracker extends Pointer {
    public static final int DETECTED = 1;
    public static final int DETECTED_NOT_SHOWN_YET = 0;
    public static final int DETECTED_TEMPORARY_LOST = 2;
    public static final int WRONG_OBJECT = 3;

    private native void allocate(@opencv_core.Ptr IDetector iDetector, @opencv_core.Ptr IDetector iDetector2, @ByRef @Const Parameters parameters);

    public native int addObject(@ByRef @Const Rect rect);

    public native void getObjects(@Cast({"cv::DetectionBasedTracker::Object*"}) @StdVector IntIntPair intIntPair);

    public native void getObjects(@ByRef RectVector rectVector);

    public native void getObjects(@StdVector ExtObject extObject);

    @ByRef
    @Const
    public native Parameters getParameters();

    public native void process(@ByRef @Const Mat mat);

    public native void resetTracking();

    @Cast({"bool"})
    public native boolean run();

    @Cast({"bool"})
    public native boolean setParameters(@ByRef @Const Parameters parameters);

    public native void stop();

    static {
        Loader.load();
    }

    public DetectionBasedTracker(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Parameters extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int maxTrackLifetime();

        public native Parameters maxTrackLifetime(int i);

        public native int minDetectionPeriod();

        public native Parameters minDetectionPeriod(int i);

        static {
            Loader.load();
        }

        public Parameters(Pointer pointer) {
            super(pointer);
        }

        public Parameters(long j) {
            super((Pointer) null);
            allocateArray(j);
        }

        public Parameters position(long j) {
            return (Parameters) super.position(j);
        }

        public Parameters getPointer(long j) {
            return new Parameters((Pointer) this).position(this.position + j);
        }

        public Parameters() {
            super((Pointer) null);
            allocate();
        }
    }

    @NoOffset
    public static class IDetector extends Pointer {
        public native void detect(@ByRef @Const Mat mat, @ByRef RectVector rectVector);

        @ByVal
        public native Size getMaxObjectSize();

        public native int getMinNeighbours();

        @ByVal
        public native Size getMinObjectSize();

        public native float getScaleFactor();

        public native void setMaxObjectSize(@ByRef @Const Size size);

        public native void setMinNeighbours(int i);

        public native void setMinObjectSize(@ByRef @Const Size size);

        public native void setScaleFactor(float f);

        static {
            Loader.load();
        }

        public IDetector(Pointer pointer) {
            super(pointer);
        }
    }

    public DetectionBasedTracker(@opencv_core.Ptr IDetector iDetector, @opencv_core.Ptr IDetector iDetector2, @ByRef @Const Parameters parameters) {
        super((Pointer) null);
        allocate(iDetector, iDetector2, parameters);
    }

    @NoOffset
    public static class ExtObject extends Pointer {
        private native void allocate(int i, @ByVal Rect rect, @Cast({"cv::DetectionBasedTracker::ObjectStatus"}) int i2);

        public native int id();

        public native ExtObject id(int i);

        @ByRef
        public native Rect location();

        public native ExtObject location(Rect rect);

        @Cast({"cv::DetectionBasedTracker::ObjectStatus"})
        public native int status();

        public native ExtObject status(int i);

        static {
            Loader.load();
        }

        public ExtObject(Pointer pointer) {
            super(pointer);
        }

        public ExtObject(int i, @ByVal Rect rect, @Cast({"cv::DetectionBasedTracker::ObjectStatus"}) int i2) {
            super((Pointer) null);
            allocate(i, rect, i2);
        }
    }
}
