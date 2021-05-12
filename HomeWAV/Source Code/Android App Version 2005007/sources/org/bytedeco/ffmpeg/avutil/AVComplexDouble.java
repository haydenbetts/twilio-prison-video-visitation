package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVComplexDouble extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double im();

    public native AVComplexDouble im(double d);

    public native double re();

    public native AVComplexDouble re(double d);

    static {
        Loader.load();
    }

    public AVComplexDouble() {
        super((Pointer) null);
        allocate();
    }

    public AVComplexDouble(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVComplexDouble(Pointer pointer) {
        super(pointer);
    }

    public AVComplexDouble position(long j) {
        return (AVComplexDouble) super.position(j);
    }

    public AVComplexDouble getPointer(long j) {
        return new AVComplexDouble((Pointer) this).position(this.position + j);
    }
}
