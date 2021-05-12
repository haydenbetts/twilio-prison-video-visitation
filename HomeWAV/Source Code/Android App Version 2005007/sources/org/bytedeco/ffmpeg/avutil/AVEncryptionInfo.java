package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVEncryptionInfo extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint32_t"})
    public native int crypt_byte_block();

    public native AVEncryptionInfo crypt_byte_block(int i);

    public native AVEncryptionInfo iv(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer iv();

    @Cast({"uint32_t"})
    public native int iv_size();

    public native AVEncryptionInfo iv_size(int i);

    public native AVEncryptionInfo key_id(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer key_id();

    @Cast({"uint32_t"})
    public native int key_id_size();

    public native AVEncryptionInfo key_id_size(int i);

    @Cast({"uint32_t"})
    public native int scheme();

    public native AVEncryptionInfo scheme(int i);

    @Cast({"uint32_t"})
    public native int skip_byte_block();

    public native AVEncryptionInfo skip_byte_block(int i);

    @Cast({"uint32_t"})
    public native int subsample_count();

    public native AVEncryptionInfo subsample_count(int i);

    public native AVEncryptionInfo subsamples(AVSubsampleEncryptionInfo aVSubsampleEncryptionInfo);

    public native AVSubsampleEncryptionInfo subsamples();

    static {
        Loader.load();
    }

    public AVEncryptionInfo() {
        super((Pointer) null);
        allocate();
    }

    public AVEncryptionInfo(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVEncryptionInfo(Pointer pointer) {
        super(pointer);
    }

    public AVEncryptionInfo position(long j) {
        return (AVEncryptionInfo) super.position(j);
    }

    public AVEncryptionInfo getPointer(long j) {
        return new AVEncryptionInfo((Pointer) this).position(this.position + j);
    }
}
