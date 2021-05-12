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
@Name({"cv::Size_<float>"})
@NoOffset
public class Size2f extends FloatPointer {
    private native void allocate();

    private native void allocate(float f, float f2);

    private native void allocate(@ByRef @Const Point2f point2f);

    private native void allocate(@ByRef @Const Size2f size2f);

    private native void allocateArray(long j);

    public native float area();

    public native double aspectRatio();

    @Cast({"bool"})
    public native boolean empty();

    public native float height();

    public native Size2f height(float f);

    @ByRef
    @Name({"operator ="})
    public native Size2f put(@ByRef @Const Size2f size2f);

    public native float width();

    public native Size2f width(float f);

    static {
        Loader.load();
    }

    public Size2f(Pointer pointer) {
        super(pointer);
    }

    public Size2f(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Size2f position(long j) {
        return (Size2f) super.position(j);
    }

    public Size2f getPointer(long j) {
        return new Size2f(this).position(this.position + j);
    }

    public Size2f() {
        super((Pointer) null);
        allocate();
    }

    public Size2f(float f, float f2) {
        super((Pointer) null);
        allocate(f, f2);
    }

    public Size2f(@ByRef @Const Size2f size2f) {
        super((Pointer) null);
        allocate(size2f);
    }

    public Size2f(@ByRef @Const Point2f point2f) {
        super((Pointer) null);
        allocate(point2f);
    }
}
