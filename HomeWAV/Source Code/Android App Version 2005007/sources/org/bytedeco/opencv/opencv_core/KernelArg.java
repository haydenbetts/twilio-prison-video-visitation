package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv::ocl")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class KernelArg extends Pointer {
    public static final int CONSTANT = 8;
    public static final int LOCAL = 1;
    public static final int NO_SIZE = 256;
    public static final int PTR_ONLY = 16;
    public static final int READ_ONLY = 2;
    public static final int READ_WRITE = 6;
    public static final int WRITE_ONLY = 4;

    @ByVal
    public static native KernelArg Constant(@ByRef @Const Mat mat);

    @ByVal
    public static native KernelArg Local(@Cast({"size_t"}) long j);

    @ByVal
    public static native KernelArg PtrReadOnly(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg PtrReadWrite(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg PtrWriteOnly(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg ReadOnly(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg ReadOnly(@ByRef @Const UMat uMat, int i, int i2);

    @ByVal
    public static native KernelArg ReadOnlyNoSize(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg ReadOnlyNoSize(@ByRef @Const UMat uMat, int i, int i2);

    @ByVal
    public static native KernelArg ReadWrite(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg ReadWrite(@ByRef @Const UMat uMat, int i, int i2);

    @ByVal
    public static native KernelArg ReadWriteNoSize(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg ReadWriteNoSize(@ByRef @Const UMat uMat, int i, int i2);

    @ByVal
    public static native KernelArg WriteOnly(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg WriteOnly(@ByRef @Const UMat uMat, int i, int i2);

    @ByVal
    public static native KernelArg WriteOnlyNoSize(@ByRef @Const UMat uMat);

    @ByVal
    public static native KernelArg WriteOnlyNoSize(@ByRef @Const UMat uMat, int i, int i2);

    private native void allocate();

    private native void allocate(int i, UMat uMat);

    private native void allocate(int i, UMat uMat, int i2, int i3, @Const Pointer pointer, @Cast({"size_t"}) long j);

    private native void allocateArray(long j);

    public native int flags();

    public native KernelArg flags(int i);

    public native int iwscale();

    public native KernelArg iwscale(int i);

    public native KernelArg m(UMat uMat);

    public native UMat m();

    @Const
    public native Pointer obj();

    public native KernelArg obj(Pointer pointer);

    @Cast({"size_t"})
    public native long sz();

    public native KernelArg sz(long j);

    public native int wscale();

    public native KernelArg wscale(int i);

    static {
        Loader.load();
    }

    public KernelArg(Pointer pointer) {
        super(pointer);
    }

    public KernelArg(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public KernelArg position(long j) {
        return (KernelArg) super.position(j);
    }

    public KernelArg getPointer(long j) {
        return new KernelArg((Pointer) this).position(this.position + j);
    }

    public KernelArg(int i, UMat uMat, int i2, int i3, @Const Pointer pointer, @Cast({"size_t"}) long j) {
        super((Pointer) null);
        allocate(i, uMat, i2, i3, pointer, j);
    }

    public KernelArg(int i, UMat uMat) {
        super((Pointer) null);
        allocate(i, uMat);
    }

    public KernelArg() {
        super((Pointer) null);
        allocate();
    }
}
