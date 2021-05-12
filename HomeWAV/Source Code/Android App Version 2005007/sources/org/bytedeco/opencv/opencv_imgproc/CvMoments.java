package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Properties(inherit = {opencv_imgproc.class})
public class CvMoments extends AbstractCvMoments {
    private native void allocate();

    private native void allocateArray(long j);

    public native double inv_sqrt_m00();

    public native CvMoments inv_sqrt_m00(double d);

    public native double m00();

    public native CvMoments m00(double d);

    public native double m01();

    public native CvMoments m01(double d);

    public native double m02();

    public native CvMoments m02(double d);

    public native double m03();

    public native CvMoments m03(double d);

    public native double m10();

    public native CvMoments m10(double d);

    public native double m11();

    public native CvMoments m11(double d);

    public native double m12();

    public native CvMoments m12(double d);

    public native double m20();

    public native CvMoments m20(double d);

    public native double m21();

    public native CvMoments m21(double d);

    public native double m30();

    public native CvMoments m30(double d);

    public native double mu02();

    public native CvMoments mu02(double d);

    public native double mu03();

    public native CvMoments mu03(double d);

    public native double mu11();

    public native CvMoments mu11(double d);

    public native double mu12();

    public native CvMoments mu12(double d);

    public native double mu20();

    public native CvMoments mu20(double d);

    public native double mu21();

    public native CvMoments mu21(double d);

    public native double mu30();

    public native CvMoments mu30(double d);

    static {
        Loader.load();
    }

    public CvMoments() {
        super((Pointer) null);
        allocate();
    }

    public CvMoments(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvMoments(Pointer pointer) {
        super(pointer);
    }

    public CvMoments position(long j) {
        return (CvMoments) super.position(j);
    }

    public CvMoments getPointer(long j) {
        return new CvMoments((Pointer) this).position(this.position + j);
    }
}
