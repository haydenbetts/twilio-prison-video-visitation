package org.bytedeco.opencv.opencv_tracking;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.presets.opencv_core;
import org.bytedeco.opencv.presets.opencv_tracking;

@Namespace("cv")
@Properties(inherit = {opencv_tracking.class})
public class TrackerGOTURN extends Tracker {
    @opencv_core.Ptr
    public static native TrackerGOTURN create();

    @opencv_core.Ptr
    public static native TrackerGOTURN create(@ByRef @Const Params params);

    static {
        Loader.load();
    }

    public TrackerGOTURN(Pointer pointer) {
        super(pointer);
    }

    public static class Params extends Pointer {
        private native void allocate();

        private native void allocateArray(long j);

        public native void read(@ByRef @Const FileNode fileNode);

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
