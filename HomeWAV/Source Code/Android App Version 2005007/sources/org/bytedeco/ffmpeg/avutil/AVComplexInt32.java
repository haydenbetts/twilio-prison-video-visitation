package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVComplexInt32 extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int im();

    public native AVComplexInt32 im(int i);

    public native int re();

    public native AVComplexInt32 re(int i);

    static {
        Loader.load();
    }

    public AVComplexInt32() {
        super((Pointer) null);
        allocate();
    }

    public AVComplexInt32(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVComplexInt32(Pointer pointer) {
        super(pointer);
    }

    public AVComplexInt32 position(long j) {
        return (AVComplexInt32) super.position(j);
    }

    public AVComplexInt32 getPointer(long j) {
        return new AVComplexInt32((Pointer) this).position(this.position + j);
    }
}
