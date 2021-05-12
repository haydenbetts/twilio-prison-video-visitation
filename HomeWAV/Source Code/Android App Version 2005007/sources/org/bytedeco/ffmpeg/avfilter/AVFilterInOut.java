package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avfilter.class})
public class AVFilterInOut extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVFilterContext filter_ctx();

    public native AVFilterInOut filter_ctx(AVFilterContext aVFilterContext);

    public native AVFilterInOut name(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer name();

    public native AVFilterInOut next();

    public native AVFilterInOut next(AVFilterInOut aVFilterInOut);

    public native int pad_idx();

    public native AVFilterInOut pad_idx(int i);

    static {
        Loader.load();
    }

    public AVFilterInOut() {
        super((Pointer) null);
        allocate();
    }

    public AVFilterInOut(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFilterInOut(Pointer pointer) {
        super(pointer);
    }

    public AVFilterInOut position(long j) {
        return (AVFilterInOut) super.position(j);
    }

    public AVFilterInOut getPointer(long j) {
        return new AVFilterInOut((Pointer) this).position(this.position + j);
    }
}
