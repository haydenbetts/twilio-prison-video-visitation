package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCodecHWConfig extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"AVHWDeviceType"})
    public native int device_type();

    public native AVCodecHWConfig device_type(int i);

    public native int methods();

    public native AVCodecHWConfig methods(int i);

    @Cast({"AVPixelFormat"})
    public native int pix_fmt();

    public native AVCodecHWConfig pix_fmt(int i);

    static {
        Loader.load();
    }

    public AVCodecHWConfig() {
        super((Pointer) null);
        allocate();
    }

    public AVCodecHWConfig(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCodecHWConfig(Pointer pointer) {
        super(pointer);
    }

    public AVCodecHWConfig position(long j) {
        return (AVCodecHWConfig) super.position(j);
    }

    public AVCodecHWConfig getPointer(long j) {
        return new AVCodecHWConfig((Pointer) this).position(this.position + j);
    }
}
