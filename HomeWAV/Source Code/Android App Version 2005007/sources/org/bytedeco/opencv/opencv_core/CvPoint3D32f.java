package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvPoint3D32f extends AbstractCvPoint3D32f {
    private native void allocate();

    private native void allocateArray(long j);

    public native float x();

    public native CvPoint3D32f x(float f);

    public native float y();

    public native CvPoint3D32f y(float f);

    public native float z();

    public native CvPoint3D32f z(float f);

    static {
        Loader.load();
    }

    public CvPoint3D32f(Pointer pointer) {
        super(pointer);
    }

    public CvPoint3D32f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvPoint3D32f position(long j) {
        return (CvPoint3D32f) super.position(j);
    }

    public CvPoint3D32f getPointer(long j) {
        return new CvPoint3D32f((Pointer) this).position(this.position + j);
    }

    public CvPoint3D32f() {
        super((Pointer) null);
        allocate();
    }
}
