package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ocl")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class Timer extends Pointer {
    private native void allocate(@ByRef @Const Queue queue);

    @Cast({"uint64"})
    public native int durationNS();

    public native void start();

    public native void stop();

    static {
        Loader.load();
    }

    public Timer(Pointer pointer) {
        super(pointer);
    }

    public Timer(@ByRef @Const Queue queue) {
        super((Pointer) null);
        allocate(queue);
    }
}
