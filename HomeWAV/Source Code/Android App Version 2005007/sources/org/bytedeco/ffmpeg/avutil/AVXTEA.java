package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVXTEA extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint32_t"})
    public native int key(int i);

    public native AVXTEA key(int i, int i2);

    @MemberGetter
    @Cast({"uint32_t*"})
    public native IntPointer key();

    static {
        Loader.load();
    }

    public AVXTEA() {
        super((Pointer) null);
        allocate();
    }

    public AVXTEA(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVXTEA(Pointer pointer) {
        super(pointer);
    }

    public AVXTEA position(long j) {
        return (AVXTEA) super.position(j);
    }

    public AVXTEA getPointer(long j) {
        return new AVXTEA((Pointer) this).position(this.position + j);
    }
}
