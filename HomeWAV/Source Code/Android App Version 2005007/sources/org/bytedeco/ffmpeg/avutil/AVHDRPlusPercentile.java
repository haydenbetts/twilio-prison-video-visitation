package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVHDRPlusPercentile extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint8_t"})
    public native byte percentage();

    public native AVHDRPlusPercentile percentage(byte b);

    public native AVHDRPlusPercentile percentile(AVRational aVRational);

    @ByRef
    public native AVRational percentile();

    static {
        Loader.load();
    }

    public AVHDRPlusPercentile() {
        super((Pointer) null);
        allocate();
    }

    public AVHDRPlusPercentile(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVHDRPlusPercentile(Pointer pointer) {
        super(pointer);
    }

    public AVHDRPlusPercentile position(long j) {
        return (AVHDRPlusPercentile) super.position(j);
    }

    public AVHDRPlusPercentile getPointer(long j) {
        return new AVHDRPlusPercentile((Pointer) this).position(this.position + j);
    }
}
