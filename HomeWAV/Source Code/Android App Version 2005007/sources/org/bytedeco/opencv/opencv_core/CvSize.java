package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvSize extends AbstractCvSize {
    private native void allocate();

    private native void allocateArray(long j);

    public native int height();

    public native CvSize height(int i);

    public native int width();

    public native CvSize width(int i);

    static {
        Loader.load();
    }

    public CvSize(Pointer pointer) {
        super(pointer);
    }

    public CvSize(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvSize position(long j) {
        return (CvSize) super.position(j);
    }

    public CvSize getPointer(long j) {
        return new CvSize((Pointer) this).position(this.position + j);
    }

    public CvSize() {
        super((Pointer) null);
        allocate();
    }
}
