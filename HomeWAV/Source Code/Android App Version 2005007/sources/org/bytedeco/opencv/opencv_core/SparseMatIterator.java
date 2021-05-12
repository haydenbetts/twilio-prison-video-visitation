package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.SparseMat;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class SparseMatIterator extends SparseMatConstIterator {
    private native void allocate();

    private native void allocate(SparseMat sparseMat);

    private native void allocate(@ByRef @Const SparseMatIterator sparseMatIterator);

    private native void allocateArray(long j);

    @ByRef
    @Name({"operator ++"})
    public native SparseMatIterator increment();

    @ByVal
    @Name({"operator ++"})
    public native SparseMatIterator increment(int i);

    public native SparseMat.Node node();

    @ByRef
    @Name({"operator ="})
    public native SparseMatIterator put(@ByRef @Const SparseMatIterator sparseMatIterator);

    static {
        Loader.load();
    }

    public SparseMatIterator(Pointer pointer) {
        super(pointer);
    }

    public SparseMatIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SparseMatIterator position(long j) {
        return (SparseMatIterator) super.position(j);
    }

    public SparseMatIterator getPointer(long j) {
        return new SparseMatIterator(this).position(this.position + j);
    }

    public SparseMatIterator() {
        super((Pointer) null);
        allocate();
    }

    public SparseMatIterator(SparseMat sparseMat) {
        super((Pointer) null);
        allocate(sparseMat);
    }

    public SparseMatIterator(@ByRef @Const SparseMatIterator sparseMatIterator) {
        super((Pointer) null);
        allocate(sparseMatIterator);
    }
}
