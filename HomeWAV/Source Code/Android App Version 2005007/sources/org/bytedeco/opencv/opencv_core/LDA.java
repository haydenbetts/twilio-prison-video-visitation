package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class LDA extends Pointer {
    private native void allocate();

    private native void allocate(int i);

    private native void allocate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    private native void allocate(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i);

    private native void allocate(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    private native void allocate(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i);

    private native void allocate(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    private native void allocate(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i);

    private native void allocate(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    private native void allocate(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i);

    private native void allocate(@ByVal MatVector matVector, @ByVal Mat mat);

    private native void allocate(@ByVal MatVector matVector, @ByVal Mat mat, int i);

    private native void allocate(@ByVal MatVector matVector, @ByVal UMat uMat);

    private native void allocate(@ByVal MatVector matVector, @ByVal UMat uMat, int i);

    private native void allocate(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    private native void allocate(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i);

    private native void allocate(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    private native void allocate(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i);

    private native void allocate(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    private native void allocate(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i);

    @ByVal
    public static native Mat subspaceProject(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @ByVal
    public static native Mat subspaceProject(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @ByVal
    public static native Mat subspaceProject(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    @ByVal
    public static native Mat subspaceReconstruct(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, @ByVal GpuMat gpuMat3);

    @ByVal
    public static native Mat subspaceReconstruct(@ByVal Mat mat, @ByVal Mat mat2, @ByVal Mat mat3);

    @ByVal
    public static native Mat subspaceReconstruct(@ByVal UMat uMat, @ByVal UMat uMat2, @ByVal UMat uMat3);

    public native void compute(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat);

    public native void compute(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat);

    public native void compute(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat);

    public native void compute(@ByVal MatVector matVector, @ByVal GpuMat gpuMat);

    public native void compute(@ByVal MatVector matVector, @ByVal Mat mat);

    public native void compute(@ByVal MatVector matVector, @ByVal UMat uMat);

    public native void compute(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat);

    public native void compute(@ByVal UMatVector uMatVector, @ByVal Mat mat);

    public native void compute(@ByVal UMatVector uMatVector, @ByVal UMat uMat);

    @ByVal
    public native Mat eigenvalues();

    @ByVal
    public native Mat eigenvectors();

    public native void load(@opencv_core.Str String str);

    public native void load(@opencv_core.Str BytePointer bytePointer);

    public native void load(@ByRef @Const FileStorage fileStorage);

    @ByVal
    public native Mat project(@ByVal GpuMat gpuMat);

    @ByVal
    public native Mat project(@ByVal Mat mat);

    @ByVal
    public native Mat project(@ByVal UMat uMat);

    @ByVal
    public native Mat reconstruct(@ByVal GpuMat gpuMat);

    @ByVal
    public native Mat reconstruct(@ByVal Mat mat);

    @ByVal
    public native Mat reconstruct(@ByVal UMat uMat);

    public native void save(@opencv_core.Str String str);

    public native void save(@opencv_core.Str BytePointer bytePointer);

    public native void save(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public LDA(Pointer pointer) {
        super(pointer);
    }

    public LDA(int i) {
        super((Pointer) null);
        allocate(i);
    }

    public LDA() {
        super((Pointer) null);
        allocate();
    }

    public LDA(@ByVal MatVector matVector, @ByVal Mat mat, int i) {
        super((Pointer) null);
        allocate(matVector, mat, i);
    }

    public LDA(@ByVal MatVector matVector, @ByVal Mat mat) {
        super((Pointer) null);
        allocate(matVector, mat);
    }

    public LDA(@ByVal UMatVector uMatVector, @ByVal Mat mat, int i) {
        super((Pointer) null);
        allocate(uMatVector, mat, i);
    }

    public LDA(@ByVal UMatVector uMatVector, @ByVal Mat mat) {
        super((Pointer) null);
        allocate(uMatVector, mat);
    }

    public LDA(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat, int i) {
        super((Pointer) null);
        allocate(gpuMatVector, mat, i);
    }

    public LDA(@ByVal GpuMatVector gpuMatVector, @ByVal Mat mat) {
        super((Pointer) null);
        allocate(gpuMatVector, mat);
    }

    public LDA(@ByVal MatVector matVector, @ByVal UMat uMat, int i) {
        super((Pointer) null);
        allocate(matVector, uMat, i);
    }

    public LDA(@ByVal MatVector matVector, @ByVal UMat uMat) {
        super((Pointer) null);
        allocate(matVector, uMat);
    }

    public LDA(@ByVal UMatVector uMatVector, @ByVal UMat uMat, int i) {
        super((Pointer) null);
        allocate(uMatVector, uMat, i);
    }

    public LDA(@ByVal UMatVector uMatVector, @ByVal UMat uMat) {
        super((Pointer) null);
        allocate(uMatVector, uMat);
    }

    public LDA(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat, int i) {
        super((Pointer) null);
        allocate(gpuMatVector, uMat, i);
    }

    public LDA(@ByVal GpuMatVector gpuMatVector, @ByVal UMat uMat) {
        super((Pointer) null);
        allocate(gpuMatVector, uMat);
    }

    public LDA(@ByVal MatVector matVector, @ByVal GpuMat gpuMat, int i) {
        super((Pointer) null);
        allocate(matVector, gpuMat, i);
    }

    public LDA(@ByVal MatVector matVector, @ByVal GpuMat gpuMat) {
        super((Pointer) null);
        allocate(matVector, gpuMat);
    }

    public LDA(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat, int i) {
        super((Pointer) null);
        allocate(uMatVector, gpuMat, i);
    }

    public LDA(@ByVal UMatVector uMatVector, @ByVal GpuMat gpuMat) {
        super((Pointer) null);
        allocate(uMatVector, gpuMat);
    }

    public LDA(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat, int i) {
        super((Pointer) null);
        allocate(gpuMatVector, gpuMat, i);
    }

    public LDA(@ByVal GpuMatVector gpuMatVector, @ByVal GpuMat gpuMat) {
        super((Pointer) null);
        allocate(gpuMatVector, gpuMat);
    }
}
