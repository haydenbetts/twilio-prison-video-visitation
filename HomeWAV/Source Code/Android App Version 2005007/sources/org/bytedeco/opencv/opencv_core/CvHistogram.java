package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_imgproc.AbstractCvHistogram;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvHistogram extends AbstractCvHistogram {
    private native void allocate();

    private native void allocateArray(long j);

    public native CvArr bins();

    public native CvHistogram bins(CvArr cvArr);

    public native CvHistogram mat(CvMatND cvMatND);

    @ByRef
    public native CvMatND mat();

    public native float thresh(int i, int i2);

    @MemberGetter
    @Cast({"float(*)[2]"})
    public native FloatPointer thresh();

    public native CvHistogram thresh(int i, int i2, float f);

    public native FloatPointer thresh2(int i);

    @Cast({"float**"})
    public native PointerPointer thresh2();

    public native CvHistogram thresh2(int i, FloatPointer floatPointer);

    public native CvHistogram thresh2(PointerPointer pointerPointer);

    public native int type();

    public native CvHistogram type(int i);

    static {
        Loader.load();
    }

    public CvHistogram() {
        super((Pointer) null);
        allocate();
    }

    public CvHistogram(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvHistogram(Pointer pointer) {
        super(pointer);
    }

    public CvHistogram position(long j) {
        return (CvHistogram) super.position(j);
    }

    public CvHistogram getPointer(long j) {
        return new CvHistogram((Pointer) this).position(this.position + j);
    }
}
