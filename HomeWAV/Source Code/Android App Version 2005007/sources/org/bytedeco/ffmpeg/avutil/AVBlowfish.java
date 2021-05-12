package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVBlowfish extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint32_t"})
    public native int p(int i);

    public native AVBlowfish p(int i, int i2);

    @MemberGetter
    @Cast({"uint32_t*"})
    public native IntPointer p();

    @Cast({"uint32_t"})
    public native int s(int i, int i2);

    public native AVBlowfish s(int i, int i2, int i3);

    @MemberGetter
    @Cast({"uint32_t(* /*[4]*/ )[256]"})
    public native IntPointer s();

    static {
        Loader.load();
    }

    public AVBlowfish() {
        super((Pointer) null);
        allocate();
    }

    public AVBlowfish(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBlowfish(Pointer pointer) {
        super(pointer);
    }

    public AVBlowfish position(long j) {
        return (AVBlowfish) super.position(j);
    }

    public AVBlowfish getPointer(long j) {
        return new AVBlowfish((Pointer) this).position(this.position + j);
    }
}
