package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avcodec.class})
public class AVProfile extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVProfile name(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer name();

    public native int profile();

    public native AVProfile profile(int i);

    static {
        Loader.load();
    }

    public AVProfile() {
        super((Pointer) null);
        allocate();
    }

    public AVProfile(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVProfile(Pointer pointer) {
        super(pointer);
    }

    public AVProfile position(long j) {
        return (AVProfile) super.position(j);
    }

    public AVProfile getPointer(long j) {
        return new AVProfile((Pointer) this).position(this.position + j);
    }
}
