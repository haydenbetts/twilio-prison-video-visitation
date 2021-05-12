package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class Seek_Pointer_long_int extends FunctionPointer {
    private native void allocate();

    @Cast({"int64_t"})
    public native long call(Pointer pointer, @Cast({"int64_t"}) long j, int i);

    static {
        Loader.load();
    }

    public Seek_Pointer_long_int(Pointer pointer) {
        super(pointer);
    }

    protected Seek_Pointer_long_int() {
        allocate();
    }
}
