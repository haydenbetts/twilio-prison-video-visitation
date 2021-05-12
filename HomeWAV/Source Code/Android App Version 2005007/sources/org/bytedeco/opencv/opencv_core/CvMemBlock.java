package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvMemBlock extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvMemBlock next();

    public native CvMemBlock next(CvMemBlock cvMemBlock);

    public native CvMemBlock prev();

    public native CvMemBlock prev(CvMemBlock cvMemBlock);

    static {
        Loader.load();
    }

    public CvMemBlock() {
        super((Pointer) null);
        allocate();
    }

    public CvMemBlock(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvMemBlock(Pointer pointer) {
        super(pointer);
    }

    public CvMemBlock position(long j) {
        return (CvMemBlock) super.position(j);
    }

    public CvMemBlock getPointer(long j) {
        return new CvMemBlock((Pointer) this).position(this.position + j);
    }
}
