package org.bytedeco.opencv.opencv_imgproc;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Rect;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.presets.opencv_imgproc;

@Namespace("cv")
@Properties(inherit = {opencv_imgproc.class})
@NoOffset
public class LineIterator extends Pointer {
    private native void allocate(@ByRef @Const Mat mat, @ByVal Point point, @ByVal Point point2);

    private native void allocate(@ByRef @Const Mat mat, @ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z);

    private native void allocate(@ByVal Point point, @ByVal Point point2);

    private native void allocate(@ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z);

    private native void allocate(@ByVal Rect rect, @ByVal Point point, @ByVal Point point2);

    private native void allocate(@ByVal Rect rect, @ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z);

    private native void allocate(@ByVal Size size, @ByVal Point point, @ByVal Point point2);

    private native void allocate(@ByVal Size size, @ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z);

    public native int count();

    public native LineIterator count(int i);

    public native int elemSize();

    public native LineIterator elemSize(int i);

    public native int err();

    public native LineIterator err(int i);

    @ByRef
    @Name({"operator ++"})
    public native LineIterator increment();

    @ByVal
    @Name({"operator ++"})
    public native LineIterator increment(int i);

    public native void init(@Const Mat mat, @ByVal Rect rect, @ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z);

    public native int minusDelta();

    public native LineIterator minusDelta(int i);

    public native int minusShift();

    public native LineIterator minusShift(int i);

    public native int minusStep();

    public native LineIterator minusStep(int i);

    @Cast({"uchar*"})
    @Name({"operator *"})
    public native BytePointer multiply();

    @ByRef
    public native Point p();

    public native LineIterator p(Point point);

    public native int plusDelta();

    public native LineIterator plusDelta(int i);

    public native int plusShift();

    public native LineIterator plusShift(int i);

    public native int plusStep();

    public native LineIterator plusStep(int i);

    @ByVal
    public native Point pos();

    public native LineIterator ptmode(boolean z);

    @Cast({"bool"})
    public native boolean ptmode();

    @Cast({"uchar*"})
    public native BytePointer ptr();

    public native LineIterator ptr(BytePointer bytePointer);

    @Cast({"const uchar*"})
    public native BytePointer ptr0();

    public native LineIterator ptr0(BytePointer bytePointer);

    public native int step();

    public native LineIterator step(int i);

    static {
        Loader.load();
    }

    public LineIterator(Pointer pointer) {
        super(pointer);
    }

    public LineIterator(@ByRef @Const Mat mat, @ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(mat, point, point2, i, z);
    }

    public LineIterator(@ByRef @Const Mat mat, @ByVal Point point, @ByVal Point point2) {
        super((Pointer) null);
        allocate(mat, point, point2);
    }

    public LineIterator(@ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(point, point2, i, z);
    }

    public LineIterator(@ByVal Point point, @ByVal Point point2) {
        super((Pointer) null);
        allocate(point, point2);
    }

    public LineIterator(@ByVal Size size, @ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(size, point, point2, i, z);
    }

    public LineIterator(@ByVal Size size, @ByVal Point point, @ByVal Point point2) {
        super((Pointer) null);
        allocate(size, point, point2);
    }

    public LineIterator(@ByVal Rect rect, @ByVal Point point, @ByVal Point point2, int i, @Cast({"bool"}) boolean z) {
        super((Pointer) null);
        allocate(rect, point, point2, i, z);
    }

    public LineIterator(@ByVal Rect rect, @ByVal Point point, @ByVal Point point2) {
        super((Pointer) null);
        allocate(rect, point, point2);
    }
}
