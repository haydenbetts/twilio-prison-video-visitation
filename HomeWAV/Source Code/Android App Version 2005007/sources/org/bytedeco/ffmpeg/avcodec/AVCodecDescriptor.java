package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVCodecDescriptor extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"AVCodecID"})
    public native int id();

    public native AVCodecDescriptor id(int i);

    public native AVCodecDescriptor long_name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer long_name();

    @MemberGetter
    @Cast({"const char*"})
    public native BytePointer mime_types(int i);

    @MemberGetter
    @Cast({"const char*const*"})
    public native PointerPointer mime_types();

    public native AVCodecDescriptor name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native AVCodecDescriptor profiles(AVProfile aVProfile);

    @Const
    public native AVProfile profiles();

    public native int props();

    public native AVCodecDescriptor props(int i);

    @Cast({"AVMediaType"})
    public native int type();

    public native AVCodecDescriptor type(int i);

    static {
        Loader.load();
    }

    public AVCodecDescriptor() {
        super((Pointer) null);
        allocate();
    }

    public AVCodecDescriptor(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVCodecDescriptor(Pointer pointer) {
        super(pointer);
    }

    public AVCodecDescriptor position(long j) {
        return (AVCodecDescriptor) super.position(j);
    }

    public AVCodecDescriptor getPointer(long j) {
        return new AVCodecDescriptor((Pointer) this).position(this.position + j);
    }
}
