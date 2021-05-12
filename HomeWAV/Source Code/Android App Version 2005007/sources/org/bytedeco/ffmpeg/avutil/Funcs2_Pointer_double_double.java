package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Funcs2_Pointer_double_double extends FunctionPointer {
    private native void allocate();

    public native double call(Pointer pointer, double d, double d2);

    static {
        Loader.load();
    }

    public Funcs2_Pointer_double_double(Pointer pointer) {
        super(pointer);
    }

    protected Funcs2_Pointer_double_double() {
        allocate();
    }
}
