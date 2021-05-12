package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVProbeData extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVProbeData buf(BytePointer bytePointer);

    @Cast({"unsigned char*"})
    public native BytePointer buf();

    public native int buf_size();

    public native AVProbeData buf_size(int i);

    public native AVProbeData filename(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer filename();

    public native AVProbeData mime_type(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer mime_type();

    static {
        Loader.load();
    }

    public AVProbeData() {
        super((Pointer) null);
        allocate();
    }

    public AVProbeData(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVProbeData(Pointer pointer) {
        super(pointer);
    }

    public AVProbeData position(long j) {
        return (AVProbeData) super.position(j);
    }

    public AVProbeData getPointer(long j) {
        return new AVProbeData((Pointer) this).position(this.position + j);
    }
}
