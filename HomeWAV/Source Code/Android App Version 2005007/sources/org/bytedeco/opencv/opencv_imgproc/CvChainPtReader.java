package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.opencv_core.CvSeqBlock;
import org.bytedeco.opencv.opencv_core.CvSeqReader;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public class CvChainPtReader extends CvSeqReader {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvSeqBlock block();

    public native CvChainPtReader block(CvSeqBlock cvSeqBlock);

    @Cast({"schar*"})
    public native BytePointer block_max();

    public native CvChainPtReader block_max(BytePointer bytePointer);

    @Cast({"schar*"})
    public native BytePointer block_min();

    public native CvChainPtReader block_min(BytePointer bytePointer);

    @Cast({"char"})
    public native byte code();

    public native CvChainPtReader code(byte b);

    public native int delta_index();

    public native CvChainPtReader delta_index(int i);

    @Cast({"schar"})
    public native byte deltas(int i, int i2);

    @MemberGetter
    @Cast({"schar(* /*[8]*/ )[2]"})
    public native BytePointer deltas();

    public native CvChainPtReader deltas(int i, int i2, byte b);

    public native int header_size();

    public native CvChainPtReader header_size(int i);

    @Cast({"schar*"})
    public native BytePointer prev_elem();

    public native CvChainPtReader prev_elem(BytePointer bytePointer);

    @ByRef
    public native CvPoint pt();

    public native CvChainPtReader pt(CvPoint cvPoint);

    @Cast({"schar*"})
    public native BytePointer ptr();

    public native CvChainPtReader ptr(BytePointer bytePointer);

    public native CvSeq seq();

    public native CvChainPtReader seq(CvSeq cvSeq);

    static {
        Loader.load();
    }

    public CvChainPtReader() {
        super((Pointer) null);
        allocate();
    }

    public CvChainPtReader(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvChainPtReader(Pointer pointer) {
        super(pointer);
    }

    public CvChainPtReader position(long j) {
        return (CvChainPtReader) super.position(j);
    }

    public CvChainPtReader getPointer(long j) {
        return new CvChainPtReader((Pointer) this).position(this.position + j);
    }
}
