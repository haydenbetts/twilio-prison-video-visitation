package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Int_func_Pointer_Pointer_int extends FunctionPointer {
    private native void allocate();

    public native int call(Pointer pointer, Pointer pointer2, int i);

    static {
        Loader.load();
    }

    public Int_func_Pointer_Pointer_int(Pointer pointer) {
        super(pointer);
    }

    protected Int_func_Pointer_Pointer_int() {
        allocate();
    }
}
