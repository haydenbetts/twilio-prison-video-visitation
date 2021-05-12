package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class avfilter_action_func extends FunctionPointer {
    private native void allocate();

    public native int call(AVFilterContext aVFilterContext, Pointer pointer, int i, int i2);

    static {
        Loader.load();
    }

    public avfilter_action_func(Pointer pointer) {
        super(pointer);
    }

    protected avfilter_action_func() {
        allocate();
    }
}
