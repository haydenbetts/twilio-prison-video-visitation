package org.bytedeco.opencv.opencv_core;

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
@Name({"cv::Scalar_<double>"})
public class Scalar extends AbstractScalar {
    @ByVal
    public static native Scalar all(double d);

    private native void allocate();

    private native void allocate(double d);

    private native void allocate(double d, double d2);

    private native void allocate(double d, double d2, double d3, double d4);

    private native void allocate(@ByRef @Const Scalar scalar);

    @ByVal
    public native Scalar conj();

    @Cast({"bool"})
    public native boolean isReal();

    @ByVal
    public native Scalar mul(@ByRef @Const Scalar scalar);

    @ByVal
    public native Scalar mul(@ByRef @Const Scalar scalar, double d);

    @ByRef
    @Name({"operator ="})
    public native Scalar put(@ByRef @Const Scalar scalar);

    static {
        Loader.load();
    }

    public Scalar(Pointer pointer) {
        super(pointer);
    }

    public Scalar() {
        super((Pointer) null);
        allocate();
    }

    public Scalar(double d, double d2, double d3, double d4) {
        super((Pointer) null);
        allocate(d, d2, d3, d4);
    }

    public Scalar(double d, double d2) {
        super((Pointer) null);
        allocate(d, d2);
    }

    public Scalar(double d) {
        super((Pointer) null);
        allocate(d);
    }

    public Scalar(@ByRef @Const Scalar scalar) {
        super((Pointer) null);
        allocate(scalar);
    }
}
