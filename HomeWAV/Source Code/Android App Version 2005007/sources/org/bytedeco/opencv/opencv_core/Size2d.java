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
@Name({"cv::Size_<double>"})
@NoOffset
public class Size2d extends DoublePointer {
    private native void allocate();

    private native void allocate(double d, double d2);

    private native void allocate(@ByRef @Const Point2d point2d);

    private native void allocate(@ByRef @Const Size2d size2d);

    private native void allocateArray(long j);

    public native double area();

    public native double aspectRatio();

    @Cast({"bool"})
    public native boolean empty();

    public native double height();

    public native Size2d height(double d);

    @ByRef
    @Name({"operator ="})
    public native Size2d put(@ByRef @Const Size2d size2d);

    public native double width();

    public native Size2d width(double d);

    static {
        Loader.load();
    }

    public Size2d(Pointer pointer) {
        super(pointer);
    }

    public Size2d(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Size2d position(long j) {
        return (Size2d) super.position(j);
    }

    public Size2d getPointer(long j) {
        return new Size2d(this).position(this.position + j);
    }

    public Size2d() {
        super((Pointer) null);
        allocate();
    }

    public Size2d(double d, double d2) {
        super((Pointer) null);
        allocate(d, d2);
    }

    public Size2d(@ByRef @Const Size2d size2d) {
        super((Pointer) null);
        allocate(size2d);
    }

    public Size2d(@ByRef @Const Point2d point2d) {
        super((Pointer) null);
        allocate(point2d);
    }
}
