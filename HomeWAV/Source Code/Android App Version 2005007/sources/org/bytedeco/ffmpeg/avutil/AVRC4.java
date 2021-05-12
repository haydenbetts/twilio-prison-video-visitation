package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVRC4 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint8_t"})
    public native byte state(int i);

    public native AVRC4 state(int i, byte b);

    @MemberGetter
    @Cast({"uint8_t*"})
    public native BytePointer state();

    public native int x();

    public native AVRC4 x(int i);

    public native int y();

    public native AVRC4 y(int i);

    static {
        Loader.load();
    }

    public AVRC4() {
        super((Pointer) null);
        allocate();
    }

    public AVRC4(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVRC4(Pointer pointer) {
        super(pointer);
    }

    public AVRC4 position(long j) {
        return (AVRC4) super.position(j);
    }

    public AVRC4 getPointer(long j) {
        return new AVRC4((Pointer) this).position(this.position + j);
    }
}
