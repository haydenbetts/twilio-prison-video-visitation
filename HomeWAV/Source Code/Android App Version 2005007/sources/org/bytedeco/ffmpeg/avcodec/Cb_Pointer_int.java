package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class Cb_Pointer_int extends FunctionPointer {
    private native void allocate();

    public native int call(@ByPtrPtr @Cast({"void**"}) Pointer pointer, @Cast({"AVLockOp"}) int i);

    static {
        Loader.load();
    }

    public Cb_Pointer_int(Pointer pointer) {
        super(pointer);
    }

    protected Cb_Pointer_int() {
        allocate();
    }
}
