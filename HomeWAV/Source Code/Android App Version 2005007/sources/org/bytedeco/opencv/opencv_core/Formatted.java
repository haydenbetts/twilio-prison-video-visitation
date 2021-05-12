package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class Formatted extends Pointer {
    @Cast({"const char*"})
    public native BytePointer next();

    public native void reset();

    static {
        Loader.load();
    }

    public Formatted(Pointer pointer) {
        super(pointer);
    }
}
