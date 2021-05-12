package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVIndexEntry extends Pointer {
    public static final int AVINDEX_DISCARD_FRAME = 2;
    public static final int AVINDEX_KEYFRAME = 1;

    private native void allocate();

    private native void allocateArray(long j);

    @NoOffset
    public native int flags();

    public native AVIndexEntry flags(int i);

    public native int min_distance();

    public native AVIndexEntry min_distance(int i);

    @Cast({"int64_t"})
    public native long pos();

    public native AVIndexEntry pos(long j);

    @NoOffset
    public native int size();

    public native AVIndexEntry size(int i);

    @Cast({"int64_t"})
    public native long timestamp();

    public native AVIndexEntry timestamp(long j);

    static {
        Loader.load();
    }

    public AVIndexEntry() {
        super((Pointer) null);
        allocate();
    }

    public AVIndexEntry(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVIndexEntry(Pointer pointer) {
        super(pointer);
    }

    public AVIndexEntry position(long j) {
        return (AVIndexEntry) super.position(j);
    }

    public AVIndexEntry getPointer(long j) {
        return new AVIndexEntry((Pointer) this).position(this.position + j);
    }
}
