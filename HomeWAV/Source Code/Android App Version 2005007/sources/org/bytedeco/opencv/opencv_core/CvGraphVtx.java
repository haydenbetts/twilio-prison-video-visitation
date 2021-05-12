package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvGraphVtx extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvGraphEdge first();

    public native CvGraphVtx first(CvGraphEdge cvGraphEdge);

    public native int flags();

    public native CvGraphVtx flags(int i);

    static {
        Loader.load();
    }

    public CvGraphVtx() {
        super((Pointer) null);
        allocate();
    }

    public CvGraphVtx(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvGraphVtx(Pointer pointer) {
        super(pointer);
    }

    public CvGraphVtx position(long j) {
        return (CvGraphVtx) super.position(j);
    }

    public CvGraphVtx getPointer(long j) {
        return new CvGraphVtx((Pointer) this).position(this.position + j);
    }
}
