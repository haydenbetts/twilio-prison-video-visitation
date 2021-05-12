package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVPixFmtDescriptor extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVPixFmtDescriptor alias(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer alias();

    @MemberGetter
    public native AVComponentDescriptor comp();

    @ByRef
    public native AVComponentDescriptor comp(int i);

    public native AVPixFmtDescriptor comp(int i, AVComponentDescriptor aVComponentDescriptor);

    @Cast({"uint64_t"})
    public native long flags();

    public native AVPixFmtDescriptor flags(long j);

    @Cast({"uint8_t"})
    public native byte log2_chroma_h();

    public native AVPixFmtDescriptor log2_chroma_h(byte b);

    @Cast({"uint8_t"})
    public native byte log2_chroma_w();

    public native AVPixFmtDescriptor log2_chroma_w(byte b);

    public native AVPixFmtDescriptor name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    @Cast({"uint8_t"})
    public native byte nb_components();

    public native AVPixFmtDescriptor nb_components(byte b);

    static {
        Loader.load();
    }

    public AVPixFmtDescriptor() {
        super((Pointer) null);
        allocate();
    }

    public AVPixFmtDescriptor(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVPixFmtDescriptor(Pointer pointer) {
        super(pointer);
    }

    public AVPixFmtDescriptor position(long j) {
        return (AVPixFmtDescriptor) super.position(j);
    }

    public AVPixFmtDescriptor getPointer(long j) {
        return new AVPixFmtDescriptor((Pointer) this).position(this.position + j);
    }
}
