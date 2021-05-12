package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public class CvHuMoments extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native double hu1();

    public native CvHuMoments hu1(double d);

    public native double hu2();

    public native CvHuMoments hu2(double d);

    public native double hu3();

    public native CvHuMoments hu3(double d);

    public native double hu4();

    public native CvHuMoments hu4(double d);

    public native double hu5();

    public native CvHuMoments hu5(double d);

    public native double hu6();

    public native CvHuMoments hu6(double d);

    public native double hu7();

    public native CvHuMoments hu7(double d);

    static {
        Loader.load();
    }

    public CvHuMoments() {
        super((Pointer) null);
        allocate();
    }

    public CvHuMoments(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvHuMoments(Pointer pointer) {
        super(pointer);
    }

    public CvHuMoments position(long j) {
        return (CvHuMoments) super.position(j);
    }

    public CvHuMoments getPointer(long j) {
        return new CvHuMoments((Pointer) this).position(this.position + j);
    }
}
