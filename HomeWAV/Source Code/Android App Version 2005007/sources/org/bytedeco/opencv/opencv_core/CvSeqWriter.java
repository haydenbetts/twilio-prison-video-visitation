package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSeqWriter extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvSeqBlock block();

    public native CvSeqWriter block(CvSeqBlock cvSeqBlock);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvSeqWriter block_max(BytePointer bytePointer);

    @Cast({"schar*"})
    public native BytePointer block_min();

    public native CvSeqWriter block_min(BytePointer bytePointer);

    public native int header_size();

    public native CvSeqWriter header_size(int i);

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvSeqWriter ptr(BytePointer bytePointer);

    public native CvSeq seq();

    public native CvSeqWriter seq(CvSeq cvSeq);

    static {
        Loader.load();
    }

    public CvSeqWriter() {
        super((Pointer) null);
        allocate();
    }

    public CvSeqWriter(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSeqWriter(Pointer pointer) {
        super(pointer);
    }

    public CvSeqWriter position(long j) {
        return (CvSeqWriter) super.position(j);
    }

    public CvSeqWriter getPointer(long j) {
        return new CvSeqWriter((Pointer) this).position(this.position + j);
    }
}
