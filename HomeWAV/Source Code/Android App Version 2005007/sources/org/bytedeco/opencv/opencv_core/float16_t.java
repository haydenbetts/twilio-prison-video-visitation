package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class float16_t extends Pointer {
    @ByVal
    @Name({"zero"})
    public static native float16_t _zero();

    private native void allocate();

    private native void allocate(float f);

    @ByVal
    public static native float16_t fromBits(@Cast({"ushort"}) short s);

    @Name({"operator float"})
    public native float asFloat();

    @Cast({"ushort"})
    public native short bits();

    static {
        Loader.load();
    }

    public float16_t(Pointer pointer) {
        super(pointer);
    }

    public float16_t() {
        super((Pointer) null);
        allocate();
    }

    public float16_t(float f) {
        super((Pointer) null);
        allocate(f);
    }
}
