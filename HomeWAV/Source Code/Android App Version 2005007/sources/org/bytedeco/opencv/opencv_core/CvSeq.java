package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSeq extends AbstractCvSeq {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvSeq block_max(BytePointer bytePointer);

    public native int delta_elems();

    public native CvSeq delta_elems(int i);

    public native int elem_size();

    public native CvSeq elem_size(int i);

    public native CvSeq first(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock first();

    public native int flags();

    public native CvSeq flags(int i);

    public native CvSeq free_blocks(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock free_blocks();

    public native CvSeq h_next();

    public native CvSeq h_next(CvSeq cvSeq);

    public native CvSeq h_prev();

    public native CvSeq h_prev(CvSeq cvSeq);

    public native int header_size();

    public native CvSeq header_size(int i);

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvSeq ptr(BytePointer bytePointer);

    public native CvMemStorage storage();

    public native CvSeq storage(CvMemStorage cvMemStorage);

    public native int total();

    public native CvSeq total(int i);

    public native CvSeq v_next();

    public native CvSeq v_next(CvSeq cvSeq);

    public native CvSeq v_prev();

    public native CvSeq v_prev(CvSeq cvSeq);

    static {
        Loader.load();
    }

    public CvSeq() {
        super((Pointer) null);
        allocate();
    }

    public CvSeq(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSeq(Pointer pointer) {
        super(pointer);
    }

    public CvSeq position(long j) {
        return (CvSeq) super.position(j);
    }

    public CvSeq getPointer(long j) {
        return new CvSeq((Pointer) this).position(this.position + j);
    }
}
