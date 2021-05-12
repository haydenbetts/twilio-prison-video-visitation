package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
@Name({"cv::Rect_<double>"})
@NoOffset
public class Rect2d extends DoublePointer {
    private native void allocate();

    private native void allocate(double d, double d2, double d3, double d4);

    private native void allocate(@ByRef @Const Point2d point2d, @ByRef @Const Point2d point2d2);

    private native void allocate(@ByRef @Const Point2d point2d, @ByRef @Const Size2d size2d);

    private native void allocate(@ByRef @Const Rect2d rect2d);

    private native void allocateArray(long j);

    public native double area();

    @ByVal
    public native Point2d br();

    @Cast({"bool"})
    public native boolean contains(@ByRef @Const Point2d point2d);

    @Cast({"bool"})
    public native boolean empty();

    public native double height();

    public native Rect2d height(double d);

    @ByRef
    @Name({"operator ="})
    public native Rect2d put(@ByRef @Const Rect2d rect2d);

    @ByVal
    public native Size2d size();

    @ByVal
    public native Point2d tl();

    public native double width();

    public native Rect2d width(double d);

    public native double x();

    public native Rect2d x(double d);

    public native double y();

    public native Rect2d y(double d);

    static {
        Loader.load();
    }

    public Rect2d(Pointer pointer) {
        super(pointer);
    }

    public Rect2d(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Rect2d position(long j) {
        return (Rect2d) super.position(j);
    }

    public Rect2d getPointer(long j) {
        return new Rect2d(this).position(this.position + j);
    }

    public Rect2d() {
        super((Pointer) null);
        allocate();
    }

    public Rect2d(double d, double d2, double d3, double d4) {
        super((Pointer) null);
        allocate(d, d2, d3, d4);
    }

    public Rect2d(@ByRef @Const Rect2d rect2d) {
        super((Pointer) null);
        allocate(rect2d);
    }

    public Rect2d(@ByRef @Const Point2d point2d, @ByRef @Const Size2d size2d) {
        super((Pointer) null);
        allocate(point2d, size2d);
    }

    public Rect2d(@ByRef @Const Point2d point2d, @ByRef @Const Point2d point2d2) {
        super((Pointer) null);
        allocate(point2d, point2d2);
    }
}
