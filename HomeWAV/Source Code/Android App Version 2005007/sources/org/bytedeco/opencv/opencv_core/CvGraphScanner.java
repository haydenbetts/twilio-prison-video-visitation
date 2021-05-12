package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvGraphScanner extends AbstractCvGraphScanner {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvGraphScanner dst(CvGraphVtx cvGraphVtx);

    public native CvGraphVtx dst();

    public native CvGraphEdge edge();

    public native CvGraphScanner edge(CvGraphEdge cvGraphEdge);

    public native CvGraph graph();

    public native CvGraphScanner graph(CvGraph cvGraph);

    public native int index();

    public native CvGraphScanner index(int i);

    public native int mask();

    public native CvGraphScanner mask(int i);

    public native CvGraphScanner stack(CvSeq cvSeq);

    public native CvSeq stack();

    public native CvGraphScanner vtx(CvGraphVtx cvGraphVtx);

    public native CvGraphVtx vtx();

    static {
        Loader.load();
    }

    public CvGraphScanner() {
        super((Pointer) null);
        allocate();
    }

    public CvGraphScanner(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvGraphScanner(Pointer pointer) {
        super(pointer);
    }

    public CvGraphScanner position(long j) {
        return (CvGraphScanner) super.position(j);
    }

    public CvGraphScanner getPointer(long j) {
        return new CvGraphScanner((Pointer) this).position(this.position + j);
    }
}
