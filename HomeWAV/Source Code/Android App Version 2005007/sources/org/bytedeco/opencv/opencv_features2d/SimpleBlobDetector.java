package org.bytedeco.opencv.opencv_features2d;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_features2d;

@Namespace("cv")
@Properties(inherit = {opencv_features2d.class})
public class SimpleBlobDetector extends Feature2D {
    private native void allocate();

    private native void allocateArray(long j);

    @opencv_core.Ptr
    public static native SimpleBlobDetector create();

    @opencv_core.Ptr
    public static native SimpleBlobDetector create(@ByRef(nullValue = "cv::SimpleBlobDetector::Params()") @Const Params params);

    @opencv_core.Str
    public native BytePointer getDefaultName();

    static {
        Loader.load();
    }

    public SimpleBlobDetector() {
        super((Pointer) null);
        allocate();
    }

    public SimpleBlobDetector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SimpleBlobDetector(Pointer pointer) {
        super(pointer);
    }

    public SimpleBlobDetector position(long j) {
        return (SimpleBlobDetector) super.position(j);
    }

    public SimpleBlobDetector getPointer(long j) {
        return new SimpleBlobDetector((Pointer) this).position(this.position + j);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        @Cast({"uchar"})
        public native byte blobColor();

        public native Params blobColor(byte b);

        public native Params filterByArea(boolean z);

        @Cast({"bool"})
        public native boolean filterByArea();

        public native Params filterByCircularity(boolean z);

        @Cast({"bool"})
        public native boolean filterByCircularity();

        public native Params filterByColor(boolean z);

        @Cast({"bool"})
        public native boolean filterByColor();

        public native Params filterByConvexity(boolean z);

        @Cast({"bool"})
        public native boolean filterByConvexity();

        public native Params filterByInertia(boolean z);

        @Cast({"bool"})
        public native boolean filterByInertia();

        public native float maxArea();

        public native Params maxArea(float f);

        public native float maxCircularity();

        public native Params maxCircularity(float f);

        public native float maxConvexity();

        public native Params maxConvexity(float f);

        public native float maxInertiaRatio();

        public native Params maxInertiaRatio(float f);

        public native float maxThreshold();

        public native Params maxThreshold(float f);

        public native float minArea();

        public native Params minArea(float f);

        public native float minCircularity();

        public native Params minCircularity(float f);

        public native float minConvexity();

        public native Params minConvexity(float f);

        public native float minDistBetweenBlobs();

        public native Params minDistBetweenBlobs(float f);

        public native float minInertiaRatio();

        public native Params minInertiaRatio(float f);

        @Cast({"size_t"})
        public native long minRepeatability();

        public native Params minRepeatability(long j);

        public native float minThreshold();

        public native Params minThreshold(float f);

        public native void read(@ByRef @Const FileNode fileNode);

        public native float thresholdStep();

        public native Params thresholdStep(float f);

        public native void write(@ByRef FileStorage fileStorage);

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
