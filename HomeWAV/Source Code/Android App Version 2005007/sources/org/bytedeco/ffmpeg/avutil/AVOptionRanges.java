package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVOptionRanges extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int nb_components();

    public native AVOptionRanges nb_components(int i);

    public native int nb_ranges();

    public native AVOptionRanges nb_ranges(int i);

    public native AVOptionRange range(int i);

    public native AVOptionRanges range(int i, AVOptionRange aVOptionRange);

    public native AVOptionRanges range(PointerPointer pointerPointer);

    @Cast({"AVOptionRange**"})
    public native PointerPointer range();

    static {
        Loader.load();
    }

    public AVOptionRanges() {
        super((Pointer) null);
        allocate();
    }

    public AVOptionRanges(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVOptionRanges(Pointer pointer) {
        super(pointer);
    }

    public AVOptionRanges position(long j) {
        return (AVOptionRanges) super.position(j);
    }

    public AVOptionRanges getPointer(long j) {
        return new AVOptionRanges((Pointer) this).position(this.position + j);
    }
}
