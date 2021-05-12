package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVPacketSideData extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVPacketSideData data(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer data();

    public native int size();

    public native AVPacketSideData size(int i);

    @Cast({"AVPacketSideDataType"})
    public native int type();

    public native AVPacketSideData type(int i);

    static {
        Loader.load();
    }

    public AVPacketSideData() {
        super((Pointer) null);
        allocate();
    }

    public AVPacketSideData(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVPacketSideData(Pointer pointer) {
        super(pointer);
    }

    public AVPacketSideData position(long j) {
        return (AVPacketSideData) super.position(j);
    }

    public AVPacketSideData getPointer(long j) {
        return new AVPacketSideData((Pointer) this).position(this.position + j);
    }
}
