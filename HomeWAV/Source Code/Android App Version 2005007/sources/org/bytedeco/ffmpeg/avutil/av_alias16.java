package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class av_alias16 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native av_alias16 u16(short s);

    @Cast({"uint16_t"})
    public native short u16();

    @Cast({"uint8_t"})
    public native byte u8(int i);

    public native av_alias16 u8(int i, byte b);

    @MemberGetter
    @Cast({"uint8_t*"})
    public native BytePointer u8();

    static {
        Loader.load();
    }

    public av_alias16() {
        super((Pointer) null);
        allocate();
    }

    public av_alias16(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public av_alias16(Pointer pointer) {
        super(pointer);
    }

    public av_alias16 position(long j) {
        return (av_alias16) super.position(j);
    }

    public av_alias16 getPointer(long j) {
        return new av_alias16((Pointer) this).position(this.position + j);
    }
}
