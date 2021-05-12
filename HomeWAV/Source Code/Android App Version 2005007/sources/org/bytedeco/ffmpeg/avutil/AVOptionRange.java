package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVOptionRange extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double component_max();

    public native AVOptionRange component_max(double d);

    public native double component_min();

    public native AVOptionRange component_min(double d);

    public native int is_range();

    public native AVOptionRange is_range(int i);

    public native AVOptionRange str(BytePointer bytePointer);

    @Cast({"const char*"})
    public native BytePointer str();

    public native double value_max();

    public native AVOptionRange value_max(double d);

    public native double value_min();

    public native AVOptionRange value_min(double d);

    static {
        Loader.load();
    }

    public AVOptionRange() {
        super((Pointer) null);
        allocate();
    }

    public AVOptionRange(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVOptionRange(Pointer pointer) {
        super(pointer);
    }

    public AVOptionRange position(long j) {
        return (AVOptionRange) super.position(j);
    }

    public AVOptionRange getPointer(long j) {
        return new AVOptionRange((Pointer) this).position(this.position + j);
    }
}
