package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVPanScan extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVPanScan _position(int i, int i2, short s);

    @MemberGetter
    @Cast({"int16_t(* /*[3]*/ )[2]"})
    @Name({"position"})
    public native ShortPointer _position();

    @Name({"position"})
    public native short _position(int i, int i2);

    public native int height();

    public native AVPanScan height(int i);

    public native int id();

    public native AVPanScan id(int i);

    public native int width();

    public native AVPanScan width(int i);

    static {
        Loader.load();
    }

    public AVPanScan() {
        super((Pointer) null);
        allocate();
    }

    public AVPanScan(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVPanScan(Pointer pointer) {
        super(pointer);
    }

    public AVPanScan position(long j) {
        return (AVPanScan) super.position(j);
    }

    public AVPanScan getPointer(long j) {
        return new AVPanScan((Pointer) this).position(this.position + j);
    }
}
