package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class av_pixelutils_sad_fn extends FunctionPointer {
    private native void allocate();

    public native int call(@Cast({"const uint8_t*"}) BytePointer bytePointer, @Cast({"ptrdiff_t"}) long j, @Cast({"const uint8_t*"}) BytePointer bytePointer2, @Cast({"ptrdiff_t"}) long j2);

    static {
        Loader.load();
    }

    public av_pixelutils_sad_fn(Pointer pointer) {
        super(pointer);
    }

    protected av_pixelutils_sad_fn() {
        allocate();
    }
}
