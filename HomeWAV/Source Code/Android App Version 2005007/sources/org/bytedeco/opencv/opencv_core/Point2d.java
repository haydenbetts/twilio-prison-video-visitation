package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::Point_<double>"})
@NoOffset
public class Point2d extends DoublePointer {
    private native void allocate();

    private native void allocate(double d, double d2);

    private native void allocate(@ByRef @Const Point2d point2d);

    private native void allocate(@ByRef @Const Size2d size2d);

    private native void allocateArray(long j);

    public native double cross(@ByRef @Const Point2d point2d);

    public native double ddot(@ByRef @Const Point2d point2d);

    public native double dot(@ByRef @Const Point2d point2d);

    @Cast({"bool"})
    public native boolean inside(@ByRef @Const Rect2d rect2d);

    @ByRef
    @Name({"operator ="})
    public native Point2d put(@ByRef @Const Point2d point2d);

    public native double x();

    public native Point2d x(double d);

    public native double y();

    public native Point2d y(double d);

    static {
        Loader.load();
    }

    public Point2d(Pointer pointer) {
        super(pointer);
    }

    public Point2d(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Point2d position(long j) {
        return (Point2d) super.position(j);
    }

    public Point2d getPointer(long j) {
        return new Point2d(this).position(this.position + j);
    }

    public Point2d() {
        super((Pointer) null);
        allocate();
    }

    public Point2d(double d, double d2) {
        super((Pointer) null);
        allocate(d, d2);
    }

    public Point2d(@ByRef @Const Point2d point2d) {
        super((Pointer) null);
        allocate(point2d);
    }

    public Point2d(@ByRef @Const Size2d size2d) {
        super((Pointer) null);
        allocate(size2d);
    }
}
