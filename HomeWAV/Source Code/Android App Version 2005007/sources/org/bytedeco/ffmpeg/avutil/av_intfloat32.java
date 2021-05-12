package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class av_intfloat32 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float f();

    public native av_intfloat32 f(float f);

    @Cast({"uint32_t"})
    public native int i();

    public native av_intfloat32 i(int i);

    static {
        Loader.load();
    }

    public av_intfloat32() {
        super((Pointer) null);
        allocate();
    }

    public av_intfloat32(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public av_intfloat32(Pointer pointer) {
        super(pointer);
    }

    public av_intfloat32 position(long j) {
        return (av_intfloat32) super.position(j);
    }

    public av_intfloat32 getPointer(long j) {
        return new av_intfloat32((Pointer) this).position(this.position + j);
    }
}
