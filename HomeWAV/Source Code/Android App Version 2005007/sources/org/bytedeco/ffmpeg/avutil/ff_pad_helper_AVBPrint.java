package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class ff_pad_helper_AVBPrint extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"unsigned"})
    public native int len();

    public native ff_pad_helper_AVBPrint len(int i);

    @Cast({"char"})
    public native byte reserved_internal_buffer(int i);

    public native ff_pad_helper_AVBPrint reserved_internal_buffer(int i, byte b);

    @MemberGetter
    @Cast({"char*"})
    public native BytePointer reserved_internal_buffer();

    @Cast({"unsigned"})
    public native int size();

    public native ff_pad_helper_AVBPrint size(int i);

    @Cast({"unsigned"})
    public native int size_max();

    public native ff_pad_helper_AVBPrint size_max(int i);

    public native ff_pad_helper_AVBPrint str(BytePointer bytePointer);

    @Cast({"char*"})
    public native BytePointer str();

    static {
        Loader.load();
    }

    public ff_pad_helper_AVBPrint() {
        super((Pointer) null);
        allocate();
    }

    public ff_pad_helper_AVBPrint(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public ff_pad_helper_AVBPrint(Pointer pointer) {
        super(pointer);
    }

    public ff_pad_helper_AVBPrint position(long j) {
        return (ff_pad_helper_AVBPrint) super.position(j);
    }

    public ff_pad_helper_AVBPrint getPointer(long j) {
        return new ff_pad_helper_AVBPrint((Pointer) this).position(this.position + j);
    }
}
