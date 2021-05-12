package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class LogCallback extends FunctionPointer {
    private native void allocate();

    public native void call(int i, @Cast({"const char*"}) BytePointer bytePointer);

    static {
        Loader.load();
    }

    public LogCallback(Pointer pointer) {
        super(pointer);
    }

    protected LogCallback() {
        allocate();
    }
}
