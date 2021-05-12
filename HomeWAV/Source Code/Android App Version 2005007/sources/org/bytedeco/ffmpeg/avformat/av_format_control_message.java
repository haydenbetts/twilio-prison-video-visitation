package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class av_format_control_message extends FunctionPointer {
    private native void allocate();

    public native int call(AVFormatContext aVFormatContext, int i, Pointer pointer, @Cast({"size_t"}) long j);

    static {
        Loader.load();
    }

    public av_format_control_message(Pointer pointer) {
        super(pointer);
    }

    protected av_format_control_message() {
        allocate();
    }
}
