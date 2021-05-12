package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class PCA extends Pointer {
    public static final int DATA_AS_COL = 1;
    public static final int DATA_AS_ROW = 0;
    public static final int USE_AVG = 2;

    private native void allocate();

    private native void allocate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    private native void allocate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d);

    private native void allocate(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    private native void allocate(@ByVal Mat mat, @ByVal Mat mat2, int i);

    private native void allocate(@ByVal Mat mat, @ByVal Mat mat2, int i, double d);

    private native void allocate(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    private native void allocate(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    private native void allocate(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d);

    private native void allocate(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    private native void allocateArray(long j);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal Mat mat, @ByVal Mat mat2, int i);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal Mat mat, @ByVal Mat mat2, int i, double d);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal UMat uMat, @ByVal UMat uMat2, int i);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d);

    @ByRef
    @Name({"operator ()"})
    public native PCA apply(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2);

    @ByVal
    public native Mat backProject(@ByVal GpuMat gpuMat);

    @ByVal
    public native Mat backProject(@ByVal Mat mat);

    @ByVal
    public native Mat backProject(@ByVal UMat uMat);

    public native void backProject(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void backProject(@ByVal Mat mat, @ByVal Mat mat2);

    public native void backProject(@ByVal UMat uMat, @ByVal UMat uMat2);

    @ByRef
    public native Mat eigenvalues();

    public native PCA eigenvalues(Mat mat);

    @ByRef
    public native Mat eigenvectors();

    public native PCA eigenvectors(Mat mat);

    @ByRef
    public native Mat mean();

    public native PCA mean(Mat mat);

    @ByVal
    public native Mat project(@ByVal GpuMat gpuMat);

    @ByVal
    public native Mat project(@ByVal Mat mat);

    @ByVal
    public native Mat project(@ByVal UMat uMat);

    public native void project(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2);

    public native void project(@ByVal Mat mat, @ByVal Mat mat2);

    public native void project(@ByVal UMat uMat, @ByVal UMat uMat2);

    public native void read(@ByRef @Const FileNode fileNode);

    public native void write(@ByRef FileStorage fileStorage);

    static {
        Loader.load();
    }

    public PCA(Pointer pointer) {
        super(pointer);
    }

    public PCA(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public PCA position(long j) {
        return (PCA) super.position(j);
    }

    public PCA getPointer(long j) {
        return new PCA((Pointer) this).position(this.position + j);
    }

    public PCA() {
        super((Pointer) null);
        allocate();
    }

    public PCA(@ByVal Mat mat, @ByVal Mat mat2, int i, int i2) {
        super((Pointer) null);
        allocate(mat, mat2, i, i2);
    }

    public PCA(@ByVal Mat mat, @ByVal Mat mat2, int i) {
        super((Pointer) null);
        allocate(mat, mat2, i);
    }

    public PCA(@ByVal UMat uMat, @ByVal UMat uMat2, int i, int i2) {
        super((Pointer) null);
        allocate(uMat, uMat2, i, i2);
    }

    public PCA(@ByVal UMat uMat, @ByVal UMat uMat2, int i) {
        super((Pointer) null);
        allocate(uMat, uMat2, i);
    }

    public PCA(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, int i2) {
        super((Pointer) null);
        allocate(gpuMat, gpuMat2, i, i2);
    }

    public PCA(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i) {
        super((Pointer) null);
        allocate(gpuMat, gpuMat2, i);
    }

    public PCA(@ByVal Mat mat, @ByVal Mat mat2, int i, double d) {
        super((Pointer) null);
        allocate(mat, mat2, i, d);
    }

    public PCA(@ByVal UMat uMat, @ByVal UMat uMat2, int i, double d) {
        super((Pointer) null);
        allocate(uMat, uMat2, i, d);
    }

    public PCA(@ByVal GpuMat gpuMat, @ByVal GpuMat gpuMat2, int i, double d) {
        super((Pointer) null);
        allocate(gpuMat, gpuMat2, i, d);
    }
}
