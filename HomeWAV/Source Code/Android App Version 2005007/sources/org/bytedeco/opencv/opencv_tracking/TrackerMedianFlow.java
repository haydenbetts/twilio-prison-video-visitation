package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerMedianFlow extends Tracker {
    @opencv_core.Ptr
    public static native TrackerMedianFlow create();

    @opencv_core.Ptr
    public static native TrackerMedianFlow create(@ByRef @Const Params params);

    static {
        Loader.load();
    }

    public TrackerMedianFlow(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int maxLevel();

        public native Params maxLevel(int i);

        public native double maxMedianLengthOfDisplacementDifference();

        public native Params maxMedianLengthOfDisplacementDifference(double d);

        public native int pointsInGrid();

        public native Params pointsInGrid(int i);

        public native void read(@ByRef @Const FileNode fileNode);

        @ByRef
        public native TermCriteria termCriteria();

        public native Params termCriteria(TermCriteria termCriteria);

        @ByRef
        public native Size winSize();

        public native Params winSize(Size size);

        @ByRef
        public native Size winSizeNCC();

        public native Params winSizeNCC(Size size);

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
