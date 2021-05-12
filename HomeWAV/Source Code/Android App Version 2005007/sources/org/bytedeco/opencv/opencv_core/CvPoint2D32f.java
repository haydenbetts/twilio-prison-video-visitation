package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvPoint2D32f extends AbstractCvPoint2D32f {
    private native void allocate();

    private native void allocateArray(long j);

    public native float x();

    public native CvPoint2D32f x(float f);

    public native float y();

    public native CvPoint2D32f y(float f);

    static {
        Loader.load();
    }

    public CvPoint2D32f(Pointer pointer) {
        super(pointer);
    }

    public CvPoint2D32f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvPoint2D32f position(long j) {
        return (CvPoint2D32f) super.position(j);
    }

    public CvPoint2D32f getPointer(long j) {
        return new CvPoint2D32f((Pointer) this).position(this.position + j);
    }

    public CvPoint2D32f() {
        super((Pointer) null);
        allocate();
    }
}
