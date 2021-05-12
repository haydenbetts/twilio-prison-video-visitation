package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class unaligned_16 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native unaligned_16 l(short s);

    @Cast({"uint16_t"})
    public native short l();

    static {
        Loader.load();
    }

    public unaligned_16() {
        super((Pointer) null);
        allocate();
    }

    public unaligned_16(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public unaligned_16(Pointer pointer) {
        super(pointer);
    }

    public unaligned_16 position(long j) {
        return (unaligned_16) super.position(j);
    }

    public unaligned_16 getPointer(long j) {
        return new unaligned_16((Pointer) this).position(this.position + j);
    }
}
