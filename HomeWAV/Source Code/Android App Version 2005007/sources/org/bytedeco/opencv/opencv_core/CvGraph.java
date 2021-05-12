package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvGraph extends AbstractCvGraph {
    private native void allocate();

    private native void allocateArray(long j);

    public native int active_count();

    public native CvGraph active_count(int i);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvGraph block_max(BytePointer bytePointer);

    public native int delta_elems();

    public native CvGraph delta_elems(int i);

    public native CvGraph edges(CvSet cvSet);

    public native CvSet edges();

    public native int elem_size();

    public native CvGraph elem_size(int i);

    public native CvGraph first(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock first();

    public native int flags();

    public native CvGraph flags(int i);

    public native CvGraph free_blocks(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock free_blocks();

    public native CvGraph free_elems(CvSetElem cvSetElem);

    public native CvSetElem free_elems();

    public native CvGraph h_next(CvSeq cvSeq);

    public native CvSeq h_next();

    public native CvGraph h_prev(CvSeq cvSeq);

    public native CvSeq h_prev();

    public native int header_size();

    public native CvGraph header_size(int i);

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvGraph ptr(BytePointer bytePointer);

    public native CvGraph storage(CvMemStorage cvMemStorage);

    public native CvMemStorage storage();

    public native int total();

    public native CvGraph total(int i);

    public native CvGraph v_next(CvSeq cvSeq);

    public native CvSeq v_next();

    public native CvGraph v_prev(CvSeq cvSeq);

    public native CvSeq v_prev();

    static {
        Loader.load();
    }

    public CvGraph() {
        super((Pointer) null);
        allocate();
    }

    public CvGraph(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvGraph(Pointer pointer) {
        super(pointer);
    }

    public CvGraph position(long j) {
        return (CvGraph) super.position(j);
    }

    public CvGraph getPointer(long j) {
        return new CvGraph((Pointer) this).position(this.position + j);
    }
}
