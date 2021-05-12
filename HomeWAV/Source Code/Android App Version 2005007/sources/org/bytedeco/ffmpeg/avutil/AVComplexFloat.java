package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVComplexFloat extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float im();

    public native AVComplexFloat im(float f);

    public native float re();

    public native AVComplexFloat re(float f);

    static {
        Loader.load();
    }

    public AVComplexFloat() {
        super((Pointer) null);
        allocate();
    }

    public AVComplexFloat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVComplexFloat(Pointer pointer) {
        super(pointer);
    }

    public AVComplexFloat position(long j) {
        return (AVComplexFloat) super.position(j);
    }

    public AVComplexFloat getPointer(long j) {
        return new AVComplexFloat((Pointer) this).position(this.position + j);
    }
}
