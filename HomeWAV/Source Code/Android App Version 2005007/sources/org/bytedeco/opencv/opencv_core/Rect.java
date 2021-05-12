package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
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
@Name({"cv::Rect_<int>"})
@NoOffset
public class Rect extends IntPointer {
    private native void allocate();

    private native void allocate(int i, int i2, int i3, int i4);

    private native void allocate(@ByRef @Const Point point, @ByRef @Const Point point2);

    private native void allocate(@ByRef @Const Point point, @ByRef @Const Size size);

    private native void allocate(@ByRef @Const Rect rect);

    private native void allocateArray(long j);

    public native int area();

    @ByVal
    public native Point br();

    @Cast({"bool"})
    public native boolean contains(@ByRef @Const Point point);

    @Cast({"bool"})
    public native boolean empty();

    public native int height();

    public native Rect height(int i);

    @ByRef
    @Name({"operator ="})
    public native Rect put(@ByRef @Const Rect rect);

    @ByVal
    public native Size size();

    @ByVal
    public native Point tl();

    public native int width();

    public native Rect width(int i);

    public native int x();

    public native Rect x(int i);

    public native int y();

    public native Rect y(int i);

    static {
        Loader.load();
    }

    public Rect(Pointer pointer) {
        super(pointer);
    }

    public Rect(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Rect position(long j) {
        return (Rect) super.position(j);
    }

    public Rect getPointer(long j) {
        return new Rect(this).position(this.position + j);
    }

    public Rect() {
        super((Pointer) null);
        allocate();
    }

    public Rect(int i, int i2, int i3, int i4) {
        super((Pointer) null);
        allocate(i, i2, i3, i4);
    }

    public Rect(@ByRef @Const Rect rect) {
        super((Pointer) null);
        allocate(rect);
    }

    public Rect(@ByRef @Const Point point, @ByRef @Const Size size) {
        super((Pointer) null);
        allocate(point, size);
    }

    public Rect(@ByRef @Const Point point, @ByRef @Const Point point2) {
        super((Pointer) null);
        allocate(point, point2);
    }
}
