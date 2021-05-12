package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVLFG extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int index();

    public native AVLFG index(int i);

    @Cast({"unsigned int"})
    public native int state(int i);

    public native AVLFG state(int i, int i2);

    @MemberGetter
    @Cast({"unsigned int*"})
    public native IntPointer state();

    static {
        Loader.load();
    }

    public AVLFG() {
        super((Pointer) null);
        allocate();
    }

    public AVLFG(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVLFG(Pointer pointer) {
        super(pointer);
    }

    public AVLFG position(long j) {
        return (AVLFG) super.position(j);
    }

    public AVLFG getPointer(long j) {
        return new AVLFG((Pointer) this).position(this.position + j);
    }
}
