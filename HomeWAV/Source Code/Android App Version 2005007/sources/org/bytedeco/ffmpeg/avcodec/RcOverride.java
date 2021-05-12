package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class RcOverride extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int end_frame();

    public native RcOverride end_frame(int i);

    public native int qscale();

    public native RcOverride qscale(int i);

    public native float quality_factor();

    public native RcOverride quality_factor(float f);

    public native int start_frame();

    public native RcOverride start_frame(int i);

    static {
        Loader.load();
    }

    public RcOverride() {
        super((Pointer) null);
        allocate();
    }

    public RcOverride(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public RcOverride(Pointer pointer) {
        super(pointer);
    }

    public RcOverride position(long j) {
        return (RcOverride) super.position(j);
    }

    public RcOverride getPointer(long j) {
        return new RcOverride((Pointer) this).position(this.position + j);
    }
}
