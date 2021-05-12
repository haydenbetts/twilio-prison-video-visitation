package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class IplConvKernelFP extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int anchorX();

    public native IplConvKernelFP anchorX(int i);

    public native int anchorY();

    public native IplConvKernelFP anchorY(int i);

    public native int nCols();

    public native IplConvKernelFP nCols(int i);

    public native int nRows();

    public native IplConvKernelFP nRows(int i);

    public native FloatPointer values();

    public native IplConvKernelFP values(FloatPointer floatPointer);

    static {
        Loader.load();
    }

    public IplConvKernelFP() {
        super((Pointer) null);
        allocate();
    }

    public IplConvKernelFP(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public IplConvKernelFP(Pointer pointer) {
        super(pointer);
    }

    public IplConvKernelFP position(long j) {
        return (IplConvKernelFP) super.position(j);
    }

    public IplConvKernelFP getPointer(long j) {
        return new IplConvKernelFP((Pointer) this).position(this.position + j);
    }
}
