package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::Point3_<double>"})
@NoOffset
public class Point3d extends DoublePointer {
    private native void allocate();

    private native void allocate(double d, double d2, double d3);

    private native void allocate(@ByRef @Const Point2d point2d);

    private native void allocate(@ByRef @Const Point3d point3d);

    private native void allocateArray(long j);

    @ByVal
    public native Point3d cross(@ByRef @Const Point3d point3d);

    public native double ddot(@ByRef @Const Point3d point3d);

    public native double dot(@ByRef @Const Point3d point3d);

    @ByRef
    @Name({"operator ="})
    public native Point3d put(@ByRef @Const Point3d point3d);

    public native double x();

    public native Point3d x(double d);

    public native double y();

    public native Point3d y(double d);

    public native double z();

    public native Point3d z(double d);

    static {
        Loader.load();
    }

    public Point3d(Pointer pointer) {
        super(pointer);
    }

    public Point3d(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Point3d position(long j) {
        return (Point3d) super.position(j);
    }

    public Point3d getPointer(long j) {
        return new Point3d(this).position(this.position + j);
    }

    public Point3d() {
        super((Pointer) null);
        allocate();
    }

    public Point3d(double d, double d2, double d3) {
        super((Pointer) null);
        allocate(d, d2, d3);
    }

    public Point3d(@ByRef @Const Point3d point3d) {
        super((Pointer) null);
        allocate(point3d);
    }

    public Point3d(@ByRef @Const Point2d point2d) {
        super((Pointer) null);
        allocate(point2d);
    }
}
