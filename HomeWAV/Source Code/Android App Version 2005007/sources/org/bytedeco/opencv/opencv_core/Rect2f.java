package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
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
@Name({"cv::Rect_<float>"})
@NoOffset
public class Rect2f extends FloatPointer {
    private native void allocate();

    private native void allocate(float f, float f2, float f3, float f4);

    private native void allocate(@ByRef @Const Point2f point2f, @ByRef @Const Point2f point2f2);

    private native void allocate(@ByRef @Const Point2f point2f, @ByRef @Const Size2f size2f);

    private native void allocate(@ByRef @Const Rect2f rect2f);

    private native void allocateArray(long j);

    public native float area();

    @ByVal
    public native Point2f br();

    @Cast({"bool"})
    public native boolean contains(@ByRef @Const Point2f point2f);

    @Cast({"bool"})
    public native boolean empty();

    public native float height();

    public native Rect2f height(float f);

    @ByRef
    @Name({"operator ="})
    public native Rect2f put(@ByRef @Const Rect2f rect2f);

    @ByVal
    public native Size2f size();

    @ByVal
    public native Point2f tl();

    public native float width();

    public native Rect2f width(float f);

    public native float x();

    public native Rect2f x(float f);

    public native float y();

    public native Rect2f y(float f);

    static {
        Loader.load();
    }

    public Rect2f(Pointer pointer) {
        super(pointer);
    }

    public Rect2f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Rect2f position(long j) {
        return (Rect2f) super.position(j);
    }

    public Rect2f getPointer(long j) {
        return new Rect2f(this).position(this.position + j);
    }

    public Rect2f() {
        super((Pointer) null);
        allocate();
    }

    public Rect2f(float f, float f2, float f3, float f4) {
        super((Pointer) null);
        allocate(f, f2, f3, f4);
    }

    public Rect2f(@ByRef @Const Rect2f rect2f) {
        super((Pointer) null);
        allocate(rect2f);
    }

    public Rect2f(@ByRef @Const Point2f point2f, @ByRef @Const Size2f size2f) {
        super((Pointer) null);
        allocate(point2f, size2f);
    }

    public Rect2f(@ByRef @Const Point2f point2f, @ByRef @Const Point2f point2f2) {
        super((Pointer) null);
        allocate(point2f, point2f2);
    }
}
