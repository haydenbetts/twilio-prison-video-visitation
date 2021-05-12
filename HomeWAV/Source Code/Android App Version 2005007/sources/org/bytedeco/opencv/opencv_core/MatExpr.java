package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class MatExpr extends Pointer {
    private native void allocate();

    private native void allocate(@ByRef @Const Mat mat);

    private native void allocate(@Const MatOp matOp, int i);

    private native void allocate(@Const MatOp matOp, int i, @ByRef(nullValue = "cv::Mat()") @Const Mat mat, @ByRef(nullValue = "cv::Mat()") @Const Mat mat2, @ByRef(nullValue = "cv::Mat()") @Const Mat mat3, double d, double d2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar);

    private native void allocateArray(long j);

    @ByRef
    public native Mat a();

    public native MatExpr a(Mat mat);

    public native double alpha();

    public native MatExpr alpha(double d);

    @ByVal
    @Name({"operator ()"})
    public native MatExpr apply(@ByRef @Const Range range, @ByRef @Const Range range2);

    @ByVal
    @Name({"operator ()"})
    public native MatExpr apply(@ByRef @Const Rect rect);

    @ByVal
    @Name({"operator cv::Mat"})
    public native Mat asMat();

    @ByRef
    public native Mat b();

    public native MatExpr b(Mat mat);

    public native double beta();

    public native MatExpr beta(double d);

    @ByRef
    public native Mat c();

    public native MatExpr c(Mat mat);

    @ByVal
    public native MatExpr col(int i);

    @ByVal
    public native Mat cross(@ByRef @Const Mat mat);

    @ByVal
    public native MatExpr diag();

    @ByVal
    public native MatExpr diag(int i);

    public native double dot(@ByRef @Const Mat mat);

    public native int flags();

    public native MatExpr flags(int i);

    @ByVal
    public native MatExpr inv();

    @ByVal
    public native MatExpr inv(int i);

    @ByVal
    public native MatExpr mul(@ByRef @Const Mat mat);

    @ByVal
    public native MatExpr mul(@ByRef @Const Mat mat, double d);

    @ByVal
    public native MatExpr mul(@ByRef @Const MatExpr matExpr);

    @ByVal
    public native MatExpr mul(@ByRef @Const MatExpr matExpr, double d);

    public native MatExpr op(MatOp matOp);

    @Const
    public native MatOp op();

    @ByVal
    public native MatExpr row(int i);

    public native MatExpr s(Scalar scalar);

    @ByRef
    public native Scalar s();

    @ByVal
    public native Size size();

    public native void swap(@ByRef MatExpr matExpr);

    @ByVal
    public native MatExpr t();

    public native int type();

    static {
        Loader.load();
    }

    public MatExpr(Pointer pointer) {
        super(pointer);
    }

    public MatExpr(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public MatExpr position(long j) {
        return (MatExpr) super.position(j);
    }

    public MatExpr getPointer(long j) {
        return new MatExpr((Pointer) this).position(this.position + j);
    }

    public MatExpr() {
        super((Pointer) null);
        allocate();
    }

    public MatExpr(@ByRef @Const Mat mat) {
        super((Pointer) null);
        allocate(mat);
    }

    public MatExpr(@Const MatOp matOp, int i, @ByRef(nullValue = "cv::Mat()") @Const Mat mat, @ByRef(nullValue = "cv::Mat()") @Const Mat mat2, @ByRef(nullValue = "cv::Mat()") @Const Mat mat3, double d, double d2, @ByRef(nullValue = "cv::Scalar()") @Const Scalar scalar) {
        super((Pointer) null);
        allocate(matOp, i, mat, mat2, mat3, d, d2, scalar);
    }

    public MatExpr(@Const MatOp matOp, int i) {
        super((Pointer) null);
        allocate(matOp, i);
    }
}
