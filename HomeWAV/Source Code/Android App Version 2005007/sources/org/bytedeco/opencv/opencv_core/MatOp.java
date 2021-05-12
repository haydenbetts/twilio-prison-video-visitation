package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
public class MatOp extends Pointer {
    public native void abs(@ByRef @Const MatExpr matExpr, @ByRef MatExpr matExpr2);

    public native void add(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2, @ByRef MatExpr matExpr3);

    public native void add(@ByRef @Const MatExpr matExpr, @ByRef @Const Scalar scalar, @ByRef MatExpr matExpr2);

    public native void assign(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void assign(@ByRef @Const MatExpr matExpr, @ByRef Mat mat, int i);

    public native void augAssignAdd(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void augAssignAnd(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void augAssignDivide(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void augAssignMultiply(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void augAssignOr(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void augAssignSubtract(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void augAssignXor(@ByRef @Const MatExpr matExpr, @ByRef Mat mat);

    public native void diag(@ByRef @Const MatExpr matExpr, int i, @ByRef MatExpr matExpr2);

    public native void divide(double d, @ByRef @Const MatExpr matExpr, @ByRef MatExpr matExpr2);

    public native void divide(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2, @ByRef MatExpr matExpr3);

    public native void divide(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2, @ByRef MatExpr matExpr3, double d);

    @Cast({"bool"})
    public native boolean elementWise(@ByRef @Const MatExpr matExpr);

    public native void invert(@ByRef @Const MatExpr matExpr, int i, @ByRef MatExpr matExpr2);

    public native void matmul(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2, @ByRef MatExpr matExpr3);

    public native void multiply(@ByRef @Const MatExpr matExpr, double d, @ByRef MatExpr matExpr2);

    public native void multiply(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2, @ByRef MatExpr matExpr3);

    public native void multiply(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2, @ByRef MatExpr matExpr3, double d);

    public native void roi(@ByRef @Const MatExpr matExpr, @ByRef @Const Range range, @ByRef @Const Range range2, @ByRef MatExpr matExpr2);

    @ByVal
    public native Size size(@ByRef @Const MatExpr matExpr);

    public native void subtract(@ByRef @Const MatExpr matExpr, @ByRef @Const MatExpr matExpr2, @ByRef MatExpr matExpr3);

    public native void subtract(@ByRef @Const Scalar scalar, @ByRef @Const MatExpr matExpr, @ByRef MatExpr matExpr2);

    public native void transpose(@ByRef @Const MatExpr matExpr, @ByRef MatExpr matExpr2);

    public native int type(@ByRef @Const MatExpr matExpr);

    static {
        Loader.load();
    }

    public MatOp(Pointer pointer) {
        super(pointer);
    }
}
