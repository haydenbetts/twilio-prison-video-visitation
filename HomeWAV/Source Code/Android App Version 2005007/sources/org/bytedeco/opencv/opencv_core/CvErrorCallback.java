package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Convention;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Convention("CV_CDECL")
public class CvErrorCallback extends FunctionPointer {
    private native void allocate();

    public native int call(int i, @Cast({"const char*"}) BytePointer bytePointer, @Cast({"const char*"}) BytePointer bytePointer2, @Cast({"const char*"}) BytePointer bytePointer3, int i2, Pointer pointer);

    static {
        Loader.load();
    }

    public CvErrorCallback(Pointer pointer) {
        super(pointer);
    }

    protected CvErrorCallback() {
        allocate();
    }
}
