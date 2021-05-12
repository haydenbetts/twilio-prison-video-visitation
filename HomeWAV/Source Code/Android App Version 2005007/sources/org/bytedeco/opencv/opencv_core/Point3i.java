package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
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
@Name({"cv::Point3_<int>"})
@NoOffset
public class Point3i extends IntPointer {
    private native void allocate();

    private native void allocate(int i, int i2, int i3);

    private native void allocate(@ByRef @Const Point3i point3i);

    private native void allocate(@ByRef @Const Point point);

    private native void allocateArray(long j);

    @ByVal
    public native Point3i cross(@ByRef @Const Point3i point3i);

    public native double ddot(@ByRef @Const Point3i point3i);

    public native int dot(@ByRef @Const Point3i point3i);

    @ByRef
    @Name({"operator ="})
    public native Point3i put(@ByRef @Const Point3i point3i);

    public native int x();

    public native Point3i x(int i);

    public native int y();

    public native Point3i y(int i);

    public native int z();

    public native Point3i z(int i);

    static {
        Loader.load();
    }

    public Point3i(Pointer pointer) {
        super(pointer);
    }

    public Point3i(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Point3i position(long j) {
        return (Point3i) super.position(j);
    }

    public Point3i getPointer(long j) {
        return new Point3i(this).position(this.position + j);
    }

    public Point3i() {
        super((Pointer) null);
        allocate();
    }

    public Point3i(int i, int i2, int i3) {
        super((Pointer) null);
        allocate(i, i2, i3);
    }

    public Point3i(@ByRef @Const Point3i point3i) {
        super((Pointer) null);
        allocate(point3i);
    }

    public Point3i(@ByRef @Const Point point) {
        super((Pointer) null);
        allocate(point);
    }
}
