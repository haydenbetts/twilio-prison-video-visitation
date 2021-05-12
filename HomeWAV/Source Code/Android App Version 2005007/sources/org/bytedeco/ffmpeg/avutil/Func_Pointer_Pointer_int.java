package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Func_Pointer_Pointer_int extends FunctionPointer {
    private native void allocate();

    public native void call(Pointer pointer, Pointer pointer2, int i);

    static {
        Loader.load();
    }

    public Func_Pointer_Pointer_int(Pointer pointer) {
        super(pointer);
    }

    protected Func_Pointer_Pointer_int() {
        allocate();
    }
}
