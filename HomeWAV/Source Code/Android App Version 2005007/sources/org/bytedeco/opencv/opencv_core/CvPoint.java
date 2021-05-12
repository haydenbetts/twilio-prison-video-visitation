package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@NoOffset
public class CvPoint extends AbstractCvPoint {
    private native void allocate();

    private native void allocateArray(long j);

    public native int x();

    public native CvPoint x(int i);

    public native int y();

    public native CvPoint y(int i);

    static {
        Loader.load();
    }

    public CvPoint(Pointer pointer) {
        super(pointer);
    }

    public CvPoint(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvPoint position(long j) {
        return (CvPoint) super.position(j);
    }

    public CvPoint getPointer(long j) {
        return new CvPoint((Pointer) this).position(this.position + j);
    }

    public CvPoint() {
        super((Pointer) null);
        allocate();
    }
}
