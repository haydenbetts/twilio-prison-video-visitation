package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class Func_AVCodecContext_Pointer_int_int extends FunctionPointer {
    private native void allocate();

    public native int call(AVCodecContext aVCodecContext, Pointer pointer, int i, int i2);

    static {
        Loader.load();
    }

    public Func_AVCodecContext_Pointer_int_int(Pointer pointer) {
        super(pointer);
    }

    protected Func_AVCodecContext_Pointer_int_int() {
        allocate();
    }
}
