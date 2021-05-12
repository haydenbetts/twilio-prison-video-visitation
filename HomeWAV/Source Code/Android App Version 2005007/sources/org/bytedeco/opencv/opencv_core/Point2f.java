package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
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
@Name({"cv::Point_<float>"})
@NoOffset
public class Point2f extends FloatPointer {
    private native void allocate();

    private native void allocate(float f, float f2);

    private native void allocate(@ByRef @Const Point2f point2f);

    private native void allocate(@ByRef @Const Size2f size2f);

    private native void allocateArray(long j);

    public native double cross(@ByRef @Const Point2f point2f);

    public native double ddot(@ByRef @Const Point2f point2f);

    public native float dot(@ByRef @Const Point2f point2f);

    @Cast({"bool"})
    public native boolean inside(@ByRef @Const Rect2f rect2f);

    @ByRef
    @Name({"operator ="})
    public native Point2f put(@ByRef @Const Point2f point2f);

    public native float x();

    public native Point2f x(float f);

    public native float y();

    public native Point2f y(float f);

    static {
        Loader.load();
    }

    public Point2f(Pointer pointer) {
        super(pointer);
    }

    public Point2f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Point2f position(long j) {
        return (Point2f) super.position(j);
    }

    public Point2f getPointer(long j) {
        return new Point2f(this).position(this.position + j);
    }

    public Point2f() {
        super((Pointer) null);
        allocate();
    }

    public Point2f(float f, float f2) {
        super((Pointer) null);
        allocate(f, f2);
    }

    public Point2f(@ByRef @Const Point2f point2f) {
        super((Pointer) null);
        allocate(point2f);
    }

    public Point2f(@ByRef @Const Size2f size2f) {
        super((Pointer) null);
        allocate(size2f);
    }
}
