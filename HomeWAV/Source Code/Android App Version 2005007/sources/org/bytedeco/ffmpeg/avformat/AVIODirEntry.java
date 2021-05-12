package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVIODirEntry extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"int64_t"})
    public native long access_timestamp();

    public native AVIODirEntry access_timestamp(long j);

    @Cast({"int64_t"})
    public native long filemode();

    public native AVIODirEntry filemode(long j);

    @Cast({"int64_t"})
    public native long group_id();

    public native AVIODirEntry group_id(long j);

    @Cast({"int64_t"})
    public native long modification_timestamp();

    public native AVIODirEntry modification_timestamp(long j);

    public native AVIODirEntry name(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer name();

    @Cast({"int64_t"})
    public native long size();

    public native AVIODirEntry size(long j);

    @Cast({"int64_t"})
    public native long status_change_timestamp();

    public native AVIODirEntry status_change_timestamp(long j);

    public native int type();

    public native AVIODirEntry type(int i);

    @Cast({"int64_t"})
    public native long user_id();

    public native AVIODirEntry user_id(long j);

    public native int utf8();

    public native AVIODirEntry utf8(int i);

    static {
        Loader.load();
    }

    public AVIODirEntry() {
        super((Pointer) null);
        allocate();
    }

    public AVIODirEntry(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVIODirEntry(Pointer pointer) {
        super(pointer);
    }

    public AVIODirEntry position(long j) {
        return (AVIODirEntry) super.position(j);
    }

    public AVIODirEntry getPointer(long j) {
        return new AVIODirEntry((Pointer) this).position(this.position + j);
    }
}
