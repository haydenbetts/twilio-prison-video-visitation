package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVPacketList extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVPacketList next();

    public native AVPacketList next(AVPacketList aVPacketList);

    @ByRef
    public native AVPacket pkt();

    public native AVPacketList pkt(AVPacket aVPacket);

    static {
        Loader.load();
    }

    public AVPacketList() {
        super((Pointer) null);
        allocate();
    }

    public AVPacketList(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVPacketList(Pointer pointer) {
        super(pointer);
    }

    public AVPacketList position(long j) {
        return (AVPacketList) super.position(j);
    }

    public AVPacketList getPointer(long j) {
        return new AVPacketList((Pointer) this).position(this.position + j);
    }
}
