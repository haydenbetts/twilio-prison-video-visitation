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
@Name({"cv::Size_<int>"})
@NoOffset
public class Size extends IntPointer {
    private native void allocate();

    private native void allocate(int i, int i2);

    private native void allocate(@ByRef @Const Point point);

    private native void allocate(@ByRef @Const Size size);

    private native void allocateArray(long j);

    public native int area();

    public native double aspectRatio();

    @Cast({"bool"})
    public native boolean empty();

    public native int height();

    public native Size height(int i);

    @ByRef
    @Name({"operator ="})
    public native Size put(@ByRef @Const Size size);

    public native int width();

    public native Size width(int i);

    static {
        Loader.load();
    }

    public Size(Pointer pointer) {
        super(pointer);
    }

    public Size(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Size position(long j) {
        return (Size) super.position(j);
    }

    public Size getPointer(long j) {
        return new Size(this).position(this.position + j);
    }

    public Size() {
        super((Pointer) null);
        allocate();
    }

    public Size(int i, int i2) {
        super((Pointer) null);
        allocate(i, i2);
    }

    public Size(@ByRef @Const Size size) {
        super((Pointer) null);
        allocate(size);
    }

    public Size(@ByRef @Const Point point) {
        super((Pointer) null);
        allocate(point);
    }
}
