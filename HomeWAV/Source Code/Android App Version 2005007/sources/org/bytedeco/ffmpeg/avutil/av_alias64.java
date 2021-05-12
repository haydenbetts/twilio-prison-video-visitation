package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class av_alias64 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float f32(int i);

    public native av_alias64 f32(int i, float f);

    @MemberGetter
    public native FloatPointer f32();

    public native double f64();

    public native av_alias64 f64(double d);

    public native av_alias64 u16(int i, short s);

    @MemberGetter
    @Cast({"uint16_t*"})
    public native ShortPointer u16();

    @Cast({"uint16_t"})
    public native short u16(int i);

    @Cast({"uint32_t"})
    public native int u32(int i);

    public native av_alias64 u32(int i, int i2);

    @MemberGetter
    @Cast({"uint32_t*"})
    public native IntPointer u32();

    @Cast({"uint64_t"})
    public native long u64();

    public native av_alias64 u64(long j);

    @Cast({"uint8_t"})
    public native byte u8(int i);

    public native av_alias64 u8(int i, byte b);

    @MemberGetter
    @Cast({"uint8_t*"})
    public native BytePointer u8();

    static {
        Loader.load();
    }

    public av_alias64() {
        super((Pointer) null);
        allocate();
    }

    public av_alias64(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public av_alias64(Pointer pointer) {
        super(pointer);
    }

    public av_alias64 position(long j) {
        return (av_alias64) super.position(j);
    }

    public av_alias64 getPointer(long j) {
        return new av_alias64((Pointer) this).position(this.position + j);
    }
}
