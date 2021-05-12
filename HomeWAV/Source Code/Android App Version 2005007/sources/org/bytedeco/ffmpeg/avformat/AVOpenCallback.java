package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVOpenCallback extends FunctionPointer {
    private native void allocate();

    public native int call(AVFormatContext aVFormatContext, @Cast({"AVIOContext**"}) PointerPointer pointerPointer, @Cast({"const char*"}) BytePointer bytePointer, int i, @Const AVIOInterruptCB aVIOInterruptCB, @Cast({"AVDictionary**"}) PointerPointer pointerPointer2);

    static {
        Loader.load();
    }

    public AVOpenCallback(Pointer pointer) {
        super(pointer);
    }

    protected AVOpenCallback() {
        allocate();
    }
}
