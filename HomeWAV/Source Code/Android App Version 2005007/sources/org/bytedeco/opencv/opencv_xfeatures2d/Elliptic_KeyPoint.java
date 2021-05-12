package org.bytedeco.opencv.opencv_xfeatures2d;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_core.KeyPoint;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.Size2f;
import org.bytedeco.opencv.presets.opencv_xfeatures2d;

@Namespace("cv::xfeatures2d")
@Properties(inherit = {opencv_xfeatures2d.class})
@NoOffset
public class Elliptic_KeyPoint extends KeyPoint {
    private native void allocate();

    private native void allocate(@ByVal Point2f point2f, float f, @ByVal Size size, float f2, float f3);

    private native void allocateArray(long j);

    @ByRef
    public native Size2f axes();

    public native Elliptic_KeyPoint axes(Size2f size2f);

    public native float si();

    public native Elliptic_KeyPoint si(float f);

    @ByRef
    @Cast({"cv::Matx23f*"})
    public native FloatPointer transf();

    public native Elliptic_KeyPoint transf(FloatPointer floatPointer);

    static {
        Loader.load();
    }

    public Elliptic_KeyPoint(Pointer pointer) {
        super(pointer);
    }

    public Elliptic_KeyPoint(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Elliptic_KeyPoint position(long j) {
        return (Elliptic_KeyPoint) super.position(j);
    }

    public Elliptic_KeyPoint getPointer(long j) {
        return new Elliptic_KeyPoint((Pointer) this).position(this.position + j);
    }

    public Elliptic_KeyPoint() {
        super((Pointer) null);
        allocate();
    }

    public Elliptic_KeyPoint(@ByVal Point2f point2f, float f, @ByVal Size size, float f2, float f3) {
        super((Pointer) null);
        allocate(point2f, f, size, f2, f3);
    }
}
