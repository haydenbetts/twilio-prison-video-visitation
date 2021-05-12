package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.opencv_imgproc.AbstractIplConvKernel;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class IplConvKernel extends AbstractIplConvKernel {
    private native void allocate();

    private native void allocateArray(long j);

    public native int anchorX();

    public native IplConvKernel anchorX(int i);

    public native int anchorY();

    public native IplConvKernel anchorY(int i);

    public native int nCols();

    public native IplConvKernel nCols(int i);

    public native int nRows();

    public native IplConvKernel nRows(int i);

    public native int nShiftR();

    public native IplConvKernel nShiftR(int i);

    public native IntPointer values();

    public native IplConvKernel values(IntPointer intPointer);

    static {
        Loader.load();
    }

    public IplConvKernel() {
        super((Pointer) null);
        allocate();
    }

    public IplConvKernel(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public IplConvKernel(Pointer pointer) {
        super(pointer);
    }

    public IplConvKernel position(long j) {
        return (IplConvKernel) super.position(j);
    }

    public IplConvKernel getPointer(long j) {
        return new IplConvKernel((Pointer) this).position(this.position + j);
    }
}
