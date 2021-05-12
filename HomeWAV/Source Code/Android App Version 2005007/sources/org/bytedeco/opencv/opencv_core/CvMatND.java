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
public class CvMatND extends AbstractCvMatND {
    private native void allocate();

    private native void allocateArray(long j);

    @Name({"data.db"})
    public native DoublePointer data_db();

    public native CvMatND data_db(DoublePointer doublePointer);

    @Name({"data.fl"})
    public native FloatPointer data_fl();

    public native CvMatND data_fl(FloatPointer floatPointer);

    @Name({"data.i"})
    public native IntPointer data_i();

    public native CvMatND data_i(IntPointer intPointer);

    @Cast({"uchar*"})
    @Name({"data.ptr"})
    public native BytePointer data_ptr();

    public native CvMatND data_ptr(BytePointer bytePointer);

    @Name({"data.s"})
    public native ShortPointer data_s();

    public native CvMatND data_s(ShortPointer shortPointer);

    @Name({"dim", ".size"})
    public native int dim_size(int i);

    public native CvMatND dim_size(int i, int i2);

    @Name({"dim", ".step"})
    public native int dim_step(int i);

    public native CvMatND dim_step(int i, int i2);

    public native int dims();

    public native CvMatND dims(int i);

    public native int hdr_refcount();

    public native CvMatND hdr_refcount(int i);

    public native IntPointer refcount();

    public native CvMatND refcount(IntPointer intPointer);

    public native int type();

    public native CvMatND type(int i);

    public /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    static {
        Loader.load();
    }

    public CvMatND() {
        super((Pointer) null);
        allocate();
    }

    public CvMatND(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public CvMatND(Pointer pointer) {
        super(pointer);
    }

    public CvMatND position(long j) {
        return (CvMatND) super.position(j);
    }

    public CvMatND getPointer(long j) {
        return new CvMatND((Pointer) this).position(this.position + j);
    }
}
