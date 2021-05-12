package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVPicture extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVPicture data(int i, BytePointer bytePointer);

    @Deprecated
    @Cast({"uint8_t*"})
    public native BytePointer data(int i);

    @MemberGetter
    @Deprecated
    @Cast({"uint8_t**"})
    public native PointerPointer data();

    @Deprecated
    public native int linesize(int i);

    public native AVPicture linesize(int i, int i2);

    @MemberGetter
    @Deprecated
    public native IntPointer linesize();

    static {
        Loader.load();
    }

    public AVPicture() {
        super((Pointer) null);
        allocate();
    }

    public AVPicture(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVPicture(Pointer pointer) {
        super(pointer);
    }

    public AVPicture position(long j) {
        return (AVPicture) super.position(j);
    }

    public AVPicture getPointer(long j) {
        return new AVPicture((Pointer) this).position(this.position + j);
    }
}
