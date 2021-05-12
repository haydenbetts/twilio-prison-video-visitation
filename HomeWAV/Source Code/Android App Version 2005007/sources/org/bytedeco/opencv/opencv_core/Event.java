package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Namespace("cv::cuda")
@NoOffset
public class Event extends Pointer {
    public static final int BLOCKING_SYNC = 1;
    public static final int DEFAULT = 0;
    public static final int DISABLE_TIMING = 2;
    public static final int INTERPROCESS = 4;

    private native void allocate();

    private native void allocate(@Cast({"cv::cuda::Event::CreateFlags"}) int i);

    public static native float elapsedTime(@ByRef @Const Event event, @ByRef @Const Event event2);

    @Cast({"bool"})
    public native boolean queryIfComplete();

    public native void record();

    public native void record(@ByRef(nullValue = "cv::cuda::Stream::Null()") Stream stream);

    public native void waitForCompletion();

    static {
        Loader.load();
    }

    public Event(Pointer pointer) {
        super(pointer);
    }

    public Event(@Cast({"cv::cuda::Event::CreateFlags"}) int i) {
        super((Pointer) null);
        allocate(i);
    }

    public Event() {
        super((Pointer) null);
        allocate();
    }

    @Opaque
    public static class Impl extends Pointer {
        public Impl() {
            super((Pointer) null);
        }

        public Impl(Pointer pointer) {
            super(pointer);
        }
    }
}
