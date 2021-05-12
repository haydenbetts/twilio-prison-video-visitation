package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class Pool_free_Pointer extends FunctionPointer {
    private native void allocate();

    public native void call(Pointer pointer);

    static {
        Loader.load();
    }

    public Pool_free_Pointer(Pointer pointer) {
        super(pointer);
    }

    protected Pool_free_Pointer() {
        allocate();
    }
}
