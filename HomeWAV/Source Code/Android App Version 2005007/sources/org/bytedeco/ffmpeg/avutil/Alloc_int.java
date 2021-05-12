package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Alloc_int extends FunctionPointer {
    private native void allocate();

    public native AVBufferRef call(int i);

    static {
        Loader.load();
    }

    public Alloc_int(Pointer pointer) {
        super(pointer);
    }

    protected Alloc_int() {
        allocate();
    }
}
