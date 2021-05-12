package org.bytedeco.opencv.opencv_calib3d;

import java.nio.DoubleBuffer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByPtrRef;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvTermCriteria;
import org.bytedeco.opencv.presets.opencv_calib3d;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_calib3d.class})
@NoOffset
public class CvLevMarq extends Pointer {
    public static final int CALC_J = 2;
    public static final int CHECK_ERR = 3;
    public static final int DONE = 0;
    public static final int STARTED = 1;

    private native void allocate();

    private native void allocate(int i, int i2);

    private native void allocate(int i, int i2, @ByVal(nullValue = "CvTermCriteria(cvTermCriteria(CV_TERMCRIT_EPS+CV_TERMCRIT_ITER,30,DBL_EPSILON))") CvTermCriteria cvTermCriteria, @Cast({"bool"}) boolean z);

    private native void allocateArray(long j);

    public native CvLevMarq J(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat J();

    public native CvLevMarq JtErr(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat JtErr();

    public native CvLevMarq JtJ(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat JtJ();

    public native CvLevMarq JtJN(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat JtJN();

    public native CvLevMarq JtJV(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat JtJV();

    public native CvLevMarq JtJW(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat JtJW();

    public native void clear();

    public native CvLevMarq completeSymmFlag(boolean z);

    @Cast({"bool"})
    public native boolean completeSymmFlag();

    public native CvLevMarq criteria(CvTermCriteria cvTermCriteria);

    @ByRef
    public native CvTermCriteria criteria();

    public native CvLevMarq err(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat err();

    public native double errNorm();

    public native CvLevMarq errNorm(double d);

    public native void init(int i, int i2);

    public native void init(int i, int i2, @ByVal(nullValue = "CvTermCriteria(cvTermCriteria(CV_TERMCRIT_EPS+CV_TERMCRIT_ITER,30,DBL_EPSILON))") CvTermCriteria cvTermCriteria, @Cast({"bool"}) boolean z);

    public native int iters();

    public native CvLevMarq iters(int i);

    public native int lambdaLg10();

    public native CvLevMarq lambdaLg10(int i);

    public native CvLevMarq mask(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat mask();

    public native CvLevMarq param(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat param();

    public native double prevErrNorm();

    public native CvLevMarq prevErrNorm(double d);

    public native CvLevMarq prevParam(CvMat cvMat);

    @opencv_core.Ptr
    public native CvMat prevParam();

    public native int solveMethod();

    public native CvLevMarq solveMethod(int i);

    public native int state();

    public native CvLevMarq state(int i);

    public native void step();

    @Cast({"bool"})
    public native boolean update(@ByPtrRef @Const CvMat cvMat, @ByPtrRef CvMat cvMat2, @ByPtrRef CvMat cvMat3);

    @Cast({"bool"})
    public native boolean updateAlt(@ByPtrRef @Const CvMat cvMat, @ByPtrRef CvMat cvMat2, @ByPtrRef CvMat cvMat3, @ByPtrRef DoubleBuffer doubleBuffer);

    @Cast({"bool"})
    public native boolean updateAlt(@ByPtrRef @Const CvMat cvMat, @ByPtrRef CvMat cvMat2, @ByPtrRef CvMat cvMat3, @ByPtrRef DoublePointer doublePointer);

    @Cast({"bool"})
    public native boolean updateAlt(@ByPtrRef @Const CvMat cvMat, @ByPtrRef CvMat cvMat2, @ByPtrRef CvMat cvMat3, @ByPtrRef double[] dArr);

    static {
        Loader.load();
    }

    public CvLevMarq(Pointer pointer) {
        super(pointer);
    }

    public CvLevMarq(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvLevMarq position(long j) {
        return (CvLevMarq) super.position(j);
    }

    public CvLevMarq getPointer(long j) {
        return new CvLevMarq((Pointer) this).position(this.position + j);
    }

    public CvLevMarq() {
        super((Pointer) null);
        allocate();
    }

    public CvLevMarq(int i, int i2, @ByVal(nullValue = "CvTermCriteria(cvTermCriteria(CV_TERMCRIT_EPS+CV_TERMCRIT_ITER,30,DBL_EPSILON))") CvTermCriteria cvTermCriteria, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(i, i2, cvTermCriteria, z);
    }

    public CvLevMarq(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }
}
