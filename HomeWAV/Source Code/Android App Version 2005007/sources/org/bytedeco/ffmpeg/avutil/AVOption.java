package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVOption extends Pointer {
    public static final int AV_OPT_FLAG_AUDIO_PARAM = 8;
    public static final int AV_OPT_FLAG_BSF_PARAM = 256;
    public static final int AV_OPT_FLAG_CHILD_CONSTS = 262144;
    public static final int AV_OPT_FLAG_DECODING_PARAM = 2;
    public static final int AV_OPT_FLAG_DEPRECATED = 131072;
    public static final int AV_OPT_FLAG_ENCODING_PARAM = 1;
    public static final int AV_OPT_FLAG_EXPORT = 64;
    public static final int AV_OPT_FLAG_FILTERING_PARAM = 65536;
    public static final int AV_OPT_FLAG_READONLY = 128;
    public static final int AV_OPT_FLAG_RUNTIME_PARAM = 32768;
    public static final int AV_OPT_FLAG_SUBTITLE_PARAM = 32;
    public static final int AV_OPT_FLAG_VIDEO_PARAM = 16;

    private native void allocate();

    private native void allocateArray(long j);

    @Name({"default_val.dbl"})
    public native double default_val_dbl();

    public native AVOption default_val_dbl(double d);

    @Cast({"int64_t"})
    @Name({"default_val.i64"})
    public native long default_val_i64();

    public native AVOption default_val_i64(long j);

    public native AVOption default_val_q(AVRational aVRational);

    @ByRef
    @Name({"default_val.q"})
    public native AVRational default_val_q();

    public native AVOption default_val_str(BytePointer bytePointer);

    @Cast({"const char*"})
    @Name({"default_val.str"})
    public native BytePointer default_val_str();

    public native int flags();

    public native AVOption flags(int i);

    public native AVOption help(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer help();

    public native double max();

    public native AVOption max(double d);

    public native double min();

    public native AVOption min(double d);

    public native AVOption name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native int offset();

    public native AVOption offset(int i);

    @Cast({"AVOptionType"})
    public native int type();

    public native AVOption type(int i);

    public native AVOption unit(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer unit();

    static {
        Loader.load();
    }

    public AVOption() {
        super((Pointer) null);
        allocate();
    }

    public AVOption(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVOption(Pointer pointer) {
        super(pointer);
    }

    public AVOption position(long j) {
        return (AVOption) super.position(j);
    }

    public AVOption getPointer(long j) {
        return new AVOption((Pointer) this).position(this.position + j);
    }
}
