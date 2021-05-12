package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVComponentDescriptor extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int depth();

    public native AVComponentDescriptor depth(int i);

    @Deprecated
    public native int depth_minus1();

    public native AVComponentDescriptor depth_minus1(int i);

    public native int offset();

    public native AVComponentDescriptor offset(int i);

    @Deprecated
    public native int offset_plus1();

    public native AVComponentDescriptor offset_plus1(int i);

    public native int plane();

    public native AVComponentDescriptor plane(int i);

    public native int shift();

    public native AVComponentDescriptor shift(int i);

    public native int step();

    public native AVComponentDescriptor step(int i);

    @Deprecated
    public native int step_minus1();

    public native AVComponentDescriptor step_minus1(int i);

    static {
        Loader.load();
    }

    public AVComponentDescriptor() {
        super((Pointer) null);
        allocate();
    }

    public AVComponentDescriptor(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVComponentDescriptor(Pointer pointer) {
        super(pointer);
    }

    public AVComponentDescriptor position(long j) {
        return (AVComponentDescriptor) super.position(j);
    }

    public AVComponentDescriptor getPointer(long j) {
        return new AVComponentDescriptor((Pointer) this).position(this.position + j);
    }
}
