package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVTimecode extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint32_t"})
    public native int flags();

    public native AVTimecode flags(int i);

    @Cast({"unsigned"})
    public native int fps();

    public native AVTimecode fps(int i);

    @ByRef
    public native AVRational rate();

    public native AVTimecode rate(AVRational aVRational);

    public native int start();

    public native AVTimecode start(int i);

    static {
        Loader.load();
    }

    public AVTimecode() {
        super((Pointer) null);
        allocate();
    }

    public AVTimecode(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVTimecode(Pointer pointer) {
        super(pointer);
    }

    public AVTimecode position(long j) {
        return (AVTimecode) super.position(j);
    }

    public AVTimecode getPointer(long j) {
        return new AVTimecode((Pointer) this).position(this.position + j);
    }
}
