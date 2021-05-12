package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class RNG_MT19937 extends Pointer {
    private native void allocate();

    private native void allocate(@Cast({"unsigned"}) int i);

    @Cast({"unsigned"})
    @Name({"operator ()"})
    public native int apply();

    @Cast({"unsigned"})
    @Name({"operator ()"})
    public native int apply(@Cast({"unsigned"}) int i);

    @Name({"operator double"})
    public native double asDouble();

    @Name({"operator float"})
    public native float asFloat();

    @Name({"operator int"})
    public native int asInt();

    @Cast({"unsigned"})
    public native int next();

    public native void seed(@Cast({"unsigned"}) int i);

    public native double uniform(double d, double d2);

    public native float uniform(float f, float f2);

    public native int uniform(int i, int i2);

    static {
        Loader.load();
    }

    public RNG_MT19937(Pointer pointer) {
        super(pointer);
    }

    public RNG_MT19937() {
        super((Pointer) null);
        allocate();
    }

    public RNG_MT19937(@Cast({"unsigned"}) int i) {
        super((Pointer) null);
        allocate(i);
    }
}
