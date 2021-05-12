package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvGraphEdge extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int flags();

    public native CvGraphEdge flags(int i);

    @MemberGetter
    @Cast({"CvGraphEdge**"})
    public native PointerPointer next();

    public native CvGraphEdge next(int i);

    public native CvGraphEdge next(int i, CvGraphEdge cvGraphEdge);

    @MemberGetter
    @Cast({"CvGraphVtx**"})
    public native PointerPointer vtx();

    public native CvGraphEdge vtx(int i, CvGraphVtx cvGraphVtx);

    public native CvGraphVtx vtx(int i);

    public native float weight();

    public native CvGraphEdge weight(float f);

    static {
        Loader.load();
    }

    public CvGraphEdge() {
        super((Pointer) null);
        allocate();
    }

    public CvGraphEdge(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvGraphEdge(Pointer pointer) {
        super(pointer);
    }

    public CvGraphEdge position(long j) {
        return (CvGraphEdge) super.position(j);
    }

    public CvGraphEdge getPointer(long j) {
        return new CvGraphEdge((Pointer) this).position(this.position + j);
    }
}
