package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvRect extends AbstractCvRect {
    private native void allocate();

    private native void allocateArray(long j);

    public native int height();

    public native CvRect height(int i);

    public native int width();

    public native CvRect width(int i);

    public native int x();

    public native CvRect x(int i);

    public native int y();

    public native CvRect y(int i);

    static {
        Loader.load();
    }

    public CvRect(Pointer pointer) {
        super(pointer);
    }

    public CvRect(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvRect position(long j) {
        return (CvRect) super.position(j);
    }

    public CvRect getPointer(long j) {
        return new CvRect((Pointer) this).position(this.position + j);
    }

    public CvRect() {
        super((Pointer) null);
        allocate();
    }
}
