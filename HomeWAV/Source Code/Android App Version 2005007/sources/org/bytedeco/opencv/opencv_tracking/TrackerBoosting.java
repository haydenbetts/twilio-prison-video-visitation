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
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerBoosting extends Tracker {
    @opencv_core.Ptr
    public static native TrackerBoosting create();

    @opencv_core.Ptr
    public static native TrackerBoosting create(@ByRef @Const Params params);

    static {
        Loader.load();
    }

    public TrackerBoosting(Pointer pointer) {
        super(pointer);
    }

    @NoOffset
    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native int featureSetNumFeatures();

        public native Params featureSetNumFeatures(int i);

        public native int iterationInit();

        public native Params iterationInit(int i);

        public native int numClassifiers();

        public native Params numClassifiers(int i);

        public native void read(@ByRef @Const FileNode fileNode);

        public native float samplerOverlap();

        public native Params samplerOverlap(float f);

        public native float samplerSearchFactor();

        public native Params samplerSearchFactor(float f);

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
