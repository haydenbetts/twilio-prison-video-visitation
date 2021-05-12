package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVEncryptionInitInfo extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVEncryptionInitInfo data(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer data();

    @Cast({"uint32_t"})
    public native int data_size();

    public native AVEncryptionInitInfo data_size(int i);

    @Cast({"uint32_t"})
    public native int key_id_size();

    public native AVEncryptionInitInfo key_id_size(int i);

    public native AVEncryptionInitInfo key_ids(int i, BytePointer bytePointer);

    public native AVEncryptionInitInfo key_ids(PointerPointer pointerPointer);

    @Cast({"uint8_t*"})
    public native BytePointer key_ids(int i);

    @Cast({"uint8_t**"})
    public native PointerPointer key_ids();

    public native AVEncryptionInitInfo next();

    public native AVEncryptionInitInfo next(AVEncryptionInitInfo aVEncryptionInitInfo);

    @Cast({"uint32_t"})
    public native int num_key_ids();

    public native AVEncryptionInitInfo num_key_ids(int i);

    public native AVEncryptionInitInfo system_id(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer system_id();

    @Cast({"uint32_t"})
    public native int system_id_size();

    public native AVEncryptionInitInfo system_id_size(int i);

    static {
        Loader.load();
    }

    public AVEncryptionInitInfo() {
        super((Pointer) null);
        allocate();
    }

    public AVEncryptionInitInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVEncryptionInitInfo(Pointer pointer) {
        super(pointer);
    }

    public AVEncryptionInitInfo position(long j) {
        return (AVEncryptionInitInfo) super.position(j);
    }

    public AVEncryptionInitInfo getPointer(long j) {
        return new AVEncryptionInitInfo((Pointer) this).position(this.position + j);
    }
}
