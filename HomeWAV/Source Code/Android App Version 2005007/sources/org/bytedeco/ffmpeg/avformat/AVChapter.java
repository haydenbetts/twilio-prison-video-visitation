package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVChapter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"int64_t"})
    public native long end();

    public native AVChapter end(long j);

    public native int id();

    public native AVChapter id(int i);

    public native AVChapter metadata(AVDictionary aVDictionary);

    public native AVDictionary metadata();

    @Cast({"int64_t"})
    public native long start();

    public native AVChapter start(long j);

    public native AVChapter time_base(AVRational aVRational);

    @ByRef
    public native AVRational time_base();

    static {
        Loader.load();
    }

    public AVChapter() {
        super((Pointer) null);
        allocate();
    }

    public AVChapter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVChapter(Pointer pointer) {
        super(pointer);
    }

    public AVChapter position(long j) {
        return (AVChapter) super.position(j);
    }

    public AVChapter getPointer(long j) {
        return new AVChapter((Pointer) this).position(this.position + j);
    }
}
