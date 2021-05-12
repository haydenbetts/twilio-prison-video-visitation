package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvPoint2D64f extends AbstractCvPoint2D64f {
    private native void allocate();

    private native void allocateArray(long j);

    public native double x();

    public native CvPoint2D64f x(double d);

    public native double y();

    public native CvPoint2D64f y(double d);

    static {
        Loader.load();
    }

    public CvPoint2D64f(Pointer pointer) {
        super(pointer);
    }

    public CvPoint2D64f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvPoint2D64f position(long j) {
        return (CvPoint2D64f) super.position(j);
    }

    public CvPoint2D64f getPointer(long j) {
        return new CvPoint2D64f((Pointer) this).position(this.position + j);
    }

    public CvPoint2D64f() {
        super((Pointer) null);
        allocate();
    }
}
