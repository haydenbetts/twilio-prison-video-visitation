package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class CvMat extends AbstractCvMat {
    private native void allocate();

    private native void allocateArray(long j);

    public native int cols();

    public native CvMat cols(int i);

    @Name({"data.db"})
    public native DoublePointer data_db();

    public native CvMat data_db(DoublePointer doublePointer);

    @Name({"data.fl"})
    public native FloatPointer data_fl();

    public native CvMat data_fl(FloatPointer floatPointer);

    @Name({"data.i"})
    public native IntPointer data_i();

    public native CvMat data_i(IntPointer intPointer);

    @Cast({"uchar*"})
    @Name({"data.ptr"})
    public native BytePointer data_ptr();

    public native CvMat data_ptr(BytePointer bytePointer);

    @Name({"data.s"})
    public native ShortPointer data_s();

    public native CvMat data_s(ShortPointer shortPointer);

    public native int hdr_refcount();

    public native CvMat hdr_refcount(int i);

    public native int height();

    public native CvMat height(int i);

    public native IntPointer refcount();

    public native CvMat refcount(IntPointer intPointer);

    public native int rows();

    public native CvMat rows(int i);

    public native int step();

    public native CvMat step(int i);

    public native int type();

    public native CvMat type(int i);

    public native int width();

    public native CvMat width(int i);

    public /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    static {
        Loader.load();
    }

    public CvMat() {
        super((Pointer) null);
        allocate();
    }

    public CvMat(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvMat(Pointer pointer) {
        super(pointer);
    }

    public CvMat position(long j) {
        return (CvMat) super.position(j);
    }

    public CvMat getPointer(long j) {
        return new CvMat((Pointer) this).position(this.position + j);
    }
}
