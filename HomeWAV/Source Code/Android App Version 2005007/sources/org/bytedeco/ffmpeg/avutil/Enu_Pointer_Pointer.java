package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Enu_Pointer_Pointer extends FunctionPointer {
    private native void allocate();

    public native int call(Pointer pointer, Pointer pointer2);

    static {
        Loader.load();
    }

    public Enu_Pointer_Pointer(Pointer pointer) {
        super(pointer);
    }

    protected Enu_Pointer_Pointer() {
        allocate();
    }
}
