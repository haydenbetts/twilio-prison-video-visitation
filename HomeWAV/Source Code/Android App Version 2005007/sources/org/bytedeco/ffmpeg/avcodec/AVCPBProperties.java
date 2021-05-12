package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCPBProperties extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int avg_bitrate();

    public native AVCPBProperties avg_bitrate(int i);

    public native int buffer_size();

    public native AVCPBProperties buffer_size(int i);

    public native int max_bitrate();

    public native AVCPBProperties max_bitrate(int i);

    public native int min_bitrate();

    public native AVCPBProperties min_bitrate(int i);

    @Cast({"uint64_t"})
    public native long vbv_delay();

    public native AVCPBProperties vbv_delay(long j);

    static {
        Loader.load();
    }

    public AVCPBProperties() {
        super((Pointer) null);
        allocate();
    }

    public AVCPBProperties(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCPBProperties(Pointer pointer) {
        super(pointer);
    }

    public AVCPBProperties position(long j) {
        return (AVCPBProperties) super.position(j);
    }

    public AVCPBProperties getPointer(long j) {
        return new AVCPBProperties((Pointer) this).position(this.position + j);
    }
}
