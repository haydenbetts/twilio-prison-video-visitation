package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
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
@Name({"cv::Point_<int>"})
@NoOffset
public class Point extends IntPointer {
    private native void allocate();

    private native void allocate(int i, int i2);

    private native void allocate(@ByRef @Const Point point);

    private native void allocate(@ByRef @Const Size size);

    private native void allocateArray(long j);

    public native double cross(@ByRef @Const Point point);

    public native double ddot(@ByRef @Const Point point);

    public native int dot(@ByRef @Const Point point);

    @Cast({"bool"})
    public native boolean inside(@ByRef @Const Rect rect);

    @ByRef
    @Name({"operator ="})
    public native Point put(@ByRef @Const Point point);

    public native int x();

    public native Point x(int i);

    public native int y();

    public native Point y(int i);

    static {
        Loader.load();
    }

    public Point(Pointer pointer) {
        super(pointer);
    }

    public Point(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Point position(long j) {
        return (Point) super.position(j);
    }

    public Point getPointer(long j) {
        return new Point(this).position(this.position + j);
    }

    public Point() {
        super((Pointer) null);
        allocate();
    }

    public Point(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }

    public Point(@ByRef @Const Point point) {
        super((Pointer) null);
        allocate(point);
    }

    public Point(@ByRef @Const Size size) {
        super((Pointer) null);
        allocate(size);
    }
}
