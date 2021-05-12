package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class Cv64suf extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double f();

    public native Cv64suf f(double d);

    @Cast({"int64"})
    public native long i();

    public native Cv64suf i(long j);

    @Cast({"uint64"})
    public native int u();

    public native Cv64suf u(int i);

    static {
        Loader.load();
    }

    public Cv64suf() {
        super((Pointer) null);
        allocate();
    }

    public Cv64suf(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Cv64suf(Pointer pointer) {
        super(pointer);
    }

    public Cv64suf position(long j) {
        return (Cv64suf) super.position(j);
    }

    public Cv64suf getPointer(long j) {
        return new Cv64suf((Pointer) this).position(this.position + j);
    }
}
