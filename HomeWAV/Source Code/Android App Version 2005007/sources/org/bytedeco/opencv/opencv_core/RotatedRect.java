package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class RotatedRect extends FloatPointer {
    private native void allocate();

    private native void allocate(@ByRef @Const Point2f point2f, @ByRef @Const Point2f point2f2, @ByRef @Const Point2f point2f3);

    private native void allocate(@ByRef @Const Point2f point2f, @ByRef @Const Size2f size2f, float f);

    private native void allocateArray(long j);

    public native float angle();

    public native RotatedRect angle(float f);

    @ByVal
    public native Rect boundingRect();

    @ByVal
    public native Rect2f boundingRect2f();

    @ByRef
    public native Point2f center();

    public native RotatedRect center(Point2f point2f);

    public native void points(Point2f point2f);

    public native RotatedRect size(Size2f size2f);

    @ByRef
    public native Size2f size();

    static {
        Loader.load();
    }

    public RotatedRect(Pointer pointer) {
        super(pointer);
    }

    public RotatedRect(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public RotatedRect position(long j) {
        return (RotatedRect) super.position(j);
    }

    public RotatedRect getPointer(long j) {
        return new RotatedRect((Pointer) this).position(this.position + j);
    }

    public RotatedRect() {
        super((Pointer) null);
        allocate();
    }

    public RotatedRect(@ByRef @Const Point2f point2f, @ByRef @Const Size2f size2f, float f) {
        super((Pointer) null);
        allocate(point2f, size2f, f);
    }

    public RotatedRect(@ByRef @Const Point2f point2f, @ByRef @Const Point2f point2f2, @ByRef @Const Point2f point2f3) {
        super((Pointer) null);
        allocate(point2f, point2f2, point2f3);
    }
}
