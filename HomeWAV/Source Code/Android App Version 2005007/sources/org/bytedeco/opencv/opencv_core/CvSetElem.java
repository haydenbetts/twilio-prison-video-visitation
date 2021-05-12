package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSetElem extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int flags();

    public native CvSetElem flags(int i);

    public native CvSetElem next_free();

    public native CvSetElem next_free(CvSetElem cvSetElem);

    static {
        Loader.load();
    }

    public CvSetElem() {
        super((Pointer) null);
        allocate();
    }

    public CvSetElem(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSetElem(Pointer pointer) {
        super(pointer);
    }

    public CvSetElem position(long j) {
        return (CvSetElem) super.position(j);
    }

    public CvSetElem getPointer(long j) {
        return new CvSetElem((Pointer) this).position(this.position + j);
    }
}
