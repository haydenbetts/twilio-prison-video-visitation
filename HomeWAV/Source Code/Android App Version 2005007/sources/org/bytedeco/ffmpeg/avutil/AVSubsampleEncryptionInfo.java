package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVSubsampleEncryptionInfo extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"unsigned int"})
    public native int bytes_of_clear_data();

    public native AVSubsampleEncryptionInfo bytes_of_clear_data(int i);

    @Cast({"unsigned int"})
    public native int bytes_of_protected_data();

    public native AVSubsampleEncryptionInfo bytes_of_protected_data(int i);

    static {
        Loader.load();
    }

    public AVSubsampleEncryptionInfo() {
        super((Pointer) null);
        allocate();
    }

    public AVSubsampleEncryptionInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVSubsampleEncryptionInfo(Pointer pointer) {
        super(pointer);
    }

    public AVSubsampleEncryptionInfo position(long j) {
        return (AVSubsampleEncryptionInfo) super.position(j);
    }

    public AVSubsampleEncryptionInfo getPointer(long j) {
        return new AVSubsampleEncryptionInfo((Pointer) this).position(this.position + j);
    }
}
