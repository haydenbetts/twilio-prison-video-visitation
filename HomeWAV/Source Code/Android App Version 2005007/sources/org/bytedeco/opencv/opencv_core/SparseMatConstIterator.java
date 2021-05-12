package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
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
import org.bytedeco.opencv.opencv_core.SparseMat;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class SparseMatConstIterator extends Pointer {
    private native void allocate();

    private native void allocate(@Const SparseMat sparseMat);

    private native void allocate(@ByRef @Const SparseMatConstIterator sparseMatConstIterator);

    private native void allocateArray(long j);

    @Cast({"size_t"})
    public native long hashidx();

    public native SparseMatConstIterator hashidx(long j);

    @ByRef
    @Name({"operator ++"})
    public native SparseMatConstIterator increment();

    @ByVal
    @Name({"operator ++"})
    public native SparseMatConstIterator increment(int i);

    @Const
    public native SparseMat m();

    public native SparseMatConstIterator m(SparseMat sparseMat);

    @Const
    public native SparseMat.Node node();

    @Cast({"uchar*"})
    public native BytePointer ptr();

    public native SparseMatConstIterator ptr(BytePointer bytePointer);

    @ByRef
    @Name({"operator ="})
    public native SparseMatConstIterator put(@ByRef @Const SparseMatConstIterator sparseMatConstIterator);

    public native void seekEnd();

    static {
        Loader.load();
    }

    public SparseMatConstIterator(Pointer pointer) {
        super(pointer);
    }

    public SparseMatConstIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public SparseMatConstIterator position(long j) {
        return (SparseMatConstIterator) super.position(j);
    }

    public SparseMatConstIterator getPointer(long j) {
        return new SparseMatConstIterator(this).position(this.position + j);
    }

    public SparseMatConstIterator() {
        super((Pointer) null);
        allocate();
    }

    public SparseMatConstIterator(@Const SparseMat sparseMat) {
        super((Pointer) null);
        allocate(sparseMat);
    }

    public SparseMatConstIterator(@ByRef @Const SparseMatConstIterator sparseMatConstIterator) {
        super((Pointer) null);
        allocate(sparseMatConstIterator);
    }
}
