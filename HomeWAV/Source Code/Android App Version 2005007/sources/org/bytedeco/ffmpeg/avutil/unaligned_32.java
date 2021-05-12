package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class unaligned_32 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint32_t"})
    public native int l();

    public native unaligned_32 l(int i);

    static {
        Loader.load();
    }

    public unaligned_32() {
        super((Pointer) null);
        allocate();
    }

    public unaligned_32(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public unaligned_32(Pointer pointer) {
        super(pointer);
    }

    public unaligned_32 position(long j) {
        return (unaligned_32) super.position(j);
    }

    public unaligned_32 getPointer(long j) {
        return new unaligned_32((Pointer) this).position(this.position + j);
    }
}
