package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class av_alias32 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float f32();

    public native av_alias32 f32(float f);

    public native av_alias32 u16(int i, short s);

    @MemberGetter
    @Cast({"uint16_t*"})
    public native ShortPointer u16();

    @Cast({"uint16_t"})
    public native short u16(int i);

    @Cast({"uint32_t"})
    public native int u32();

    public native av_alias32 u32(int i);

    @Cast({"uint8_t"})
    public native byte u8(int i);

    public native av_alias32 u8(int i, byte b);

    @MemberGetter
    @Cast({"uint8_t*"})
    public native BytePointer u8();

    static {
        Loader.load();
    }

    public av_alias32() {
        super((Pointer) null);
        allocate();
    }

    public av_alias32(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public av_alias32(Pointer pointer) {
        super(pointer);
    }

    public av_alias32 position(long j) {
        return (av_alias32) super.position(j);
    }

    public av_alias32 getPointer(long j) {
        return new av_alias32((Pointer) this).position(this.position + j);
    }
}
