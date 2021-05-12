package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.NoOffset;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Namespace("cv")
@Properties(inherit = {opencv_core.class})
@NoOffset
public class Moments extends Pointer {
    private native void allocate();

    private native void allocate(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10);

    private native void allocateArray(long j);

    public native double m00();

    public native Moments m00(double d);

    public native double m01();

    public native Moments m01(double d);

    public native double m02();

    public native Moments m02(double d);

    public native double m03();

    public native Moments m03(double d);

    public native double m10();

    public native Moments m10(double d);

    public native double m11();

    public native Moments m11(double d);

    public native double m12();

    public native Moments m12(double d);

    public native double m20();

    public native Moments m20(double d);

    public native double m21();

    public native Moments m21(double d);

    public native double m30();

    public native Moments m30(double d);

    public native double mu02();

    public native Moments mu02(double d);

    public native double mu03();

    public native Moments mu03(double d);

    public native double mu11();

    public native Moments mu11(double d);

    public native double mu12();

    public native Moments mu12(double d);

    public native double mu20();

    public native Moments mu20(double d);

    public native double mu21();

    public native Moments mu21(double d);

    public native double mu30();

    public native Moments mu30(double d);

    public native double nu02();

    public native Moments nu02(double d);

    public native double nu03();

    public native Moments nu03(double d);

    public native double nu11();

    public native Moments nu11(double d);

    public native double nu12();

    public native Moments nu12(double d);

    public native double nu20();

    public native Moments nu20(double d);

    public native double nu21();

    public native Moments nu21(double d);

    public native double nu30();

    public native Moments nu30(double d);

    static {
        Loader.load();
    }

    public Moments(Pointer pointer) {
        super(pointer);
    }

    public Moments(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public Moments position(long j) {
        return (Moments) super.position(j);
    }

    public Moments getPointer(long j) {
        return new Moments((Pointer) this).position(this.position + j);
    }

    public Moments() {
        super((Pointer) null);
        allocate();
    }

    public Moments(double d, double d2, double d3, double d4, double d5, double d6, double d7, double d8, double d9, double d10) {
        super((Pointer) null);
        allocate(d, d2, d3, d4, d5, d6, d7, d8, d9, d10);
    }
}
