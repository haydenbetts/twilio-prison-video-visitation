package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class Cv32suf extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native float f();

    public native Cv32suf f(float f);

    public native int i();

    public native Cv32suf i(int i);

    @Cast({"unsigned"})
    public native int u();

    public native Cv32suf u(int i);

    static {
        Loader.load();
    }

    public Cv32suf() {
        super((Pointer) null);
        allocate();
    }

    public Cv32suf(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Cv32suf(Pointer pointer) {
        super(pointer);
    }

    public Cv32suf position(long j) {
        return (Cv32suf) super.position(j);
    }

    public Cv32suf getPointer(long j) {
        return new Cv32suf((Pointer) this).position(this.position + j);
    }
}
