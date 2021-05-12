package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class RNG extends Pointer {
    public static final int NORMAL = 1;
    public static final int UNIFORM = 0;

    private native void allocate();

    private native void allocate(@Cast({"uint64"}) int i);

    @Name({"fill"})
    public native void _fill(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @Name({"fill"})
    public native void _fill(@ByVal GpuMat gpuMat, int i, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @Cast({"bool"}) boolean z);

    @Name({"fill"})
    public native void _fill(@ByVal Mat mat, int i, @ByVal Mat mat2, @ByVal Mat mat3);

    @Name({"fill"})
    public native void _fill(@ByVal Mat mat, int i, @ByVal Mat mat2, @ByVal Mat mat3, @Cast({"bool"}) boolean z);

    @Name({"fill"})
    public native void _fill(@ByVal UMat uMat, int i, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @Name({"fill"})
    public native void _fill(@ByVal UMat uMat, int i, @ByVal UMat uMat2, @ByVal UMat uMat3, @Cast({"bool"}) boolean z);

    @Cast({"unsigned"})
    @Name({"operator ()"})
    public native int apply();

    @Cast({"unsigned"})
    @Name({"operator ()"})
    public native int apply(@Cast({"unsigned"}) int i);

    @Cast({"uchar"})
    @Name({"operator uchar"})
    public native byte asByte();

    @Name({"operator double"})
    public native double asDouble();

    @Name({"operator float"})
    public native float asFloat();

    @Cast({"unsigned"})
    @Name({"operator unsigned"})
    public native int asInt();

    @Cast({"ushort"})
    @Name({"operator ushort"})
    public native short asShort();

    @Cast({"bool"})
    @Name({"operator =="})
    public native boolean equals(@ByRef @Const RNG rng);

    public native double gaussian(double d);

    @Cast({"unsigned"})
    public native int next();

    @Cast({"uint64"})
    public native int state();

    public native RNG state(int i);

    public native double uniform(double d, double d2);

    public native float uniform(float f, float f2);

    public native int uniform(int i, int i2);

    static {
        Loader.load();
    }

    public RNG(Pointer pointer) {
        super(pointer);
    }

    public RNG() {
        super((Pointer) null);
        allocate();
    }

    public RNG(@Cast({"uint64"}) int i) {
        super((Pointer) null);
        allocate(i);
    }
}
