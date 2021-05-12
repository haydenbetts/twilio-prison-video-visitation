package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class av_intfloat64 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double f();

    public native av_intfloat64 f(double d);

    @Cast({"uint64_t"})
    public native long i();

    public native av_intfloat64 i(long j);

    static {
        Loader.load();
    }

    public av_intfloat64() {
        super((Pointer) null);
        allocate();
    }

    public av_intfloat64(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public av_intfloat64(Pointer pointer) {
        super(pointer);
    }

    public av_intfloat64 position(long j) {
        return (av_intfloat64) super.position(j);
    }

    public av_intfloat64 getPointer(long j) {
        return new av_intfloat64((Pointer) this).position(this.position + j);
    }
}
