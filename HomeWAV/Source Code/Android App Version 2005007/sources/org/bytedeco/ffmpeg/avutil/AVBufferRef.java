package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVBufferRef extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVBuffer buffer();

    public native AVBufferRef buffer(AVBuffer aVBuffer);

    public native AVBufferRef data(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer data();

    public native int size();

    public native AVBufferRef size(int i);

    static {
        Loader.load();
    }

    public AVBufferRef() {
        super((Pointer) null);
        allocate();
    }

    public AVBufferRef(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVBufferRef(Pointer pointer) {
        super(pointer);
    }

    public AVBufferRef position(long j) {
        return (AVBufferRef) super.position(j);
    }

    public AVBufferRef getPointer(long j) {
        return new AVBufferRef((Pointer) this).position(this.position + j);
    }
}
