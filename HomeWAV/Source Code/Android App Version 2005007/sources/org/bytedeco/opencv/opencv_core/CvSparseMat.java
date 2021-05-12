package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSparseMat extends AbstractCvSparseMat {
    private native void allocate();

    private native void allocateArray(long j);

    public native void copyToSparseMat(@ByRef SparseMat sparseMat);

    public native int dims();

    public native CvSparseMat dims(int i);

    public native int hashsize();

    public native CvSparseMat hashsize(int i);

    public native Pointer hashtable(int i);

    @Cast({"void**"})
    public native PointerPointer hashtable();

    public native CvSparseMat hashtable(int i, Pointer pointer);

    public native CvSparseMat hashtable(PointerPointer pointerPointer);

    public native int hdr_refcount();

    public native CvSparseMat hdr_refcount(int i);

    public native CvSet heap();

    public native CvSparseMat heap(CvSet cvSet);

    public native int idxoffset();

    public native CvSparseMat idxoffset(int i);

    public native IntPointer refcount();

    public native CvSparseMat refcount(IntPointer intPointer);

    public native int size(int i);

    @MemberGetter
    public native IntPointer size();

    public native CvSparseMat size(int i, int i2);

    public native int type();

    public native CvSparseMat type(int i);

    public native int valoffset();

    public native CvSparseMat valoffset(int i);

    public /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    static {
        Loader.load();
    }

    public CvSparseMat() {
        super((Pointer) null);
        allocate();
    }

    public CvSparseMat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSparseMat(Pointer pointer) {
        super(pointer);
    }

    public CvSparseMat position(long j) {
        return (CvSparseMat) super.position(j);
    }

    public CvSparseMat getPointer(long j) {
        return new CvSparseMat((Pointer) this).position(this.position + j);
    }
}
