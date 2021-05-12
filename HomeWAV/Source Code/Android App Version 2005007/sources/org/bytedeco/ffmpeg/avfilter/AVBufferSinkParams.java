package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVBufferSinkParams extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVBufferSinkParams pixel_fmts(IntPointer intPointer);

    @Cast({"const AVPixelFormat*"})
    public native IntPointer pixel_fmts();

    static {
        Loader.load();
    }

    public AVBufferSinkParams() {
        super((Pointer) null);
        allocate();
    }

    public AVBufferSinkParams(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBufferSinkParams(Pointer pointer) {
        super(pointer);
    }

    public AVBufferSinkParams position(long j) {
        return (AVBufferSinkParams) super.position(j);
    }

    public AVBufferSinkParams getPointer(long j) {
        return new AVBufferSinkParams((Pointer) this).position(this.position + j);
    }
}
