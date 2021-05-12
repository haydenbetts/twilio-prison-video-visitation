package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avformat.class})
public class AVProgram extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"AVDiscard"})
    public native int discard();

    public native AVProgram discard(int i);

    @Cast({"int64_t"})
    public native long end_time();

    public native AVProgram end_time(long j);

    public native int flags();

    public native AVProgram flags(int i);

    public native int id();

    public native AVProgram id(int i);

    public native AVProgram metadata(AVDictionary aVDictionary);

    public native AVDictionary metadata();

    @Cast({"unsigned int"})
    public native int nb_stream_indexes();

    public native AVProgram nb_stream_indexes(int i);

    public native int pcr_pid();

    public native AVProgram pcr_pid(int i);

    public native int pmt_pid();

    public native AVProgram pmt_pid(int i);

    public native int pmt_version();

    public native AVProgram pmt_version(int i);

    public native int program_num();

    public native AVProgram program_num(int i);

    public native int pts_wrap_behavior();

    public native AVProgram pts_wrap_behavior(int i);

    @Cast({"int64_t"})
    public native long pts_wrap_reference();

    public native AVProgram pts_wrap_reference(long j);

    @Cast({"int64_t"})
    public native long start_time();

    public native AVProgram start_time(long j);

    public native AVProgram stream_index(IntPointer intPointer);

    @Cast({"unsigned int*"})
    public native IntPointer stream_index();

    static {
        Loader.load();
    }

    public AVProgram() {
        super((Pointer) null);
        allocate();
    }

    public AVProgram(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVProgram(Pointer pointer) {
        super(pointer);
    }

    public AVProgram position(long j) {
        return (AVProgram) super.position(j);
    }

    public AVProgram getPointer(long j) {
        return new AVProgram((Pointer) this).position(this.position + j);
    }
}
