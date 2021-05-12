package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvChain extends CvSeq {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvChain block_max(BytePointer bytePointer);

    public native int delta_elems();

    public native CvChain delta_elems(int i);

    public native int elem_size();

    public native CvChain elem_size(int i);

    public native CvChain first(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock first();

    public native int flags();

    public native CvChain flags(int i);

    public native CvChain free_blocks(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock free_blocks();

    public native CvChain h_next(CvSeq cvSeq);

    public native CvSeq h_next();

    public native CvChain h_prev(CvSeq cvSeq);

    public native CvSeq h_prev();

    public native int header_size();

    public native CvChain header_size(int i);

    public native CvChain origin(CvPoint cvPoint);

    @ByRef
    public native CvPoint origin();

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvChain ptr(BytePointer bytePointer);

    public native CvChain storage(CvMemStorage cvMemStorage);

    public native CvMemStorage storage();

    public native int total();

    public native CvChain total(int i);

    public native CvChain v_next(CvSeq cvSeq);

    public native CvSeq v_next();

    public native CvChain v_prev(CvSeq cvSeq);

    public native CvSeq v_prev();

    static {
        Loader.load();
    }

    public CvChain() {
        super((Pointer) null);
        allocate();
    }

    public CvChain(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvChain(Pointer pointer) {
        super(pointer);
    }

    public CvChain position(long j) {
        return (CvChain) super.position(j);
    }

    public CvChain getPointer(long j) {
        return new CvChain((Pointer) this).position(this.position + j);
    }
}
