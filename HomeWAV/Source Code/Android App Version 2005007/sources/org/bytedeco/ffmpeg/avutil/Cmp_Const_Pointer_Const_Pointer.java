package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Cmp_Const_Pointer_Const_Pointer extends FunctionPointer {
    private native void allocate();

    public native int call(@Const Pointer pointer, @Const Pointer pointer2);

    static {
        Loader.load();
    }

    public Cmp_Const_Pointer_Const_Pointer(Pointer pointer) {
        super(pointer);
    }

    protected Cmp_Const_Pointer_Const_Pointer() {
        allocate();
    }
}
