package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class SVD extends Pointer {
    public static final int FULL_UV = 4;
    public static final int MODIFY_A = 1;
    public static final int NO_UV = 2;

    private native void allocate();

    private native void allocate(@ByVal GpuMat gpuMat);

    private native void allocate(@ByVal GpuMat gpuMat, int i);

    private native void allocate(@ByVal Mat mat);

    private native void allocate(@ByVal Mat mat, int i);

    private native void allocate(@ByVal UMat uMat);

    private native void allocate(@ByVal UMat uMat, int i);

    private native void allocateArray(long j);

    public static native void backSubst(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, @ByVal GpuMat gpuMat5);

    public static native void backSubst(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, @ByVal Mat mat5);

    public static native void backSubst(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, @ByVal UMat uMat5);

    public static native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public static native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    public static native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4);

    public static native void compute(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3, @ByVal GpuMat gpuMat4, int i);

    public static native void compute(@ByVal Mat mat, @ByVal Mat mat2);

    public static native void compute(@ByVal Mat mat, @ByVal Mat mat2, int i);

    public static native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4);

    public static native void compute(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3, @ByVal Mat mat4, int i);

    public static native void compute(@ByVal UMat uMat, @ByVal UMat uMat2);

    public static native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    public static native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4);

    public static native void compute(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3, @ByVal UMat uMat4, int i);

    public static native void solveZ(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public static native void solveZ(@ByVal Mat mat, @ByVal Mat mat2);

    public static native void solveZ(@ByVal UMat uMat, @ByVal UMat uMat2);

    @ByRef
    @Name({"operator ()"})
    public native SVD apply(@ByVal GpuMat gpuMat);

    @ByRef
    @Name({"operator ()"})
    public native SVD apply(@ByVal GpuMat gpuMat, int i);

    @ByRef
    @Name({"operator ()"})
    public native SVD apply(@ByVal Mat mat);

    @ByRef
    @Name({"operator ()"})
    public native SVD apply(@ByVal Mat mat, int i);

    @ByRef
    @Name({"operator ()"})
    public native SVD apply(@ByVal UMat uMat);

    @ByRef
    @Name({"operator ()"})
    public native SVD apply(@ByVal UMat uMat, int i);

    public native void backSubst(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void backSubst(@ByVal Mat mat, @ByVal Mat mat2);

    public native void backSubst(@ByVal UMat uMat, @ByVal UMat uMat2);

    @ByRef
    public native Mat u();

    public native SVD u(Mat mat);

    @ByRef
    public native Mat vt();

    public native SVD vt(Mat mat);

    @ByRef
    public native Mat w();

    public native SVD w(Mat mat);

    static {
        Loader.load();
    }

    public SVD(Pointer pointer) {
        super(pointer);
    }

    public SVD(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SVD position(long j) {
        return (SVD) super.position(j);
    }

    public SVD getPointer(long j) {
        return new SVD((Pointer) this).position(this.position + j);
    }

    public SVD() {
        super((Pointer) null);
        allocate();
    }

    public SVD(@ByVal Mat mat, int i) {
        super((Pointer) null);
        allocate(mat, i);
    }

    public SVD(@ByVal Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }

    public SVD(@ByVal UMat uMat, int i) {
        super((Pointer) null);
        allocate(uMat, i);
    }

    public SVD(@ByVal UMat uMat) {
        super((Pointer) null);
        allocate(uMat);
    }

    public SVD(@ByVal GpuMat gpuMat, int i) {
        super((Pointer) null);
        allocate(gpuMat, i);
    }

    public SVD(@ByVal GpuMat gpuMat) {
        super((Pointer) null);
        allocate(gpuMat);
    }
}
