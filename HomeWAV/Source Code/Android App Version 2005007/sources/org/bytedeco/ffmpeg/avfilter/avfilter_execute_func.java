package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class avfilter_execute_func extends FunctionPointer {
    private native void allocate();

    public native int call(AVFilterContext aVFilterContext, avfilter_action_func avfilter_action_func, Pointer pointer, IntPointer intPointer, int i);

    static {
        Loader.load();
    }

    public avfilter_execute_func(Pointer pointer) {
        super(pointer);
    }

    protected avfilter_execute_func() {
        allocate();
    }
}
