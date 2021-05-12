package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSeqReader extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvSeqBlock block();

    public native CvSeqReader block(CvSeqBlock cvSeqBlock);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvSeqReader block_max(BytePointer bytePointer);

    @Cast({"schar*"})
    public native BytePointer block_min();

    public native CvSeqReader block_min(BytePointer bytePointer);

    public native int delta_index();

    public native CvSeqReader delta_index(int i);

    public native int header_size();

    public native CvSeqReader header_size(int i);

    @Cast({"schar*"})
    public native BytePointer prev_elem();

    public native CvSeqReader prev_elem(BytePointer bytePointer);

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvSeqReader ptr(BytePointer bytePointer);

    public native CvSeq seq();

    public native CvSeqReader seq(CvSeq cvSeq);

    static {
        Loader.load();
    }

    public CvSeqReader() {
        super((Pointer) null);
        allocate();
    }

    public CvSeqReader(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSeqReader(Pointer pointer) {
        super(pointer);
    }

    public CvSeqReader position(long j) {
        return (CvSeqReader) super.position(j);
    }

    public CvSeqReader getPointer(long j) {
        return new CvSeqReader((Pointer) this).position(this.position + j);
    }
}
