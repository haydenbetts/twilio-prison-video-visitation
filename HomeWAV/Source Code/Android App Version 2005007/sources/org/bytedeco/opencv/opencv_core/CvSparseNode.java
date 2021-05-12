package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvSparseNode extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    @Cast({"unsigned"})
    public native int hashval();

    public native CvSparseNode hashval(int i);

    public native CvSparseNode next();

    public native CvSparseNode next(CvSparseNode cvSparseNode);

    static {
        Loader.load();
    }

    public CvSparseNode() {
        super((Pointer) null);
        allocate();
    }

    public CvSparseNode(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSparseNode(Pointer pointer) {
        super(pointer);
    }

    public CvSparseNode position(long j) {
        return (CvSparseNode) super.position(j);
    }

    public CvSparseNode getPointer(long j) {
        return new CvSparseNode((Pointer) this).position(this.position + j);
    }
}
