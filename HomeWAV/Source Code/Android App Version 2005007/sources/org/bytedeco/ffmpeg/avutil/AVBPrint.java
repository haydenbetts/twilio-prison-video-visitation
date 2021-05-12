package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVBPrint extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"unsigned"})
    public native int len();

    public native AVBPrint len(int i);

    @Cast({"char"})
    public native byte reserved_internal_buffer(int i);

    public native AVBPrint reserved_internal_buffer(int i, byte b);

    @MemberGetter
    @Cast({"char*"})
    public native BytePointer reserved_internal_buffer();

    @Cast({"char"})
    public native byte reserved_padding(int i);

    public native AVBPrint reserved_padding(int i, byte b);

    @MemberGetter
    @Cast({"char*"})
    public native BytePointer reserved_padding();

    @Cast({"unsigned"})
    public native int size();

    public native AVBPrint size(int i);

    @Cast({"unsigned"})
    public native int size_max();

    public native AVBPrint size_max(int i);

    public native AVBPrint str(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer str();

    static {
        Loader.load();
    }

    public AVBPrint() {
        super((Pointer) null);
        allocate();
    }

    public AVBPrint(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBPrint(Pointer pointer) {
        super(pointer);
    }

    public AVBPrint position(long j) {
        return (AVBPrint) super.position(j);
    }

    public AVBPrint getPointer(long j) {
        return new AVBPrint((Pointer) this).position(this.position + j);
    }
}
