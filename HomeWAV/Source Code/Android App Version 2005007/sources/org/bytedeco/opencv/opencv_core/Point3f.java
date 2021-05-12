package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
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
@Name({"cv::Point3_<float>"})
@NoOffset
public class Point3f extends FloatPointer {
    private native void allocate();

    private native void allocate(float f, float f2, float f3);

    private native void allocate(@ByRef @Const Point2f point2f);

    private native void allocate(@ByRef @Const Point3f point3f);

    private native void allocateArray(long j);

    @ByVal
    public native Point3f cross(@ByRef @Const Point3f point3f);

    public native double ddot(@ByRef @Const Point3f point3f);

    public native float dot(@ByRef @Const Point3f point3f);

    @ByRef
    @Name({"operator ="})
    public native Point3f put(@ByRef @Const Point3f point3f);

    public native float x();

    public native Point3f x(float f);

    public native float y();

    public native Point3f y(float f);

    public native float z();

    public native Point3f z(float f);

    static {
        Loader.load();
    }

    public Point3f(Pointer pointer) {
        super(pointer);
    }

    public Point3f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Point3f position(long j) {
        return (Point3f) super.position(j);
    }

    public Point3f getPointer(long j) {
        return new Point3f(this).position(this.position + j);
    }

    public Point3f() {
        super((Pointer) null);
        allocate();
    }

    public Point3f(float f, float f2, float f3) {
        super((Pointer) null);
        allocate(f, f2, f3);
    }

    public Point3f(@ByRef @Const Point3f point3f) {
        super((Pointer) null);
        allocate(point3f);
    }

    public Point3f(@ByRef @Const Point2f point2f) {
        super((Pointer) null);
        allocate(point2f);
    }
}
