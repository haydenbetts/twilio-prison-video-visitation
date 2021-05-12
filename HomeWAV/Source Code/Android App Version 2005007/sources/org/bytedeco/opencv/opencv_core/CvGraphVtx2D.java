package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvGraphVtx2D extends CvGraphVtx {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvGraphEdge first();

    public native CvGraphVtx2D first(CvGraphEdge cvGraphEdge);

    public native int flags();

    public native CvGraphVtx2D flags(int i);

    public native CvGraphVtx2D ptr(CvPoint2D32f cvPoint2D32f);

    public native CvPoint2D32f ptr();

    static {
        Loader.load();
    }

    public CvGraphVtx2D() {
        super((Pointer) null);
        allocate();
    }

    public CvGraphVtx2D(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvGraphVtx2D(Pointer pointer) {
        super(pointer);
    }

    public CvGraphVtx2D position(long j) {
        return (CvGraphVtx2D) super.position(j);
    }

    public CvGraphVtx2D getPointer(long j) {
        return new CvGraphVtx2D((Pointer) this).position(this.position + j);
    }
}
