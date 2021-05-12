package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvBox2D extends AbstractCvBox2D {
    private native void allocate();

    private native void allocateArray(long j);

    public native float angle();

    public native CvBox2D angle(float f);

    @ByVal
    @Name({"operator cv::RotatedRect"})
    public native RotatedRect asRotatedRect();

    public native CvBox2D center(CvPoint2D32f cvPoint2D32f);

    @ByRef
    public native CvPoint2D32f center();

    public native CvBox2D size(CvSize2D32f cvSize2D32f);

    @ByRef
    public native CvSize2D32f size();

    static {
        Loader.load();
    }

    public CvBox2D() {
        super((Pointer) null);
        allocate();
    }

    public CvBox2D(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvBox2D(Pointer pointer) {
        super(pointer);
    }

    public CvBox2D position(long j) {
        return (CvBox2D) super.position(j);
    }

    public CvBox2D getPointer(long j) {
        return new CvBox2D((Pointer) this).position(this.position + j);
    }
}
