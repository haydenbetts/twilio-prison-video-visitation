package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSeqBlock extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int count();

    public native CvSeqBlock count(int i);

    @Cast({"schar*"})
    public native BytePointer data();

    public native CvSeqBlock data(BytePointer bytePointer);

    public native CvSeqBlock next();

    public native CvSeqBlock next(CvSeqBlock cvSeqBlock);

    public native CvSeqBlock prev();

    public native CvSeqBlock prev(CvSeqBlock cvSeqBlock);

    public native int start_index();

    public native CvSeqBlock start_index(int i);

    static {
        Loader.load();
    }

    public CvSeqBlock() {
        super((Pointer) null);
        allocate();
    }

    public CvSeqBlock(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSeqBlock(Pointer pointer) {
        super(pointer);
    }

    public CvSeqBlock position(long j) {
        return (CvSeqBlock) super.position(j);
    }

    public CvSeqBlock getPointer(long j) {
        return new CvSeqBlock((Pointer) this).position(this.position + j);
    }
}
