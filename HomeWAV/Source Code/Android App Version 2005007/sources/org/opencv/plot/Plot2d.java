package org.opencv.plot;

import org.opencv.core.Algorithm;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Plot2d extends Algorithm {
    private static native long create_0(long j);

    private static native long create_1(long j, long j2);

    private static native void delete(long j);

    private static native void render_0(long j, long j2);

    private static native void setGridLinesNumber_0(long j, int i);

    private static native void setInvertOrientation_0(long j, boolean z);

    private static native void setMaxX_0(long j, double d);

    private static native void setMaxY_0(long j, double d);

    private static native void setMinX_0(long j, double d);

    private static native void setMinY_0(long j, double d);

    private static native void setNeedPlotLine_0(long j, boolean z);

    private static native void setPlotAxisColor_0(long j, double d, double d2, double d3, double d4);

    private static native void setPlotBackgroundColor_0(long j, double d, double d2, double d3, double d4);

    private static native void setPlotGridColor_0(long j, double d, double d2, double d3, double d4);

    private static native void setPlotLineColor_0(long j, double d, double d2, double d3, double d4);

    private static native void setPlotLineWidth_0(long j, int i);

    private static native void setPlotSize_0(long j, int i, int i2);

    private static native void setPlotTextColor_0(long j, double d, double d2, double d3, double d4);

    private static native void setPointIdxToPrint_0(long j, int i);

    private static native void setShowGrid_0(long j, boolean z);

    private static native void setShowText_0(long j, boolean z);

    protected Plot2d(long j) {
        super(j);
    }

    public static Plot2d __fromPtr__(long j) {
        return new Plot2d(j);
    }

    public static Plot2d create(Mat mat) {
        return __fromPtr__(create_0(mat.nativeObj));
    }

    public static Plot2d create(Mat mat, Mat mat2) {
        return __fromPtr__(create_1(mat.nativeObj, mat2.nativeObj));
    }

    public void render(Mat mat) {
        render_0(this.nativeObj, mat.nativeObj);
    }

    public void setGridLinesNumber(int i) {
        setGridLinesNumber_0(this.nativeObj, i);
    }

    public void setInvertOrientation(boolean z) {
        setInvertOrientation_0(this.nativeObj, z);
    }

    public void setMaxX(double d) {
        setMaxX_0(this.nativeObj, d);
    }

    public void setMaxY(double d) {
        setMaxY_0(this.nativeObj, d);
    }

    public void setMinX(double d) {
        setMinX_0(this.nativeObj, d);
    }

    public void setMinY(double d) {
        setMinY_0(this.nativeObj, d);
    }

    public void setNeedPlotLine(boolean z) {
        setNeedPlotLine_0(this.nativeObj, z);
    }

    public void setPlotAxisColor(Scalar scalar) {
        setPlotAxisColor_0(this.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3]);
    }

    public void setPlotBackgroundColor(Scalar scalar) {
        setPlotBackgroundColor_0(this.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3]);
    }

    public void setPlotGridColor(Scalar scalar) {
        setPlotGridColor_0(this.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3]);
    }

    public void setPlotLineColor(Scalar scalar) {
        setPlotLineColor_0(this.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3]);
    }

    public void setPlotLineWidth(int i) {
        setPlotLineWidth_0(this.nativeObj, i);
    }

    public void setPlotSize(int i, int i2) {
        setPlotSize_0(this.nativeObj, i, i2);
    }

    public void setPlotTextColor(Scalar scalar) {
        setPlotTextColor_0(this.nativeObj, scalar.val[0], scalar.val[1], scalar.val[2], scalar.val[3]);
    }

    public void setPointIdxToPrint(int i) {
        setPointIdxToPrint_0(this.nativeObj, i);
    }

    public void setShowGrid(boolean z) {
        setShowGrid_0(this.nativeObj, z);
    }

    public void setShowText(boolean z) {
        setShowText_0(this.nativeObj, z);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        delete(this.nativeObj);
    }
}
