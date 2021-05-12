package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class Cv16suf extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native Cv16suf i(short s);

    public native short i();

    public native Cv16suf u(short s);

    @Cast({"ushort"})
    public native short u();

    static {
        Loader.load();
    }

    public Cv16suf() {
        super((Pointer) null);
        allocate();
    }

    public Cv16suf(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Cv16suf(Pointer pointer) {
        super(pointer);
    }

    public Cv16suf position(long j) {
        return (Cv16suf) super.position(j);
    }

    public Cv16suf getPointer(long j) {
        return new Cv16suf((Pointer) this).position(this.position + j);
    }
}
