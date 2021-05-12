package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.CvSeq;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public class CvConnectedComp extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double area();

    public native CvConnectedComp area(double d);

    public native CvSeq contour();

    public native CvConnectedComp contour(CvSeq cvSeq);

    @ByRef
    public native CvRect rect();

    public native CvConnectedComp rect(CvRect cvRect);

    @ByRef
    public native CvScalar value();

    public native CvConnectedComp value(CvScalar cvScalar);

    static {
        Loader.load();
    }

    public CvConnectedComp() {
        super((Pointer) null);
        allocate();
    }

    public CvConnectedComp(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvConnectedComp(Pointer pointer) {
        super(pointer);
    }

    public CvConnectedComp position(long j) {
        return (CvConnectedComp) super.position(j);
    }

    public CvConnectedComp getPointer(long j) {
        return new CvConnectedComp((Pointer) this).position(this.position + j);
    }
}
