package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Free_Pointer_byte__ extends FunctionPointer {
    private native void allocate();

    public native void call(Pointer pointer, @Cast({"uint8_t*"}) byte[] bArr);

    static {
        Loader.load();
    }

    public Free_Pointer_byte__(Pointer pointer) {
        super(pointer);
    }

    protected Free_Pointer_byte__() {
        allocate();
    }
}
