package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVPacket extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVPacket buf(AVBufferRef aVBufferRef);

    public native AVBufferRef buf();

    @Deprecated
    @Cast({"int64_t"})
    public native long convergence_duration();

    public native AVPacket convergence_duration(long j);

    public native AVPacket data(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer data();

    @Cast({"int64_t"})
    public native long dts();

    public native AVPacket dts(long j);

    @Cast({"int64_t"})
    public native long duration();

    public native AVPacket duration(long j);

    public native int flags();

    public native AVPacket flags(int i);

    @Cast({"int64_t"})
    public native long pos();

    public native AVPacket pos(long j);

    @Cast({"int64_t"})
    public native long pts();

    public native AVPacket pts(long j);

    public native AVPacket side_data(AVPacketSideData aVPacketSideData);

    public native AVPacketSideData side_data();

    public native int side_data_elems();

    public native AVPacket side_data_elems(int i);

    public native int size();

    public native AVPacket size(int i);

    public native int stream_index();

    public native AVPacket stream_index(int i);

    static {
        Loader.load();
    }

    public AVPacket() {
        super((Pointer) null);
        allocate();
    }

    public AVPacket(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVPacket(Pointer pointer) {
        super(pointer);
    }

    public AVPacket position(long j) {
        return (AVPacket) super.position(j);
    }

    public AVPacket getPointer(long j) {
        return new AVPacket((Pointer) this).position(this.position + j);
    }
}
