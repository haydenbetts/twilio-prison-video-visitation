package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVDES extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint64_t"})
    public native long round_keys(int i, int i2);

    public native AVDES round_keys(int i, int i2, long j);

    @MemberGetter
    @Cast({"uint64_t(* /*[3]*/ )[16]"})
    public native LongPointer round_keys();

    public native int triple_des();

    public native AVDES triple_des(int i);

    static {
        Loader.load();
    }

    public AVDES() {
        super((Pointer) null);
        allocate();
    }

    public AVDES(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVDES(Pointer pointer) {
        super(pointer);
    }

    public AVDES position(long j) {
        return (AVDES) super.position(j);
    }

    public AVDES getPointer(long j) {
        return new AVDES((Pointer) this).position(this.position + j);
    }
}
