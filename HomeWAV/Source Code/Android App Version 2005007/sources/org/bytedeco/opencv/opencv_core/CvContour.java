package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvContour extends CvSeq {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvContour block_max(BytePointer bytePointer);

    public native int color();

    public native CvContour color(int i);

    public native int delta_elems();

    public native CvContour delta_elems(int i);

    public native int elem_size();

    public native CvContour elem_size(int i);

    public native CvContour first(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock first();

    public native int flags();

    public native CvContour flags(int i);

    public native CvContour free_blocks(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock free_blocks();

    public native CvContour h_next(CvSeq cvSeq);

    public native CvSeq h_next();

    public native CvContour h_prev(CvSeq cvSeq);

    public native CvSeq h_prev();

    public native int header_size();

    public native CvContour header_size(int i);

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvContour ptr(BytePointer bytePointer);

    public native CvContour rect(CvRect cvRect);

    @ByRef
    public native CvRect rect();

    public native int reserved(int i);

    @MemberGetter
    public native IntPointer reserved();

    public native CvContour reserved(int i, int i2);

    public native CvContour storage(CvMemStorage cvMemStorage);

    public native CvMemStorage storage();

    public native int total();

    public native CvContour total(int i);

    public native CvContour v_next(CvSeq cvSeq);

    public native CvSeq v_next();

    public native CvContour v_prev(CvSeq cvSeq);

    public native CvSeq v_prev();

    static {
        Loader.load();
    }

    public CvContour() {
        super((Pointer) null);
        allocate();
    }

    public CvContour(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvContour(Pointer pointer) {
        super(pointer);
    }

    public CvContour position(long j) {
        return (CvContour) super.position(j);
    }

    public CvContour getPointer(long j) {
        return new CvContour((Pointer) this).position(this.position + j);
    }
}
