package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit = {avutil.class})
public class AVSphericalMapping extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"uint32_t"})
    public native int bound_bottom();

    public native AVSphericalMapping bound_bottom(int i);

    @Cast({"uint32_t"})
    public native int bound_left();

    public native AVSphericalMapping bound_left(int i);

    @Cast({"uint32_t"})
    public native int bound_right();

    public native AVSphericalMapping bound_right(int i);

    @Cast({"uint32_t"})
    public native int bound_top();

    public native AVSphericalMapping bound_top(int i);

    @Cast({"uint32_t"})
    public native int padding();

    public native AVSphericalMapping padding(int i);

    public native int pitch();

    public native AVSphericalMapping pitch(int i);

    @Cast({"AVSphericalProjection"})
    public native int projection();

    public native AVSphericalMapping projection(int i);

    public native int roll();

    public native AVSphericalMapping roll(int i);

    public native int yaw();

    public native AVSphericalMapping yaw(int i);

    static {
        Loader.load();
    }

    public AVSphericalMapping() {
        super((Pointer) null);
        allocate();
    }

    public AVSphericalMapping(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public AVSphericalMapping(Pointer pointer) {
        super(pointer);
    }

    public AVSphericalMapping position(long j) {
        return (AVSphericalMapping) super.position(j);
    }

    public AVSphericalMapping getPointer(long j) {
        return new AVSphericalMapping((Pointer) this).position(this.position + j);
    }
}
