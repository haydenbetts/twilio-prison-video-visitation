package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class FFTComplex extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"FFTSample"})
    public native float im();

    public native FFTComplex im(float f);

    @Cast({"FFTSample"})
    public native float re();

    public native FFTComplex re(float f);

    static {
        Loader.load();
    }

    public FFTComplex() {
        super((Pointer) null);
        allocate();
    }

    public FFTComplex(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public FFTComplex(Pointer pointer) {
        super(pointer);
    }

    public FFTComplex position(long j) {
        return (FFTComplex) super.position(j);
    }

    public FFTComplex getPointer(long j) {
        return new FFTComplex((Pointer) this).position(this.position + j);
    }
}
