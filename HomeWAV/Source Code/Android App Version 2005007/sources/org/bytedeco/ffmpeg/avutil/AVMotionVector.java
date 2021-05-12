package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVMotionVector extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native AVMotionVector dst_x(short s);

    public native short dst_x();

    public native AVMotionVector dst_y(short s);

    public native short dst_y();

    @Cast({"uint64_t"})
    public native long flags();

    public native AVMotionVector flags(long j);

    @Cast({"uint8_t"})
    public native byte h();

    public native AVMotionVector h(byte b);

    public native AVMotionVector motion_scale(short s);

    @Cast({"uint16_t"})
    public native short motion_scale();

    public native int motion_x();

    public native AVMotionVector motion_x(int i);

    public native int motion_y();

    public native AVMotionVector motion_y(int i);

    public native int source();

    public native AVMotionVector source(int i);

    public native AVMotionVector src_x(short s);

    public native short src_x();

    public native AVMotionVector src_y(short s);

    public native short src_y();

    @Cast({"uint8_t"})
    public native byte w();

    public native AVMotionVector w(byte b);

    static {
        Loader.load();
    }

    public AVMotionVector() {
        super((Pointer) null);
        allocate();
    }

    public AVMotionVector(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVMotionVector(Pointer pointer) {
        super(pointer);
    }

    public AVMotionVector position(long j) {
        return (AVMotionVector) super.position(j);
    }

    public AVMotionVector getPointer(long j) {
        return new AVMotionVector((Pointer) this).position(this.position + j);
    }
}
