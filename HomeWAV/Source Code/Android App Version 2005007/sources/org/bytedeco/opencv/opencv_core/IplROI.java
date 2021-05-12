package org.bytedeco.opencv.opencv_core;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.opencv.presets.opencv_core;

@Properties(inherit = {opencv_core.class})
public class IplROI extends Pointer {
    private native void allocate();

    private native void allocateArray(long j);

    public native int coi();

    public native IplROI coi(int i);

    public native int height();

    public native IplROI height(int i);

    public native int width();

    public native IplROI width(int i);

    public native int xOffset();

    public native IplROI xOffset(int i);

    public native int yOffset();

    public native IplROI yOffset(int i);

    static {
        Loader.load();
    }

    public IplROI() {
        super((Pointer) null);
        allocate();
    }

    public IplROI(long j) {
        super((Pointer) null);
        allocateArray(j);
    }

    public IplROI(Pointer pointer) {
        super(pointer);
    }

    public IplROI position(long j) {
        return (IplROI) super.position(j);
    }

    public IplROI getPointer(long j) {
        return new IplROI((Pointer) this).position(this.position + j);
    }
}
