package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class av_tx_fn extends FunctionPointer {
    private native void allocate();

    public native void call(AVTXContext aVTXContext, Pointer pointer, Pointer pointer2, @Cast({"ptrdiff_t"}) long j);

    static {
        Loader.load();
    }

    public av_tx_fn(Pointer pointer) {
        super(pointer);
    }

    protected av_tx_fn() {
        allocate();
    }
}
