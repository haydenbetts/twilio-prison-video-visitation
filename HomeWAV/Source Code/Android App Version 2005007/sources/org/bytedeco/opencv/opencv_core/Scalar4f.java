package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::Scalar_<float>"})
public class Scalar4f extends FloatPointer {
    @ByVal
    public static native Scalar4f all(float f);

    private native void allocate();

    private native void allocate(float f);

    private native void allocate(float f, float f2);

    private native void allocate(float f, float f2, float f3, float f4);

    private native void allocate(@ByRef @Const Scalar4f scalar4f);

    @ByVal
    public native Scalar4f conj();

    @Cast({"bool"})
    public native boolean isReal();

    @ByVal
    public native Scalar4f mul(@ByRef @Const Scalar4f scalar4f);

    @ByVal
    public native Scalar4f mul(@ByRef @Const Scalar4f scalar4f, double d);

    @ByRef
    @Name({"operator ="})
    public native Scalar4f put(@ByRef @Const Scalar4f scalar4f);

    static {
        Loader.load();
    }

    public Scalar4f(Pointer pointer) {
        super(pointer);
    }

    public Scalar4f() {
        super((Pointer) null);
        allocate();
    }

    public Scalar4f(float f, float f2, float f3, float f4) {
        super((Pointer) null);
        allocate(f, f2, f3, f4);
    }

    public Scalar4f(float f, float f2) {
        super((Pointer) null);
        allocate(f, f2);
    }

    public Scalar4f(float f) {
        super((Pointer) null);
        allocate(f);
    }

    public Scalar4f(@ByRef @Const Scalar4f scalar4f) {
        super((Pointer) null);
        allocate(scalar4f);
    }
}
