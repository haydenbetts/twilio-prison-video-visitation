package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvSize2D32f extends AbstractCvSize2D32f {
    private native void allocate();

    private native void allocateArray(long j);

    public native float height();

    public native CvSize2D32f height(float f);

    public native float width();

    public native CvSize2D32f width(float f);

    static {
        Loader.load();
    }

    public CvSize2D32f(Pointer pointer) {
        super(pointer);
    }

    public CvSize2D32f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSize2D32f position(long j) {
        return (CvSize2D32f) super.position(j);
    }

    public CvSize2D32f getPointer(long j) {
        return new CvSize2D32f((Pointer) this).position(this.position + j);
    }

    public CvSize2D32f() {
        super((Pointer) null);
        allocate();
    }
}
