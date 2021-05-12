package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVRational extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int den();

    public native AVRational den(int i);

    public native int num();

    public native AVRational num(int i);

    static {
        Loader.load();
    }

    public AVRational() {
        super((Pointer) null);
        allocate();
    }

    public AVRational(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVRational(Pointer pointer) {
        super(pointer);
    }

    public AVRational position(long j) {
        return (AVRational) super.position(j);
    }

    public AVRational getPointer(long j) {
        return new AVRational((Pointer) this).position(this.position + j);
    }
}
