package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSet extends AbstractCvSet {
    private native void allocate();

    private native void allocateArray(long j);

    public native int active_count();

    public native CvSet active_count(int i);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvSet block_max(BytePointer bytePointer);

    public native int delta_elems();

    public native CvSet delta_elems(int i);

    public native int elem_size();

    public native CvSet elem_size(int i);

    public native CvSeqBlock first();

    public native CvSet first(CvSeqBlock cvSeqBlock);

    public native int flags();

    public native CvSet flags(int i);

    public native CvSeqBlock free_blocks();

    public native CvSet free_blocks(CvSeqBlock cvSeqBlock);

    public native CvSet free_elems(CvSetElem cvSetElem);

    public native CvSetElem free_elems();

    public native CvSeq h_next();

    public native CvSet h_next(CvSeq cvSeq);

    public native CvSeq h_prev();

    public native CvSet h_prev(CvSeq cvSeq);

    public native int header_size();

    public native CvSet header_size(int i);

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvSet ptr(BytePointer bytePointer);

    public native CvMemStorage storage();

    public native CvSet storage(CvMemStorage cvMemStorage);

    public native int total();

    public native CvSet total(int i);

    public native CvSeq v_next();

    public native CvSet v_next(CvSeq cvSeq);

    public native CvSeq v_prev();

    public native CvSet v_prev(CvSeq cvSeq);

    static {
        Loader.load();
    }

    public CvSet() {
        super((Pointer) null);
        allocate();
    }

    public CvSet(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSet(Pointer pointer) {
        super(pointer);
    }

    public CvSet position(long j) {
        return (CvSet) super.position(j);
    }

    public CvSet getPointer(long j) {
        return new CvSet((Pointer) this).position(this.position + j);
    }
}
