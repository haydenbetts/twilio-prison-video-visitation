package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVFifoBuffer extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVFifoBuffer buffer(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer buffer();

    public native AVFifoBuffer end(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer end();

    @Cast({"uint32_t"})
    public native int rndx();

    public native AVFifoBuffer rndx(int i);

    public native AVFifoBuffer rptr(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer rptr();

    @Cast({"uint32_t"})
    public native int wndx();

    public native AVFifoBuffer wndx(int i);

    public native AVFifoBuffer wptr(BytePointer bytePointer);

    @Cast({"uint8_t*"})
    public native BytePointer wptr();

    static {
        Loader.load();
    }

    public AVFifoBuffer() {
        super((Pointer) null);
        allocate();
    }

    public AVFifoBuffer(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVFifoBuffer(Pointer pointer) {
        super(pointer);
    }

    public AVFifoBuffer position(long j) {
        return (AVFifoBuffer) super.position(j);
    }

    public AVFifoBuffer getPointer(long j) {
        return new AVFifoBuffer((Pointer) this).position(this.position + j);
    }
}
