package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvPoint3D64f extends AbstractCvPoint3D64f {
    private native void allocate();

    private native void allocateArray(long j);

    public native double x();

    public native CvPoint3D64f x(double d);

    public native double y();

    public native CvPoint3D64f y(double d);

    public native double z();

    public native CvPoint3D64f z(double d);

    static {
        Loader.load();
    }

    public CvPoint3D64f(Pointer pointer) {
        super(pointer);
    }

    public CvPoint3D64f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvPoint3D64f position(long j) {
        return (CvPoint3D64f) super.position(j);
    }

    public CvPoint3D64f getPointer(long j) {
        return new CvPoint3D64f((Pointer) this).position(this.position + j);
    }

    public CvPoint3D64f() {
        super((Pointer) null);
        allocate();
    }
}
