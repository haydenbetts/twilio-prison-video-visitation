package org.bytedeco.opencv.opencv_core;

import java.nio.IntBuffer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.annotation.StdVector;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class KeyPoint extends Pointer {
    private native void allocate();

    private native void allocate(float f, float f2, float f3);

    private native void allocate(float f, float f2, float f3, float f4, float f5, int i, int i2);

    private native void allocate(@ByVal Point2f point2f, float f);

    private native void allocate(@ByVal Point2f point2f, float f, float f2, float f3, int i, int i2);

    private native void allocateArray(long j);

    public static native void convert(@ByRef @Const KeyPointVector keyPointVector, @ByRef Point2fVector point2fVector);

    public static native void convert(@ByRef @Const KeyPointVector keyPointVector, @ByRef Point2fVector point2fVector, @StdVector IntBuffer intBuffer);

    public static native void convert(@ByRef @Const KeyPointVector keyPointVector, @ByRef Point2fVector point2fVector, @StdVector IntPointer intPointer);

    public static native void convert(@ByRef @Const KeyPointVector keyPointVector, @ByRef Point2fVector point2fVector, @StdVector int[] iArr);

    public static native void convert(@ByRef @Const Point2fVector point2fVector, @ByRef KeyPointVector keyPointVector);

    public static native void convert(@ByRef @Const Point2fVector point2fVector, @ByRef KeyPointVector keyPointVector, float f, float f2, int i, int i2);

    public static native float overlap(@ByRef @Const KeyPoint keyPoint, @ByRef @Const KeyPoint keyPoint2);

    public native float angle();

    public native KeyPoint angle(float f);

    public native int class_id();

    public native KeyPoint class_id(int i);

    @Cast({"size_t"})
    public native long hash();

    public native int octave();

    public native KeyPoint octave(int i);

    public native KeyPoint pt(Point2f point2f);

    @ByRef
    public native Point2f pt();

    public native float response();

    public native KeyPoint response(float f);

    public native float size();

    public native KeyPoint size(float f);

    static {
        Loader.load();
    }

    public KeyPoint(Pointer pointer) {
        super(pointer);
    }

    public KeyPoint(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public KeyPoint position(long j) {
        return (KeyPoint) super.position(j);
    }

    public KeyPoint getPointer(long j) {
        return new KeyPoint((Pointer) this).position(this.position + j);
    }

    public KeyPoint() {
        super((Pointer) null);
        allocate();
    }

    public KeyPoint(@ByVal Point2f point2f, float f, float f2, float f3, int i, int i2) {
        super((Pointer) null);
        allocate(point2f, f, f2, f3, i, i2);
    }

    public KeyPoint(@ByVal Point2f point2f, float f) {
        super((Pointer) null);
        allocate(point2f, f);
    }

    public KeyPoint(float f, float f2, float f3, float f4, float f5, int i, int i2) {
        super((Pointer) null);
        allocate(f, f2, f3, f4, f5, i, i2);
    }

    public KeyPoint(float f, float f2, float f3) {
        super((Pointer) null);
        allocate(f, f2, f3);
    }
}
