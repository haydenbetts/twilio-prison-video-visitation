package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvTreeNodeIterator extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int level();

    public native CvTreeNodeIterator level(int i);

    public native int max_level();

    public native CvTreeNodeIterator max_level(int i);

    @Const
    public native Pointer node();

    public native CvTreeNodeIterator node(Pointer pointer);

    static {
        Loader.load();
    }

    public CvTreeNodeIterator() {
        super((Pointer) null);
        allocate();
    }

    public CvTreeNodeIterator(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvTreeNodeIterator(Pointer pointer) {
        super(pointer);
    }

    public CvTreeNodeIterator position(long j) {
        return (CvTreeNodeIterator) super.position(j);
    }

    public CvTreeNodeIterator getPointer(long j) {
        return new CvTreeNodeIterator((Pointer) this).position(this.position + j);
    }
}
