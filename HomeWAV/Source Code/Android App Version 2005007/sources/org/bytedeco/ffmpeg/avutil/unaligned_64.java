package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class unaligned_64 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint64_t"})
    public native long l();

    public native unaligned_64 l(long j);

    static {
        Loader.load();
    }

    public unaligned_64() {
        super((Pointer) null);
        allocate();
    }

    public unaligned_64(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public unaligned_64(Pointer pointer) {
        super(pointer);
    }

    public unaligned_64 position(long j) {
        return (unaligned_64) super.position(j);
    }

    public unaligned_64 getPointer(long j) {
        return new unaligned_64((Pointer) this).position(this.position + j);
    }
}
