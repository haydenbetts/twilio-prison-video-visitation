package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
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
@Name({"cv::Scalar_<int>"})
public class Scalar4i extends IntPointer {
    @ByVal
    public static native Scalar4i all(int i);

    private native void allocate();

    private native void allocate(int i);

    private native void allocate(int i, int i2);

    private native void allocate(int i, int i2, int i3, int i4);

    private native void allocate(@ByRef @Const Scalar4i scalar4i);

    @ByVal
    public native Scalar4i conj();

    @Cast({"bool"})
    public native boolean isReal();

    @ByVal
    public native Scalar4i mul(@ByRef @Const Scalar4i scalar4i);

    @ByVal
    public native Scalar4i mul(@ByRef @Const Scalar4i scalar4i, double d);

    @ByRef
    @Name({"operator ="})
    public native Scalar4i put(@ByRef @Const Scalar4i scalar4i);

    static {
        Loader.load();
    }

    public Scalar4i(Pointer pointer) {
        super(pointer);
    }

    public Scalar4i() {
        super((Pointer) null);
        allocate();
    }

    public Scalar4i(int i, int i2, int i3, int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, i4);
    }

    public Scalar4i(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }

    public Scalar4i(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public Scalar4i(@ByRef @Const Scalar4i scalar4i) {
        super((Pointer) null);
        allocate(scalar4i);
    }
}
