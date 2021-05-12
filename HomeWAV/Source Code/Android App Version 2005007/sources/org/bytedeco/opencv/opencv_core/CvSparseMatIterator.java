package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSparseMatIterator extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int curidx();

    public native CvSparseMatIterator curidx(int i);

    public native CvSparseMat mat();

    public native CvSparseMatIterator mat(CvSparseMat cvSparseMat);

    public native CvSparseMatIterator node(CvSparseNode cvSparseNode);

    public native CvSparseNode node();

    static {
        Loader.load();
    }

    public CvSparseMatIterator() {
        super((Pointer) null);
        allocate();
    }

    public CvSparseMatIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSparseMatIterator(Pointer pointer) {
        super(pointer);
    }

    public CvSparseMatIterator position(long j) {
        return (CvSparseMatIterator) super.position(j);
    }

    public CvSparseMatIterator getPointer(long j) {
        return new CvSparseMatIterator((Pointer) this).position(this.position + j);
    }
}
